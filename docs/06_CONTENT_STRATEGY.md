# 06_CONTENT_STRATEGY.md
## ENT Math AI — Question Bank, Content Production & Quality Strategy

> **Статус:** canonical content strategy  
> **Версия:** `CONTENT-v1`  
> **Дата:** 5 октября 2026  
> **Связанные документы:**  
> `00_CONCEPT.md`  
> `02_CURRICULUM_MAP.md`  
> `03_DOMAIN.md`  
> `04_LEARNING_ENGINE.md`  
> `05_AI_TUTOR.md`  
> `ENT_Math_AI_Technical_Specification_v1.md`
>
> **Назначение:** определить, откуда ENT Math AI получает учебный контент, как создаются и проверяются задания, как обеспечиваются лицензирование, математическая корректность, bilingual quality, difficulty, skill mapping, versioning и empirical calibration.

---

# 1. Главная мысль

ENT Math AI не должен выигрывать количеством вопросов.

Он должен выигрывать:

```text
качеством
+
структурой
+
метаданными
+
проверяемостью
+
педагогической полезностью
```

Продукт может иметь:

```text
700 сильных вопросов
```

и быть полезнее банка из:

```text
70 000 плохо размеченных вопросов
```

если эти 700 вопросов позволяют Learning Engine:

- диагностировать;
- различать skills;
- подбирать difficulty;
- проверять transfer;
- строить recovery loop;
- измерять mastery.

---

# 2. Content Is Core IP

Question Bank — не вспомогательная таблица.

Со временем он становится одной из главных интеллектуальных активов продукта.

Ценность одного вопроса:

```text
Question Text
+
Verified Answer
+
Verified Solution
+
Skill Mapping
+
Difficulty
+
Competency Tags
+
Prerequisites
+
Bilingual Version
+
Empirical Statistics
+
Error Patterns
```

---

# 3. Официальная спецификация — Blueprint, а не Question Bank

Официальная спецификация НЦТ используется для:

- структуры предмета;
- 10 разделов;
- 18 тем;
- допустимого содержания;
- форматов заданий;
- distribution A/B/C;
- alignment с ЕНТ.

Она НЕ означает, что мы можем:

```text
копировать закрытые вопросы НЦТ
```

или:

```text
перепубликовывать содержание реального экзамена
```

без соответствующих прав.

---

# 4. Content Source Hierarchy

Предпочтительный порядок:

```text
1. ORIGINAL_AUTHORED
2. PARAMETRIC_GENERATED
3. EXPERT_AUTHORED
4. LICENSED
5. OPEN_LICENSE
6. AI_ASSISTED_DRAFT
7. PRIVATE_IMPORTED_EXTERNAL
```

`PRIVATE_IMPORTED_EXTERNAL` никогда автоматически не попадает в public Question Bank.

---

# 5. Source Type: ORIGINAL_AUTHORED

Лучший основной источник Alpha.

Задание создаётся специально для ENT Math AI:

- по Curriculum Map;
- под конкретный skill;
- под difficulty;
- под нужный format.

Преимущества:

- полный контроль;
- понятные права;
- точная разметка;
- можно строить balanced bank;
- можно создавать bilingual пары.

---

# 6. Source Type: EXPERT_AUTHORED

Задание создаёт преподаватель/методист.

Требования:

- contractual rights defined;
- source declared;
- не копировать материалы работодателя/курса;
- author confirms originality/right to contribute;
- проходит тот же QA pipeline.

---

# 7. Source Type: LICENSED

Third-party content допускается только если:

```text
есть явное право на использование
```

Нужно хранить:

```text
licenseType
contract/reference
territory
duration
modificationRights
translationRights
AIProcessingRights
redistributionRights
```

---

# 8. Source Type: OPEN_LICENSE

Допустимо при совместимой лицензии.

Нужно проверять:

- attribution;
- commercial use;
- derivative works;
- share-alike;
- translation;
- redistribution.

Нельзя считать:

```text
"нашёл бесплатно в интернете"
=
"можно коммерчески использовать"
```

---

# 9. Source Type: AI_ASSISTED_DRAFT

AI может создавать:

```text
черновик
```

но не production question.

Pipeline:

```text
Prompt
  ↓
AI Draft
  ↓
Structural Validation
  ↓
Deterministic Answer Check
  ↓
Human Math Review
  ↓
Skill Review
  ↓
Language Review
  ↓
APPROVED
```

---

# 10. Source Type: PRIVATE_IMPORTED_EXTERNAL

Ученик загрузил:

- PDF;
- фото;
- screenshot;
- внешний пробник;
- официальный result report.

Такой контент может использоваться:

```text
для личного assessment
```

но НЕ автоматически для:

```text
public Question Bank
```

---

# 11. Official Exam Content Boundary

Нужно различать:

```text
официальная спецификация
```

и:

```text
содержание конкретных тестовых заданий
```

Спецификация — наш curriculum blueprint.

Конкретные вопросы могут иметь отдельный правовой/конфиденциальный режим.

---

# 12. Real ENT Content Policy

ENT Math AI не должен:

- поощрять фотографирование реального ЕНТ;
- просить пользователей приносить вопросы из экзаменационной аудитории;
- публиковать «сливы»;
- строить Question Bank из разглашаемых реальных экзаменационных заданий;
- создавать marketplace leaked questions.

Если пользователь загружает запрещённый/сомнительный материал:

он не становится public content.

---

# 13. Official Trial Test Results

Разрешённый продуктовый use case:

```text
user completes official trial assessment
→ uploads score/report/screenshot
→ system extracts results
→ creates Assessment Evidence
```

Это отличается от:

```text
копирования полного банка закрытых вопросов
```

---

# 14. Legal Review Requirement

До commercial launch требуется отдельный legal review по Казахстану для:

- copyright;
- database rights where applicable;
- contractual content;
- external assessments;
- user uploads;
- minors/privacy;
- terms of use.

Этот документ задаёт engineering/content policy, но не заменяет юридическое заключение.

---

# 15. Public vs Private Content

## Public Approved Content

Может показываться всем пользователям.

Требует:

- rights cleared;
- review completed;
- approved status.

## Private Imported Content

Доступен только владельцу assessment.

Не требует превращения в Question entity public bank.

---

# 16. Canonical Question Object

Question:

```text
Question
- identity
- source
- lifecycle
```

Version:

```text
QuestionVersion
- content
- language
- difficulty
- format
- curriculum mapping
```

---

# 17. Question Metadata Contract

Каждый APPROVED QuestionVersion обязан иметь:

```yaml
canonicalCode: TRIG-EQ-00124
curriculumVersion: ENT-MATH-2026-v1
officialTopic: "06"

primarySkill: EQ-TRIG-02

secondarySkills:
  - TRIG-ID-03

prerequisiteSkills:
  - EQ-TRIG-01

difficulty: B
format: SINGLE_CHOICE

estimatedTimeSeconds: 120

competencyTags:
  - METHOD_SELECTION
  - ALGEBRAIC_TRANSFORMATION

source:
  type: ORIGINAL_AUTHORED

language: RU

answerValidator:
  type: EXACT_OPTION

status: APPROVED
```

---

# 18. Required Content Components

Для standard question:

```text
1. Stem
2. Answer options / input definition
3. Correct answer
4. Verified solution
5. Primary skill
6. Difficulty
7. Format
8. Source/provenance
9. Language
```

Желательно:

```text
10. Prerequisites
11. Competency tags
12. Estimated time
13. Curated Hint 1
14. Curated Hint 2
15. Common error notes
```

---

# 19. Question Lifecycle

```text
DRAFT
   ↓
STRUCTURAL_REVIEW
   ↓
MATH_REVIEW
   ↓
PEDAGOGICAL_REVIEW
   ↓
LANGUAGE_REVIEW
   ↓
VALIDATION
   ↓
APPROVED
```

Possible exits:

```text
REJECTED
NEEDS_REVISION
SUSPENDED
RETIRED
```

---

# 20. Draft

Question exists but:

- cannot be served;
- may be incomplete;
- can change freely.

---

# 21. Structural Review

Checks:

- valid format;
- all required fields;
- no duplicate options;
- no missing diagram;
- answer type compatible;
- valid LaTeX;
- exact one primary skill;
- curriculum mapping exists.

---

# 22. Math Review

Reviewer verifies:

- problem is solvable;
- answer is correct;
- solution is correct;
- no hidden ambiguity;
- all domain restrictions considered;
- no extraneous roots;
- options make mathematical sense.

---

# 23. Pedagogical Review

Checks:

- appropriate school level;
- actually tests intended skill;
- not dependent on obscure trick unless C-level intentionally;
- difficulty estimate plausible;
- wording not misleading;
- distractors meaningful;
- item adds useful learning signal.

---

# 24. Language Review

Separate for:

```text
RU
KK
```

Checks:

- natural wording;
- terminology;
- punctuation;
- no literal awkward translation;
- mathematical meaning identical.

---

# 25. Validation Stage

Automated:

- answer validator passes;
- solution final answer matches AnswerKey;
- format valid;
- skill exists;
- no forbidden status;
- assets exist;
- LaTeX compiles/renders;
- duplicate similarity check.

---

# 26. Approval

Only APPROVED:

```text
can enter student practice
```

Approval records:

```text
reviewer
timestamp
version
```

---

# 27. Reviewer Roles

Recommended:

```text
AUTHOR
MATH_REVIEWER
LANGUAGE_REVIEWER_KK
LANGUAGE_REVIEWER_RU
CONTENT_EDITOR
APPROVER
```

Alpha team may combine roles.

But:

> author should ideally not be sole final approver of high-impact content.

---

# 28. Four-Eyes Principle

Для:

- C-level items;
- full mocks;
- diagnostic anchors;
- parametric generators;

желательно:

```text
author != final math reviewer
```

---

# 29. Question Families

Question Bank should not consist only of isolated questions.

Useful construct:

```text
QuestionFamily
```

Example:

```text
Quadratic substitution in trig equations
```

Family has:

- pattern;
- target skill;
- difficulty envelope;
- generation constraints;
- common errors.

---

# 30. Why Families Matter

They help:

- parametric generation;
- transfer variants;
- avoid memorization;
- create recovery items;
- control difficulty.

---

# 31. Parametric Question Generation

For suitable algebra questions, use deterministic templates.

Example:

```text
ax² + bx + c = 0
```

Generator chooses parameters satisfying constraints.

Advantages:

- many equivalent forms;
- deterministic answer;
- low copyright risk;
- reproducible;
- controllable difficulty.

---

# 32. Generator Contract

Each generator defines:

```text
generatorId
version
targetSkill
parameterSpace
constraints
answerFunction
solutionFunction
difficultyRules
validationProperties
```

---

# 33. Generator Example

```text
QUADRATIC-INTEGER-ROOTS-v1

choose:
r1, r2

derive:
x² - (r1+r2)x + r1*r2 = 0

constraints:
r1 != r2
|r1| <= 10
|r2| <= 10
```

---

# 34. Generator Validation

Property tests:

```text
generated item solvable
answer key correct
all options distinct
no invalid denominator
no accidental multiple correct options
difficulty constraints
```

---

# 35. Seeded Generation

Every generated instance stores:

```text
generatorVersion
seed
parameters
```

So exact question can be reproduced.

---

# 36. When NOT to Parameterize

Avoid naive generators for:

- complex geometry diagrams;
- nuanced word problems;
- C-level modeling;
- tasks where random values create absurd real-world conditions.

---

# 37. AI-Assisted Authoring

AI can help:

- propose variants;
- rephrase wording;
- create distractor candidates;
- translate draft;
- suggest skill mapping;
- generate solution draft;
- identify ambiguities.

AI cannot final-approve.

---

# 38. AI Draft Labels

Every AI-assisted draft stores:

```text
aiAssisted = true
provider/model
promptVersion
createdAt
```

This is internal provenance.

---

# 39. AI-Generated Answer Verification

If generated question has deterministic answer:

backend/script verifies it independently.

Examples:

- numeric evaluation;
- symbolic computation;
- exhaustive option check.

---

# 40. AI Cannot Validate Itself

Bad:

```text
Gemini generates question
→ Gemini says answer is correct
→ APPROVED
```

Good:

```text
Gemini draft
→ deterministic check
→ human review
```

---

# 41. Difficulty Is an Item Property

Never:

```text
Skill EQ-QUAD = B
```

Correct:

```text
Question A: EQ-QUAD-02, difficulty A
Question B: EQ-QUAD-02, difficulty B
Question C: EQ-QUAD-02, difficulty C
```

---

# 42. Difficulty Assignment v1

Initial difficulty = expert estimate.

Use official categories:

```text
A
B
C
```

---

# 43. A-Level Characteristics

Usually:

- direct method;
- familiar representation;
- 1–2 main steps;
- no major method-selection challenge.

---

# 44. B-Level Characteristics

Usually:

- choose method;
- multiple transformations;
- combine 1–2 skills;
- interpret graph/table/diagram.

---

# 45. C-Level Characteristics

Usually:

- multi-skill;
- non-obvious setup;
- more reasoning;
- transfer;
- deeper modeling.

---

# 46. Difficulty Rubric

Reviewer score dimensions:

```text
method selection
number of steps
algebraic complexity
representation complexity
skill integration
distractor strength
time pressure
```

Total maps to tentative A/B/C.

---

# 47. Empirical Difficulty

After enough student data:

expert difficulty remains metadata.

Add:

```text
empiricalDifficulty
```

Do not silently overwrite original.

---

# 48. Difficulty Calibration Minimum

Do not calibrate from:

```text
10 attempts
```

Initial threshold:

```text
>= 100 independent attempts
```

for basic statistics.

More sophisticated IRT requires much more stable data.

---

# 49. Question Discrimination

Future metric:

> does this item distinguish learners with stronger vs weaker relevant ability?

Low discrimination may indicate:

- ambiguity;
- guessing;
- wrong mapping;
- too easy;
- broken item.

---

# 50. Guessing Risk

Multiple-choice items have chance success.

Balance bank with:

- matching;
- multi-select;
- numeric/input forms where product mode allows;
- repeated varied evidence.

---

# 51. Official Format Fidelity

Practice bank should include official formats:

```text
SINGLE_CHOICE
CONTEXT_SINGLE_CHOICE
MULTI_SELECT
MATCHING
```

Other pedagogical formats may exist:

```text
INTEGER
DECIMAL
STEP_INPUT
```

but must be labeled:

```text
PRACTICE_ONLY
```

if not exam-format.

---

# 52. Context-Based Questions

Need:

```text
ContextBlock
  ↓
Question A
Question B
...
```

Context must be versioned.

---

# 53. Matching Questions

AnswerKey:

```text
left item → right option
```

Need prevent ambiguous duplicate mapping unless allowed.

---

# 54. Multi-Select

Metadata must define:

- scoring policy;
- number of possible options;
- whether partial credit exists.

Do not let frontend invent scoring.

---

# 55. Distractor Strategy

Good distractor:

- corresponds to plausible mistake;
- not nonsense;
- not obviously different format;
- useful diagnostically.

Example:

```text
correct root
sign error root
forgot domain restriction
used wrong formula
```

---

# 56. Diagnostic Distractors

Especially valuable if distractor maps to error pattern.

Example:

```text
Option B
→ SIGN_ERROR

Option C
→ FORMULA_CONFUSION
```

This can create rule-based ErrorEvent.

---

# 57. Distractor Metadata

Optional:

```text
optionErrorMap
```

Example:

```json
{
  "B": "SIGN_ERROR",
  "C": "FORMULA_CONFUSION"
}
```

Only when reviewer confident.

---

# 58. Solution Requirements

Verified solution should be:

- mathematically correct;
- stepwise;
- sufficiently concise;
- school-aligned;
- usable by Tutor.

---

# 59. Solution Structure

Recommended:

```yaml
steps:
  - id: 1
    concept: ...
    expression: ...
  - id: 2
    ...
finalAnswer: ...
```

Not only giant free-text paragraph.

---

# 60. Why Structured Solution

AI Tutor can enforce:

```text
maxRevealStep
```

and answer leakage boundaries.

---

# 61. Curated Hints

For P0 Alpha content, strongly preferred:

```text
Hint 1
Hint 2
```

This provides AI fallback.

---

# 62. Hint 1

Should:

- nudge;
- not name final method fully;
- not reveal answer.

---

# 63. Hint 2

Can:

- state method;
- still leave calculations to student.

---

# 64. Error Notes

Question can include:

```text
commonErrors
```

Example:

```text
forgetting ODZ
wrong sign in discriminant
```

These are reviewer-authored patterns.

---

# 65. Estimated Time

Initial expert estimate.

Later empirical:

```text
median independent solve time
```

Store both.

---

# 66. Skill Mapping Review

Reviewer asks:

> What knowledge is this item primarily measuring?

Not:

> What topics happen to appear in the text?

---

# 67. Primary Skill

Exactly one.

It gets main mastery evidence.

---

# 68. Secondary Skill

Used when solving meaningfully requires another skill.

Do not create 8 secondary links to make metadata look rich.

---

# 69. Prerequisite Skill

Knowledge expected before intended skill.

Not necessarily scored from item.

---

# 70. Competency Tags

Separate from mathematical skill.

Examples:

```text
METHOD_SELECTION
DIAGRAM_INTERPRETATION
GRAPH_INTERPRETATION
MODELING
MULTI_STEP_REASONING
ALGEBRAIC_TRANSFORMATION
```

---

# 71. Mapping Confidence

For internal approved content:

```text
1.0
```

after review.

For AI-suggested mapping before review:

```text
< 1
```

---

# 72. Bilingual Architecture

Canonical meaning independent from language.

Conceptually:

```text
Question Identity
  ├── RU Version
  └── KK Version
```

Both share:

- answer semantics;
- skills;
- difficulty intent.

---

# 73. Translation Is Not Blind Copy

Translation review verifies:

- same givens;
- same question;
- same options;
- same answer;
- same ambiguity level.

---

# 74. Language Version Independence

If RU wording fixed:

KK version may require independent revision.

Do not auto-update translated text silently.

---

# 75. Translation Status

Per language:

```text
DRAFT
REVIEWED
APPROVED
```

Question can be approved RU but not yet KK.

Then it cannot appear in KK pool.

---

# 76. Mathematical Glossary

Required versioned glossary:

```text
ConceptCode
RU
KK
Notes
Preferred
DeprecatedVariants
```

Used by:

- authors;
- translators;
- Tutor;
- QA.

---

# 77. Kazakh Language Quality

Need subject-matter reviewer fluent in academic/school Kazakh.

Not enough:

```text
generic translator
```

because mathematical terminology matters.

---

# 78. Symbols Stay Canonical

Examples:

```text
sin
cos
log
√
π
```

not translated differently by language.

---

# 79. Diagram Content

Use structured diagram specification where feasible.

Options:

- SVG;
- generated geometry;
- static reviewed asset.

---

# 80. Diagram Accessibility

Store alt/description.

Useful for:

- accessibility;
- Tutor context;
- QA.

---

# 81. Generated Geometry Diagrams

Prefer deterministic geometry tools for simple figures.

Do not ask image model to draw mathematically precise diagram.

---

# 82. Image Generation Rule

Decorative AI images:

not needed.

Mathematical diagrams must be deterministic/reviewed.

---

# 83. Asset Versioning

QuestionVersion references immutable asset version.

Never replace image file under same semantic reference without versioning.

---

# 84. Duplicate Detection

Need detect:

- exact duplicates;
- near duplicates;
- same template with trivial number changes;
- accidental copied external item.

---

# 85. Exact Duplicate

Normalize:

- whitespace;
- LaTeX;
- option ordering where appropriate.

Hash.

---

# 86. Semantic Similarity

Future:

embeddings can flag possible duplicates.

But:

```text
human reviews
```

final decision.

No vector DB required for Alpha.

---

# 87. Near-Duplicate Purpose

Some controlled variants are intentional.

If same family:

store:

```text
QuestionFamilyId
```

Not considered harmful duplicate.

---

# 88. Exposure Diversity

Learning Engine should know family.

Avoid:

```text
5 questions same template
```

as five strong independent evidence points.

---

# 89. Family Repetition Discount

`04_LEARNING_ENGINE` can later include:

```text
same-family discount
```

even if exact question differs.

Alpha enhancement recommended.

---

# 90. Content Coverage Matrix

Need dashboard:

```text
Skill × Difficulty × Format × Language
```

Example:

| Skill | A RU | B RU | C RU | A KK | B KK | C KK |
|---|---:|---:|---:|---:|---:|---:|
| EQ-QUAD-02 | 12 | 15 | 6 | 12 | 15 | 6 |
| EQ-TRIG-02 | 8 | 11 | 4 | 7 | 10 | 4 |

---

# 91. Coverage Health

Possible statuses:

```text
EMPTY
CRITICAL
THIN
USABLE
HEALTHY
```

---

# 92. Initial Thresholds

For P0 skill:

```text
<5 approved items
→ CRITICAL

5–9
→ THIN

10–14
→ USABLE

15+
→ HEALTHY for Alpha
```

After Beta thresholds increase.

---

# 93. Alpha Target

From Curriculum Map:

```text
~40–50 selected P0 skills
```

Target:

```text
~12–15 approved items / skill
```

Total:

```text
~500–700 high-quality items
```

---

# 94. Why Not All 127 Skills First

Because quality effort grows explosively:

```text
127 skills
× 20 questions
× 2 languages
= 5,080 localized question instances
```

before review overhead.

This delays validation of product thesis.

---

# 95. Alpha Scope Selection

Choose skills with:

- central prerequisites;
- common school relevance;
- good automatic validation;
- diverse learning patterns;
- enough breadth to test Adaptive Engine.

---

# 96. Proposed Alpha Content Waves

## Wave 1 — Algebra Core

```text
NUM / ALG
EQ-LIN
EQ-QUAD
EQ-RAT
INEQ-LIN
INEQ-QUAD
```

## Wave 2 — Trigonometry + Logs

```text
TRIG
EQ-TRIG
EQ-EXP
EQ-LOG
INEQ-EXP
INEQ-LOG
```

## Wave 3 — Functions / Analysis

```text
FUN
CALC-DER
basic CALC-INT
```

## Wave 4 — Geometry Core

```text
PLN-TRI
PLN-MET
PLN-AREA
PLN-CIR
```

---

# 97. Stereometry Timing

Stereometry should enter after content pipeline proven.

Reason:

- diagrams;
- more review;
- higher ambiguity risk.

Not because stereometry unimportant.

---

# 98. Alpha Question Mix

Within mature P0 skill target:

```text
~40% A
~40% B
~20% C
```

This is bank-production mix, not official test distribution.

Why more B/C than official A/B/C ratio:

adaptive practice needs enough upward progression.

---

# 99. Exam Simulation Pool

Separate tagged subset should approximate official distribution:

```text
A ~50%
B ~30%
C ~20%
```

according to specification.

---

# 100. Practice Pool vs Mock Pool

Question can belong to:

```text
PRACTICE_ELIGIBLE
MOCK_ELIGIBLE
DIAGNOSTIC_ELIGIBLE
```

These are different qualities.

---

# 101. Diagnostic Anchor Criteria

Diagnostic item:

- stable difficulty;
- clear mapping;
- low ambiguity;
- good discrimination;
- no obscure trick;
- ideally independent of rare prerequisite.

---

# 102. Mock Eligibility

Mock item:

- exam-like wording;
- official format;
- no Tutor-specific scaffolding;
- reviewed timing;
- representative difficulty.

---

# 103. Practice Eligibility

May include:

- pedagogical formats;
- simpler steps;
- generated variants;
- scaffolded questions.

---

# 104. Holdout Questions

Some questions should not be used for routine drilling.

Purpose:

```text
independent validation
mock calibration
transfer measurement
```

---

# 105. Holdout Pool

For each mature skill:

try reserve:

```text
10–20%
```

unseen/low-exposure items.

---

# 106. Content Leakage

If questions widely memorized:

empirical success inflates.

Track:

```text
exposure count
family exposure
```

---

# 107. Content Authoring UI

Admin/editor needs:

```text
Question editor
LaTeX preview
Option editor
Skill selector
Difficulty rubric
Solution steps
Hint editor
RU/KK tabs
Source/provenance
Validation
Review history
```

---

# 108. Do Not Author Directly in SQL

Production content should flow through:

```text
admin UI
or
validated import format
```

not raw database editing.

---

# 109. Bulk Authoring

Support:

```text
YAML/JSON/CSV structured import
```

for trusted content team.

Always passes validator.

---

# 110. Content as Code — Partial

Useful for:

- generators;
- seed content;
- glossary;
- curriculum map;
- golden test items.

But large live bank can live in DB with export/versioning.

---

# 111. Content Export

Need ability:

```text
export question bank metadata
```

for:

- review;
- backup;
- migration;
- offline analysis.

---

# 112. Approval Audit

Every approval:

```text
who
when
what version
review notes
```

---

# 113. Content Defect Workflow

User reports:

```text
wrong answer
ambiguous
translation problem
formatting
```

Creates:

```text
ContentIssue
```

---

# 114. Defect Severity

```text
P0_CRITICAL
P1_HIGH
P2_MEDIUM
P3_LOW
```

---

# 115. Critical Defect

Examples:

- wrong AnswerKey;
- impossible question;
- two correct answers in single-choice;
- misleading diagram changing answer.

Action:

```text
SUSPEND immediately
```

---

# 116. High Defect

Examples:

- incorrect translation;
- wrong skill mapping;
- serious ambiguity.

May suspend language version or question.

---

# 117. Low Defect

Examples:

- typo;
- minor formatting.

Can remain active if meaning unaffected, but fix via new version.

---

# 118. Affected Learning Evidence

Wrong AnswerKey may have corrupted Mastery.

Workflow:

```text
Question suspended
→ identify affected Attempts
→ identify LearningEvidence
→ invalidate/supersede
→ rebuild Mastery
```

---

# 119. Translation Defect Impact

If only KK text wrong:

affected:

```text
KK QuestionVersion
```

RU version may remain valid.

---

# 120. Mapping Defect Impact

Wrong skill mapping:

mathematical correctness unaffected, but mastery affected.

Must:

```text
rebuild evidence target
```

---

# 121. Empirical Quality Signals

Track:

```text
success rate
response time
skip rate
hint rate
full solution rate
option selection distribution
high-mastery failure rate
user issue rate
```

---

# 122. Suspicious Item Patterns

Potential issue:

```text
90% choose same wrong distractor
```

Maybe:

- common misconception;
- or ambiguous wording.

Needs review.

---

# 123. High-Mastery Failure Signal

If learners with strong mastery consistently fail:

possible:

- item too hard;
- wrong mapping;
- broken item;
- answer issue.

---

# 124. Low-Mastery Success Signal

Possible:

- item too easy;
- guessing;
- leaked/memorized;
- weak discrimination.

---

# 125. Option Distribution

Single choice:

if distractors never selected:

they may be useless.

But not automatically defect.

---

# 126. Review Queue from Analytics

Generate:

```text
CONTENT_REVIEW_SIGNAL
```

not automatic content change.

---

# 127. Human Remains Final Content Authority

Empirical metrics can flag.

AI can flag.

Students can report.

Final status change for approved high-impact item:

human/admin policy.

---

# 128. Content Versioning

Published version immutable.

Fix:

```text
v1 → RETIRED/SUPERSEDED
v2 → APPROVED
```

Historical Attempts keep `questionVersionId`.

---

# 129. Minor Formatting Changes

Even minor changes should preserve version trace.

Could classify:

```text
PATCH
```

but do not silently mutate production meaning.

---

# 130. AnswerKey Versioning

AnswerKey belongs to QuestionVersion.

No global mutable answer.

---

# 131. Solution Versioning

Solution can have language versions.

If mathematical method corrected:

new QuestionVersion or SolutionVersion with audit.

---

# 132. Hint Versioning

Curated hints versioned.

Important for Tutor regression.

---

# 133. Content Release

Approved does not always mean immediately visible.

Use:

```text
releaseState
```

Possible:

```text
INTERNAL
BETA
PUBLIC
```

---

# 134. Beta Content

New item may initially be served to small cohort.

Purpose:

- empirical sanity check;
- issue detection.

---

# 135. Beta Item Weight

Learning Engine may assign reduced:

```text
itemQuality
```

until validated empirically.

Example:

```text
0.8
```

---

# 136. Mature Item

After:

- enough usage;
- no defects;
- stable performance;

quality may become:

```text
1.0
```

---

# 137. Content Quality Score

Do not collapse all quality into one magic score initially.

Keep factors:

```text
reviewStatus
empiricalData
issueCount
mappingConfidence
languageStatus
```

Learning Engine uses simple itemQuality policy.

---

# 138. AI-Assisted Duplicate Check

AI may compare wording similarity.

But no automatic plagiarism/legal conclusion.

---

# 139. Copyright Provenance Checklist

Before APPROVED:

- [ ] Source declared.
- [ ] Rights basis known.
- [ ] If original, author attestation recorded.
- [ ] If licensed, license allows intended use.
- [ ] Translation right checked where relevant.
- [ ] No copied closed-bank wording without permission.
- [ ] External user import not promoted to public bank.
- [ ] No leaked live exam content.

---

# 140. Author Attestation

Contributor confirms:

> «Я имею право передать данный материал для использования в ENT Math AI и не копировал его из закрытого источника, если такое использование не разрешено.»

Exact legal wording to be reviewed by lawyer.

---

# 141. Public Web Sources

Web page availability does not mean free reuse.

If using idea/fact:

rewrite into original problem.

If reproducing exact wording:

need rights basis.

---

# 142. Textbook-Inspired Questions

Mathematical concept itself is not owned.

But exact creative wording/selection may be protected.

Strategy:

```text
use curriculum concept
→ author original item
```

not:

```text
copy screenshot
```

---

# 143. User-Uploaded External Content

Terms should state:

- user uploads for personal analysis;
- no guarantee of rights to redistribute;
- product does not automatically publish it.

---

# 144. Abuse / Takedown

Need process for:

- rights complaint;
- leaked content report;
- source dispute.

Admin can:

```text
quarantine
remove public access
retain legal audit metadata as appropriate
```

---

# 145. Private Assessment Retention

Private external question text should have separate retention policy from public Question Bank.

May be deleted after learning extraction if product design permits.

---

# 146. Minimal External Storage Principle

If external screenshot gives only topic scores:

do not persist full image longer than needed.

Normalize:

```text
Assessment
Topic Results
Provenance
```

---

# 147. OCR Errors

External extraction never becomes public content.

If OCR text only needed for mapping:

store confidence.

User review required when material.

---

# 148. Content Moderation for Uploads

Reject/quarantine:

- non-educational abusive files;
- malware;
- obviously unrelated content;
- suspicious leaked exam dumps depending on policy.

---

# 149. Question Writing Style Guide

Questions should:

- be concise;
- avoid unnecessary stories;
- avoid ambiguous pronouns;
- define variables;
- use consistent symbols;
- specify units;
- state rounding if needed.

---

# 150. Negative Wording

Avoid:

```text
Which is NOT...
```

unless deliberately required.

Negative wording increases accidental error unrelated to mathematics.

---

# 151. Trick Questions

Avoid trick for trick's sake.

C-level means:

```text
deeper reasoning
```

not:

```text
linguistic trap
```

---

# 152. Cultural Context

Word problems can use Kazakhstan-relevant contexts.

But context must not introduce:

- regional bias;
- obscure cultural knowledge;
- unnecessary socioeconomic assumptions.

---

# 153. Currency

If money:

```text
₸
```

and realistic values.

Math should not depend on current market price unless source is dynamic and intentional.

---

# 154. Names

Use diverse neutral names when needed.

Avoid content requiring personal demographic inference.

---

# 155. Units

Use SI / school-standard units.

Always verify conversion.

---

# 156. Rounding

If approximate answer:

explicitly state:

```text
round to ...
```

---

# 157. Domain Restrictions

Must explicitly/reliably handle:

- denominator ≠ 0;
- log argument > 0;
- log base constraints;
- even root constraints;
- trig domains where needed.

---

# 158. Geometry Diagram Rule

Diagram not necessarily to scale unless stated.

If visual measurement not intended:

avoid misleading proportions.

---

# 159. Geometry Given Data

Do not rely on visual impression for:

- right angle;
- equal lengths;
- parallel lines;

unless marked or stated.

---

# 160. Context Item Rule

All needed information must be in context.

Subquestions should not accidentally depend on previous student answer unless designed.

---

# 161. Multi-select Distractors

Ensure exact intended correct set.

Automated exhaustive option validation where possible.

---

# 162. Matching Validation

No duplicate right-side identifier bugs.

Automated permutation/structure checks.

---

# 163. Numeric Input

Define accepted forms:

```text
2
2.0
2,0?
```

Locale-aware input normalization.

---

# 164. Decimal Separators

RU/KK users may enter:

```text
2,5
```

or:

```text
2.5
```

Normalizer can accept both where unambiguous.

---

# 165. Rational Answers

Accept normalized equivalents:

```text
1/2
2/4
```

if validator supports rational equivalence.

---

# 166. Sign Variants

Need canonical parser for:

```text
−
-
```

---

# 167. LaTeX Authoring

Store source in controlled syntax.

Need preview and lint.

---

# 168. Unsupported LaTeX

Reject macros not supported by renderer.

---

# 169. Security

Question content is data, not executable HTML.

Sanitize all rendered text.

---

# 170. Content API

Student API never returns:

```text
correct answer
solution
hidden tags
```

before policy allows.

---

# 171. Answer Leakage Through API

Question endpoint must not accidentally include:

```text
answerKey
```

in JSON.

Use separate DTOs.

---

# 172. Admin API

Content admin endpoints separately authorized.

---

# 173. Database Access

Question Bank tables owned by `content` module.

Learning module accesses through content API/port, not raw repository.

---

# 174. Search

Admin needs search:

- code;
- skill;
- source;
- status;
- language;
- difficulty;
- issue.

Student does not need full bank search in Alpha.

---

# 175. Random Practice

If added:

still selects through QuestionSelectionService.

No:

```sql
ORDER BY random()
```

as learning strategy.

---

# 176. Content Selection and Family

QuestionSelectionService receives:

```text
skill
target difficulty
exposure history
family history
language
```

---

# 177. Question Serving Snapshot

When session assigns a question:

store:

```text
questionVersionId
```

So mid-session content release cannot change item.

---

# 178. Suspended Mid-Session

If critical question suspended:

server can:

- skip;
- replace;
- invalidate attempt if needed.

Policy documented.

---

# 179. Question Retirement

RETIRED:

- not served new;
- historical records retained.

---

# 180. Content Analytics Ownership

Raw attempts in assessment/learning.

Content module gets aggregated metrics/read models.

Avoid cross-module SQL spaghetti.

---

# 181. Alpha Production Team Estimate

Minimal content team can start with:

```text
1 math lead
1–2 authors
1 KK reviewer
1 RU/math reviewer
```

Roles can overlap.

But math approval and KK quality must not be ignored.

---

# 182. Production Velocity Target

Quality-first initial target:

```text
10–20 fully reviewed bilingual questions / working day
```

per small team after process stabilizes.

Do not force higher quota if defects rise.

---

# 183. 600-Question Alpha Math

At:

```text
15 bilingual approved questions/day
```

600 canonical questions require significant production effort.

This confirms why content scope must be narrow.

---

# 184. Canonical vs Localized Count

If one canonical question has:

```text
RU + KK
```

count as:

```text
1 canonical question
2 localized versions
```

Do not inflate marketing number.

---

# 185. Alpha Minimum Before Closed Beta

Not necessarily 600.

Closed beta can start when:

```text
critical P0 path
has sufficient content
```

Example:

```text
250–350 high-quality canonical questions
```

for narrower cohort.

---

# 186. Expansion Gate

Add next skills when:

- current content healthy;
- Learning Engine works;
- users need coverage;
- authoring pipeline stable.

---

# 187. Content Demand Score

Internal priority:

```text
Demand =
student priority frequency
× missing content severity
× curriculum centrality
```

Helps decide what authors create next.

---

# 188. Do Not Optimize for Count

Bad KPI:

```text
10,000 questions by December
```

Better:

```text
90% of active P0 skills have ≥15 approved items
<0.5% critical defect rate
bilingual parity >95%
```

---

# 189. Content KPIs

Track:

```text
approved canonical questions
coverage by skill
coverage by difficulty
KK/RU parity
critical defect rate
issue rate / 1k attempts
question reuse concentration
holdout availability
median review time
```

---

# 190. Quality KPI

Possible:

```text
Critical Content Defects
per 10,000 item exposures
```

This is more meaningful than question count.

---

# 191. Bilingual Parity KPI

```text
approved KK versions
/
approved RU canonical set
```

Target for public release:

```text
near 100% for supported scope
```

---

# 192. Content Freshness

Math concepts stable.

But curriculum mapping may change.

Need:

```text
curriculum version review
```

each official spec update.

---

# 193. Annual Curriculum Audit

When NTC publishes new spec:

```text
diff official sections/topics
→ map current skills
→ identify additions/removals
→ update curriculum version
→ re-evaluate content eligibility
```

---

# 194. Stable Skill Reuse

If concept unchanged:

reuse stable skill.

QuestionVersion may become eligible under new curriculum version.

---

# 195. Deprecated Content

If topic removed:

content can remain historical.

Not served for active curriculum.

---

# 196. Content and Learning Evidence

Question defect can affect learning evidence.

Therefore content quality is not cosmetic.

Wrong content:

```text
corrupts Student Model
```

This is why approval pipeline must be strict.

---

# 197. Content and Tutor

Tutor quality depends heavily on:

```text
verified solution
```

Therefore every P0 item should strive for structured verified solution.

---

# 198. Content and External Import

External question text should never be assumed approved.

It can help:

- mapping;
- assessment explanation;
- private tutoring;

under separate policy.

---

# 199. Content and Readiness

Internal Mock requires a dedicated calibrated pool.

Routine repeated practice questions should not dominate score estimation.

---

# 200. Mock Pool Separation

Possible policy:

```text
MOCK_ONLY
PRACTICE_ONLY
BOTH
```

For high-stakes calibration:

reserve mock items.

---

# 201. Diagnostic Pool Separation

Diagnostic anchors should have:

```text
DIAGNOSTIC_ELIGIBLE
```

and limited exposure.

---

# 202. Exposure Limits

For holdout items:

avoid showing in free practice before diagnostic/mock.

---

# 203. Security of Holdout Pool

Admin permissions restricted.

No public API list.

---

# 204. Question Bank Backup

Regular DB backup + object storage backup.

Export metadata periodically.

---

# 205. Content Disaster Recovery

Need restore:

- questions;
- versions;
- solutions;
- assets;
- review history;
- provenance.

---

# 206. Content Migration Test

CI validates migrations against representative bank.

---

# 207. Test Fixtures

Maintain small curated test set in repository:

```text
test-fixtures/content/
```

Used for:

- validators;
- API;
- Tutor;
- Learning Engine.

---

# 208. Golden Questions

A subset of questions acts as engineering goldens.

Never changed casually.

---

# 209. Parametric Generator Tests

CI:

```text
generate thousands of seeds
```

for cheap deterministic templates.

Assert properties.

---

# 210. Content Lint

Automated lint:

- duplicate options;
- empty options;
- broken LaTeX;
- mismatch language;
- missing units;
- missing answer;
- inconsistent option count;
- trailing spaces;
- suspicious HTML;
- forbidden fields.

---

# 211. Semantic Lint

Possible AI-assisted flag:

- ambiguity;
- awkward wording;
- unusual terminology.

Flag only.

Human decides.

---

# 212. Content Review SLA

Not needed for Alpha public promise.

Internal queue should expose:

```text
oldest pending
critical issue age
```

---

# 213. Student Reports

At question:

```text
[ Сообщить об ошибке ]
```

Options:

- wrong answer;
- unclear wording;
- typo;
- translation;
- diagram;
- other.

---

# 214. Report Does Not Reveal Correct Answer

Report UI can collect issue without exposing hidden solution.

---

# 215. Automatic Suspension Threshold

For critical reports:

do not auto-suspend from one anonymous report.

But:

```text
multiple high-confidence reports
→ urgent review
```

Emergency admin kill available.

---

# 216. Content Trust UI

No need show full content provenance to student routinely.

But official/external labeling should be truthful.

Never label our authored item:

```text
"Official ENT question"
```

unless it truly is and rights allow.

---

# 217. Marketing Language

Allowed:

```text
"aligned with ENT specification"
```

Not automatically:

```text
"official ENT questions"
```

---

# 218. AI Marketing

Do not say:

```text
"AI generated unique official questions"
```

Official status does not come from style similarity.

---

# 219. Source Naming

Internal authored:

```text
ENT Math AI Practice
```

External private:

show actual source if known.

---

# 220. Content Provenance UI for Admin

Admin sees:

```text
source
author
license
AI assistance
reviewers
version history
```

---

# 221. Content Quality Gate — P0 Alpha

Before P0 skill becomes active:

- [ ] minimum 10 approved canonical items;
- [ ] at least A and B coverage;
- [ ] RU and KK available for supported languages;
- [ ] verified solutions;
- [ ] answer validators tested;
- [ ] at least 2 holdout items;
- [ ] no open critical issue.

---

# 222. Stronger Beta Gate

Before public Beta:

- [ ] ~15+ items per active P0 skill;
- [ ] A/B/C coverage where appropriate;
- [ ] bilingual parity;
- [ ] empirical statistics available for core items;
- [ ] issue rate acceptable;
- [ ] diagnostic anchors reviewed.

---

# 223. Mock Release Gate

Full mock set requires:

- official format mix;
- difficulty distribution target;
- no recently overexposed items;
- double math review;
- bilingual review;
- timing sanity check.

---

# 224. Mock Assembly

Assembler uses constraints:

```text
total questions
format distribution
difficulty distribution
curriculum coverage
family diversity
no duplicate
```

---

# 225. Official Distribution

For current profile mathematics specification:

```text
A ≈ 50%
B ≈ 30%
C ≈ 20%
```

Mock assembler uses this target.

Exact topic distribution cannot be invented if not officially specified.

---

# 226. Context Questions in Mock

Need match official format count.

Context blocks must be curated.

---

# 227. Multi-select / Matching in Mock

Need exact official format proportions according to current specification.

---

# 228. Mock Blueprint Version

```text
ENT-MATH-MOCK-2026-v1
```

If official format changes:

new blueprint.

---

# 229. Content Factory Workflow

```text
Curriculum Gap
      ↓
Authoring Brief
      ↓
Original / Parametric / AI-assisted Draft
      ↓
Structural Validation
      ↓
Math Review
      ↓
Skill Mapping Review
      ↓
Difficulty Review
      ↓
RU/KK Language Review
      ↓
Automated Validator
      ↓
APPROVED
      ↓
Beta Exposure
      ↓
Empirical Calibration
      ↓
MATURE
```

---

# 230. Authoring Brief

Before writing question:

```text
target skill
difficulty
format
competency tag
common misconception
desired solution pattern
```

This is better than:

> «Напиши 20 задач по логарифмам.»

---

# 231. AI Authoring Prompt

AI prompt should be generated from brief.

Example conceptual:

```text
Create ONE draft item.
Target skill: EQ-LOG-03
Difficulty: B
Format: SINGLE_CHOICE
Do not imitate known copyrighted test text.
Use original values and wording.
Return structured JSON.
```

---

# 232. AI Output Schema for Draft

```json
{
  "stem": "...",
  "options": [],
  "answer": "...",
  "solutionSteps": [],
  "skill": "EQ-LOG-03",
  "difficulty": "B",
  "notes": []
}
```

---

# 233. AI Draft Validation

Reject if:

- wrong skill;
- answer mismatch;
- ambiguous options;
- unsupported notation;
- suspiciously copied wording;
- duplicated item;
- invalid domain.

---

# 234. AI Translation Draft

AI may create initial KK/RU counterpart.

Still:

```text
LANGUAGE_REVIEW REQUIRED
```

---

# 235. Translation Review Tool

Admin can see side-by-side:

```text
RU
KK
```

plus answer/variables highlighting.

---

# 236. Variable Consistency Check

Automated:

if RU has:

```text
a=3, b=5
```

KK must not accidentally become:

```text
a=3, b=6
```

Need structured values where possible.

---

# 237. Option Consistency Check

Ensure correct option remains semantically correct across translations.

---

# 238. Parametric + Bilingual

Best approach:

template separates:

```text
mathematical data
```

from:

```text
localized wording
```

Then same generated instance renders RU/KK.

---

# 239. Example Template Model

```text
Template:
"Find roots of {equation}"

Data:
equation = ...

Locale:
RU → "Найдите корни..."
KK → "Теңдеудің түбірлерін табыңыз..."
```

---

# 240. This Is Better Than Translating Every Instance

Less review overhead.

But localized template must be carefully reviewed.

---

# 241. Content Service Interfaces

Conceptual:

```text
getQuestionForStudent(...)
getVerifiedAnswer(...)
getVerifiedSolution(...)
searchQuestionsForAdmin(...)
approveQuestion(...)
suspendQuestion(...)
```

Learning module never gets AnswerKey before attempt validation use case unless authorized internal path.

---

# 242. Answer Validation Ownership

Content owns canonical answer data.

Assessment/application invokes:

```text
AnswerValidationService
```

---

# 243. Tutor Context Ownership

Tutor receives verified solution through controlled Content port.

Not direct DB.

---

# 244. Question Editing Concurrency

Admin editor needs optimistic locking.

Avoid reviewers overwriting each other.

---

# 245. Review Comments

Store comments separate from student-visible content.

---

# 246. Audit Trail

Every content transition:

```text
DRAFT → APPROVED
APPROVED → SUSPENDED
...
```

audited.

---

# 247. Content Permissions

Roles:

```text
AUTHOR
REVIEWER
APPROVER
ADMIN
```

Least privilege.

---

# 248. Production Direct DB Writes

Forbidden except controlled emergency/migration procedure.

---

# 249. Metrics by Skill

Need dashboard:

```text
coverage
attempts
success
hint usage
defects
```

---

# 250. Metrics by Language

Detect:

```text
KK success very different from RU
```

Could indicate:

- translation issue;
- cohort difference.

Do not assume automatically translation defect.

Review.

---

# 251. Translation A/B Testing

Avoid experimenting with mathematically different wording without preserving equivalence.

Could test clarity after expert approval.

---

# 252. Accessibility

Question text readable with keyboard/screen reader as feasible.

Diagrams have descriptions.

Do not encode essential question only as image if avoidable.

---

# 253. Mobile First Constraints

Questions must render on phone.

Avoid giant tables/diagrams without responsive design.

---

# 254. Formula Overflow

KaTeX containers need horizontal handling.

Content QA includes common phone viewport.

---

# 255. Image Resolution

Assets optimized but precise.

No unreadable labels.

---

# 256. Printing

Not MVP requirement.

But structured content should make future printable worksheets possible.

---

# 257. Content Offline Future

Question structure should support caching/mobile later.

No architecture dependency now.

---

# 258. Question IDs in User UI

Do not expose sequential database IDs as meaningful exam numbers.

Can show:

```text
Practice #12
```

separate from canonical code.

---

# 259. Canonical Code

Internal useful:

```text
EQ-LOG-03-000124
```

Stable for support/debugging.

---

# 260. Authoring Statistics

Track:

```text
draft → approval time
rejection reasons
defects by author/template
```

For process improvement, not punishment.

---

# 261. AI Draft Quality Analytics

Track:

```text
AI drafts accepted %
revision count
math defect rate
language defect rate
```

If AI saves no time:

stop using it in that workflow.

---

# 262. Expert Time Allocation

Use experts where AI/determinism weak:

- C-level reasoning;
- geometry;
- bilingual nuance;
- difficulty calibration;
- diagnostic anchors.

Use automation where strong:

- schema;
- arithmetic checks;
- duplicate checks;
- simple parametric generation.

---

# 263. Content Cost Model

Track internal:

```text
cost per approved question
cost per bilingual pair
review minutes
AI cost
defect rework cost
```

Useful for business planning.

---

# 264. Content Unit Economics

Subscription price must eventually cover:

- AI;
- infra;
- content amortization;
- support;
- acquisition.

Question Bank is investment, not one-time free asset.

---

# 265. Closed Beta Content Strategy

Do not tell beta students:

> «Вся математика полностью покрыта»

if not true.

Clearly scope supported topics.

---

# 266. Unsupported Skill UX

If user opens unsupported skill:

```text
Скоро
```

or not shown.

Do not generate random AI questions live just to fill gap.

---

# 267. Live AI Question Generation

Not permitted in Alpha student practice.

Why:

- correctness risk;
- unreviewed difficulty;
- mapping uncertainty;
- reproducibility;
- inconsistent learning evidence.

---

# 268. Future On-Demand Generation

Possible only with:

```text
validated generator
or
AI + deterministic validation + safety gate
```

and lower evidence weight until mature.

---

# 269. Question Bank Moat

Long-term defensibility:

```text
curated content
+
metadata
+
empirical calibration
+
error mappings
+
bilingual quality
+
learning outcome data
```

Not raw text count.

---

# 270. External Source Adapters

If future partner provides licensed content:

adapter maps:

```text
external item
→ canonical QuestionVersion
```

with source/license metadata.

---

# 271. Partner Content Isolation

Can tag:

```text
tenant/entitlement
```

later.

Not needed Alpha.

---

# 272. Content Removal

If license expires:

```text
not served new
```

Historical attempts remain with minimal necessary snapshot/provenance according to legal policy.

---

# 273. Mock Content Security

Do not expose answer endpoints before completion.

Server-side authorization.

---

# 274. Client Inspection

Assume user can inspect frontend/network.

Therefore answer must not be sent until allowed.

---

# 275. Practice Answer Security

Even practice answer hidden until submit/policy.

---

# 276. Question Randomization

Option order can randomize if semantics allow.

Store presented order with Attempt.

---

# 277. Matching Randomization

Store mapping presentation order.

---

# 278. Randomization Reproducibility

Session stores seed/presentation snapshot.

---

# 279. Content Schema Validation Example

```text
APPROVED requires:
source
primary skill
difficulty
format
language
stem
answer key
verified solution
reviewer
```

---

# 280. Question Without Solution

Can be:

```text
DRAFT
```

but not P0 Tutor-enabled APPROVED item.

---

# 281. Question Without KK

Can be RU-approved internally.

But if product promises bilingual supported skill:

not public for KK user.

---

# 282. Skill Without Enough Questions

Learning Engine gets:

```text
contentAvailabilityGate
```

and avoids overconfident practice.

---

# 283. Content Availability API

Planning needs:

```text
countEligibleItems(skill, language, difficulty, exclusions)
```

---

# 284. Avoid N+1 Content Query

Precompute/read model coverage counts.

---

# 285. Content Selection Performance

Question selection target:

```text
<100 ms DB-side
```

excluding complex future ranking.

---

# 286. Content Indexes

Likely:

```text
question_version(status, language, difficulty)
question_skill_link(skill_id, role)
question_family(family_id)
```

---

# 287. Content Data Model Additions

Recommended entities:

```text
QuestionFamily
QuestionAsset
ContentIssue
ContentReview
ContentApproval
QuestionEligibility
LocalizedTemplate
ParametricGeneratorDefinition
```

Not all mandatory first migration.

---

# 288. Minimal Alpha Tables

Must-have:

```text
questions
question_versions
question_skill_links
answer_keys
solutions
content_sources
content_issues
```

Hints can initially live in solution structured data if simpler.

---

# 289. Avoid Premature CMS Complexity

Admin UI can start simple.

Do not build:

- workflow engine;
- complex editorial calendar;
- rich collaboration suite.

---

# 290. First Authoring Format

Recommended:

```text
admin form + JSON import
```

enough.

---

# 291. Content Seeding

Initial approved bank can be imported from version-controlled JSON/YAML after review.

---

# 292. Seed IDs

Stable UUID/canonical codes.

Repeated seed does not duplicate.

---

# 293. Environments

Production bank separate from dev/test.

No test questions mixed accidentally.

---

# 294. Test Content Flag

```text
environment/testOnly
```

or separate DB seed.

---

# 295. Synthetic Students

Use synthetic data to test distribution.

Never publish fake empirical stats as real.

---

# 296. Alpha Content Milestones

## C0 — Pipeline Ready

- schema;
- admin/import;
- validator;
- versioning;
- review status.

## C1 — First 50

- end-to-end review process tested.

## C2 — 150

- algebra core usable.

## C3 — 300

- multiple P0 branches.

## C4 — 500+

- closed beta broad enough.

Exact release can occur earlier for narrow cohort.

---

# 297. Content Review Milestone

Before C2:

measure:

```text
defect rate
review time
translation bottleneck
```

Improve process before scaling.

---

# 298. Alpha Content Team Checklist

Every weekly cycle:

```text
1. identify coverage gaps
2. issue authoring briefs
3. create drafts
4. math review
5. bilingual review
6. approve
7. inspect empirical signals
8. fix defects
```

---

# 299. Do Not Batch 500 AI Questions First

This creates:

- review debt;
- duplicates;
- inconsistent difficulty;
- incorrect answers;
- bad translations;
- unusable metadata.

Create smaller validated batches.

---

# 300. Suggested Batch Size

```text
10–30 canonical questions
```

per review batch.

---

# 301. Content Research Backlog

Future experiments:

- IRT calibration;
- distractor diagnostics;
- family-level repetition discount;
- AI ambiguity detector;
- automated geometry proof checks;
- symbolic solution verification;
- difficulty prediction.

---

# 302. Quality Before Model Sophistication

A sophisticated Learning Engine with bad questions produces sophisticated wrong conclusions.

Therefore:

```text
Content Quality
>
Model Complexity
```

in early product stages.

---

# 303. Official References — Current Basis

The current mathematics specification states that the test content is based on the national secondary-education standard and defines the structure and content used to build the test-item bank.

ENT Math AI uses that specification as a curriculum blueprint, not as permission to copy protected test content.

---

# 304. NTC Trial Testing Context

NTC provides online trial testing through its official platforms.

ENT Math AI may accept user-generated result evidence from such trial testing when safely and legally provided.

The product should prefer:

```text
result/report import
```

over:

```text
collection of confidential/live exam questions
```

---

# 305. Current Public-Content Decision

For Alpha:

```text
PRIMARY:
original authored content

SECONDARY:
deterministic parametric content

OPTIONAL:
licensed/open content after rights review

AI:
draft assistant only

external uploads:
private assessment evidence only
```

This is the safest and most strategically defensible content model.

---

# 306. Definition of Content Done

A QuestionVersion is production-ready only if:

- [ ] legal/source provenance exists;
- [ ] curriculum mapping exists;
- [ ] exactly one primary skill;
- [ ] difficulty assigned;
- [ ] format valid;
- [ ] AnswerKey verified;
- [ ] solution verified;
- [ ] RU/KK status appropriate;
- [ ] structural validation passes;
- [ ] no critical ambiguity;
- [ ] assets valid;
- [ ] reviewer approval recorded;
- [ ] question is not a prohibited external/live exam leak.

---

# 307. Alpha Success Criteria — Content

Content layer is ready for Closed Beta when:

- core P0 paths have usable coverage;
- no known critical AnswerKey defects;
- KK/RU supported scope is genuinely usable;
- Question Selection can avoid excessive repetition;
- Tutor has verified solution context;
- Diagnostic has anchor pool;
- Mock can be assembled from holdout-quality items;
- defect/report workflow works;
- provenance exists for every public item.

---

# 308. Source References — Checked October 2026

## National Testing Center — Mathematics Specification

Current official mathematics specification for ENT usage from 2026.

https://testcenter.kz/wp-content/uploads/2025/10/10_%D0%9C%D0%B0%D1%82%D0%B5%D0%BC%D0%B0%D1%82%D0%B8%D0%BA%D0%B0_%D1%80%D1%83%D1%81.pdf

## National Testing Center — Preparation

NTC states that preparation should rely on subject specifications and provides official online trial testing.

https://new.testcenter.kz/?page_id=23376

## NTC — Testing Conduct

Current conduct rules prohibit taking testing materials out of the testing room and discussing/disclosing test-item content during testing.

https://testcenter.kz/?lang=ru&page_id=15582

---

# 309. Final Content Principle

> **Мы не строим огромный склад задач.  
> Мы строим измерительный и обучающий инструмент.**

Каждое задание должно отвечать на вопрос:

```text
Что именно оно измеряет?
Почему ответ считается правильным?
Насколько мы этому доверяем?
Что Learning Engine узнает после попытки?
Как Tutor может помочь, не разрушив learning signal?
```

Если на эти вопросы нет ответа, вопрос ещё не готов к production.

---

# 310. Next Document

Следующий обязательный документ:

> **`07_MVP_SCOPE.md`**

Он должен превратить все предыдущие документы в жёстко ограниченный продуктовый Alpha/MVP:

```text
exact user journeys
exact features
exact non-goals
screens
MVP modules
supported skills
import scope
AI Tutor scope
admin scope
acceptance criteria
release gates
```

После этого можно будет перестать проектировать «идеальную платформу» и определить, что именно мы реально строим первой версией.
