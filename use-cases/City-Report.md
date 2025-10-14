
# USE CASE: 7 - All the cities in the world organised by largest population to smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst I want to produce a report of all the cities in the world organised by largest population to smallest so that I can assess global urban populations.

### Scope

World demographic reporting system.

### Level

Primary task

### Preconditions

Dataset contains all city names with populations.

### Success End Condition

A complete report is generated listing all cities with population figures and ranked descending.

### Failed End Condition

No report produced.

### Primary Actor

Data Analyst

### Trigger

Analyst initiates “Generate City Report”

## MAIN SUCCESS SCENARIO

1. Analyst opens the system and selects **City Report**.
2. System pulls city/population info from the database.
3. System computes ranking by population.
4. System generates the report with columns : Name, Country, District and Population

## EXTENSIONS

2. **Data is unreachable**:
    1. System aborts as the source is unavailable.

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: *Relase 1.0

---

# USE CASE: 8 - All the cities in a continent organised by largest population to smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst I want to produce a report of all the cities in a continent organised by largest population to smallest so that I can compare urban populations.

### Scope

World demographic reporting system.

### Level

Primary task

### Preconditions

Dataset contains all city names of the given continent and their population.

### Success End Condition

A complete report is generated listing all cities in the continent with population figures and ranked descending.

### Failed End Condition

No report produced.

### Primary Actor

Data Analyst

### Trigger

Analyst initiates “Generate City Report by a continent”

## MAIN SUCCESS SCENARIO

1. Analyst opens the system and selects **City Report by a continent**.
2. Analyst enters a continent name.
3. System pulls city/population info from the database.
4. System computes ranking by population.
5. System generates the report with columns : Name, Country, District and Population

## EXTENSIONS

2. **Incorrect entry**:
    1. System gives a warning and ask for an re-entry.
3. **Data is unreachable**:
    1. System aborts as the source is unavailable.

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: *Relase 1.0

---

# USE CASE: 9 - All the cities in a region organised by largest population to smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst I want to produce a report of all the cities in a region organised by largest population to smallest so that I can assess urban populations within a region.

### Scope

World demographic reporting system.

### Level

Primary task

### Preconditions

Dataset contains all city names of the given region and their population.

### Success End Condition

A complete report is generated listing all cities in the region with population figures and ranked descending.

### Failed End Condition

No report produced.

### Primary Actor

Data Analyst

### Trigger

Analyst initiates “Generate City Report by a region”

## MAIN SUCCESS SCENARIO

1. Analyst opens the system and selects **City Report by a region**.
2. Analyst enters a region name.
3. System pulls city/population info from the database.
4. System computes ranking by population.
5. System generates the report with columns : Name, Country, District and Population

## EXTENSIONS

2. **Incorrect entry**:
    1. System gives a warning and ask for an re-entry.
3. **Data is unreachable**:
    1. System aborts as the source is unavailable.

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: *Relase 1.0

---

# USE CASE: 10 - All the cities in a country organised by largest population to smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst I want to produce a report of all the cities in a country organised by largest population to smallest so that I can assess national urban population.

### Scope

World demographic reporting system.

### Level

Primary task

### Preconditions

Dataset contains all city names of the given country and their population.

### Success End Condition

A complete report is generated listing all cities in the country with population figures and ranked descending.

### Failed End Condition

No report produced.

### Primary Actor

Data Analyst

### Trigger

Analyst initiates “Generate City Report by a country”

## MAIN SUCCESS SCENARIO

1. Analyst opens the system and selects **City Report by a country**.
2. Analyst enters a country name.
3. System pulls city/population info from the database.
4. System computes ranking by population.
5. System generates the report with columns : Name, Country, District and Population

## EXTENSIONS

2. **Incorrect entry**:
    1. System gives a warning and ask for an re-entry.
3. **Data is unreachable**:
    1. System aborts as the source is unavailable.

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: *Relase 1.0

---

# USE CASE: 11 - All the cities in a district organised by largest population to smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst I want to produce a report of all the cities in a district organised by largest population to smallest so that I can assess local urban population.

### Scope

World demographic reporting system.

### Level

Primary task

### Preconditions

Dataset contains all city names of the given district and their population.

### Success End Condition

A complete report is generated listing all cities in the district with population figures and ranked descending.

### Failed End Condition

No report produced.

### Primary Actor

Data Analyst

### Trigger

Analyst initiates “Generate City Report by a district”

## MAIN SUCCESS SCENARIO

1. Analyst opens the system and selects **City Report by a district**.
2. Analyst enters a district name.
3. System pulls city/population info from the database.
4. System computes ranking by population.
5. System generates the report with columns : Name, Country, District and Population

## EXTENSIONS

2. **Incorrect entry**:
    1. System gives a warning and ask for an re-entry.
3. **Data is unreachable**:
    1. System aborts as the source is unavailable.

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: *Relase 1.0

---

# USE CASE: 12 - The top N populated cities in the world where N is provided by the user

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst I want to produce a report of the top N populated cities in the world so that I can identify the largest cities globally.

### Scope

World demographic reporting system.

### Level

Primary task

### Preconditions

Dataset contains all city names with populations.

### Success End Condition

A complete report is generated listing top N number of cities with population figures and ranked descending.

### Failed End Condition

No report produced.

### Primary Actor

Data Analyst

### Trigger

Analyst initiates “Generate City Report by top N”

## MAIN SUCCESS SCENARIO

1. Analyst opens the system and selects **City Report by top N**.
2. Analyst enter a number.
3. System pulls city/population info from the database.
4. System computes ranking by population and takes only top N.
5. System generates the report with columns : Name, Country, District and Population

## EXTENSIONS

2. **Incorrect entry**:
    1. System gives a warning and ask for an re-entry.
3. **Data is unreachable**:
    1. System aborts as the source is unavailable.

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: *Relase 1.0

---

# USE CASE: 13 - The top N populated cities in a continent where N is provided by the user

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst I want to produce a report of the top N populated cities in a continent so that I can find the major cities in each continent.

### Scope

World demographic reporting system.

### Level

Primary task

### Preconditions

Dataset contains all city names of the given continent and their population.

### Success End Condition

A complete report is generated listing top N number of cities in the continent with population figures and ranked descending.

### Failed End Condition

No report produced.

### Primary Actor

Data Analyst

### Trigger

Analyst initiates “Generate City Report by top N in a continent”

## MAIN SUCCESS SCENARIO

1. Analyst opens the system and selects **City Report by top N in a continent**.
2. Analyst enters a continent name then a number.
3. System pulls city/population info from the database.
4. System computes ranking by population.
5. System generates the report with columns : Name, Country, District and Population

## EXTENSIONS

2. **Incorrect entry**:
    1. System gives a warning and ask for an re-entry.
3. **Data is unreachable**:
    1. System aborts as the source is unavailable.

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: *Relase 1.0

---

# USE CASE: 14 - The top N populated cities in a region where N is provided by the user

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst I want to produce a report of the top N populated cities in a region so that I can assess urban centres in a region.

### Scope

World demographic reporting system.

### Level

Primary task

### Preconditions

Dataset contains all city names of the given region and their population.

### Success End Condition

A complete report is generated listing top N number of cities in the region with population figures and ranked descending.

### Failed End Condition

No report produced.

### Primary Actor

Data Analyst

### Trigger

Analyst initiates “Generate City Report by top N in a region”

## MAIN SUCCESS SCENARIO

1. Analyst opens the system and selects **City Report by top N in a region**.
2. Analyst enters a region name then a number.
3. System pulls city/population info from the database.
4. System computes ranking by population.
5. System generates the report with columns : Name, Country, District and Population

## EXTENSIONS

2. **Incorrect entry**:
    1. System gives a warning and ask for an re-entry.
3. **Data is unreachable**:
    1. System aborts as the source is unavailable.

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: *Relase 1.0

---

# USE CASE: 15 - The top N populated cities in a country where N is provided by the user

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst I want to produce a report of the top N populated cities in a country so that I can assess the largest cities of the country.

### Scope

World demographic reporting system.

### Level

Primary task

### Preconditions

Dataset contains all city names of the given country and their population.

### Success End Condition

A complete report is generated listing top N number of cities in the country with population figures and ranked descending.

### Failed End Condition

No report produced.

### Primary Actor

Data Analyst

### Trigger

Analyst initiates “Generate City Report by top N in a country”

## MAIN SUCCESS SCENARIO

1. Analyst opens the system and selects **City Report by top N in a country**.
2. Analyst enters a country name then a number.
3. System pulls city/population info from the database.
4. System computes ranking by population.
5. System generates the report with columns : Name, Country, District and Population

## EXTENSIONS

2. **Incorrect entry**:
    1. System gives a warning and ask for an re-entry.
3. **Data is unreachable**:
    1. System aborts as the source is unavailable.

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: *Relase 1.0

---

# USE CASE: 16 - The top N populated cities in a district where N is provided by the user

## CHARACTERISTIC INFORMATION

### Goal in Context

As an analyst I want to produce a report of the top N populated cities in a district so that I can assess the largest cities of the district.

### Scope

World demographic reporting system.

### Level

Primary task

### Preconditions

Dataset contains all city names of the given district and their population.

### Success End Condition

A complete report is generated listing top N number of cities in the district with population figures and ranked descending.

### Failed End Condition

No report produced.

### Primary Actor

Data Analyst

### Trigger

Analyst initiates “Generate City Report by top N in a district”

## MAIN SUCCESS SCENARIO

1. Analyst opens the system and selects **City Report by top N in a district**.
2. Analyst enters a district name then a number.
3. System pulls city/population info from the database.
4. System computes ranking by population.
5. System generates the report with columns : Name, Country, District and Population

## EXTENSIONS

2. **Incorrect entry**:
    1. System gives a warning and ask for an re-entry.
3. **Data is unreachable**:
    1. System aborts as the source is unavailable.

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: *Relase 1.0