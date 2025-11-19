package com.napier.devops.controller;

import com.napier.devops.model.Country;
import com.napier.devops.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for CountryController
 */
@SpringBootTest(classes = com.napier.devops.TestApplication.class)
@ActiveProfiles("test")
class CountryControllerTest {

    @Autowired
    private CountryController countryController;

    @Autowired
    private CountryService countryService;

    private List<Country> sampleCountries;

    @BeforeEach
    void setup() {
        // Create some sample countries for testing
        sampleCountries = Arrays.asList(
                new Country(),
                new Country()
        );
    }
    @Test
    void getAllCountriesWorld_returnsNonNullList() {
        List<Country> actual = countryController.getAllCountriesWorld();
        assertThat(actual).isNotNull(); // just check it's not null
    }




    @Test
    void getCountriesInRegion_returnsNonNullList() {
        String region = "Europe";
        List<Country> actual = countryController.getCountriesInRegion(region).getBody();
        assertThat(actual).isNotNull();
    }

    @Test
    void getTopCountriesWorld_returnsNonNullList() {
        int limit = 5;
        List<Country> actual = countryController.getTopCountriesWorld(limit).getBody();
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isLessThanOrEqualTo(limit);
    }

    @Test
    void getTopCountriesContinent_returnsNonNullList() {
        String continent = "Asia";
        int limit = 3;
        List<Country> actual = countryController.getTopCountriesContinent(continent, limit).getBody();
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isLessThanOrEqualTo(limit);
    }

    @Test
    void getTopCountriesRegion_returnsNonNullList() {
        String region = "Europe";
        int limit = 2;
        List<Country> actual = countryController.getTopCountriesRegion(region, limit).getBody();
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isLessThanOrEqualTo(limit);
    }
}
