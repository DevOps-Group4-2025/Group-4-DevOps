package com.napier.devops.repository;

import com.napier.devops.model.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository for Population entities
 */
public interface PopulationRepository extends CrudRepository<Country, String> {

    /**
     * Requirement 26: The population of the world.
     * @return the population of the world
     */
    @Query("SELECT SUM(co.population) AS WorldPopulation FROM Country co")
    Long getWorldPopulation();

    /**
     * Requirement 27: The population of a continent.
     * @param continent the continent to get the population from
     * @return the population of a continent
     */
    @Query("SELECT SUM(co.population) AS ContinentPopulation FROM Country co WHERE co.continent = :continent")
    Long getContinentPopulation(@Param("continent") String continent);

    /**
     * Requirement 28: The population of a region.
     * @param region the region to get the population from
     * @return the population of a region
     */
    @Query("SELECT SUM(co.population) AS RegionPopulation FROM Country co WHERE co.region = :region")
    Long getRegionPopulation(@Param("region") String region);

    /**
     * Requirement 29: The population of a country.
     * @param name the name of the country
     * @return the population of a country
     */
    @Query("SELECT co.population AS CountryPopulation FROM Country co WHERE co.name = :name")
    Long getCountryPopulation(@Param("name") String name);

    /**
     * Requirement 30: The population of a district.
     * @param district the district to get the population from
     * @return the population of a district
     */
    @Query("SELECT SUM(c.population) AS DistrictPopulation FROM City c WHERE c.district = :district")
    Long getDistrictPopulation(@Param("district") String district);

    /**
     * Requirement 31: The population of a city.
     * @param name the name of the city
     * @return the population of a city
     */
    @Query(value = "SELECT Population AS CityPopulation FROM city WHERE Name = :name LIMIT 1",nativeQuery = true)
    Long getCityPopulation(@Param("name") String name);

}
