# Tutorial: How to Add a New Query to the System

This guide demonstrates how to implement a new report by adding a new query to the system. We will use **"List all countries in a specific continent, ordered by population"** (Use Case 2) as our example.

This tutorial walks you through adding the feature, from the data layer to the final output in your application.

---

### Steps Overview

1.  **Define the Use Case**: Ensure the new requirement is documented.
2.  **Update the Repository**: Add the new query to the data access layer (JPA Repository).
3.  **Implement the Service**: Create the business logic to call the repository method.
4.  **Expose the Functionality**: Add the new report to the main application so it can be executed and displayed.
5.  **Update Project Documentation**: Mark the requirement as complete in the `README.md`.

---

### Step 1: Define the Use Case

First, we ensure the requirement is clearly defined in your `use-cases` directory. For this example, **USE CASE 2** is already well-documented in `use-cases/Country_Breakdowns.md`, so no changes are needed. It clearly outlines the goal, actors, and success conditions.

**File**: `use-cases/Country_Breakdowns.md`

```markdown
## USE CASE 2: List All Countries in a Continent by Population (Descending)

### CHARACTERISTIC INFORMATION

**Goal in Context**  
As a *data analyst*, I want *to view a list of all countries within a specified continent, ordered from most populated to least populated*, so that *I can assess the population distribution across a specific continent.*
...
```

---

### Step 2: Add the Query to the Repository

Next, we add the new query to the Spring Data JPA repository that handles the `Country` entity.

**File**: `src/main/java/com/napier/devops/repository/CountryRepository.java`

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
*   A new method `findByContinentOrderByPopulationDesc` was added.
*   The `@Query` annotation contains the JPQL to select countries from a given continent, ordered by population.
*   `@Param("continent")` maps the method's `continent` parameter to the `:continent` placeholder in the query.

---

### Step 3: Implement the Service Layer

The service layer contains the business logic. We'll add a new method to `CountryService` to call our new repository method.

**File**: `src/main/java/com/napier/devops/service/CountryService.java`

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
*   A new public method, `getAllCountriesInContinent`, was created to call the repository.

---

### Step 4: Expose the Functionality in the Application

Now, let's add this new report to the main application entry point, `Group4Application.java`, so it can be executed and the results displayed. We will add it to both the automated run and the interactive menu.

*(For brevity, only the relevant sections of `Group4Application.java` are shown. You would integrate the new method calls as needed.)*

**Example Integration:**
1.  Add a new display method:
    ```java
    private void displayAllCountriesInContinent(String continent) {
        System.out.printf("\n=== ALL COUNTRIES IN %s (BY POPULATION) ===\n", continent.toUpperCase());
        List<Country> countries = countryService.getAllCountriesInContinent(continent);
        displayCountries(countries);
    }
    ```
2.  Call it from the `run` method for automated reports:
    ```java
    runUseCase("usecase2.log", () -> {
        System.out.println("\n=== USE CASE 2: All countries in a continent (Asia) ===");
        displayAllCountriesInContinent("Asia");
    });
    ```
3.  Add it to the `handleMenuSelection` `switch` statement for interactive mode:
    ```java
    case 2:
        displayAllCountriesInContinent("Asia"); // Or prompt user for continent
        break;
    ```

---

### Step 5: Update the `README.md`

Finally, update the `README.md` to reflect that Requirement 2 is now complete. This keeps the project status transparent.

**File**: `README.md`

```markdown
| ID | Requirement | Met | Screenshot | Log File |
|----|--------------|-----|-------------|-----------|
| 1 | All the countries in the world organised by largest population to smallest. | ✅ Yes | ![Req1](images/req1.png) | [usecase1.log](...) |
| 2 | All the countries in a continent organised by largest population to smallest. | ✅ Yes | <!-- TODO: Add screenshot --> | [usecase2.log](...) |
| 3 | All the countries in a region organised by largest population to smallest. | ⚙️ In Progress |  | 🚫 Not generated |

```

Update the % to reflect the new case, take a screenshot of the output, add it to the images folder, update the README.md accordingly, and mark one more requirement as complete! ✅

You have now successfully added a new query and reporting feature to your application! You can follow this same pattern to implement the other SQL queries you have.
