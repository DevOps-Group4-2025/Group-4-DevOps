package com.napier.devops.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CountryLanguage model class.
 */
class CountryLanguageTest {

    /**
     * Tests all getter and setter methods.
     */
    @Test
    void testGettersAndSetters() {
        CountryLanguage countryLanguage = new CountryLanguage();
        countryLanguage.setCountryCode("TST");
        countryLanguage.setLanguage("Test Language");
        countryLanguage.setIsOfficial("T");
        countryLanguage.setPercentage(50.5);

        assertEquals("TST", countryLanguage.getCountryCode());
        assertEquals("Test Language", countryLanguage.getLanguage());
        assertEquals("T", countryLanguage.getIsOfficial());
        assertEquals(50.5, countryLanguage.getPercentage());
    }
}
