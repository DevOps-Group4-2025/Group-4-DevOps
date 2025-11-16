package com.napier.devops.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CountryLanguageId model class.
 */
class CountryLanguageIdTest {

    /**
     * Tests all getter and setter methods.
     */
    @Test
    void testGettersAndSetters() {
        CountryLanguageId countryLanguageId = new CountryLanguageId();
        countryLanguageId.setCountryCode("TST");
        countryLanguageId.setLanguage("Test Language");

        assertEquals("TST", countryLanguageId.getCountryCode());
        assertEquals("Test Language", countryLanguageId.getLanguage());
    }

    /**
     * Tests the constructor.
     */
    @Test
    void testConstructor() {
        CountryLanguageId countryLanguageId = new CountryLanguageId("TST", "Test Language");

        assertEquals("TST", countryLanguageId.getCountryCode());
        assertEquals("Test Language", countryLanguageId.getLanguage());
    }

    /**
     * Tests the equals() and hashCode() methods.
     */
    @Test
    void testEqualsAndHashCode() {
        CountryLanguageId id1 = new CountryLanguageId("TST", "Test Language");
        CountryLanguageId id2 = new CountryLanguageId("TST", "Test Language");
        CountryLanguageId id3 = new CountryLanguageId("DIFFERENT", "Test Language");
        CountryLanguageId id4 = new CountryLanguageId("TST", "DIFFERENT");

        assertEquals(id1, id2);
        assertEquals(id1.hashCode(), id2.hashCode());

        assertNotEquals(id1, id3);
        assertNotEquals(id1.hashCode(), id3.hashCode());

        assertNotEquals(id1, id4);
        assertNotEquals(id1.hashCode(), id4.hashCode());

        assertNotEquals(id1, null);
        assertNotEquals(id1, new Object());
    }
}
