package com.napier.devops.controller;

import com.napier.devops.TestApplication;
import com.napier.devops.model.CapitalCity;
import com.napier.devops.model.City;
import com.napier.devops.model.Country;
import com.napier.devops.repository.CityRepository;
import com.napier.devops.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for CapitalController
 */
@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("test")
class CapitalControllerTest {

    @Autowired
    private CapitalController capitalController;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    void seedData() {
        countryRepository.deleteAll();
        cityRepository.deleteAll();

        City canberra = buildCity(10L, "Canberra", "AUS", "ACT", 426_704);
        City wellington = buildCity(11L, "Wellington", "NZL", "Wellington", 215_400);

        cityRepository.saveAll(List.of(canberra, wellington));

        countryRepository.saveAll(List.of(
                buildCountry("AUS", "Australia", "Oceania", "Australia and New Zealand", canberra.getId()),
                buildCountry("NZL", "New Zealand", "Oceania", "Australia and New Zealand", wellington.getId())
        ));
    }

    @Test
    void getAllCapitalCities() {
        List<CapitalCity> response = capitalController.getAllCapitalCities().getBody();

        assertThat(response)
                .isNotNull()
                .hasSize(2)
                .extracting(CapitalCity::getCityName)
                .containsExactly("Canberra", "Wellington");
    }

    @Test
    void getCapitalCitiesInContinent() {
        List<CapitalCity> response = capitalController.getCapitalCitiesInContinent("Oceania").getBody();

        assertThat(response)
                .isNotNull()
                .hasSize(2)
                .extracting(CapitalCity::getCityName)
                .containsExactly("Canberra", "Wellington");
    }

    @Test
    void getCapitalCitiesInContinent_blankContinent() {
        ResponseEntity<List<CapitalCity>> response = capitalController.getCapitalCitiesInContinent("");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void getCapitalCitiesInRegion() {
        List<CapitalCity> response = capitalController.getCapitalCitiesInRegion("Australia and New Zealand").getBody();

        assertThat(response)
                .isNotNull()
                .hasSize(2)
                .extracting(CapitalCity::getCityName)
                .containsExactly("Canberra", "Wellington");
    }

    @Test
    void getTopCapitalCitiesWorld() {
        List<CapitalCity> response = capitalController.getTopCapitalCitiesWorld(1).getBody();

        assertThat(response)
                .isNotNull()
                .hasSize(1)
                .extracting(CapitalCity::getCityName)
                .containsExactly("Canberra");
    }

    @Test
    void getTopCapitalCitiesInContinent_returnsResponse() {
        List<CapitalCity> response = capitalController.getTopCapitalCitiesInContinent("Oceania", 5).getBody();

        assertThat(response)
                .isNotNull()
                .hasSize(2)
                .extracting(CapitalCity::getCityName)
                .containsExactly("Canberra", "Wellington");
    }

    @Test
    void getTopCapitalCitiesRegion() {
        List<CapitalCity> response = capitalController.getTopCapitalCitiesRegion("Australia and New Zealand", 1).getBody();

        assertThat(response)
                .isNotNull()
                .hasSize(1)
                .extracting(CapitalCity::getCityName)
                .containsExactly("Canberra");
    }

    private City buildCity(Long id, String name, String countryCode, String district, int population) {
        City city = new City();
        city.setId(id);
        city.setName(name);
        city.setCountryCode(countryCode);
        city.setDistrict(district);
        city.setPopulation(population);
        return city;
    }

    private Country buildCountry(String code, String name, String continent, String region, Long capitalId) {
        Country country = new Country();
        country.setCode(code);
        country.setName(name);
        country.setContinent(continent);
        country.setRegion(region);
        country.setPopulation(1_000_000L);
        country.setCapital(capitalId);
        country.setCode2(code.substring(0, 2));
        return country;
    }
}


