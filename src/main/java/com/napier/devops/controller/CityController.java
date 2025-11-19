package com.napier.devops.controller;

import com.napier.devops.model.City;
import com.napier.devops.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

/**
 * Controller for city related endpoints
 */
@Controller
public class CityController {

    @Autowired
    CityService cityService;

    /**
     * Get all cities in the world
     * @return a list of all cities in the world
     */
    public List<City> getAllCitiesInTheWorld() {
        return cityService.getAllCitiesInTheWorld();
    }

    /**
     * Get all cities in a continent
     * @param continent the continent to get the cities from
     * @return a list of all cities in a continent
     */
    public List<City> getAllCitiesInAContinent(String continent) {
        return cityService.getAllCitiesInAContinent(continent);
    }

    /**
     * Get all cities in a region
     * @param region the region to get the cities from
     * @return a list of all cities in a region
     */
    public List<City> getAllCitiesInARegion(String region) {
        return cityService.getAllCitiesInARegion(region);
    }

    /**
     * Get all cities in a country
     * @param country the country to get the cities from
     * @return a list of all cities in a country
     */
    public List<City> getAllCitiesInACountry(String country) {
        return cityService.getAllCitiesInACountry(country);
    }

    /**
     * Get all cities in a district
     * @param district the district to get the cities from
     * @return a list of all cities in a district
     */
    public List<City> getAllCitiesInADistrict(String district) {
        return cityService.getAllCitiesInADistrict(district);
    }

    /**
     * Get top N cities in the world
     * @param topN the number of cities to return
     * @return a list of top N cities in the world
     */
    public List<City> getTopNCitiesInTheWorld(int topN) {
        return cityService.getTopNCitiesInTheWorld(topN);
    }

    /**
     * Get top N cities in a continent
     * @param continent the continent to get the cities from
     * @param topN the number of cities to return
     * @return a list of top N cities in a continent
     */
    public List<City> getTopNCitiesInAContinent(String continent, int topN) {
        return cityService.getTopNCitiesInAContinent(continent, topN);
    }

    /**
     * Get top N cities in a region
     * @param region the region to get the cities from
     * @param topN the number of cities to return
     * @return a list of top N cities in a region
     */
    public List<City> getTopNCitiesInARegion(String region, int topN) {
        return cityService.getTopNCitiesInARegion(region, topN);
    }

    /**
     * Get top N cities in a country
     * @param country the country to get the cities from
     * @param topN the number of cities to return
     * @return a list of top N cities in a country
     */
    public List<City> getTopNCitiesInACountry(String country, int topN) {
        return cityService.getTopNCitiesInACountry(country, topN);
    }

    /**
     * Get top N cities in a district
     * @param district the district to get the cities from
     * @param topN the number of cities to return
     * @return a list of top N cities in a district
     */
    public List<City> getTopNCitiesInADistrict(String district, int topN) {
        return cityService.getTopNCitiesInADistrict(district, topN);
    }
}
