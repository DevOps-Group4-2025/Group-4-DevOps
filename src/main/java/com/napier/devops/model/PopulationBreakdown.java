package com.napier.devops.model;

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
}
