package com.napier.devops.repository;

/**
 * Projection interface for aggregated population breakdown native queries.
 * <p>
 * Spring Data maps native query result set columns (aliased to camelCase)
 * to the getter method names declared here.
 * </p>
 */
public interface PopulationBreakdownProjection {
    String getType();

    String getName();

    Long getTotalPopulation();

    Long getPopulationInCities();

    Long getPopulationNotInCities();

    Double getInCitiesPercentage();

    Double getNotInCitiesPercentage();
}
