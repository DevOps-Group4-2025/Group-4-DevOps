package com.napier.devops.model;

import java.math.BigDecimal;

/**
 * Represents an aggregated population breakdown for a specific scope.
 * <p>
 * Each instance contains totals and percentages for the amount of population
 * living in cities vs not living in cities for a given scope (continent, region, or country).
 * </p>
 *
 * @param type                 the breakdown type (e.g. "continent", "region", "country")
 * @param name                 the name of the continent, region or country
 * @param totalPopulation      the total population for the scope
 * @param populationInCities   population living in cities for the scope
 * @param populationNotInCities population not living in cities for the scope
 * @param inCitiesPercentage   percent of population that lives in cities (0.00-100.00)
 * @param notInCitiesPercentage percent of population that does not live in cities (0.00-100.00)
 */
public record PopulationBreakdown(
        String type,
        String name,
        Long totalPopulation,
        Long populationInCities,
        Long populationNotInCities,
        Double inCitiesPercentage,
        Double notInCitiesPercentage
) {

    public PopulationBreakdown(String type2, String name2, long totalPopulation2, long populationInCities2,
            long populationNotInCities2, BigDecimal inCitiesPercentage2, BigDecimal notInCitiesPercentage2) {
        this(type2,
             name2,
             totalPopulation2,
             populationInCities2,
             populationNotInCities2,
             inCitiesPercentage2 != null ? inCitiesPercentage2.doubleValue() : null,
             notInCitiesPercentage2 != null ? notInCitiesPercentage2.doubleValue() : null
        );
    }
}
