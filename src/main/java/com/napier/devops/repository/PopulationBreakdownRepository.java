package com.napier.devops.repository;

import com.napier.devops.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring Data repository exposing native queries for population breakdowns.
 * <p>
 * This repository returns projection interfaces ( {@link PopulationBreakdownProjection} )
 * which are mapped from native SQL result sets. The service layer maps projections
 * into the {@link com.napier.devops.model.PopulationBreakdown} record.
 * </p>
 */
public interface PopulationBreakdownRepository extends JpaRepository<Country, String> {

    @Query(value = "SELECT 'continent' AS type, country.continent AS name, "
        + "SUM(country.Population) AS totalPopulation, "
        + "COALESCE(SUM(city.Population),0) AS populationInCities, "
        + "SUM(country.Population) - COALESCE(SUM(city.Population),0) AS populationNotInCities, "
        + "ROUND(CASE WHEN SUM(country.Population) > 0 THEN COALESCE(SUM(city.Population),0) / SUM(country.Population) * 100 ELSE 0 END, 2) AS inCitiesPercentage, "
        + "ROUND(CASE WHEN SUM(country.Population) > 0 THEN (SUM(country.Population) - COALESCE(SUM(city.Population),0)) / SUM(country.Population) * 100 ELSE 0 END, 2) AS notInCitiesPercentage "
        + "FROM country LEFT JOIN city ON country.code = city.countrycode "
        + "GROUP BY country.continent "
        + "ORDER BY totalPopulation DESC", nativeQuery = true)
    List<PopulationBreakdownProjection> getAllByContinent();

    @Query(value = "SELECT 'region' AS type, country.region AS name, "
        + "SUM(country.Population) AS totalPopulation, "
        + "COALESCE(SUM(city.Population),0) AS populationInCities, "
        + "SUM(country.Population) - COALESCE(SUM(city.Population),0) AS populationNotInCities, "
        + "ROUND(CASE WHEN SUM(country.Population) > 0 THEN COALESCE(SUM(city.Population),0) / SUM(country.Population) * 100 ELSE 0 END, 2) AS inCitiesPercentage, "
        + "ROUND(CASE WHEN SUM(country.Population) > 0 THEN (SUM(country.Population) - COALESCE(SUM(city.Population),0)) / SUM(country.Population) * 100 ELSE 0 END, 2) AS notInCitiesPercentage "
        + "FROM country LEFT JOIN city ON country.code = city.countrycode "
        + "GROUP BY country.region "
        + "ORDER BY totalPopulation DESC", nativeQuery = true)
    List<PopulationBreakdownProjection> getAllByRegion();

    @Query(value = "SELECT 'country' AS type, country.Name AS name, "
        + "country.Population AS totalPopulation, "
        + "COALESCE(SUM(city.Population),0) AS populationInCities, "
        + "country.Population - COALESCE(SUM(city.Population),0) AS populationNotInCities, "
        + "ROUND(CASE WHEN country.Population > 0 THEN COALESCE(SUM(city.Population),0) / country.Population * 100 ELSE 0 END, 2) AS inCitiesPercentage, "
        + "ROUND(CASE WHEN country.Population > 0 THEN (country.Population - COALESCE(SUM(city.Population),0)) / country.Population * 100 ELSE 0 END, 2) AS notInCitiesPercentage "
        + "FROM country LEFT JOIN city ON country.code = city.countrycode "
        + "GROUP BY country.Name, country.Population "
        + "ORDER BY totalPopulation DESC", nativeQuery = true)
    List<PopulationBreakdownProjection> getAllByCountry();
}
