# Testing & Execution Architecture

This document explains how the project verifies functionality across layers, how the in-memory H2 database underpins test isolation, and how Docker-based automation replicates the test run inside containers. Share this with anyone onboarding to ensure they understand the full testing flow.

---

## 1. High-Level Flow

1. **Local Maven tests** (`mvn -q "-Dspring.profiles.active=test" test`)
   - Loads the Spring context defined in `TestApplication`.
   - Builds an in-memory H2 schema from entity metadata.
   - Executes service, controller, and repository tests against H2.

2. **Docker Compose tests** (`docker compose -f docker-compose.test.yml up --build --abort-on-container-exit`)
   - Spins up the Maven image defined in `docker-compose.test.yml`.
   - Mounts the repository and runs the exact Maven command above inside a container.
   - Ensures the CI-like environment mirrors local behaviour.

3. **Interactive runs** (for manual demos or QA)
   - `docker compose run --rm -e INTERACTIVE_MENU=true app`
   - Launches the Spring Boot app with the menu loop enabled (`INTERACTIVE_MENU=true`).

---

## 2. Test Structure

Our tests are scoped by layer, all under `src/test/java/com/napier/devops`:

| Directory | Focus | Notes |
|-----------|-------|-------|
| `controller/` | REST layer | e.g. `CapitalControllerTest` verifies request handling + JSON-style DTO responses.
| `service/` | Business logic | e.g. `CapitalCityServiceTest` seeds H2 directly through repositories, asserts ordering and validation.
| `repository/` | Data access integration | e.g. `CountryRepositoryIT` confirms Spring Data custom queries execute with the real context.

### 2.1 Bootstrapping with `TestApplication`

- Located at `src/test/java/com/napier/devops/TestApplication.java`.
- Minimal Spring Boot configuration that imports all components except the main `Group4Application` runner.
- Allows tests to autowire controllers/services/repositories without executing the production command-line logic.

### 2.2 Shared Patterns

- **Explicit builders** within tests craft entity instances so expectations stay readable.
- **H2 auto DDL** is triggered via `spring.jpa.hibernate.ddl-auto=create-drop`, so tables are regenerated per test run.
- **Profile isolation** ensures production and test configs never collide.

---

## 3. H2 Test Database Deep Dive

### 3.1 Purpose

- Provides a lightweight substitute for MySQL during automated tests.
- Keeps tests deterministic: each run starts with a clean schema, no external dependencies.
- Emulates MySQL syntax via `MODE=MySQL`, so our JPQL/native queries behave the same as in production.

### 3.2 Configuration

- Defined in `src/test/resources/application-test.properties`:
  ```properties
  spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MySQL
  spring.jpa.hibernate.ddl-auto=create-drop
  spring.sql.init.mode=never
  ```

- Key settings:
  - `mem:testdb` creates an in-memory DB unique to the JVM lifecycle.
  - `DB_CLOSE_DELAY=-1` keeps the DB alive for the whole test suite instead of per-connection.
  - `MODE=MySQL` aligns identifier quoting, function names, and SQL grammar.
  - `create-drop` rebuilds the schema for each suite, mirroring entity annotations.

### 3.3 How Tests Use H2

- Service tests directly call repositories to seed data using standard Spring Data APIs.
- Repository tests execute actual JPA queries, so any SQL mistakes surface immediately.
- All results retrieved from H2 mimic what a MySQL instance would return, letting us validate conversion to DTOs or response payloads.

---

## 4. Docker-Based Test Execution

### 4.1 `docker-compose.test.yml`

```yaml
version: '3.8'

services:
  tests:
    image: maven:3.9-eclipse-temurin-17
    working_dir: /app
    volumes:
      - ./:/app
      - maven-repo:/root/.m2
    command: mvn -q -Dspring.profiles.active=test test

volumes:
  maven-repo:
    driver: local
```

- **Image**: official Maven 17 image, ensuring a clean environment.
- **Volumes**:
  - `./:/app`: mounts the project so the container sees the current branch.
  - `maven-repo`: caches dependencies between runs, speeding up subsequent executions.
- **Command**: runs the same Maven test command we use locally.

### 4.2 Usage

1. `docker compose -f docker-compose.test.yml up --build --abort-on-container-exit`
2. Watch the logs for JVM output and Spring Boot startup.
3. Container exits with code 0 if the suite passes; Docker tears it down.

This matches what a CI server would do, so passing locally here is a strong signal the pipeline will stay green.

---

## 5. Interactive Menu Runs Inside Docker

- The production `Group4Application` auto-runs Use Case 1 then exits.
- We added `INTERACTIVE_MENU=true` (or `--interactive`) to enter the menu loop.
- In containers, use:
  ```bash
  docker compose run --rm -e INTERACTIVE_MENU=true app
  ```
- You’ll see menu options, including:
  - `21. Top N capital cities in a continent`
  - `23-25. Population breakdowns`
  - `100. Exit`

- Option 21 now calls `CapitalCityService`, prompting for continent and N, then prints a table of results.

---

## 6. Quick Reference Commands

| Scenario | Command |
|----------|---------|
| Full local suite | `mvn -q "-Dspring.profiles.active=test" test` |
| Single test class | `mvn -q "-Dspring.profiles.active=test" -Dtest=CapitalCityServiceTest test` |
| Dockerised tests | `docker compose -f docker-compose.test.yml up --build --abort-on-container-exit` |
| Interactive menu in Docker | `docker compose run --rm -e INTERACTIVE_MENU=true app` |

---

## 7. Key Takeaways for the Team

- **Layered tests** give us coverage over integration points, not just unit logic.
- **H2 mode** mirrors MySQL closely without requiring a heavy container for every test run.
- **Docker Compose** test service ensures what we run locally is identical to CI.
- **Interactive flag** lets QA and demos exercise the menu without editing code.

Understanding this structure helps everyone extend the app (new use cases, queries, endpoints) with confidence, knowing exactly which test bucket to target and how to run it quickly.

