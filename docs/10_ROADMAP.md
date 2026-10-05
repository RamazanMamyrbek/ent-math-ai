# 10_ROADMAP.md
## ENT Math AI — Implementation Roadmap

> **Статус:** canonical implementation roadmap  
> **Версия:** `ROADMAP-v1`  
> **Дата:** 6 октября 2026  
> **Связанные документы:**  
> `00_CONCEPT.md`  
> `02_CURRICULUM_MAP.md`  
> `03_DOMAIN.md`  
> `04_LEARNING_ENGINE.md`  
> `05_AI_TUTOR.md`  
> `06_CONTENT_STRATEGY.md`  
> `07_MVP_SCOPE.md`  
> `08_METRICS_AND_EXPERIMENTS.md`  
> `09_ARCHITECTURE.md`
>
> **Назначение:** определить точную последовательность реализации Closed Alpha ENT Math AI. Roadmap задаёт порядок, зависимости, deliverables, acceptance criteria и release gates. Он не заменяет ExecPlan конкретной фазы.

---

# 1. Главный принцип реализации

Мы не строим систему горизонтальными слоями:

```text
сначала все Entity
потом все Repository
потом все Service
потом все Controller
потом весь Frontend
```

Мы строим **vertical slices**:

```text
User action
   ↓
Frontend
   ↓
API
   ↓
Application
   ↓
Domain
   ↓
Persistence
   ↓
Tests
   ↓
Browser verification
```

Каждая фаза должна оставлять систему:

```text
buildable
testable
runnable
demonstrable
```

---

# 2. Roadmap Overview

```text
Phase 0   Engineering Harness
Phase 1   Identity + Onboarding
Phase 2   Curriculum + Content Foundation
Phase 3   First Practice Vertical Slice
Phase 4   Learning Evidence + Mastery
Phase 5   Progress v1
Phase 6   Diagnostic
Phase 7   Recommendation + Today
Phase 8   Review / Spaced Practice
Phase 9   AI Tutor
Phase 10  External Assessment Import
Phase 11  Mini Mock + Readiness
Phase 12  Admin / Content Production Hardening
Phase 13  Observability / Security / Reliability
Phase 14  Private Alpha
Phase 15  Closed Alpha
Phase 16  Paid Beta Decision
```

---

# 3. Definition of Phase Done

Фаза завершена только если:

```text
code complete
+
tests green
+
build green
+
runtime verified
+
browser flow verified
+
docs updated
+
decision changes recorded
```

---

# 4. Перед production-кодом

Должны существовать:

```text
00_CONCEPT.md
02_CURRICULUM_MAP.md
03_DOMAIN.md
04_LEARNING_ENGINE.md
05_AI_TUTOR.md
06_CONTENT_STRATEGY.md
07_MVP_SCOPE.md
08_METRICS_AND_EXPERIMENTS.md
09_ARCHITECTURE.md
10_ROADMAP.md
```

После начала разработки docs могут меняться, но изменения должны быть intentional, versioned и reviewed.

---

# 5. Следующие файлы перед кодом

Создать:

```text
AGENTS.md
backend/AGENTS.md
frontend/AGENTS.md

docs/adr/
docs/exec-plans/active/
docs/exec-plans/completed/
docs/product-specs/
```

---

# 6. Initial ADR Set

```text
ADR-001 Modular Monolith
ADR-002 PostgreSQL as System of Record
ADR-003 Session Authentication
ADR-004 Immutable Learning Evidence
ADR-005 AI Provider Abstraction
ADR-006 External Import Provenance
ADR-007 No Credential Scraping
ADR-008 S3-Compatible Object Storage
ADR-009 PostgreSQL Async Jobs
ADR-010 No Vector Database in Alpha
ADR-011 Score Prediction Requires Calibrating Assessments
ADR-012 LLM Cannot Determine Mathematical Truth
```

ADR должен появиться до внедрения соответствующего архитектурного решения.

---

# 7. ExecPlan Rule

ExecPlan обязателен для:

- каждой крупной фазы;
- database redesign;
- Learning Engine algorithm;
- Tutor integration;
- Import pipeline;
- security-sensitive work;
- migration affecting production data.

Мелкие bugfix не требуют отдельного большого ExecPlan.

---

# 8. ExecPlan Template

```text
Goal
Current State
Relevant Docs
Constraints
Non-Goals
Implementation Steps
Data Changes
API Changes
Tests
Browser Verification
Observability
Acceptance Criteria
Progress
Decisions
Final Verification
```

---

# 9. Phase 0 — Engineering Harness

## Цель

Создать минимальный профессиональный репозиторий, который:

```text
clone
→ configure
→ run
→ test
```

без ручной магии.

## Deliverables

Root:

```text
AGENTS.md
README.md
.gitignore
.env.example
docker-compose.yml
```

Backend:

```text
Spring Boot
Maven
Java 25
Spring Modulith
Actuator
Flyway
PostgreSQL
basic test setup
```

Frontend:

```text
Next.js
TypeScript strict
lint
test baseline
```

Infrastructure:

```text
PostgreSQL
MinIO
```

CI:

```text
backend build/test
frontend lint/typecheck/build
```

## Walking Skeleton

Browser:

```text
/
```

Frontend requests:

```text
GET /api/v1/system/status
```

Backend returns:

```json
{
  "status": "UP",
  "version": "...",
  "database": "UP"
}
```

Page displays:

```text
ENT Math AI
System ready
```

## Acceptance

```text
docker compose up
```

Then:

- frontend loads;
- backend health works;
- DB connected;
- Flyway migration applied;
- MinIO reachable;
- backend tests pass;
- frontend build passes;
- CI green.

## Forbidden Work

Do NOT yet build:

- Tutor;
- Gemini;
- Assessment Import;
- Mastery;
- complicated UI.

---

# 10. Phase 1 — Identity + Onboarding

## Цель

Первый real user flow:

```text
Register
→ Login
→ Profile
→ Goal
→ /today protected page
```

## Domain

```text
UserAccount
StudentProfile
StudyGoal
```

## Backend

```text
POST /api/v1/auth/register
POST /api/v1/auth/login
POST /api/v1/auth/logout
GET  /api/v1/auth/session

GET   /api/v1/me
PATCH /api/v1/me/profile

GET /api/v1/me/goal
PUT /api/v1/me/goal
```

## Security

```text
Spring Security
Spring Session JDBC
HttpOnly cookie
CSRF
password hashing
ownership foundations
```

## Frontend

```text
Register
Login
Onboarding
Goal Setup
Empty Today
```

## Acceptance

E2E:

```text
register
→ onboarding
→ save goal
→ reload
→ session persists
→ logout
```

---

# 11. Phase 2 — Curriculum + Content Foundation

## Цель

Создать предметную основу для первого вопроса.

## Scope

```text
Subject
CurriculumVersion
OfficialSection
OfficialTopic
Skill
SkillPrerequisite

Question
QuestionVersion
QuestionSkillLink
AnswerKey
Solution
ContentSource
```

## Seed Scope

Start only:

```text
5–8 skills
```

Example:

```text
ALG-OPS-01
ALG-SF-01
EQ-LIN-01
EQ-QUAD-01
EQ-QUAD-02
EQ-QUAD-03
```

## Content

Seed:

```text
20–30 reviewed questions
```

RU + KK where possible.

## Validators

First:

```text
SINGLE_CHOICE
INTEGER
DECIMAL
```

Then:

```text
MULTI_SELECT
MATCHING
RATIONAL
```

## Minimal Admin

```text
list
view
create/edit draft
approve
suspend
```

## Acceptance

System can:

```text
load Skill
load APPROVED Question
validate correct/incorrect answer
load verified solution
```

---

# 12. Phase 3 — First Practice Vertical Slice

## Цель

```text
Student
→ Question
→ Answer
→ Correctness
→ Session Summary
```

## Domain

```text
PracticeSession
Attempt
```

## APIs

```text
POST /api/v1/practice/sessions
GET  /api/v1/practice/sessions/{id}/next
POST /api/v1/practice/sessions/{id}/attempts
POST /api/v1/practice/sessions/{id}/complete
```

## Frontend

Practice screen:

- question;
- answer controls;
- submit;
- correctness feedback;
- next;
- progress indicator.

## Security

Question DTO must NOT contain:

```text
correct answer
solution
hidden mapping
```

before allowed.

## Acceptance

```text
Login
→ Practice
→ solve 5 questions
→ answers validated
→ session complete
```

Inspect network response and verify answer key is not leaked.

---

# 13. Phase 4 — Learning Evidence + Mastery

## Цель

Перевести practice из тестового приложения в adaptive foundation.

## Domain

```text
LearningEvidence
SkillMasteryProjection
AlgorithmVersion
```

## Algorithm

Implement exactly:

```text
mastery-v1
confidence-v1
```

from `04_LEARNING_ENGINE.md`.

## Flow

```text
AttemptRecorded
→ EvidenceFactory
→ LearningEvidence
→ MasteryCalculator
→ SkillMasteryProjection
```

## Requirements

```text
Evidence immutable
Mastery rebuildable
Algorithm version persisted
```

## Replay

```text
rebuildStudent(studentId)
```

must reproduce same result.

## Acceptance

After several answers:

```text
different skill states
different confidence
replay deterministic
```

---

# 14. Phase 5 — Progress v1

## Цель

Показать пользователю, что система реально строит модель знаний.

## Screens

```text
Progress Overview
Skill Map
Recent Sessions
```

## UI States

```text
Недостаточно данных
Развивается
Сильный
Нужно повторить
```

Do not expose raw probability as fake certainty by default.

## Acceptance

Two synthetic users with different history see different Skill Maps.

---

# 15. Phase 6 — Diagnostic

## Цель

Получить initial knowledge state без ручного выбора тем.

## Content Requirement

Need:

```text
diagnostic anchor pool
```

## Model

```text
Assessment(type=DIAGNOSTIC)
+
PracticeSession(type=DIAGNOSTIC)
```

## Algorithm v1

```text
10–12 anchor questions
+
targeted follow-up
max 20 items
```

Can start with deterministic branch rules.

## Output

```text
Strong skills
Priority skills
Unknown skills
```

## Acceptance

Synthetic profiles:

```text
strong algebra / weak trig
weak algebra / strong geometry
unknown
```

must produce meaningfully different outputs.

---

# 16. Phase 7 — Recommendation Engine + Today

## Цель

Реализовать главный product promise:

> не думай, что учить сегодня.

## Domain

```text
DailyPlan
DailyPlanItem
RecommendationDecision
```

## Algorithm

Implement `recommendation-v1`:

```text
gap
uncertainty
exam relevance
prerequisite gate
content availability
```

Review/error factors can remain zero until Phase 8.

## Today Screen

```text
Сегодня
~30 мин

Activity 1
Activity 2
Activity 3

Почему эти задания?
```

## Acceptance

Two students with same goal but different learning state get different Today plans.

---

# 17. Phase 8 — Review / Memory / Error Signals

## Цель

Добавить временное измерение learning state.

## Domain

```text
ReviewState
ErrorEvent basic
LearningDisagreement shell
```

## Algorithm

Implement:

```text
review-v1
stability
retrievability
nextReviewAt
```

## Behavior

Strong but overdue skill:

```text
review
```

not:

```text
weak
```

## Error Signals

Start with deterministic/simple:

```text
SIGN_ERROR
FORMULA_CONFUSION
CALCULATION_ERROR
UNKNOWN
```

## Acceptance

Clock-controlled test:

```text
skill strong
advance time
retrievability falls
review appears in Today
```

---

# 18. Phase 9 — AI Tutor

## Цель

Добавить contextual help without changing mathematical truth.

## Prerequisites

Already required:

```text
verified AnswerKey
verified Solution
Attempt
Learning Evidence
Practice UI
```

## Provider Layer

```text
AiTutorProvider
GoogleGenAiTutorAdapter
FAST/STANDARD/STRONG aliases
```

## Rollout

First:

```text
Hint 1
Hint 2
```

Then:

```text
Guided Step
Concept Explanation
Clarify
Full Solution
```

## Safety

Before user release:

```text
structured output
semantic validation
answer leakage detection
prompt injection tests
RU/KK evals
kill switch
```

## Learning Integration

Attempt tracks:

```text
highestHintLevel
fullSolutionViewed
```

EvidenceFactory applies assistance weighting.

## Acceptance

```text
wrong answer
→ Hint 1
→ no final answer leak
→ Hint 2
→ independent recovery
→ evidence weighted correctly
```

---

# 19. Phase 10 — External Assessment Import

## Цель

Позволить существующим пробникам стать learning evidence.

## Step A — Start Simple

Start with:

```text
manual score
result screenshot
PDF result report
topic breakdown
```

Do NOT start with full handwritten reasoning recognition.

## Upload

```text
signed upload
private object storage
sha256
retention
```

## Async Jobs

```text
ImportJob
PostgreSQL queue
Worker profile
retries
idempotency
```

## Extraction

AI extracts:

```text
date
source
subject
score
maxScore
topicResults
```

## Review

User can:

```text
confirm
correct
exclude
```

## Canonicalization

```text
ImportExtraction
→ Assessment
→ AssessmentTopicResult
```

## Evidence Rules

```text
total score
→ readiness evidence

topic result
→ aggregate topic evidence

item result
→ granular evidence only if reliable
```

## Duplicate Protection

```text
file hash
assessment fingerprint
idempotency
```

## Step B — Experimental Paper Mock

Only after result import is stable:

```text
multi-image/PDF
marked answers
answer key
```

## Explicit Non-Goal

No reliable handwritten reasoning reconstruction.

## Acceptance

```text
upload screenshot
→ processing
→ review
→ correct date
→ confirm
→ assessment appears
→ plan/readiness changes if relevant
```

---

# 20. Phase 11 — Mini Mock + Readiness

## Цель

Получить independent outcome signal.

## Mini Mock

```text
15–20 items
mixed supported skills
timed
Tutor disabled
```

## Holdout

Need:

```text
MOCK_ELIGIBLE
```

items.

## Result

Show:

```text
score
skill/topic breakdown
time
basic errors
```

## Readiness v1

Only if:

```text
>= 2 eligible assessments
```

Then show:

```text
range
confidence level
```

Never fake exact prediction.

---

# 21. Phase 12 — Admin + Content Production Hardening

## Цель

Make content production sustainable.

## Admin

Improve:

```text
Question Editor
RU/KK side-by-side
Skill Mapping
Difficulty
Answer
Solution Steps
Hints
Source
Review
Approval
Issue Queue
```

## Bulk Import

```text
JSON/YAML
```

## Lint

```text
broken LaTeX
duplicate options
missing answer
missing solution
invalid skill
bad language status
suspicious HTML
```

## Parametric Generators

Start only with safe families:

```text
quadratic equations
simple linear/rational forms
```

## Coverage Dashboard

```text
Skill × Difficulty × Language
```

## Content Milestones

```text
C1 50
C2 150
C3 300
C4 500+
```

Only APPROVED canonical questions count.

---

# 22. Phase 13 — Hardening

## Security

Review:

```text
auth
CSRF
IDOR
rate limits
upload safety
XSS
admin roles
AI keys
prompt injection
answer leakage
```

## Privacy

```text
raw upload retention
delete upload
exclude assessment
account deletion
PII minimization
```

## Observability

Dashboards:

```text
API
DB
worker queue
Tutor
AI cost
Import
content issues
```

## Backup

Test restore.

## Performance

Smoke test target scenario:

```text
~100 concurrent active users
```

## Failure Tests

Simulate:

```text
AI unavailable
worker stopped
storage unavailable
duplicate job
bad extraction
```

## Gate

No Closed Alpha until:

```text
no known critical security issue
backup restore verified
kill switches work
monitoring works
```

---

# 23. Phase 14 — Private Alpha

## Cohort

```text
10–20 invited students
```

## Focus

```text
bugs
UX confusion
content defects
algorithm sanity
AI failure
import behavior
```

Not PMF.

## Before Start

```text
support channel
baseline mini mock
interview template
issue triage
```

## Exit

Proceed when:

```text
core flows reliable
Today understandable
no systemic content trust problem
Tutor safe enough
Import usable
```

---

# 24. Phase 15 — Closed Alpha

## Cohort

```text
30–50 students
4–6 weeks
```

## Protocol

```text
Baseline
→ Usage
→ Follow-up
→ Interviews
→ Go/No-Go report
```

## No Major Expansion

During cohort:

```text
fix
measure
learn
```

not:

```text
add physics
add parent dashboard
rewrite backend
```

## Key Questions

```text
Do students activate?
Do they follow Today?
Do they return?
Do they learn?
Does Tutor help?
Does Import matter?
Do they trust results?
What is AI cost?
Can content production scale?
```

## Output

```text
ALPHA_REPORT.md
```

---

# 25. Phase 16 — Paid Beta Decision

No automatic monetization.

Evaluate:

```text
Product Pull
Learning Signal
Trust
Economic Feasibility
```

Possible:

```text
Proceed
Iterate
Narrow
Stop/Pivot
```

---

# 26. Cross-Cutting Track — Content

Runs from Phase 2 onward:

```text
20–30 seed
→ 50
→ 150
→ 300
→ 500+
```

Content and engineering proceed in parallel.

---

# 27. Cross-Cutting Track — Documentation

After meaningful decision update:

```text
relevant spec
ADR
ExecPlan
decision log
```

Docs must not become historical fiction.

---

# 28. Cross-Cutting Track — Testing

Never postpone:

```text
tests until after MVP
```

Learning Engine mistakes contaminate real data.

---

# 29. Cross-Cutting Track — Metrics

Instrumentation starts before Alpha.

Otherwise launch produces no evidence.

---

# 30. Cross-Cutting Track — Security

Security begins in Phase 1.

Phase 13 is hardening/review, not first security work.

---

# 31. Implementation Order Within a Phase

Default:

```text
1. ExecPlan
2. domain model
3. migration
4. application use case
5. API contract
6. unit/integration tests
7. frontend
8. E2E
9. observability
10. docs
```

---

# 32. Commit Strategy

Prefer focused commits:

```text
feat(identity): add session registration flow
feat(content): add question answer validation
feat(learning): create immutable evidence
```

Avoid giant unrelated commits.

---

# 33. Branch Strategy

Simple:

```text
main
feature/*
fix/*
```

Short-lived branches.

No GitFlow bureaucracy.

---

# 34. Pull Request Requirements

```text
what
why
relevant spec
test evidence
screenshots if UI
migration notes
known limitations
```

---

# 35. Coding Agent Instruction Pattern

Good:

```text
Implement Phase X only.
Read AGENTS.md and linked specs.
Do not implement future phases.
Maintain module boundaries.
Run required tests.
Verify the user flow.
Update the ExecPlan with progress and decisions.
```

Bad:

```text
Build the entire AI ENT SaaS.
```

---

# 36. Agent Hard Stops

Without explicit architecture decision, agent must NOT:

```text
add Kafka
add Redis
create microservice
change mastery formula
call Gemini for correctness
change stable skill IDs
replace Postgres
add vector DB
```

---

# 37. Phase Isolation

Do not build Import while LearningEvidence contract is unstable.

Do not build Tutor before verified solution pipeline exists.

Do not build Readiness before Assessment model exists.

---

# 38. Technical Debt Rule

Allowed:

```text
simple implementation behind stable interface
```

Not allowed:

```text
shortcut violating core invariant
```

Allowed example:

```text
simple admin UI
```

Forbidden example:

```text
direct mutable mastery column updates
```

---

# 39. No Future-Proofing Theater

Do not create overabstracted infrastructure for hypothetical providers.

One clean port + one adapter is enough.

---

# 40. First 10 Engineering Milestones

```text
M01 repo boots
M02 user can register/login
M03 one approved question served
M04 answer validation works
M05 attempt persists
M06 evidence created
M07 mastery visible
M08 diagnostic works
M09 Today personalized
M10 review due affects Today
```

Only then Tutor.

---

# 41. Next 10 Milestones

```text
M11 Hint 1
M12 Hint 2
M13 Tutor leakage checks
M14 Full Solution recovery
M15 signed upload
M16 result extraction
M17 import review
M18 assessment history
M19 mini mock
M20 readiness range
```

---

# 42. Content Milestones

```text
C01 curriculum seed
C02 30 questions
C03 50 questions
C04 150 questions
C05 300 questions
C06 500 questions
```

---

# 43. Release Gate Mapping

```text
Engineering Alpha:
M01–M10

Private Alpha:
M01–M18
+ enough content for supported slice

Closed Alpha:
M01–M20
+ ~48 skills
+ C06 target
+ hardening
```

---

# 44. Suggested First Supported Slice

Start with:

```text
Quadratic equations
```

Why:

- clear prerequisites;
- deterministic validation;
- easy A/B/C content;
- easy Tutor explanations;
- later links naturally to trig substitution.

Skills:

```text
ALG-OPS-01
ALG-SF-01
EQ-LIN-01
EQ-QUAD-01
EQ-QUAD-02
EQ-QUAD-03
```

---

# 45. First Slice Demo

At end:

```text
User logs in
→ selects Quadratic Equations
→ solves 6 questions
→ sees skill state
```

This should exist before Diagnostic.

---

# 46. Second Domain Slice

Add:

```text
TRIG-ANG-01
TRIG-VAL-01
TRIG-ID-01
EQ-TRIG-01
EQ-TRIG-02
```

Now prerequisite routing becomes demonstrable.

---

# 47. Third Domain Slice

Add:

```text
logs / exponentials
```

Then:

```text
inequalities
functions
geometry
```

---

# 48. Why Not Geometry First

Geometry introduces diagram assets early.

Better validate learning engine on algebra first.

---

# 49. Content Production Order

```text
1 Algebra Core
2 Trigonometry
3 Logs/Exponentials
4 Inequalities
5 Functions/Derivative
6 Geometry Core
```

---

# 50. Tutor Rollout Order

```text
internal dev
→ team test
→ 5 users
→ 20 users
→ Closed Alpha
```

Feature flag throughout.

---

# 51. Import Rollout Order

```text
manual score
→ screenshot score
→ PDF result
→ topic breakdown
→ paper mock experimental
```

Do not start with hardest OCR problem.

---

# 52. Readiness Rollout

```text
hidden internal calculation
→ internal comparison
→ beta UI range
```

Do not expose uncalibrated score predictor immediately.

---

# 53. Algorithm Rollout

For changes:

```text
offline replay
→ synthetic profiles
→ shadow
→ small cohort
→ default
```

---

# 54. Data Migration Philosophy

Before real Alpha:

schema can evolve quickly.

After real student data exists, preserve:

```text
attempt
assessment
learning_evidence
```

Never reset production DB for convenience.

---

# 55. Staging

Deploy staging early, around Phase 1–2.

Do not wait until hardening to discover deployment issues.

---

# 56. Production-Like Staging

Should include:

```text
real PostgreSQL behavior
object storage adapter
AI sandbox key when AI introduced
HTTPS
```

---

# 57. Browser Verification

For every user-facing feature:

tests + actual rendered flow verification.

---

# 58. Mobile Verification

Core practice tested from early phases at:

```text
360px
```

---

# 59. Accessibility Verification

Core controls:

- keyboard;
- labels;
- focus;
- no color-only correctness.

---

# 60. Performance Philosophy

Measure first.

No speculative caching.

---

# 61. AI Cost Verification

From first Tutor/import call:

record:

```text
tokens
latency
estimated cost
feature
model
```

---

# 62. Content Cost Verification

Track:

```text
authoring time
review time
translation time
rework
```

Content economics matter.

---

# 63. Main Technical Risks

Ranked:

```text
1. Content quality / production speed
2. Learning model false confidence
3. Import extraction quality
4. Tutor answer leakage/math errors
5. Scope expansion
6. KK language quality
7. Weak retention despite good engineering
```

---

# 64. Risk Mitigation — Content

```text
narrow initial scope
small review batches
expert review
parametric generation where safe
```

---

# 65. Risk Mitigation — Learning

```text
simple model
confidence separate
replay
holdout calibration
```

---

# 66. Risk Mitigation — Import

```text
result screenshot first
user review
confidence gating
manual fallback
```

---

# 67. Risk Mitigation — Tutor

```text
verified solution
hint boundaries
structured output
leakage detector
evals
kill switch
```

---

# 68. Risk Mitigation — Scope

Every new feature must answer:

```text
Which MVP hypothesis does this test?
What work does it delay?
```

---

# 69. Roadmap Review Cadence

Review:

```text
after each phase
after major product evidence
after official curriculum change
```

Not daily.

---

# 70. Phase State

```text
NOT_STARTED
ACTIVE
BLOCKED
DONE
DEFERRED
```

Track in ExecPlans/project board.

---

# 71. Safe Parallel Work

Possible:

```text
content production
frontend polish
AI eval fixture writing
docs
```

while core backend slice is active.

---

# 72. Unsafe Parallel Work

Do not independently modify:

```text
LearningEvidence
Mastery
Recommendation
```

without coordination.

---

# 73. Merge Discipline

Before merge:

```text
update branch
tests
architecture verify
migration check
```

---

# 74. Flyway Rule

Released migration immutable.

Fix through new migration.

---

# 75. First ExecPlan

Create next:

```text
docs/exec-plans/active/EP-0001-engineering-harness.md
```

Goal:

```text
Phase 0
```

---

# 76. Second ExecPlan

Only after Phase 0:

```text
EP-0002-identity-onboarding.md
```

Do not prewrite all 16 detailed plans before implementation teaches us anything.

---

# 77. Antigravity Workflow

Give it:

```text
AGENTS.md
current ExecPlan
relevant docs
acceptance criteria
```

Never:

```text
Build the whole project.
```

---

# 78. End State after Closed Alpha

If successful:

```text
bilingual adaptive web product
~48 supported skills
~500 approved canonical questions
persistent student model
Daily Mission
controlled AI Tutor
external assessment import
mini mocks
progress
real learner data
Alpha outcome report
```

At that point the next roadmap must be evidence-driven.

---

# 79. Final Roadmap Principle

> **Сначала доказать механизм ценности. Потом масштабировать содержание. Потом масштабировать пользователей. Только потом масштабировать инфраструктуру.**

Canonical order:

```text
Correctness
→ Learning Model
→ Personalization
→ Tutor
→ External Evidence
→ Measurement
→ Real Users
→ Retention
→ Learning Outcome
→ Monetization
→ Scale
```

---

# 80. Immediate Next Step

После этого документа:

```text
1. AGENTS.md
2. backend/AGENTS.md
3. frontend/AGENTS.md
4. initial ADRs
5. EP-0001-engineering-harness.md
6. Phase 0 implementation
```

Следующий документ:

> **`AGENTS.md`**

Он должен быть коротким и жёстким: карта репозитория, архитектурные инварианты, команды запуска/тестов и правила для coding agents.
