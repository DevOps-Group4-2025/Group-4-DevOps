package com.napier.devops.service;

import com.napier.devops.model.LanguageStats;
import com.napier.devops.repository.CountryLanguageRepository;
import com.napier.devops.repository.LanguageStatsProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing and retrieving language-related statistics.
 */
@Service
public class LanguageService {

    @Autowired
    private CountryLanguageRepository countryLanguageRepository;

    /**
     * USE CASE 32: Retrieves language statistics for Chinese, English, Hindi, Spanish, and Arabic.
     * Returns the languages ordered by number of speakers (descending), including
     * the total number of speakers and percentage of world population.
     *
     * @return List of LanguageStats ordered by speakers (descending)
     */
    public List<LanguageStats> getLanguageStatistics() {
        List<LanguageStatsProjection> projections = countryLanguageRepository.getLanguageStatistics();
        return projections.stream()
                .map(p -> new LanguageStats(
                        p.getLanguage(),
                        p.getSpeakers(),
                        p.getPercentageOfWorldPopulation()
                ))
                .toList();
    }
}

