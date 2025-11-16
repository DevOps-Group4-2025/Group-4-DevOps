package com.napier.devops.repository;

/**
 * Projection interface for language statistics native queries.
 * <p>
 * Spring Data maps native query result set columns (aliased to camelCase)
 * to the getter method names declared here.
 * </p>
 */
public interface LanguageStatsProjection {
    String getLanguage();

    Long getSpeakers();

    Double getPercentageOfWorldPopulation();
}

