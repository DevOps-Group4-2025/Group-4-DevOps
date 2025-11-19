package com.napier.devops.repository;


import com.napier.devops.model.Country;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for Country entities
 */
public interface CountryRepository extends CrudRepository<Country, String> {

    /**
     * Use Case 1: Get all countries in the world ordered by population (descending)
     * @return a list of all countries in the world
     */
    @Query("SELECT c FROM Country c ORDER BY c.population DESC")
    List<Country> getAllCountriesWorld();

    /**
     * Use Case 2: All the countries in a continent organised by largest population to smallest.
     * @param continent the continent to get the countries from
     * @return a list of all countries in a continent
     */
    @Query("SELECT c FROM Country c WHERE c.continent = :continent ORDER BY c.population DESC")
    List<Country> findByContinentOrderByPopulationDesc(@Param("continent") String continent);

    /**
     * USE CASE 3: List All Countries in a Region by Population (Descending)
     * @param region the region to get the countries from
     * @return a list of all countries in a region
     */
    @Query("""
        SELECT c FROM Country c
        WHERE LOWER(c.region) = LOWER(:region)
        ORDER BY c.population DESC
    """)
    List<Country> findCountriesInRegionByPopulationDesc(@Param("region") String region);

    /**
     * USE CASE 4: List Top N Most Populated Countries in the World
     * @param pageable the pageable object to limit the results
     * @return a list of top N countries in the world
     */
    @Query("""
        SELECT c FROM Country c
        ORDER BY c.population DESC
    """)
    List<Country> findTopCountriesInWorld(Pageable pageable);

    /**
     * USE CASE 5: List Top N Most Populated Countries in a Continent
     * @param continent the continent to get the countries from
     * @param pageable the pageable object to limit the results
     * @return a list of top N countries in a continent
     */
    @Query("""
        SELECT c FROM Country c
        WHERE LOWER(c.continent) = LOWER(:continent)
        ORDER BY c.population DESC
    """)
    List<Country> findTopCountriesInContinent(@Param("continent") String continent, Pageable pageable);

    /**
     * USE CASE 6: List Top N Most Populated Countries in a Region
     * @param region the region to get the countries from
     * @param pageable the pageable object to limit the results
     * @return a list of top N countries in a region
     */
    @Query("""
        SELECT c FROM Country c
        WHERE LOWER(c.region) = LOWER(:region)
        ORDER BY c.population DESC
    """)
    List<Country> findTopCountriesInRegion(@Param("region") String region, Pageable pageable);


}