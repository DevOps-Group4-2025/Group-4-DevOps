package com.napier.devops.service;

import com.napier.devops.model.CapitalCity;
import com.napier.devops.repository.CapitalCityRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Service layer for capital city reporting use cases.
 */
@Service
public class CapitalCityService {

    private final CapitalCityRepository capitalCityRepository;

    /**
     * Constructor for CapitalCityService
     * @param capitalCityRepository the repository to handle capital city data
     */
    public CapitalCityService(CapitalCityRepository capitalCityRepository) {
        this.capitalCityRepository = capitalCityRepository;
    }

    /**
     * Returns the top {@code limit} capital cities within the supplied continent
     * ordered by population descending.
     *
     * @param continent the continent to filter on (case-insensitive)
     * @param limit     number of results to return (must be positive)
     * @return ordered list of capital cities
     * @throws IllegalArgumentException when the continent is blank or limit invalid
     */
    // USE CASE 21: Produce a Report on Top N Capital Cities in a Continent
    public List<CapitalCity> getTopCapitalCitiesInContinent(String continent, int limit) {
        if (!StringUtils.hasText(continent)) {
            throw new IllegalArgumentException("continent must not be blank");
        }
        if (limit <= 0) {
            throw new IllegalArgumentException("limit must be greater than zero");
        }

        return capitalCityRepository.findTopCapitalCitiesByContinent(
                continent.trim(),
                PageRequest.of(0, limit)
        );
    }

    /**
     * USE CASE 17: List All Capital Cities in the World by Population
     * @return a list of all capital cities in the world ordered by population
     */
    public List<CapitalCity> getAllCapitalCitiesByPopulation() {
        return capitalCityRepository.findAllCapitalCitiesByPopulationDesc();
    }

    /**
     * USE CASE 18: List All Capital Cities in a Continent by Population
     * @param continent the continent to get the capital cities from
     * @return a list of all capital cities in a continent ordered by population
     */
    public List<CapitalCity> getCapitalCitiesInContinentByPopulation(String continent) {
        return capitalCityRepository.findCapitalCitiesInContinentByPopulationDesc(continent);
    }

    /**
     * USE CASE 19: List All Capital Cities in a Region by Population
     * @param region the region to get the capital cities from
     * @return a list of all capital cities in a region ordered by population
     */
    public List<CapitalCity> getCapitalCitiesInRegionByPopulation(String region) {
        return capitalCityRepository.findCapitalCitiesInRegionByPopulationDesc(region);
    }

    /**
     * USE CASE 20: Produce a Report on Top N Capital Cities in the World
     * @param limit the number of capital cities to return
     * @return a list of top N capital cities in the world
     */
    public List<CapitalCity> getTopCapitalCitiesWorld(int limit) {
        return capitalCityRepository.findTopCapitalCitiesWorld(org.springframework.data.domain.PageRequest.of(0, limit));
    }

    /**
     * USE CASE 22: Produce a Report on Top N Capital Cities in a Region
     * @param region the region to get the capital cities from
     * @param limit the number of capital cities to return
     * @return a list of top N capital cities in a region
     */
    public List<CapitalCity> getTopCapitalCitiesInRegion(String region, int limit) {
        return capitalCityRepository.findTopCapitalCitiesByRegion(
                region, org.springframework.data.domain.PageRequest.of(0, limit));
    }
}


