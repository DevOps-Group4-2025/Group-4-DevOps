package com.napier.devops.service;


import com.napier.devops.model.Country;
import com.napier.devops.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for country related operations
 */
@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    /**
     * Get all countries in the world
     * @return a list of all countries in the world
     */
    public List<Country> getAllCountriesWorld() {
        return countryRepository.getAllCountriesWorld();
    }

    /**
     * Retrieves all countries in a given continent, ordered by population.
     * @param continent The name of the continent.
     * @return A list of countries in the specified continent.
     */
    public List<Country> getAllCountriesInContinent(String continent) {
        return countryRepository.findByContinentOrderByPopulationDesc(continent);
    }

    /**
     * USE CASE 3
     * @param region the region to get the countries from
     * @return a list of all countries in a region ordered by population
     */
    public List<Country> getCountriesInRegionByPopulation(String region) {
        return countryRepository.findCountriesInRegionByPopulationDesc(region);
    }

    /**
     * USE CASE 4
     * @param limit the number of countries to return
     * @return a list of top N countries in the world
     */
    public List<Country> getTopCountriesInWorld(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return countryRepository.findTopCountriesInWorld(pageable);
    }

    /**
     * USE CASE 5
     * @param continent the continent to get the countries from
     * @param limit the number of countries to return
     * @return a list of top N countries in a continent
     */
    public List<Country> getTopCountriesInContinent(String continent, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return countryRepository.findTopCountriesInContinent(continent, pageable);
    }

    /**
     * USE CASE 6
     * @param region the region to get the countries from
     * @param limit the number of countries to return
     * @return a list of top N countries in a region
     */
    public List<Country> getTopCountriesInRegion(String region, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return countryRepository.findTopCountriesInRegion(region, pageable);
    }
}