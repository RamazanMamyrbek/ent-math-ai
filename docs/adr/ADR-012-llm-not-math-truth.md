# ADR-012 — LLM Cannot Determine Mathematical Truth

**Status:** Accepted  
**Date:** 6 October 2026

## Context

LLMs are useful for explanation and extraction but can produce confident mathematical errors. A high-stakes learning product cannot let a probabilistic language model define answer correctness.

## Decision

Mathematical correctness is determined only by verified content and deterministic validators.

The LLM may:

```text
hint
explain
clarify
classify possible error
extract structured external data
```

The LLM may not:

```text
mark answer correct
change AnswerKey
change mastery directly
publish content automatically
```

Tutor output is anchored to verified solution context and passes semantic validation.

## Consequences

Positive:

- stronger trust;
- provider independence;
- repairable Tutor failures;
- AI outages do not break practice correctness.

Negative:

- validator/content infrastructure requires more engineering.

## Alternatives Rejected

- ask the LLM whether an answer is correct;
- use LLM self-verification as production truth.
