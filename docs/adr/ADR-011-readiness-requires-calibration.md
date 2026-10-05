# ADR-011 — Readiness Prediction Requires Calibrating Assessments

**Status:** Accepted  
**Date:** 6 October 2026

## Context

Skill mastery is not the same as expected ENT score. Converting average mastery directly into an exam score would create false precision.

## Decision

Readiness/score range is derived primarily from eligible mixed/full assessments with explicit source reliability, exam similarity and recency.

Before sufficient evidence exists, the UI shows that more data is needed.

Closed Alpha rule:

```text
fewer than 2 eligible calibrating assessments
→ no score prediction
```

## Consequences

Positive:

- honest uncertainty;
- prevents fake precision;
- keeps skill state and exam calibration conceptually separate.

Negative:

- new users may not see a forecast immediately.

## Alternatives Rejected

- `average mastery × max score`;
- LLM-generated score predictions;
- exact score after short diagnostic.
