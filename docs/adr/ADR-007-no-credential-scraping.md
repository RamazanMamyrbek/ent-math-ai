# ADR-007 — No Credential Scraping

**Status:** Accepted  
**Date:** 6 October 2026

## Context

The product may benefit from official/third-party assessment results, but asking users for external passwords creates security, privacy, legal and operational risk.

## Decision

ENT Math AI will not collect or store NTC or other third-party credentials for scraping.

Allowed approaches:

```text
user-uploaded result/report
manual entry
official documented API
official OAuth-like integration if available
```

## Consequences

Positive:

- lower security risk;
- clearer trust boundary;
- avoids brittle private scraping.

Negative:

- less automated acquisition of external data until official integration exists.

## Alternatives Rejected

- password collection;
- browser automation against private accounts;
- unofficial credential-based scraping.
