package com.napier.devops.repository;

import com.napier.devops.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CityRepository extends CrudRepository<City, String> {

    // Requirement 7: All the cities in the world organised by largest population to smallest.
    @Query("SELECT c FROM City c ORDER BY c.population DESC")
    List<City> getAllCitiesInTheWorld();

    // Requirement 8: All the cities in a continent organised by largest population to smallest.
    @Query("SELECT c FROM City c, Country co WHERE c.countryCode = co.code AND co.continent = 'Asia' ORDER BY c.population DESC")
    List<City> getAllCitiesInAContinent();

    // Requirement 9: All the cities in a region organised by largest population to smallest.
    @Query("SELECT c FROM City c, Country co WHERE c.countryCode = co.code AND co.region = 'Eastern Asia' ORDER BY c.population DESC")
    List<City> getAllCitiesInARegion();

    // Requirement 10: All the cities in a country organised by largest population to smallest.
    @Query("SELECT c FROM City c, Country co WHERE c.countryCode = co.code AND co.name = 'Japan' ORDER BY c.population DESC")
    List<City> getAllCitiesInACountry();

    // Requirement 11: All the cities in a district organised by largest population to smallest.
    @Query("SELECT c FROM City c WHERE c.district = 'Shanghai' ORDER BY c.population DESC")
    List<City> getAllCitiesInADistrict();

    // Requirement 12: The top N populated cities in the world where N is provided by the user.
    @Query("SELECT c FROM City c ORDER BY c.population DESC LIMIT 10")
    List<City> getTopNCitiesInTheWorld();

    // Requirement 13: The top N populated cities in a continent where N is provided by the user.
    @Query("SELECT c FROM City c, Country co WHERE c.countryCode = co.code AND co.continent = 'Asia' ORDER BY c.population DESC LIMIT 10")
    List<City> getTopNCitiesInAContinent();

    // Requirement 14: The top N populated cities in a region where N is provided by the user.
    @Query("SELECT c FROM City c, Country co WHERE c.countryCode = co.code AND co.region = 'Eastern Asia' ORDER BY c.population DESC LIMIT 10")
    List<City> getTopNCitiesInARegion();

    // Requirement 15: The top N populated cities in a country where N is provided by the user.
    @Query("SELECT c FROM City c, Country co WHERE c.countryCode = co.code AND co.name = 'Japan' ORDER BY c.population DESC LIMIT 10")
    List<City> getTopNCitiesInACountry();

    // Requirement 16: The top N populated cities in a district where N is provided by the user.
    @Query("SELECT c FROM City c WHERE c.district = 'Shanghai' ORDER BY c.population DESC LIMIT 10")
    List<City> getTopNCitiesInADistrict();
}
