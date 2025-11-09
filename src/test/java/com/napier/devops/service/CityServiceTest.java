package com.napier.devops.service;

import com.napier.devops.model.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = com.napier.devops.TestApplication.class)
@ActiveProfiles("test")
class CityServiceTest {

    @Autowired
    private CityService cityService;

    @Test
    void getAllCitiesInTheWorld_returnsNonNullList() {
        List<City> actual = cityService.getAllCitiesInTheWorld();
        assertThat(actual).isNotNull();
    }

    @Test
    void getAllCitiesInAContinent_returnsNonNullList() {
        List<City> actual = cityService.getAllCitiesInAContinent("Europe");
        assertThat(actual).isNotNull();
    }

    @Test
    void getAllCitiesInARegion_returnsNonNullList() {
        List<City> actual = cityService.getAllCitiesInARegion("Caribbean");
        assertThat(actual).isNotNull();
    }

    @Test
    void getAllCitiesInACountry_returnsNonNullList() {
        List<City> actual = cityService.getAllCitiesInACountry("Spain");
        assertThat(actual).isNotNull();
    }

    @Test
    void getAllCitiesInADistrict_returnsNonNullList() {
        List<City> actual = cityService.getAllCitiesInADistrict("Zuid-Holland");
        assertThat(actual).isNotNull();
    }

    @Test
    void getTopNCitiesInTheWorld_returnsNonNullList() {
        List<City> actual = cityService.getTopNCitiesInTheWorld(5);
        assertThat(actual).isNotNull();
    }

    @Test
    void getTopNCitiesInAContinent_returnsNonNullList() {
        List<City> actual = cityService.getTopNCitiesInAContinent("Europe", 5);
        assertThat(actual).isNotNull();
    }

    @Test
    void getTopNCitiesInARegion_returnsNonNullList() {
        List<City> actual = cityService.getTopNCitiesInARegion("Caribbean", 5);
        assertThat(actual).isNotNull();
    }

    @Test
    void getTopNCitiesInACountry_returnsNonNullList() {
        List<City> actual = cityService.getTopNCitiesInACountry("Spain", 5);
        assertThat(actual).isNotNull();
    }

    @Test
    void getTopNCitiesInADistrict_returnsNonNullList() {
        List<City> actual = cityService.getTopNCitiesInADistrict("Zuid-Holland", 5);
        assertThat(actual).isNotNull();
    }
}
