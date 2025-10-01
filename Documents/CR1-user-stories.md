## Code Review 1 – User Stories and Checklist

Purpose: Capture the Week 6/7 (CR1) workflow/user‑value outcomes and a ready‑to‑tick checklist for review readiness.

### Sprint Frame (copy into Sprint 1)
- Name: Sprint 1 (CR1)
- Dates: <start> → <end> (14 days)
- Goal: Demonstrate CR1 readiness: workflow set up, CI, Docker, GitFlow, backlog, use cases, diagram.

### Labels to create
- Type: Story, Task, Docs, Process, Bug
- Priority: P0‑Critical, P1‑High, P2‑Medium, P3‑Low
- Area: DevOps, Process, Docs
- Epic: CR1
- Sprint: Sprint‑1
- Size: 1, 2, 3, 5, 8

---

### User Stories (CR1 scope only)

1) CI validates every change (GitHub Actions)
- As a DevOps engineer, I want CI to run tests, coverage, package, and Docker build on pushes/PRs to master/develop so quality is continuously verified.
- Acceptance Criteria
  - Workflow triggers on push/PR for master and develop
  - Steps: test (JUnit5), coverage (JaCoCo), package JAR, build Docker image
  - Artifacts uploaded; checks required on protected branches

2) Runnable self‑contained JAR
- As a developer, I want a shaded JAR so the app can run with a single command.
- Acceptance Criteria
  - `mvn clean package` produces executable shaded JAR
  - `java -jar target/*.jar` prints app banner locally and in CI logs

3) Docker image builds and runs
- As a DevOps engineer, I want a Docker image so the app runs consistently across machines.
- Acceptance Criteria
  - `docker build -t goup-4:latest .` succeeds
  - `docker run goup-4:latest` starts and logs are visible
  - README contains build/run instructions

4) GitFlow branches & protections
- As a release manager, I want master/develop protections and a release branch so we can safely release.
- Acceptance Criteria
  - Branch protections: PRs required + status checks on master & develop
  - `release/0.1` created from develop

5) Product Backlog exists and is story‑driven
- As a PO, I want a product backlog so requirements are tracked as user stories.
- Acceptance Criteria
  - One issue per story with AC/DoD, labels, estimate (Size), Priority
  - Backlog visible on board, synced to Zube

6) Issues tracked on Kanban & Sprint boards (with Zube integration)
- As a PM, I want GitHub issues synced to Zube and visible on boards to manage flow.
- Acceptance Criteria
  - Kanban columns: Backlog, To‑do, In‑Progress, Review, Done
  - Sprint‑1 created (2 weeks) and populated with committed scope
  - Zube shows synced issues; movement reflects in GitHub

7) Use cases and use case diagram
- As an analyst, I want full use cases and a diagram so behavior is clear before implementation.
- Acceptance Criteria
  - Text use cases in `docs/use-cases/*.md` (main + alternate flows, pre/postconditions)
  - Diagram in `docs/diagrams/use-case.(png|svg)` (+ source .drawio/.puml)
  - README links to both

8) Governance & release basics
- As a maintainer, I want Code of Conduct and a first release so the project is presentable.
- Acceptance Criteria
  - `CODE_OF_CONDUCT.md` and `LICENSE` in repo root
  - First release `v0.1.0` created with shaded JAR attached and brief notes
  - README shows badges (build master/develop, coverage, release, license)

---

### Definition of Done (DoD) for CR1 stories
- Code/tests committed; CI green on develop
- Proper labels (Type, Priority, Size, Epic: CR1, Sprint‑1) set
- Linked to Milestone: Code Review 1
- README/docs updated when relevant (badges, instructions, links)
- Evidence attached (logs/screenshots/links to runs or releases)

---

### Ready‑to‑tick CR1 Checklist

- [ ] Repo protections: master/develop protected; required checks enabled
- [ ] `release/0.1` created from develop
- [ ] `mvn clean package` builds executable shaded JAR locally
- [ ] CI builds shaded JAR and uploads artifact
- [ ] Docker image builds (`docker build`) and runs (`docker run`) with visible logs
- [ ] Actions green on push/PR to master/develop (tests, coverage, package, docker)
- [ ] All CR1 items exist as GitHub Issues with labels, estimates, milestone, sprint
- [ ] Zube integration working; Kanban + Sprint‑1 boards active
- [ ] Use cases in `docs/use-cases/`; use case diagram in `docs/diagrams/`; README links present
- [ ] README badges visible (build master/develop, coverage, release, license)
- [ ] First release `v0.1.0` created with shaded JAR attached
- [ ] Evidence/screenshots linked in relevant issues

---

### Quick Issue Template (paste when creating stories)

Title: <Short user‑value outcome>

As a <role>, I want <capability> so that <value>.

Acceptance Criteria
- Given …
- When …
- Then …

Definition of Done
- CI green; artifacts produced where applicable
- Docs/README updated; links added
- Labels set (Story, Epic: CR1, Sprint‑1, Priority, Size)
- Milestone: Code Review 1

---

Notes
- Keep Sprint‑1 capacity ≈ 18–20 points across the team (sum of Size labels)
- Prioritize P0 (must‑have for demo), then P1 until capacity is full


