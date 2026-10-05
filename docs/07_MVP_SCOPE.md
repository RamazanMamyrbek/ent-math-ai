# 07_MVP_SCOPE.md
## ENT Math AI — Alpha / MVP Scope

> **Статус:** canonical product scope  
> **Версия:** `MVP-v1`  
> **Дата:** 5 октября 2026  
> **Связанные документы:**  
> `00_CONCEPT.md`  
> `02_CURRICULUM_MAP.md`  
> `03_DOMAIN.md`  
> `04_LEARNING_ENGINE.md`  
> `05_AI_TUTOR.md`  
> `06_CONTENT_STRATEGY.md`  
> `ENT_Math_AI_Technical_Specification_v1.md`
>
> **Назначение:** превратить полную концепцию ENT Math AI в конкретную первую версию, которую реально можно построить, протестировать на учениках и использовать для проверки продуктовых гипотез.

---

# 1. Главный принцип MVP

MVP ENT Math AI не должен доказывать:

> «Мы можем построить большую образовательную платформу».

Он должен доказать:

> **«Если система понимает слабые навыки ученика и сама выбирает ему следующие действия, ученик возвращается и его измеримый результат улучшается».**

Поэтому MVP строится вокруг одного loop:

```text
Определить состояние знаний
        ↓
Выбрать следующий полезный skill
        ↓
Дать качественную задачу
        ↓
Проверить ответ
        ↓
Помочь без преждевременного решения
        ↓
Обновить Learning Evidence
        ↓
Перестроить Knowledge State
        ↓
Сформировать следующий Daily Mission
        ↺
```

Всё, что не усиливает или не проверяет этот loop, является кандидатом на исключение.

---

# 2. Название релиза

Рабочее имя:

```text
ENT Math AI — Closed Alpha
```

Продуктовый бренд пока не зафиксирован.

В коде и документации допускается временный internal slug:

```text
ent-math-ai
```

Не тратить разработку на финальный naming до рабочей Alpha.

---

# 3. Что именно мы проверяем

MVP должен проверить семь гипотез.

## H1 — Diagnostic Value

После короткой диагностики ученик воспринимает результат как достаточно полезный и правдоподобный.

## H2 — Daily Plan Value

Ученику проще нажать:

```text
Начать занятие
```

чем самостоятельно выбирать тему.

## H3 — Adaptive Value

Система способна давать разные маршруты ученикам с разными пробелами.

## H4 — Tutor Value

Дозированные AI-hints помогают выйти из тупика, не превращая продукт в решебник.

## H5 — External Evidence Value

Загрузка прошлого пробника уменьшает cold start и делает профиль знаний полезнее.

## H6 — Retention

Ученики возвращаются к Daily Mission несколько раз в неделю.

## H7 — Learning Outcome

У регулярных пользователей улучшается независимый mixed/mock результат.

---

# 4. Primary User

Первый пользователь:

```text
Ученик 10–11 класса
Казахстан
Профильная математика ЕНТ
RU или KK
Средний / выше базового уровень
Готов заниматься 20–45 минут в день
```

Не оптимизируем первую версию специально под:

- олимпиадников;
- учеников с нулевой математической базой;
- учителей;
- школы;
- родителей как основной UI;
- другие предметы.

---

# 5. Primary User Job

> «Я хочу повысить свой результат по профильной математике ЕНТ, но не хочу сам разбираться, какие темы мне сейчас важнее всего учить.»

---

# 6. MVP Promise

Публичное обещание первой версии:

> **Пройди диагностику или загрузи свой последний пробник. Мы построим карту слабых навыков и каждый день будем давать тебе персональную практику по математике ЕНТ.**

Не обещаем:

```text
+10 баллов гарантированно
```

Не обещаем:

```text
точный результат ЕНТ
```

Не обещаем:

```text
100% покрытие всей математики
```

до фактического покрытия.

---

# 7. MVP North-Star User Flow

```text
Landing
  ↓
Register
  ↓
Onboarding
  ↓
┌──────────────────────────────┐
│ Как определить твой уровень? │
│                              │
│ [Пройти диагностику]         │
│ [Загрузить пробник]          │
└──────────────┬───────────────┘
               ↓
       Initial Knowledge State
               ↓
         Result / Weaknesses
               ↓
            Today
               ↓
        Daily Mission
               ↓
        Practice Session
               ↓
      Hint / Explanation
               ↓
       Session Summary
               ↓
          Progress
               ↓
         Return Tomorrow
```

---

# 8. MVP Modes

В Alpha есть только четыре основных пользовательских режима:

```text
1. Diagnostic
2. Daily Mission
3. Free Topic Practice
4. Assessment Import
```

Дополнительно ближе к Closed Beta:

```text
5. Internal Mini Mock
```

Полноценный официальный-size mock может появиться в конце Alpha, но не блокирует первый тест продукта.

---

# 9. Exact MVP Modules

Обязательные модули:

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

Не добавляем:

```text
billing
school
classroom
parent
social
notification platform
```

до проверки core loop.

---

# 10. MVP Screen Map

Пользовательские экраны:

```text
01 Landing
02 Register
03 Login
04 Onboarding
05 Choose Starting Method
06 Diagnostic
07 Diagnostic Result
08 Import Upload
09 Import Processing
10 Import Review
11 Imported Assessment Result
12 Today
13 Practice
14 Session Summary
15 Progress Overview
16 Skill Map
17 Assessment History
18 Settings
```

Internal:

```text
A1 Admin Questions
A2 Admin Question Editor
A3 Admin Content Review
A4 Admin Skills
A5 Admin Import Support
A6 Admin Content Issues
```

---

# 11. Landing

Главная задача landing:

```text
Diagnostic Start
```

Primary headline:

> Узнай, где ты теряешь баллы по математике ЕНТ.

Secondary:

> Пройди диагностику или загрузи последний пробник — система построит персональный план подготовки.

CTA:

```text
[ Определить мой уровень ]
```

Не нужен огромный marketing website.

---

# 12. Registration

Alpha:

```text
email
password
```

Опционально:

```text
Google sign-in
```

но OAuth не должен задерживать release.

Не собирать:

- ИИН;
- адрес;
- школу;
- телефон;
- данные родителей

без реальной необходимости.

---

# 13. Onboarding

Обязательные поля:

```text
preferredLanguage: KK / RU
grade: 10 / 11 / other
targetScore: optional
examDate or approximate period
dailyMinutes: 15 / 30 / 45 / 60
```

Default timezone:

```text
Asia/Almaty
```

но профиль должен поддерживать изменение.

---

# 14. Starting Method

После onboarding:

```text
Как определить твой текущий уровень?

[ Пройти диагностику ]
15–25 минут

[ Загрузить последний пробник ]
Фото, screenshot или PDF
```

Можно пропустить и начать Free Practice только как запасной путь.

Но без initial evidence:

Today должен предложить diagnostic.

---

# 15. Diagnostic Scope

Alpha Diagnostic:

```text
10–12 anchor questions
+
4–8 adaptive questions
```

Maximum:

```text
20 items
```

Target:

```text
15–25 минут
```

---

# 16. Diagnostic Does NOT

Не пытается:

- проверить все 127 skills;
- дать официальный score prediction;
- быть копией полного ЕНТ;
- использовать LLM для определения правильности.

---

# 17. Diagnostic Output

Минимум:

```text
Сильные навыки
Приоритетные навыки
Навыки с недостатком данных
Initial Learning State
```

Пример:

```text
Сильные:
✓ квадратные уравнения
✓ степени

Приоритет:
• рациональные неравенства
• тригонометрические уравнения
• подобие треугольников

Нужно уточнить:
• логарифмические неравенства
```

---

# 18. No Fake Score after Diagnostic

Если Diagnostic недостаточно калиброван:

не показывать:

```text
«Ты сейчас получишь 27/40»
```

Показывать:

```text
Readiness: данные собираются
```

или:

```text
Текущий профиль знаний
```

Score range появляется после достаточного mock evidence.

---

# 19. Closed Alpha Curriculum Scope

Полная Curriculum Map содержит больше 100 granular skills.

Closed Alpha намеренно поддерживает **48 skills**.

Цель:

- широкий enough learning loop;
- сильные prerequisite chains;
- алгебра;
- тригонометрия;
- функции;
- базовая геометрия;
- возможность строить реальные персональные маршруты.

---

# 20. Alpha Skill Set — Foundations

```text
NUM-EXPR-01
NUM-EXPR-02
NUM-RAD-01
NUM-RAD-02
NUM-DOM-01

NUM-POW-01
NUM-POW-02
NUM-POW-03

ALG-OPS-01
ALG-SF-01
ALG-POLY-01
ALG-RAT-01
ALG-RAT-02
```

Count:

```text
13
```

---

# 21. Alpha Skill Set — Equations / Systems

```text
EQ-LIN-01
EQ-QUAD-01
EQ-QUAD-02
EQ-QUAD-03
EQ-RAT-01
EQ-RAT-02

SYS-LIN-01
```

Count:

```text
7
```

Cumulative:

```text
20
```

---

# 22. Alpha Skill Set — Trigonometry

```text
TRIG-ANG-01
TRIG-VAL-01
TRIG-PROP-01
TRIG-ID-01
TRIG-ID-02

EQ-TRIG-01
EQ-TRIG-02
```

Count:

```text
7
```

Cumulative:

```text
27
```

---

# 23. Alpha Skill Set — Exponentials / Logarithms

```text
EQ-EXP-01
EQ-EXP-02

EQ-LOG-01
EQ-LOG-02
EQ-LOG-03
```

Count:

```text
5
```

Cumulative:

```text
32
```

---

# 24. Alpha Skill Set — Inequalities

```text
INEQ-LIN-01
INEQ-QUAD-01
INEQ-RAT-01
INEQ-EXP-01
INEQ-LOG-01
```

Count:

```text
5
```

Cumulative:

```text
37
```

---

# 25. Alpha Skill Set — Functions / Calculus

```text
FUN-BASE-01
FUN-GRAPH-01
FUN-MONO-01

CALC-DER-01
CALC-DER-02
CALC-DER-04
```

Count:

```text
6
```

Cumulative:

```text
43
```

---

# 26. Alpha Skill Set — Geometry Core

```text
PLN-ANG-01
PLN-TRI-01
PLN-TRI-03
PLN-MET-01
PLN-MET-03
```

Count:

```text
5
```

Final Closed Alpha:

```text
48 skills
```

---

# 27. Why These 48

Они дают несколько длинных prerequisite chains.

Пример:

```text
ALG
 ↓
QUADRATIC
 ↓
RATIONAL / TRIG / FUNCTIONS
```

и:

```text
TRIG BASICS
 ↓
TRIG IDENTITIES
 ↓
TRIG EQUATIONS
```

и:

```text
TRIANGLE
 ↓
SIMILARITY
 ↓
PYTHAGORAS / SIN-COS THEOREMS
```

Это позволяет проверить саму adaptive hypothesis.

---

# 28. Skills Deliberately Deferred

Не входят в first Closed Alpha:

```text
сложные системы
тригонометрические неравенства
иррациональные системы
прогрессии
интеграл
полный modeling block
расширенная окружность
векторы
преобразования плоскости
стереометрия
3D-векторы
```

Это не означает низкую важность на ЕНТ.

Это production sequencing.

---

# 29. Content Target per Active Skill

Closed Alpha target:

```text
10–12 canonical approved questions / skill
```

Для 48 skills:

```text
480–576 canonical questions
```

Каждый canonical question должен иметь:

```text
RU
KK
```

для публичного bilingual support.

---

# 30. Minimum Release Coverage

Private Alpha может стартовать раньше:

```text
24–30 skills
~250–320 canonical questions
```

Но Closed Alpha, на которой оценивается продуктовая гипотеза:

```text
48 skills
~500 questions
```

---

# 31. Content Difficulty Target

По каждому mature Alpha skill:

желательно:

```text
A: 4–5
B: 4–5
C: 1–2
```

Не искусственно создавать C там, где skill естественно foundational.

---

# 32. Holdout

Из банка:

```text
~10–15%
```

по возможности reserve для:

- diagnostic;
- independent validation;
- mini mocks.

---

# 33. Practice Mode

Экран показывает:

```text
Question
Progress
Answer controls

[ Подсказка ]
[ Объяснить ]
```

После policy:

```text
[ Полное решение ]
```

---

# 34. Practice Must Support

Alpha formats:

```text
SINGLE_CHOICE
MULTI_SELECT
MATCHING
INTEGER
DECIMAL
```

`CONTEXT_SINGLE_CHOICE` можно добавить после base engine, но желательно до full mock.

---

# 35. Practice Does NOT Need

Alpha:

- drawing canvas;
- handwritten math recognition;
- equation editor уровня CAS;
- voice input;
- live teacher.

---

# 36. Attempt Signals

Record:

```text
answer
correctness
response time
hint level
full solution viewed
question version
timestamp
```

Optional:

```text
self confidence
```

можно отложить.

---

# 37. Daily Mission

Main home screen после activation:

```text
Сегодня
~30 минут

1. Weak Skill Practice
2. Weak Skill Practice
3. Review
4. Mixed Check
```

CTA:

```text
[ Начать ]
```

---

# 38. Daily Mission v1 Composition

При обычном horizon:

```text
~55% weak / priority
~25% review
~20% mixed validation
```

Используется policy из `04_LEARNING_ENGINE.md`.

---

# 39. Daily Mission Constraints

Must:

- respect dailyMinutes;
- include explanation reason;
- avoid excessive one-skill repetition;
- react to prerequisite gap;
- include due review.

---

# 40. Daily Mission Reason

Пример:

> Сегодня начинаем с квадратных уравнений, потому что они мешают тебе стабильно решать более сложные тригонометрические задачи.

Reason создаётся deterministic policy.

LLM не придумывает причину.

---

# 41. Free Topic Practice

Пользователь может вручную открыть поддерживаемую тему.

Это важно для sense of control.

Но default:

```text
Today
```

---

# 42. Free Practice Impact

Attempts создают LearningEvidence как обычная practice.

Но question selection всё равно контролирует engine.

---

# 43. AI Tutor Scope

В Closed Alpha:

```text
Hint 1
Hint 2
Guided Step
Concept Explanation
Clarification
Full Solution
Basic Error Feedback
```

---

# 44. Tutor Non-Goals

Нет:

- voice tutor;
- unrestricted chatbot;
- internet browsing;
- teacher avatar;
- long-term companion;
- generic homework assistant;
- Tutor in Mock mode.

---

# 45. Tutor Provider

Initial adapter:

```text
Google GenAI / Gemini
```

Architecture remains provider-independent.

---

# 46. Tutor Required Safety

Before release:

- structured output;
- schema validation;
- semantic validation;
- final-answer leakage check;
- RU/KK evals;
- prompt injection tests;
- verified solution anchor.

---

# 47. Tutor Content Requirement

Tutor enabled only for QuestionVersion with:

```text
verified AnswerKey
verified Solution
```

Prefer:

```text
curated Hint 1/2
```

for P0 items.

---

# 48. External Assessment Import — MVP Goal

Primary goal:

> **позволить ученику использовать уже существующий пробник как evidence вместо обязательного повторного тестирования внутри продукта.**

---

# 49. Import Type A — Result Screenshot

MVP supports:

```text
JPG
PNG
PDF
```

Example:

```text
screenshot result page
official/third-party report
```

Extract:

```text
assessment date
source if identifiable
subject
total score
max score
topic results if visible
```

---

# 50. Import Type B — Multi-page Result Report

Supports:

```text
PDF
multiple images
```

Extract:

- total;
- topic breakdown;
- item breakdown if clearly present.

User reviews extracted data.

---

# 51. Import Type C — Paper Mock + Answer Key

Closed Alpha experimental capability.

User uploads:

```text
paper test photos/PDF
+
answer sheet / marked responses
+
answer key
```

System may extract:

- selected answers;
- key;
- score;
- readable item text.

But:

```text
user review is mandatory
```

---

# 52. Paper Mock Skill Mapping

If item text is readable and mapping confidence high:

```text
external item
→ skill evidence
```

If not:

```text
score only
or
topic only
```

Never invent granular mastery.

---

# 53. Handwritten Full-Solution Analysis — OUT

MVP does NOT attempt reliable reconstruction of:

```text
student handwritten reasoning steps
```

It may read marked answer.

But not promise:

> «AI найдёт первую ошибку в твоей рукописной тетради»

in MVP.

---

# 54. External Import Legal Boundary

Imported third-party item:

```text
private student assessment
```

does not become public Question Bank automatically.

No credential scraping.

No NTC password collection.

---

# 55. Import User Review

Before evidence application:

```text
Мы распознали:

Дата
Источник
Результат
Темы / ответы
```

User can:

```text
Confirm
Correct
Exclude
```

---

# 56. Import Processing UX

Asynchronous.

States:

```text
Uploading
Processing
Needs Review
Ready
Failed
```

User does not stare at blocking request.

---

# 57. Import Acceptance Criteria

Flow must work:

```text
upload
→ extract
→ review
→ correct one value
→ confirm
→ assessment appears in history
→ evidence created
→ relevant learning state recalculated
```

---

# 58. Assessment History

Screen:

```text
Date
Source
Type
Score
Confidence/verification label
```

Examples:

```text
03 Oct  Official/External  28/40
26 Sep  Paper Mock         24/40
18 Sep  ENT Math AI        —
```

---

# 59. Score Range Scope

Closed Alpha:

score prediction is **not required for activation**.

It appears only if:

```text
>= 2 eligible mixed/full assessments
```

according to Learning Engine policy.

Otherwise:

```text
Недостаточно данных для прогноза
```

---

# 60. Progress Overview

Must show:

```text
Goal
Study consistency
Priority skills
Strong skills
Assessment history
Recent progress
```

---

# 61. Skill Map

Show only supported/meaningful skills.

Example:

```text
Quadratic equations
Strong

Trig equations
Needs work

Log equations
Developing

Triangle similarity
Not enough data
```

---

# 62. Do Not Show Fake 127-Skill Dashboard

If content supports only 48:

UI should not imply complete coverage.

Unsupported skills:

- hidden;
- or marked clearly as not yet supported.

---

# 63. Session Summary

After Daily Mission:

```text
Solved
Correct
Independent correct
Hints used
Skills practiced
Review scheduled
```

Do not over-focus on percentage accuracy.

---

# 64. Error Notebook MVP

Basic version only.

Show repeated validated signals:

```text
SIGN_ERROR
FORMULA_CONFUSION
CALCULATION_ERROR
```

No elaborate AI psychological interpretation.

---

# 65. Internal Mini Mock

Closed Alpha optional but strongly recommended.

Example:

```text
15–20 items
mixed supported skills
timed
no Tutor
```

Purpose:

- calibration;
- transfer;
- user progress.

---

# 66. Full 40-question Mock

Can enter late Alpha if content pool supports it.

Not release blocker for first learner cohort.

---

# 67. Admin Scope — Questions

Admin must:

```text
create
edit draft
preview
review
approve
suspend
retire
```

---

# 68. Admin Scope — Content Metadata

Must edit:

```text
skill
difficulty
format
answer
solution
RU
KK
source
```

---

# 69. Admin Scope — Import Support

Support user import issues:

- extraction failed;
- wrong mapping;
- duplicate;
- user report.

Admin cannot silently alter learning evidence without audit.

---

# 70. Admin Scope — Analytics

Alpha needs basic internal views:

```text
active students
diagnostic completion
daily mission completion
imports
Tutor calls
AI cost
content issues
```

No huge BI platform.

---

# 71. Notifications

Not core Alpha.

Optional:

```text
email reminder
```

later.

Do not build push infrastructure before retention loop proven.

---

# 72. Payments

OUT of Private Alpha.

For Paid Beta:

simple subscription can be added after:

```text
retention + learning signal
```

are visible.

---

# 73. Parent Dashboard

OUT of MVP.

Future high-value feature.

Do not let it expand initial domain/UI.

---

# 74. Teacher Dashboard

OUT.

---

# 75. School / B2B

OUT.

---

# 76. Other Subjects

OUT.

Architecture generic enough, but product only:

```text
profile mathematics
```

---

# 77. Native Mobile Apps

OUT.

MVP:

```text
responsive web app / PWA-friendly
```

---

# 78. Voice

OUT.

---

# 79. Social Features

OUT:

- friends;
- leaderboard;
- chat;
- study groups;
- public profiles.

---

# 80. Advanced Gamification

OUT.

Allowed basic:

```text
study streak
weekly consistency
milestones
```

No XP economy.

---

# 81. Video Lessons

OUT.

Tutor + concise concept notes are enough for Alpha.

---

# 82. Video Marketplace / Tutor Marketplace

OUT.

---

# 83. Full CMS

OUT.

Simple internal content editor only.

---

# 84. Deep Knowledge Tracing

OUT.

Use `mastery-v1`.

---

# 85. IRT Production Model

OUT.

Collect data for future calibration.

---

# 86. Vector DB

OUT.

---

# 87. Redis

OUT unless a measured need appears.

---

# 88. Kafka / RabbitMQ

OUT.

PostgreSQL job queue.

---

# 89. Kubernetes

OUT.

---

# 90. Microservices

OUT.

Modular monolith.

---

# 91. MVP Technical Shape

```text
Browser
  ↓
Next.js
  ↓
Spring Boot Modular Monolith
  ├── PostgreSQL
  ├── Object Storage
  └── Gemini Provider
```

Optional separate worker process from same backend artifact.

---

# 92. Authentication

Required:

```text
email/password
HttpOnly secure session
```

Google auth optional.

---

# 93. Supported Devices

Primary:

```text
mobile browser
desktop browser
```

Minimum tested widths:

```text
360px
768px
1280px
```

---

# 94. Bilingual Release Requirement

Supported scope must work in:

```text
RU
KK
```

This is not a later translation feature.

---

# 95. Bilingual Definition of Done

For active public question:

- RU approved;
- KK approved.

For UI:

- no missing keys in core user journeys.

Tutor:

- quality eval in both languages.

---

# 96. Performance MVP Requirements

Normal API:

```text
p95 < 300 ms
```

excluding AI.

Tutor target:

```text
p95 < ~8 sec
```

Import:

async, first usable result target:

```text
< ~60 sec
```

for small document, not hard SLA.

---

# 97. Reliability

MVP should survive:

```text
Gemini unavailable
```

Core still works:

- login;
- practice;
- answer validation;
- learning state;
- curated hints/solutions where present.

Import/Tutor may degrade.

---

# 98. Security Minimum

Before external beta:

- secure session;
- CSRF;
- IDOR tests;
- file type validation;
- private object storage;
- upload size limits;
- malware scan strategy;
- AI key server-side;
- rate limits;
- no external credentials;
- PII minimization.

---

# 99. Privacy Minimum

User can:

```text
delete uploaded source file
exclude assessment
delete account
```

Raw import retention policy documented.

---

# 100. Analytics Events

Required:

```text
signup_completed
onboarding_completed

diagnostic_started
diagnostic_completed

daily_plan_viewed
daily_plan_started
daily_plan_completed

attempt_submitted
hint_requested
full_solution_opened

import_started
import_extracted
import_confirmed
import_corrected

mini_mock_completed
progress_viewed
```

---

# 101. Activation Definition

User is activated if within first session/day:

```text
initial knowledge state created
AND
first personalized practice completed
```

Initial state can come from:

```text
Diagnostic
or
Assessment Import + targeted follow-up
```

---

# 102. Alpha Success Metrics

Primary:

```text
Diagnostic/Import Completion
First Personalized Session Completion
D1 Retention
D7 Retention
Sessions per Active Week
Daily Mission Completion
Independent Transfer Success
Mini-Mock Improvement
```

---

# 103. Suggested Early Thresholds

Not final PMF criteria, but useful targets:

```text
Initial-state completion        >= 60%
First personalized session      >= 70% of activated
D1 retention                    >= 40%
D7 retention                    >= 20%
Weekly sessions among retained  >= 3
```

These are internal hypotheses, not industry facts.

---

# 104. Learning Validation Metric

For students with enough usage:

```text
Baseline mixed assessment
        vs
Follow-up mixed assessment
```

Primary learning measure:

```text
score delta
```

Secondary:

```text
independent accuracy
difficulty progression
error recurrence
```

---

# 105. Tutor Success Metric

Better than number of AI chats:

```text
% of Tutor-assisted episodes
followed by independent near-transfer success
```

---

# 106. Import Success Metric

```text
% imports successfully confirmed
median correction count
time to review
% imports that change learning plan
```

---

# 107. Import Quality Metric

Critical:

```text
user correction rate
```

by extracted field.

If:

```text
30% of scores need correction
```

the feature is not trustworthy.

---

# 108. Content Quality Metric

```text
critical defects per 10,000 exposures
```

Plus:

```text
student issue rate
```

---

# 109. AI Cost Metric

Required:

```text
AI cost / weekly active student
```

Separate:

```text
Tutor
Import
Content authoring
```

---

# 110. Private Alpha Cohort

First cohort:

```text
10–20 students
```

Goal:

- catch product/algorithm/content bugs.

Do not market broadly.

---

# 111. Closed Alpha Cohort

Then:

```text
30–50 students
```

Duration:

```text
4–6 weeks
```

Enough to inspect:

- usage;
- retention;
- learning signals;
- import quality.

---

# 112. Student Selection

Prefer students who:

- actually plan to take math;
- have at least 4 weeks;
- can use system 3+ times/week;
- vary in starting level.

Avoid sample entirely from close friends/highly motivated developers.

---

# 113. Baseline Measurement

Before cohort:

```text
Diagnostic
+
Mixed Mini Mock
```

where possible.

---

# 114. Follow-up Measurement

Week 4/6:

same blueprint, different holdout items.

Avoid exact repeated test.

---

# 115. User Interviews

At minimum:

```text
5–10 interviews
```

during cohort.

Ask:

- Did Today save decision effort?
- Was skill diagnosis believable?
- Did hints help?
- When did you open Full Solution?
- Was import useful?
- What made you return/not return?

---

# 116. MVP Release Gates — G0

## Engineering Harness

Must pass:

```text
repo
CI
backend health
frontend
DB migrations
Docker dev environment
observability basics
```

---

# 117. MVP Release Gates — G1

## Identity / Onboarding

Must pass E2E:

```text
register
login
set language
set goal
session persists
logout
```

---

# 118. MVP Release Gates — G2

## Curriculum / Content

Must have:

```text
selected Alpha skills
question versions
deterministic validators
RU/KK
verified solution
```

---

# 119. MVP Release Gates — G3

## Practice / Learning

Must prove:

```text
attempt
→ evidence
→ mastery
→ confidence
```

with deterministic replay.

---

# 120. MVP Release Gates — G4

## Diagnostic

Must produce different profiles for synthetic students:

```text
strong algebra / weak trig
weak algebra / strong geometry
unknown student
```

---

# 121. MVP Release Gates — G5

## Daily Plan

Must react to:

```text
weak skill
prerequisite gap
review due
low confidence
```

---

# 122. MVP Release Gates — G6

## AI Tutor

Must pass:

```text
RU eval
KK eval
answer leakage
prompt injection
math consistency
fallback
```

---

# 123. MVP Release Gates — G7

## External Import

Required E2E:

```text
upload screenshot/PDF
→ extract
→ review
→ correct
→ confirm
→ assessment
→ evidence / readiness
→ plan update
```

---

# 124. MVP Release Gates — G8

## Progress

Must display:

```text
supported skill state
assessment history
recent session
goal
```

without fake precision.

---

# 125. MVP Release Gates — G9

## Closed Alpha Ready

Requirements:

```text
no known critical security defects
no known critical AnswerKey defects
core P0 content coverage usable
AI telemetry present
content issue reporting works
backup works
privacy/delete flow defined
```

---

# 126. Acceptance Scenario A — New Student

```text
Given:
new student

When:
registers
sets RU
target 35
30 minutes/day
completes diagnostic

Then:
knowledge state exists
priority skills displayed
Today plan created
first practice can start
```

---

# 127. Acceptance Scenario B — Different Student

```text
Given:
Student B performs differently

Then:
Today plan differs materially
```

This proves personalization isn't cosmetic.

---

# 128. Acceptance Scenario C — Hint

```text
Given:
student answers incorrectly

When:
requests Hint 1

Then:
Tutor gives a nudge
does not reveal final answer
Mastery does not change because Tutor spoke
```

---

# 129. Acceptance Scenario D — Full Solution

```text
When:
student views Full Solution
and submits correct answer

Then:
same item does not create independent success evidence

And:
system later offers near-transfer check
```

---

# 130. Acceptance Scenario E — Prerequisite

```text
Given:
EQ-TRIG-03 weak
EQ-QUAD-03 very weak

Then:
plan prioritizes prerequisite check/remediation
before repeated advanced trig drill
```

---

# 131. Acceptance Scenario F — Review

```text
Given:
skill mastery strong
but review overdue

Then:
skill appears in review
without being labeled as weak
```

---

# 132. Acceptance Scenario G — Imported Result

```text
Given:
student uploads result screenshot

When:
AI extracts 28/40
student corrects date
confirms

Then:
Assessment created
provenance stored
history updated
```

---

# 133. Acceptance Scenario H — External Topic Breakdown

```text
Given:
import says
Trigonometry = 4/7

Then:
topic aggregate evidence created

And:
system does NOT assign 4/7 mastery
to every trig granular skill
```

---

# 134. Acceptance Scenario I — Total Score Only

```text
Given:
only 29/40 is known

Then:
readiness evidence updated

But:
granular skills remain unchanged
```

---

# 135. Acceptance Scenario J — Duplicate Import

```text
Given:
same file imported twice

Then:
user sees duplicate warning
and learning evidence is not doubled
```

---

# 136. Acceptance Scenario K — Gemini Down

```text
Given:
AI provider unavailable

Then:
practice answer checking works
Daily Plan works
mastery works

Tutor/import show graceful degradation
```

---

# 137. Acceptance Scenario L — Question Defect

```text
Given:
approved AnswerKey later found wrong

When:
question suspended
affected evidence invalidated

Then:
student mastery can be rebuilt
```

---

# 138. Acceptance Scenario M — KK

```text
Given:
KK user

Then:
onboarding
question
Tutor
progress
import review

all work without falling back to RU unintentionally
```

---

# 139. Acceptance Scenario N — Unsupported Skill

```text
Given:
student asks to practice a skill outside Alpha coverage

Then:
system does not generate unreviewed random questions

And:
clearly states that this section is not yet supported
```

---

# 140. Definition of MVP Done

MVP is NOT done when:

```text
all Jira cards closed
```

It is technically done when:

```text
core flows work end-to-end
tests green
security baseline complete
content coverage sufficient
Tutor controlled
import controlled
learning state replayable
```

It is product-validated only when:

```text
real students use it repeatedly
+
independent learning signal improves
```

---

# 141. What We Do After Alpha Only If Evidence Supports It

Potential:

```text
Parent Mode
Full 40-question mocks
Math Literacy
Stereometry expansion
Native app
Payments
Teacher dashboard
More sophisticated KT
Official integration
```

Not automatic roadmap commitments.

---

# 142. MVP Development Priorities

Priority order:

```text
P0 — correctness / learning truth
P1 — core user loop
P2 — Tutor / import wow features
P3 — polish
P4 — growth features
```

Never prioritize animation above answer correctness.

---

# 143. UI Priority

First UI quality:

```text
clear
fast
mobile-friendly
trustworthy
```

Not:

```text
flashy AI gradients everywhere
```

---

# 144. Product Personality

Should feel:

```text
calm
precise
modern
focused
smart
```

Not:

```text
childish
casino-like
bureaucratic
```

---

# 145. Main Navigation

Recommended Alpha:

```text
Today
Practice
Progress
Assessments
```

Settings under profile.

No 12-menu sidebar.

---

# 146. Today Is Default Route

After login:

```text
/today
```

unless onboarding incomplete.

---

# 147. Empty State — No Learning State

Today:

> Чтобы построить первый план, пройди диагностику или загрузи последний пробник.

CTA pair.

---

# 148. Empty State — No Assessment

Progress still works from practice.

Score forecast:

> Недостаточно пробных результатов.

---

# 149. Error State — Import

If extraction fails:

> Мы не смогли надёжно распознать этот файл. Попробуй более чёткое фото или введи результат вручную.

Manual fallback useful.

---

# 150. Manual Assessment Entry

MVP supports fallback:

```text
source
date
score/max
optional topic results
```

Reliability lower than verified document.

---

# 151. Why Manual Entry Exists

AI import can fail.

User still needs unified history.

But source reliability policy protects Mastery.

---

# 152. Manual Topic Entry

If user manually enters:

```text
Trig 4/7
```

store as self-reported topic evidence.

Low reliability.

---

# 153. Mock Scheduling

Not automated calendar in Alpha.

Today may recommend:

```text
Mini Mock
```

based on policy.

---

# 154. Streak

Optional simple:

```text
days with completed meaningful session
```

No punishment if broken.

---

# 155. Weekly Goal

Can show:

```text
3 / 5 sessions
```

based on preference.

---

# 156. No Notification Dependency

User can use product without allowing notifications.

---

# 157. Browser Support

Current evergreen:

```text
Chrome
Edge
Safari mobile
```

Firefox best effort.

---

# 158. Accessibility Minimum

Core flow keyboard-accessible.

Correct/incorrect not indicated by color only.

Form labels.

Focus states.

---

# 159. MVP Data Retention

Raw imports:

configurable temporary retention.

Normalized assessments/evidence:

remain until deletion/exclusion according to privacy policy.

---

# 160. Audit

Required admin audit for:

```text
question approval
answer correction
assessment override
evidence invalidation
algorithm policy activation
```

---

# 161. Operational Dashboard

At least:

```text
API errors
DB health
Import queue
AI latency
AI cost
Tutor failures
Content critical issues
```

---

# 162. Support Tool

Admin can look up user by safe identifier/email and see:

- import status;
- assessment status;
- content issue.

No ability to see password or secret.

---

# 163. Beta Support

For first 50 students:

manual support is acceptable.

Do not build Zendesk-like system.

---

# 164. What We Will Learn from MVP

After 4–6 weeks we should answer:

```text
1. Do students complete diagnostic/import?
2. Do they trust the knowledge map?
3. Do they follow Today?
4. Does Tutor reduce stuck moments?
5. Does external import matter?
6. Do they return?
7. Does independent performance improve?
8. Which skills/content are bottlenecks?
9. How expensive is AI per student?
10. Is the product worth paying for?
```

---

# 165. Kill / Pivot Signals

Need rethink if:

```text
users prefer generic ChatGPT for help
AND
Daily Plan creates no retention advantage
```

or:

```text
learning outcome shows no advantage
despite regular usage
```

or:

```text
quality content production cost is economically impossible
```

---

# 166. Strong Positive Signals

Especially valuable:

```text
students return without reminders
students follow Today rather than browsing
students upload external assessments voluntarily
students mention "it knows what I am weak at"
independent mock improves
parents/students ask to continue after beta
```

---

# 167. Paid Beta Gate

Do NOT monetize just because app works.

Paid Beta after:

```text
core retention signal
+
learning outcome signal
+
stable content quality
```

Then test:

```text
willingness to pay
```

---

# 168. Proposed Paid Beta Product

Potential later:

```text
Free:
initial diagnostic
limited practice
limited Tutor

Pro:
full Daily Plan
more practice
Tutor
imports
assessment history
advanced progress
```

Not implemented until validated.

---

# 169. Roadmap Boundary

`07_MVP_SCOPE.md` defines **what**.

`10_ROADMAP.md` will define:

```text
in what order
and with which milestones
```

No future feature may enter MVP because it sounds interesting unless this document is explicitly revised.

---

# 170. Change Control

To add a new MVP feature:

required:

```text
Problem
Why needed for hypothesis
Cost
What gets removed/delayed
Acceptance criteria
```

No free scope expansion.

---

# 171. Current MVP Formula

```text
ENT Math AI Closed Alpha
=
48 supported skills
+
~500 verified bilingual questions
+
Diagnostic
+
Assessment Import
+
Learning Evidence
+
Mastery / Confidence / Review State
+
Adaptive Daily Mission
+
Deterministic Practice
+
Controlled AI Tutor
+
Progress / Assessment History
+
Basic Mini Mock
```

Everything else is secondary.

---

# 172. The Smallest Product That Still Proves the Idea

If schedule/production pressure forces further reduction, preserve:

```text
24–30 skills
~250 questions
Diagnostic
Practice
Learning Engine
Daily Plan
Hint 1/2
Progress
Result screenshot import
```

Cut first:

```text
full paper mock parsing
full solution AI
advanced error notebook
full mock
fancy analytics
```

Never cut:

```text
verified answers
Learning Evidence
Mastery replayability
Daily Plan logic
content quality
```

---

# 173. Release Definition

## Engineering Alpha

Team-only.

## Private Alpha

10–20 invited students.

## Closed Alpha

30–50 students, 4–6 weeks.

## Paid Beta

Only after evidence.

## Public MVP

Only after:

- supported curriculum broad enough;
- retention;
- learning benefit;
- content production pipeline sustainable.

---

# 174. Final MVP Principle

> **MVP — это не урезанная версия будущего большого сайта.  
> MVP — это минимальный эксперимент, который проверяет главный механизм ценности.**

Для ENT Math AI этот механизм:

```text
Understand Student
      ↓
Choose Best Next Action
      ↓
Help Without Doing the Work
      ↓
Measure Real Learning
      ↓
Adapt Again
```

Если этот цикл работает, продукт стоит расширять.

Если он не работает, ещё 100 экранов, физика, родители, payments и mobile app не спасут продукт.

---

# 175. Next Document

Следующий документ:

> **`08_METRICS_AND_EXPERIMENTS.md`**

Он должен определить:

```text
North Star
Activation
Retention
Learning Metrics
Tutor Metrics
Import Metrics
Content Metrics
AI Cost Metrics
Beta Cohort Design
Baseline / Follow-up Testing
A/B Experiments
Statistical Guardrails
Go / No-Go Criteria
```

После него можно зафиксировать финальную architecture view и построить `10_ROADMAP.md`.
