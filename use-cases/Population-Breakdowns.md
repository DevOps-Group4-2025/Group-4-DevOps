# GROUP 4 — Population Breakdowns (in cities vs not)

---

## USE CASE 23: Produce a Report on Population Breakdown by Continent

### CHARACTERISTIC INFORMATION

**Goal in Context**  
As a *data analyst* I want *to produce a report showing total population, population in cities, and population not in cities for each continent* so that *I can analyze global urbanization trends.*

**Scope**  
World demographic reporting system.

**Level**  
Primary task.

**Preconditions**
- The database contains up-to-date population data for all countries and cities, categorized by continent.
- Data on urban and non-urban populations is available.

**Success End Condition**  
A report is available showing, for each continent:
- Total population
- Population in cities (+%)
- Population not in cities (+%)

**Failed End Condition**  
Report cannot be generated or contains incomplete data.

**Primary Actor**  
Data Analyst.

**Trigger**  
A request is made for a population breakdown by continent.

### MAIN SUCCESS SCENARIO
1. The analyst requests a population breakdown by continent.
2. The system retrieves total population for each continent.
3. The system retrieves population in cities for each continent.
4. The system calculates percentages for in-city and not-in-city populations.
5. The system compiles and presents the data in a structured report.

### EXTENSIONS
- **2a.** Missing data → system notifies analyst and requests update.
- **5a.** Analyst may choose report output format (PDF, CSV, or dashboard).
- **5b.** *Interactive front end*: The analyst uses a graphical dashboard or web interface to select “Continent” as the report level, and optionally filter specific continents before generating the report.

### SUB-VARIATIONS
- Report may include historical trends by year.
- Continent selection may be filtered.

### SCHEDULE
**DUE DATE:** Release 1.0 for Group 4 Use Case 23

---

## USE CASE 24: Produce a Report on Population Breakdown by Region

### CHARACTERISTIC INFORMATION

**Goal in Context**  
As a *data analyst* I want *to generate a report showing total population, population in cities, and population not in cities for each region* so that *I can support regional planning and policy development.*

**Scope**  
Regional population reporting system.

**Level**  
Primary task.

**Preconditions**
- Regional and city-level population data exist in the database.
- Each country is mapped to a region.

**Success End Condition**  
A report showing, for each region:
- Total population
- Population in cities (+%)
- Population not in cities (+%)

**Failed End Condition**  
Report not produced or regional data missing.

**Primary Actor**  
Data Analyst.

**Trigger**  
A request for a regional population breakdown report.

### MAIN SUCCESS SCENARIO
1. The analyst requests a regional population breakdown.
2. The system collects total population per region.
3. The system collects urban and non-urban population data.
4. The system computes urbanization percentages.
5. The report is generated and made available.

### EXTENSIONS
- **3a.** Some regions lack data → system highlights gaps.
- **5a.** Analyst selects preferred output format.
- **5b.** *Interactive front end*: Through the same dashboard interface, the analyst can select “Region” as the analysis level and optionally choose one or more regions to focus the report.

### SUB-VARIATIONS
- Regions can be filtered by continent or country groupings.
- The report can include trend comparisons between regions.

### SCHEDULE
**DUE DATE:** Release 1.0 for Group 4 Use Case 24

---

## USE CASE 25: Produce a Report on Population Breakdown by Country

### CHARACTERISTIC INFORMATION

**Goal in Context**  
As a *data analyst* I want *to produce a report showing total population, population in cities, and population not in cities for each country* so that *I can assess national urbanization patterns.*

**Scope**  
Country-level demographic reporting.

**Level**  
Primary task.

**Preconditions**
- Country-level and city-level population data are stored in the database.
- Urban and non-urban classifications exist.

**Success End Condition**  
A report is produced showing, for each country:
- Total population
- Population in cities (+%)
- Population not in cities (+%)

**Failed End Condition**  
Report generation fails or lacks complete data.

**Primary Actor**  
Data Analyst.

**Trigger**  
A request for a population breakdown by country.

### MAIN SUCCESS SCENARIO
1. The analyst requests a country population breakdown.
2. The system retrieves total, in-city, and not-in-city population figures.
3. The system calculates corresponding percentages.
4. The system compiles and formats the report.
5. The report is provided for analysis or publication.

### EXTENSIONS
- **2a.** If some data is missing, the system marks incomplete entries.
- **4a.** Analyst may export to Excel, PDF, or online dashboard.
- **5b.** *Interactive front end*: From the dashboard, the analyst can select “Country” as the report level, choose specific countries, and trigger generation of the detailed population breakdown.

### SUB-VARIATIONS
- Country selection may be filtered by continent or region.
- Report may show historical comparisons.

### SCHEDULE
**DUE DATE:** Release 1.0 for Group 4 Use Case 25

---
