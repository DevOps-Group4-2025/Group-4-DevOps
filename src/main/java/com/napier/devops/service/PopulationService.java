package com.napier.devops.service;

import com.napier.devops.repository.PopulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for population related operations
 */
@Service
public class PopulationService{

@Autowired
PopulationRepository populationRepository;

    /**
     * Get the world population
     * @return the world population
     */
    public Long getWorldPopulation() {
        return populationRepository.getWorldPopulation();
    }

    /**
     * Get the population of a continent
     * @param continent the continent to get the population from
     * @return the population of a continent
     */
    public Long getContinentPopulation(String continent) {
        return populationRepository.getContinentPopulation(continent);
    }

    /**
     * Get the population of a region
     * @param region the region to get the population from
     * @return the population of a region
     */
    public Long getRegionPopulation(String region) {
        return populationRepository.getRegionPopulation(region);
    }

    /**
     * Get the population of a country
     * @param country the country to get the population from
     * @return the population of a country
     */
    public Long getCountryPopulation(String country) {
        return populationRepository.getCountryPopulation(country);
    }

    /**
     * Get the population of a district
     * @param district the district to get the population from
     * @return the population of a district
     */
    public Long getDistrictPopulation(String district) {
        return populationRepository.getDistrictPopulation(district);
    }

    /**
     * Get the population of a city
     * @param city the city to get the population from
     * @return the population of a city
     */
    public Long getCityPopulation(String city) {
        return populationRepository.getCityPopulation(city);
    }

}
