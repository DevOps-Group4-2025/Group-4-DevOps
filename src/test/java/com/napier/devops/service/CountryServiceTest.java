package com.napier.devops.service;

import com.napier.devops.model.Country;
import com.napier.devops.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = com.napier.devops.TestApplication.class)
@ActiveProfiles("test")
class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    void setUp() {
        countryRepository.deleteAll();
        countryRepository.saveAll(List.of(
                buildCountry("CHN", "China", "Asia", 1277558000),
                buildCountry("IND", "India", "Asia", 1013662000),
                buildCountry("USA", "United States", "North America", 278357000)
        ));
    }

    @Test
    void getAllCountriesWorld_returnsNonNullList() {
        List<Country> actual = countryService.getAllCountriesWorld();
        assertThat(actual).isNotNull().hasSize(3);
    }

    @Test
    void getAllCountriesInContinent_returnsCorrectlyOrderedData() {
        // When
        List<Country> countries = countryService.getAllCountriesInContinent("Asia");

        // Then
        assertThat(countries)
                .isNotNull()
                .hasSize(2)
                .extracting(Country::getName)
                .containsExactly("China", "India");
    }

    private Country buildCountry(String code, String name, String continent, long population) {
        Country country = new Country();
        country.setCode(code);
        country.setName(name);
        country.setContinent(continent);
        country.setPopulation(population);
        return country;
    }
}
