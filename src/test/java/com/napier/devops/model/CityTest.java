package com.napier.devops.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the City model class.
 */
class CityTest {

    /**
     * Tests all getter and setter methods.
     */
    @Test
    void testGettersAndSetters() {
        City city = new City();
        Long id = 1L;
        String name = "Test City";
        String countryCode = "TST";
        String district = "Test District";
        Integer population = 100000;

        city.setId(id);
        city.setName(name);
        city.setCountryCode(countryCode);
        city.setDistrict(district);
        city.setPopulation(population);

        assertEquals(id, city.getId());
        assertEquals(name, city.getName());
        assertEquals(countryCode, city.getCountryCode());
        assertEquals(district, city.getDistrict());
        assertEquals(population, city.getPopulation());
    }

    /**
     * Tests the toString() method for correct formatting.
     */
    @Test
    void testToString() {
        City city = new City();
        city.setName("Kabul");
        city.setCountryCode("AFG");
        city.setDistrict("Kabol");
        city.setPopulation(1780000);

        String expected = "Kabul (AFG) - Kabol - Population: 1,780,000";
        assertEquals(expected, city.toString());
    }
}
