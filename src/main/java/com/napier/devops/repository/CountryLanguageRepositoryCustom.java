package com.napier.devops.repository;

import java.util.List;

/**
 * Custom fragment interface for CountryLanguageRepository.
 * Allows database-specific SQL implementations.
 */
public interface CountryLanguageRepositoryCustom {
    List<LanguageStatsProjection> getLanguageStatistics();
}

