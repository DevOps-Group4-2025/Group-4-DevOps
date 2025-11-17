package com.napier.devops.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LanguageStatsTest {

    @Test
    void testDefaultConstructor() {
        LanguageStats stats = new LanguageStats();

        assertNull(stats.getLanguage());
        assertNull(stats.getSpeakers());
        assertNull(stats.getPercentageOfWorldPopulation());
    }

    @Test
    void testParameterizedConstructor() {
        LanguageStats stats = new LanguageStats("English", 1500000000L, 19.5);

        assertEquals("English", stats.getLanguage());
        assertEquals(1500000000L, stats.getSpeakers());
        assertEquals(19.5, stats.getPercentageOfWorldPopulation());
    }

    @Test
    void testSettersAndGetters() {
        LanguageStats stats = new LanguageStats();

        stats.setLanguage("Spanish");
        stats.setSpeakers(500000000L);
        stats.setPercentageOfWorldPopulation(6.5);

        assertEquals("Spanish", stats.getLanguage());
        assertEquals(500000000L, stats.getSpeakers());
        assertEquals(6.5, stats.getPercentageOfWorldPopulation());
    }
}
