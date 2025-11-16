package com.napier.devops.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Country model class.
 */
class CountryTest {

    /**
     * Tests all getter and setter methods.
     */
    @Test
    void testGettersAndSetters() {
        Country country = new Country();
        country.setCode("TST");
        country.setName("Testland");
        country.setContinent("Testinent");
        country.setRegion("Test Region");
        country.setSurfaceArea(12345.67f);
        country.setIndepYear(2000);
        country.setPopulation(1234567L);
        country.setLifeExpectancy(75.5f);
        country.setGnp(12345678.90f);
        country.setGnpOld(11111111.11f);
        country.setLocalName("Testlocal");
        country.setGovernmentForm("Testocracy");
        country.setHeadOfState("Test Head");
        country.setCapital(1L);
        country.setCode2("TS");

        assertEquals("TST", country.getCode());
        assertEquals("Testland", country.getName());
        assertEquals("Testinent", country.getContinent());
        assertEquals("Test Region", country.getRegion());
        assertEquals(12345.67f, country.getSurfaceArea());
        assertEquals(2000, country.getIndepYear());
        assertEquals(1234567L, country.getPopulation());
        assertEquals(75.5f, country.getLifeExpectancy());
        assertEquals(12345678.90f, country.getGnp());
        assertEquals(11111111.11f, country.getGnpOld());
        assertEquals("Testlocal", country.getLocalName());
        assertEquals("Testocracy", country.getGovernmentForm());
        assertEquals("Test Head", country.getHeadOfState());
        assertEquals(1L, country.getCapital());
        assertEquals("TS", country.getCode2());
    }

    /**
     * Tests constructor that initializes essential fields.
     */
    @Test
    void testConstructorWithEssentialFields() {
        Country country = new Country("C1", "Country1", "Continent1", "Region1", 1000000L);

        assertEquals("C1", country.getCode());
        assertEquals("Country1", country.getName());
        assertEquals("Continent1", country.getContinent());
        assertEquals("Region1", country.getRegion());
        assertEquals(1000000L, country.getPopulation());
    }

    /**
     * Tests the default constructor.
     */
    @Test
    void testDefaultConstructor() {
        Country country = new Country();
        assertNull(country.getCode());
        assertNull(country.getName());
        assertNull(country.getContinent());
        assertNull(country.getRegion());
        assertNull(country.getPopulation());
    }

    /**
     * Tests the toString() method.
     */
    @Test
    void testToString() {
        Country country = new Country("C1", "Country1", "Continent1", "Region1", 1000000L);
        String expected = "Country1 (C1) - Continent1, Region1 - Population: 1,000,000";
        assertEquals(expected, country.toString());
    }
}
