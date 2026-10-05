# ENT Math AI — Ultimate Product Concept

> **Тип документа:** Concept Bible / Product Vision  
> **Статус:** рабочая концепция до технического проектирования  
> **Версия:** 1.0  
> **Дата:** 5 октября 2026  
> **Рынок старта:** Казахстан  
> **Первый вертикальный продукт:** профильная математика ЕНТ / ҰБТ  
> **Языки:** қазақша + русский  
> **Рабочее название:** ENT Math AI / ENT Math Coach

---

# 1. Суть продукта в одном абзаце

**ENT Math AI** — адаптивная AI-платформа подготовки к профильной математике ЕНТ. Система диагностирует знания ученика на уровне отдельных навыков, строит персональную карту сильных и слабых мест, формирует ежедневный план подготовки, выбирает следующее наиболее полезное задание, анализирует ошибки и предоставляет пошаговую помощь AI-репетитора. После каждой попытки модель знаний обновляется, а маршрут подготовки перестраивается с учётом цели ученика, сложности заданий, истории ошибок, использования подсказок, скорости решения и времени до экзамена.

Главная идея:

> **Мы не продаём уроки, тесты или чат с LLM. Мы продаём управляемый путь от текущего уровня ученика к его целевому результату на ЕНТ.**

---

# 2. Главная проблема

Современный ученик уже имеет огромный выбор контента:

- YouTube;
- онлайн-курсы;
- Telegram;
- пробники;
- банки тестов;
- репетиторы;
- ChatGPT;
- Gemini;
- PDF и конспекты.

Проблема не в отсутствии материалов.

Проблема в том, что ученик часто не знает:

1. какие именно навыки у него слабые;
2. почему он теряет баллы;
3. что нужно учить именно сегодня;
4. какую сложность выбирать;
5. когда повторять уже изученное;
6. движется ли он к своей цели.

Типичный цикл сегодня:

```text
пробник
  ↓
22/40
  ↓
несколько ошибок
  ↓
случайная тема
  ↓
урок
  ↓
несколько задач
  ↓
другая тема
  ↓
новый пробник
  ↓
почти тот же результат
```

ENT Math AI должен заменить хаотичную подготовку управляемым learning loop.

---

# 3. Product Thesis

Основная продуктовая гипотеза:

> **Если система постоянно поддерживает актуальную модель знаний ученика и выбирает следующее наиболее полезное учебное действие с учётом экзаменационной важности навыка, текущего mastery, prerequisite gaps, забывания и оставшегося времени, подготовка может быть эффективнее линейного курса или простой базы тестов.**

Продукт должен отвечать ученику на пять вопросов:

1. **Что я уже знаю?**
2. **Что я не знаю?**
3. **Почему я теряю баллы?**
4. **Что мне делать сегодня?**
5. **Приближаюсь ли я к своей цели?**

---

# 4. Чем продукт НЕ является

ENT Math AI — это не:

- ChatGPT-wrapper;
- решебник;
- генератор ответов;
- обычный банк тестов;
- библиотека видеоуроков;
- LMS;
- копия Daryn;
- копия iTest;
- маркетплейс репетиторов;
- красивый dashboard без реальной адаптивности.

Если продукт сводится к интерфейсу:

```text
Введите задачу → получить решение
```

то он легко заменяется бесплатной универсальной LLM.

---

# 5. Чем продукт является

Рабочее определение:

> **AI-powered adaptive exam coach.**

Он управляет подготовкой:

```text
Диагностика
    ↓
Knowledge Map
    ↓
Weakness Detection
    ↓
Daily Plan
    ↓
Practice
    ↓
AI Tutor
    ↓
Error Analysis
    ↓
Mastery Update
    ↓
Spaced Review
    ↓
Mock Exam
    ↓
Strategy Update
    ↺
```

---

# 6. Value Proposition

## Для ученика

> **Не думай, что учить сегодня. Система найдёт темы, на которых ты теряешь баллы, и каждый день даст задания, которые сильнее всего приближают тебя к цели.**

## Для родителя

> **Видеть не количество просмотренных уроков, а реальный прогресс: регулярность, слабые темы, изменение mastery и динамику пробных результатов.**

---

# 7. Jobs To Be Done

Главный Job:

> Когда я готовлюсь к ЕНТ и не понимаю, на что тратить ограниченное время, я хочу, чтобы система сама определила мои слабые места и построила персональный маршрут, чтобы повысить результат к экзамену.

Дополнительные Jobs:

- понять стартовый уровень;
- перестать хаотично готовиться;
- быстро получить помощь после ошибки;
- не тратить время на уже освоенные темы;
- вовремя повторять материал;
- понимать причины ошибок;
- видеть измеримый прогресс;
- понимать, реалистична ли цель;
- снизить тревогу от неопределённости.

---

# 8. Целевая аудитория

## Primary ICP

Ученик 10–11 класса в Казахстане, который:

- сдаёт профильную математику;
- хочет улучшить текущий результат;
- готов заниматься регулярно;
- уже знает часть программы;
- имеет конкретные пробелы;
- не умеет самостоятельно строить эффективный план подготовки.

## Наиболее привлекательный ранний сегмент

Не сильнейший ученик, уже близкий к максимуму.

Не ученик с почти нулевой базой, которому нужен длительный фундаментальный курс.

Лучший первый сегмент:

> **ученик среднего уровня, который уже решает часть заданий, но нестабилен и теряет баллы из-за конкретных пробелов.**

Пример:

```text
Текущий уровень: 18–30 / 40
Цель:            30–38 / 40
До экзамена:     2–8 месяцев
Время:           20–60 минут в день
```

---

# 9. Почему стартовать с профильной математики

Математика хорошо подходит для первой вертикали:

1. ответы многих задач можно проверять детерминированно;
2. программу можно декомпозировать на granular skills;
3. навыки имеют prerequisite-связи;
4. можно учитывать сложность и скорость решения;
5. ошибки можно классифицировать;
6. можно измерять mastery;
7. можно калибровать readiness через mock exams;
8. архитектура позже расширяется на другие предметы.

---

# 10. Рыночный контекст

По официальным итогам основного ЕНТ-2026:

- около **233 000** абитуриентов участвовали в основном ЕНТ;
- с учётом двух попыток проведено около **422 000** тестирований;
- **73,1%** участников сдавали на казахском языке;
- **26,8%** — на русском.

Вывод:

> **казахский язык должен быть частью ядра продукта, а не поздней локализацией.**

Официальный формат ЕНТ-2026 включает обязательные блоки и два профильных предмета. Для профильной математики Национальный центр тестирования публикует официальную спецификацию, которую нужно использовать как первичную основу curriculum map.

---

# 11. Конкурентная логика

## Контентные платформы

Сильны в:

- видео;
- готовых курсах;
- большом объёме материалов.

Наше отличие:

> не давать библиотеку, а постоянно решать, **что конкретно этому ученику нужно делать дальше**.

## Тестовые платформы

Сильны в:

- пробниках;
- тестах;
- статистике.

Наше отличие:

> строить persistent learning state и персональную стратегию подготовки.

## ChatGPT / Gemini

Сильны в:

- объяснении;
- диалоге;
- гибкости.

Но обычно не имеют:

- долгосрочной модели знаний ученика;
- curriculum graph ЕНТ;
- validated question bank;
- адаптивного recommendation engine;
- экзаменационной калибровки;
- ограничений, не позволяющих слишком рано решать задачу за ученика.

## Репетитор

Сильный персональный опыт, но:

- дороже;
- ограничен временем;
- качество зависит от человека;
- невозможно получать помощь постоянно.

---

# 12. Product Moat

LLM сам по себе не moat.

Защита продукта со временем должна формироваться из:

```text
Official ENT Structure
        +
Validated Question Bank
        +
Skill Graph
        +
Prerequisite Graph
        +
Student Attempt History
        +
Mastery Model
        +
Error Taxonomy
        +
Adaptive Recommendation Engine
        +
Exam Calibration
        +
Bilingual Pedagogy
        +
Learning Outcome Data
```

Самая ценная долгосрочная информация:

> какие задания, подсказки и последовательности обучения реально помогают определённым типам учеников закрывать определённые пробелы.

---

# 13. North Star

Не оптимизировать:

- количество сообщений;
- screen time;
- просмотренные страницы;
- количество открытых уроков.

Оптимизировать:

> **изменение объективно измеряемой готовности ученика.**

Ранний кандидат North Star Metric:

```text
Weekly Mastery Gain among Active Learners
```

Позднее:

```text
Verified Predicted Score Improvement
```

---

# 14. Основной User Journey

## 14.1. Landing

Headline:

> **Узнай, где ты теряешь баллы на ЕНТ.**

CTA:

> **Пройти диагностику**

## 14.2. Onboarding

Минимум:

- язык;
- класс;
- дата/период экзамена;
- текущий ориентировочный результат;
- целевой результат;
- доступное время в день.

Пример:

```text
Сейчас:       23
Цель:         35
До экзамена:  94 дня
В день:       35 минут
```

## 14.3. Diagnostic

Цель — получить не только общий балл, а первичную оценку навыков.

Правильная модель:

```text
Short Diagnostic
      ↓
Approximate Knowledge State
      ↓
Continuous Refinement During Practice
```

## 14.4. Diagnostic Result

```text
Текущий уровень: 23/40

Сильные стороны:
✓ квадратные уравнения
✓ логарифмы
✓ производная

Главные потери:
1. Планиметрия
2. Тригонометрия
3. Стереометрия
4. Неравенства
```

## 14.5. Goal Gap

```text
Текущий диапазон: 23–25
Цель:             35
Разрыв:           ≈10–12
До экзамена:      94 дня
```

## 14.6. Personalized Daily Plan

```text
Сегодня • 32 минуты

1. Тригонометрические уравнения
   6 задач

2. Планиметрия
   5 задач

3. Повторение логарифмов
   3 задачи

4. Mini Check
   5 mixed задач
```

Главный CTA:

> **Начать занятие**

---

# 15. Core Learning Loop

```text
            ASSESSMENT
                ↓
         KNOWLEDGE STATE
                ↓
        NEXT BEST ACTION
                ↓
          PRACTICE ITEM
                ↓
       STUDENT RESPONSE
                ↓
     ┌──────────┴──────────┐
     ↓                     ↓
  CORRECT               INCORRECT
     ↓                     ↓
Confidence Update      Error Diagnosis
     │                     │
     └──────────┬──────────┘
                ↓
          MASTERY UPDATE
                ↓
          REVIEW SCHEDULE
                ↓
          NEXT BEST ACTION
                ↺
```

---

# 16. Knowledge Map

Математика хранится как иерархия и граф навыков.

Верхний уровень:

```text
MATHEMATICS
│
├── Numbers & Expressions
├── Equations
├── Inequalities
├── Functions
├── Sequences
├── Mathematical Analysis
├── Planimetry
└── Stereometry
```

Для адаптивности нужны granular skills.

Например:

```text
TRIGONOMETRY
│
├── TRIG-01 Unit Circle
├── TRIG-02 Basic Identities
├── TRIG-03 Transformations
├── TRIG-04 Basic Equations
├── TRIG-05 Equation Transformations
├── TRIG-06 Inequalities
└── TRIG-07 Mixed Exam Problems
```

---

# 17. Prerequisite Graph

Навыки не независимы.

```text
Algebraic Manipulation
        ↓
Quadratic Equations
        ↓
Quadratic Substitution
        ↓
Advanced Trigonometric Equations
```

Если ученик проваливает TRIG-05, система должна уметь проверить, не находится ли причина в prerequisite.

Это даёт более глубокую диагностику, чем простое:

```text
Тригонометрия — 43%
```

---

# 18. Mastery Model

Для каждого skill:

```text
Skill                         Mastery
------------------------------------
Quadratic equations           0.91
Logarithms                    0.78
Trig equations                0.43
Planimetry                    0.31
Stereometry                   0.48
```

Важно:

> **Mastery ≠ percentage correct.**

Два правильных ответа могут иметь разный смысл:

```text
Attempt A
correct = true
time = 35 sec
hints = 0
```

```text
Attempt B
correct = true
time = 4m 20s
hints = 3
```

Уровень самостоятельного владения навыком различается.

---

# 19. Mastery v1

Факторы:

```text
correctness
difficulty
hints_used
response_time
attempt_number
recency
previous_mastery
```

Концептуально:

```text
M_new = f(
    M_old,
    correctness,
    difficulty,
    hint_dependency,
    response_time,
    recency
)
```

Первый алгоритм должен быть:

- прозрачным;
- тестируемым;
- объяснимым;
- легко изменяемым.

Не нужен сложный ML в первой версии.

---

# 20. Future Mastery Models

После накопления данных можно исследовать:

- Bayesian Knowledge Tracing;
- Item Response Theory;
- Elo-like rating;
- Performance Factor Analysis;
- Deep Knowledge Tracing;
- hybrid models.

Принцип:

> **evidence before complexity.**

---

# 21. Adaptive Recommendation Engine

Следующее задание не выбирается случайно.

Пример факторов:

```text
Knowledge Gap
× Exam Importance
× Forgetting Risk
× Difficulty Fit
× Prerequisite Readiness
× Time Remaining
× Expected Learning Gain
```

Условно:

```text
Priority(skill) =
Gap × ExamWeight × Urgency × ForgettingFactor × LearningOpportunity
```

Это концепция, а не финальная формула.

---

# 22. Next Best Action

Следующим действием может быть:

- новая задача;
- prerequisite task;
- повторение;
- короткое объяснение;
- worked example;
- near-transfer problem;
- mixed practice;
- mini test;
- mock exam.

Главная UX-идея:

> пользователь редко должен думать «что открыть дальше?»

---

# 23. Difficulty Adaptation

Если ученик уверенно решает A:

```text
A → A → B → B → C
```

Если проваливает B:

```text
B ✗
B ✗
 ↓
diagnose
 ↓
prerequisite check
 ↓
easier task
 ↓
guided explanation
 ↓
return to B
```

---

# 24. AI Tutor

LLM отвечает прежде всего за **педагогическую коммуникацию**.

Он может:

- давать подсказки;
- задавать наводящие вопросы;
- объяснять ошибки;
- переформулировать;
- объяснять другим способом;
- сравнивать методы;
- отвечать на уточнения;
- говорить на казахском и русском.

LLM не должен быть единственным источником математической истины.

---

# 25. Главный принцип Tutor

> **Never solve too early.**

Плохой Tutor:

```text
Ученик: Не понимаю.
AI: Вот полное решение...
```

Хороший Tutor:

```text
Ученик: Не понимаю.

AI:
Посмотри на выражение.
Если обозначить sin(x) через t,
на какое знакомое уравнение оно станет похоже?
```

---

# 26. Hint Ladder

## Level 0 — Independent Attempt

Ученик решает самостоятельно.

## Level 1 — Nudge

Минимальное направление.

## Level 2 — Strategy Hint

Подсказка о подходе.

## Level 3 — Guided Step

Конкретный следующий шаг.

## Level 4 — Concept Explanation

Короткое объяснение нужной идеи.

## Level 5 — Full Solution

Полное решение.

Правило:

> просмотр Full Solution не подтверждает mastery.

После него нужна новая самостоятельная задача.

---

# 27. Mathematical Truth Layer

Критический архитектурный принцип:

```text
LLM text ≠ mathematical truth
```

Правильность должна проверяться через:

- заранее сохранённый ответ;
- numeric evaluation;
- symbolic equivalence;
- rule-based validation;
- deterministic mathematical engine;
- библиотеки вроде SymPy там, где это применимо.

Архитектура:

```text
Question
   ↓
Answer Engine
   ↓
Correct / Incorrect
   ↓
Tutor Explanation
```

Не:

```text
Question → LLM → "похоже, правильно"
```

---

# 28. Question Bank

Каждое задание — структурированный объект.

Пример:

```json
{
  "id": "TRIG-EQ-00421",
  "topic": "TRIGONOMETRY",
  "skill": "TRIG_05",
  "difficulty": "B",
  "format": "SINGLE_CHOICE",
  "estimated_time_sec": 150,
  "prerequisites": [
    "QUADRATIC_SUBSTITUTION",
    "TRIG_BASIC_EQUATIONS"
  ],
  "answer": "...",
  "solution": "...",
  "source_type": "CURATED",
  "status": "VALIDATED"
}
```

---

# 29. Content Strategy

Принцип:

> **качество важнее количества.**

Лучше:

```text
1 000 проверенных и качественно размеченных заданий
```

чем:

```text
100 000 автоматически сгенерированных задач
```

Источники:

1. собственные авторские задания;
2. предметные эксперты;
3. лицензированный контент;
4. открытые материалы с допустимым использованием;
5. parameterized templates;
6. AI-generated drafts после обязательной проверки.

Нельзя строить продукт на незаконном копировании защищённых банков задач.

---

# 30. AI Content Pipeline

```text
LLM Draft
   ↓
Schema Validation
   ↓
Answer Validation
   ↓
Difficulty Review
   ↓
Content Review
   ↓
Publish
```

Не:

```text
LLM → Production
```

---

# 31. Error Intelligence

Система анализирует не только тему, но и тип ошибки.

Базовая taxonomy:

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
```

Полезный insight:

> «Ты знаешь логарифмические формулы, но регулярно ошибаешься при переходе к новому основанию.»

Это лучше, чем:

```text
Логарифмы: 58%
```

---

# 32. Personal Error Notebook

Будущая сильная функция:

```text
МОИ ТИПИЧНЫЕ ОШИБКИ

1. Теряю минус при раскрытии скобок
   7 случаев

2. Путаю sin(a+b)
   4 случая

3. Забываю ОДЗ
   6 случаев
```

Перед экзаменом:

> **Твои 10 самых опасных ошибок.**

---

# 33. Spaced Repetition

Система должна возвращать изученное до полного забывания.

Пример:

```text
Day 0   — learning
Day 2   — check
Day 7   — review
Day 21  — mixed review
```

Интервалы адаптируются по результатам.

---

# 34. Interleaving

После освоения темы нельзя бесконечно давать однотипные задания.

Нужно смешивать:

```text
logarithms
quadratic
geometry
trigonometry
functions
```

Так ученик учится распознавать метод самостоятельно.

---

# 35. Diagnostic Engine

Диагностика балансирует:

- coverage;
- precision;
- time;
- fatigue.

Вместо двухчасового теста на входе:

```text
Short Diagnostic
      ↓
Approximate Model
      ↓
Continuous Refinement
```

---

# 36. Score / Readiness Prediction

Фича привлекательная, но должна быть честной.

Не:

```text
Ты получишь 31.7
```

Лучше:

```text
Текущий диапазон: 27–30
Уверенность: средняя

Чтобы уточнить прогноз:
пройди Mixed Mock.
```

Inputs в будущем:

- mastery;
- difficulty-adjusted accuracy;
- mock scores;
- time performance;
- coverage;
- hint dependency;
- recency;
- consistency.

---

# 37. Goal Engine

Пользователь задаёт:

```text
Цель: 35
До экзамена: 90 дней
Время: 35 минут в день
```

Система оценивает:

- gap;
- priority skills;
- workload;
- realistic range;
- weekly strategy.

Нельзя обещать гарантированный балл.

---

# 38. Exam Strategy Engine

## 120+ дней

- фундамент;
- prerequisite gaps;
- новые темы;
- gradual mastery.

## 30–120 дней

- основные пробелы;
- mixed practice;
- тайминг;
- mocks.

## 7–30 дней

- highest expected score gain;
- доступные баллы;
- повторение;
- recurring errors;
- time management.

## < 7 дней

- закрепление;
- личные ошибки;
- стратегия экзамена;
- минимизация перегруза.

---

# 39. Daily Mission

Главный home screen отвечает на один вопрос:

> **Что мне делать сегодня?**

```text
Сегодня • 31 минута

🔥 Главный фокус
Тригонометрические уравнения
6 задач

🧠 Второй фокус
Планиметрия
5 задач

🔁 Повторение
Логарифмы
3 задачи

⚡ Mini Check
5 mixed задач
```

---

# 40. Мотивация и геймификация

Допустимо:

- streak;
- weekly consistency;
- milestones;
- mastery growth;
- goal progress.

Недопустимо оптимизировать addiction.

Принцип:

> **Game mechanics support learning; they do not replace learning.**

---

# 41. Bilingual Experience

Оба языка — first-class:

- UI;
- условия задач;
- tutor;
- explanations;
- reports;
- glossary.

Полезная функция:

```text
[ Қазақша түсіндір ]
[ Объяснить по-русски ]
```

Нужно создать собственный bilingual mathematical glossary.

---

# 42. Parent Mode

Не обязательно в Alpha.

Будущий weekly report:

```text
Неделя 12

Занятий:         5/7
Решено:          96 задач
Точность:        64% → 71%

Сильный прогресс:
Логарифмы

Главный пробел:
Планиметрия

Прогноз:
25–28 → 27–30
```

Фокус — learning outcome, а не слежка.

---

# 43. MVP Definition

Первый настоящий MVP состоит из шести систем:

1. **Diagnostic** — определяет стартовый уровень.
2. **Knowledge Map** — хранит mastery.
3. **Daily Plan** — выбирает next-best actions.
4. **Practice Engine** — выдаёт задачи и фиксирует попытки.
5. **AI Tutor** — даёт дозированную помощь.
6. **Progress** — показывает изменение уровня.

---

# 44. MVP Feature List

## Authentication

- регистрация;
- вход;
- профиль.

## Onboarding

- язык;
- цель;
- экзамен;
- доступное время.

## Diagnostic

- стартовый набор;
- результат;
- первичная карта знаний.

## Practice

- задача;
- ввод/варианты;
- проверка;
- hints;
- explanation.

## Tutor

- Hint 1;
- Hint 2;
- Explain;
- Full Solution.

## Learning

- skill mastery;
- weak areas;
- error tags.

## Planning

- Daily Mission.

## Progress

- sessions;
- solved;
- accuracy;
- mastery trend;
- readiness range.

---

# 45. Что НЕ входит в MVP

- native iOS/Android;
- физика;
- информатика;
- история Казахстана;
- полноценная математическая грамотность;
- teacher LMS;
- B2B school dashboard;
- маркетплейс репетиторов;
- видеозвонки;
- social network;
- school leaderboards;
- voice tutor;
- собственная LLM;
- сложная neural mastery model;
- огромная генерация контента;
- advanced parent analytics.

---

# 46. MVP Success Criteria

MVP успешен, если реальные ученики:

1. проходят диагностику;
2. завершают первую персональную сессию;
3. возвращаются;
4. используют Tutor;
5. повышают mastery;
6. улучшают mock score;
7. готовы продолжать пользоваться.

---

# 47. Метрики

## Acquisition

- landing conversion;
- signup;
- diagnostic start.

## Activation

Главное событие:

> **diagnostic completed + first personalized session completed.**

## Engagement

- sessions/week;
- items/session;
- Daily Mission completion;
- Tutor usage.

## Retention

- D1;
- D7;
- D30.

## Learning

- mastery delta;
- mock score delta;
- difficulty progression;
- recurring error reduction.

## Business

- free → paid;
- ARPU;
- churn;
- CAC;
- LTV.

---

# 48. Ранние ключевые метрики

```text
Diagnostic Completion
First Session Completion
D1 Retention
D7 Retention
Sessions per Week
Daily Mission Completion
Mock Score Improvement
```

Регистрации сами по себе не показатель успеха.

---

# 49. Monetization Hypothesis

## Free

- диагностика;
- ограниченная практика;
- базовая карта знаний;
- ограниченный Tutor;
- basic progress.

## Pro

Рабочая гипотеза цены:

```text
≈ 2 990–4 990 ₸ / месяц
```

Возможности:

- расширенная практика;
- полный Tutor;
- adaptive plan;
- error intelligence;
- mock exams;
- score prediction;
- advanced progress.

Цена должна тестироваться.

---

# 50. Exam Sprint

Отдельный будущий продукт:

> **30-Day ENT Math Sprint**

Для последних недель:

- rapid diagnostic;
- high-impact gaps;
- Daily Mission;
- mocks;
- error review;
- exam strategy.

Возможна разовая оплата.

---

# 51. Go-To-Market

Первая beta:

```text
20–50 учеников
```

Каналы:

- школы;
- учителя;
- Telegram;
- TikTok;
- Instagram;
- учебные сообщества;
- рекомендации.

Цель раннего этапа:

> **не масштаб, а доказательство learning value.**

---

# 52. Closed Beta Experiment

Пример:

```text
30 учеников
4 недели
профильная математика
```

До начала:

- baseline diagnostic;
- baseline mock.

После:

- Week-4 mock;
- интервью;
- retention analysis;
- learning analysis.

Главная гипотеза:

> ученики, регулярно выполняющие adaptive Daily Mission, демонстрируют положительное изменение объективного результата.

---

# 53. User Research

Вопросы ученикам:

- Как готовишься сейчас?
- Как выбираешь тему на сегодня?
- Что делаешь после плохого пробника?
- Что раздражает в онлайн-курсах?
- Используешь ChatGPT/Gemini?
- Для чего?
- Когда чаще всего бросаешь подготовку?
- Кто платит?
- За что было бы не жалко платить?

Не спрашивать:

> «Тебе понравилась бы наша AI-платформа?»

Такой вопрос почти бесполезен.

---

# 54. Product Principles

## P1. Outcome over Content

Важно не сколько показали, а чему научили.

## P2. Diagnose before Teaching

Сначала найти проблему.

## P3. Student Attempts First

AI не решает всё первым сообщением.

## P4. Verified Math

LLM не единственный source of truth.

## P5. Visible Personalization

Пользователь понимает, почему выбрано это действие.

## P6. Simplicity

Главный экран отвечает: «что делать сегодня?»

## P7. Bilingual by Design

Қазақша и русский одинаково важны.

## P8. Explain Uncertainty

Прогноз не выдаётся за гарантию.

## P9. Evidence before Complexity

Сначала простой работающий алгоритм.

## P10. Learning over Addiction

Не оптимизировать бессмысленное удержание.

---

# 55. Trust & Privacy

Продукт работает со школьниками, поэтому необходимо:

- минимизировать собираемые данные;
- разделять официальные сведения и собственные прогнозы;
- безопасно хранить данные;
- ограничивать доступ;
- иметь понятную privacy policy;
- учитывать несовершеннолетних пользователей;
- не строить манипулятивный UX.

Пример формулировки:

> «Прогноз является оценкой системы и не гарантирует официальный результат ЕНТ.»

---

# 56. AI Safety

Нужны:

- system prompts;
- prompt injection protection;
- output constraints;
- rate limits;
- audit logging;
- isolation между пользователями;
- moderation при необходимости;
- запрет на раскрытие чужих данных.

---

# 57. Cost Control

LLM не вызывается на каждое действие.

```text
Submit Answer
    ↓
Deterministic Checker
    ↓
Correct?
 ↙        ↘
yes       no
 ↓         ↓
No LLM    Tutor call if needed
```

Позже можно использовать model routing:

```text
simple hint        → cheap/fast model
complex explanation → stronger model
math verification → deterministic engine
translation        → curated/cheap model
```

---

# 58. Provider Independence

AI-слой должен позволять заменить:

- Gemini;
- OpenAI;
- Claude;
- open-source models.

Бизнес-логика не должна напрямую зависеть от одного SDK.

---

# 59. Future Teacher Layer

После B2C PMF:

```text
Class 11A

Algebra       72%
Geometry      48%
Trigonometry  39%
```

Учитель может видеть class-level patterns.

Но Teacher Mode не должен усложнять первый MVP.

---

# 60. Future B2B

Позднее:

```text
Student Adaptive Engine
        +
Teacher Dashboard
        +
Class Analytics
```

Клиенты:

- школы;
- учебные центры;
- репетиторские центры.

---

# 61. Expansion Path

```text
ENT AI
│
├── Mathematics
├── Physics
├── Informatics
├── Math Literacy
├── Kazakhstan History
├── Reading Literacy
└── ...
```

Общее ядро:

```text
Knowledge Graph
+
Adaptive Engine
+
Tutor
+
Assessment
+
Progress
```

---

# 62. Long-Term Vision

Потенциальная большая платформа:

> **Adaptive AI Learning Operating System for high-stakes exams.**

Но это долгосрочная перспектива, а не оправдание раздуть MVP.

---

# 63. Defensibility Layers

## Layer 1 — Curriculum Model

Точная модель экзамена.

## Layer 2 — Question Metadata

Качественная разметка.

## Layer 3 — Learning Data

```text
student state
→ item
→ hint
→ response
→ mastery change
```

## Layer 4 — Recommendation Quality

Качество next-best action.

## Layer 5 — Local Language Quality

Сильная казахская математика.

## Layer 6 — Brand & Trust

Реально доказанный результат.

---

# 64. Главные риски

## Risk 1 — ChatGPT enough

Если продукт просто чат — differentiation почти нет.

**Mitigation:** persistent student state + adaptive engine + validated content.

## Risk 2 — плохой question bank

**Mitigation:** curated-first.

## Risk 3 — ошибки LLM

**Mitigation:** deterministic truth layer.

## Risk 4 — fake score prediction

**Mitigation:** ranges + uncertainty + mock calibration.

## Risk 5 — low retention

**Mitigation:** Daily Mission + short sessions + visible progress.

## Risk 6 — content cost

**Mitigation:** narrow alpha scope.

## Risk 7 — seasonality

**Mitigation:** 10 класс, другие предметы, Exam Sprint, дальнейшая экспансия.

---

# 65. Critical Unknowns

До масштабирования нужно получить ответы:

1. Улучшает ли adaptive routing реальные результаты?
2. Сколько заданий нужно для диагностики?
3. Какой granularity skill map оптимален?
4. Какая формула mastery v1 лучше всего работает?
5. Какие hints реально помогают?
6. Где LLM полезен, а где создаёт шум?
7. Какой price point приемлем?
8. Кто фактически платит: ученик или родитель?
9. Нужен ли Parent Mode рано?
10. Как измерять качество казахских объяснений?
11. Какие темы дают максимальный early learning gain?
12. Какой минимальный question bank достаточен?

---

# 66. Alpha Scope

Не нужно сразу покрывать всю программу.

Рабочая гипотеза:

```text
5–7 крупных тематических зон
20–30 granular skills
500–1000 validated items
```

Возможные блоки:

1. Квадратные уравнения
2. Неравенства
3. Логарифмы
4. Тригонометрия
5. Функции / производная
6. Планиметрия
7. Стереометрия

Окончательное решение — после syllabus audit.

---

# 67. Conceptual Architecture

```text
                    ┌──────────────────┐
                    │      USER        │
                    └────────┬─────────┘
                             ↓
                    ┌──────────────────┐
                    │     PRACTICE     │
                    └────────┬─────────┘
                             ↓
             ┌───────────────┴──────────────┐
             ↓                              ↓
     ┌───────────────┐              ┌───────────────┐
     │ ANSWER ENGINE │              │    AI TUTOR   │
     └───────┬───────┘              └───────┬───────┘
             │                              │
             └───────────────┬──────────────┘
                             ↓
                    ┌──────────────────┐
                    │ LEARNING RECORD  │
                    └────────┬─────────┘
                             ↓
                    ┌──────────────────┐
                    │ MASTERY ENGINE   │
                    └────────┬─────────┘
                             ↓
                    ┌──────────────────┐
                    │ ADAPTIVE ENGINE  │
                    └────────┬─────────┘
                             ↓
                    ┌──────────────────┐
                    │  NEXT ACTION     │
                    └──────────────────┘
```

---

# 68. Product Data Flywheel

```text
more students
    ↓
more attempts
    ↓
better item calibration
    ↓
better mastery estimates
    ↓
better recommendations
    ↓
better outcomes
    ↓
more trust
    ↓
more students
```

Privacy остаётся обязательным ограничением.

---

# 69. Difficulty Calibration

Изначально:

```text
A / B / C
```

задаётся экспертно.

Позже реальная статистика уточняет difficulty.

Пример:

```text
Expected: B
Observed success rate: 31%
→ возможно задача ближе к C
```

---

# 70. Time Intelligence

Нужно различать:

```text
correct + fast
correct + slow
incorrect + fast
incorrect + slow
```

Это разные learning signals.

---

# 71. Confidence — будущая функция

Ученик может отметить:

```text
Уверен
Не уверен
Угадал
```

Это помогает находить:

```text
confident + wrong → misconception risk
uncertain + correct → weak confidence
```

Не обязательно для MVP.

---

# 72. Explainable Recommendations

Не:

> «AI выбрал эту тему.»

А:

> «Мы возвращаем логарифмы, потому что прошло 9 дней после последнего успешного повторения.»

или:

> «Планиметрия сейчас имеет самый большой потенциал роста твоего результата.»

---

# 73. Student Control

Система рекомендует, но не блокирует свободу.

Должны существовать:

- Recommended Today;
- Topics;
- My Mistakes;
- Mock Exam;
- Extra Practice.

Главный режим — рекомендованный.

---

# 74. Motivation without Shame

Плохо:

> «Ты ленишься уже 3 дня!»

Хорошо:

> «Вернёмся короткой 10-минутной сессией?»

Продукт должен облегчать возвращение.

---

# 75. Anti-Cheating Learning Logic

Если открыто полное решение:

```text
item_result != mastered
```

Для подтверждения нужен новый самостоятельный item.

---

# 76. Recovery Loop

```text
wrong
 ↓
diagnose
 ↓
hint / explanation
 ↓
worked example
 ↓
near-transfer item
 ↓
independent item
 ↓
mastery update
```

Это один из центральных learning loops.

---

# 77. Session Design

В будущем:

```text
Quick Practice — 10 min
Daily Plan     — 30 min
Deep Session   — 60 min
```

В MVP достаточно Daily Plan.

---

# 78. Mock Exam

Mock Mode:

- без Tutor;
- без hints;
- с таймингом;
- полный result;
- detailed analysis после завершения.

Mock нужен прежде всего для калибровки readiness.

---

# 79. Mock Review

Вместо:

```text
26/40
```

показывать:

```text
Потеряно 14 заданий

5 — content gaps
4 — careless/calculation
3 — time pressure
2 — prerequisite gaps
```

После этого план перестраивается.

---

# 80. Progress Screen

Минимальный состав:

```text
Goal
35

Current Range
27–30

Readiness
68%

Strong
Equations
Logarithms

Priority
Geometry
Trigonometry

Consistency
5 sessions this week
```

---

# 81. Retention Loop

```text
Daily Plan
   ↓
Small Win
   ↓
Visible Progress
   ↓
Next Recommendation
   ↓
Return
```

Уведомления вторичны.

---

# 82. Brand Direction

ENT Math AI — рабочее название.

Финальный бренд должен быть:

- коротким;
- понятным на русском и казахском;
- пригодным для расширения на другие предметы;
- связанным с движением, целью или прогрессом;
- современным, но не детским.

Смысловые территории:

- Qadam;
- Target;
- Score;
- Step;
- Bilim;
- Path;
- Boost;
- Progress.

Naming — отдельная задача.

---

# 83. One-Sentence Pitch

> **ENT Math AI — адаптивный AI-репетитор для профильной математики ЕНТ, который определяет слабые навыки ученика, строит персональный ежедневный маршрут и помогает повышать готовность к целевому результату.**

---

# 84. 30-Second Pitch

> Большинство платформ подготовки к ЕНТ дают ученику материалы и тесты, но ученик всё равно должен сам понимать, что именно ему учить. ENT Math AI сначала диагностирует знания на уровне отдельных навыков, затем каждый день выбирает наиболее полезные задания. Встроенный AI-репетитор помогает пошагово, не выдавая решение сразу. После каждой попытки карта знаний обновляется, а персональный маршрут перестраивается. Поэтому система оптимизирует не количество просмотренных уроков, а прогресс к целевому результату ЕНТ.

---

# 85. Investor-Style Pitch

> ENT Math AI starts as an adaptive preparation platform for Kazakhstan's high-stakes Unified National Testing mathematics exam. Instead of competing on content volume, the platform maintains a persistent student knowledge model, selects the next-best learning activity, provides a constrained AI tutor, and continuously estimates exam readiness. The initial wedge is profile mathematics in Kazakh and Russian. Over time, the same adaptive infrastructure can expand across ENT subjects and other high-stakes examinations.

---

# 86. Что делает идею сильной

Есть:

- понятная боль;
- понятный outcome;
- измеримый feedback loop;
- естественная роль AI;
- локальная специализация;
- recurring revenue hypothesis;
- расширение B2C → B2B;
- расширение на другие предметы;
- потенциальный data moat.

---

# 87. Что убьёт идею

Если сделать:

```text
Chat
+
Dashboard
+
100 AI-generated Questions
```

и назвать это adaptive learning.

Тогда продукт почти ничем не отличается от generic LLM interface.

Настоящее ядро требует:

- skill model;
- mastery;
- validated content;
- recommendation logic;
- pedagogical Tutor;
- measurable outcome.

---

# 88. MVP Philosophy

> **Построить маленький, но настоящий learning engine.**

Предпочтительно:

```text
20 skills
700 quality items
real mastery
real adaptive routing
```

а не:

```text
40 красивых экранов
10 000 AI tasks
fake personalization
```

---

# 89. Development Stages

## Stage 0 — Product Discovery

- user interviews;
- competitor audit;
- syllabus decomposition;
- content audit;
- pricing hypotheses.

## Stage 1 — Learning Prototype

Проверить без красивого UI:

- question model;
- mastery;
- recommendation;
- Tutor behavior.

## Stage 2 — Alpha

- auth;
- onboarding;
- diagnostic;
- practice;
- Daily Plan;
- progress.

## Stage 3 — Closed Beta

20–50 учеников.

## Stage 4 — Learning Validation

4–8 недель.

## Stage 5 — Paid Beta

Willingness to pay.

## Stage 6 — Public MVP

Content expansion + acquisition.

---

# 90. Product Before Architecture

До подробной технической архитектуры нужно зафиксировать:

1. Alpha syllabus scope.
2. Question formats.
3. Mastery v1.
4. Recommendation v1.
5. Tutor policy.
6. Diagnostic design.
7. Progress model.
8. MVP metrics.
9. Content pipeline.
10. Validation strategy.

---

# 91. Рекомендуемая документация репозитория

```text
docs/
├── 00_CONCEPT.md
├── 01_PRODUCT.md
├── 02_CURRICULUM_MAP.md
├── 03_DOMAIN.md
├── 04_LEARNING_ENGINE.md
├── 05_AI_TUTOR.md
├── 06_CONTENT_STRATEGY.md
├── 07_MVP_SCOPE.md
├── 08_METRICS_AND_EXPERIMENTS.md
├── 09_ARCHITECTURE.md
├── 10_ROADMAP.md
│
├── product-specs/
├── adr/
├── exec-plans/
└── research/
```

Этот файл должен стать:

```text
docs/00_CONCEPT.md
```

---

# 92. Open Product Decisions

Пока сознательно не фиксируем окончательно:

1. Показывать ли score prediction сразу после первой диагностики?
2. Нужен ли full mock в MVP?
3. Сколько granular skills должно быть в Alpha?
4. Сколько items нужно на skill?
5. Поддерживать ли multiple-answer сразу?
6. Добавлять ли математическую грамотность в первый продукт?
7. Как хранить bilingual content?
8. Когда вводить Parent Mode?
9. Какой price point тестировать первым?
10. Как организовать human review контента?
11. Какая формула mastery v1?
12. Какой размер Daily Mission оптимален?

---

# 93. Key Hypotheses

## H1 — Problem

Ученикам не хватает не только контента, но и персональной стратегии.

## H2 — Adaptive Value

Personalized Daily Mission повышает эффективность подготовки.

## H3 — Tutor Value

Structured hints полезнее мгновенной выдачи решения.

## H4 — Visibility

Knowledge Map увеличивает ощущение контроля.

## H5 — Outcome

Visible score improvement повышает retention и willingness to pay.

## H6 — Language

Качественный казахский Tutor может стать сильным преимуществом.

## H7 — Buyer

Родитель готов платить за доказуемый прогресс.

---

# 94. Порядок проверки гипотез

```text
1. Боль существует?
       ↓
2. Диагностику проходят?
       ↓
3. Daily Mission выполняют?
       ↓
4. Возвращаются?
       ↓
5. Результат улучшается?
       ↓
6. Готовы платить?
       ↓
7. Acquisition масштабируется?
```

---

# 95. Ultimate Product Statement

> **ENT Math AI — персональная адаптивная система подготовки к профильной математике ЕНТ. Она строит живую модель знаний ученика на уровне отдельных навыков, выявляет причины потери баллов, выбирает следующее наиболее полезное учебное действие и использует AI-репетитора для пошаговой помощи. Система обновляет персональный маршрут в зависимости от результатов, ошибок, сложности заданий, скорости решения, использования подсказок и оставшегося времени до экзамена. Основная ценность продукта — не доступ к контенту, а измеримый прогресс от текущего уровня к целевому результату ЕНТ.**

---

# 96. Vision Diagram

```text
                           TARGET SCORE
                               ▲
                               │
                     ┌─────────┴─────────┐
                     │ SCORE / READINESS │
                     └─────────▲─────────┘
                               │
                     ┌─────────┴─────────┐
                     │    MOCK EXAMS     │
                     └─────────▲─────────┘
                               │
                     ┌─────────┴─────────┐
                     │   ADAPTIVE PLAN   │
                     └─────────▲─────────┘
                               │
               ┌───────────────┴───────────────┐
               │                               │
      ┌────────┴────────┐            ┌─────────┴────────┐
      │ PRACTICE ENGINE │            │    AI TUTOR      │
      └────────▲────────┘            └─────────▲────────┘
               │                               │
               └───────────────┬───────────────┘
                               │
                     ┌─────────┴─────────┐
                     │  ERROR ANALYSIS   │
                     └─────────▲─────────┘
                               │
                     ┌─────────┴─────────┐
                     │  MASTERY MODEL    │
                     └─────────▲─────────┘
                               │
                     ┌─────────┴─────────┐
                     │   KNOWLEDGE MAP   │
                     └─────────▲─────────┘
                               │
                     ┌─────────┴─────────┐
                     │    DIAGNOSTIC     │
                     └─────────▲─────────┘
                               │
                            STUDENT
```

---

# 97. Ultimate Formula

```text
ENT Math AI
=
Personalized Knowledge Model
+
Adaptive Next-Best-Action Engine
+
Validated ENT Question Bank
+
Deterministic Math Verification
+
Pedagogically Constrained AI Tutor
+
Error Intelligence
+
Spaced Review
+
Mock Calibration
+
Goal-Oriented Planning
+
Kazakh/Russian First-Class Experience
```

---

# 98. Источники рыночного и экзаменационного контекста

Проверено для концепции в октябре 2026 года. Правила и цены могут меняться.

## Министерство науки и высшего образования РК — итоги ЕНТ-2026

https://www.gov.kz/memleket/entities/sci/press/news/details/1259037?lang=ru

## Национальный центр тестирования

https://testcenter.kz/

## Спецификации ЕНТ

https://testcenter.kz/?lang=ru&page_id=15094

## Daryn.online

https://daryn.online/

## iTest

https://itest.kz/

---

# 99. Current Strategic Decision

```text
Market:
Kazakhstan

Exam:
ENT / ҰБТ

Initial Vertical:
Profile Mathematics

Primary User:
Student

Primary Buyer:
Student or Parent

Languages:
Kazakh + Russian

Core Differentiator:
Adaptive Learning Engine

AI Role:
Tutor / Explanation / Guidance

Truth Source:
Validated Content + Deterministic Checking

Primary Outcome:
Improvement toward target exam score

Business Model Hypothesis:
Freemium → Subscription

Long-Term Expansion:
Other ENT subjects
```

---

# 100. Следующий документ

После `00_CONCEPT.md` наиболее важным должен стать:

```text
04_LEARNING_ENGINE.md
```

Он должен детально определить:

- curriculum/skill graph;
- prerequisite graph;
- mastery v1;
- diagnostic algorithm;
- recommendation scoring;
- spaced repetition;
- difficulty adaptation;
- error model;
- Daily Mission algorithm;
- score/readiness estimation.

Именно Learning Engine определит, является ли проект настоящей адаптивной системой или просто красивым интерфейсом вокруг LLM.

---

# 101. Финальный тезис

Нельзя строить продукт вокруг вопроса:

> **«Как встроить LLM в подготовку к ЕНТ?»**

Правильный вопрос:

> **«Как построить систему, которая знает состояние знаний ученика, понимает его цель и каждый день принимает всё более точные решения о том, что ему делать дальше?»**

LLM — лишь один из компонентов.

Настоящее ядро:

```text
STUDENT MODEL
      +
ADAPTIVE ENGINE
      +
VERIFIED CONTENT
      +
AI PEDAGOGY
      +
EXAM STRATEGY
```

> **Не помочь ученику решить сегодняшнюю задачу. Помочь ему системно стать тем учеником, который сможет решить экзаменационные задачи сам.**
