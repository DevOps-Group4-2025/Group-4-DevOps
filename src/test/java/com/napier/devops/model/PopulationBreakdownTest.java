package com.napier.devops.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the PopulationBreakdown record.
 */
class PopulationBreakdownTest {

    /**
     * Tests the custom constructor that converts BigDecimal to Double.
     */
    @Test
    void testBigDecimalConstructor() {
        // Arrange
        String type = "Continent";
        String name = "Europe";
        long totalPopulation = 730000000L;
        long populationInCities = 500000000L;
        long populationNotInCities = 230000000L;
        BigDecimal inCitiesPercentage = new BigDecimal("68.49");
        BigDecimal notInCitiesPercentage = new BigDecimal("31.51");

        // Act
        PopulationBreakdown breakdown = new PopulationBreakdown(type, name, totalPopulation, populationInCities, populationNotInCities, inCitiesPercentage, notInCitiesPercentage);

        // Assert
        assertEquals(68.49, breakdown.inCitiesPercentage());
        assertEquals(31.51, breakdown.notInCitiesPercentage());
        assertEquals(name, breakdown.name());
    }
}
