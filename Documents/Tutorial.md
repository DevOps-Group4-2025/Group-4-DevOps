
# Tutorial: How to Add a New Query to the System

This guide demonstrates how to implement a new report by adding a new query to the system.
We will use **"List all countries in a specific continent, ordered by population"** (Use Case 2) as our example.

This tutorial walks you through adding the feature — from setting up your branch to updating the final documentation.

---

## Steps Overview

0. **Branch Workflow** – Create and manage your feature branch properly.
1. **Define the Use Case** – Ensure the new requirement is documented.
2. **Update the Repository** – Add the new query to the data access layer (JPA Repository).
3. **Implement the Service** – Create the business logic to call the repository method.
4. **Expose the Functionality** – Add the new report to the main application.
5. **Update Project Documentation** – Mark the requirement as complete in the `README.md`.

---

## Step 0: Branch Workflow

Before you start coding, make sure you’re working on the correct branch to keep your repository clean and organized.

1. **Checkout from `master` to `develop`**

   ```bash
   git checkout master
   git pull origin master
   git checkout -b develop origin/develop
   ```

2. **Create your feature branch from `develop`**
   Replace `feature/your-feature-name` with a descriptive name, e.g. `feature/usecase2-country-by-continent`.

   ```bash
   git checkout develop
   git pull origin develop
   git checkout -b feature/usecase2-country-by-continent
   ```

3. **Work on your feature**

   ```bash
   git add .
   git commit -m "Implement Use Case 2: countries in a continent by population"
   ```

4. **Push your feature branch**

   ```bash
   git push -u origin feature/usecase2-country-by-continent
   ```

5. **Merge your feature back into `develop` when complete**

   ```bash
   git checkout develop
   git pull origin develop
   git merge feature/usecase2-country-by-continent
   git push origin develop
   ```

6. **If preparing for a release, merge into a `release` branch first, then into `master` and create a tag**

   ```bash
   git checkout -b release/v1.0.0 develop
   git push origin release/v1.0.0

   # After final testing, merge release into master
   git checkout master
   git merge release/v1.0.0
   git push origin master

   # Merge release back into develop to keep it updated
   git checkout develop
   git merge release/v1.0.0
   git push origin develop

   # Tag the release
   git checkout master
   git tag -a v1.0.0 -m "Release version 1.0.0"
   git push origin v1.0.0
   ```

### 🔀 Branching Flow Diagram

```
master
   │
   ├── develop
   │      │
   │      └── feature/usecase2-country-by-continent
   │               │
   │               └── (merge back into develop)
   │
   └── release/v1.0.0
          │
          └── merge into master + tag
```

---

## Step 1: Define the Use Case

Ensure the requirement is clearly defined in your `use-cases` directory.
For this example, **USE CASE 2** is already documented in `use-cases/Country_Breakdowns.md`.

**File:** `use-cases/Country_Breakdowns.md`

```markdown
## USE CASE 2: List All Countries in a Continent by Population (Descending)

### CHARACTERISTIC INFORMATION

**Goal in Context**  
As a *data analyst*, I want *to view a list of all countries within a specified continent, ordered from most populated to least populated*, so that *I can assess the population distribution across a specific continent.*
...
```

---

## Step 2: Add the Query to the Repository

**File:** `src/main/java/com/napier/devops/repository/CountryRepository.java`

```java
package com.napier.devops.repository;

import com.napier.devops.model.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, String> {

    // Use Case 1: Get all countries in the world ordered by population (descending)
    @Query("SELECT c FROM Country c ORDER BY c.population DESC")
    List<Country> getAllCountriesWorld();

    // Use Case 2: All the countries in a continent organised by largest population to smallest.
    @Query("SELECT c FROM Country c WHERE c.continent = :continent ORDER BY c.population DESC")
    List<Country> findByContinentOrderByPopulationDesc(@Param("continent") String continent);
}
```

**Changes Made:**

* Added the method `findByContinentOrderByPopulationDesc`.
* Used JPQL to select countries by continent, ordered by population.
* Bound the parameter using `@Param("continent")`.

---

## Step 3: Implement the Service Layer

**File:** `src/main/java/com/napier/devops/service/CountryService.java`

```java
package com.napier.devops.service;

import com.napier.devops.model.Country;
import com.napier.devops.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public List<Country> getAllCountriesWorld() {
        return countryRepository.getAllCountriesWorld();
    }

    /**
     * Retrieves all countries in a given continent, ordered by population.
     * @param continent The name of the continent.
     * @return A list of countries in the specified continent.
     */
    public List<Country> getAllCountriesInContinent(String continent) {
        return countryRepository.findByContinentOrderByPopulationDesc(continent);
    }
}
```

**Changes Made:**

* Added `getAllCountriesInContinent` method to the service layer.

---

## Step 4: Expose the Functionality in the Application

Integrate your new report in `Group4Application.java`.

```java
private void displayAllCountriesInContinent(String continent) {
    System.out.printf("\n=== ALL COUNTRIES IN %s (BY POPULATION) ===\n", continent.toUpperCase());
    List<Country> countries = countryService.getAllCountriesInContinent(continent);
    displayCountries(countries);
}
```

In the `run()` method:

```java
runUseCase("usecase2.log", () -> {
    System.out.println("\n=== USE CASE 2: All countries in a continent (Asia) ===");
    displayAllCountriesInContinent("Asia");
});
```

In the interactive menu:

```java
case 2:
    displayAllCountriesInContinent("Asia"); // Or prompt the user for continent
    break;
```

---

## Step 5: Update the `README.md`

Finally, reflect your progress in the `README.md`.

**File:** `README.md`

```markdown
| ID | Requirement | Met | Screenshot | Log File |
|----|--------------|-----|-------------|-----------|
| 1 | All the countries in the world organised by largest population to smallest. | ✅ Yes | ![Req1](images/req1.png) | [usecase1.log](...) |
| 2 | All the countries in a continent organised by largest population to smallest. | ✅ Yes | <!-- TODO: Add screenshot --> | [usecase2.log](...) |
| 3 | All the countries in a region organised by largest population to smallest. | ⚙️ In Progress |  | 🚫 Not generated |
```

Update the percentage to reflect the new case, take a screenshot of the output, add it to the `images` folder, update the `README.md` accordingly, and mark one more requirement as complete! ✅

---

🎉 **You have now successfully added a new query and reporting feature to your application!**
Follow this same pattern — from branching to documentation — for all future use cases to keep your development structured and traceable.



---

## Step 6: SQL Queries for All 32 Use Cases

Below are the SQL queries corresponding to each use case for reference.

### Use Case 1: All countries in the world by population

```sql
SELECT Code, Name, Continent, Region, Population, Capital
FROM country
ORDER BY Population DESC;
```

### Use Case 2: All countries in a specific continent by population

```sql
SELECT Code, Name, Continent, Region, Population, Capital
FROM country
WHERE Continent = 'Europe'
ORDER BY Population DESC;
```

### Use Case 3: All countries in a specific region by population

```sql
SELECT Code, Name, Continent, Region, Population, Capital
FROM country
WHERE Region = 'Western Europe'
ORDER BY Population DESC;
```

### Use Case 4: Top N most populated countries in the world

```sql
SELECT Code, Name, Continent, Region, Population, Capital
FROM country
ORDER BY Population DESC;
```

### Use Case 5: Top N most populated countries in a continent

```sql
SELECT Code, Name, Continent, Region, Population, Capital
FROM country
WHERE Continent = 'Asia'
ORDER BY Population DESC
LIMIT 10;
```

### Use Case 6: Top N most populated countries in a region

```sql
SELECT Code, Name, Continent, Region, Population, Capital
FROM country
WHERE Region = 'Eastern Asia'
ORDER BY Population DESC
LIMIT 10;
```

### Use Case 7: All cities in the world by population

```sql
SELECT Name, CountryCode, District, Population
FROM city
ORDER BY Population DESC;
```

### Use Case 8: All cities in a continent by population

```sql
SELECT city.Name, country.Name AS Country, city.District, city.Population
FROM city
JOIN country ON city.CountryCode = country.Code
WHERE country.Continent = 'Asia'
ORDER BY city.Population DESC;
```

### Use Case 9: All cities in a region by population

```sql
SELECT city.Name, country.Name AS Country, city.District, city.Population
FROM city
JOIN country ON city.CountryCode = country.Code
WHERE country.Region = 'Eastern Asia'
ORDER BY city.Population DESC;
```

### Use Case 10: All cities in a country by population

```sql
SELECT city.Name, country.Name AS Country, city.District, city.Population
FROM city
JOIN country ON city.CountryCode = country.Code
WHERE country.Name = 'China'
ORDER BY city.Population DESC;
```

### Use Case 11: All cities in a district by population

```sql
SELECT Name, CountryCode, District, Population
FROM city
WHERE District = 'California'
ORDER BY Population DESC;
```

### Use Case 12: Top N most populated cities in the world

```sql
SELECT Name, CountryCode, District, Population
FROM city
ORDER BY Population DESC
LIMIT 10;
```

### Use Case 13: Top N most populated cities in a continent

```sql
SELECT city.Name, country.Name AS Country, city.District, city.Population
FROM city
JOIN country ON city.CountryCode = country.Code
WHERE country.Continent = 'Asia'
ORDER BY city.Population DESC;
```

### Use Case 14: Top N most populated cities in a region

```sql
SELECT city.Name, country.Name AS Country, city.District, city.Population
FROM city
JOIN country ON city.CountryCode = country.Code
WHERE country.Region = 'Eastern Asia'
ORDER BY city.Population DESC
LIMIT 10;
```

### Use Case 15: Top N most populated cities in a country

```sql
SELECT city.Name, country.Name AS Country, city.District, city.Population
FROM city
JOIN country ON city.CountryCode = country.Code
WHERE country.Name = 'China'
ORDER BY city.Population DESC;
```

### Use Case 16: Top N most populated cities in a district

```sql
SELECT Name, CountryCode, District, Population
FROM city
WHERE District = 'California'
ORDER BY Population DESC
LIMIT 10;
```

### Use Case 17: All capital cities in the world by population

```sql
SELECT city.Name, country.Name AS Country, city.Population
FROM city
JOIN country ON city.ID = country.Capital
ORDER BY city.Population DESC;
```

### Use Case 18: All capital cities in a continent by population

```sql
SELECT city.Name, country.Name AS Country, city.Population
FROM city
JOIN country ON city.ID = country.Capital
WHERE country.Continent = 'Europe'
ORDER BY city.Population DESC;
```

### Use Case 19: All capital cities in a region by population

```sql
SELECT city.Name, country.Name AS Country, city.Population
FROM city
JOIN country ON city.ID = country.Capital
WHERE country.Region = 'Western Europe'
ORDER BY city.Population DESC;
```

### Use Case 20: Top N most populated capital cities in the world

```sql
SELECT city.Name, country.Name AS Country, city.Population
FROM city
JOIN country ON city.ID = country.Capital
ORDER BY city.Population DESC;
```

### Use Case 21: Top N most populated capital cities in a continent

```sql
SELECT city.Name, country.Name AS Country, city.Population
FROM city
JOIN country ON city.ID = country.Capital
WHERE country.Continent = 'Asia'
ORDER BY city.Population DESC
LIMIT 10;
```

### Use Case 22: Top N most populated capital cities in a region

```sql
SELECT city.Name, country.Name AS Country, city.Population
FROM city
JOIN country ON city.ID = country.Capital
WHERE country.Region = 'Eastern Asia'
ORDER BY city.Population DESC
LIMIT 10;
```

### Use Case 23: Population statistics by continent

```sql
SELECT 
  country.Continent,
  SUM(country.Population) AS TotalPopulation,
  SUM(city.Population) AS PopulationInCities,
  SUM(country.Population) - SUM(city.Population) AS PopulationNotInCities,
  ROUND(SUM(city.Population) / SUM(country.Population) * 100, 2) AS PercentInCities,
  ROUND((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population) * 100, 2) AS PercentNotInCities
FROM country
JOIN city ON country.Code = city.CountryCode
GROUP BY country.Continent;
```

### Use Case 24: Population statistics by region

```sql
SELECT 
  country.Region,
  SUM(country.Population) AS TotalPopulation,
  SUM(city.Population) AS PopulationInCities,
  SUM(country.Population) - SUM(city.Population) AS PopulationNotInCities,
  ROUND(SUM(city.Population) / SUM(country.Population) * 100, 2) AS PercentInCities,
  ROUND((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population) * 100, 2) AS PercentNotInCities
FROM country
JOIN city ON country.Code = city.CountryCode
GROUP BY country.Region;
```

### Use Case 25: Population statistics by country

```sql
SELECT 
  country.Name AS Country,
  country.Population AS TotalPopulation,
  SUM(city.Population) AS PopulationInCities,
  country.Population - SUM(city.Population) AS PopulationNotInCities,
  ROUND(SUM(city.Population) / country.Population * 100, 2) AS PercentInCities,
  ROUND((country.Population - SUM(city.Population)) / country.Population * 100, 2) AS PercentNotInCities
FROM country
JOIN city ON country.Code = city.CountryCode
GROUP BY country.Name, country.Population;
```

### Use Case 26: Total population of the world

```sql
SELECT SUM(Population) AS WorldPopulation
FROM country;
```

### Use Case 27: Total population of a continent

```sql
SELECT Continent, SUM(Population) AS ContinentPopulation
FROM country
WHERE Continent = 'Asia'
GROUP BY Continent;
```

### Use Case 28: Total population of a region

```sql
SELECT Region, SUM(Population) AS RegionPopulation
FROM country
WHERE Region = 'Eastern Asia'
GROUP BY Region;
```

### Use Case 29: Population of a country

```sql
SELECT Name AS Country, Population
FROM country
WHERE Name = 'China';
```

### Use Case 30: Population of a district

```sql
SELECT District, SUM(Population) AS DistrictPopulation
FROM city
WHERE District = 'California'
GROUP BY District;
```

### Use Case 31: Population of a city

```sql
SELECT Name AS City, Population
FROM city
WHERE Name = 'Stockholm';
```

### Use Case 32: Number of speakers for selected languages

```sql
SELECT 
  cl.Language,
  SUM(c.Population * cl.Percentage / 100) AS Speakers,
  ROUND(SUM(c.Population * cl.Percentage / 100) / (SELECT SUM(Population) FROM country) * 100, 2) AS PercentageOfWorldPopulation
FROM countrylanguage cl
JOIN country c ON cl.CountryCode = c.Code
WHERE cl.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic')
GROUP BY cl.Language
ORDER BY Speakers DESC;
```
