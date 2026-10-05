# 09_ARCHITECTURE.md
## ENT Math AI — System Architecture

> **Статус:** canonical architecture specification  
> **Версия:** `ARCH-v1`  
> **Дата:** 5 октября 2026  
> **Связанные документы:**  
> `00_CONCEPT.md`  
> `02_CURRICULUM_MAP.md`  
> `03_DOMAIN.md`  
> `04_LEARNING_ENGINE.md`  
> `05_AI_TUTOR.md`  
> `06_CONTENT_STRATEGY.md`  
> `07_MVP_SCOPE.md`  
> `08_METRICS_AND_EXPERIMENTS.md`  
> `ENT_Math_AI_Technical_Specification_v1.md`
>
> **Назначение:** зафиксировать окончательную архитектуру Closed Alpha / MVP так, чтобы реализация могла идти вертикальными срезами без скрытых архитектурных решений.

---

# 1. Архитектурный принцип

Closed Alpha строится как:

> **Modular Monolith + PostgreSQL + Object Storage + Async Workers + External AI Provider**

Это не временный «грязный монолит».

Это намеренно модульная система с:

- явными bounded contexts;
- направленными dependencies;
- domain events;
- собственностью данных;
- заменяемыми infrastructure adapters;
- возможностью позднее выделять отдельные сервисы только при реальной необходимости.

---

# 2. Почему НЕ микросервисы

На Alpha у нас:

```text
1 product
1 small team
1 database
rapidly changing domain
high iteration rate
limited traffic
```

Микросервисы сейчас добавят:

- network failures;
- distributed transactions;
- auth propagation;
- service discovery;
- tracing complexity;
- deployment overhead;
- schema coordination;
- message broker dependency.

Они не улучшат Learning Engine.

Поэтому:

```text
Modular Monolith first
```

---

# 3. Архитектурные Goals

Система должна быть:

```text
Modular
Explainable
Testable
Replayable
Bilingual
AI-provider-independent
Cloud-neutral
Secure
Observable
Cheap to operate
Easy for AI coding agents to understand
```

---

# 4. Архитектурные Non-Goals Alpha

Не строим:

```text
microservices
service mesh
Kubernetes
Kafka
Redis cluster
GraphQL federation
vector database
custom LLM serving
custom OCR model
event sourcing for entire app
CQRS everywhere
```

---

# 5. C4 — Context Level

```text
┌──────────────────────────────┐
│          Student             │
│  Browser / Mobile Browser    │
└──────────────┬───────────────┘
               │ HTTPS
               ▼
┌──────────────────────────────┐
│        ENT Math AI           │
│   Web + Backend Platform     │
└───────┬──────────┬───────────┘
        │          │
        │          │ AI API
        │          ▼
        │   ┌───────────────┐
        │   │ Gemini / LLM  │
        │   └───────────────┘
        │
        │ Object storage
        ▼
┌──────────────────────────────┐
│    S3-compatible Storage     │
└──────────────────────────────┘

External assessment sources:
user-provided files only in Alpha.
No credential scraping.
```

---

# 6. C4 — Container Level

```text
┌─────────────────────────────────────────────┐
│                 Browser                     │
│                                             │
│            Next.js Web App                  │
└─────────────────────┬───────────────────────┘
                      │ REST/JSON
                      ▼
┌─────────────────────────────────────────────┐
│            Spring Boot Application          │
│                                             │
│  identity                                   │
│  curriculum                                 │
│  content                                    │
│  assessment                                 │
│  ingestion                                  │
│  learning                                   │
│  planning                                   │
│  tutor                                      │
│  progress                                   │
│  admin                                      │
└──────────────┬──────────────┬───────────────┘
               │              │
               │              │ Async provider calls
               │              ▼
               │       ┌──────────────┐
               │       │ AI Provider  │
               │       └──────────────┘
               │
               ├──────────────→ Object Storage
               │
               ▼
┌─────────────────────────────────────────────┐
│               PostgreSQL                    │
│  transactional data + jobs + sessions       │
└─────────────────────────────────────────────┘
```

---

# 7. Runtime Processes

Один codebase, два runtime profiles:

```text
API process
WORKER process
```

## API

Отвечает за:

- auth;
- REST;
- synchronous domain operations;
- practice;
- content reads;
- plan reads;
- import creation.

## Worker

Отвечает за:

- AI import extraction;
- document processing;
- long mastery rebuild;
- async recalculation;
- cleanup jobs;
- selected AI evaluation jobs.

---

# 8. Почему API и Worker из одного artifact

Плюсы:

- один domain model;
- один deployment artifact;
- нет distributed service boundary;
- можно масштабировать независимо;
- heavy AI jobs не блокируют request threads.

Позднее worker можно физически выделить без изменения business interfaces.

---

# 9. Recommended Technology Stack

## Backend

```text
Java 25 LTS
Spring Boot 4.1.x
Spring Modulith
Spring Security
Spring Data JPA
Spring Session JDBC
Flyway
Bean Validation
Actuator
Micrometer
OpenTelemetry
Maven
```

## Frontend

```text
Next.js 16.x
React 19
TypeScript strict
App Router
TanStack Query
React Hook Form
Zod
KaTeX
Tailwind CSS
shadcn/ui
Playwright
```

## Persistence

```text
PostgreSQL 18
```

## Storage

```text
S3-compatible BlobStoragePort
```

Local:

```text
MinIO
```

## AI

```text
AiProviderPort
Google GenAI adapter first
```

---

# 10. Repository Architecture

```text
ent-math-ai/
│
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
│   ├── product-specs/
│   └── exec-plans/
│
├── backend/
│   ├── AGENTS.md
│   ├── pom.xml
│   └── src/
│
├── frontend/
│   ├── AGENTS.md
│   ├── package.json
│   └── src/
│
├── infra/
│   ├── docker/
│   ├── observability/
│   └── deploy/
│
└── test-fixtures/
    ├── content/
    ├── imports/
    ├── learning/
    └── ai-golden/
```

---

# 11. Backend Module Map

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
shared
```

`shared` должен быть маленьким.

Не превращать `shared` в свалку.

---

# 12. Module Internal Structure

Рекомендуемая логика:

```text
module/
├── api/
├── application/
├── domain/
└── infrastructure/
```

Не обязательно слепо создавать эти директории для каждого маленького модуля.

Но dependency direction:

```text
infrastructure
      ↓
application
      ↓
domain
```

HTTP/API адаптеры входят через application layer.

---

# 13. Identity Module

Владеет:

```text
UserAccount
StudentProfile
StudyGoal
Session/Auth
```

Public application APIs:

```text
getCurrentStudent()
getActiveGoal()
updateGoal()
```

Не знает:

- questions;
- mastery;
- Tutor internals.

---

# 14. Curriculum Module

Владеет:

```text
Subject
CurriculumVersion
OfficialSection
OfficialTopic
Skill
SkillPrerequisite
```

Public interfaces:

```text
getSkill()
getPrerequisites()
getSupportedSkills()
getCurriculumVersion()
```

Это read-mostly foundational module.

---

# 15. Content Module

Владеет:

```text
Question
QuestionVersion
QuestionSkillLink
AnswerKey
Solution
ContentSource
ContentIssue
QuestionFamily
```

Public interfaces:

```text
getPracticeQuestion()
validateAnswer()
getVerifiedSolution()
getContentCoverage()
reportIssue()
```

Learning module не читает AnswerKey напрямую.

---

# 16. Assessment Module

Владеет:

```text
PracticeSession
Attempt
Assessment
AssessmentItem
AssessmentTopicResult
```

Public interfaces:

```text
startPracticeSession()
submitAttempt()
createAssessment()
confirmAssessment()
excludeAssessment()
```

---

# 17. Ingestion Module

Владеет:

```text
Upload
ImportJob
ImportExtraction
ImportReviewItem
```

Public interfaces:

```text
createUpload()
createImport()
getImportStatus()
getReview()
updateReview()
confirmImport()
```

После confirmation вызывает canonical assessment flow.

---

# 18. Learning Module

Владеет:

```text
LearningEvidence
SkillMasteryProjection
ReviewState
ErrorEvent
LearningDisagreement
AlgorithmVersion
```

Public interfaces:

```text
recordEvidence()
getSkillState()
rebuildSkill()
rebuildStudent()
getTopicState()
```

Это domain core.

---

# 19. Planning Module

Владеет:

```text
DailyPlan
DailyPlanItem
RecommendationDecision
```

Public interfaces:

```text
getTodayPlan()
generatePlan()
invalidatePlan()
rankSkills()
```

Использует:

- learning;
- curriculum;
- content;
- active goal.

---

# 20. Tutor Module

Владеет:

```text
TutorInteraction
TutorPolicy
PromptVersion
AI routing
```

Public interface:

```text
requestHint()
requestExplanation()
requestFullSolution()
```

Не меняет mastery/correctness.

---

# 21. Progress Module

Read-model / query-oriented.

Отдаёт:

```text
ProgressOverview
SkillMapView
AssessmentTimeline
ErrorNotebookView
ReadinessView
```

Не владеет learning truth.

---

# 22. Admin Module

Оркестрирует internal use cases:

- content review;
- question approval;
- import support;
- policy activation;
- audit views.

Не должен обходить module APIs напрямую без причины.

---

# 23. Dependency Direction

Conceptual:

```text
identity     curriculum
   │             │
   │             ▼
   │           content
   │             │
   ▼             ▼
assessment ← ingestion
   │
   ▼
learning
   │
   ├────────→ planning
   │             │
   └────────→ progress

content + assessment + learning
              ↓
             tutor
```

---

# 24. Forbidden Dependencies

Examples:

```text
curriculum → learning
content → planning
learning → tutor
identity → Gemini adapter
domain → Spring MVC
domain → S3 SDK
```

---

# 25. Spring Modulith Enforcement

Use:

- module verification tests;
- documented allowed dependencies;
- module integration tests.

Architecture test must fail if forbidden dependency introduced.

---

# 26. Database Strategy

Single PostgreSQL instance.

Why:

- strong transactions;
- relational domain;
- operational simplicity;
- JSONB where needed;
- job queue possible;
- sessions possible.

No separate DB per module in Alpha.

---

# 27. Logical Database Ownership

Even with one schema/database:

```text
identity owns identity tables
content owns content tables
learning owns learning tables
...
```

Other modules must not use another module's repository directly.

---

# 28. No Cross-Module Repository Access

Bad:

```java
planningRepository
    -> learningJpaRepository
```

Good:

```text
planning
→ LearningQueryPort
```

---

# 29. Database Naming

Recommended:

```text
snake_case
```

Examples:

```text
learning_evidence
skill_mastery
question_versions
daily_plans
```

---

# 30. UUID Strategy

Use UUID for domain identities.

Do not expose sequential IDs.

Canonical content codes remain human-readable:

```text
EQ-LOG-03-000124
```

---

# 31. Flyway

All schema changes:

```text
Flyway
```

Rules:

- forward-only;
- released migration immutable;
- no manual production DDL;
- expand-and-contract for destructive change.

---

# 32. Transactions

Use transaction boundaries around domain use case.

Example `submitAttempt`:

```text
validate session
validate answer
save Attempt
publish AttemptRecorded
commit
```

Reliable event processing continues after commit.

---

# 33. Domain Events

Use Spring Modulith events.

Core events:

```text
AttemptRecorded
AssessmentConfirmed
AssessmentExcluded
LearningEvidenceCreated
LearningEvidenceInvalidated
MasteryChanged
LearningDisagreementDetected
DailyPlanInvalidated
QuestionSuspended
ImportConfirmed
```

---

# 34. Event Publication Reliability

Use durable event publication registry / DB-backed publication where relevant.

Goal:

```text
transaction committed
→ event not silently lost
```

---

# 35. Synchronous vs Async

Synchronous:

- auth;
- answer validation;
- create Attempt;
- lightweight evidence creation where cheap.

Async allowed:

- mastery rebuild;
- plan regeneration;
- import extraction;
- document parsing;
- analytics projection;
- long cleanup.

---

# 36. Consistency Model

Strong:

```text
auth
ownership
answer correctness
attempt persistence
assessment confirmation
```

Eventual:

```text
skill mastery refresh
progress dashboard
plan recalculation
analytics
```

---

# 37. UI Processing States

Frontend must understand:

```text
processing
updating
ready
failed
```

Do not pretend eventual data is immediately final.

---

# 38. Async Job Architecture

Alpha:

```text
PostgreSQL-backed job queue
```

Table:

```text
async_jobs
```

Fields conceptual:

```text
id
type
payload
status
attempts
next_attempt_at
locked_at
locked_by
created_at
completed_at
last_error
```

---

# 39. Worker Claiming

Pattern:

```sql
SELECT ...
FOR UPDATE SKIP LOCKED
```

Worker leases job.

---

# 40. Job Requirements

Every job:

- idempotent;
- retry-aware;
- bounded retries;
- observable;
- cancellable when appropriate.

---

# 41. No Infinite Retry

Example:

```text
maxAttempts = 5
```

with exponential backoff.

Permanent validation failure:

```text
FAILED_FINAL
```

---

# 42. Job Types

Initial:

```text
IMPORT_CLASSIFY
IMPORT_EXTRACT
IMPORT_VALIDATE
MASTERY_REBUILD
PLAN_RECALCULATE
RAW_UPLOAD_CLEANUP
ANALYTICS_PROJECTION
```

---

# 43. File Upload Architecture

Preferred:

```text
Browser
  ↓ request signed upload
Backend
  ↓
signed URL
  ↓
Browser → Object Storage
```

Avoid proxying large file bytes through API unless necessary.

---

# 44. Upload Security

Before processing:

```text
MIME validation
file size
page count
extension mismatch
malware scan
image metadata strip where appropriate
```

---

# 45. Object Storage

Port:

```text
BlobStoragePort
```

Methods conceptual:

```text
createUploadUrl()
createDownloadUrl()
delete()
getMetadata()
```

Adapter:

```text
MinIO local
S3/GCS/R2 production
```

---

# 46. No Public Upload URLs

Raw student files private.

Access only through:

```text
short-lived signed URL
```

---

# 47. Upload Retention

Raw upload has configurable expiration.

Normalized Assessment survives independently.

---

# 48. Import Pipeline

```text
Upload
  ↓
Scan
  ↓
Classify
  ↓
Extract
  ↓
Schema Validate
  ↓
Semantic Validate
  ↓
Review Model
  ↓
User Confirm
  ↓
Canonical Assessment
  ↓
Learning Evidence
  ↓
Mastery / Plan refresh
```

---

# 49. Import Boundary

`ingestion` stops responsibility at:

```text
reviewed canonical assessment data
```

It does NOT calculate mastery.

---

# 50. AI Provider Architecture

Use port:

```text
AiProvider
```

Capabilities:

```text
extractDocument()
classifyDocument()
generateTutorResponse()
optionalContentDraft()
```

Can split into narrower ports if implementation becomes clearer.

---

# 51. Provider Adapters

Initial:

```text
GoogleGenAiAdapter
```

Future:

```text
OpenAiAdapter
AnthropicAdapter
LocalAdapter
```

Domain uses capability aliases, not provider models.

---

# 52. Model Alias Layer

Config:

```text
FAST
STANDARD
STRONG
```

Example mapping:

```text
FAST → provider model X
STANDARD → provider model Y
STRONG → provider model Z
```

Can change without domain code.

---

# 53. AI Structured Outputs

All machine workflow AI calls:

```text
JSON Schema constrained
```

Then:

```text
application semantic validation
```

Schema validity does not equal truth.

---

# 54. Tutor Architecture

```text
Student Request
   ↓
Tutor Application Service
   ↓
Load verified question/solution
   ↓
Resolve allowed hint level
   ↓
Build minimal context
   ↓
AI Provider
   ↓
Structured response
   ↓
Leakage/semantic checks
   ↓
Persist TutorInteraction
   ↓
Return
```

---

# 55. Tutor Failure Does Not Break Practice

If provider unavailable:

```text
answer checking works
practice continues
curated hint may be used
```

---

# 56. Learning Engine Independence

Learning core has zero external AI dependency.

This is a hard architecture invariant.

---

# 57. Answer Validation Architecture

```text
Attempt Answer
      ↓
AnswerValidationService
      ↓
Validator Strategy
```

Strategies:

```text
ExactOptionValidator
MultiSelectValidator
IntegerValidator
DecimalToleranceValidator
RationalValidator
MatchingValidator
```

Later:

```text
SymbolicValidatorAdapter
```

---

# 58. No LLM Correctness

LLM never implements AnswerValidator.

---

# 59. Question Selection Architecture

Planning requests:

```text
target skill
difficulty preference
language
exclusions
recent families
```

Content returns eligible QuestionVersion.

---

# 60. Exposure History

Assessment module provides recent question/family exposure.

Content selection uses it.

---

# 61. Frontend Architecture

Feature-oriented:

```text
src/
├── app/
├── features/
│   ├── auth/
│   ├── onboarding/
│   ├── diagnostic/
│   ├── practice/
│   ├── today/
│   ├── tutor/
│   ├── imports/
│   ├── progress/
│   └── assessments/
├── entities/
├── shared/
└── generated/
```

---

# 62. Frontend State Rule

Server is source of truth.

Use:

```text
TanStack Query
```

for server state.

Avoid giant client global store.

---

# 63. Local UI State

Use component/local state for:

- modals;
- current input;
- local filters.

No domain algorithms in frontend.

---

# 64. API Contracts

REST:

```text
/api/v1
```

OpenAPI generated/maintained as contract.

Frontend types generated where practical.

---

# 65. REST Resource Groups

```text
/auth
/me
/diagnostics
/practice
/attempts
/today
/progress
/assessments
/imports
/admin
```

---

# 66. Error Contract

Standard:

```json
{
  "code": "IMPORT_REQUIRES_REVIEW",
  "message": "Some fields require confirmation.",
  "details": {},
  "traceId": "..."
}
```

Frontend branches on `code`, not English message text.

---

# 67. Idempotency

Required for high-risk mutation endpoints:

```text
submit attempt
confirm import
complete assessment
recalculate plan
upload registration
```

Header:

```text
Idempotency-Key
```

---

# 68. Authentication Architecture

Web Alpha:

```text
Spring Security
Spring Session JDBC
HttpOnly Cookie
```

Not JWT-by-default.

---

# 69. Cookie Security

```text
HttpOnly
Secure
SameSite=Lax/Strict
```

Production HTTPS only.

---

# 70. CSRF

Enabled for state-changing browser requests.

Use Spring Security integration.

---

# 71. Authorization

Student-owned resource rule:

```text
resource.studentId == currentStudentId
```

Backend enforced.

UUID secrecy is not authorization.

---

# 72. Roles

Initial:

```text
ROLE_STUDENT
ROLE_CONTENT_AUTHOR
ROLE_CONTENT_REVIEWER
ROLE_ADMIN
ROLE_SUPPORT
```

Least privilege.

---

# 73. Admin Separation

Admin routes:

```text
/admin/*
```

separate authorization.

Could later live in separate frontend app, but not required Alpha.

---

# 74. PII Boundary

Identity module owns direct identifiers.

Learning/Tutor work with:

```text
student internal ID
```

No need for name/email.

---

# 75. AI Privacy Boundary

Prompt excludes:

- email;
- phone;
- full name;
- parent data;
- exact address.

---

# 76. Prompt Injection Boundary

Student input / uploaded text always:

```text
UNTRUSTED DATA
```

Never instructions.

---

# 77. External Credentials

Architecture does not contain:

```text
NTC username/password
third-party password
```

No credential scraping.

---

# 78. Security Threat Model

Key threats:

```text
account takeover
IDOR
CSRF
XSS
malicious uploads
prompt injection
AI cost abuse
admin privilege abuse
content answer leakage
private assessment leakage
```

---

# 79. XSS Protection

Question/Tutor content rendered as:

- escaped text;
- controlled Markdown;
- KaTeX;
- sanitized output.

No raw untrusted HTML.

---

# 80. Answer Leakage via API

Public question DTO must not contain:

```text
AnswerKey
VerifiedSolution
hidden correct option metadata
```

before allowed.

Separate internal DTO.

---

# 81. Upload Quarantine

File can be:

```text
PENDING
AVAILABLE
QUARANTINED
REJECTED
```

AI only gets safe available object.

---

# 82. Rate Limiting

Required for:

```text
login
register
Tutor
imports
import retry
plan recalc
```

Implementation can begin DB/in-memory for single-node; future external limiter only when needed.

---

# 83. Secrets

Use:

- env/secrets manager;
- separate environments;
- no Git secrets.

AI keys server-side only.

---

# 84. Observability Architecture

Three pillars:

```text
Metrics
Logs
Traces
```

Use:

```text
Micrometer
OpenTelemetry
Actuator
```

---

# 85. Core Metrics

Technical:

```text
http latency
http errors
DB pool
job queue depth
job duration
AI latency
AI cost
import correction
mastery rebuild duration
plan generation duration
```

---

# 86. Logging

Structured JSON.

Fields:

```text
timestamp
level
traceId
requestId
module
event
```

Avoid PII/raw document.

---

# 87. Tracing

Trace:

```text
Browser request
→ API
→ DB
→ AI provider
```

For async:

propagate correlation via job metadata.

---

# 88. AI Telemetry

Every call:

```text
feature
provider
model
promptVersion
inputTokens
outputTokens
latency
status
estimatedCost
```

---

# 89. Product Analytics

Domain events can feed analytics projection.

Do not fire arbitrary frontend-only events for learning truth.

---

# 90. Metrics Store Alpha

Can use:

```text
Prometheus-compatible metrics
```

for technical.

Product analytics can initially be:

```text
PostgreSQL analytics tables
+
optional product analytics tool
```

No warehouse required.

---

# 91. Deployment — Local

Docker Compose:

```text
postgres
minio
backend-api
backend-worker
frontend
```

AI external.

---

# 92. Deployment — Staging

Same topology conceptually:

```text
frontend
api
worker
managed postgres
object storage
```

---

# 93. Deployment — Production

Minimal:

```text
CDN / Reverse Proxy
        ↓
Next.js
        ↓
Spring API
        ↓
PostgreSQL

Spring Worker
   ├─→ PostgreSQL
   ├─→ Object Storage
   └─→ AI Provider
```

---

# 94. No Kubernetes Requirement

Can deploy to:

- managed containers;
- VM containers;
- PaaS;
- Cloud Run-like services.

Choose simplest operational environment.

---

# 95. Horizontal Scaling

API should be stateless except session stored in DB.

Therefore:

```text
multiple API replicas
```

possible later.

---

# 96. Worker Scaling

Jobs claimed with DB locking.

Multiple workers safe if idempotent.

---

# 97. Session Scaling

Spring Session JDBC supports multiple API replicas without Redis initially.

---

# 98. PostgreSQL Backup

Required:

- automated backups;
- point-in-time recovery if provider supports;
- restore procedure tested before Beta.

---

# 99. Object Storage Backup

Public authored content assets backed up/versioned.

Raw uploads obey retention, not indefinite backup.

---

# 100. Disaster Recovery Priorities

Must recover:

```text
users
curriculum
question bank
assessments
learning evidence
mastery can rebuild
```

Important:

```text
SkillMasteryProjection is disposable/rebuildable.
LearningEvidence is not.
```

---

# 101. Derived Data Strategy

Rebuildable:

```text
skill_mastery
topic projections
progress read models
recommendation cache
```

Primary:

```text
attempt
assessment
learning_evidence
question versions
curriculum
```

---

# 102. Caching

Alpha:

```text
minimal
```

Possible in-process cache:

- curriculum;
- glossary;
- immutable config.

No Redis before measured need.

---

# 103. Cache Invalidation

Prefer caching immutable/versioned data.

Avoid caching mutable student learning state aggressively.

---

# 104. Feature Flags

Simple DB/config flags:

```text
AI_TUTOR_ENABLED
EXTERNAL_IMPORT_ENABLED
FULL_SOLUTION_ENABLED
MINI_MOCK_ENABLED
READINESS_ENABLED
```

No feature flag SaaS required Alpha.

---

# 105. Configuration

Separate:

```text
application config
policy config
secrets
```

Algorithm policies versioned.

---

# 106. Environment Separation

```text
local
dev
staging
production
```

Separate:

- DB;
- storage;
- AI keys;
- OAuth config.

---

# 107. CI Pipeline

On PR:

```text
backend compile
unit tests
module architecture tests
integration tests
migration tests
frontend lint
frontend typecheck
frontend tests
frontend build
OpenAPI contract checks
security/dependency scan
```

---

# 108. Testcontainers

Use for:

```text
PostgreSQL
MinIO where valuable
```

Integration tests should not depend on developer machine state.

---

# 109. E2E

Playwright critical flows:

```text
register → diagnostic → today → practice
upload → review → confirm
hint → recovery
progress
```

---

# 110. AI Tests in CI

Normal PR CI:

```text
mock provider
```

Real AI:

```text
scheduled/manual eval
```

Avoid flaky expensive PR builds.

---

# 111. Golden AI Evals

Stored:

```text
test-fixtures/ai-golden/
```

Run before model/prompt rollout.

---

# 112. Content Tests

Need:

```text
answer validators
solution consistency
LaTeX rendering sanity
skill references
duplicate checks
generator properties
```

---

# 113. Learning Engine Tests

Deterministic replay tests.

Given fixed evidence/policy/clock:

same result.

---

# 114. Architecture Tests

Spring Modulith verifies:

- module boundaries;
- forbidden dependencies.

Also custom tests if needed.

---

# 115. Frontend Contract Tests

Generated API client helps avoid type drift.

Critical response schemas validated.

---

# 116. API Versioning

Start:

```text
/api/v1
```

Do not version every endpoint differently.

Breaking public API:

future `/v2`.

Internal Alpha can evolve carefully.

---

# 117. Backward Compatibility

For rolling deploy:

DB migration should allow old/new app overlap where infrastructure requires.

Use expand-contract.

---

# 118. Audit Architecture

Audit table / service for:

```text
question approval
question suspension
answer correction
assessment override
evidence invalidation
policy activation
```

---

# 119. Algorithm Registry

Persist:

```text
mastery-v1
review-v1
recommendation-v1
readiness-v1
source-reliability-v1
```

---

# 120. Rebuild Architecture

Admin/internal command:

```text
rebuildStudent(studentId, algorithmVersion)
```

Can enqueue job.

---

# 121. Shadow Algorithm

Future:

```text
production v1
shadow v2
```

Shadow projection stored separately or offline analytics.

No user impact.

---

# 122. Curriculum Versioning

`CurriculumVersion` explicit.

Question mapping knows version.

Learning evidence references stable skill/curriculum node.

---

# 123. New Official Specification

Flow:

```text
publish ENT-MATH-2027-v1
→ diff
→ reuse stable skills where meaning same
→ new mappings
→ mark content eligibility
```

No mass destructive rewrite.

---

# 124. Question Versioning

Approved version immutable.

Session stores exact `questionVersionId`.

---

# 125. Content Defect Recovery

Wrong answer:

```text
suspend question
→ invalidate affected evidence
→ rebuild mastery
```

Architecture must support query from QuestionVersion to Attempt/Evidence.

---

# 126. External Import Correction

```text
Assessment corrected
→ old evidence invalidated
→ replacement evidence
→ rebuild
```

No direct mastery edit.

---

# 127. Assessment Source Abstraction

Single canonical model.

No tables like:

```text
ntc_results
itest_results
daryn_results
```

Adapters map to Assessment.

---

# 128. Official API Future

If provider offers OAuth/API:

add:

```text
ExternalAssessmentConnectorPort
```

Adapter authenticates officially.

Does not alter Assessment domain.

---

# 129. Readiness Architecture

Readiness estimator consumes:

```text
eligible Assessments
```

not raw Tutor/chat.

Produces derived projection.

---

# 130. Progress Read Models

Can denormalize for fast UI.

Examples:

```text
student_progress_overview
assessment_timeline_view
skill_state_view
```

Can rebuild.

---

# 131. Search Architecture

No Elasticsearch Alpha.

PostgreSQL text search enough for admin content.

---

# 132. No Vector DB

Tutor context deterministic.

No RAG needed initially.

---

# 133. Future Symbolic Math

If Java validators insufficient:

introduce:

```text
MathValidationPort
```

Adapter:

```text
SymPy service
```

Only after ADR.

---

# 134. Future OCR Model

Same:

```text
DocumentExtractionPort
```

Can change from Gemini to specialized OCR later.

---

# 135. ADR Requirements

Architecture-impacting changes require ADR.

Initial ADRs:

```text
ADR-001 Modular Monolith
ADR-002 PostgreSQL System of Record
ADR-003 Session Auth
ADR-004 AI Provider Abstraction
ADR-005 Immutable Learning Evidence
ADR-006 External Import Provenance
ADR-007 No Credential Scraping
ADR-008 Object Storage Port
ADR-009 No Vector DB Alpha
ADR-010 PostgreSQL Async Jobs
ADR-011 Readiness Requires Assessment Calibration
ADR-012 AI Cannot Determine Mathematical Truth
```

---

# 136. Failure Mode — AI Down

Expected behavior:

```text
Tutor unavailable/degraded
Import delayed
Practice OK
Learning Engine OK
Today OK
Progress OK
```

---

# 137. Failure Mode — Object Storage Down

```text
uploads unavailable
existing normalized assessments OK
practice OK
```

---

# 138. Failure Mode — Worker Down

```text
new imports remain queued
async rebuild delayed
API remains available
```

Alert queue depth.

---

# 139. Failure Mode — PostgreSQL Down

Core unavailable.

Need:

- managed HA where feasible;
- backup;
- recovery.

No pretending app works without system of record.

---

# 140. Failure Mode — Bad Content

Question can be killed immediately.

Learning evidence repair supported.

---

# 141. Failure Mode — Bad Algorithm Release

Because:

```text
LearningEvidence preserved
```

we can rollback algorithm and rebuild projections.

---

# 142. Failure Mode — Bad Prompt/Model

Feature flag / config rollback.

No impact on mathematical truth.

---

# 143. Failure Mode — Duplicate Import

Hash/fingerprint and idempotency block double evidence.

---

# 144. Failure Mode — Low Confidence OCR

Route to:

```text
NEEDS_REVIEW
```

Never silent application.

---

# 145. Failure Mode — Unsupported External Document

Return:

```text
IMPORT_UNSUPPORTED_FORMAT
```

Manual entry fallback.

---

# 146. Data Flow — Internal Practice

```text
Frontend
  ↓
POST attempt
  ↓
Assessment Application
  ↓
Content Answer Validator
  ↓
Attempt saved
  ↓
AttemptRecorded
  ↓
Learning Evidence
  ↓
Mastery update
  ↓
Plan may invalidate
  ↓
Progress refresh
```

---

# 147. Data Flow — Tutor

```text
Frontend
 ↓
Tutor endpoint
 ↓
load Attempt
 ↓
load verified content
 ↓
resolve Tutor policy
 ↓
AI provider
 ↓
semantic validator
 ↓
TutorInteraction
 ↓
response
```

No mastery mutation.

---

# 148. Data Flow — External Import

```text
Frontend
 ↓
signed upload
 ↓
Object Storage
 ↓
ImportJob
 ↓
Worker
 ↓
AI extraction
 ↓
Review
 ↓
Assessment
 ↓
LearningEvidence
 ↓
Mastery
 ↓
Plan
```

---

# 149. Data Flow — Daily Plan

```text
Student + Goal
      +
Skill States
      +
Review State
      +
Prerequisite Graph
      +
Content Availability
      ↓
Recommendation Engine
      ↓
DailyPlan
      ↓
Question Selection
```

---

# 150. Data Flow — Progress

```text
Learning projections
Assessments
Daily Plans
Attempts
      ↓
Progress Read Model
      ↓
Frontend
```

---

# 151. Data Flow — Content Approval

```text
Author Draft
 ↓
Review
 ↓
Validation
 ↓
Approval
 ↓
QuestionVersion APPROVED
 ↓
Eligible for serving
```

---

# 152. Data Flow — Content Defect

```text
Issue
 ↓
Review
 ↓
Suspend
 ↓
Find affected evidence
 ↓
Invalidate
 ↓
Rebuild
```

---

# 153. Performance Targets Alpha

Not SLA, engineering targets:

```text
normal API p95 < 300 ms
answer validation < 500 ms
today plan < 2 s
Tutor p95 ~ < 8 s
import async initial result ~ < 60 s
```

---

# 154. DB Performance

Indexes on:

```text
attempts(student_id, created_at)
learning_evidence(student_id, skill_id, occurred_at)
skill_mastery(student_id, skill_id)
assessments(student_id, occurred_at)
import_jobs(status, created_at)
daily_plans(student_id, plan_date)
question_skill_links(skill_id, role)
```

---

# 155. Avoid Premature Indexing

No JSONB GIN indexes without query evidence.

---

# 156. Pagination

All potentially long list endpoints paginated:

- assessments;
- content admin;
- issues;
- audit.

---

# 157. Connection Pool

Use standard Spring/Hikari defaults tuned only with evidence.

---

# 158. N+1 Protection

JPA queries reviewed.

Do not rely on lazy-loading in API serialization.

---

# 159. DTO Boundary

Never serialize JPA entities directly to frontend.

Use application/read DTOs.

---

# 160. Validation Boundary

Input validation:

```text
HTTP schema
+
application invariants
+
domain invariants
```

---

# 161. Clock Abstraction

Learning uses injected Clock.

Testing future time deterministic.

---

# 162. Time Storage

Persist timestamps in UTC.

Student-day logic uses profile timezone.

---

# 163. Language Architecture

UI locale:

```text
KK
RU
```

Content locale same first-class.

No database column:

```text
text
```

without language semantics for localized content.

---

# 164. Math Rendering

KaTeX frontend.

Content syntax constrained.

No raw HTML.

---

# 165. Accessibility

Architecture supports:

- semantic forms;
- keyboard;
- alt descriptions;
- non-color feedback.

Not separate later rewrite.

---

# 166. Mobile Responsiveness

Primary user likely mobile-capable.

Practice layout must work 360px width.

---

# 167. Offline Support

Not MVP.

Architecture does not depend on offline state.

---

# 168. PWA

Possible later.

No blocker.

---

# 169. Email

Only essential account flows if implemented.

No notification platform Alpha.

---

# 170. Payments

No payment module Alpha.

Future entitlement boundary.

---

# 171. Parent/Teacher

No tables scattered for them now.

Add later as separate bounded contexts.

---

# 172. Multi-Subject Future

Architecture already has:

```text
Subject
CurriculumVersion
Skill
```

Expansion possible.

But no premature generic abstraction beyond proven needs.

---

# 173. Architecture Quality Gates

Before feature merge:

- module dependency valid;
- tests;
- authorization;
- observability;
- error handling;
- no secret leakage;
- docs updated if contract changes.

---

# 174. Definition of Architecture Done

Architecture is sufficiently specified when team can answer:

```text
Where does this logic live?
Who owns this data?
What is source of truth?
Is it sync or async?
What happens if dependency fails?
How is it tested?
How is it observed?
How can it be changed later?
```

without inventing new answers during implementation.

---

# 175. Architecture Change Control

If implementation requires:

```text
new database
new message broker
new AI provider capability
new service boundary
new cross-module dependency
```

create ADR first.

---

# 176. Anti-Patterns

Do not introduce:

## God Service

```text
StudentService
```

doing auth, mastery, plan, import.

## God Controller

Business logic in REST controller.

## AI Everywhere

Direct Gemini SDK calls from multiple modules.

## Shared Repository

Cross-module repository imports.

## Mutable Truth

Manual mastery field edits.

## Infrastructure-Driven Domain

Domain model shaped around provider SDK.

---

# 177. Agent-Friendly Architecture

Why this matters:

AI coding agents perform better when:

- module boundaries explicit;
- docs current;
- source of truth known;
- acceptance criteria local;
- few hidden conventions.

Therefore every substantial implementation task should reference:

```text
AGENTS.md
relevant domain doc
relevant product spec
ExecPlan
```

---

# 178. Recommended Root AGENTS.md Responsibilities

Root file should state:

```text
product purpose
repo map
architecture choice
hard invariants
how to run
how to test
where docs live
when ADR required
when ExecPlan required
```

Keep short.

---

# 179. Backend AGENTS.md

Should state:

```text
Spring Modulith
module rules
transaction rules
Flyway rules
testing rules
no business logic in controllers
```

---

# 180. Frontend AGENTS.md

Should state:

```text
strict TS
feature-oriented
generated API types
server is source of truth
no duplicated learning algorithms
kk/ru required
```

---

# 181. Deployment Decision Alpha

Prefer managed services where they reduce operational work.

Principle:

> spend engineering time on learning engine/content, not cluster administration.

---

# 182. Cloud Neutrality

Cloud-neutral means:

```text
business/domain logic portable
```

not:

```text
never use cloud feature
```

Use S3-compatible/storage abstraction and standard PostgreSQL.

---

# 183. Cost Control Architecture

Avoid LLM where deterministic path exists.

Core policy:

```text
submit answer
→ no AI

correct
→ no AI

request hint
→ AI maybe

upload file
→ AI extraction

plan generation
→ no AI
```

---

# 184. AI Cost Failure Protection

Rate limits + budgets + telemetry.

If provider cost spikes:

route to cheaper model / curated fallback.

---

# 185. Database as Queue Tradeoff

Good for Alpha:

- fewer components;
- transactional job creation;
- easy local dev.

Limitations:

- not huge-scale throughput;
- queue contention later.

When measured pressure appears:

ADR for SQS/RabbitMQ/etc.

---

# 186. When to Consider Redis

Only if:

- session DB load;
- rate limiting needs shared fast store;
- hot cache bottleneck.

No speculative Redis.

---

# 187. When to Consider Message Broker

Only if:

- DB queue bottleneck;
- high event fanout;
- independent consumers/services.

Not because architecture diagrams look more professional.

---

# 188. When to Split Microservice

Candidate if module has:

```text
independent scaling
independent deployment cadence
clear ownership
stable API
high isolation benefit
```

Likely first future candidates:

```text
ingestion
AI gateway
```

Not now.

---

# 189. Architecture Roadmap Link

`10_ROADMAP.md` will implement this architecture in vertical order.

Architecture does NOT imply building every module skeleton first.

Preferred:

```text
walking skeleton
→ vertical slices
```

---

# 190. First Walking Skeleton

```text
Browser
→ Next.js
→ Spring Boot
→ PostgreSQL
```

Feature:

```text
authenticated user
→ /today
→ backend status/read model
```

Then expand.

---

# 191. Why Vertical Slices

Bad implementation:

```text
create 200 entities
create all repositories
create all controllers
then connect UI
```

Good:

```text
small complete user flow
→ test
→ next slice
```

---

# 192. First True Product Slice

Recommended:

```text
Register
→ Goal
→ one seeded skill
→ one approved question
→ submit answer
→ evidence
→ mastery
→ progress
```

This proves architecture before AI/import complexity.

---

# 193. Second Slice

```text
multiple skills
→ recommendation
→ Today
```

---

# 194. Third Slice

```text
Diagnostic
```

---

# 195. Fourth Slice

```text
Tutor
```

---

# 196. Fifth Slice

```text
Import
```

Full ordering formalized in Roadmap.

---

# 197. Production Readiness Checklist

Before Closed Alpha:

```text
HTTPS
backup
restore tested
security baseline
monitoring
alerts
AI kill switch
content suspension
account deletion path
import retention
rate limiting
error reporting
```

---

# 198. Final Architecture Decision

Canonical Alpha architecture:

```text
Frontend:
Next.js + TypeScript

Backend:
Java + Spring Boot + Spring Modulith

Persistence:
PostgreSQL

Auth:
Spring Session JDBC + HttpOnly Cookie

Files:
S3-compatible Blob Storage

Async:
PostgreSQL Job Queue + Worker Profile

AI:
Provider Port → Gemini Adapter

Learning:
Deterministic Weighted Evidence Engine

Tutor:
Controlled Structured LLM Layer

Math Truth:
Deterministic Answer Validators

Deployment:
Containers + Managed PostgreSQL/Object Storage

Observability:
Micrometer + OpenTelemetry

Testing:
JUnit + Testcontainers + Playwright + AI Golden Evals
```

---

# 199. Final Architecture Principle

> **Сложность должна находиться там, где находится ценность продукта.**

Для ENT Math AI ценность находится в:

```text
Curriculum
Content Quality
Learning Evidence
Mastery
Recommendation
Tutor Pedagogy
External Assessment Understanding
```

а не в:

```text
Kafka
Kubernetes
service mesh
20 микросервисах
```

---

# 200. Next Document

Следующий обязательный документ:

> **`10_ROADMAP.md`**

Он превратит:

```text
MVP Scope
+
Architecture
+
Release Gates
```

в конкретную последовательность реализации:

```text
Phase 0 — Repository / Harness
Phase 1 — Identity
Phase 2 — Curriculum / Content
Phase 3 — Practice
Phase 4 — Learning Engine
Phase 5 — Diagnostic
Phase 6 — Today / Planning
Phase 7 — Tutor
Phase 8 — Import
Phase 9 — Progress / Mini Mock
Phase 10 — Hardening
Phase 11 — Private Alpha
Phase 12 — Closed Alpha
```

После `10_ROADMAP.md` можно создавать `AGENTS.md`, ADRs и первый ExecPlan, а затем начинать production-код.
