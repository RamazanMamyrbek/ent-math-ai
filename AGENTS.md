# AGENTS.md
## ENT Math AI — Repository Instructions for Coding Agents

> This file defines repository-wide rules for Antigravity and other coding agents.  
> Read it before making changes.  
> Detailed product and architecture decisions live in `/docs`.

---

# 1. Product Purpose

ENT Math AI is an adaptive bilingual learning platform for Kazakhstan's profile mathematics ENT preparation.

The core product loop is:

```text
Student Activity
→ Learning Evidence
→ Mastery / Confidence / Review State
→ Recommendation
→ Daily Mission
→ Practice
→ New Evidence
```

The product is NOT primarily:

```text
a chatbot
a generic question bank
a video course
a test-only application
```

The main value is:

> Understand what the student knows, determine what they should do next, and measure whether independent performance improves.

---

# 2. Required Reading

Before substantial work, read the relevant documents.

Canonical documentation:

```text
docs/00_CONCEPT.md
docs/02_CURRICULUM_MAP.md
docs/03_DOMAIN.md
docs/04_LEARNING_ENGINE.md
docs/05_AI_TUTOR.md
docs/06_CONTENT_STRATEGY.md
docs/07_MVP_SCOPE.md
docs/08_METRICS_AND_EXPERIMENTS.md
docs/09_ARCHITECTURE.md
docs/10_ROADMAP.md
```

Do not invent a conflicting architecture when an answer already exists there.

For a focused task, read only the relevant subset plus this file and the active ExecPlan.

---

# 3. Repository Map

Target structure:

```text
/
├── AGENTS.md
├── README.md
├── docker-compose.yml
├── .env.example
│
├── docs/
│   ├── 00_CONCEPT.md
│   ├── 02_CURRICULUM_MAP.md
│   ├── 03_DOMAIN.md
│   ├── 04_LEARNING_ENGINE.md
│   ├── 05_AI_TUTOR.md
│   ├── 06_CONTENT_STRATEGY.md
│   ├── 07_MVP_SCOPE.md
│   ├── 08_METRICS_AND_EXPERIMENTS.md
│   ├── 09_ARCHITECTURE.md
│   ├── 10_ROADMAP.md
│   ├── adr/
│   ├── exec-plans/
│   │   ├── active/
│   │   └── completed/
│   └── product-specs/
│
├── backend/
├── frontend/
├── infra/
└── test-fixtures/
```

Subdirectories may contain their own `AGENTS.md`. More specific instructions override this file only within their scope.

---

# 4. Current Architecture

Closed Alpha uses:

```text
Frontend:
Next.js + TypeScript

Backend:
Java + Spring Boot + Spring Modulith

Persistence:
PostgreSQL

Authentication:
Spring Security
Spring Session JDBC
HttpOnly cookies

Files:
S3-compatible object storage
MinIO locally

Async:
PostgreSQL-backed job queue
Worker runtime from same backend codebase

AI:
provider abstraction
Gemini adapter first

Math truth:
deterministic validators

Learning:
deterministic versioned algorithms
```

Architecture style:

> Modular Monolith first.

Do NOT create microservices unless an approved ADR explicitly changes this.

---

# 5. Backend Modules

Canonical module boundaries:

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

Keep `shared` small.

A module must not access another module's repository directly.

Use:

```text
public application interfaces
ports
domain events
read models
```

instead.

---

# 6. Hard Architectural Invariants

These rules MUST NOT be violated.

## Mathematical Truth

LLM never decides whether an answer is correct.

Correct:

```text
AnswerKey
+ deterministic validator
→ correctness
```

Forbidden:

```text
Gemini says "B"
→ mark correct
```

## Learning Evidence

`LearningEvidence` is immutable historical truth.

Do NOT directly mutate mastery after an answer.

Correct:

```text
Attempt / Assessment
→ LearningEvidence
→ SkillMasteryProjection
```

Forbidden:

```text
student.mastery += 10
```

## Mastery

`SkillMasteryProjection` is derived and rebuildable.

It may be recalculated when algorithm versions change.

Never manually edit mastery as a fix.

## Mastery ≠ Confidence

Always preserve the distinction:

```text
mastery
confidence
retrievability
```

They are different concepts.

## Forgetting

Do not reduce mastery simply because time passed.

Use:

```text
ReviewState
Stability
Retrievability
```

according to `04_LEARNING_ENGINE.md`.

## External Assessment Evidence

A total external score does NOT automatically update granular skills.

```text
29/40
```

may affect readiness.

It must not become:

```text
all skills = 72.5%
```

## AI Tutor

Tutor may:

```text
hint
explain
clarify
guide
```

Tutor may NOT:

```text
change AnswerKey
change correctness
change mastery
change curriculum
```

## Full Solution

Viewing a full solution is not independent mastery evidence for the same item.

A new independent transfer question is required.

## Curriculum

Stable `skill_code` values must not be renamed casually.

Curriculum changes require versioning.

## Content

Published `QuestionVersion` is immutable.

A material correction creates a new version or suspends the old one.

## Question Quality

AI-generated questions are drafts only.

They do NOT become `APPROVED` without required validation/review.

## External Content

Private uploaded assessments do not automatically become public Question Bank content.

Never build the bank from leaked/live ENT exam questions.

## Credentials

Never collect or store NTC or other third-party passwords for scraping.

Future integrations require an official API/OAuth-style mechanism.

---

# 7. AI Rules

Business/domain code must not depend directly on Gemini SDK.

Use provider ports/adapters.

Do not add direct AI calls from arbitrary services.

Preferred pattern:

```text
application use case
→ AI port
→ provider adapter
```

Use structured output for machine workflows.

Schema-valid AI output still requires semantic validation.

Student input and uploaded text are always:

```text
UNTRUSTED DATA
```

Never treat them as system instructions.

---

# 8. Privacy Rules

Do not send unnecessary PII to AI providers.

Tutor normally needs only:

```text
language
question
verified answer
verified solution
student attempt
allowed hint level
relevant skill context
```

Do not include:

```text
email
phone
full name
address
payment data
unrelated student history
```

unless a documented use case explicitly requires it.

---

# 9. Security Rules

Authorization is server-side.

Never rely on:

```text
hidden UI
UUID secrecy
frontend checks
```

Student-owned resources must verify ownership.

All raw uploads are private.

Use short-lived signed access where needed.

Do not expose `AnswerKey` or `Solution` in student question DTOs before policy allows.

---

# 10. Database Rules

PostgreSQL is the system of record.

Schema migrations use Flyway.

Rules:

```text
released migration is immutable
no manual production DDL
use a new migration for fixes
```

Do not create a new datastore without an ADR.

Do not add Redis, Kafka, RabbitMQ, Elasticsearch, or a vector database speculatively.

---

# 11. Domain Layer Rules

Domain logic must not depend on:

```text
Spring MVC
HTTP
Next.js
Gemini SDK
S3 SDK
controller DTOs
```

Keep infrastructure behind interfaces/ports where appropriate.

Do not serialize JPA entities directly to the frontend.

---

# 12. Frontend Rules

The backend is the source of truth for domain state.

Do not duplicate:

```text
Mastery formulas
Recommendation formulas
Answer validation
Tutor policy
```

in TypeScript.

Frontend may contain UI state and presentation logic only.

Core UI must support:

```text
RU
KK
```

Do not ship core screens with one language missing.

---

# 13. API Rules

Base path:

```text
/api/v1
```

Use stable machine-readable error codes.

Example:

```json
{
  "code": "IMPORT_REQUIRES_REVIEW",
  "message": "...",
  "traceId": "..."
}
```

Frontend behavior must branch on `code`, not translated message text.

Use idempotency for mutation flows where duplicate requests can corrupt state.

Especially:

```text
attempt submission
assessment confirmation
import confirmation
```

---

# 14. Async Work

Use the PostgreSQL-backed job system defined in architecture.

Jobs must be:

```text
idempotent
retry-aware
observable
bounded
```

Never implement infinite retry loops.

Long-running import/AI work must not occupy request threads unnecessarily.

---

# 15. Observability

Substantial production code must be observable.

Use:

```text
structured logs
metrics
trace/correlation IDs
```

Do not log:

```text
passwords
raw secrets
unnecessary PII
full uploaded documents
```

AI calls should record at least:

```text
feature
provider
model
prompt version
latency
token usage
status
estimated cost
```

---

# 16. Algorithm Versioning

Learning algorithms and policies must be versioned.

Examples:

```text
mastery-v1
confidence-v1
review-v1
recommendation-v1
readiness-v1
source-reliability-v1
```

Do not silently change production formulas while keeping the same version.

---

# 17. Determinism

For identical:

```text
LearningEvidence
policy version
Clock
```

Learning Engine output should be deterministic.

Inject `Clock`.

Do not call `now()` directly throughout core learning logic.

---

# 18. Testing Requirements

At minimum, new behavior requires appropriate:

```text
unit tests
integration tests
architecture tests
E2E tests for critical user flows
```

Learning algorithms require replay/property tests.

Security-sensitive endpoints require authorization tests.

AI functionality requires deterministic mocked tests plus separate real-model evals.

---

# 19. Browser Verification

For user-facing work:

> Tests alone are not enough.

Verify the actual rendered flow.

Core layouts must be checked at mobile width.

Target minimum:

```text
360px
```

---

# 20. AI Evaluation

Do not treat a few manual prompts as sufficient testing.

Tutor/provider changes must eventually pass the golden evaluation suite defined in `05_AI_TUTOR.md`.

Important categories:

```text
RU
KK
math correctness
answer leakage
prompt injection
wrong student reasoning
full solution
safety
```

---

# 21. Content Tests

Approved content must be validated for:

```text
AnswerKey
solution consistency
skill references
difficulty
format
LaTeX
required language versions
```

Question defects can corrupt Learning Evidence, so content correctness is a data integrity concern.

---

# 22. ExecPlan Workflow

For substantial work:

1. read this file;
2. read relevant canonical docs;
3. inspect current code;
4. create/update the active ExecPlan;
5. implement only the intended slice;
6. run relevant tests;
7. verify runtime/browser behavior;
8. update ExecPlan progress/decisions;
9. update canonical docs only if contracts changed.

Do not silently redesign architecture inside implementation.

---

# 23. ADR Workflow

Create an ADR before changing a significant architectural decision.

Examples:

```text
new datastore
new broker
new service boundary
auth strategy
AI provider architecture
learning model family
```

An ADR should contain:

```text
Context
Decision
Alternatives
Consequences
```

---

# 24. Scope Control

Before adding an MVP feature ask:

```text
Which MVP hypothesis does this test?
What current work does it delay?
```

Do not add features merely because they are technically interesting.

Current non-goals include:

```text
native mobile apps
physics
parent dashboard
teacher LMS
voice tutor
social features
microservices
Kubernetes
deep knowledge tracing
```

unless the roadmap is explicitly revised.

---

# 25. Phase Discipline

Follow `10_ROADMAP.md`.

Do not implement future phases simply because they are easy to scaffold.

Examples:

```text
Do not build Import before LearningEvidence is stable.
Do not build Tutor before verified solutions exist.
Do not build Readiness before Assessment exists.
```

---

# 26. Definition of Done

A feature is not done merely because code compiles.

Default DoD:

```text
implementation complete
tests green
build green
authorization correct
runtime verified
browser verified if UI
observability added where relevant
migration verified
docs/ExecPlan updated
```

---

# 27. Commands

Exact commands are finalized during Phase 0 and must then be kept current in this file and README.

Expected shape:

```bash
# local dependencies
docker compose up -d

# backend
cd backend
./mvnw test
./mvnw spring-boot:run

# frontend
cd frontend
npm install
npm run dev
npm run lint
npm run typecheck
npm test
npm run build
```

On Windows, use the repository-provided wrapper variants.

Do not invent undocumented setup steps if automation can encode them.

---

# 28. Change Discipline

Prefer small, focused commits.

Do not combine:

```text
architecture rewrite
UI redesign
database migration
unrelated refactor
```

in one change unless the ExecPlan explicitly requires it.

---

# 29. Simplicity Rule

Use the simplest implementation that respects the architecture.

Good:

```text
one provider port
one Gemini adapter
```

Bad:

```text
multiple factories and abstractions for providers that do not exist
```

Avoid speculative infrastructure.

---

# 30. Core Product Invariant

When forced to choose between:

```text
feature breadth
```

and:

```text
learning correctness
```

choose learning correctness.

When forced to choose between:

```text
more AI
```

and:

```text
deterministic reliable behavior
```

choose deterministic reliable behavior.

When forced to choose between:

```text
more questions
```

and:

```text
better validated questions
```

choose quality.

---

# 31. Immediate Implementation Order

Current next work:

```text
1. backend/AGENTS.md
2. frontend/AGENTS.md
3. initial ADRs
4. docs/exec-plans/active/EP-0001-engineering-harness.md
5. Phase 0 implementation
```

Do not start Phase 1 before Phase 0 acceptance criteria pass.
