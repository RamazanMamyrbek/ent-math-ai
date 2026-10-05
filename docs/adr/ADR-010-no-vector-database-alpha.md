# ADR-010 — No Vector Database in Alpha

**Status:** Accepted  
**Date:** 6 October 2026

## Context

Tutor context is already structured: verified question, answer, solution, skill, attempt and hint policy. The first product does not require semantic search over a massive knowledge corpus.

## Decision

Do not introduce a vector database or generic RAG layer in Closed Alpha.

Tutor context is assembled deterministically from canonical product data.

## Consequences

Positive:

- simpler architecture;
- lower cost;
- less hallucination surface;
- easier provenance.

Negative:

- broad semantic retrieval is not available.

## Alternatives Rejected

- Pinecone/Weaviate/pgvector-driven Tutor from day one;
- embedding every content object automatically.

## Revisit When

A concrete retrieval problem cannot be solved reliably through structured curriculum/content queries.
