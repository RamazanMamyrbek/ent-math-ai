# 03_DOMAIN.md
## ENT Math AI — Domain Model & Invariants

> **Статус:** canonical domain specification  
> **Версия:** `DOMAIN-v1`  
> **Дата:** 5 октября 2026  
> **Связанные документы:**  
> `00_CONCEPT.md`  
> `02_CURRICULUM_MAP.md`  
> `ENT_Math_AI_Technical_Specification_v1.md`  
>
> **Назначение:** определить предметную модель ENT Math AI до написания production-кода.  
> Этот документ является source of truth для backend-модулей, API-контрактов, Learning Engine, import pipeline, Question Bank и agentic development.

---

# 1. Главный принцип доменной модели

ENT Math AI не является CRUD-приложением вокруг таблицы `users`.

Его доменная модель строится вокруг цепочки:

```text
Student
   ↓
Goal
   ↓
Curriculum
   ↓
Skill
   ↓
Question
   ↓
Attempt / Assessment
   ↓
Learning Evidence
   ↓
Mastery
   ↓
Plan
   ↓
Next Learning Action
```

При этом AI является **инфраструктурным помощником**, а не владельцем доменной истины.

Ключевая формула:

```text
Verified Domain Data
        +
Learning Evidence
        +
Rebuildable Student Model
        +
Explainable Adaptive Decisions
```

---

# 2. Что является Source of Truth

В проекте несколько разных источников истины.

## 2.1. Curriculum truth

Источник:

```text
CurriculumVersion
OfficialSection
OfficialTopic
Skill
SkillPrerequisite
```

Определяет:

- какие навыки существуют;
- как они называются;
- как связаны;
- к какой версии программы относятся.

---

## 2.2. Content truth

Источник:

```text
Question
QuestionVersion
AnswerKey
Solution
QuestionSkillLink
```

Определяет:

- что именно спрашивает задание;
- какой ответ является правильным;
- к какому skill относится;
- какой difficulty имеет конкретная версия.

---

## 2.3. Learning truth

Источник:

```text
LearningEvidence
```

Не:

```text
SkillMastery
```

`SkillMastery` — вычисляемая projection.

Это критический принцип.

---

## 2.4. Assessment truth

Источник:

```text
Assessment
AssessmentItem
AssessmentTopicResult
```

Импортированный assessment становится canonical только после прохождения validation/review policy.

---

## 2.5. Planning truth

Источник:

```text
DailyPlan
DailyPlanItem
```

Но план является временным решением системы.

Он может быть invalidated и пересоздан.

---

# 3. Aggregate Boundaries

Основные aggregates:

```text
UserAccount
StudentProfile
StudyGoal

CurriculumVersion
Skill

Question
QuestionVersion

PracticeSession
Attempt

Assessment

LearningEvidence
SkillMasteryProjection

DailyPlan

ImportJob

TutorInteraction
```

Правило:

> один aggregate не должен менять внутреннее состояние другого aggregate напрямую.

Коммуникация — через application services, domain services и domain events.

---

# 4. Identity Model

## 4.1. UserAccount

Представляет учётную запись.

Поля концептуально:

```text
UserAccount
- id
- email
- passwordHash
- status
- createdAt
- updatedAt
```

Status:

```text
PENDING
ACTIVE
SUSPENDED
DELETED
```

Инварианты:

1. email уникален в рамках активных аккаунтов;
2. password hash никогда не покидает identity module;
3. удалённый аккаунт не должен продолжать создавать learning activity;
4. auth identity не содержит learning logic.

---

## 4.2. StudentProfile

Учебный профиль пользователя.

```text
StudentProfile
- studentId
- userId
- displayName?
- preferredLanguage
- grade?
- timezone
- onboardingStatus
- createdAt
```

Language:

```text
KK
RU
```

Инварианты:

1. ровно один active StudentProfile на UserAccount в Alpha;
2. язык можно менять;
3. изменение языка не создаёт нового learning state;
4. profile не хранит mastery.

---

# 5. StudyGoal

Определяет цель ученика.

```text
StudyGoal
- id
- studentId
- subjectId
- targetScore?
- examDate?
- dailyMinutes
- status
- createdAt
- updatedAt
```

Status:

```text
ACTIVE
COMPLETED
CANCELLED
SUPERSEDED
```

Инварианты:

1. на один subject в Alpha — максимум одна ACTIVE цель;
2. новая цель supersedes старую;
3. `dailyMinutes > 0`;
4. targetScore не может превышать официально допустимый максимум;
5. отсутствие targetScore допустимо;
6. отсутствие точной examDate допустимо, если пользователь знает только период.

---

# 6. Subject

В Alpha:

```text
MATHEMATICS
```

Позднее:

```text
PHYSICS
INFORMATICS
MATH_LITERACY
KAZAKHSTAN_HISTORY
READING_LITERACY
```

`Subject` — стабильный справочник.

---

# 7. CurriculumVersion

Представляет конкретную версию программы.

Пример:

```text
ENT-MATH-2026-v1
```

Поля:

```text
CurriculumVersion
- id
- subjectId
- code
- title
- effectiveFrom
- effectiveTo?
- status
- sourceUrl
- createdAt
```

Status:

```text
DRAFT
ACTIVE
RETIRED
```

Инварианты:

1. code уникален;
2. published QuestionVersion обязан ссылаться на конкретный CurriculumVersion;
3. изменение curriculum не должно молча менять смысл старых attempts;
4. retired version остаётся доступной исторически.

---

# 8. OfficialSection

10 официальных разделов.

```text
OfficialSection
- id
- curriculumVersionId
- code
- nameRu
- nameKk
- sortOrder
```

Это официальный слой.

Не смешивать его с granular skills.

---

# 9. OfficialTopic

18 официальных тем.

```text
OfficialTopic
- id
- sectionId
- code
- nameRu
- nameKk
- officialDescriptionRu
- officialDescriptionKk
- sortOrder
```

Инвариант:

> granular Skill всегда принадлежит одному OfficialTopic в текущем curriculum mapping.

---

# 10. Skill

Главный предметный элемент Adaptive Engine.

```text
Skill
- id
- stableCode
- officialTopicId
- nameRu
- nameKk
- descriptionRu?
- descriptionKk?
- coverageTier
- status
```

CoverageTier:

```text
P0
P1
P2
```

Status:

```text
DRAFT
ACTIVE
RETIRED
```

Инварианты:

1. `stableCode` уникален глобально;
2. stableCode не содержит curriculum year;
3. active skill обязан иметь RU и KK labels;
4. skill не имеет difficulty;
5. skill не хранит student-specific mastery;
6. retired skill не удаляется физически, если на него есть historical evidence.

---

# 11. SkillPrerequisite

Ориентированное ребро:

```text
prerequisiteSkill
       ↓
targetSkill
```

Поля:

```text
SkillPrerequisite
- prerequisiteSkillId
- targetSkillId
- strength
- rationale?
```

Strength:

```text
REQUIRED
RECOMMENDED
SUPPORTING
```

Инварианты:

1. self-reference запрещён;
2. graph не должен содержать cycles;
3. prerequisite не означает абсолютный запрет на изучение target;
4. Learning Engine может использовать threshold readiness.

---

# 12. Question

Стабильная identity задания.

```text
Question
- id
- canonicalCode
- sourceId
- lifecycleStatus
- createdAt
```

LifecycleStatus:

```text
DRAFT
IN_REVIEW
APPROVED
SUSPENDED
RETIRED
```

Инварианты:

1. Question сам по себе не содержит изменяемый текст;
2. текст живёт в QuestionVersion;
3. APPROVED Question обязан иметь минимум одну APPROVED QuestionVersion;
4. SUSPENDED нельзя выдавать новым студентам.

---

# 13. QuestionVersion

Версионируемое содержимое задания.

```text
QuestionVersion
- id
- questionId
- version
- curriculumVersionId
- language
- stem
- format
- difficulty
- estimatedTimeSeconds
- status
- metadata
- approvedAt?
```

Format:

```text
SINGLE_CHOICE
CONTEXT_SINGLE_CHOICE
MULTI_SELECT
MATCHING
INTEGER
DECIMAL
RATIONAL
```

В Alpha официальные ENT formats имеют приоритет.

Difficulty:

```text
A
B
C
```

Инварианты:

1. опубликованная версия immutable;
2. correction создаёт новую version;
3. difficulty принадлежит item/version, не skill;
4. approved version обязана иметь AnswerKey;
5. approved version обязана иметь Primary Skill;
6. language входит в identity content version.

---

# 14. QuestionSkillLink

Связывает задание с навыками.

```text
QuestionSkillLink
- questionVersionId
- skillId
- role
- weight
```

Role:

```text
PRIMARY
SECONDARY
PREREQUISITE
```

Инварианты:

1. ровно один PRIMARY;
2. PRIMARY определяет основной learning target;
3. SECONDARY не должен использоваться для полного mastery credit автоматически;
4. mapping может иметь reviewer metadata;
5. mapping изменения versioned вместе с content mapping policy.

---

# 15. AnswerKey

Математическая истина задания.

```text
AnswerKey
- questionVersionId
- answerType
- canonicalAnswer
- validatorType
- tolerance?
```

ValidatorType:

```text
EXACT_OPTION
SET_OF_OPTIONS
INTEGER_EXACT
DECIMAL_TOLERANCE
RATIONAL_NORMALIZED
MATCHING_EXACT
SYMBOLIC_EQUIVALENCE
```

Инварианты:

1. AI не имеет права изменять AnswerKey;
2. answer validation детерминирован;
3. published AnswerKey immutable;
4. выявленная ошибка приводит к новой version / suspension.

---

# 16. Solution

Проверенное решение.

```text
Solution
- questionVersionId
- language
- steps
- fullExplanation
- verificationStatus
```

VerificationStatus:

```text
DRAFT
REVIEWED
VERIFIED
```

Tutor может использовать только допустимую проверенную solution context согласно policy.

---

# 17. ContentSource

Происхождение задания.

```text
ContentSource
- id
- sourceType
- title
- author?
- licenseType
- sourceUrl?
- usageNotes?
```

SourceType:

```text
INTERNAL_AUTHORED
EXPERT_AUTHORED
LICENSED
OPEN_LICENSE
PARAMETRIC_GENERATED
AI_ASSISTED_DRAFT
```

Инвариант:

> каждый APPROVED Question обязан иметь provenance.

---

# 18. PracticeSession

Одна учебная сессия.

```text
PracticeSession
- id
- studentId
- sessionType
- startedAt
- completedAt?
- status
- planId?
```

SessionType:

```text
DAILY_PLAN
FREE_PRACTICE
TARGETED_REMEDIATION
SPACED_REVIEW
DIAGNOSTIC
MOCK
```

Status:

```text
CREATED
ACTIVE
COMPLETED
ABANDONED
EXPIRED
```

Инварианты:

1. Session не хранит mastery;
2. MOCK запрещает Tutor;
3. completed session не принимает новые attempts;
4. abandoned session не считается автоматически completed.

---

# 19. Attempt

Факт ответа ученика на конкретную QuestionVersion.

```text
Attempt
- id
- studentId
- sessionId
- questionVersionId
- submittedAnswer
- correctness
- responseTimeMs?
- hintLevelUsed
- fullSolutionViewed
- selfConfidence?
- createdAt
```

Correctness:

```text
CORRECT
INCORRECT
PARTIALLY_CORRECT
UNSCORABLE
```

HintLevelUsed:

```text
NONE
LEVEL_1
LEVEL_2
LEVEL_3
CONCEPT_EXPLANATION
FULL_SOLUTION
```

Инварианты:

1. Attempt immutable после принятия;
2. correctness вычисляет validator;
3. LLM не определяет correctness;
4. fullSolutionViewed влияет на LearningEvidence policy;
5. один и тот же вопрос может иметь несколько attempts;
6. повторный attempt не уничтожает предыдущий.

---

# 20. Assessment

Более формальная проверка знаний.

```text
Assessment
- id
- studentId
- subjectId
- assessmentType
- sourceType
- sourceName?
- occurredAt
- score?
- maxScore?
- reliability
- status
```

AssessmentType:

```text
DIAGNOSTIC
INTERNAL_MOCK
EXTERNAL_MOCK
OFFICIAL_RESULT
PAPER_TEST
TOPIC_TEST
```

Status:

```text
DRAFT
PROCESSING
NEEDS_REVIEW
CONFIRMED
EXCLUDED
SUPERSEDED
```

Инварианты:

1. только CONFIRMED assessment создаёт активное external learning evidence;
2. EXCLUDED не участвует в mastery;
3. assessment score сам по себе не создаёт granular skill mastery;
4. total-score-only assessment используется прежде всего для readiness calibration.

---

# 21. AssessmentItem

Item-level результат assessment.

```text
AssessmentItem
- id
- assessmentId
- position
- questionVersionId?
- externalQuestionText?
- mappedSkillId?
- selectedAnswer?
- correctAnswer?
- correctness?
- extractionConfidence?
- mappingConfidence?
```

Инварианты:

1. external item может не иметь internal Question;
2. mappedSkill нужен только при достаточной confidence;
3. нельзя выдумывать correctAnswer, если source его не даёт;
4. mapping должен иметь provenance.

---

# 22. AssessmentTopicResult

Aggregate external result.

Пример:

```text
Тригонометрия: 4 / 7
```

Модель:

```text
AssessmentTopicResult
- id
- assessmentId
- curriculumNodeId
- correctCount?
- totalCount?
- percentage?
- mappingConfidence
```

Инварианты:

1. не распространять автоматически на все descendants;
2. хранить на ближайшем достоверном curriculum node;
3. aggregate evidence имеет меньшую granular precision.

---

# 23. LearningEvidence

Самый важный domain object после Skill.

```text
LearningEvidence
- id
- studentId
- curriculumNodeId
- evidenceType
- outcome
- sourceReliability
- extractionConfidence
- mappingConfidence
- itemQuality
- difficultyWeight
- attemptId?
- assessmentId?
- occurredAt
- algorithmVersion
- status
- provenance
```

EvidenceType:

```text
INTERNAL_INDEPENDENT_ATTEMPT
INTERNAL_ASSISTED_ATTEMPT
INTERNAL_MOCK_ITEM
DIAGNOSTIC_ITEM
EXTERNAL_ITEM_RESULT
EXTERNAL_TOPIC_SUMMARY
OFFICIAL_ASSESSMENT_RESULT
SELF_REPORTED_RESULT
```

Status:

```text
ACTIVE
SUPERSEDED
INVALIDATED
```

Инварианты:

1. evidence immutable;
2. correction не переписывает историю бесследно;
3. каждый evidence имеет provenance;
4. каждый evidence знает curriculum target;
5. external evidence имеет source reliability;
6. low-confidence import не может незаметно стать high-confidence mastery signal.

---

# 24. Provenance

Для каждого evidence система должна ответить:

> Откуда это известно?

Пример:

```text
Provenance
- sourceType
- sourceId
- uploadId?
- assessmentId?
- attemptId?
- pageNumber?
- externalItemNumber?
- extractedByModel?
- modelVersion?
- userConfirmed
- reviewerId?
```

Provenance — часть доверия и отладки.

---

# 25. SkillMasteryProjection

Не aggregate, а derived projection.

```text
SkillMasteryProjection
- studentId
- skillId
- mastery
- confidence
- effectiveEvidenceWeight
- lastEvidenceAt
- nextReviewAt?
- algorithmVersion
- calculatedAt
```

Инварианты:

1. полностью rebuildable из LearningEvidence;
2. не является permanent truth;
3. algorithmVersion обязателен;
4. manual edit запрещён;
5. user correction идёт через source/evidence, а не через mastery field.

---

# 26. TopicMasteryProjection

UI может показывать:

```text
Тригонометрия: 62%
```

Но это derived aggregate.

Нельзя хранить его как единственный learning state.

---

# 27. ErrorEvent

Факт диагностированной ошибки.

```text
ErrorEvent
- id
- studentId
- curriculumNodeId
- attemptId?
- assessmentItemId?
- errorType
- confidence
- detectionSource
- createdAt
```

ErrorType:

```text
CONCEPT_GAP
FORMULA_CONFUSION
SIGN_ERROR
ALGEBRA_MANIPULATION
CALCULATION_ERROR
READING_ERROR
METHOD_SELECTION_ERROR
DIAGRAM_INTERPRETATION
TIME_PRESSURE
CARELESS_ERROR
UNKNOWN
```

DetectionSource:

```text
RULE
TUTOR_MODEL
EXPERT
USER_CONFIRMED
```

Инвариант:

> AI classification ошибки не должна автоматически считаться 100% истинной.

---

# 28. LearningDisagreement

Отдельный diagnostic signal.

Пример:

```text
internal mastery = 0.82
official mock performance = 0.41
```

Модель:

```text
LearningDisagreement
- id
- studentId
- curriculumNodeId
- internalEstimate
- externalEstimate
- delta
- severity
- sourceAssessmentId
- status
```

Status:

```text
OPEN
VALIDATING
RESOLVED
DISMISSED
```

Используется Planning Engine.

---

# 29. DailyPlan

План на день.

```text
DailyPlan
- id
- studentId
- goalId
- planDate
- timeBudgetMinutes
- strategyVersion
- status
- generatedAt
```

Status:

```text
ACTIVE
COMPLETED
INVALIDATED
EXPIRED
SUPERSEDED
```

Инварианты:

1. максимум один ACTIVE plan на день и goal;
2. импорт нового сильного evidence может invalidate текущий plan;
3. plan должен уважать time budget;
4. plan содержит explainable reasons.

---

# 30. DailyPlanItem

Единица плана.

```text
DailyPlanItem
- id
- planId
- sequence
- activityType
- targetSkillId?
- questionId?
- targetMinutes
- reasonCode
- status
```

ActivityType:

```text
LEARN
PRACTICE
REVIEW
PREREQUISITE_CHECK
MIXED_CHECK
MINI_TEST
MOCK
```

ReasonCode:

```text
WEAK_SKILL
SPACED_REVIEW
PREREQUISITE_GAP
EXAM_PRIORITY
UNCERTAINTY_REDUCTION
EXTERNAL_INTERNAL_DISAGREEMENT
GOAL_GAP
```

Status:

```text
PENDING
IN_PROGRESS
COMPLETED
SKIPPED
```

---

# 31. RecommendationDecision

Очень полезный audit object.

```text
RecommendationDecision
- id
- studentId
- planId
- targetSkillId
- score
- factors
- algorithmVersion
- createdAt
```

`factors` может содержать:

```text
knowledgeGap
examWeight
uncertainty
reviewDue
prerequisitePenalty
urgency
expectedLearningGain
```

Это позволяет объяснить:

> почему система выбрала именно этот skill.

---

# 32. ImportJob

Aggregate ingestion pipeline.

```text
ImportJob
- id
- studentId
- status
- documentType?
- sourceHint?
- extractionVersion?
- overallConfidence?
- createdAt
- startedAt?
- completedAt?
```

State:

```text
CREATED
UPLOADED
SCANNING
CLASSIFYING
EXTRACTING
VALIDATING
NEEDS_REVIEW
CONFIRMED
APPLYING
COMPLETED
FAILED_RETRYABLE
FAILED_FINAL
CANCELLED
```

Инварианты:

1. transitions контролируются state machine;
2. confirm идемпотентен;
3. COMPLETED job повторно не создаёт evidence;
4. raw document считается untrusted data.

---

# 33. Upload

Физический объект файла.

```text
Upload
- id
- studentId
- storageKey
- originalFilename
- mimeType
- sizeBytes
- sha256
- status
- createdAt
- expiresAt
```

Status:

```text
PENDING
AVAILABLE
QUARANTINED
REJECTED
DELETED
```

Инварианты:

1. storageKey не является public URL;
2. ownership обязателен;
3. duplicate hash проверяется;
4. raw upload имеет retention policy.

---

# 34. ImportExtraction

Машинный результат распознавания.

```text
ImportExtraction
- id
- importJobId
- schemaVersion
- structuredPayload
- overallConfidence
- provider
- model
- createdAt
```

Это **не canonical assessment**.

До review это только предложение extraction layer.

---

# 35. ImportReviewItem

Поле, которое требует подтверждения.

```text
ImportReviewItem
- id
- importJobId
- fieldPath
- extractedValue
- confirmedValue?
- confidence
- status
```

Status:

```text
AUTO_ACCEPTED
REQUIRES_REVIEW
CONFIRMED
CORRECTED
REJECTED
```

---

# 36. TutorInteraction

Факт обращения к AI Tutor.

```text
TutorInteraction
- id
- studentId
- questionVersionId
- attemptId?
- requestedMode
- hintLevel
- promptVersion
- provider
- model
- responseStatus
- usage
- createdAt
```

RequestedMode:

```text
HINT
EXPLAIN
FULL_SOLUTION
CLARIFY
```

Инварианты:

1. TutorInteraction не меняет correctness;
2. TutorInteraction сам по себе не меняет mastery;
3. использование Tutor влияет на subsequent evidence policy;
4. полный ответ не даёт mastery credit как independent success.

---

# 37. AI Provider Result

AI-output не является domain entity.

Он становится доменно значимым только после:

```text
schema validation
+
policy validation
+
application mapping
```

---

# 38. Student Knowledge State

Важно:

> отдельной mutable сущности `StudentKnowledgeState` не нужно.

Она вычисляется из:

```text
SkillMasteryProjection
+
ErrorEvents
+
LearningDisagreements
+
Assessment History
+
Review Schedule
```

Это предотвращает giant god-object.

---

# 39. ReviewSchedule

Можно моделировать как projection:

```text
SkillReviewState
- studentId
- skillId
- lastSuccessfulReviewAt
- nextReviewAt
- intervalDays
- streak
- algorithmVersion
```

Не обязательно отдельный aggregate.

---

# 40. Readiness

Readiness — derived model.

```text
ReadinessProjection
- studentId
- subjectId
- rangeLow?
- rangeHigh?
- confidence
- calibrationEvidenceCount
- algorithmVersion
- calculatedAt
```

Инварианты:

1. readiness != guaranteed score;
2. нельзя показывать узкий score range без достаточной calibration evidence;
3. total score evidence не подменяет granular mastery.

---

# 41. MockExam

Не отдельная сущность от Assessment.

Internal Mock — это:

```text
Assessment
assessmentType = INTERNAL_MOCK
```

Practice mechanics могут использовать PracticeSession `MOCK`, но итог canonical result — Assessment.

---

# 42. Diagnostic

Diagnostic также не требует отдельного mega-domain.

Можно представить:

```text
Assessment(type=DIAGNOSTIC)
+
PracticeSession(type=DIAGNOSTIC)
+
DiagnosticPolicy
```

Результат превращается в LearningEvidence.

---

# 43. Domain Services

Нужны services, когда операция не принадлежит естественно одной entity.

## `AnswerValidationService`

```text
QuestionVersion + Answer
→ Correctness
```

## `EvidenceFactory`

```text
Attempt / Assessment result
→ LearningEvidence
```

## `MasteryCalculator`

```text
LearningEvidence[]
→ SkillMasteryProjection
```

## `RecommendationEngine`

```text
Student learning projections + Goal + Curriculum
→ ranked next actions
```

## `AssessmentImportMapper`

```text
Reviewed Extraction
→ Assessment
```

## `QuestionSelectionService`

```text
Skill + Mastery + History
→ QuestionVersion
```

---

# 44. Domain Events

Минимальный набор:

```text
UserRegistered
StudyGoalChanged

AttemptRecorded
PracticeSessionCompleted

AssessmentConfirmed
AssessmentExcluded

LearningEvidenceCreated
LearningEvidenceInvalidated
MasteryChanged
LearningDisagreementDetected

DailyPlanInvalidated
DailyPlanGenerated
DailyPlanCompleted

ImportConfirmed
ImportFailed

QuestionSuspended
```

---

# 45. Event Semantics

Domain event описывает **то, что уже произошло**.

Хорошо:

```text
AssessmentConfirmed
```

Плохо:

```text
ConfirmAssessment
```

Command и event — разные вещи.

---

# 46. Important Event Flow — Internal Attempt

```text
Student submits answer
        ↓
AnswerValidationService
        ↓
Attempt recorded
        ↓
AttemptRecorded
        ↓
EvidenceFactory
        ↓
LearningEvidenceCreated
        ↓
Mastery recalculation
        ↓
MasteryChanged?
        ↓
DailyPlan invalidate if meaningful
```

---

# 47. Important Event Flow — External Import

```text
Upload
  ↓
ImportJob
  ↓
Extraction
  ↓
User Review
  ↓
ImportConfirmed
  ↓
AssessmentConfirmed
  ↓
External LearningEvidence
  ↓
Mastery rebuild
  ↓
Disagreement detection
  ↓
Plan recalculation
```

---

# 48. Important Event Flow — Incorrect Content

```text
Question defect discovered
        ↓
QuestionSuspended
        ↓
Affected QuestionVersions identified
        ↓
Affected Attempts/Evidence identified
        ↓
Relevant evidence invalidated/superseded
        ↓
Mastery rebuild
        ↓
Plans recalculated
```

Это обязательная архитектурная возможность.

---

# 49. Domain Invariant — Mathematical Truth

Никогда:

```text
LLM says answer is B
→ mark attempt correct
```

Только:

```text
AnswerKey + Validator
→ correctness
```

---

# 50. Domain Invariant — Evidence First

Никогда:

```text
user uploaded score
→ mastery = 0.8
```

Только:

```text
source
→ Assessment
→ LearningEvidence
→ Mastery Projection
```

---

# 51. Domain Invariant — No Silent AI Authority

AI может предложить:

- skill mapping;
- error type;
- extraction;
- hint;
- explanation.

Но confidence/policy определяют, можно ли это использовать.

---

# 52. Domain Invariant — No Credential Import

External source integration не имеет сущности:

```text
ExternalPlatformPassword
```

Такой концепции в модели быть не должно.

Разрешённый future flow:

```text
OAuthConnection
```

только если официальный provider это поддерживает.

---

# 53. Domain Invariant — Historical Reproducibility

Для важного learning decision желательно знать:

```text
curriculumVersion
questionVersion
masteryAlgorithmVersion
recommendationAlgorithmVersion
promptVersion
sourceReliabilityPolicyVersion
```

Это позволяет воспроизводить решения.

---

# 54. Domain Invariant — Explainability

DailyPlanItem не должен существовать без объяснимой причины.

Минимум:

```text
reasonCode
```

Желательно:

```text
decision factors
```

---

# 55. Domain Invariant — Bilingual First

Для user-facing canonical content:

```text
ru
kk
```

должны быть first-class.

Не:

```text
RU + autoTranslate()
```

как permanent architecture.

---

# 56. Domain Invariant — Content Versioning

Нельзя редактировать опубликованное задание так:

```text
Question 123:
старый текст → новый текст
```

при сохранении той же historical semantics.

Новая содержательная версия:

```text
QuestionVersion v2
```

---

# 57. Domain Invariant — Skill Stability

Stable Skill Code не должен меняться при:

- смене UI label;
- исправлении перевода;
- новой curriculum version;
- изменении difficulty policy.

---

# 58. Domain Invariant — Delete vs Exclude

Learning history нельзя просто `DELETE`, если это ломает provenance.

Пользовательская ошибка импорта:

```text
Assessment → EXCLUDED
Evidence → INVALIDATED
Mastery → REBUILD
```

Raw file при этом может быть удалён физически согласно privacy policy.

---

# 59. Domain Invariant — Mastery Is Not Grade

`mastery = 0.72`

не означает:

```text
72% на ЕНТ
```

Это probability-like/internal estimate конкретного skill.

---

# 60. Domain Invariant — Score Is Not Mastery

`29/40`

не означает, что система знает:

```text
каждый skill = 72.5%
```

Total score используется для readiness calibration.

---

# 61. Domain Invariant — Assistance Matters

```text
correct independent
```

и:

```text
correct after full solution
```

не создают одинаковое evidence.

---

# 62. Domain Invariant — Time Matters Carefully

Response time — signal, не абсолютная истина.

Нельзя считать медленный ответ плохим без контекста:

- difficulty;
- accessibility;
- interruption;
- device;
- first exposure.

---

# 63. Domain Invariant — External Evidence Is Weighted

Официальный подтверждённый результат и self-report имеют разную reliability.

Policy versioned.

---

# 64. Domain Invariant — Confidence Is Separate from Mastery

Ученик может иметь:

```text
mastery = 0.75
confidence = 0.20
```

если данных мало.

Это отличается от:

```text
mastery = 0.75
confidence = 0.95
```

---

# 65. Domain Invariant — Uncertainty Can Generate Work

Низкая confidence — причина назначить diagnostic/check activity.

Не обязательно считать это weak skill.

---

# 66. Domain Invariant — Prerequisite Gap

Если target skill слаб:

```text
target = 0.35
```

но prerequisite ещё слабее:

```text
prerequisite = 0.20
```

Planning Engine должен уметь работать с причиной, а не только с симптомом.

---

# 67. Domain Invariant — Assessment Source Independence

Assessment model не должен иметь отдельную таблицу на каждый бренд.

Плохо:

```text
ntc_results
itest_results
daryn_results
```

Правильно:

```text
Assessment
sourceType
sourceName
provenance
```

Specific adapter переводит внешний формат в canonical model.

---

# 68. Domain Invariant — External Import Is Suggestive Until Confirmed

AI extraction:

```text
"score": 28
```

до confirmation не является canonical Assessment.

---

# 69. Domain Invariant — Duplicate Protection

Один и тот же внешний тест не должен дважды усиливать mastery.

Нужны:

- file hash;
- assessment fingerprint;
- idempotency.

---

# 70. Domain Invariant — User Can Correct AI

Любое распознанное поле с relevant impact должно быть:

- видно;
- исправимо;
- traceable.

---

# 71. Domain Invariant — Planning Is Disposable

DailyPlan можно удалить/rebuild как projection-like decision.

LearningEvidence нельзя.

---

# 72. Domain Invariant — Algorithms Are Versioned

Нельзя менять формулу mastery в production и забыть, что старые projections считались иначе.

---

# 73. Domain Invariant — No Fake Precision

UI/domain не должен притворяться, что знает:

```text
score = 31.783
```

если реальная uncertainty широкая.

---

# 74. Domain Invariant — Content Quality Before Quantity

Question имеет lifecycle и approval.

AI-generated draft никогда не становится APPROVED автоматически.

---

# 75. Domain Invariant — No Student Mastery from Unverified Question

Если Question suspended из-за неправильного answer key:

соответствующее evidence должно быть пересмотрено.

---

# 76. Domain Ownership Matrix

| Concept | Owner module |
|---|---|
| UserAccount | identity |
| StudentProfile | identity |
| StudyGoal | identity |
| Subject | curriculum |
| CurriculumVersion | curriculum |
| OfficialSection | curriculum |
| OfficialTopic | curriculum |
| Skill | curriculum |
| SkillPrerequisite | curriculum |
| Question | content |
| QuestionVersion | content |
| AnswerKey | content |
| Solution | content |
| ContentSource | content |
| PracticeSession | assessment/practice boundary |
| Attempt | assessment/practice boundary |
| Assessment | assessment |
| AssessmentItem | assessment |
| AssessmentTopicResult | assessment |
| Upload | ingestion |
| ImportJob | ingestion |
| ImportExtraction | ingestion |
| ImportReviewItem | ingestion |
| LearningEvidence | learning |
| SkillMasteryProjection | learning |
| ErrorEvent | learning |
| LearningDisagreement | learning |
| DailyPlan | planning |
| DailyPlanItem | planning |
| RecommendationDecision | planning |
| TutorInteraction | tutor |
| ReadinessProjection | progress/learning read model |

---

# 77. Recommended Package Boundaries

Backend:

```text
com.entmath.identity
com.entmath.curriculum
com.entmath.content
com.entmath.assessment
com.entmath.ingestion
com.entmath.learning
com.entmath.planning
com.entmath.tutor
com.entmath.progress
com.entmath.admin
```

Внутри модуля:

```text
api/
application/
domain/
infrastructure/
```

Не обязательно механически создавать 4 папки в каждом маленьком модуле, но dependencies должны отражать это направление.

---

# 78. Domain Layer Rule

`domain/` не зависит от:

- Spring MVC;
- JPA repository implementation;
- Gemini SDK;
- S3 SDK;
- Next.js;
- HTTP.

Domain может зависеть от:

- Java standard library;
- value objects;
- domain interfaces;
- deterministic math utilities.

---

# 79. Application Layer Rule

Application layer:

- orchestrates use cases;
- starts transaction;
- loads aggregates;
- calls domain services;
- saves;
- emits events.

Не содержит:

- HTTP-specific parsing;
- raw AI SDK;
- SQL.

---

# 80. Infrastructure Layer Rule

Infrastructure implements ports:

```text
QuestionRepository
BlobStorage
AiProvider
JobQueue
EmailSender
Clock
```

---

# 81. Value Objects

Рекомендуемые value objects:

```text
StudentId
SkillCode
CurriculumCode
QuestionCode
Score
Mastery
Confidence
Reliability
Difficulty
Language
ExamDate
TimeBudget
```

Преимущество:

```text
double mastery
```

не перепутается с:

```text
double confidence
```

---

# 82. Mastery Value Object

```text
Mastery
value ∈ [0,1]
```

Не предоставляет setter.

---

# 83. Confidence Value Object

```text
Confidence
value ∈ [0,1]
```

Не означает probability of correctness конкретного question.

---

# 84. Reliability Value Object

```text
Reliability
value ∈ [0,1]
policyVersion
```

---

# 85. Score Value Object

Нельзя просто:

```text
int score
```

Нужно:

```text
Score
- earned
- maximum
```

Чтобы:

```text
29/40
```

не спутать с:

```text
29/50
```

---

# 86. Exam Target Value

Для профильного предмета официальный assessment может иметь:

- 40 tasks;
- до 50 points.

Поэтому domain должен различать:

```text
raw correct count
```

и:

```text
official points
```

если scoring format это требует.

Не предполагать, что `questions == points`.

---

# 87. QuestionResult

Полезный VO:

```text
QuestionResult
- correctness
- earnedPoints
- maxPoints
```

---

# 88. Attempt Assistance Profile

Вместо одного boolean `usedHint`:

```text
AssistanceProfile
- highestHintLevel
- fullSolutionViewed
- tutorTurns
```

Это пригодится EvidenceFactory.

---

# 89. Assessment Source

VO:

```text
AssessmentSource
- type
- name?
- externalReference?
- reliabilityPolicyKey
```

---

# 90. Curriculum Node

External topic evidence не всегда granular Skill.

Поэтому LearningEvidence target может быть абстракцией:

```text
CurriculumNode
```

Types:

```text
OFFICIAL_TOPIC
SKILL
```

Для Alpha этого достаточно.

Не надо делать generic graph DB.

---

# 91. Evidence Target Precision

LearningEvidence хранит:

```text
targetNodeType
targetNodeId
precision
```

Precision:

```text
ITEM_SKILL
TOPIC_AGGREGATE
SUBJECT_TOTAL
```

Это помогает не путать granularity.

---

# 92. Evidence Outcome

Для skill-level item:

```text
0..1
```

Для aggregate topic:

можно хранить:

```text
correct/total
```

и при projection преобразовывать policy-based.

Не терять исходные counts.

---

# 93. Assessment Correction Model

Если после confirmation обнаружена ошибка:

```text
AssessmentCorrection
- assessmentId
- field
- oldValue
- newValue
- reason
- correctedAt
```

После:

```text
AffectedEvidenceInvalidated
→ replacement evidence
→ mastery rebuild
```

---

# 94. Content Defect Model

```text
ContentIssue
- questionVersionId
- issueType
- severity
- status
- reportedBy
- createdAt
```

IssueType:

```text
WRONG_ANSWER
AMBIGUOUS_STEM
BROKEN_FORMATTING
TRANSLATION_ERROR
WRONG_SKILL_MAPPING
WRONG_DIFFICULTY
```

---

# 95. Why Wrong Skill Mapping Matters

Если Question математически правильный, но mapping неверный:

```text
attempt
→ evidence
→ wrong mastery
```

Поэтому mapping defect — не cosmetic issue.

---

# 96. Domain Policies

Некоторые вещи не сущности, а versioned policies:

```text
SourceReliabilityPolicy
EvidenceWeightPolicy
MasteryPolicy
RecommendationPolicy
DiagnosticPolicy
TutorPolicy
ScoreCalibrationPolicy
RetentionPolicy
```

---

# 97. Policy Versioning

Пример:

```text
source-reliability-v1
mastery-v1
recommendation-v1
```

LearningEvidence хранит relevant policy version там, где это нужно для reproducibility.

---

# 98. Time as Dependency

Domain calculations должны получать `Clock`.

Не использовать:

```java
LocalDateTime.now()
```

внутри core domain logic напрямую.

Это нужно для:

- tests;
- spaced repetition;
- recency decay;
- exam urgency.

---

# 99. Timezone Rule

User timezone:

```text
Asia/Almaty
```

может быть default, но хранится как profile setting.

DailyPlan date определяется в timezone студента.

---

# 100. Aggregate Transaction Rules

## Attempt

В одной транзакции:

```text
Attempt save
+
AttemptRecorded event
```

## Import confirmation

В одной logical operation:

```text
Import review finalized
+
Assessment canonicalized
+
AssessmentConfirmed
```

Evidence может строиться subscriber'ом с reliable event publication.

---

# 101. Eventual Consistency

Допустима для:

- mastery projection;
- progress dashboard;
- plan refresh;
- analytics.

Не допустима для:

- auth;
- ownership;
- answer correctness;
- import confirmation state.

---

# 102. Read Models

Для UI не нужно заставлять frontend собирать domain сам.

Read models:

```text
TodayView
ProgressOverview
SkillMapView
AssessmentTimelineView
ErrorNotebookView
DiagnosticResultView
ImportReviewView
```

---

# 103. TodayView

Пример:

```json
{
  "date": "2026-10-05",
  "targetMinutes": 30,
  "estimatedMinutes": 28,
  "items": [
    {
      "type": "PRACTICE",
      "skillCode": "EQ-TRIG-02",
      "reasonCode": "WEAK_SKILL"
    }
  ]
}
```

---

# 104. SkillMapView

Не раскрывать внутреннюю формулу полностью.

```json
{
  "skillCode": "EQ-TRIG-02",
  "mastery": 0.42,
  "confidence": 0.71,
  "status": "PRIORITY"
}
```

---

# 105. Skill State Labels

UI labels derived:

```text
UNKNOWN
EMERGING
DEVELOPING
STRONG
MASTERED_LIKE
NEEDS_REVIEW
```

Они не являются source of truth.

---

# 106. Domain Error Codes

Примеры:

```text
QUESTION_NOT_APPROVED
ASSESSMENT_ALREADY_CONFIRMED
IMPORT_REQUIRES_REVIEW
IMPORT_DUPLICATE
ATTEMPT_SESSION_CLOSED
FULL_SOLUTION_NOT_ALLOWED_IN_MOCK
INVALID_CURRICULUM_MAPPING
PLAN_ALREADY_SUPERSEDED
```

---

# 107. Idempotency Invariants

Особенно:

```text
submit attempt
confirm import
complete assessment
apply evidence
```

Повторный HTTP request не должен создавать дубль.

---

# 108. Soft vs Hard Delete

Hard delete допустим для:

- expired raw uploads;
- temporary extraction artifacts;
- privacy-required purge.

Soft/status-based history для:

- Question;
- Assessment;
- LearningEvidence;
- CurriculumVersion.

---

# 109. Domain Audit Requirements

Audit обязателен для:

- Question approval;
- Question suspension;
- AnswerKey correction;
- curriculum change;
- source reliability policy;
- manual assessment override;
- evidence invalidation by admin.

---

# 110. Student-visible Audit

Пользователю не нужен системный audit log.

Но для внешнего assessment желательно показывать:

```text
Source
Date
Imported at
Confirmed by you
Used in learning model: yes/no
```

---

# 111. Parent Domain — Later

Не включать в Alpha core.

Будущие entities:

```text
GuardianLink
GuardianPermission
WeeklyReport
```

Не добавлять сейчас `parent_id` во все student tables.

---

# 112. Billing Domain — Later

Будущее:

```text
Subscription
Plan
Entitlement
Payment
```

Learning domain не должен проверять Stripe напрямую.

Будет:

```text
EntitlementService
```

---

# 113. Notification Domain — Later

Планирование learning activity не должно отправлять push самостоятельно.

Событие:

```text
ReviewDue
```

Notification module решает канал.

---

# 114. Multi-subject Expansion

Чтобы расшириться:

```text
Subject
CurriculumVersion
Skill
Question
Evidence
Mastery
```

уже generic.

Но не делать abstraction настолько generic, чтобы mathematics становится неудобной.

Правило:

> generic where proven, specific where needed.

---

# 115. Mathematical Extensions

В будущем может появиться:

```text
ExpressionAnswer
SymbolicStep
HandwrittenStep
```

Но не включать их в Alpha aggregate design до реальной необходимости.

---

# 116. Domain Model — High-Level Diagram

```text
UserAccount
    │ 1
    ▼
StudentProfile
    │
    ├────────────→ StudyGoal
    │
    ├────────────→ PracticeSession
    │                  │
    │                  ▼
    │               Attempt ───────┐
    │                              │
    ├────────────→ Assessment ─────┤
    │                              │
    │                              ▼
    │                      LearningEvidence
    │                              │
    │                              ▼
    │                    SkillMasteryProjection
    │                              │
    │                              ▼
    └──────────────────────→ DailyPlan
                                   │
                                   ▼
                             DailyPlanItem

CurriculumVersion
    │
    ├── OfficialSection
    │      └── OfficialTopic
    │             └── Skill
    │                   └── SkillPrerequisite
    │
    └──────────────→ QuestionVersion
                           │
                           ├── AnswerKey
                           ├── Solution
                           └── QuestionSkillLink
```

---

# 117. Import Diagram

```text
Upload
   │
   ▼
ImportJob
   │
   ▼
ImportExtraction
   │
   ▼
ImportReviewItem
   │
   ▼
Assessment
   │
   ├── AssessmentItem
   └── AssessmentTopicResult
            │
            ▼
     LearningEvidence
```

---

# 118. Tutor Diagram

```text
Attempt
   │
   ├────→ AnswerValidator ───→ Correctness
   │
   └────→ TutorInteraction
                 │
                 ▼
             AI Provider

AI Provider
DOES NOT
modify Attempt.correctness
```

---

# 119. Domain Model Anti-Patterns

Запрещено:

## God Student

```text
Student {
  questions
  mastery
  imports
  payments
  plans
  tutorChats
  everything
}
```

## AI Service Everywhere

```text
QuestionService → Gemini
AssessmentService → Gemini
PlanningService → Gemini
MasteryService → Gemini
```

AI должен быть ограничен adapters/use cases.

## Mutable Mastery

```text
student.mastery += 10
```

## Topic Percentage as Truth

```text
student.trigonometry = 72
```

## Question Text Without Version

```text
question.text = newText
```

## Brand-specific assessment tables

```text
ntc_result
itest_result
daryn_result
```

---

# 120. Alpha Domain Scope

Обязательно реализовать:

```text
UserAccount
StudentProfile
StudyGoal

CurriculumVersion
OfficialTopic
Skill
SkillPrerequisite

Question
QuestionVersion
QuestionSkillLink
AnswerKey
Solution

PracticeSession
Attempt

Assessment
AssessmentItem
AssessmentTopicResult

Upload
ImportJob
ImportExtraction
ImportReviewItem

LearningEvidence
SkillMasteryProjection
ErrorEvent

DailyPlan
DailyPlanItem
RecommendationDecision

TutorInteraction
```

---

# 121. Not Required in Alpha

Не реализовывать пока:

```text
GuardianLink
Billing
School
Classroom
Teacher
DeepKnowledgeModel
VectorEmbedding
VoiceSession
HandwritingStepGraph
SocialChallenge
AchievementEconomy
```

---

# 122. Persistence Guidance

Не каждая domain concept обязана иметь отдельную таблицу.

Например:

```text
Score
Mastery
Confidence
Reliability
AssistanceProfile
```

могут быть embeddable/value objects.

Но:

```text
LearningEvidence
Assessment
Attempt
QuestionVersion
```

должны сохраняться явно.

---

# 123. JSONB Guidance

JSONB допустим для:

- structured submitted answers;
- provider metadata;
- extraction payload;
- recommendation factor snapshot;
- provenance extras.

JSONB не должен заменять нормальную модель:

Плохо:

```text
student_state JSONB
```

со всем продуктом внутри.

---

# 124. External IDs

Внутри:

```text
UUID
```

Внешний source identifier хранится отдельно.

Никогда не делать official platform ID нашим primary key.

---

# 125. Database Ownership

Даже при одной PostgreSQL:

каждый module логически владеет своими таблицами.

Другой module не должен делать repository access напрямую в чужую таблицу.

Взаимодействие:

- public application interface;
- read projection;
- event.

---

# 126. Domain Contract for Learning Engine

`04_LEARNING_ENGINE.md` получает из этой модели:

```text
LearningEvidence
SkillPrerequisite
SkillMasteryProjection
ErrorEvent
Assessment
StudyGoal
```

и обязан определить:

1. evidence weighting;
2. mastery formula;
3. confidence;
4. decay;
5. topic aggregation;
6. recommendation score;
7. prerequisite logic;
8. spaced repetition;
9. disagreement handling;
10. readiness calibration.

---

# 127. Domain Contract for AI Tutor

`05_AI_TUTOR.md` получает:

```text
QuestionVersion
AnswerKey
Solution
Attempt
Skill
ErrorEvent?
Student language
TutorInteraction history
```

И не имеет права напрямую писать:

```text
SkillMasteryProjection
LearningEvidence
AnswerKey
```

---

# 128. Domain Contract for Content Pipeline

`06_CONTENT_STRATEGY.md` обязан определить:

- authoring;
- review;
- licensing;
- bilingual validation;
- difficulty calibration;
- skill mapping;
- content defect handling;
- AI-assisted generation rules.

---

# 129. Domain Contract for External Import

Import layer отвечает только за:

```text
untrusted document
→ reviewed structured assessment
```

После этого он заканчивает ответственность.

Он не вычисляет Mastery.

---

# 130. Definition of Domain Done

Доменная модель считается достаточно определённой для Alpha, если:

- [x] Curriculum отделён от learning state.
- [x] Question versioned.
- [x] Correctness deterministic.
- [x] Assessment generic по source.
- [x] External import имеет review boundary.
- [x] LearningEvidence immutable/rebuildable.
- [x] Mastery является projection.
- [x] Plan explainable и disposable.
- [x] AI не владеет математической истиной.
- [x] Source provenance сохраняется.
- [x] Algorithm policies versioned.
- [x] Bilingual domain поддерживается.
- [x] Privacy-sensitive raw uploads отделены от normalized learning data.

---

# 131. Open Domain Decisions

Эти вопросы остаются для следующих документов:

1. Точный `Mastery v1`.
2. Как преобразовать aggregate topic evidence в projection.
3. Как агрегировать granular skills в TopicMastery.
4. Как считать confidence.
5. Какой decay использовать.
6. Как измерять item quality.
7. Как калибровать source reliability.
8. Как определять learning disagreement threshold.
9. Как формировать Daily Plan.
10. Как оценивать readiness.
11. Нужна ли partial correctness в MATCHING/MULTI_SELECT.
12. Какой minimum evidence нужен для high-confidence mastery.

Они относятся к `04_LEARNING_ENGINE.md`, а не должны решаться случайно внутри entity-классов.

---

# 132. Final Domain Principle

Главная ошибка, которую нужно избежать:

```text
UI
→ Controller
→ Service
→ Entity
→ Gemini
```

Правильная модель:

```text
Curriculum
     +
Verified Content
     +
Student Activity
     +
External Assessments
     ↓
Learning Evidence
     ↓
Rebuildable Knowledge Model
     ↓
Explainable Planning
     ↓
AI-assisted Pedagogy
```

Именно эта доменная модель позволяет ENT Math AI оставаться образовательной системой, даже если завтра:

- мы поменяем Gemini на другую модель;
- выйдет новая спецификация ЕНТ;
- изменится mastery algorithm;
- появятся новые источники пробников;
- добавятся физика и информатика.

---

# 133. Next Document

Следующий обязательный документ:

> **`04_LEARNING_ENGINE.md`**

В нём нужно уже математически зафиксировать:

```text
Evidence Weighting
Mastery v1
Confidence v1
Recency Decay
Topic Aggregation
Prerequisite Detection
Next-Best-Action
Question Difficulty Adaptation
Spaced Repetition
External/Internal Disagreement
Daily Mission Composition
Readiness / Score Calibration
```

Это будет интеллектуальное ядро ENT Math AI.
