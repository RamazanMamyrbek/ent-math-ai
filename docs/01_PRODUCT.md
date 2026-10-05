# 01_PRODUCT.md
## ENT Math AI — Product Definition

> **Status:** canonical product definition  
> **Version:** `PRODUCT-v1`  
> **Date:** 6 October 2026  
> **Related docs:** `00_CONCEPT.md`, `02_CURRICULUM_MAP.md`, `07_MVP_SCOPE.md`, `08_METRICS_AND_EXPERIMENTS.md`

# 1. Product Statement

ENT Math AI is a bilingual adaptive learning platform for Kazakhstan students preparing for the profile mathematics part of the ENT.

The product does not primarily sell access to content. It maintains a persistent model of the student's knowledge and decides what the student should do next.

Core promise:

> **Не думай, что учить. Система определит твои слабые навыки и каждый день даст те действия, которые сильнее всего приближают тебя к целевому результату ЕНТ.**

# 2. Problem

Students preparing for high-stakes mathematics usually have access to questions, videos, teachers and generic AI assistants, but still face three unresolved problems:

1. They do not know precisely which granular skills are weak.
2. They do not know what to study today.
3. They cannot reliably tell whether apparent progress transfers to independent exam-style performance.

A topic label such as `Тригонометрия` is too broad. A useful system must distinguish prerequisite and target skills and update them from evidence.

# 3. Primary User

Closed Alpha is optimized for:

```text
Kazakhstan
Grades 10–11
Profile mathematics ENT
RU or KK
Roughly mid-level preparation
20–45 minutes/day
2–8 months before exam
```

A particularly useful initial segment is a student who currently scores roughly in the middle range and wants to move materially higher, but does not have a precise daily study strategy.

# 4. Primary Job To Be Done

> «Я хочу повысить балл по профильной математике, но не хочу каждый день сам решать, какую тему сейчас учить и какие задачи мне действительно помогут.»

# 5. Core User Story

```text
Сейчас: 21/40
Цель: 34/40
До экзамена: 112 дней
Время: 30 минут в день

→ system estimates current knowledge
→ identifies bottlenecks
→ creates today's mission
→ observes independent attempts
→ updates learning state
→ repeats
```

# 6. Core Loop

```text
Diagnostic / Imported Assessment
        ↓
Knowledge Map
        ↓
Daily Mission
        ↓
Practice
        ↓
AI Tutor when needed
        ↓
Error / Evidence Analysis
        ↓
Mastery + Confidence + Review State
        ↓
Next Best Action
        ↺
```

# 7. Five Questions the Product Must Answer

For every active student the product should gradually answer:

```text
1. Что я уже умею?
2. Что я не умею?
3. Почему я теряю баллы?
4. Что мне делать сегодня?
5. Двигаюсь ли я к своей цели?
```

# 8. Product Differentiation

Compared with a normal question bank:

```text
Question bank:
choose topic → solve tasks → see percentage

ENT Math AI:
evidence → skill model → prerequisite reasoning → next-best-action → independent validation
```

Compared with a generic LLM:

```text
Generic LLM:
explains current prompt

ENT Math AI:
persistent curriculum model
persistent student evidence
verified answer keys
controlled hints
adaptive daily planning
external assessment history
```

Compared with a tutor:

ENT Math AI does not attempt to replace the human relationship. It aims to automate persistent diagnosis, repetition scheduling, question routing and evidence tracking at much lower marginal cost.

# 9. Core Moat

Long-term defensibility comes from the combination of:

```text
official curriculum model
granular skill graph
verified bilingual question bank
student attempt history
external assessment evidence
provenance and reliability
mastery calibration
error patterns
adaptive recommendation data
exam calibration
learning outcome data
```

The LLM provider is not the moat.

# 10. Product Principles

1. Outcome over content.
2. Diagnose before teaching.
3. Student attempts first.
4. Mathematical truth is deterministic.
5. Full solution viewed does not equal mastery.
6. Personalization must be visible and explainable.
7. Bilingual by design.
8. Uncertainty is shown, not hidden.
9. Evidence before model complexity.
10. Motivation without shame.
11. Learning over addictive engagement.
12. Recommendations must be explainable.

# 11. Supported Languages

Closed Alpha supports:

```text
Kazakh (KK)
Russian (RU)
```

Kazakh is a first-class language, not an afterthought or permanent machine translation of Russian content.

Math notation remains canonical across languages.

# 12. Curriculum Scope

The full curriculum model contains a granular skill graph mapped to the official ENT mathematics specification.

Closed Alpha intentionally supports a smaller subset, approximately:

```text
48 granular skills
```

covering:

```text
algebra foundations
linear/quadratic/rational equations
trigonometry
exponentials/logarithms
selected inequalities
functions/derivatives
geometry core
```

See `07_MVP_SCOPE.md`.

# 13. Closed Alpha Product

The Closed Alpha consists of:

```text
Authentication + Onboarding
Diagnostic
Knowledge / Skill Map
Daily Mission
Practice Engine
Learning Evidence
Mastery / Confidence / Review State
Controlled AI Tutor
External Assessment Import
Assessment History
Progress
Mini Mock
Basic Admin / Content Workflow
```

# 14. External Assessment Value Proposition

Strategic statement:

> **Неважно, где ты проходишь пробники. Загрузи результат сюда — и твой персональный профиль подготовки обновится.**

Alpha supports result screenshots/PDFs and manual review.

External total scores are useful for readiness but do not magically become granular mastery.

# 15. AI Tutor Positioning

Tutor is embedded in the solving flow.

It is not a generic empty chatbot.

Default behavior:

```text
independent attempt
→ nudge
→ strategy hint
→ guided step
→ concept explanation
→ full solution only when allowed
→ independent transfer check
```

The LLM may explain, but does not decide mathematical truth.

# 16. Progress Model

The student model distinguishes:

```text
Mastery
Confidence
Retrievability
```

A student may know a skill well but need review, or may have a high provisional estimate with insufficient evidence.

The UI must not turn low-confidence estimates into fake precision.

# 17. MVP Non-Goals

Closed Alpha does not include:

```text
native mobile applications
all ENT subjects
parent dashboard
teacher LMS
school/B2B platform
social network
leaderboards
voice tutor
live video
unrestricted chatbot
deep knowledge tracing
custom LLM
vector database
microservices
Kubernetes
full handwriting reasoning analysis
credential scraping
```

# 18. Success

A working application is not enough.

Product validation requires:

```text
students reach personalized value
students return
students follow Daily Mission
independent performance improves
content/Tutor/import remain trustworthy
variable AI cost is controllable
```

The primary outcome is independent learning gain, not screen time.

# 19. Closed Alpha Research Goal

Recommended validation cohort:

```text
30–50 students
4–6 weeks
```

Protocol:

```text
baseline mixed assessment
→ adaptive usage
→ follow-up holdout assessment
→ interviews
→ Go / No-Go review
```

# 20. Monetization

Payments are not a prerequisite for validating the learning loop.

Potential later model:

```text
Free:
diagnostic
limited practice
basic skill map

Pro:
full Daily Mission
expanded practice
AI Tutor
imports/history
advanced progress
mocks/readiness
```

Pricing remains a hypothesis until value and retention are demonstrated.

# 21. Expansion Logic

If Closed Alpha succeeds:

```text
broader mathematics coverage
→ Parent Mode
→ Paid Beta
→ other ENT subjects
→ teacher / B2B
```

Expansion must be evidence-driven.

# 22. Final Product Principle

ENT Math AI should remain useful even if the AI provider changes tomorrow.

The product is fundamentally:

```text
Curriculum
+
Verified Content
+
Learning Evidence
+
Adaptive Decisions
+
Independent Measurement
```

AI improves pedagogy and extraction; it is not the source of truth.
