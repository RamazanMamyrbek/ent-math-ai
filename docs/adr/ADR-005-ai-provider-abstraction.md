# ADR-005 — AI Provider Abstraction

**Status:** Accepted  
**Date:** 6 October 2026

## Context

Gemini is the initial provider, but provider models, pricing, capabilities and availability can change. Core business logic must not depend on one provider SDK.

## Decision

All AI use goes through explicit application-facing ports.

Examples:

```text
AiTutorProvider
DocumentExtractionPort
```

The initial infrastructure adapter uses Google GenAI / Gemini.

Domain code never imports the provider SDK.

## Consequences

Positive:

- provider replaceability;
- easier testing;
- clean cost/failure handling;
- stable business contracts.

Negative:

- small abstraction layer must be maintained.

## Alternatives Rejected

- direct Gemini SDK calls from services/controllers;
- multi-provider orchestration framework before needed.
