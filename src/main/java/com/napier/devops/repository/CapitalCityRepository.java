package com.napier.devops.repository;

import com.napier.devops.model.CapitalCity;
import com.napier.devops.model.Country;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository exposing capital city reporting queries.
 */
public interface CapitalCityRepository extends JpaRepository<Country, String> {

    /**
     * USE CASE 17: List All Capital Cities in the World by Population
     * @return a list of all capital cities in the world ordered by population
     */
    @Query("""
            SELECT new com.napier.devops.model.CapitalCity(
                city.name,
                country.name,
                city.population
            )
            FROM Country country
            JOIN City city ON city.id = country.capital
            ORDER BY city.population DESC
            """)
    List<CapitalCity> findAllCapitalCitiesByPopulationDesc();

    /**
     * USE CASE 18: List All Capital Cities in a Continent by Population
     * @param continent the continent to get the capital cities from
     * @return a list of all capital cities in a continent ordered by population
     */
    @Query("""
    SELECT new com.napier.devops.model.CapitalCity(
        city.name,
        country.name,
        city.population
    )
    FROM Country country
    JOIN City city ON city.id = country.capital
    WHERE LOWER(country.continent) = LOWER(:continent)
    ORDER BY city.population DESC
""")
    List<CapitalCity> findCapitalCitiesInContinentByPopulationDesc(@Param("continent") String continent);

    /**
     * USE CASE 19: List All Capital Cities in a Region by Population
     * @param region the region to get the capital cities from
     * @return a list of all capital cities in a region ordered by population
     */
    @Query("""
    SELECT new com.napier.devops.model.CapitalCity(
        c.name, co.name, c.population
    )
    FROM Country co
    JOIN City c ON c.id = co.capital
    WHERE LOWER(co.region) = LOWER(:region)
    ORDER BY c.population DESC
""")
    List<CapitalCity> findCapitalCitiesInRegionByPopulationDesc(@Param("region") String region);

    /**
     * USE CASE 20: Produce a Report on Top N Capital Cities in the World
     * @param pageable the pageable object to limit the results
     * @return a list of top N capital cities in the world
     */
    @Query("""
    SELECT new com.napier.devops.model.CapitalCity(
        c.name, co.name, c.population
    )
    FROM Country co
    JOIN City c ON c.id = co.capital
    ORDER BY c.population DESC
""")
    List<CapitalCity> findTopCapitalCitiesWorld(org.springframework.data.domain.Pageable pageable);

    /**
     * USE CASE 21: Produce a Report on Top N Capital Cities in a Continent
     * @param continent the continent to get the capital cities from
     * @param pageable the pageable object to limit the results
     * @return a list of top N capital cities in a continent
     */
    @Query("""
            SELECT new com.napier.devops.model.CapitalCity(
                city.name,
                country.name,
                city.population
            )
            FROM Country country
            JOIN City city ON city.id = country.capital
            WHERE LOWER(country.continent) = LOWER(:continent)
            ORDER BY city.population DESC
            """)
    List<com.napier.devops.model.CapitalCity> findTopCapitalCitiesByContinent(
            @Param("continent") String continent,
            Pageable pageable);

    /**
     * USE CASE 22: Top N capital cities in a region (desc)
     * @param region the region to get the capital cities from
     * @param pageable the pageable object to limit the results
     * @return a list of top N capital cities in a region
     */
    @Query("""
    SELECT new com.napier.devops.model.CapitalCity(
        c.name, co.name, c.population
    )
    FROM Country co
    JOIN City c ON c.id = co.capital
    WHERE LOWER(co.region) = LOWER(:region)
    ORDER BY c.population DESC
""")
    List<CapitalCity> findTopCapitalCitiesByRegion(
            @Param("region") String region,
            Pageable pageable);
}


