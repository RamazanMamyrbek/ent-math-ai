# ADR-006 — External Assessment Import Requires Provenance

**Status:** Accepted  
**Date:** 6 October 2026

## Context

External assessments vary in reliability, granularity and extraction quality. A screenshot, paper mock and official report are not equivalent evidence.

## Decision

Every normalized external assessment/evidence record retains provenance and reliability metadata.

At minimum:

```text
source type
assessment/upload reference
source reliability
extraction confidence
mapping confidence
user confirmation
```

Low-confidence extraction cannot silently update mastery.

Topic-level results remain topic-level. Total score remains readiness/calibration evidence.

## Consequences

Positive:

- trust;
- explainability;
- reversible corrections;
- reliable evidence weighting.

Negative:

- import model and review UI are more complex.

## Alternatives Rejected

- import score and overwrite student state directly;
- discard source metadata after extraction.
