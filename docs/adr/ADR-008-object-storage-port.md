# ADR-008 — S3-Compatible Object Storage Abstraction

**Status:** Accepted  
**Date:** 6 October 2026

## Context

Assessment imports include screenshots, images and PDFs. Keeping large binary objects directly in PostgreSQL or coupling the domain to one cloud vendor is unnecessary.

## Decision

Use a `BlobStoragePort` abstraction.

Local:

```text
MinIO
```

Production:

```text
S3-compatible / cloud object storage adapter
```

Raw uploads remain private and are accessed through short-lived signed URLs.

## Consequences

Positive:

- efficient binary storage;
- local/production parity;
- cloud portability;
- supports retention/deletion.

Negative:

- another infrastructure dependency.

## Alternatives Rejected

- storing all files as DB blobs;
- public bucket URLs;
- provider-specific storage API throughout business code.
