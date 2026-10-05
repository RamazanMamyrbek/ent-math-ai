# frontend/AGENTS.md
## ENT Math AI Frontend Rules

> Scope: everything under `/frontend`.  
> Root `AGENTS.md` remains mandatory.

# 1. Stack

```text
Next.js 16.x
React 19
TypeScript strict
App Router
TanStack Query
React Hook Form
Zod
Tailwind CSS
shadcn/ui
KaTeX
Playwright
```

Do not replace the stack as part of normal feature work.

# 2. Source of Truth

The backend owns domain truth.

Frontend must NOT reimplement:

```text
answer validation
mastery
confidence
retrievability
recommendation scoring
readiness calculation
Tutor hint policy
```

Frontend renders and orchestrates user interaction.

# 3. Feature-Oriented Structure

Preferred shape:

```text
src/
├── app/
├── features/
│   ├── auth/
│   ├── onboarding/
│   ├── diagnostic/
│   ├── practice/
│   ├── today/
│   ├── tutor/
│   ├── imports/
│   ├── progress/
│   └── assessments/
├── entities/
├── shared/
└── generated/
```

Do not create a giant `components/` directory containing the whole product.

# 4. Server State

Use TanStack Query for backend/server state.

Avoid giant global client stores.

Local component state is appropriate for:

```text
dialog open state
temporary form state
filters
presentation state
```

# 5. API Client

Prefer generated or typed API contracts.

Do not scatter raw `fetch()` calls through UI components.

Centralize transport concerns:

```text
base URL
CSRF
error mapping
credentials
trace handling
```

# 6. Error Handling

Branch on backend error code, not message text.

Example:

```text
IMPORT_REQUIRES_REVIEW
SESSION_EXPIRED
QUESTION_NOT_APPROVED
```

User-visible messages are localized in frontend.

# 7. Authentication

Authentication is session-cookie based.

Do not store auth tokens in localStorage.

Do not invent JWT client logic.

# 8. Bilingual UI

Core product supports:

```text
KK
RU
```

All core screens must have both locales before considered complete.

Do not use runtime machine translation as the permanent product localization layer.

# 9. Math

Render controlled mathematical markup with KaTeX.

Do not render raw untrusted HTML.

Handle formula overflow on mobile.

# 10. Practice Screen

Must work well on:

```text
360px mobile width
desktop
```

Do not send hidden answer data to the browser before policy allows it.

Correctness comes from backend response.

# 11. Tutor UI

Tutor is contextual to the current question.

Do not create a generic empty-chat-first home screen.

Allowed controls:

```text
Hint
Explain
Clarify
Full Solution when policy permits
```

Do not expose internal system prompts/provider details.

# 12. Full Solution UX

When policy requires, tell the student that viewing the full solution does not count as independent mastery.

Do not use shame/guilt language.

# 13. Today

`/today` is the default authenticated learning destination once onboarding is complete.

The page should emphasize:

```text
what to do
how long it takes
why it was selected
```

Do not overload it with analytics.

# 14. Progress

Do not turn low-confidence estimates into fake precision.

Prefer labels such as:

```text
Недостаточно данных
Развивается
Сильный
Нужно повторить
```

Exact internal probabilities may be hidden or contextual.

# 15. Imports

Import is asynchronous.

UI must represent:

```text
uploading
processing
needs review
confirmed
failed
```

Do not block the browser on long AI processing.

Low-confidence extracted fields should be visibly reviewable.

# 16. Forms

Use:

```text
React Hook Form
Zod
```

where appropriate.

Frontend validation improves UX but does not replace backend validation.

# 17. Accessibility

Core flows require:

```text
semantic labels
keyboard access
visible focus
no color-only correctness
accessible error text
```

# 18. Components

Prefer composition over one giant configurable component.

Shared components should reflect repeated UI patterns, not speculative future reuse.

# 19. Loading States

Use clear states.

Do not show endless spinners when a meaningful skeleton/status is possible.

For async import, show status and allow navigation away.

# 20. Optimistic Updates

Use only where domain correctness is not at risk.

Do NOT optimistically claim:

```text
attempt correct
assessment confirmed
mastery changed
```

before backend confirms.

# 21. Security

Treat all displayed user/AI/import text as untrusted.

No `dangerouslySetInnerHTML` for untrusted content.

No secrets in frontend env variables.

# 22. Analytics

Do not send unnecessary PII to analytics.

Learning truth remains in backend domain records.

Frontend analytics may record interaction/funnel events only according to the metric contract.

# 23. Testing

As applicable:

```text
component/unit tests
contract tests
Playwright E2E
mobile viewport checks
```

Critical E2E paths eventually include:

```text
register → onboarding
diagnostic → today
practice → submit
hint flow
import → review → confirm
progress
```

# 24. TypeScript

Strict mode stays enabled.

Avoid:

```text
any
unsafe casts
silent nullable assumptions
```

unless justified and localized.

# 25. Styling

Keep UI:

```text
calm
precise
modern
focused
```

Avoid:

```text
casino gamification
excessive gradients
childish visuals
```

# 26. Definition of Done

Frontend feature is done when:

```text
API state handled
loading/error/empty states handled
RU/KK handled
mobile checked
accessibility basics checked
tests pass
browser flow verified
ExecPlan updated
```

# 27. Commands

Phase 0 will finalize the package manager and exact scripts.

Expected shape:

```bash
npm run dev
npm run lint
npm run typecheck
npm test
npm run build
```

Keep root README and this file synchronized after Phase 0.

<!-- BEGIN:nextjs-agent-rules -->

# This is NOT the Next.js you know

This version has breaking changes — APIs, conventions, and file structure may all differ from your training data. Read the relevant guide in `node_modules/next/dist/docs/` (resolved from this file's directory; in monorepos the `next` package may not be visible from the repo root) before writing any code. Heed deprecation notices.

This block is written and re-added by `next dev` — verify at `node_modules/next/dist/server/lib/generate-agent-files.js`. Removing it from a diff only re-creates the uncommitted change; committing it with your work keeps the tree clean.

<!-- END:nextjs-agent-rules -->
