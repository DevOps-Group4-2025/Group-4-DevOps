# Use Case 32 - Language Statistics Report

## 📋 Description

Implement a language statistics report that displays the number of people who speak Chinese, English, Hindi, Spanish, and Arabic, ordered from greatest to smallest, including the percentage of the world population.

## ✅ Acceptance Criteria

- [x] Report displays all five languages: Chinese, English, Hindi, Spanish, Arabic
- [x] Languages are ordered by number of speakers (greatest to smallest)
- [x] Report shows total number of speakers for each language
- [x] Report shows percentage of world population for each language
- [x] Feature is accessible from main menu (option 32)
- [x] Report output is saved to `output/usecase32.log`
- [x] All unit tests pass (6 tests)
- [x] All integration tests pass (10 tests)
- [x] Code follows existing project patterns and conventions

## 🔧 Technical Implementation

### Development Approach
- ✅ **Test-Driven Development (TDD)** - Tests written first
- ✅ **Integration Tests** - Repository integration tests included
- ✅ **Unit Tests** - Service layer unit tests included

### Database Query
- ✅ Aggregates speakers across all countries for each language
- ✅ Calculates: `SUM(country.population * countrylanguage.percentage / 100)`
- ✅ Calculates percentage: `(total speakers / world population) * 100`
- ✅ Database-aware implementation (MySQL backticks + SIGNED, H2 double quotes + BIGINT)

### Components Implemented

#### 1. Repository Layer
- ✅ `CountryLanguageRepository` - Custom repository with database detection
- ✅ `CountryLanguageRepositoryCustom` - Interface for custom methods
- ✅ `CountryLanguageRepositoryImpl` - Implementation with MySQL/H2 compatibility
- ✅ `LanguageStatsProjection` - Projection interface for query results

#### 2. Service Layer
- ✅ `LanguageService` - Maps projections to DTOs

#### 3. Controller Layer
- ✅ `LanguageController` - REST endpoint and main app integration

#### 4. Main Application Integration
- ✅ Menu option "32. Languages report" added
- ✅ `displayLanguageStatistics()` method implemented
- ✅ Formatted output with proper columns
- ✅ Log file output to `output/usecase32.log`

### Testing

#### Unit Tests (`LanguageServiceTest`) - 6 tests ✅
- ✅ Returns non-null list
- ✅ Returns all five languages
- ✅ Orders by speakers (descending)
- ✅ Calculates speakers correctly
- ✅ Calculates percentage of world correctly
- ✅ Handles empty database

#### Integration Tests (`CountryLanguageRepositoryIT`) - 10 tests ✅
- ✅ Query execution success
- ✅ All five required languages returned
- ✅ Ordered by speakers (descending)
- ✅ Chinese speakers calculation
- ✅ English speakers calculation
- ✅ Spanish speakers calculation
- ✅ Percentage of world calculation
- ✅ Percentage values are valid (0-100)
- ✅ Empty database handling
- ✅ Only required languages filtered

## 📊 Output Format

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

## 🗄️ Database Schema

- **countrylanguage** table: `CountryCode`, `Language`, `Percentage`
- **country** table: `Code`, `Population`

## 📁 Files Created/Modified

### New Files
- `src/main/java/com/napier/devops/repository/CountryLanguageRepositoryCustom.java`
- `src/main/java/com/napier/devops/repository/CountryLanguageRepositoryImpl.java`
- `src/main/java/com/napier/devops/repository/LanguageStatsProjection.java`
- `src/main/java/com/napier/devops/service/LanguageService.java`
- `src/test/java/com/napier/devops/service/LanguageServiceTest.java`
- `src/test/java/com/napier/devops/repository/CountryLanguageRepositoryIT.java`

### Modified Files
- `src/main/java/com/napier/devops/repository/CountryLanguageRepository.java`
- `src/main/java/com/napier/devops/controller/LanguageController.java`
- `src/main/java/com/napier/devops/Group4Application.java`
- `src/test/resources/application-test.properties`

## ✅ Testing Results

- **Total Tests:** 20
- **Passed:** 20
- **Failed:** 0
- **Errors:** 0
- **Build Status:** ✅ SUCCESS

## 🚀 Ready for Review

All acceptance criteria met. Feature is complete, tested, and working in both IntelliJ and Docker environments.

