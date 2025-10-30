## Group-4-DevOps

### Build Status

**Master branch:**  

[![Master build](https://img.shields.io/github/actions/workflow/status/DevOps-Group4-2025/Group-4-DevOps/main.yml?branch=master&label=master%20build&logo=github&style=flat-square)](https://github.com/DevOps-Group4-2025/Group-4-DevOps/actions/workflows/main.yml?query=branch%3Amaster)

[![LICENSE](https://img.shields.io/github/license/DevOps-Group4-2025/Group-4-DevOps.svg?style=flat-square)](https://github.com/DevOps-Group4-2025/Group-4-DevOps/blob/master/LICENSE)

[![Releases](https://img.shields.io/github/release/DevOps-Group4-2025/Group-4-DevOps/all.svg?style=flat-square)](https://github.com/DevOps-Group4-2025/Group-4-DevOps/releases)

**Develop branch:**  

[![Develop build](https://img.shields.io/github/actions/workflow/status/DevOps-Group4-2025/Group-4-DevOps/main.yml?branch=develop&label=develop%20build&logo=github&style=flat-square)](https://github.com/DevOps-Group4-2025/Group-4-DevOps/actions/workflows/main.yml?query=branch%3Adevelop)

**Milestone**

![CR1 progress](https://img.shields.io/github/milestones/progress-percent/DevOps-Group4-2025/Group-4-DevOps/1?label=CR1%20progress)
![CR1 closed](https://img.shields.io/github/milestones/progress/DevOps-Group4-2025/Group-4-DevOps/1?label=CR1%20closed)

## Reporting Bugs

If you find a bug, please open an issue using our [Bug Report template](link-to-template). Include steps to reproduce, expected behaviour, and screenshots if possible.


**Status:**  
✅ **4 of 32 requirements implemented — 12.5% complete**

| ID | Requirement | Met | Screenshot |
|----|--------------|-----|-------------|
| 1 | All the countries in the world organised by largest population to smallest. | ✅ Yes | ![Req1](images/req1.png) |
| 2 | All the countries in a continent organised by largest population to smallest. | ⚙️ In Progress |  |
| 3 | All the countries in a region organised by largest population to smallest. | ⚙️ In Progress |  |
| 4 | The top N populated countries in the world where N is provided by the user. | ⚙️ In Progress |  |
| 5 | The top N populated countries in a continent where N is provided by the user. | ⚙️ In Progress |  |
| 6 | The top N populated countries in a region where N is provided by the user. | ⚙️ In Progress |  |
| 7 | All the cities in the world organised by largest population to smallest. | ⚙️ In Progress |  |
| 8 | All the cities in a continent organised by largest population to smallest. | ⚙️ In Progress |  |
| 9 | All the cities in a region organised by largest population to smallest. | ⚙️ In Progress |  |
| 10 | All the cities in a country organised by largest population to smallest. | ⚙️ In Progress |  |
| 11 | All the cities in a district organised by largest population to smallest. | ⚙️ In Progress |  |
| 12 | The top N populated cities in the world where N is provided by the user. | ⚙️ In Progress |  |
| 13 | The top N populated cities in a continent where N is provided by the user. | ⚙️ In Progress |  |
| 14 | The top N populated cities in a region where N is provided by the user. | ⚙️ In Progress |  |
| 15 | The top N populated cities in a country where N is provided by the user. | ⚙️ In Progress |  |
| 16 | The top N populated cities in a district where N is provided by the user. | ⚙️ In Progress |  |
| 17 | All the capital cities in the world organised by largest population to smallest. | ⚙️ In Progress |  |
| 18 | All the capital cities in a continent organised by largest population to smallest. | ⚙️ In Progress |  |
| 19 | All the capital cities in a region organised by largest to smallest. | ⚙️ In Progress |  |
| 20 | The top N populated capital cities in the world where N is provided by the user. | ⚙️ In Progress |  |
| 21 | The top N populated capital cities in a continent where N is provided by the user. | ⚙️ In Progress |  |
| 22 | The top N populated capital cities in a region where N is provided by the user. | ⚙️ In Progress |  |
| 23 | The population of people, people living in cities, and people not living in cities in each continent. | ✅ Yes | ![Req23](images/req23.png) |
| 24 | The population of people, people living in cities, and people not living in cities in each region. | ✅ Yes | ![Req24](images/req24.png) |
| 25 | The population of people, people living in cities, and people not living in cities in each country. | ✅ Yes | ![Req25](images/req25.png) |
| 26 | Validation of database connectivity (MySQL world dataset). | ⚙️ In Progress |  |
| 27 | Implementation of Docker-based deployment. | ⚙️ In Progress |  |
| 28 | Automated CI pipeline build verification (GitHub Actions). | ⚙️ In Progress |  |
| 29 | GitFlow branching strategy applied (feature/develop/master). | ⚙️ In Progress |  |
| 30 | Version tagging and release documentation. | ⚙️ In Progress |  |
| 31 | Code quality and testing (unit/integration tests). | ⚙️ In Progress |  |
| 32 | Project documentation and reproducibility. | ⚙️ In Progress |  |


## About the Project

**Group-4 DevOps** is a population insights reporting service for the SET09803 DevOps module.  
It turns the classic **MySQL “world”** database into clear, reproducible reports for analysts, with a strong focus on DevOps practice: **GitFlow** (master/develop/feature), **automated CI** with GitHub Actions, **Dockerized** runtime, and **versioned releases**.

### What it delivers
- **Countries**: all / by continent / by region + **Top-N** variants
- **Cities**: world, continent, region, country, district + **Top-N** variants
- **Capital Cities**: world / continent / region + **Top-N**
- **Population Breakdowns**: in-cities vs not-in-cities for **continent / region / country**
- **Population Totals**: world / continent / region / country / district / city
- **Languages**: speakers of Chinese, English, Hindi, Spanish, Arabic (with % of world)

### Tech stack
- **Java 17** + **Maven** (builds self-contained JAR)
- **MySQL 8** (supplied *world* dataset)
- **Docker / Docker Compose** (app + DB)
- **GitHub Actions** (build & test on `feature/*`, `develop`, and `master`)
- **Releases**: tagged and documented (e.g., `v0.1.0`)

### Why it matters
The reports support quick analysis of **urbanisation**, **regional planning**, and **language reach**, giving stakeholders a consistent, auditable way to query global population data.


