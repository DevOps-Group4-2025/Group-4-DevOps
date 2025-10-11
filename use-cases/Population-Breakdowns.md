# GROUP 4 — Population Breakdowns (in cities vs not)

---

## USE CASE 23: Produce a Report on Population Breakdown by Continent

### CHARACTERISTIC INFORMATION

**Goal in Context**  
As a *demographic analyst* I want *to produce a report showing total population, population in cities, and population not in cities for each continent* so that *I can analyze urbanization trends globally.*

**Scope**  
World demographic reporting system.

**Level**  
Primary task.

**Preconditions**  
- Database contains up-to-date population data for all countries and cities, categorized by continent.  
- Data on urban and non-urban populations is available.

**Success End Condition**  
A report is available showing, for each continent:  
- Total population  
- Population in cities (+%)  
- Population not in cities (+%)

**Failed End Condition**  
Report cannot be generated or contains incomplete data.

**Primary Actor**  
Demographic Analyst.

**Trigger**  
A request is made for a population breakdown by continent.

### MAIN SUCCESS SCENARIO
1. Analyst requests population breakdown by continent.  
2. System retrieves total population for each continent.  
3. System retrieves population in cities for each continent.  
4. System calculates percentages for in-city and not-in-city populations.  
5. System compiles and presents the data in a structured report.

### EXTENSIONS
- **2a.** Missing data → system notifies analyst and requests update.  
- **5a.** Analyst may choose report output format (PDF, CSV, or dashboard).

### SUB-VARIATIONS
- Report may include historical trends by year.  
- Continent selection may be filtered.

### SCHEDULE
**DUE DATE:** Release 1.0 for Group 4 Use Case 23  

---

## USE CASE 24: Produce a Report on Population Breakdown by Region

### CHARACTERISTIC INFORMATION

**Goal in Context**  
As a *regional data officer* I want *to generate a report showing total population, population in cities, and population not in cities for each region* so that *I can support regional planning and policy development.*

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
Regional Data Officer.

**Trigger**  
Request for regional population breakdown report.

### MAIN SUCCESS SCENARIO
1. Officer requests regional population breakdown.  
2. System collects total population per region.  
3. System collects urban and non-urban population data.  
4. System computes urbanization percentages.  
5. Report is generated and made available.

### EXTENSIONS
- **3a.** Some regions lack data → system highlights gaps.  
- **5a.** Officer selects preferred output format.

### SUB-VARIATIONS
- Regions can be filtered by continent or country groupings.  
- Report can include trend comparison between regions.

### SCHEDULE
**DUE DATE:** Release 1.0 for Group 4 Use Case 24  

---

## USE CASE 25: Produce a Report on Population Breakdown by Country

### CHARACTERISTIC INFORMATION

**Goal in Context**  
As a *national statistics officer* I want *to produce a report showing total population, population in cities, and population not in cities for each country* so that *I can assess national urbanization patterns.*

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
National Statistics Officer.

**Trigger**  
A request for population breakdown by country.

### MAIN SUCCESS SCENARIO
1. Officer requests country population breakdown.  
2. System retrieves total, in-city, and not-in-city population figures.  
3. System calculates corresponding percentages.  
4. System compiles and formats the report.  
5. Report is provided for analysis or publication.

### EXTENSIONS
- **2a.** If some data missing, system marks incomplete entries.  
- **4a.** Officer may export to Excel, PDF, or online dashboard.

### SUB-VARIATIONS
- Country selection may be filtered by continent or region.  
- Report may show historical comparisons.

### SCHEDULE
**DUE DATE:** Release 1.0 for Group 4 Use Case 25  

---
