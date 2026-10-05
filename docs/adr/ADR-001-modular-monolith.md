# ADR-001 — Modular Monolith

**Status:** Accepted  
**Date:** 6 October 2026

## Context

The product has substantial domain complexity but a small initial team, one main application, modest Alpha traffic and rapidly evolving requirements.

Microservices would introduce network boundaries, distributed failure modes, deployment complexity and coordination cost before there is evidence that independent scaling is needed.

## Decision

Build the backend as a **Spring Boot modular monolith** using Spring Modulith to enforce module boundaries.

Canonical modules:

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

Use one codebase and one primary PostgreSQL database.

## Consequences

Positive:

- simpler transactions;
- easier local development;
- fewer operational components;
- domain boundaries remain explicit;
- modules can be extracted later if justified.

Negative:

- one application release deploys many modules together;
- discipline is required to prevent cross-module coupling.

## Alternatives Rejected

- microservices from day one;
- service-per-feature architecture;
- unstructured monolith.

## Revisit When

A module has independent scaling, deployment cadence, ownership and a stable API that materially justify extraction.
