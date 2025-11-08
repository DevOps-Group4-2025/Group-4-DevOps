package com.napier.devops.repository;

import com.napier.devops.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = com.napier.devops.TestApplication.class)
@ActiveProfiles("test")
class CountryRepositoryIT {

    @Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    void setUp() {
        countryRepository.deleteAll();

        countryRepository.saveAll(List.of(
                buildCountry("CHN", "China", "Asia", 1277558000),
                buildCountry("IND", "India", "Asia", 1013662000),
                buildCountry("USA", "United States", "North America", 278357000),
                buildCountry("IDN", "Indonesia", "Asia", 212107000)
        ));
    }

    @Test
    void getAllCountriesWorld_executesQuery() {
        List<Country> countries = countryRepository.getAllCountriesWorld();
        assertThat(countries)
                .isNotNull()
                .hasSize(4)
                .extracting(Country::getName)
                .containsExactly("China", "India", "United States", "Indonesia");
    }

    @Test
    void findByContinentOrderByPopulationDesc_returnsCorrectlyOrderedData() {
        // When
        List<Country> countries = countryRepository.findByContinentOrderByPopulationDesc("Asia");

        // Then
        assertThat(countries)
                .isNotNull()
                .hasSize(3)
                .extracting(Country::getName)
                .containsExactly("China", "India", "Indonesia");
    }

    @Test
    void findByContinentOrderByPopulationDesc_returnsEmptyForNoMatch() {
        // When
        List<Country> countries = countryRepository.findByContinentOrderByPopulationDesc("Europe");

        // Then
        assertThat(countries).isNotNull().isEmpty();
    }

    private Country buildCountry(String code, String name, String continent, int population) {
        Country country = new Country();
        country.setCode(code);
        country.setName(name);
        country.setContinent(continent);
        country.setPopulation((long) population);
        return country;
    }
}
