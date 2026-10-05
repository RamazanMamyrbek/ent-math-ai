# 04_LEARNING_ENGINE.md
## ENT Math AI — Learning Engine Specification

> **Статус:** canonical algorithm specification  
> **Версия:** `LEARNING-ENGINE-v1`  
> **Дата:** 5 октября 2026  
> **Связанные документы:**  
> `00_CONCEPT.md`  
> `02_CURRICULUM_MAP.md`  
> `03_DOMAIN.md`  
> `ENT_Math_AI_Technical_Specification_v1.md`  
>
> **Назначение:** формально определить, как ENT Math AI превращает ответы, пробники и внешние результаты ученика в:
>
> - Learning Evidence;
> - Skill Mastery;
> - Confidence;
> - Review State;
> - prerequisite diagnosis;
> - Next-Best-Action;
> - Daily Mission;
> - readiness / score estimation.
>
> Этот документ является source of truth для `learning` и `planning` модулей.

---

# 1. Главный принцип

Learning Engine не должен быть «магическим AI».

Его первая production-версия должна быть:

```text
детерминированной
+
объяснимой
+
версионируемой
+
воспроизводимой
+
тестируемой
+
калибруемой на реальных данных
```

LLM не участвует непосредственно в расчёте Mastery.

Главная цепочка:

```text
Student Activity
      +
External Assessments
      ↓
Normalized Learning Evidence
      ↓
Skill Mastery Estimate
      +
Confidence
      +
Retrievability
      ↓
Learning State
      ↓
Recommendation Engine
      ↓
Daily Mission
      ↓
New Activity
      ↺
```

---

# 2. Почему не начинать с Deep Learning

Существует большой класс Knowledge Tracing моделей:

- Bayesian Knowledge Tracing;
- Performance Factor Analysis;
- Item Response Theory;
- Deep Knowledge Tracing;
- Transformer-based knowledge tracing;
- hybrid models.

Но Alpha имеет:

- мало собственных данных;
- новый curriculum graph;
- смешанные источники evidence;
- необходимость explainability;
- необходимость быстро менять правила;
- повышенную цену скрытой ошибки.

Поэтому `v1` использует простую probabilistic evidence model.

Правило проекта:

> **Сложность модели должна появляться после данных, а не вместо данных.**

---

# 3. Научная позиция v1

Классический Bayesian Knowledge Tracing моделирует скрытое состояние знания ученика и обновляет вероятность знания по наблюдаемым ответам.

Deep Knowledge Tracing показывает возможность изучать более сложные зависимости последовательностей из данных.

Однако для Alpha нам важнее:

- прозрачность;
- возможность смешивать источники;
- provenance;
- ручная проверяемость;
- простое offline replay.

Поэтому мы используем собственную **Weighted Evidence Mastery Model** как baseline.

Позднее она обязана сравниваться offline с:

```text
BKT
PFA
IRT / Rasch-like item model
DKT / transformer KT
```

по реальным данным.

---

# 4. Learning State состоит не из одного числа

Для каждого Skill система хранит/вычисляет минимум:

```text
Mastery
Confidence
Retrievability
Stability
Evidence Mass
Last Evidence
Next Review
Error Profile
Disagreement Signals
```

Это важно.

Пример:

```text
Student A

mastery       = 0.82
confidence    = 0.91
retrievability= 0.42
```

Интерпретация:

> навык хорошо освоен, но давно не извлекался из памяти и требует повторения.

Другой пример:

```text
Student B

mastery       = 0.82
confidence    = 0.18
retrievability= 0.95
```

Интерпретация:

> текущие данные выглядят хорошо, но доказательств слишком мало.

Одинаковый `0.82` не означает одинаковое состояние.

---

# 5. Основные обозначения

Для skill `s` и evidence `i`:

```text
y_i  — normalized outcome ∈ [0,1]
w_i  — effective evidence weight
m_s  — mastery estimate ∈ [0,1]
c_s  — confidence ∈ [0,1]
R_s  — retrievability ∈ [0,1]
S_s  — memory stability in days
```

Дополнительные коэффициенты:

```text
r_i  — source reliability
x_i  — extraction confidence
g_i  — mapping confidence
q_i  — item quality
d_i  — difficulty evidence multiplier
a_i  — assistance multiplier
p_i  — repetition discount
```

---

# 6. Evidence Granularity

Learning Engine различает три precision levels:

```text
ITEM_SKILL
TOPIC_AGGREGATE
SUBJECT_TOTAL
```

## ITEM_SKILL

Можно обновлять granular SkillMastery.

## TOPIC_AGGREGATE

Нельзя искусственно разбрасывать результат на дочерние skills.

Используется для:

- topic confidence;
- targeted diagnostic;
- disagreement detection.

## SUBJECT_TOTAL

Используется для:

- readiness;
- score calibration;
- global progress.

Не обновляет granular mastery напрямую.

---

# 7. Evidence Types

Canonical types:

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

---

# 8. Source Reliability v1

Начальная policy:

| Evidence source | Reliability |
|---|---:|
| Internal Mock Item | 1.00 |
| Internal Independent Attempt | 0.95 |
| Official Structured Result | 0.95 |
| Official Document Import | 0.90 |
| Partner Verified Result | 0.90 |
| Paper Mock + Answer Key | 0.80 |
| External Item Result | 0.75 |
| External Topic Summary | 0.65 |
| Paper Mock without Key | 0.55 |
| Self-Reported Result | 0.25 |

Это **начальная продуктовая гипотеза**, а не научная константа.

Все значения принадлежат:

```text
SourceReliabilityPolicy v1
```

и могут быть перекалиброваны.

---

# 9. Extraction Confidence

Для internal activity:

```text
x_i = 1
```

Для AI-import:

```text
x_i ∈ [0,1]
```

Если extraction требует user correction:

после подтверждения:

```text
x_i = max(original_confidence, CONFIRMED_FLOOR)
```

Начальная гипотеза:

```text
CONFIRMED_FLOOR = 0.95
```

Потому что подтверждённое человеком поле уже не является чисто AI inference.

---

# 10. Mapping Confidence

Internal curated Question:

```text
g_i = 1
```

External question classified to Skill:

```text
g_i ∈ [0,1]
```

Правило:

```text
g_i < 0.70
→ НЕ создавать granular ITEM_SKILL evidence автоматически
```

Вместо этого:

- user/expert review;
- map to higher topic;
- targeted diagnostic.

---

# 11. Item Quality

Approved internal question:

начально:

```text
q_i = 1.0
```

После накопления data quality может учитывать:

- item defect history;
- discrimination;
- ambiguity reports;
- empirical fit;
- unusual response pattern.

External unknown question:

```text
q_i < 1
```

по policy.

Не использовать popularity как quality.

---

# 12. Outcome Normalization

Для binary item:

```text
correct   → y = 1
incorrect → y = 0
```

Для partial-scoring formats:

```text
y = earned_points / max_points
```

Но partial policy должна быть одинаковой с AnswerValidator.

Пример MATCHING:

```text
3/4 correct pairs
→ y = 0.75
```

если официальный scoring / internal policy разрешает partial credit.

---

# 13. Assistance Policy

Correct answer после подсказки не равен самостоятельному correct.

Начальная схема:

| Highest assistance | Outcome cap | Assistance weight |
|---|---:|---:|
| NONE | 1.00 | 1.00 |
| HINT_1 | 0.95 | 0.90 |
| HINT_2 | 0.85 | 0.80 |
| GUIDED_STEP | 0.75 | 0.65 |
| CONCEPT_EXPLANATION | 0.70 | 0.55 |
| FULL_SOLUTION | no positive success evidence | 0.00 |

Правило:

> просмотр полного решения **не создаёт положительное evidence за этот же item**.

Если до просмотра был incorrect attempt:

incorrect evidence сохраняется.

После решения похожего near-transfer item самостоятельно:

создаётся новое положительное evidence.

---

# 14. Why Full Solution Is Not `y = 0`

Просмотр решения сам по себе не означает:

> ученик ничего не знает.

Поэтому:

```text
FULL_SOLUTION
```

не добавляет искусственный failure.

Он:

1. блокирует positive success evidence текущего item;
2. создаёт pedagogical state;
3. запускает recovery loop;
4. требует independent near-transfer check.

---

# 15. Repetition Discount

Повтор одного и того же Question не должен бесконечно повышать mastery.

Для положительного evidence:

```text
1st independent exposure → p = 1.00
2nd exposure             → p = 0.50
3rd exposure             → p = 0.25
4+                       → p = 0.10
```

Если вопрос повторён слишком скоро:

может быть:

```text
p = 0
```

для mastery gain.

Повтор всё ещё может использоваться педагогически, но не как сильное доказательство знания.

---

# 16. Difficulty Evidence Multiplier

Сложность должна влиять **асимметрично**.

Correct C — сильнее положительный сигнал.

Incorrect A — сильнее отрицательный сигнал.

## Positive evidence

| Difficulty | multiplier |
|---|---:|
| A | 0.80 |
| B | 1.00 |
| C | 1.20 |

## Negative evidence

| Difficulty | multiplier |
|---|---:|
| A | 1.20 |
| B | 1.00 |
| C | 0.80 |

Почему:

```text
correct on easy
≠ strong proof of advanced mastery

incorrect on very hard
≠ strong proof of complete ignorance
```

После появления empirical item calibration эти значения заменяются.

---

# 17. Effective Evidence Weight

Для item-level evidence:

```text
w_i =
r_i
× x_i
× g_i
× q_i
× d_i
× a_i
× p_i
```

Weight ограничивается:

```text
0 ≤ w_i ≤ 1.25
```

Чтобы один item не мог полностью перевернуть skill state.

---

# 18. Internal Attempt Example

Ученик:

```text
Question difficulty = B
correct = true
no hints
first exposure
```

Параметры:

```text
r = 0.95
x = 1
g = 1
q = 1
d = 1
a = 1
p = 1
```

Получаем:

```text
w = 0.95
y = 1
```

---

# 19. Assisted Attempt Example

```text
difficulty = B
correct
HINT_2
```

```text
r = 0.95
a = 0.80
outcome capped at 0.85
```

Итого:

```text
w ≈ 0.76
y = 0.85
```

Это положительное, но слабее самостоятельного доказательства.

---

# 20. Wrong Easy Question Example

```text
difficulty = A
incorrect
independent
```

```text
d = 1.20
```

Такой failure сильнее влияет на posterior, чем ошибка на C-level item.

---

# 21. Mastery Model v1

Используется Weighted Beta evidence model.

Prior:

```text
alpha_0 = 1
beta_0  = 1
```

Для skill `s`:

```text
alpha_s =
alpha_0 + Σ(w_i × y_i)

beta_s =
beta_0 + Σ(w_i × (1 - y_i))
```

Mastery:

```text
m_s =
alpha_s / (alpha_s + beta_s)
```

---

# 22. Почему Beta Model

Плюсы:

- понятен;
- incremental;
- поддерживает fractional evidence;
- легко replay;
- легко тестировать;
- confidence можно отделить;
- нет training pipeline;
- подходит как baseline.

Минусы:

- не моделирует последовательность так богато, как BKT/DKT;
- не моделирует индивидуальную ability отдельно;
- item difficulty пока heuristic;
- forgetting вынесен отдельно.

Это сознательный tradeoff.

---

# 23. Mastery не decay'ится напрямую

Очень важное решение.

Мы НЕ делаем:

```text
mastery_today =
mastery_yesterday × 0.99
```

Почему:

старение данных означает не обязательно:

> ученик разучился.

Это означает:

> мы менее уверены в актуальной retrievability.

Поэтому forgetting моделируется отдельным Review State.

---

# 24. Evidence Window

По умолчанию Mastery posterior использует все valid item-level evidence.

Но historical evidence может быть downweighted policy при доказанной curriculum drift / content issue.

Для `v1`:

```text
NO time decay in mastery posterior
```

Время учитывается через:

```text
Retrievability
Review State
Recent Validation
```

Это делает модель более интерпретируемой.

---

# 25. Evidence Mass

```text
E_s = Σ(w_i)
```

Это не количество вопросов.

Пример:

```text
10 low-confidence external signals
```

могут иметь меньший evidence mass, чем:

```text
5 clean independent internal attempts
```

---

# 26. Confidence v1

Начальная формула:

```text
c_s = 1 - exp(-E_s / K)
```

где:

```text
K = 5
```

Примеры:

```text
E = 1  → c ≈ 0.18
E = 3  → c ≈ 0.45
E = 5  → c ≈ 0.63
E = 10 → c ≈ 0.86
E = 15 → c ≈ 0.95
```

Зачем:

confidence растёт быстро в начале, затем насыщается.

---

# 27. Confidence Labels

```text
0.00–0.24  VERY_LOW
0.25–0.49  LOW
0.50–0.74  MEDIUM
0.75–0.89  HIGH
0.90–1.00  VERY_HIGH
```

UI не обязан показывать эти technical labels напрямую.

---

# 28. Mastery Labels

Только UI/read model:

```text
m < 0.40       EMERGING
0.40–0.64      DEVELOPING
0.65–0.84      STRONG
m >= 0.85      MASTERY_LIKE
```

Но:

```text
MASTERY_LIKE
```

разрешён только при:

```text
confidence >= 0.70
```

и adequate recent retrieval evidence.

Иначе:

```text
PROVISIONALLY_STRONG
```

---

# 29. Unknown Skill

Если:

```text
confidence < 0.20
```

skill считается:

```text
UNKNOWN
```

Даже если posterior mean случайно высокий.

Это предотвращает:

```text
1 correct answer
→ "ты освоил тему"
```

---

# 30. Separate Memory Model

Для каждого skill поддерживается:

```text
ReviewState
- stabilityDays
- lastIndependentRetrievalAt
- retrievability
- nextReviewAt
- successfulReviewCount
```

Это отдельная система от Mastery.

---

# 31. Retrievability v1

Определяем `S` как интервал в днях, при котором ожидаемая retrievability остаётся около 90%.

Формула v1:

```text
R(t, S) = 0.9 ^ (t / S)
```

где:

```text
t = days since last successful independent retrieval
S = stabilityDays
```

Свойство:

```text
R(S,S) = 0.90
```

---

# 32. Initial Stability

После первого уверенного самостоятельного correct:

```text
S = 2 days
```

После assisted correct:

```text
S = 1 day
```

После failure:

не создавать иллюзию стабильности.

---

# 33. Review Grade

Система сама выводит grade.

```text
AGAIN
HARD
GOOD
EASY
```

### AGAIN

- incorrect;
- или failed retrieval.

### HARD

- correct с hint;
- или значительно медленнее expected;
- или correct после нескольких attempts.

### GOOD

- independent correct;
- нормальное время.

### EASY

- independent correct;
- higher-than-target difficulty;
- без помощи;
- быстро, но не подозрительно быстро.

---

# 34. Stability Update v1

Начальные multipliers:

```text
AGAIN:
S_new = max(1, S_old × 0.50)

HARD:
S_new = max(1, S_old × 1.30)

GOOD:
S_new = max(2, S_old × 2.00)

EASY:
S_new = max(3, S_old × 2.50)
```

Cap Alpha:

```text
S <= 120 days
```

Это heuristic baseline.

Позднее заменить на calibrated scheduler / FSRS-like model при достаточных данных.

---

# 35. Next Review

Target retrievability:

```text
R_target = 0.90
```

Так как по definition `R(S,S)=0.9`:

```text
nextReviewAt =
lastIndependentRetrievalAt + S days
```

Для слабых skills engine может review раньше.

---

# 36. Why Not Full FSRS in v1

FSRS является сильным современным spaced-repetition scheduler и отдельно моделирует:

- difficulty;
- stability;
- retrievability.

Наша модель использует похожее концептуальное разделение.

Но ENT Math отличается:

- question != flashcard;
- один skill имеет много items;
- difficulty относится к item;
- transfer важнее буквального recall;
- подсказки и multi-step reasoning важны.

Поэтому сначала используем более простой skill-level ReviewState.

FSRS-like calibration — future experiment, не dependency Alpha.

---

# 37. Effective Learning Readiness

Для planning можно использовать:

```text
effective_readiness =
mastery × retrievability
```

Но это **не сохраняется как новый mastery**.

Пример:

```text
mastery = 0.90
R = 0.60

effective readiness = 0.54
```

Интерпретация:

> знание было сильным, но сейчас retrieval нуждается в проверке.

---

# 38. Review Urgency

```text
reviewUrgency =
1 - R
```

Clamp:

```text
[0,1]
```

Если review overdue:

добавляется overdue bonus.

---

# 39. External Topic Evidence

Пример:

```text
Official report:
Trigonometry = 4/7
```

Если нет item-level mapping:

НЕ делаем:

```text
TRIG-01 = 4/7
TRIG-02 = 4/7
TRIG-03 = 4/7
...
```

Создаём:

```text
TOPIC_AGGREGATE evidence
```

Он влияет на:

- topic assessment state;
- targeted diagnostic priority;
- disagreement detection.

Не напрямую на granular Mastery.

---

# 40. Topic Mastery Projection

Для UI:

```text
TopicMastery =
Σ(m_s × c_s × v_s)
/
Σ(c_s × v_s)
```

где:

```text
v_s = curriculum aggregation weight
```

До появления официальных/эмпирических весов:

```text
v_s = 1
```

---

# 41. Topic Coverage

```text
TopicCoverage =
Σ(c_s × v_s)
/
Σ(v_s)
```

Если:

```text
coverage < 0.40
```

UI не должен показывать topic mastery как точный вывод.

Можно:

```text
"Недостаточно данных"
```

---

# 42. External Aggregate Consistency Check

External topic result можно сравнить с internal topic projection.

Например:

```text
internal = 0.78
external = 0.42
difference = 0.36
```

Это создаёт возможный disagreement.

---

# 43. Learning Disagreement v1

Trigger:

```text
abs(internal - external) >= 0.25
```

при:

```text
internal confidence >= 0.60
external reliability >= 0.65
```

Severity:

```text
0.25–0.34 → MEDIUM
0.35–0.49 → HIGH
>= 0.50   → CRITICAL
```

Это начальные thresholds.

---

# 44. Что делать при disagreement

Не усреднять слепо.

Planning Engine создаёт:

```text
VALIDATION MISSION
```

Пример:

```text
5–8 mixed no-hint items
из спорной области
```

После:

- обновляет skill evidence;
- проверяет time pressure;
- проверяет transfer;
- resolution signal.

---

# 45. Error Recurrence Score

Для каждого error type/skill:

```text
recent_errors =
weighted count over last 30 days
```

Начальный recency weighting:

```text
last 7 days  → 1.0
8–14 days    → 0.7
15–30 days   → 0.4
>30 days     → 0.1
```

Normalize:

```text
errorRecurrence ∈ [0,1]
```

---

# 46. Candidate Skill Pool

Recommendation Engine сначала строит candidates:

```text
1. active curriculum skills
2. content available
3. relevant to active goal
4. not temporarily blocked
```

Дополнительно candidates из:

- overdue reviews;
- prerequisite gaps;
- external disagreement;
- diagnostic uncertainty.

---

# 47. Core Skill Need Factors

Для skill `s`:

```text
gap_s = 1 - mastery_s
uncertainty_s = 1 - confidence_s
review_s = 1 - retrievability_s
disagreement_s ∈ [0,1]
error_s ∈ [0,1]
examRelevance_s ∈ [0,1]
```

---

# 48. Exam Relevance v1

Важно:

в официальной спецификации нет granular frequency для каждого нашего Skill.

Поэтому нельзя выдумывать:

```text
TRIG = 17%
LOG = 8%
```

`v1`:

```text
examRelevance = 0.5
```

нейтрально для всех granular skills в допустимом curriculum.

Допустимые overrides:

- официальный blueprint;
- надёжная большая эмпирическая выборка;
- expert policy с явной маркировкой.

---

# 49. Recommendation Priority v1

Base score:

```text
P_base =
0.35 × gap
+
0.20 × reviewUrgency
+
0.15 × uncertainty
+
0.15 × disagreement
+
0.10 × examRelevance
+
0.05 × errorRecurrence
```

Все factors:

```text
[0,1]
```

---

# 50. Почему additive, а не multiplicative

Плохая формула:

```text
gap × review × uncertainty × ...
```

Если один factor = 0:

весь priority = 0.

Additive model:

- проще объяснять;
- проще калибровать;
- не уничтожает отдельный сильный сигнал.

---

# 51. Content Availability Gate

Если для skill нет достаточного approved content:

```text
availabilityGate < 1
```

Если:

```text
0 usable questions
```

skill нельзя назначить обычной practice activity.

Можно назначить:

- diagnostic pending content;
- external/manual learning recommendation;
- later content production signal.

Alpha не должен ломаться из-за неполного coverage.

---

# 52. Prerequisite Gate

Для REQUIRED prerequisites берём:

```text
p_min = minimum prerequisite mastery
```

Policy:

```text
p_min >= 0.55 → gate = 1.00

0.40–0.54
→ gate = 0.60

< 0.40
→ gate = 0.25
```

Target skill не блокируется полностью.

Но prerequisite получает boost.

---

# 53. Prerequisite Boost

Если target high priority, но prerequisite слаб:

```text
prerequisiteBoost =
0.20 + 0.50 × targetPriority
```

Clamp:

```text
<= 0.40 absolute boost
```

Это предотвращает бесконечное «учим базу и никогда не возвращаемся к цели».

---

# 54. Final Priority

```text
P_final =
P_base
× prerequisiteGate
× contentAvailabilityGate
+ prerequisiteBoost
```

Clamp:

```text
[0,1]
```

---

# 55. Recommendation Reason Codes

Engine обязан сохранить причины:

```text
WEAK_SKILL
SPACED_REVIEW
HIGH_UNCERTAINTY
PREREQUISITE_GAP
EXTERNAL_INTERNAL_DISAGREEMENT
RECURRENT_ERROR
MIXED_TRANSFER_CHECK
```

UI explanation строится из deterministic reason.

LLM не придумывает причину recommendation.

---

# 56. Example Recommendation

```text
Skill: EQ-TRIG-03

mastery        = 0.38
confidence     = 0.81
R              = 0.72
disagreement   = 0
errorRecurrence= 0.40
examRelevance  = 0.50
```

Factors:

```text
gap        = 0.62
review     = 0.28
uncertainty= 0.19
```

Base:

```text
0.35*0.62
+0.20*0.28
+0.15*0.19
+0.15*0
+0.10*0.50
+0.05*0.40

≈ 0.372
```

Но:

```text
EQ-QUAD-03 mastery = 0.31
```

REQUIRED prerequisite gate:

```text
0.25
```

И prerequisite получает boost.

Система объясняет:

> «Сложные тригонометрические уравнения остаются слабой темой, но сначала нужно укрепить квадратную замену.»

---

# 57. Question Selection

После выбора Skill нужно выбрать item.

Filter:

```text
APPROVED
correct curriculum
correct language
correct primary skill
allowed format
not defective
not too recently exposed
```

---

# 58. Difficulty Target

Начальная policy:

```text
mastery < 0.35
→ 80% A, 20% B

0.35–0.64
→ 40% A, 55% B, 5% C

0.65–0.84
→ 15% A, 60% B, 25% C

>= 0.85
→ 10% A, 45% B, 45% C
```

Если confidence low:

немного больше diagnostic variety.

---

# 59. Desired Success Rate

Для learning practice:

целевой диапазон:

```text
65–85% success
```

Необходимо калибровать.

Слишком легко:

```text
95–100%
→ мало learning signal
```

Слишком сложно:

```text
<40%
→ frustration / poor scaffolding risk
```

---

# 60. Item Exposure Penalty

Ranking снижает вопросы:

```text
seen today
seen yesterday
seen multiple times recently
```

И повышает:

```text
unseen
not seen for long time
same skill, different representation
```

---

# 61. Transfer Preference

После нескольких correct на одном шаблоне engine выбирает:

```text
different surface form
different context
mixed representation
```

чтобы проверить transfer, а не memorization pattern.

---

# 62. Mixed Practice

Mixed item/session включается, когда:

```text
mastery >= 0.65
```

или перед mock.

Цель:

- method selection;
- topic recognition;
- transfer;
- exam-like context.

---

# 63. Recovery Loop

После failure:

```text
wrong
 ↓
classify / inspect
 ↓
hint or concept explanation
 ↓
worked/guided example
 ↓
near-transfer item
 ↓
independent transfer item
 ↓
new evidence
```

Full solution текущего item не завершает remediation.

---

# 64. Consecutive Failure Guard

Если ученик получает:

```text
3 failures подряд
```

в одном skill:

не продолжать бездумно drill.

Engine выбирает:

```text
check prerequisite
OR
lower difficulty
OR
concept explanation
OR
short break / switch
```

---

# 65. Frustration Guard

В одной Daily Mission не должно быть:

```text
> 40% прогнозируемо очень трудных items
```

для обычного режима.

Mock — исключение.

---

# 66. Daily Mission Budget

Input:

```text
dailyMinutes
```

Engine использует estimated item times.

Target:

```text
estimated mission time
<= dailyMinutes × 1.10
```

Не давать 50 минут пользователю с budget 30.

---

# 67. Daily Mission Composition by Exam Horizon

## > 90 дней

```text
55% weak/new skill work
25% spaced review
20% mixed/validation
```

## 31–90 дней

```text
45% weak skill
25% review
30% mixed/validation
```

## 8–30 дней

```text
30% weak/high-value
25% review
45% mixed/mock-oriented
```

## <= 7 дней

```text
15% targeted gap
30% review
55% mixed/mock/error prevention
```

Новые тяжёлые ветви в последнюю неделю назначаются осторожно.

---

# 68. Daily Mission Item Types

```text
TARGETED_PRACTICE
PREREQUISITE_CHECK
SPACED_REVIEW
ERROR_REPAIR
MIXED_CHECK
MINI_TEST
MOCK_BLOCK
```

---

# 69. Diversity Constraint

Mission не должна состоять из:

```text
20 одинаковых задач
```

Ограничение:

```text
обычно <= 60% времени на один granular skill
```

Исключение:

targeted remediation session.

---

# 70. Session Completion

Mission считается completed не только по времени.

Минимум:

```text
required core activities completed
```

Skipped items сохраняются.

Не давать fake completion за открытие страницы.

---

# 71. Diagnostic v1 — Goal

Получить:

```text
полезную initial map
```

за:

```text
15–25 минут
```

Не пытаться доказать все 127 skills.

---

# 72. Diagnostic Stage 1 — Anchors

Выбираем:

```text
10–12 anchor items
```

по major branches.

Хороший anchor:

- high content quality;
- хороший coverage signal;
- moderate difficulty;
- минимальная ambiguity;
- желательно B или strong A.

---

# 73. Diagnostic Stage 2 — Targeted Branching

После anchors:

engine выбирает:

```text
4–8 items
```

туда, где:

```text
uncertainty high
AND
branch important for prerequisite graph
```

---

# 74. Diagnostic Stop Rule

Остановить если:

```text
items >= MAX_DIAGNOSTIC_ITEMS
OR
elapsed >= 25 min
OR
marginal uncertainty reduction low
```

Alpha:

```text
MAX_DIAGNOSTIC_ITEMS = 20
```

---

# 75. Imported Assessment Cold Start

Если пользователь импортировал item-level reliable assessment:

часть diagnostic можно пропустить.

Если только:

```text
29/40 total
```

skill map всё ещё unknown.

Engine предлагает short diagnostic.

---

# 76. Readiness ≠ Mastery

Readiness — оценка экзаменационного результата.

Mastery — skill-level learning state.

Нельзя:

```text
average mastery × 40
```

и назвать score prediction.

---

# 77. Readiness Evidence

Для `ScoreRange` используются assessments, максимально похожие на настоящий exam:

```text
INTERNAL_FULL_MOCK
OFFICIAL_MOCK
OFFICIAL_RESULT
VERIFIED_EXTERNAL_FULL_MOCK
```

Topic quiz не является полноценным score calibrator.

---

# 78. Assessment Similarity

Начальная policy:

| Assessment | Similarity |
|---|---:|
| Official result | 1.00 |
| Official mock | 0.95 |
| Internal full-format mock | 0.90 |
| Verified external full mock | 0.80 |
| Tutor-created partial mock | 0.55 |
| Topic test | 0.20 |

---

# 79. Readiness Weight

Для assessment `j`:

```text
W_j =
sourceReliability
× examSimilarity
× recencyWeight
```

Recency:

```text
recencyWeight =
exp(-ageDays / 60)
```

Это readiness-specific decay, не mastery decay.

---

# 80. Minimum Evidence for Score Range

До:

```text
2 sufficiently reliable full/mixed assessments
```

не показываем «прогноз».

Показываем:

```text
Latest Score
Readiness Index
Need more data
```

---

# 81. Score Estimate v1

Для eligible assessments:

```text
mu =
Σ(W_j × normalizedScore_j)
/
Σ(W_j)
```

Где normalizedScore приводится к одной official point scale.

---

# 82. Score Variance

```text
variance =
Σ(W_j × (score_j - mu)^2)
/
Σ(W_j)
```

```text
sd = sqrt(variance)
```

---

# 83. Score Range v1

Heuristic interval:

```text
margin =
max(
  MIN_MARGIN,
  1.5 × sd
)
```

Начально:

```text
MIN_MARGIN = 3 official points
```

Если eligible assessments < 3:

```text
margin = max(margin, 4)
```

Range:

```text
[mu - margin, mu + margin]
```

clamped to official score limits.

---

# 84. Why Range, Not Exact Score

Потому что:

- разные варианты;
- test anxiety;
- time pressure;
- luck;
- question distribution;
- limited evidence.

UI:

```text
27–31
```

лучше, чем:

```text
29.37
```

---

# 85. Readiness Confidence

Учитывает:

```text
number of calibrating assessments
source quality
recency
score consistency
format similarity
```

Может быть:

```text
LOW
MEDIUM
HIGH
```

---

# 86. Mastery-Informed Score — Not Yet

В `v1` granular mastery НЕ модифицирует напрямую score forecast.

Почему:

мы ещё не знаем реальную empirical mapping:

```text
skill mastery vector
→ official score
```

После Beta можно обучить/calibrate model.

---

# 87. Future IRT

Item Response Theory можно использовать позднее для:

- empirical item difficulty;
- discrimination;
- ability estimate;
- adaptive diagnostic.

Не внедрять IRT до:

- достаточного number of responses per item;
- stable item bank;
- psychometric validation.

---

# 88. Future BKT

BKT candidate для skill-level temporal model.

Нужно сравнивать:

```text
Weighted Evidence v1
vs
BKT
```

Metrics:

- next-item prediction;
- calibration;
- interpretability;
- robustness to assisted attempts;
- external evidence integration.

---

# 89. Future DKT / Transformer KT

Разрешено только после:

- большой event dataset;
- clear offline improvement;
- no calibration regression;
- explainability strategy;
- privacy review.

Нельзя внедрять потому, что «AI продукт должен иметь нейросеть».

---

# 90. Model Evaluation — Next Item Prediction

Для historical replay:

предсказываем вероятность correct следующего independent item.

Metrics:

```text
Brier Score
Log Loss
AUC
Calibration Error
```

AUC одного недостаточно.

---

# 91. Calibration

Если модель говорит:

```text
P(correct)=0.8
```

то на большой выборке таких случаев correct rate должен быть близок к 80%.

Нужны reliability diagrams.

---

# 92. Learning Outcome Evaluation

Самая важная offline prediction metric не равна product success.

Также измеряем:

```text
pre/post mock gain
retention
transfer performance
time-to-mastery
error recurrence
```

---

# 93. Recommendation Evaluation

Сравниваем cohorts / A/B:

```text
adaptive recommendation
vs
reasonable baseline
```

Outcome:

- learning gain;
- retention;
- completion;
- frustration;
- time efficiency.

---

# 94. Baseline Recommendation

Нужен честный baseline:

```text
weakest-skill-first
+
fixed spaced review
```

Если сложный algorithm не выигрывает baseline — он не нужен.

---

# 95. Algorithm Versioning

Каждое вычисление связано с:

```text
mastery-v1
confidence-v1
review-v1
recommendation-v1
readiness-v1
```

---

# 96. Replayability

Нужен offline command:

```text
rebuild student learning state
```

Input:

```text
all ACTIVE LearningEvidence
+ policy versions
```

Output:

```text
SkillMasteryProjection
ReviewState
TopicProjection
```

Результат при одинаковом input должен быть deterministic.

---

# 97. Policy Configuration

Не hard-code в 50 Java классах.

Versioned configuration:

```yaml
mastery:
  priorAlpha: 1.0
  priorBeta: 1.0
  confidenceK: 5.0

recommendation:
  gapWeight: 0.35
  reviewWeight: 0.20
  uncertaintyWeight: 0.15
  disagreementWeight: 0.15
  examWeight: 0.10
  errorWeight: 0.05
```

После release policy immutable.

Новая настройка:

```text
recommendation-v2
```

---

# 98. Pseudocode — Evidence to Mastery

```text
function calculateMastery(studentId, skillId):

    evidence = loadActiveItemSkillEvidence(studentId, skillId)

    alpha = 1.0
    beta  = 1.0
    mass  = 0.0

    for e in evidence:
        w = effectiveWeight(e)
        y = normalizedOutcome(e)

        alpha += w * y
        beta  += w * (1 - y)
        mass  += w

    mastery = alpha / (alpha + beta)
    confidence = 1 - exp(-mass / 5.0)

    return MasteryProjection(
        mastery,
        confidence,
        mass
    )
```

---

# 99. Pseudocode — Weight

```text
function effectiveWeight(e):

    weight =
        e.sourceReliability
      * e.extractionConfidence
      * e.mappingConfidence
      * e.itemQuality
      * difficultyMultiplier(e)
      * assistanceMultiplier(e)
      * repetitionDiscount(e)

    return clamp(weight, 0, 1.25)
```

---

# 100. Pseudocode — Retrievability

```text
function retrievability(reviewState, now):

    if reviewState.lastIndependentRetrievalAt == null:
        return 0

    t = daysBetween(
        reviewState.lastIndependentRetrievalAt,
        now
    )

    S = max(reviewState.stabilityDays, 1)

    return pow(0.9, t / S)
```

---

# 101. Pseudocode — Recommendation

```text
function priority(skillState):

    gap = 1 - mastery
    review = 1 - retrievability
    uncertainty = 1 - confidence

    base =
        0.35 * gap
      + 0.20 * review
      + 0.15 * uncertainty
      + 0.15 * disagreement
      + 0.10 * examRelevance
      + 0.05 * errorRecurrence

    gate = prerequisiteGate(skillState)
    availability = contentAvailability(skillState)

    boost = prerequisiteBoostIfApplicable(skillState)

    return clamp(
        base * gate * availability + boost,
        0,
        1
    )
```

---

# 102. Pseudocode — Daily Mission

```text
function buildDailyMission(student, date):

    budget = student.dailyMinutes
    horizon = daysUntilExam(student.goal)

    composition = compositionFor(horizon)

    candidates = rankSkills(student)

    plan = []

    fill(plan, REVIEW, composition.review, budget)
    fill(plan, TARGETED, composition.targeted, budget)
    fill(plan, MIXED, composition.mixed, budget)

    applyDiversityConstraint(plan)
    applyFrustrationGuard(plan)
    trimToBudget(plan)

    return plan
```

---

# 103. Numerical Safety

Все formulas должны:

- clamp output;
- reject NaN;
- reject Infinity;
- handle zero evidence;
- handle missing dates;
- use deterministic rounding.

---

# 104. Zero Evidence Skill

Если:

```text
E = 0
```

posterior:

```text
m = 0.5
```

Но UI state:

```text
UNKNOWN
```

Не показывать:

> «Вы знаете тему на 50%.»

---

# 105. One Correct Answer

Prior:

```text
alpha=1
beta=1
```

One clean B correct with weight ~1:

```text
alpha=2
beta=1
m≈0.67
confidence≈0.18
```

UI:

```text
Недостаточно данных
```

Это правильнее, чем `67% mastery`.

---

# 106. Repeated Independent Success

Например 6 разнообразных independent items:

posterior mean и confidence постепенно растут.

Только после достаточного evidence:

можно назвать skill strong.

---

# 107. Failure After Strong Mastery

Один recent failure не должен:

```text
0.90 → 0.20
```

Beta evidence смягчает volatility.

Но:

- retrievability;
- error recurrence;
- disagreement

могут повысить review priority.

---

# 108. Assistance Abuse

Если пользователь:

```text
hint
hint
full solution
submit correct
```

это не улучшает mastery.

Но система не наказывает морально.

Она просто говорит:

> нужно подтвердить понимание самостоятельной задачей.

---

# 109. Guessing

Multi-choice correct может быть guess.

В `v1` отдельный guess parameter не моделируется.

Mitigation:

- repeated diverse items;
- confidence grows постепенно;
- harder formats;
- self-confidence optional;
- future IRT/BKT.

---

# 110. Very Fast Correct Answers

Не автоматически считать EASY.

Если time слишком низкое:

может быть:

- remembered answer;
- accidental tap;
- repeated exposure.

Нужен lower bound sanity check.

---

# 111. Expected Time

QuestionVersion имеет:

```text
estimatedTimeSeconds
```

Первоначально expert estimate.

Позднее:

```text
empirical median / percentile
```

Time factor не должен быть сильным до calibration.

---

# 112. Self-Reported Confidence

Optional:

```text
CONFIDENT
UNSURE
GUESSED
```

Использование v1:

не меняет mastery напрямую.

Используется как diagnostic metadata.

Позднее может улучшать:

- misconception detection;
- underconfidence;
- guessing model.

---

# 113. Misconception Signal

Сильный кандидат:

```text
CONFIDENT + INCORRECT
```

→ misconception investigation.

Но не объявлять конкретную misconception без достаточного evidence.

---

# 114. Error Intelligence and Recommendation

Если skill mastery средний, но одна ошибка повторяется:

```text
errorRecurrence high
```

engine может назначить:

```text
ERROR_REPAIR
```

вместо обычного drill.

---

# 115. External Result Trust

Даже official screenshot после AI extraction:

имеет:

```text
source reliability
× extraction confidence
```

После user confirmation extraction confidence повышается, но source provenance сохраняется.

---

# 116. Official Total Score

Пример:

```text
Official:
31 / 50 points
```

Он очень важен для readiness.

Но не говорит системе автоматически:

```text
TRIG mastery = X
```

если нет тематического/item breakdown.

---

# 117. Multiple External Tests

Не считать два файла разными evidence, если это один и тот же assessment.

Duplicate detection before evidence creation.

---

# 118. Corrected Import

Correction process:

```text
old evidence → INVALIDATED
new canonical assessment
→ replacement evidence
→ rebuild
```

Не редактировать mastery вручную.

---

# 119. Question Defect

Если AnswerKey был wrong:

```text
affected evidence invalidated
→ corrected question version
→ mastery replay
```

Система должна поддерживать это технически.

---

# 120. Curriculum Migration

Новая curriculum version:

не пересчитывает старую историю вслепую.

Skill stableCode может сохраниться.

Если skill meaning materially changed:

новый skill code.

---

# 121. Cold Start Strategy

При signup:

### Option A

```text
Diagnostic
```

### Option B

```text
Import Assessment
```

После B:

engine вычисляет uncertainty.

Если granular coverage низкий:

назначает targeted diagnostic.

---

# 122. Targeted Diagnostic Priority

Для unknown skills:

```text
diagnosticPriority =
uncertainty
× graphCentrality
× curriculumRelevance
```

`graphCentrality` v1 может быть heuristic:

skills с большим количеством dependents выше.

---

# 123. Graph Centrality v1

Простая metric:

```text
centrality =
normalized number of downstream skills
```

Используется только в diagnostic information gathering.

Не путать с exam frequency.

---

# 124. Prerequisite Validation

Слабый prerequisite не принимается из одного item.

Если target fails и prereq low confidence:

назначить:

```text
2–3 targeted prerequisite checks
```

---

# 125. Stable Skill, Failed Transfer

Если:

```text
skill mastery high
direct practice strong
mixed mock weak
```

создаётся:

```text
TRANSFER_RISK
```

Это не обязательно content gap.

Planning:

```text
mixed practice
method selection
timed context
```

---

# 126. Time Pressure Signal

Если internal untimed strong, timed mock weak:

возможный:

```text
TIME_PRESSURE
```

Но требует repeated pattern.

Не классифицировать по одному test.

---

# 127. Recommendation Exploration

Чтобы engine не застревал в одной картине:

```text
5–10% activities
```

можно использовать как low-risk exploration:

- adjacent skill;
- mixed check;
- uncertainty reduction.

Не использовать random hard C questions.

---

# 128. Recommendation Exploitation

Остальные:

```text
90–95%
```

на основе highest expected utility.

Alpha ratios — hypothesis.

---

# 129. Content Gap Signal

Если high-priority skill не имеет достаточного content:

создать internal product metric:

```text
CONTENT_DEMAND_SCORE
```

Это помогает команде приоритизировать question authoring.

---

# 130. Daily Plan Recalculation

Trigger:

- goal changed;
- strong new assessment imported;
- mock completed;
- major mastery change;
- plan expired.

Не пересчитывать на каждый маленький click.

---

# 131. Recalculation Stability

Чтобы план не прыгал после каждого ответа:

использовать threshold.

Пример:

```text
invalidate if:
high-reliability assessment confirmed
OR
priority ranking changes materially
OR
critical prerequisite discovered
```

---

# 132. Plan Locking During Session

После начала Daily Mission:

обычно не перестраивать текущую очередь полностью.

Новые learning signals влияют на:

- последнюю часть session;
- следующий plan.

Иначе UX становится хаотичным.

---

# 133. Exam Strategy Layer

Recommendation Engine имеет horizon policy.

Это НЕ отдельный AI.

Rules:

- далеко до экзамена → learning;
- ближе → mixed transfer;
- очень близко → score preservation / error prevention.

---

# 134. Last-Week Guard

За <=7 дней:

не рекомендовать тяжёлую новую ветвь с длинной prerequisite chain, если expected payoff низкий.

Система может сказать:

> «Сейчас выгоднее закрепить темы, где ты уже близок к стабильному результату.»

---

# 135. Goal Gap

Если target score сильно выше recent readiness:

не превращать это в ложную urgency каждого skill.

Goal gap влияет на:

- recommended time;
- mock frequency;
- breadth vs depth strategy.

---

# 136. No Guaranteed Target

Learning Engine возвращает:

```text
goal feasibility estimate
```

только после calibration data.

Никогда:

```text
"35 гарантировано"
```

---

# 137. Recommendation Explainability Example

```text
Сегодня: PLN-MET-03

Почему:
- mastery 0.43
- confidence 0.82
- prerequisite ready
- review due
- 2 recent geometry errors
```

User-facing:

> «Здесь у тебя устойчивый пробел, база для темы уже готова, а последние задачи показывают повторяющуюся ошибку.»

---

# 138. Internal Explainability Snapshot

Store:

```json
{
  "algorithmVersion": "recommendation-v1",
  "skill": "PLN-MET-03",
  "factors": {
    "gap": 0.57,
    "review": 0.34,
    "uncertainty": 0.18,
    "disagreement": 0.00,
    "examRelevance": 0.50,
    "errorRecurrence": 0.42
  },
  "prerequisiteGate": 1.0,
  "finalPriority": 0.39
}
```

---

# 139. Privacy Rule

Learning algorithm не нуждается в:

- ФИО;
- email;
- пол;
- адрес;
- школа

для расчёта Mastery v1.

Не использовать unnecessary personal attributes.

---

# 140. Fairness

Если в будущем модель начнёт использовать population-trained parameters:

обязательно проверить performance/calibration по:

- language;
- device conditions;
- different preparation levels.

Не оптимизировать одну группу ценой другой незаметно.

---

# 141. Language and Learning State

Смена языка объяснения:

```text
KK ↔ RU
```

не должна создавать отдельный mastery автоматически.

Но в будущем можно анализировать language-specific comprehension, если данные покажут необходимость.

---

# 142. LLM Independence

Если Gemini unavailable:

Learning Engine продолжает работать:

- validate answers;
- update evidence;
- calculate mastery;
- build plans.

Недоступен только Tutor/import-AI functionality.

Это обязательный architectural property.

---

# 143. Unit Test Matrix — Mastery

Обязательно:

```text
zero evidence
one correct
one incorrect
multiple correct
mixed evidence
assisted correct
full solution
repeat question
difficulty A/B/C
low reliability source
high reliability source
invalidated evidence
```

---

# 144. Unit Test Matrix — Review

```text
first success
failure
hard success
good success
easy success
days elapsed
future clock
missing last retrieval
stability cap
```

---

# 145. Unit Test Matrix — Recommendation

```text
weak skill
unknown skill
overdue strong skill
prerequisite gap
disagreement
content unavailable
repeated error
near exam
far from exam
```

---

# 146. Property Tests

Полезные invariants:

```text
more identical positive evidence
must not decrease mastery

more identical negative evidence
must not increase mastery

confidence increases with evidence mass

invalidated evidence
has no effect

priority remains within [0,1]

retrievability decreases with time
when stability fixed
```

---

# 147. Golden Student Simulations

Создать synthetic profiles:

## Profile A — Strong but rusty

```text
mastery high
R low
```

Expected:

review.

## Profile B — Weak prerequisite

```text
target low
prereq lower
```

Expected:

prerequisite.

## Profile C — Unknown

```text
confidence low
```

Expected:

diagnostic.

## Profile D — Internal/external disagreement

Expected:

validation mission.

## Profile E — Exam in 5 days

Expected:

mixed/review-heavy mission.

---

# 148. Offline Replay Dataset

Каждый event:

```text
student pseudonymous id
skill
question
difficulty
correctness
assistance
timestamp
assessment context
```

Без unnecessary PII.

---

# 149. Model Registry

Таблица/config registry:

```text
AlgorithmVersion
- type
- version
- configHash
- activatedAt
- retiredAt?
- notes
```

---

# 150. Shadow Evaluation

Будущий algorithm может считать projection параллельно:

```text
v1 production
v2 shadow
```

без влияния на пользователя.

Сравниваем offline metrics.

---

# 151. A/B Testing Guard

Не экспериментировать с learning algorithms без:

- minimum sample;
- safety metric;
- stop criterion;
- analysis plan.

---

# 152. Metrics for Beta

Learning Engine dashboard:

```text
Mastery calibration
Next-item Brier score
D7 learning gain
Mock score gain
Review success rate
Recommendation completion
Disagreement resolution rate
Tutor dependency rate
```

---

# 153. Tutor Dependency Metric

Если пользователь всё чаще решает только с hints:

это важный signal.

Пример:

```text
assisted_correct / all_correct
```

Но не использовать как punishment.

---

# 154. Mastery Saturation Check

Если skill:

```text
m >= 0.90
c >= 0.85
R >= 0.90
```

не нужно часто назначать direct drill.

Переключаем:

- mixed;
- C-level;
- spaced review;
- transfer.

---

# 155. Weak Skill Definition

Для planning:

```text
m < 0.55
AND c >= 0.40
```

Но low-confidence skill:

```text
UNKNOWN / NEEDS_DIAGNOSTIC
```

а не слабый.

---

# 156. Strong Skill Definition

```text
m >= 0.75
c >= 0.60
```

Strong ≠ permanent.

Review state отдельно.

---

# 157. Mastery-Like Definition

```text
m >= 0.85
c >= 0.75
recent independent evidence exists
```

Называть UI аккуратно:

```text
"Сильный навык"
```

лучше, чем обещать «освоено навсегда».

---

# 158. Recent Independent Evidence

Начальная policy:

```text
within last 45 days
```

или review due state not overdue.

---

# 159. Uncertainty Reduction

Иногда лучший next action не максимальный gap.

Пример:

```text
Skill A:
m=0.30 c=0.95

Skill B:
m=0.70 c=0.05
```

B может получить targeted diagnostic, потому что мы почти ничего о нём не знаем.

---

# 160. Information Value

В future recommendation:

```text
Expected Information Gain
```

может заменить простую uncertainty term.

Не Alpha.

---

# 161. Item Calibration Data

Собирать с первого дня:

```text
attempt count
success rate
response time
student state at attempt
hint usage
```

Но не менять difficulty automatically до minimum sample.

---

# 162. Empirical Difficulty

После достаточных данных:

```text
difficulty ≠ raw percent incorrect
```

Потому что population ability differs.

Лучше future IRT.

---

# 163. Question Quality Alert

Потенциальный defect:

```text
high-mastery students
systematically fail item
```

или:

```text
low discrimination
```

Создать content review signal.

---

# 164. Learning Engine API Boundary

Learning module предоставляет:

```text
recordEvidence(...)
getSkillState(...)
rebuildSkill(...)
rebuildStudent(...)
getTopicState(...)
detectDisagreements(...)
```

Planning module:

```text
rankSkills(...)
buildDailyPlan(...)
selectQuestion(...)
```

---

# 165. Learning Engine Does Not

Не делает:

- HTTP;
- file OCR;
- Gemini calls;
- auth;
- payments;
- content editing.

---

# 166. Determinism

Для одинаковых:

```text
evidence
policy version
clock
```

результат должен быть одинаковым.

Если recommendation имеет exploration randomness:

seed и decision сохраняются.

---

# 167. Randomness Policy

Для content diversity можно использовать random selection.

Но:

- ranking deterministic;
- random only within near-equal candidate pool;
- selected item persisted.

---

# 168. Explainable Randomness

Не показывать:

> «AI выбрал случайно.»

User reason остаётся learning reason.

Randomness только уменьшает repetitiveness.

---

# 169. Migration from v1 to v2

Process:

```text
1. define v2
2. offline replay
3. shadow projections
4. compare calibration/outcomes
5. limited experiment
6. activate
7. rebuild projections
8. preserve v1 history
```

---

# 170. When to Replace v1

Не по времени.

Только если данные показывают конкретную проблему:

- poor calibration;
- recommendation baseline wins;
- heterogeneous learners poorly modeled;
- item difficulty effects too crude;
- forgetting scheduler poor.

---

# 171. Success Criteria for Learning Engine Alpha

Engine считается технически готовым, если:

- evidence reproducibly converts to mastery;
- confidence separates unknown from known;
- assistance affects evidence correctly;
- full solution cannot fake mastery;
- repeated items are discounted;
- prerequisite gap changes plan;
- review urgency works independently from mastery;
- external topic evidence does not contaminate granular skills;
- disagreement creates validation mission;
- Daily Mission respects time budget;
- readiness never claims precision without assessments;
- algorithm is fully replayable;
- all decisions have version and reason.

---

# 172. Research References

## Knowledge Tracing

Albert T. Corbett, John R. Anderson.  
**Knowledge Tracing: Modeling the Acquisition of Procedural Knowledge.**  
User Modeling and User-Adapted Interaction, 4, 253–278 (1995).  
DOI: `10.1007/BF01099821`

## Deep Knowledge Tracing

Chris Piech et al.  
**Deep Knowledge Tracing.**  
arXiv:1506.05908 (2015).  
https://arxiv.org/abs/1506.05908

## Interpretable vs Deep Knowledge Tracing

Mohammad Khajah, Robert V. Lindsey, Michael C. Mozer.  
**How deep is knowledge tracing?**  
arXiv:1604.02416 (2016).  
https://arxiv.org/abs/1604.02416

## Item Response Theory

Samuel A. Livingston.  
**Basic Concepts of Item Response Theory: A Nonmathematical Introduction.**  
ETS Research Memorandum RM-20-06 (2020).  
https://www.ets.org/research/policy_research_reports/publications/report/2020/kbxx.html

## Spaced Repetition / FSRS

Open Spaced Repetition.  
**FSRS Algorithm — Difficulty, Stability, Retrievability model.**  
https://github.com/open-spaced-repetition/awesome-fsrs/wiki/The-Algorithm

---

# 173. What We Borrow and What We Do Not

Из Knowledge Tracing:

```text
idea of latent knowledge state
continuous update from observations
```

Из IRT:

```text
future separation of item difficulty and learner ability
```

Из FSRS:

```text
separation:
knowledge/mastery
vs
retrievability/stability
```

Но ENT Math AI v1 — собственный explainable baseline, потому что:

- multi-source evidence;
- external assessment import;
- skill graph;
- prerequisite routing;
- assisted attempts;
- exam-oriented planning

не совпадают один-в-один с flashcards или классическим tutor dataset.

---

# 174. Final Algorithm Stack v1

```text
                    ┌─────────────────────┐
                    │ Student Activities  │
                    │ External Results    │
                    └──────────┬──────────┘
                               ↓
                    ┌─────────────────────┐
                    │ Evidence Normalizer │
                    └──────────┬──────────┘
                               ↓
                 ┌─────────────┴──────────────┐
                 ↓                            ↓
        ┌──────────────────┐        ┌──────────────────┐
        │ Mastery Model v1 │        │ Review State v1  │
        │ Weighted Beta    │        │ S + R(t,S)       │
        └─────────┬────────┘        └─────────┬────────┘
                  │                           │
                  └────────────┬──────────────┘
                               ↓
                    ┌─────────────────────┐
                    │ Skill Learning State│
                    └──────────┬──────────┘
                               ↓
             ┌─────────────────┼──────────────────┐
             ↓                 ↓                  ↓
     Prerequisite        Disagreement        Error Signals
        Logic               Logic
             └─────────────────┼──────────────────┘
                               ↓
                    ┌─────────────────────┐
                    │ Recommendation v1   │
                    └──────────┬──────────┘
                               ↓
                    ┌─────────────────────┐
                    │ Daily Mission       │
                    └──────────┬──────────┘
                               ↓
                           PRACTICE
                               ↺
```

---

# 175. Final Learning Principle

Главная цель Learning Engine:

> **Не максимизировать число решённых задач.  
> Не максимизировать время в приложении.  
> Не максимизировать обращения к AI.**

Он должен максимизировать:

```text
полезное изменение знаний
на единицу времени ученика
```

при ограничениях:

```text
exam goal
time remaining
student fatigue
content quality
uncertainty
prerequisites
memory decay
```

---

# 176. Next Document

Следующий обязательный документ:

> **`05_AI_TUTOR.md`**

Он должен определить:

```text
Tutor pedagogical policy
Hint Ladder
Socratic behavior
Prompt architecture
Context contract
Math safety
Answer leakage prevention
Kazakh/Russian language policy
Structured outputs
LLM provider abstraction
Tutor cost controls
AI eval suite
Prompt injection defense
Failure/fallback behavior
```

Learning Engine после этого уже не должен меняться из-за того, какой именно LLM используется.
