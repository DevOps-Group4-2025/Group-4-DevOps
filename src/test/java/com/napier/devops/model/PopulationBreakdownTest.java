package com.napier.devops.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the PopulationBreakdown record.
 */
class PopulationBreakdownTest {

    @Test
    void testCanonicalConstructor() {
        PopulationBreakdown breakdown = new PopulationBreakdown(
                "continent",
                "Asia",
                4600000000L,
                2300000000L,
                2300000000L,
                50.0,
                50.0
        );

        assertAll("Canonical Constructor",
                () -> assertEquals("continent", breakdown.type()),
                () -> assertEquals("Asia", breakdown.name()),
                () -> assertEquals(4600000000L, breakdown.totalPopulation()),
                () -> assertEquals(2300000000L, breakdown.populationInCities()),
                () -> assertEquals(2300000000L, breakdown.populationNotInCities()),
                () -> assertEquals(50.0, breakdown.inCitiesPercentage()),
                () -> assertEquals(50.0, breakdown.notInCitiesPercentage())
        );
    }

    /**
     * Tests the custom constructor that converts BigDecimal to Double.
     */
    @Test
    void testBigDecimalConstructor() {
        PopulationBreakdown breakdown = new PopulationBreakdown(
                "continent",
                "Europe",
                730000000L,
                500000000L,
                230000000L,
                new BigDecimal("68.49"),
                new BigDecimal("31.51")
        );

        assertAll("BigDecimal Constructor",
                () -> assertEquals("continent", breakdown.type()),
                () -> assertEquals("Europe", breakdown.name()),
                () -> assertEquals(730000000L, breakdown.totalPopulation()),
                () -> assertEquals(500000000L, breakdown.populationInCities()),
                () -> assertEquals(230000000L, breakdown.populationNotInCities()),
                () -> assertEquals(68.49, breakdown.inCitiesPercentage()),
                () -> assertEquals(31.51, breakdown.notInCitiesPercentage())
        );
    }

    /**
     * Tests null-handling in the BigDecimal constructor.
     */
    @Test
    void testBigDecimalConstructorWithNulls() {
        PopulationBreakdown breakdown = new PopulationBreakdown(
                "country",
                "Testland",
                1000L,
                600L,
                400L,
                null,
                null
        );

        assertAll("Null BigDecimal Handling",
                () -> assertEquals("country", breakdown.type()),
                () -> assertEquals("Testland", breakdown.name()),
                () -> assertEquals(1000L, breakdown.totalPopulation()),
                () -> assertEquals(600L, breakdown.populationInCities()),
                () -> assertEquals(400L, breakdown.populationNotInCities()),
                () -> assertNull(breakdown.inCitiesPercentage()),
                () -> assertNull(breakdown.notInCitiesPercentage())
        );
    }
}
