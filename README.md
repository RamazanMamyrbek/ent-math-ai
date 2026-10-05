# ENT Math AI

ENT Math AI is an adaptive bilingual learning platform for Kazakhstan's profile mathematics ENT preparation.

## Tech Stack
- **Backend**: Java 21, Spring Boot 3.4.0, Spring Modulith, Maven
- **Frontend**: Next.js 15, React 19, TypeScript
- **Infrastructure**: PostgreSQL 18, MinIO, Docker Compose

## Prerequisites
- Docker & Docker Compose
- JDK 21+
- Node.js 22+

## Running Locally

### 1. Infrastructure Services
Start the PostgreSQL and MinIO containers:
```bash
docker-compose up -d
```

### 2. Backend
Navigate to the `backend` directory and run with Maven:
```bash
cd backend
mvnw.cmd spring-boot:run
```
*(On Unix/macOS, use `./mvnw spring-boot:run`)*

The backend server will start on port 8080.
- System Status: [http://localhost:8080/api/v1/system/status](http://localhost:8080/api/v1/system/status)

### 3. Frontend
Navigate to the `frontend` directory, install dependencies, and start the Next.js dev server:
```bash
cd frontend
npm install
npm run dev
```

The frontend will be available at [http://localhost:3000](http://localhost:3000).

## Testing

**Backend Tests:**
```bash
cd backend
mvnw.cmd verify
```
*(Tests use Testcontainers. Note: if Docker is unavailable in your environment, container tests will be automatically skipped.)*

**Frontend Tests (Playwright):**
```bash
cd frontend
npm run test:e2e
```
