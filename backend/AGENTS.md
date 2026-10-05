# backend/AGENTS.md
## ENT Math AI Backend Rules

> Scope: everything under `/backend`.  
> Root `AGENTS.md` remains mandatory. This file adds backend-specific rules.

# 1. Stack

```text
Java 25 LTS
Spring Boot 4.1.x
Spring Modulith
Spring Security
Spring Session JDBC
Spring Data JPA
Flyway
Bean Validation
Actuator
Micrometer / OpenTelemetry
Maven
PostgreSQL
```

Do not replace these choices inside normal feature work.

# 2. Module Boundaries

Canonical modules:

```text
identity
curriculum
content
assessment
ingestion
learning
planning
tutor
progress
admin
```

Prefer package structure:

```text
com.entmath.<module>
```

Inside a module, use a pragmatic variant of:

```text
api/
application/
domain/
infrastructure/
```

Do not create empty architecture folders merely for appearance.

# 3. Dependency Direction

Business logic belongs in domain/application code.

Forbidden:

```text
domain → Spring MVC
domain → JPA implementation
domain → Gemini SDK
domain → S3 SDK
controller → repository business logic
```

Infrastructure implements ports defined toward the inside.

# 4. Cross-Module Access

A module must not import another module's JPA repository.

Use:

```text
public application interface
domain/application port
domain event
read model
```

Spring Modulith verification tests must protect these boundaries.

# 5. Controllers

Controllers:

```text
parse HTTP
validate transport input
call application use case
map result to DTO
```

Controllers must not contain:

```text
mastery formulas
transaction orchestration
repository queries
AI calls
authorization shortcuts
```

# 6. DTOs

Never expose JPA entities directly.

Separate:

```text
request DTO
response DTO
internal domain object
persistence entity
```

where separation materially helps.

Student question DTOs must never leak hidden answer data.

# 7. Transactions

Application services define transaction boundaries.

Use strong consistency for:

```text
authentication
ownership
answer validation + attempt recording
assessment confirmation
```

Derived projections may update asynchronously where the architecture specifies it.

Do not use distributed transactions in Alpha.

# 8. Persistence

PostgreSQL is the system of record.

Flyway only for schema evolution.

Rules:

```text
released migration is immutable
no manual production DDL
new correction = new migration
```

Use UUIDs for domain identities.

Use JSONB only where flexible structured metadata is genuinely appropriate.

Do not hide the whole domain in JSONB.

# 9. JPA

Avoid:

```text
EAGER everything
bidirectional entity graphs by default
Open Session in View dependence
entity serialization
```

Queries should be explicit enough to avoid accidental N+1 problems.

Use optimistic locking where concurrent editorial updates matter.

# 10. Repositories

Repositories persist aggregates / owned models.

Do not create generic repositories shared by every module.

Repository naming should communicate domain ownership.

# 11. Mathematical Correctness

Only deterministic validators determine correctness.

Validator strategies may include:

```text
ExactOptionValidator
MultiSelectValidator
IntegerValidator
DecimalToleranceValidator
RationalValidator
MatchingValidator
```

LLM calls are forbidden in correctness paths.

# 12. Learning Engine

Implementation must match the versioned algorithms in `docs/04_LEARNING_ENGINE.md`.

Hard rules:

```text
LearningEvidence immutable
SkillMasteryProjection rebuildable
Mastery != Confidence
Retrievability separate from Mastery
Clock injected
algorithm version persisted
```

Never patch a student's mastery directly to fix a bug.

Fix evidence/policy and rebuild.

# 13. Domain Events

Core events include:

```text
AttemptRecorded
AssessmentConfirmed
AssessmentExcluded
LearningEvidenceCreated
LearningEvidenceInvalidated
MasteryChanged
DailyPlanInvalidated
ImportConfirmed
QuestionSuspended
```

Event names describe facts that already happened.

Prefer durable publication for events whose loss would corrupt projections.

# 14. Async Jobs

Use the PostgreSQL-backed queue defined in architecture.

Jobs must be:

```text
idempotent
leased safely
bounded retries
observable
retry-aware
```

Use `FOR UPDATE SKIP LOCKED`-style claiming when appropriate.

No infinite retry.

# 15. AI Integration

Provider SDK calls belong in infrastructure adapters.

Business-facing abstractions must use ports such as:

```text
AiTutorProvider
DocumentExtractionPort
```

Do not spread Gemini SDK usage across modules.

Structured output must still pass semantic validation.

# 16. Tutor

Tutor cannot:

```text
mark answer correct
change AnswerKey
change mastery
publish content
change curriculum
```

Tutor responses must be validated for:

```text
schema
hint boundary
answer leakage
math consistency where possible
```

# 17. Ingestion

Uploaded documents are untrusted.

Before processing:

```text
validate size
validate MIME
detect mismatch
scan/quarantine strategy
private storage
```

No mastery update before reviewed/confirmed assessment data is canonical.

# 18. Security

Use Spring Security consistently.

Every student-owned resource endpoint requires ownership enforcement.

Do not trust IDs from the client.

Use server-side authorization for admin operations.

CSRF remains enabled for browser session flows.

Passwords use a strong Spring Security password encoder.

# 19. Errors

Expose stable error codes.

Example:

```json
{
  "code": "ATTEMPT_SESSION_CLOSED",
  "message": "...",
  "traceId": "..."
}
```

Do not expose stack traces or internal secrets.

# 20. Time

Persist timestamps in UTC.

Use injected `Clock`.

Student-day calculations use profile timezone.

# 21. Observability

Production-relevant use cases should emit useful metrics/logs.

Do not use student ID as a high-cardinality metrics label.

Never log passwords, cookies, raw documents or unnecessary PII.

# 22. Tests

Required as applicable:

```text
JUnit unit tests
Spring integration tests
Testcontainers PostgreSQL
Spring Modulith boundary tests
Flyway migration tests
security/ownership tests
algorithm property tests
```

CI must not require production AI.

# 23. Test Data

Use deterministic fixtures.

Learning tests must control time through `Clock`.

Do not depend on test execution order.

# 24. Coding Style

Prefer:

```text
clear domain names
small application use cases
explicit invariants
immutable value objects where useful
```

Avoid:

```text
GodService
UtilityEverything
generic Map<String,Object> domain contracts
premature abstraction
```

# 25. Definition of Done

Backend change is done when:

```text
module boundary valid
migration verified if needed
unit/integration tests pass
authorization tested
error handling defined
observability added where meaningful
API contract updated
relevant ExecPlan updated
```

# 26. Commands

Phase 0 will finalize exact commands.

Expected:

```bash
./mvnw test
./mvnw verify
./mvnw spring-boot:run
```

On Windows use `mvnw.cmd`.

Keep commands synchronized with root README after Phase 0.
