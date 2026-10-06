# EP-0002 — Identity & Onboarding

## Status
Active

## Goal
Implement the Authentication and User Onboarding flows defined in Phase 1 of the Roadmap.

## Current State
Phase 0 completed. Repository is booted, tests and CI are green. Walking skeleton exists but no real business logic.

## Relevant Docs
- `AGENTS.md`
- `backend/AGENTS.md`
- `frontend/AGENTS.md`
- `docs/03_DOMAIN.md`
- `docs/07_MVP_SCOPE.md`
- `docs/09_ARCHITECTURE.md`
- `docs/10_ROADMAP.md`
- `docs/adr/ADR-003-session-authentication.md`

## Constraints
- Must use Spring Security + Spring Session JDBC.
- Session cookie must be HttpOnly, Secure (in prod), suitable SameSite. JS must not read it.
- CSRF protection via separate `XSRF-TOKEN` cookie.
- Flyway is the single source of truth for schema. Spring Session schema initialization must be disabled (`spring.session.jdbc.initialize-schema=never`).
- Module boundaries should be respected (`identity` module).

## Non-Goals
- Password recovery (email flows deferred).
- OAuth2 (Google/Apple login).
- RBAC complex roles (just basic Student role for now).
- Curriculum, questions, practice, LearningEvidence, mastery, Tutor, Import.

## Implementation Steps
1. **Infrastructure**: Add `spring-boot-starter-security` and `spring-session-jdbc` to `pom.xml`. Set `spring.session.jdbc.initialize-schema=never`.
2. **Database**: Update Flyway migrations for `UserAccount`, `StudentProfile`, `StudyGoal`, and Spring Session tables matching exactly the domain requirements.
3. **Domain Layer**: Create `identity` module with models (`UserAccount`, `StudentProfile`, `StudyGoal`) and repositories. No giant `User` entity.
4. **Security Config**: Configure `SecurityFilterChain` for JSON API (return 401 instead of redirect), CSRF, password hashing (Standard Spring PasswordEncoder), email normalization, and session management.
5. **Controllers**: Implement auth (`/register`, `/login`, `/session`, `/logout`) and profile (`/me`, `/me/profile`, `/me/goal`) endpoints with strict ownership checks (no `studentId` from client).
6. **Frontend**: Build UI pages (`/register`, `/login`, `/onboarding`, `/today`) and API integration. `/today` shows Phase 1 empty state.
7. **E2E Tests**: Add Playwright test for registration -> onboarding -> `/today` -> reload -> logout -> login.

## Data Changes
New tables: `user_account`, `student_profile`, `study_goal`, `spring_session`, `spring_session_attributes`.

## API Changes
- `POST /api/v1/auth/register`
- `POST /api/v1/auth/login`
- `POST /api/v1/auth/logout`
- `GET  /api/v1/auth/session`
- `GET  /api/v1/me`
- `PATCH /api/v1/me/profile`
- `GET  /api/v1/me/goal`
- `PUT  /api/v1/me/goal`

## Tests
- Backend Unit & Integration tests for Authentication and strict ownership.
- Architecture tests (Modulith).
- Playwright E2E tests in Frontend.

## Browser Verification
- Login/Register forms check.
- Cookie persistence after reload.
- Protected routes redirect to login if no session.

## Observability
- Log authentication failures.

## Acceptance Criteria
- регистрация нового пользователя работает;
- duplicate email корректно отклоняется;
- пароль хранится только как hash;
- login с правильным password работает;
- login с неправильным password возвращает корректную ошибку;
- authenticated session сохраняется после reload;
- session cookie HttpOnly;
- CSRF-защита реально работает для mutating endpoints;
- `/api/v1/me/**` без login → 401;
- protected frontend route без session → login;
- onboarding сохраняет StudentProfile;
- onboarding сохраняет активный StudyGoal;
- повторное открытие onboarding не создаёт duplicate profile/goal;
- logout инвалидирует session;
- повторный login работает;
- Modulith verification green;
- backend integration tests green;
- Playwright E2E green;
- GitHub Actions backend/frontend green.

## Progress
- [x] EP-0002 planned
- [x] Dependencies added
- [x] Migrations applied
- [x] Domain & Config complete
- [x] Backend API complete
- [x] Frontend UI complete
- [ ] E2E Tests green

## Decisions
- Spring Session uses PostgreSQL (default schema) created via Flyway.
- CSRF protection enabled via CookieCsrfTokenRepository.
- Identity module separated from rest of the app.
- Email is normalized before saving.

## Final Verification
Pending. Running integration tests and Playwright E2E tests.
