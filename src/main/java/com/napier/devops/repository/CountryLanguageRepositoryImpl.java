package com.napier.devops.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Custom implementation of CountryLanguageRepository to handle database-specific SQL syntax.
 * Uses backticks for MySQL and double quotes for H2.
 */
public class CountryLanguageRepositoryImpl implements CountryLanguageRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Environment environment;

    public List<LanguageStatsProjection> getLanguageStatistics() {
        String databaseUrl = environment.getProperty("spring.datasource.url", "");
        boolean isH2 = databaseUrl.contains("h2:");
        
        String queryString;
        if (isH2) {
            // H2 uses double quotes for identifiers and BIGINT is supported
            queryString = """
                SELECT 
                    cl."Language" AS language,
                    CAST(SUM(c."Population" * cl."Percentage" / 100) AS BIGINT) AS speakers,
                    CAST((SUM(c."Population" * cl."Percentage" / 100) * 100.0 / 
                          (SELECT SUM("Population") FROM country)) AS DECIMAL(5,2)) AS percentageOfWorldPopulation
                FROM countrylanguage cl
                INNER JOIN country c ON cl."CountryCode" = c."Code"
                WHERE cl."Language" IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic')
                GROUP BY cl."Language"
                ORDER BY speakers DESC
                """;
        } else {
            // MySQL uses backticks for identifiers and SIGNED instead of BIGINT
            queryString = """
                SELECT 
                    cl.`Language` AS language,
                    CAST(SUM(c.`Population` * cl.`Percentage` / 100) AS SIGNED) AS speakers,
                    CAST((SUM(c.`Population` * cl.`Percentage` / 100) * 100.0 / 
                          (SELECT SUM(`Population`) FROM country)) AS DECIMAL(5,2)) AS percentageOfWorldPopulation
                FROM countrylanguage cl
                INNER JOIN country c ON cl.`CountryCode` = c.`Code`
                WHERE cl.`Language` IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic')
                GROUP BY cl.`Language`
                ORDER BY speakers DESC
                """;
        }
        
        Query query = entityManager.createNativeQuery(queryString);
        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();
        
        return results.stream().map(row -> new LanguageStatsProjection() {
            @Override
            public String getLanguage() {
                return (String) row[0];
            }
            
            @Override
            public Long getSpeakers() {
                return ((Number) row[1]).longValue();
            }
            
            @Override
            public Double getPercentageOfWorldPopulation() {
                return ((Number) row[2]).doubleValue();
            }
        }).collect(Collectors.toList());
    }
}

