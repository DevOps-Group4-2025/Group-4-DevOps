package com.napier.devops.repository;

import com.napier.devops.TestApplication;
import com.napier.devops.model.CapitalCity;
import com.napier.devops.model.City;
import com.napier.devops.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("test")
class CapitalCityRepositoryIT {

    @Autowired
    private CapitalCityRepository capitalCityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @BeforeEach
    void setUp() {
        countryRepository.deleteAll();
        cityRepository.deleteAll();

        // Capitals
        City paris = buildCity(1L, "Paris", "FRA", 3_000_000);
        City berlin = buildCity(2L, "Berlin", "DEU", 3_500_000);
        City tokyo = buildCity(3L, "Tokyo", "JPN", 9_000_000);

        cityRepository.saveAll(List.of(paris, berlin, tokyo));

        // Countries
        Country france = buildCountry("FRA", "France", "Europe", "Western Europe", paris.getId());
        Country germany = buildCountry("DEU", "Germany", "Europe", "Western Europe", berlin.getId());
        Country japan = buildCountry("JPN", "Japan", "Asia", "Eastern Asia", tokyo.getId());

        countryRepository.saveAll(List.of(france, germany, japan));
    }

    @Test
    void findCapitalCitiesByContinentOrderByPopulationDesc() {
        // When
        List<CapitalCity> europeanCapitals = capitalCityRepository.findCapitalCitiesInContinentByPopulationDesc("Europe");

        // Then
        assertThat(europeanCapitals)
                .hasSize(2)
                .extracting(CapitalCity::getCityName)
                .containsExactly("Berlin", "Paris");
    }

    @Test
    void findCapitalCitiesInRegionByPopulationDesc() {
        // When
        List<CapitalCity> westernEuropeCapitals = capitalCityRepository.findCapitalCitiesInRegionByPopulationDesc("Western Europe");

        // Then
        assertThat(westernEuropeCapitals)
                .hasSize(2)
                .extracting(CapitalCity::getCityName)
                .containsExactly("Berlin", "Paris");
    }

    @Test
    void findCapitalCitiesInContinentByPopulationDesc_NoResults() {
        // When
        List<CapitalCity> africanCapitals = capitalCityRepository.findCapitalCitiesInContinentByPopulationDesc("Africa");

        // Then
        assertThat(africanCapitals).isEmpty();
    }

    private City buildCity(Long id, String name, String countryCode, int population) {
        City city = new City();
        city.setId(id);
        city.setName(name);
        city.setCountryCode(countryCode);
        city.setPopulation(population);
        return city;
    }

    private Country buildCountry(String code, String name, String continent, String region, Long capitalId) {
        Country country = new Country();
        country.setCode(code);
        country.setName(name);
        country.setContinent(continent);
        country.setRegion(region);
        country.setCapital(capitalId);
        country.setPopulation(10_000_000L); // Set a default population
        return country;
    }
}