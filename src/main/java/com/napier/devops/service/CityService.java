package com.napier.devops.service;

import com.napier.devops.model.City;
import com.napier.devops.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service for city related operations
 */
@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    /**
     * Get all cities in the world
     * @return a list of all cities in the world
     */
    public List<City> getAllCitiesInTheWorld() {
        return cityRepository.getAllCitiesInTheWorld();
    }

    /**
     * Get all cities in a continent
     * @param continent the continent to get the cities from
     * @return a list of all cities in a continent
     */
    public List<City> getAllCitiesInAContinent(String continent) {
        return cityRepository.getAllCitiesInAContinent(continent);
    }

    /**
     * Get all cities in a region
     * @param region the region to get the cities from
     * @return a list of all cities in a region
     */
    public List<City> getAllCitiesInARegion(String region) {
        return cityRepository.getAllCitiesInARegion(region);
    }

    /**
     * Get all cities in a country
     * @param country the country to get the cities from
     * @return a list of all cities in a country
     */
    public List<City> getAllCitiesInACountry(String country) {
        return cityRepository.getAllCitiesInACountry(country);
    }

    /**
     * Get all cities in a district
     * @param district the district to get the cities from
     * @return a list of all cities in a district
     */
    public List<City> getAllCitiesInADistrict(String district) {
        return cityRepository.getAllCitiesInADistrict(district);
    }

    /**
     * Get top N cities in the world
     * @param topN the number of cities to return
     * @return a list of top N cities in the world
     */
    public List<City> getTopNCitiesInTheWorld(int topN) {
        return cityRepository.getTopNCitiesInTheWorld(topN);
    }

    /**
     * Get top N cities in a continent
     * @param continent the continent to get the cities from
     * @param topN the number of cities to return
     * @return a list of top N cities in a continent
     */
    public List<City> getTopNCitiesInAContinent(String continent, int topN) {
        return cityRepository.getTopNCitiesInAContinent(continent, topN);
    }

    /**
     * Get top N cities in a region
     * @param region the region to get the cities from
     * @param topN the number of cities to return
     * @return a list of top N cities in a region
     */
    public List<City> getTopNCitiesInARegion(String region, int topN) {
        return cityRepository.getTopNCitiesInARegion(region, topN);
    }

    /**
     * Get top N cities in a country
     * @param country the country to get the cities from
     * @param topN the number of cities to return
     * @return a list of top N cities in a country
     */
    public List<City> getTopNCitiesInACountry(String country, int topN) {
        return cityRepository.getTopNCitiesInACountry(country, topN);
    }

    /**
     * Get top N cities in a district
     * @param district the district to get the cities from
     * @param topN the number of cities to return
     * @return a list of top N cities in a district
     */
    public List<City> getTopNCitiesInADistrict(String district, int topN) {
        return cityRepository.getTopNCitiesInADistrict(district, topN);
    }
}
