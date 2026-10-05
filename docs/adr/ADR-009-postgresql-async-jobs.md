# ADR-009 — PostgreSQL-Backed Async Jobs

**Status:** Accepted  
**Date:** 6 October 2026

## Context

Import extraction, document processing and some rebuilds are asynchronous, but Alpha does not justify a message broker.

## Decision

Use a PostgreSQL-backed job table with worker processes from the same backend artifact.

Job claiming uses safe locking such as:

```text
FOR UPDATE SKIP LOCKED
```

Jobs must be idempotent, retry-aware and observable.

## Consequences

Positive:

- no broker infrastructure;
- transactional job creation;
- simple local development;
- sufficient for Alpha scale.

Negative:

- not intended for very high-throughput messaging.

## Alternatives Rejected

- Kafka;
- RabbitMQ;
- SQS from day one;
- in-memory-only jobs.

## Revisit When

Queue throughput, contention, fan-out or independent service consumers justify a broker.
