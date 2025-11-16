package com.napier.devops.repository;

import com.napier.devops.model.Country;
import com.napier.devops.model.CountryLanguage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for CountryLanguageRepository.
 * Tests the repository layer database queries for language statistics.
 */
@SpringBootTest(classes = com.napier.devops.TestApplication.class)
@ActiveProfiles("test")
class CountryLanguageRepositoryIT {

    @Autowired
    private CountryLanguageRepository countryLanguageRepository;

    @Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    void setUp() {
        // Clean up existing data
        countryLanguageRepository.deleteAll();
        countryRepository.deleteAll();

        // Create test countries with realistic populations
        Country china = buildCountry("CHN", "China", 1277558000L);
        Country india = buildCountry("IND", "India", 1013662000L);
        Country usa = buildCountry("USA", "United States", 278357000L);
        Country mexico = buildCountry("MEX", "Mexico", 98881000L);
        Country spain = buildCountry("ESP", "Spain", 39441700L);
        Country saudiArabia = buildCountry("SAU", "Saudi Arabia", 21607000L);
        Country uk = buildCountry("GBR", "United Kingdom", 59623400L);

        countryRepository.saveAll(List.of(china, india, usa, mexico, spain, saudiArabia, uk));

        // Create test language data matching real-world scenarios
        // Chinese: 100% of China
        CountryLanguage chinese = buildCountryLanguage("CHN", "Chinese", 100.0);
        
        // English: 86% of USA + 98% of UK
        CountryLanguage englishUSA = buildCountryLanguage("USA", "English", 86.0);
        CountryLanguage englishUK = buildCountryLanguage("GBR", "English", 98.0);
        
        // Hindi: 40% of India
        CountryLanguage hindi = buildCountryLanguage("IND", "Hindi", 40.0);
        
        // Spanish: 92% of Mexico + 74% of Spain
        CountryLanguage spanishMexico = buildCountryLanguage("MEX", "Spanish", 92.0);
        CountryLanguage spanishSpain = buildCountryLanguage("ESP", "Spanish", 74.0);
        
        // Arabic: 95% of Saudi Arabia
        CountryLanguage arabic = buildCountryLanguage("SAU", "Arabic", 95.0);

        countryLanguageRepository.saveAll(List.of(
                chinese, englishUSA, englishUK, hindi, 
                spanishMexico, spanishSpain, arabic));
    }

    @Test
    void getLanguageStatistics_executesQuerySuccessfully() {
        // When
        List<LanguageStatsProjection> result = countryLanguageRepository.getLanguageStatistics();

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void getLanguageStatistics_returnsAllFiveRequiredLanguages() {
        // When
        List<LanguageStatsProjection> result = countryLanguageRepository.getLanguageStatistics();

        // Then
        assertThat(result)
                .isNotNull()
                .hasSize(5)
                .extracting(LanguageStatsProjection::getLanguage)
                .containsExactlyInAnyOrder("Chinese", "English", "Hindi", "Spanish", "Arabic");
    }

    @Test
    void getLanguageStatistics_ordersBySpeakersDescending() {
        // When
        List<LanguageStatsProjection> result = countryLanguageRepository.getLanguageStatistics();

        // Then
        assertThat(result).isNotNull().hasSize(5);

        // Verify descending order
        for (int i = 0; i < result.size() - 1; i++) {
            Long currentSpeakers = result.get(i).getSpeakers();
            Long nextSpeakers = result.get(i + 1).getSpeakers();
            assertThat(currentSpeakers)
                    .as("Language at index %d should have more or equal speakers than next", i)
                    .isGreaterThanOrEqualTo(nextSpeakers);
        }
    }

    @Test
    void getLanguageStatistics_calculatesChineseSpeakersCorrectly() {
        // When
        List<LanguageStatsProjection> result = countryLanguageRepository.getLanguageStatistics();

        // Then
        LanguageStatsProjection chinese = result.stream()
                .filter(l -> "Chinese".equals(l.getLanguage()))
                .findFirst()
                .orElse(null);

        assertThat(chinese).isNotNull();
        // Chinese: 100% of 1,277,558,000 = 1,277,558,000
        assertThat(chinese.getSpeakers()).isEqualTo(1277558000L);
    }

    @Test
    void getLanguageStatistics_calculatesEnglishSpeakersCorrectly() {
        // When
        List<LanguageStatsProjection> result = countryLanguageRepository.getLanguageStatistics();

        // Then
        LanguageStatsProjection english = result.stream()
                .filter(l -> "English".equals(l.getLanguage()))
                .findFirst()
                .orElse(null);

        assertThat(english).isNotNull();
        // English: 86% of 278,357,000 (USA) + 98% of 59,623,400 (UK)
        // = 239,387,020 + 58,430,932 = 297,817,952
        long expectedEnglish = (long)(278357000L * 0.86 + 59623400L * 0.98);
        assertThat(english.getSpeakers()).isEqualTo(expectedEnglish);
    }

    @Test
    void getLanguageStatistics_calculatesSpanishSpeakersCorrectly() {
        // When
        List<LanguageStatsProjection> result = countryLanguageRepository.getLanguageStatistics();

        // Then
        LanguageStatsProjection spanish = result.stream()
                .filter(l -> "Spanish".equals(l.getLanguage()))
                .findFirst()
                .orElse(null);

        assertThat(spanish).isNotNull();
        // Spanish: 92% of 98,881,000 (Mexico) + 74% of 39,441,700 (Spain)
        // = 90,970,520 + 29,186,858 = 120,157,378
        long expectedSpanish = (long)(98881000L * 0.92 + 39441700L * 0.74);
        assertThat(spanish.getSpeakers()).isEqualTo(expectedSpanish);
    }

    @Test
    void getLanguageStatistics_calculatesPercentageOfWorldCorrectly() {
        // When
        List<LanguageStatsProjection> result = countryLanguageRepository.getLanguageStatistics();

        // Then
        LanguageStatsProjection chinese = result.stream()
                .filter(l -> "Chinese".equals(l.getLanguage()))
                .findFirst()
                .orElse(null);

        assertThat(chinese).isNotNull();
        assertThat(chinese.getPercentageOfWorldPopulation()).isNotNull();
        
        // Calculate expected percentage
        long worldPopulation = 1277558000L + 1013662000L + 278357000L + 98881000L + 
                               39441700L + 21607000L + 59623400L;
        double expectedPercentage = (1277558000.0 / worldPopulation) * 100.0;
        
        // Allow small rounding differences
        assertThat(chinese.getPercentageOfWorldPopulation())
                .isCloseTo(expectedPercentage, org.assertj.core.data.Offset.offset(0.1));
    }

    @Test
    void getLanguageStatistics_percentageValuesAreValid() {
        // When
        List<LanguageStatsProjection> result = countryLanguageRepository.getLanguageStatistics();

        // Then
        for (LanguageStatsProjection stats : result) {
            assertThat(stats.getPercentageOfWorldPopulation())
                    .as("Percentage for %s should be between 0 and 100", stats.getLanguage())
                    .isNotNull()
                    .isGreaterThanOrEqualTo(0.0)
                    .isLessThanOrEqualTo(100.0);
        }
    }

    @Test
    void getLanguageStatistics_handlesEmptyDatabase() {
        // Given
        countryLanguageRepository.deleteAll();
        countryRepository.deleteAll();

        // When
        List<LanguageStatsProjection> result = countryLanguageRepository.getLanguageStatistics();

        // Then
        assertThat(result).isNotNull().isEmpty();
    }

    @Test
    void getLanguageStatistics_filtersOnlyRequiredLanguages() {
        // Given - add a language that should not be included
        Country france = buildCountry("FRA", "France", 59225700L);
        countryRepository.save(france);
        CountryLanguage french = buildCountryLanguage("FRA", "French", 100.0);
        countryLanguageRepository.save(french);

        // When
        List<LanguageStatsProjection> result = countryLanguageRepository.getLanguageStatistics();

        // Then
        assertThat(result)
                .extracting(LanguageStatsProjection::getLanguage)
                .doesNotContain("French")
                .containsExactlyInAnyOrder("Chinese", "English", "Hindi", "Spanish", "Arabic");
    }

    private Country buildCountry(String code, String name, long population) {
        Country country = new Country();
        country.setCode(code);
        country.setName(name);
        country.setPopulation(population);
        country.setContinent("Asia");
        country.setRegion("Test Region");
        return country;
    }

    private CountryLanguage buildCountryLanguage(String countryCode, String language, double percentage) {
        CountryLanguage countryLanguage = new CountryLanguage();
        countryLanguage.setCountryCode(countryCode);
        countryLanguage.setLanguage(language);
        countryLanguage.setPercentage(percentage);
        countryLanguage.setIsOfficial("T");
        return countryLanguage;
    }
}

