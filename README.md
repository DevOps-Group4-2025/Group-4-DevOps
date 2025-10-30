﻿## Group-4-DevOps

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

