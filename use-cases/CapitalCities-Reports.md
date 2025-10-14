# GROUP 3 - Capital Cities Reports

---

## USE CASE 17: List All Capital Cities in the World by Population

### CHARACTERISTIC INFORMATION

**Goal in Context**  
As a data analyst I want to generate a list of all capital cities in the world ordered by population so that I can identify the largest capitals globally.

**Scope**  
World demographic reporting system.

**Level**  
Primary task.

**Preconditions**
- The database contains complete city and country population data.
- Each country has a valid capital city reference.

**Success End Condition**  
A list of all capital cities is displayed, sorted from largest to smallest by population.

**Failed End Condition**  
Data missing or query fails to produce an ordered list.

**Primary Actor**  
Data Analyst.

**Trigger**  
A request is made to generate a capital city report for the entire world.

### MAIN SUCCESS SCENARIO
1. The analyst selects the option for a world capital city report.
2. The system retrieves all capital cities and populations.
3. The system sorts the results by population descending.
4. The report is displayed to the analyst.

### EXTENSIONS
3a. Missing city data → mark as “Data Unavailable.”  
4a. Analyst may export report as CSV, PDF, or Excel.

### SUB-VARIATIONS
Report may include additional fields such as continent or region.

### SCHEDULE
**DUE DATE:** Release 1.0 — Use Case 17

---

## USE CASE 18: List All Capital Cities in a Continent by Population

### CHARACTERISTIC INFORMATION

**Goal in Context**  
As a data analyst I want to list all capital cities within a specific continent ordered by population so that I can compare capitals at a continental level.

**Scope**  
Continental population reporting.

**Level**  
Primary task.

**Preconditions**
- The database contains continent information for each country.
- Valid continent name is provided.

**Success End Condition**  
Report lists all capital cities in the chosen continent ordered by population.

**Failed End Condition**  
Invalid continent or missing data prevents report generation.

**Primary Actor**  
Data Analyst.

**Trigger**  
A request is made for a capital cities report by continent.

### MAIN SUCCESS SCENARIO
1. The analyst selects a continent.
2. The system validates the input.
3. The system retrieves all capital cities for that continent.
4. The system sorts them by population descending.
5. The report is displayed.

### EXTENSIONS
2a. Invalid continent → system prompts for valid input.  
5a. Analyst may export report.

### SUB-VARIATIONS
Optional filtering by region within the continent.

### SCHEDULE
**DUE DATE:** Release 1.0 — Use Case 18

---

## USE CASE 19: List All Capital Cities in a Region by Population

### CHARACTERISTIC INFORMATION

**Goal in Context**  
As a data analyst I want to view all capital cities within a selected region ordered by population so that I can analyze population distribution in that region.

**Scope**  
Regional capital reporting.

**Level**  
Primary task.

**Preconditions**
- Each country is mapped to a region in the database.

**Success End Condition**  
A sorted report of regional capital cities is displayed.

**Failed End Condition**  
Invalid region or incomplete data prevents report generation.

**Primary Actor**  
Data Analyst.

**Trigger**  
A request is made for a capital city report by region.

### MAIN SUCCESS SCENARIO
1. The analyst enters or selects a region name.
2. The system validates it.
3. The system retrieves all capital cities within that region.
4. The system sorts by population descending.
5. The report is displayed.

### EXTENSIONS
2a. Invalid region → prompt for correction.  
5a. Analyst may export report.

### SUB-VARIATIONS
Reports may include country-level filters.

### SCHEDULE
**DUE DATE:** Release 1.0 — Use Case 19

---

## USE CASE 20: Produce a Report on Top N Capital Cities in the World

### CHARACTERISTIC INFORMATION

**Goal in Context**  
As a data analyst I want to enter a number (N) and see the top N most populated capital cities in the world so that I can focus on the largest capitals.

**Scope**  
World capital reporting.

**Level**  
Primary task.

**Preconditions**
- Valid integer N is provided.
- City and country population data exist.

**Success End Condition**  
Top N world capital cities are displayed in descending order of population.

**Failed End Condition**  
Invalid N or missing data prevents report generation.

**Primary Actor**  
Data Analyst.

**Trigger**  
A request is made for the top N capital cities in the world.

### MAIN SUCCESS SCENARIO
1. The analyst inputs N.
2. The system validates N.
3. The system retrieves all capital cities globally.
4. The system orders them by population descending.
5. The system limits results to N entries.
6. The report is displayed.

### EXTENSIONS
2a. Invalid N → system asks for re-entry.  
6a. Analyst may export report.

### SUB-VARIATIONS
Report may include continent and region fields.

### SCHEDULE
**DUE DATE:** Release 1.0 — Use Case 20

---

## USE CASE 21: Produce a Report on Top N Capital Cities in a Continent

### CHARACTERISTIC INFORMATION

**Goal in Context**  
As a data analyst I want to enter a continent and number (N) to get the top N most populated capital cities in that continent so that I can evaluate population concentration by continent.

**Scope**  
Continental capital reporting.

**Level**  
Primary task.

**Preconditions**
- Valid continent name and integer N are provided.

**Success End Condition**  
Report lists the top N capital cities in the chosen continent.

**Failed End Condition**  
Invalid continent or N input.

**Primary Actor**  
Data Analyst.

**Trigger**  
A request for the top N capital cities in a continent.

### MAIN SUCCESS SCENARIO
1. The analyst enters a continent and N.
2. The system validates both inputs.
3. The system retrieves matching capital cities.
4. The system sorts by population descending.
5. The system limits results to N entries.
6. The report is displayed.

### EXTENSIONS
2a. Invalid inputs → system prompts to correct.  
6a. Analyst may export report.

### SUB-VARIATIONS
Report may include region details.

### SCHEDULE
**DUE DATE:** Release 1.0 — Use Case 21

---

## USE CASE 22: Produce a Report on Top N Capital Cities in a Region

### CHARACTERISTIC INFORMATION

**Goal in Context**  
As a data analyst I want to input a region and N to generate a report of the top N most populated capital cities in that region so that I can compare capitals regionally.

**Scope**  
Regional capital reporting.

**Level**  
Primary task.

**Preconditions**
- Valid region and integer N provided.

**Success End Condition**  
Report displays top N capital cities within the selected region.

**Failed End Condition**  
Invalid inputs or missing data.

**Primary Actor**  
Data Analyst.

**Trigger**  
A request is made for a top N regional capital city report.

### MAIN SUCCESS SCENARIO
1. The analyst inputs a region name and N.
2. The system validates both.
3. The system retrieves all capital cities for that region.
4. The system orders by population descending.
5. The system limits to N entries.
6. The report is displayed.

### EXTENSIONS
2a. Invalid region or N → system prompts for correction.  
6a. Analyst may export report.

### SUB-VARIATIONS
Report may show comparative rank by region or continent.

### SCHEDULE
**DUE DATE:** Release 1.0 — Use Case 22
