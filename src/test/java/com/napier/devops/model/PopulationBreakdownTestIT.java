package com.napier.devops.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link PopulationBreakdown} model.
 * This test verifies that the record can be created and its fields accessed correctly.
 */
class PopulationBreakdownTest {

    /**
     * 
     */
    @Test
    void populationBreakdownRecordCanBeCreatedAndAccessed() {
        // Given
        String type = "Continent";
        String name = "Asia";
        long totalPopulation = 1_000_000L;
        long populationInCities = 250_000L;
        long populationNotInCities = 750_000L;
        BigDecimal inCitiesPercentage = new BigDecimal("25.00");
        BigDecimal notInCitiesPercentage = new BigDecimal("75.00");

        // When
        PopulationBreakdown breakdown = new PopulationBreakdown(
                type,
                name,
                totalPopulation,
                populationInCities,
                populationNotInCities,
                inCitiesPercentage,
                notInCitiesPercentage
        );

        // Then
        assertThat(breakdown.type()).isEqualTo(type);
        assertThat(breakdown.name()).isEqualTo(name);
        assertThat(breakdown.totalPopulation()).isEqualTo(totalPopulation);
        assertThat(breakdown.populationInCities()).isEqualTo(populationInCities);
        assertThat(breakdown.populationNotInCities()).isEqualTo(populationNotInCities);
    }
}