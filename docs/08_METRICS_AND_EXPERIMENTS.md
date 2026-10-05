# 08_METRICS_AND_EXPERIMENTS.md
## ENT Math AI — Metrics, Validation & Experimentation Framework

> **Статус:** canonical measurement specification  
> **Версия:** `METRICS-v1`  
> **Дата:** 5 октября 2026  
> **Связанные документы:**  
> `00_CONCEPT.md`  
> `02_CURRICULUM_MAP.md`  
> `03_DOMAIN.md`  
> `04_LEARNING_ENGINE.md`  
> `05_AI_TUTOR.md`  
> `06_CONTENT_STRATEGY.md`  
> `07_MVP_SCOPE.md`
>
> **Назначение:** определить, как ENT Math AI доказывает, что продукт:
>
> - полезен;
> - возвращает пользователей;
> - реально улучшает независимый результат;
> - корректно работает как adaptive system;
> - не создаёт ложную персонализацию;
> - экономически управляем;
> - готов к переходу Alpha → Beta → Paid Beta.

---

# 1. Главный принцип измерения

ENT Math AI нельзя оценивать одной метрикой.

Высокое:

```text
time in app
```

может означать:

```text
ученик учится
```

или:

```text
продукт запутанный и задачи слишком сложные
```

Высокое:

```text
Tutor usage
```

может означать:

```text
Tutor полезен
```

или:

```text
ученик перестал решать самостоятельно
```

Поэтому система метрик должна разделять:

```text
Product Usage
Learning Outcome
Learning Quality
Trust / Reliability
AI Quality
Content Quality
Unit Economics
```

---

# 2. Две вершины системы метрик

У ENT Math AI две ключевые вершины.

## Product North Star

> **Weekly Effective Learners**

Количество учеников, которые за неделю получили реальную персонализированную учебную ценность.

Рабочее определение:

```text
student has:
>= 3 Meaningful Learning Sessions in 7 days
AND
>= 1 independent validation activity
```

## Outcome North Star

> **Independent Learning Gain**

Изменение результата на независимом mixed / holdout assessment.

Это главная проверка того, что продукт действительно учит.

---

# 3. Почему North Star не равен DAU

`DAU` полезен как operational metric.

Но:

```text
DAU ↑
```

не доказывает learning.

Мы не строим social feed.

---

# 4. Meaningful Learning Session

Session считается meaningful, если:

```text
duration >= MIN_MEANINGFUL_DURATION
AND
meaningful attempts >= MIN_ATTEMPTS
AND
session not abandoned immediately
```

Initial hypothesis:

```text
MIN_MEANINGFUL_DURATION = 8 minutes
MIN_ATTEMPTS = 5
```

Но session также может считаться meaningful, если это:

```text
completed mini mock
```

или:

```text
validated diagnostic block
```

---

# 5. Independent Validation Activity

Это activity, где:

```text
no Tutor
no hint
no full solution
```

и задача:

- unseen;
- или sufficiently separated from prior exposure;
- ideally mixed/holdout.

Это критично для outcome measurement.

---

# 6. Core Metric Layers

```text
L0 — Reliability / Safety
L1 — Acquisition
L2 — Activation
L3 — Engagement
L4 — Retention
L5 — Learning
L6 — Trust / Quality
L7 — Economics
```

Если L0 broken:

остальные цифры не имеют значения.

---

# 7. L0 — Reliability Metrics

Обязательно:

```text
API success rate
API latency
import failure rate
Tutor failure rate
content critical defect rate
duplicate evidence rate
mastery rebuild failures
security incidents
```

---

# 8. Learning Truth Reliability

Critical metric:

```text
Invalid Learning Evidence Rate
```

То есть evidence, который позже пришлось invalidated из-за:

- wrong answer key;
- duplicate import;
- bad mapping;
- broken extraction.

Цель:

```text
как можно ближе к нулю
```

---

# 9. Acquisition Metrics

Пока не главный приоритет Alpha.

Минимум:

```text
Landing Visitors
Signup Conversion
Cost per Signup
Traffic Source
```

Paid acquisition:

OUT until core loop validated.

---

# 10. Activation Funnel

Canonical funnel:

```text
Landing
  ↓
Signup
  ↓
Onboarding Complete
  ↓
Initial State Started
  ↓
Initial State Completed
  ↓
First Personalized Session Started
  ↓
First Personalized Session Completed
```

---

# 11. Activation Definition

Activated student:

```text
Initial Knowledge State exists
AND
first personalized learning session completed
```

Initial state can come from:

```text
Diagnostic
or
Imported Assessment + sufficient targeted checks
```

---

# 12. Activation Metrics

Track:

```text
Signup → Onboarding
Onboarding → Diagnostic/Import Start
Start → Initial State Complete
Initial State → First Session Start
First Session Start → Complete
Signup → Activation
```

---

# 13. Time to Value

Metric:

```text
TimeToInitialValue
```

From:

```text
signup
```

to:

```text
first personalized recommendation visible
```

And:

```text
TimeToFirstLearningSession
```

---

# 14. Why Import Matters Here

Hypothesis:

```text
Import flow
```

reduces:

```text
time to useful profile
```

for users who already have tests.

Experiment later:

```text
Diagnostic-first
vs
Choice: Diagnostic or Import
```

---

# 15. Early Activation Targets

Internal hypotheses, NOT industry benchmarks:

```text
Onboarding completion             >= 75%
Initial-state start               >= 70%
Initial-state completion          >= 60%
Activated after initial state     >= 70%
Signup → full activation          >= 40%
```

These thresholds are provisional.

---

# 16. Engagement Metrics

Track:

```text
Sessions / Active Week
Meaningful Sessions / Week
Questions / Session
Daily Mission Start Rate
Daily Mission Completion Rate
Free Practice Share
Tutor Usage
Import Usage
Mini Mock Usage
```

---

# 17. Do Not Optimize Raw Questions Solved

Bad product objective:

```text
maximize questions solved
```

Possible failure:

students spam easy questions.

Better:

```text
meaningful independent practice
+
learning progression
```

---

# 18. Daily Mission Adoption

Metric:

```text
DailyMissionAdoption =
students who start Today recommendation
/
students who open app with active plan
```

---

# 19. Daily Mission Completion

```text
DailyMissionCompletion =
completed plans
/
started plans
```

But inspect:

- time budget;
- difficulty;
- reason for abandonment.

---

# 20. Recommendation Acceptance

When student has recommended Today plan but chooses free practice:

track:

```text
RECOMMENDATION_ACCEPTED
RECOMMENDATION_BYPASSED
```

This reveals trust in adaptive engine.

---

# 21. Recommendation Override Reason

Optional lightweight feedback:

```text
Не хочу эту тему
Слишком сложно
Слишком легко
Хочу другую тему
Другое
```

Do not force every time.

---

# 22. Retention Definitions

## D1

Student returns and completes meaningful activity on day after activation.

## D7

Student completes meaningful activity during day 7 window.

For small Alpha:

also use:

```text
Week-1 Retention
```

because exact-day metrics are noisy.

---

# 23. Weekly Retention

Recommended:

```text
W1
W2
W4
```

Definition:

student completes ≥1 meaningful session in week window.

---

# 24. Habit Metric

```text
Meaningful Learning Days / Week
```

Better than app opens.

---

# 25. Closed Alpha Retention Targets

Internal hypotheses:

```text
D1 >= 40%
D7 >= 20%
W2 >= 20%
retained learners:
>= 3 meaningful sessions/week
```

Not universal industry benchmarks.

---

# 26. Why Small Alpha Retention Is Noisy

With:

```text
n = 30
```

one student changes metric by:

```text
3.3 percentage points
```

Therefore always report:

```text
count + percentage
```

Example:

```text
9 / 30 = 30%
```

not only `30%`.

---

# 27. Learning Metrics — Highest Priority

Core:

```text
Independent Mixed Assessment Gain
Holdout Accuracy Gain
Difficulty Progression
Skill Mastery Calibration
Transfer Success
Error Recurrence Reduction
Review Success
```

---

# 28. Baseline Assessment

Before substantial product usage:

```text
Baseline Mini Mock
```

Recommended:

```text
15–20 mixed items
```

from supported Alpha scope.

No Tutor.

Timed.

---

# 29. Follow-up Assessment

After:

```text
4–6 weeks
```

use:

```text
same blueprint
different holdout items
```

Do not reuse exact questions.

---

# 30. Primary Learning Outcome

For student `i`:

```text
Gain_i =
FollowUpScore_i
-
BaselineScore_i
```

At cohort level report:

```text
mean gain
median gain
distribution
confidence interval
```

---

# 31. Normalized Gain

Can additionally calculate:

```text
NormalizedGain =
(followup - baseline)
/
(maxScore - baseline)
```

Use carefully.

Do not make it primary until data quality proven.

---

# 32. Skill-Level Learning Gain

For selected skills:

```text
Pre Holdout Accuracy
vs
Post Holdout Accuracy
```

Important:

holdout item families should differ from practice families.

---

# 33. Transfer Success

After guided learning:

```text
near-transfer independent success
```

and later:

```text
farther mixed transfer success
```

This is more meaningful than same-template repetition.

---

# 34. Tutor-Assisted Recovery Metric

Canonical:

```text
RecoverySuccess =
independent transfer success
after Tutor-assisted episode
```

Window:

```text
same session / next targeted check
```

---

# 35. Full Solution Recovery

Metric:

```text
FullSolutionRecoveryRate
```

Student saw full solution, then later solves:

```text
new related item independently
```

This measures whether full solution actually helps learning.

---

# 36. Hint Dependency

```text
HintDependencyRatio =
assisted correct attempts
/
all correct attempts
```

Trend matters more than absolute value.

Desired:

```text
decrease over repeated skill practice
```

---

# 37. Difficulty Progression

For a skill:

```text
A → B → C
```

Measure:

```text
highest independently sustained difficulty
```

Not simply highest attempted difficulty.

---

# 38. Sustained Difficulty

Example:

```text
3 independent B-level successes
across distinct families
```

better evidence than:

```text
1 lucky C correct
```

---

# 39. Review Success

When review becomes due:

```text
Review Success Rate =
successful independent retrieval
/
review attempts
```

Track by interval.

---

# 40. Forgetting Model Evaluation

Bucket by predicted retrievability:

```text
R 0.9–1.0
R 0.8–0.9
R 0.7–0.8
...
```

Compare actual success.

If:

```text
predicted R = 0.8
```

actual success should eventually be near that region.

This calibrates Review Model.

---

# 41. Mastery Calibration

Bucket predicted mastery:

```text
0.2–0.3
0.3–0.4
...
0.8–0.9
```

Then inspect independent next-item correctness.

---

# 42. Brier Score

For independent binary outcome:

```text
Brier =
mean((p_i - y_i)^2)
```

Lower is better.

Useful because it measures probability calibration.

---

# 43. Log Loss

Also track:

```text
-log likelihood
```

Punishes confident wrong predictions.

---

# 44. AUC

Can track, but:

> AUC alone is insufficient.

A model can rank students well but be badly calibrated.

---

# 45. Calibration Error

Use:

```text
Expected Calibration Error
```

or reliability diagram.

Do not rely on one metric only.

---

# 46. Recommendation Quality

Metrics:

```text
Plan Acceptance
Plan Completion
Post-Plan Independent Success
Time-to-Next-Skill-Improvement
Prerequisite Recovery Success
```

---

# 47. Recommendation Baseline

Always compare adaptive engine against a simple baseline.

Baseline:

```text
weakest skill first
+
fixed review schedule
```

If adaptive algorithm does not beat baseline:

complexity not justified.

---

# 48. Alpha Recommendation Evaluation

Small Alpha cannot robustly A/B every learner.

Use:

```text
offline replay
synthetic profiles
qualitative analysis
within-student outcome
```

A/B later with larger sample.

---

# 49. Prerequisite Routing Metric

When prerequisite gap detected:

```text
PrerequisiteRecovery =
target skill performance after prerequisite remediation
```

Compare to cases where student continues direct drilling.

---

# 50. Error Intelligence Metrics

Track:

```text
error type frequency
repeat error rate
error resolution rate
```

---

# 51. Error Recurrence

For error pattern:

```text
recurrence within 30 days
```

Goal:

decrease after targeted remediation.

---

# 52. Tutor Metrics

Core:

```text
Tutor Request Rate
Hint Level Distribution
Full Solution Rate
Tutor Failure Rate
Tutor Latency
Leakage Reject Rate
Semantic Reject Rate
Recovery Success
```

---

# 53. Tutor Usage Is Not Success

Never declare:

```text
Tutor calls ↑
→ feature successful
```

Success requires:

```text
help
→ independent success
```

---

# 54. Tutor Answer Leakage

Metric:

```text
LeakageDetected / TutorGenerations
```

Track separately:

```text
caught before display
reported after display
```

Displayed leakage is much more severe.

---

# 55. Tutor Math Error Rate

From:

- automated validation;
- user reports;
- sampled expert review.

Metric:

```text
Verified Math Defects / Reviewed Tutor Responses
```

---

# 56. Tutor Bilingual Quality

Separate:

```text
RU
KK
```

Never average them into one number only.

---

# 57. Tutor Human Rating

Periodic expert scoring:

```text
Math Correctness
Hint Appropriateness
Clarity
Language Naturalness
Answer Leakage
```

Scale:

```text
1–5
```

---

# 58. Import Metrics

Core:

```text
Import Start Rate
Upload Success
Extraction Success
Needs Review Rate
Confirmation Rate
Correction Rate
Time to Confirmation
Duplicate Detection
Import → Plan Change
```

---

# 59. Extraction Success

Definition:

```text
document processed
AND
minimum required fields extracted
```

Not:

```text
AI returned JSON
```

---

# 60. Field-Level Correction Rate

Track separately:

```text
score
maxScore
date
source
topic result
item answer
skill mapping
```

This tells where extraction is weak.

---

# 61. Import Trust Metric

After confirmation optional question:

> «Результат распознан правильно?»

But actual correction behavior is stronger signal.

---

# 62. Import Value

Metric:

```text
Imported users
vs
Diagnostic-only users
```

Compare:

- time to activation;
- activation completion;
- early retention;
- trust interviews.

Do not claim causality without experiment.

---

# 63. Import → Learning Impact

Metric:

```text
% confirmed imports
that materially change:
- plan
- readiness
- diagnostic needs
```

If import never changes anything:

feature may be cosmetic.

---

# 64. Content Metrics

Core:

```text
Coverage
Critical Defects
Issue Rate
Skill Distribution
Difficulty Distribution
Language Parity
Question Family Concentration
Holdout Availability
```

---

# 65. Content Critical Defect Rate

```text
Critical defects
/
10,000 item exposures
```

Track:

- wrong answer;
- impossible question;
- two answers;
- severe mistranslation.

---

# 66. Student Content Report Rate

```text
reports
/
1,000 exposures
```

Break down by:

- issue type;
- language;
- author;
- question family.

---

# 67. Content Coverage

Per skill:

```text
eligible questions by:
language
difficulty
format
```

---

# 68. Content Repetition Concentration

Metric:

```text
Top 10% questions
share of total exposures
```

If too high:

bank too thin.

---

# 69. Family Repetition

Track:

```text
same family exposure within 7 days
```

Important for false mastery.

---

# 70. Content Empirical Difficulty

For item:

```text
independent success rate
```

but do not equate directly to A/B/C without calibration.

---

# 71. AI Cost Metrics

Core:

```text
AI Cost / WAU
AI Cost / Activated Student
Tutor Cost / Session
Import Cost / Confirmed Import
Content AI Cost / Approved Question
```

---

# 72. Cost Segmentation

Separate:

```text
Tutor
Import
Content Authoring
Other
```

Never one giant Gemini bill.

---

# 73. Unit Economics — Early

Before monetization:

track:

```text
monthly infrastructure cost
AI variable cost
content production cost
support time
```

---

# 74. AI Cost Guardrail

Internal hypothesis for Beta:

AI variable cost should be comfortably below expected subscription gross margin.

Do not fix exact KZT threshold until pricing tested.

---

# 75. Cost Outlier Detection

Alert if user/day:

```text
Tutor calls unusually high
Import retries unusually high
```

Could be bug or abuse.

---

# 76. Product Trust Metrics

Track:

```text
Import corrections
Content error reports
Tutor error reports
Plan bypass rate
Assessment exclusions
```

High trust is essential for educational product.

---

# 77. Knowledge Map Trust

Interview question:

> «Насколько карта слабых тем совпадает с тем, как ты сам оцениваешь себя?»

Scale:

```text
1–5
```

Plus open explanation.

---

# 78. Recommendation Trust

Question:

> «Почему ты иногда не выполнял рекомендованный план?»

Qualitative coding categories.

---

# 79. Qualitative Data Is Required

Small Alpha cannot be understood only through dashboards.

Need:

```text
interviews
session observation
support messages
open-ended feedback
```

---

# 80. Closed Alpha Research Design

Recommended:

```text
n = 30–50 students
duration = 4–6 weeks
```

This is product validation, not definitive clinical-style evidence.

---

# 81. Baseline Protocol

At start:

```text
1. profile
2. diagnostic
3. independent baseline mini mock
4. short interview/questionnaire
```

---

# 82. Usage Protocol

Students asked to:

```text
use Today
3+ times/week
20–45 min/session
```

But real usage is recorded.

Do not exclude low-engagement students from retention analysis.

---

# 83. Follow-up Protocol

At end:

```text
independent follow-up mini mock
same blueprint
different holdout questions
final interview
```

---

# 84. Primary Alpha Analysis Set

Two views:

## Intent-to-use style

All activated students.

Useful for product reality.

## Engaged cohort

Students meeting predefined engagement threshold.

Useful for:

> does product show learning signal when actually used?

Both must be reported.

---

# 85. Do Not Cherry-Pick Completers

Bad:

```text
only 12 students who finished everything
improved +6
```

without stating:

```text
18 others dropped out
```

Always report denominator.

---

# 86. Pre/Post Statistical Analysis

For paired baseline/follow-up scores:

report:

```text
n
mean baseline
mean follow-up
mean difference
median difference
95% confidence interval
```

---

# 87. Paired Test

If score differences roughly suitable:

```text
paired t-test
```

If sample small/skewed/outliers:

```text
Wilcoxon signed-rank
```

But p-value is secondary in early product Alpha.

---

# 88. Effect Size

Report:

```text
Cohen's dz
```

for paired designs where appropriate.

Also show raw points.

Educationally:

```text
+4 points
```

is easier to interpret than only:

```text
d = 0.6
```

---

# 89. Bootstrap Confidence Interval

Good default for small cohorts:

```text
bootstrap 95% CI
```

for:

- median gain;
- mean gain;
- retention;
- recovery rate.

---

# 90. No Causal Claim from Simple Pre/Post

If students improve after 4 weeks:

could be:

- product;
- school;
- tutor;
- self-study;
- test familiarity.

So wording:

> «Мы наблюдаем improvement signal.»

Not:

> «ENT Math AI caused +5 points.»

Causal inference requires controlled comparison.

---

# 91. Controlled Experiment — Later

When sample larger:

```text
Adaptive Plan
vs
Structured Non-Adaptive Plan
```

Both get:

- same content quality;
- similar practice volume.

Difference:

adaptive routing.

This tests actual adaptive value.

---

# 92. Ethical Experiment Principle

Do not create intentionally bad education control.

Control should be:

```text
reasonable baseline
```

not:

```text
no help
```

---

# 93. A/B Experiment Eligibility

Only after:

```text
enough active users
stable instrumentation
predefined hypothesis
predefined primary metric
```

---

# 94. Experiment Registry

Every experiment stores:

```text
id
hypothesis
population
variant
primary metric
secondary metrics
guardrails
start/end
analysis plan
decision
```

---

# 95. No Metric Shopping

Before experiment starts:

choose:

```text
PRIMARY METRIC
```

Do not later search 25 metrics and report the one that became significant.

---

# 96. Multiple Comparisons

If many outcomes:

mark:

```text
exploratory
```

or apply correction where appropriate.

---

# 97. Sample Size Planning

Before serious A/B:

estimate:

```text
baseline rate
minimum detectable effect
alpha
power
```

Do not choose `n=50` automatically for every experiment.

---

# 98. Small Sample Rule

If sample too small:

prefer:

```text
descriptive evidence
confidence intervals
qualitative insight
```

over false certainty.

---

# 99. Experiment E1 — Onboarding Choice

Hypothesis:

> Allowing Import as alternative to Diagnostic improves activation for students with prior assessments.

Variants:

```text
A: Diagnostic-first
B: Diagnostic or Import
```

Primary:

```text
Activation Rate
```

Secondary:

```text
Time to Initial Value
D1
Import confirmation
```

---

# 100. Experiment E2 — Today vs Topic List

Hypothesis:

> Default personalized Today increases meaningful session starts and retention.

Variants:

```text
A: Today default
B: Topic selection default
```

Primary:

```text
Meaningful Session Start
```

Secondary:

```text
W1 retention
decision time
```

---

# 101. Experiment E3 — Tutor Nudge

Hypothesis:

> Level-1 nudge before strategy hint improves independent recovery.

Variants:

```text
A: Nudge first
B: Strategy hint first
```

Primary:

```text
Independent Near-Transfer Success
```

Guardrail:

```text
session abandonment
frustration feedback
```

---

# 102. Experiment E4 — Full Solution UX

Hypothesis:

> Explicit warning + recovery item reduces passive copying.

Variants:

```text
A: plain full solution
B: solution + "must verify independently"
```

Primary:

```text
post-solution independent success
```

---

# 103. Experiment E5 — Recommendation Explanation

Hypothesis:

> Showing “why this today” increases recommendation acceptance.

Variants:

```text
A: no reason
B: concise deterministic reason
```

Primary:

```text
Plan Acceptance
```

Secondary:

```text
Plan Completion
Trust rating
```

---

# 104. Experiment E6 — Import Confirmation UX

Hypothesis:

> Field-level confidence highlighting improves correction quality without excessive friction.

Variants:

```text
A: flat review
B: low-confidence fields highlighted
```

Primary:

```text
confirmed field accuracy
```

Secondary:

```text
time to confirm
drop-off
```

---

# 105. Experiment E7 — Spaced Review

Later:

```text
fixed schedule
vs
retrievability-based
```

Primary:

```text
delayed independent recall
```

---

# 106. Experiment E8 — Adaptive Routing

Most important future controlled test.

```text
Adaptive Engine
vs
Weakest-Skill Baseline
```

Primary:

```text
holdout learning gain per study hour
```

This directly tests core IP.

---

# 107. Guardrail Metrics

Every experiment may have guardrails:

```text
critical errors
drop-off
Tutor leakage
student frustration
AI cost
time per session
```

A metric win is not acceptable if guardrail seriously worsens.

---

# 108. Learning Gain per Study Hour

Future strong metric:

```text
GainEfficiency =
Independent Assessment Gain
/
Meaningful Study Hours
```

Useful for product thesis:

> optimize useful learning per unit of student time.

---

# 109. Do Not Use It Too Early

With short/noisy assessments:

gain/hour can be unstable.

Use after instrumentation matures.

---

# 110. Time Tracking

Session time should exclude:

- tab background time;
- long inactivity;
- processing wait.

Use active interaction windows.

---

# 111. Active Study Time

Heuristic:

if no interaction for:

```text
> 5 minutes
```

pause active timer.

Exact threshold configurable.

---

# 112. Score Forecast Evaluation

Once `readiness-v1` active:

For each eligible assessment:

```text
forecast range
actual score
```

Track:

```text
coverage rate:
actual inside predicted range
```

---

# 113. Range Calibration

If system claims:

```text
80% confidence interval
```

then long-run actual coverage should approximate that confidence.

Until calibrated:

UI should avoid statistical confidence labels that imply guarantees.

---

# 114. Forecast Error

Track:

```text
MAE
RMSE
```

on midpoint.

But range coverage matters more.

---

# 115. Forecast Bias

Check:

```text
mean(actual - predicted midpoint)
```

Detect systematic optimism/pessimism.

---

# 116. Language Segmentation

All core product metrics segmented by:

```text
KK
RU
```

Because bilingual quality is core product promise.

---

# 117. Grade Segmentation

Optional:

```text
10
11
```

Useful but secondary.

---

# 118. Starting-Level Segmentation

Important:

```text
low
mid
high
```

based on baseline assessment, not self-label.

Adaptive product may work differently by level.

---

# 119. Do Not Over-Segment Small Alpha

With n=30:

do not draw strong conclusions from:

```text
n=4 subgroup
```

Use exploratory labels.

---

# 120. Instrumentation Contract

Every analytics event has:

```text
eventName
eventVersion
anonymous/student internal id
timestamp
sessionId?
properties
```

---

# 121. Event Versioning

Example:

```text
daily_plan_completed.v1
```

Schema changes require version/change discipline.

---

# 122. No PII in Analytics

Do not send:

- email;
- name;
- uploaded document contents;
- raw answer text

to third-party analytics unless explicitly required and reviewed.

---

# 123. Learning Data ≠ Click Analytics

Source of truth for:

```text
Attempt
Assessment
LearningEvidence
Mastery
```

is product DB.

Analytics platform is not learning truth.

---

# 124. Canonical Event — Attempt

Properties:

```text
questionVersionId
skillCode
difficulty
correctness
assistanceLevel
responseTimeBucket
sessionType
language
```

Avoid raw answer unless internal DB use case requires it.

---

# 125. Canonical Event — Tutor

```text
responseType
hintLevel
language
providerAlias
success
latencyBucket
```

Cost stored server-side.

---

# 126. Canonical Event — Import

```text
documentType
pagesBucket
extractionStatus
reviewCorrectionsCount
sourceType
```

No raw document text.

---

# 127. Dashboard 1 — Product

Show:

```text
signups
activation
DAU/WAU
meaningful sessions
D1/W1
Today adoption
```

---

# 128. Dashboard 2 — Learning

Show:

```text
baseline/follow-up
holdout gain
transfer success
review success
mastery calibration
```

---

# 129. Dashboard 3 — AI

Show:

```text
Tutor calls
failure
latency
leakage rejects
semantic rejects
cost
```

---

# 130. Dashboard 4 — Import

Show:

```text
uploads
successful extraction
review correction
confirmation
processing time
```

---

# 131. Dashboard 5 — Content

Show:

```text
coverage
defects
reports
question exposure
language parity
```

---

# 132. Daily Operational Alerting

Alert on:

```text
API failure spike
Import queue stuck
AI provider failure
Tutor leakage rejection spike
critical content defect
DB errors
```

---

# 133. Weekly Product Review

Every week:

```text
1. Funnel
2. Retention
3. Learning
4. Tutor
5. Import
6. Content
7. Cost
8. Qualitative notes
```

---

# 134. Weekly Review Must End with Decisions

Not:

```text
interesting dashboard
```

But:

```text
what changed?
what do we do?
what hypothesis next?
```

---

# 135. Decision Log

Important product decisions go to:

```text
docs/09_DECISIONS_LOG.md
```

or ADR/experiment registry.

Examples:

```text
Import correction too high → restrict supported report types
Tutor Level 1 ignored → change UX
```

---

# 136. Alpha Interview Protocol

Interview ~20–30 min.

Questions:

```text
Как ты обычно выбираешь, что учить?
Что сделал после последнего плохого пробника?
Когда Today казался полезным?
Когда он ошибался?
Что делал, если не мог решить?
Когда открывал полное решение?
Загрузил бы ты ещё один пробник?
Что заставило бы тебя перестать пользоваться?
```

---

# 137. Avoid Leading Questions

Bad:

> «Тебе понравилась наша умная персонализация?»

Good:

> «Как ты выбирал тему сегодня?»

---

# 138. Observation Sessions

For 5–8 students:

watch first use.

Record:

- where confused;
- what they expect from import;
- whether they understand Skill Map;
- whether Tutor controls make sense.

---

# 139. Trust Interview Signal

Ask:

> «Был момент, когда система показала что-то, чему ты не поверил?»

This may reveal more than generic satisfaction score.

---

# 140. Satisfaction

Can collect:

```text
1–5 usefulness
1–5 trust
```

But not primary success metric.

---

# 141. NPS

Not useful as core Alpha metric.

Maybe later.

---

# 142. Go / No-Go Framework

At end of Closed Alpha, evaluate four pillars:

```text
A. Product Pull
B. Learning Signal
C. Trust / Quality
D. Economic Feasibility
```

---

# 143. Pillar A — Product Pull

GO signal:

```text
meaningful repeat use
students follow Today
some users return without reminders
```

Quantitative provisional:

```text
W1 retention >= 20%
retained users >= 3 sessions/week
```

Strong GO:

```text
W1 >= 30%
```

Again: hypotheses, not universal benchmarks.

---

# 144. Pillar B — Learning Signal

GO:

```text
positive median holdout gain
+
majority of engaged students non-negative
+
transfer/review metrics improving
```

Strong GO:

```text
material raw-point improvement
with CI suggesting signal above noise
```

Do not demand statistical significance from n=30.

---

# 145. Pillar C — Trust / Quality

GO:

```text
no systemic critical content issue
Tutor serious math errors rare
Import correction manageable
users generally trust knowledge map
```

NO-GO / Fix First:

```text
frequent wrong answers
misleading mastery
import unreliable
```

---

# 146. Pillar D — Economics

GO:

```text
AI variable cost controllable
content pipeline sustainable
support load manageable
```

Exact paid unit economics tested later.

---

# 147. Strong Continue Decision

Proceed to broader Beta if:

```text
Product Pull: GO
Learning: GO
Trust: GO
Economics: not obviously broken
```

---

# 148. Iterate, Not Scale

If:

```text
Usage good
Learning weak
```

Do not scale marketing.

Fix:

- content;
- recommendation;
- Tutor;
- assessment.

---

# 149. Learn, Not Monetize

If:

```text
Learning good
Retention weak
```

Fix:

- Today UX;
- session length;
- onboarding;
- habit design.

Do not assume pricing solves it.

---

# 150. Fix Trust First

If:

```text
Retention okay
but wrong answers/import errors high
```

stop expansion.

Education product cannot survive low trust.

---

# 151. Pivot Signal

Reconsider core proposition if after several iterations:

```text
users consistently prefer manual topic selection
AND
adaptive routing shows no learning/time advantage
```

Then adaptive engine may not be the primary value.

---

# 152. Kill Feature Signal — Import

If:

```text
very few users have usable prior results
OR
correction burden remains huge
OR
import doesn't change plan/value
```

reduce scope.

Do not keep it because it looks impressive.

---

# 153. Kill Feature Signal — AI Tutor

If:

```text
curated hints perform equally well
AND
LLM cost/errors are high
```

use AI more selectively.

---

# 154. Feature Graduation

Feature moves:

```text
EXPERIMENTAL
→ BETA
→ CORE
```

only after metrics.

---

# 155. Metrics Review Cadence

## Daily

Reliability / incidents.

## Weekly

Product + content + AI.

## Every 2–4 weeks

Learning outcome / cohort analysis.

## End of cohort

Go / No-Go.

---

# 156. Data Quality Checks

Before analysis:

```text
duplicate events
missing IDs
clock anomalies
bot/test users
broken session tracking
assessment invalidation
```

Bad telemetry can create fake conclusions.

---

# 157. Test Accounts

Mark:

```text
is_test_user
```

Exclude from product metrics.

---

# 158. Staff Accounts

Separate.

---

# 159. Late Events

Analytics ingestion should handle events arriving out of order.

Learning truth uses DB timestamps / domain events.

---

# 160. Timezone

Product cohort reporting:

use:

```text
student local timezone
```

for daily usage.

Backend analytics can also store UTC.

---

# 161. Metric Definition Registry

Every metric documented:

```text
name
formula
population
time window
source tables
owner
version
```

Avoid two dashboards with different "D7".

---

# 162. Example Metric Definition

```yaml
name: daily_mission_completion_rate
version: v1
population: daily plans started
numerator: plans completed
denominator: plans started
window: calendar day in student timezone
source: daily_plans
```

---

# 163. Data Warehouse

Not required Alpha.

PostgreSQL + analytics queries sufficient.

When data grows:

introduce warehouse after measured need.

---

# 164. Product Analytics Tool

Optional third-party tool for click/funnel views.

But learning data stays internal.

---

# 165. Experiment Assignment

When A/B begins:

assignment stored server-side.

Sticky:

```text
student remains in same variant
```

---

# 166. Randomization Unit

Usually:

```text
student
```

not session.

Avoid cross-contamination.

---

# 167. Experiment Exclusions

Predefine:

- staff/test accounts;
- users outside supported curriculum;
- invalid baseline.

Do not exclude after seeing result.

---

# 168. Missing Follow-up

Report attrition.

Do not impute magically in small Alpha.

Show:

```text
completed follow-up / baseline cohort
```

---

# 169. Attrition Analysis

Compare baseline characteristics:

```text
completers
vs
dropouts
```

Could reveal bias.

---

# 170. Engagement Dose Analysis

Explore relationship:

```text
meaningful study time
vs
learning gain
```

Do not automatically infer causality.

---

# 171. Dose Threshold

Potential future:

minimum useful weekly practice.

Can inform product recommendation.

---

# 172. Student Goal Attainment

Track:

```text
target score
vs
latest verified assessment
```

But do not claim guarantee.

---

# 173. Time-to-Goal

Future metric only after calibrated readiness model.

---

# 174. Parent Value Metrics — Later

Not Alpha.

Potential:

```text
weekly report open
parent trust
renewal
```

---

# 175. Teacher/B2B Metrics — Later

Not Alpha.

---

# 176. Paid Beta Metrics — Future

When monetization starts:

```text
Free → Paid Conversion
Trial → Paid
Monthly Retention
Churn
ARPU
Gross Margin
LTV
CAC
```

Not current North Star.

---

# 177. Pricing Experiment Guard

Do not price-test before basic value proven.

A low conversion with weak product tells little.

---

# 178. Privacy Metrics

Track operationally:

```text
account deletion success
raw upload deletion
retention job success
unauthorized access incidents
```

---

# 179. Security Metrics

```text
auth failures
rate-limit hits
upload rejections
malware detections
IDOR test failures
```

Do not expose security dashboard publicly.

---

# 180. Content Language Bias Check

If:

```text
KK learners
show systematically worse item performance
on translated pairs
```

review translations before interpreting as learner ability.

---

# 181. Tutor Language Bias Check

Compare:

```text
recovery success RU
vs
KK
```

with caution for cohort differences.

---

# 182. External/Internal Disagreement Metric

Track:

```text
% reliable external assessments
with large disagreement
```

Could diagnose:

- internal item calibration;
- timing;
- transfer;
- source mismatch.

---

# 183. Disagreement Resolution

After validation mission:

```text
resolved
confirmed internal
confirmed external
still uncertain
```

Useful model-quality metric.

---

# 184. Recommendation Churn

Metric:

```text
how often top priority skill changes
```

Too much:

plan unstable.

Too little:

engine may be unresponsive.

---

# 185. Plan Stability Guard

Track:

```text
plans invalidated mid-day
```

Should be rare except strong new evidence.

---

# 186. Mastery Volatility

Track:

```text
abs change per evidence event
```

Large frequent jumps indicate unstable model.

---

# 187. Confidence Growth

Track typical:

```text
evidence mass
→ confidence
```

Ensure unknown skills don't look certain too quickly.

---

# 188. Unknown Coverage

Metric:

```text
% active supported skills
with low confidence
```

Can guide diagnostic selection.

---

# 189. Curriculum Coverage of Student

```text
supported skills with meaningful evidence
/
supported skills
```

Do not confuse with mastery.

---

# 190. Study Time Accuracy

Compare:

```text
estimated plan time
vs
actual active time
```

Planning should improve.

---

# 191. Plan Overrun

```text
actual > estimated × 1.25
```

Track.

Could indicate:

- question timing wrong;
- difficulty mismatch.

---

# 192. Frustration Proxy

Possible:

```text
3+ consecutive failures
+
session abandonment
```

Track as proxy, not psychological diagnosis.

---

# 193. Frustration Guard Effect

After engine intervention:

```text
continue session?
independent recovery?
```

---

# 194. Support Burden

Metrics:

```text
support conversations / 100 active students
import support cases
content issue cases
account issues
```

---

# 195. Alpha Exit Report

At end produce:

```text
1. Cohort
2. Funnel
3. Retention
4. Usage
5. Learning Outcomes
6. Tutor
7. Import
8. Content
9. Cost
10. Qualitative Findings
11. Incidents
12. Go / No-Go Decisions
13. Next Experiments
```

---

# 196. No Vanity Summary

Do not report only:

```text
2,000 questions solved
10,000 AI messages
```

without:

- students;
- retention;
- independent gain.

---

# 197. Minimum Alpha Evidence Package

Before saying:

> «концепция подтверждена»

need at least:

```text
repeat usage signal
+
independent learning signal
+
acceptable trust
```

---

# 198. Strong Evidence Package

Better:

```text
30–50 students
4–6 weeks
reasonable retention
positive holdout gain
Tutor recovery signal
manageable import correction
no systemic content trust issue
AI cost understood
```

---

# 199. What Success Does NOT Mean Yet

Even strong Alpha does not prove:

- national scale;
- profitability;
- all-subject expansion;
- school B2B;
- causal superiority over tutor;
- exact score prediction.

Those require later experiments.

---

# 200. Current Go / No-Go Table

| Area | GO | ITERATE | STOP/FIX FIRST |
|---|---|---|---|
| Activation | majority reaches first personalized session | significant drop-off but understandable | users rarely reach value |
| Retention | meaningful repeat use visible | weak habit but some loyal users | nearly everyone disappears |
| Learning | positive independent gain signal | mixed/noisy signal | consistent no improvement/regression |
| Tutor | recovery benefit, low error | useful but too costly/verbose | wrong math / passive copying |
| Import | trusted, manageable corrections | valuable only for subset | unreliable / mostly useless |
| Content | low critical defect | thin coverage | trust-breaking errors |
| Cost | understandable/control possible | optimization needed | economically implausible |

---

# 201. Final Metrics Principle

> **Ученик не приходит ради метрик.  
> Метрики нужны нам, чтобы не обмануть себя.**

Главная последовательность:

```text
Did they reach value?
        ↓
Did they come back?
        ↓
Did they actually learn?
        ↓
Can we trust the measurement?
        ↓
Can we deliver this sustainably?
```

---

# 202. Final Outcome Hierarchy

Если выбирать между:

```text
more sessions
```

и:

```text
greater independent learning gain
```

выбираем learning gain.

Если выбирать между:

```text
more Tutor messages
```

и:

```text
less Tutor dependency
```

выбираем independence.

Если выбирать между:

```text
more imported data
```

и:

```text
more trustworthy data
```

выбираем trust.

---

# 203. Next Document

Следующий обязательный документ:

> **`09_ARCHITECTURE.md`**

Он должен объединить уже принятые решения в единую implementable system view:

```text
C4-style architecture
frontend boundaries
backend modules
database ownership
domain events
async jobs
AI adapters
object storage
security boundaries
deployment
observability
failure modes
data flows
module dependency rules
```

После этого:

> **`10_ROADMAP.md`**

превратит scope и architecture в последовательные implementation phases / vertical slices.
