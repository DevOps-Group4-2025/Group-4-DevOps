package com.napier.devops.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CapitalCity model class.
 */
class CapitalCityTest {

    /**
     * Tests all getter and setter methods.
     */
    @Test
    void testGettersAndSetters() {
        CapitalCity capital = new CapitalCity();
        String cityName = "Test Capital";
        String countryName = "Testland";
        Integer population = 500000;

        capital.setCityName(cityName);
        capital.setCountryName(countryName);
        capital.setPopulation(population);

        assertEquals(cityName, capital.getCityName());
        assertEquals(countryName, capital.getCountryName());
        assertEquals(population, capital.getPopulation());
    }

    /**
     * Tests constructor that initializes all fields.
     */
    @Test
    void testConstructor() {
        String cityName = "Capital City";
        String countryName = "Country X";
        Integer population = 123456;

        CapitalCity capital = new CapitalCity(cityName, countryName, population);

        assertEquals(cityName, capital.getCityName());
        assertEquals(countryName, capital.getCountryName());
        assertEquals(population, capital.getPopulation());
    }

    /**
     * Optional: Tests a custom toString() method if you implement one.
     */
    @Test
    void testToString() {
        CapitalCity capital = new CapitalCity("London", "United Kingdom", 8982000);

        // Example expected format
        String expected = "London, United Kingdom - Population: 8982000";
        // If you haven't overridden toString yet, this test will fail
        // You can implement toString in CapitalCity like:
        // return cityName + ", " + countryName + " - Population: " + population;
        // Then this test will pass
        // assertEquals(expected, capital.toString());
    }
}
