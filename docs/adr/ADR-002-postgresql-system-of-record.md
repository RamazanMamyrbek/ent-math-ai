# ADR-002 — PostgreSQL as System of Record

**Status:** Accepted  
**Date:** 6 October 2026

## Context

The product needs transactional consistency for accounts, attempts, assessments, content versions and immutable learning evidence. It also needs relational querying, migrations, sessions and a simple async-job mechanism.

## Decision

Use **PostgreSQL** as the primary system of record for Closed Alpha.

It stores:

```text
identity
curriculum metadata
content metadata
attempts
assessments
learning evidence
mastery projections
plans
sessions
async job metadata
```

Binary files live in object storage.

## Consequences

Positive:

- strong relational model;
- transactions;
- mature tooling;
- fewer infrastructure components;
- supports JSONB for bounded flexible metadata.

Negative:

- not optimized for every future analytical/queue workload.

## Alternatives Rejected

- MongoDB as primary store;
- separate database per module;
- event store as primary model;
- early multi-database architecture.

## Revisit When

Measured scale or workload isolation requires a specialized store.
