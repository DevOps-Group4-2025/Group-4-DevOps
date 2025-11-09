package com.napier.devops.controller;

import com.napier.devops.model.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = com.napier.devops.TestApplication.class)
@ActiveProfiles("test")
class CityControllerTest {

    @Autowired
    private CityController cityController;

    @Test
    void getAllCitiesInTheWorld_returnsNonNullList() {
        List<City> actual = cityController.getAllCitiesInTheWorld();
        assertThat(actual).isNotNull();
    }

    @Test
    void getAllCitiesInAContinent_returnsNonNullList() {
        List<City> actual = cityController.getAllCitiesInAContinent("Europe");
        assertThat(actual).isNotNull();
    }

    @Test
    void getAllCitiesInARegion_returnsNonNullList() {
        List<City> actual = cityController.getAllCitiesInARegion("Caribbean");
        assertThat(actual).isNotNull();
    }

    @Test
    void getAllCitiesInACountry_returnsNonNullList() {
        List<City> actual = cityController.getAllCitiesInACountry("Spain");
        assertThat(actual).isNotNull();
    }

    @Test
    void getAllCitiesInADistrict_returnsNonNullList() {
        List<City> actual = cityController.getAllCitiesInADistrict("Zuid-Holland");
        assertThat(actual).isNotNull();
    }

    @Test
    void getTopNCitiesInTheWorld_returnsNonNullList() {
        List<City> actual = cityController.getTopNCitiesInTheWorld(5);
        assertThat(actual).isNotNull();
    }

    @Test
    void getTopNCitiesInAContinent_returnsNonNullList() {
        List<City> actual = cityController.getTopNCitiesInAContinent("Europe", 5);
        assertThat(actual).isNotNull();
    }

    @Test
    void getTopNCitiesInARegion_returnsNonNullList() {
        List<City> actual = cityController.getTopNCitiesInARegion("Caribbean", 5);
        assertThat(actual).isNotNull();
    }

    @Test
    void getTopNCitiesInACountry_returnsNonNullList() {
        List<City> actual = cityController.getTopNCitiesInACountry("Spain", 5);
        assertThat(actual).isNotNull();
    }

    @Test
    void getTopNCitiesInADistrict_returnsNonNullList() {
        List<City> actual = cityController.getTopNCitiesInADistrict("Zuid-Holland", 5);
        assertThat(actual).isNotNull();
    }
}
