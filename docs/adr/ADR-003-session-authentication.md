# ADR-003 — Session Authentication for Web Alpha

**Status:** Accepted  
**Date:** 6 October 2026

## Context

Closed Alpha is a first-party web application. The browser and backend are under our control. JWT-based stateless auth would add token rotation, client storage and revocation complexity without a demonstrated need.

## Decision

Use:

```text
Spring Security
Spring Session JDBC
secure HttpOnly cookie
CSRF protection
```

Sessions are stored in PostgreSQL.

## Consequences

Positive:

- simple browser security model;
- no access token in localStorage;
- easy revocation;
- supports multiple API replicas later.

Negative:

- server-side session persistence is required.

## Alternatives Rejected

- JWT access/refresh tokens by default;
- localStorage bearer tokens;
- custom authentication protocol.

## Revisit When

A native/mobile/public API use case requires a different authentication boundary.
