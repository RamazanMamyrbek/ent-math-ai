# ADR-004 — Immutable Learning Evidence

**Status:** Accepted  
**Date:** 6 October 2026

## Context

Mastery algorithms will evolve. Imported assessments can be corrected, questions can be suspended and content defects can invalidate prior observations. Directly storing mutable mastery as truth would make historical repair and algorithm comparison unreliable.

## Decision

`LearningEvidence` is the durable learning-history source of truth.

`SkillMasteryProjection` and related states are rebuildable projections.

Corrections use:

```text
invalidate/supersede evidence
→ create replacement evidence if needed
→ rebuild projection
```

Never manually edit mastery to repair history.

## Consequences

Positive:

- reproducibility;
- algorithm replay;
- historical audit;
- safe correction of content/import defects.

Negative:

- more storage;
- rebuild logic is required.

## Alternatives Rejected

- direct `mastery += delta`;
- storing only latest topic percentages;
- destructive rewriting of attempt history.
