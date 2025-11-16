---
name: Use Case 32 - Language Statistics Report
about: Implement language statistics report for Chinese, English, Hindi, Spanish, and Arabic
title: '[USE CASE 32] Language Statistics Report'
labels: enhancement, use-case, backend
assignees: ''
---

## 📋 Use Case Description

**Use Case Number:** 32  
**Title:** Language Statistics Report  
**Priority:** High

### Goal
As an analyst, I want to produce a report showing the number of people who speak Chinese, English, Hindi, Spanish, and Arabic from greatest to smallest, including the percentage of the world population, so that I can support linguistic reporting.

## ✅ Acceptance Criteria

- [ ] Report displays all five languages: Chinese, English, Hindi, Spanish, Arabic
- [ ] Languages are ordered by number of speakers (greatest to smallest)
- [ ] Report shows total number of speakers for each language
- [ ] Report shows percentage of world population for each language
- [ ] Feature is accessible from main menu (option 32)
- [ ] Report output is saved to `output/usecase32.log`
- [ ] All unit tests pass
- [ ] All integration tests pass
- [ ] Code follows existing project patterns and conventions

## 🔧 Technical Requirements

### Development Approach
- **Test-Driven Development (TDD)** - Write tests first, then implement
- **Integration Tests** - Must include repository integration tests
- **Unit Tests** - Must include service layer unit tests

### Database Query Requirements
- Query must aggregate speakers across all countries for each language
- Calculate: `SUM(country.population * countrylanguage.percentage / 100)` for total speakers
- Calculate: `(total speakers / world population) * 100` for percentage
- Must handle database compatibility (MySQL and H2 for testing)

### Implementation Components

#### 1. Repository Layer
- [ ] Create/update `CountryLanguageRepository` with native SQL query
- [ ] Query must return language, speakers count, and percentage
- [ ] Use projection interface for result mapping
- [ ] Handle database-specific SQL syntax (MySQL backticks, H2 double quotes)

#### 2. Service Layer
- [ ] Create `LanguageService` class
- [ ] Map repository projections to DTOs
- [ ] Handle business logic and data transformation

#### 3. Controller Layer
- [ ] Create/update `LanguageController` class
- [ ] Provide REST endpoint `/languages` (optional)
- [ ] Provide method for main application integration

#### 4. Main Application Integration
- [ ] Add menu option "32. Languages report" to main menu
- [ ] Implement `displayLanguageStatistics()` method
- [ ] Format output with proper columns and alignment
- [ ] Save output to log file

### Testing Requirements

#### Unit Tests (`LanguageServiceTest`)
- [ ] Test that service returns non-null list
- [ ] Test that all five languages are returned
- [ ] Test that languages are ordered by speakers (descending)
- [ ] Test speaker count calculation
- [ ] Test percentage of world calculation
- [ ] Test empty database handling

#### Integration Tests (`CountryLanguageRepositoryIT`)
- [ ] Test query execution success
- [ ] Test all five required languages are returned
- [ ] Test ordering by speakers (descending)
- [ ] Test Chinese speakers calculation
- [ ] Test English speakers calculation
- [ ] Test Spanish speakers calculation
- [ ] Test percentage of world calculation
- [ ] Test percentage values are valid (0-100)
- [ ] Test empty database handling
- [ ] Test only required languages are filtered

## 📊 Expected Output Format

```
=== LANGUAGE STATISTICS REPORT ===

Language                         Speakers       Percentage of World
----------------------------------------------------------------------
Chinese                     1,191,843,539                    19.61%
Hindi                         405,633,070                     6.67%
Spanish                       355,029,462                     5.84%
English                       347,077,867                     5.71%
Arabic                        233,839,239                     3.85%
```

## 🗄️ Database Schema Reference

- **Table:** `countrylanguage`
  - `CountryCode` (VARCHAR) - Foreign key to country
  - `Language` (VARCHAR) - Language name
  - `Percentage` (DECIMAL) - Percentage of country population speaking this language

- **Table:** `country`
  - `Code` (VARCHAR) - Primary key
  - `Population` (BIGINT) - Total country population

## 🔍 Calculation Logic

1. **Total Speakers per Language:**
   ```
   SUM(country.Population * countrylanguage.Percentage / 100)
   ```

2. **Percentage of World Population:**
   ```
   (Total Speakers / World Population) * 100
   ```

3. **World Population:**
   ```
   SUM(country.Population)
   ```

## 📁 Related Files

- `src/main/java/com/napier/devops/repository/CountryLanguageRepository.java`
- `src/main/java/com/napier/devops/service/LanguageService.java`
- `src/main/java/com/napier/devops/controller/LanguageController.java`
- `src/main/java/com/napier/devops/Group4Application.java`
- `src/main/java/com/napier/devops/model/LanguageStats.java`
- `src/test/java/com/napier/devops/service/LanguageServiceTest.java`
- `src/test/java/com/napier/devops/repository/CountryLanguageRepositoryIT.java`

## 🧪 Testing Commands

```bash
# Run all language tests
mvn test -Dtest="*Language*"

# Run only unit tests
mvn test -Dtest=LanguageServiceTest

# Run only integration tests
mvn test -Dtest=CountryLanguageRepositoryIT
```

## 📝 Notes

- Follow existing code patterns in the project
- Ensure database compatibility (MySQL for production, H2 for tests)
- Use projection interfaces for native query results
- Maintain consistent code style and documentation

## 🔗 Related Use Cases

- Use Case 1-31: Other population and city reports
- Follows same pattern as other use cases in the system

