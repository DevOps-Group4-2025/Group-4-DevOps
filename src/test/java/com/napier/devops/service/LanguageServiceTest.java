package com.napier.devops.service;

import com.napier.devops.model.Country;
import com.napier.devops.model.CountryLanguage;
import com.napier.devops.model.CountryLanguageId;
import com.napier.devops.model.LanguageStats;
import com.napier.devops.repository.CountryLanguageRepository;
import com.napier.devops.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for LanguageService.
 * Tests the service layer logic for retrieving language statistics.
 */
@SpringBootTest(classes = com.napier.devops.TestApplication.class)
@ActiveProfiles("test")
class LanguageServiceTest {

    @Autowired
    private LanguageService languageService;

    @Autowired
    private CountryLanguageRepository countryLanguageRepository;

    @Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    void setUp() {
        // Clean up existing data
        countryLanguageRepository.deleteAll();
        countryRepository.deleteAll();

        // Create test countries
        Country china = buildCountry("CHN", "China", 1277558000L);
        Country india = buildCountry("IND", "India", 1013662000L);
        Country usa = buildCountry("USA", "United States", 278357000L);
        Country spain = buildCountry("ESP", "Spain", 39441700L);
        Country saudiArabia = buildCountry("SAU", "Saudi Arabia", 21607000L);

        countryRepository.saveAll(List.of(china, india, usa, spain, saudiArabia));

        // Create test language data
        // Chinese: 100% of China = 1,277,558,000
        CountryLanguage chinese = buildCountryLanguage("CHN", "Chinese", 100.0);
        
        // English: 86% of USA = 239,387,020
        CountryLanguage englishUSA = buildCountryLanguage("USA", "English", 86.0);
        
        // Hindi: 40% of India = 405,464,400
        CountryLanguage hindi = buildCountryLanguage("IND", "Hindi", 40.0);
        
        // Spanish: 74% of Spain = 29,186,858
        CountryLanguage spanish = buildCountryLanguage("ESP", "Spanish", 74.0);
        
        // Arabic: 95% of Saudi Arabia = 20,526,650
        CountryLanguage arabic = buildCountryLanguage("SAU", "Arabic", 95.0);

        countryLanguageRepository.saveAll(List.of(chinese, englishUSA, hindi, spanish, arabic));
    }

    @Test
    void getLanguageStatistics_returnsNonNullList() {
        // When
        List<LanguageStats> result = languageService.getLanguageStatistics();

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void getLanguageStatistics_returnsAllFiveLanguages() {
        // When
        List<LanguageStats> result = languageService.getLanguageStatistics();

        // Then
        assertThat(result)
                .isNotNull()
                .hasSize(5)
                .extracting(LanguageStats::getLanguage)
                .containsExactlyInAnyOrder("Chinese", "English", "Hindi", "Spanish", "Arabic");
    }

    @Test
    void getLanguageStatistics_ordersBySpeakersDescending() {
        // When
        List<LanguageStats> result = languageService.getLanguageStatistics();

        // Then
        assertThat(result)
                .isNotNull()
                .hasSize(5);

        // Verify ordering: Chinese should be first (highest speakers)
        assertThat(result.get(0).getLanguage()).isEqualTo("Chinese");
        
        // Verify descending order
        for (int i = 0; i < result.size() - 1; i++) {
            Long currentSpeakers = result.get(i).getSpeakers();
            Long nextSpeakers = result.get(i + 1).getSpeakers();
            assertThat(currentSpeakers).isGreaterThanOrEqualTo(nextSpeakers);
        }
    }

    @Test
    void getLanguageStatistics_calculatesSpeakersCorrectly() {
        // When
        List<LanguageStats> result = languageService.getLanguageStatistics();

        // Then
        LanguageStats chinese = result.stream()
                .filter(l -> "Chinese".equals(l.getLanguage()))
                .findFirst()
                .orElse(null);

        assertThat(chinese).isNotNull();
        // Chinese: 100% of 1,277,558,000 = 1,277,558,000
        assertThat(chinese.getSpeakers()).isEqualTo(1277558000L);
    }

    @Test
    void getLanguageStatistics_calculatesPercentageOfWorldCorrectly() {
        // When
        List<LanguageStats> result = languageService.getLanguageStatistics();

        // Then
        LanguageStats chinese = result.stream()
                .filter(l -> "Chinese".equals(l.getLanguage()))
                .findFirst()
                .orElse(null);

        assertThat(chinese).isNotNull();
        assertThat(chinese.getPercentageOfWorldPopulation()).isNotNull();
        assertThat(chinese.getPercentageOfWorldPopulation()).isGreaterThan(0.0);
        assertThat(chinese.getPercentageOfWorldPopulation()).isLessThanOrEqualTo(100.0);
    }

    @Test
    void getLanguageStatistics_handlesEmptyDatabase() {
        // Given
        countryLanguageRepository.deleteAll();
        countryRepository.deleteAll();

        // When
        List<LanguageStats> result = languageService.getLanguageStatistics();

        // Then
        assertThat(result).isNotNull().isEmpty();
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

