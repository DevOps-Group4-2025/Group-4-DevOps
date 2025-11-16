package com.napier.devops.repository;

import com.napier.devops.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends CrudRepository<City, String> {

    // Requirement 7: All the cities in the world organised by largest population to smallest.
    @Query("SELECT c FROM City c ORDER BY c.population DESC")
    List<City> getAllCitiesInTheWorld();

    // Requirement 8: All the cities in a continent organised by largest population to smallest.
    @Query("SELECT c FROM City c, Country co WHERE c.countryCode = co.code AND co.continent = :continent ORDER BY c.population DESC")
    List<City> getAllCitiesInAContinent(@Param("continent") String continent);

    // Requirement 9: All the cities in a region organised by largest population to smallest.
    @Query("SELECT c FROM City c, Country co WHERE c.countryCode = co.code AND co.region = :region ORDER BY c.population DESC")
    List<City> getAllCitiesInARegion(@Param("region") String region);

    // Requirement 10: All the cities in a country organised by largest population to smallest.
    @Query("SELECT c FROM City c, Country co WHERE c.countryCode = co.code AND co.name = :country ORDER BY c.population DESC")
    List<City> getAllCitiesInACountry(@Param("country") String country);

    // Requirement 11: All the cities in a district organised by largest population to smallest.
    @Query("SELECT c FROM City c WHERE c.district = :district ORDER BY c.population DESC")
    List<City> getAllCitiesInADistrict(@Param("district") String district);

    // Requirement 12: The top N populated cities in the world where N is provided by the user.
    @Query("SELECT c FROM City c ORDER BY c.population DESC LIMIT :topN")
    List<City> getTopNCitiesInTheWorld(@Param("topN") int topN);

    // Requirement 13: The top N populated cities in a continent where N is provided by the user.
    @Query("SELECT c FROM City c, Country co WHERE c.countryCode = co.code AND co.continent = :continent ORDER BY c.population DESC LIMIT :topN")
    List<City> getTopNCitiesInAContinent(@Param("continent") String continent, @Param("topN") int topN);

    // Requirement 14: The top N populated cities in a region where N is provided by the user.
    @Query("SELECT c FROM City c, Country co WHERE c.countryCode = co.code AND co.region = :region ORDER BY c.population DESC LIMIT :topN")
    List<City> getTopNCitiesInARegion(@Param("region") String region, @Param("topN") int topN);

    // Requirement 15: The top N populated cities in a country where N is provided by the user.
    @Query("SELECT c FROM City c, Country co WHERE c.countryCode = co.code AND co.name = :country ORDER BY c.population DESC LIMIT :topN")
    List<City> getTopNCitiesInACountry(@Param("country") String country, @Param("topN") int topN);

    // Requirement 16: The top N populated cities in a district where N is provided by the user.
    @Query("SELECT c FROM City c WHERE c.district = :district ORDER BY c.population DESC LIMIT :topN")
    List<City> getTopNCitiesInADistrict(@Param("district") String district, @Param("topN") int topN);
}
