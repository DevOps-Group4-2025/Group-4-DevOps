package com.napier.devops.controller;

import com.napier.devops.service.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Controller for population related endpoints
 */
@Controller
public class PopulationController {

    @Autowired
    private PopulationService populationService;

    /**
     * Get the world population
     * @return the world population
     */
    public Long getWorldPopulation() {
        return populationService.getWorldPopulation();
    }

    /**
     * Get the population of a continent
     * @param continent the continent to get the population from
     * @return the population of a continent
     */
    public Long getContinentPopulation(String continent) {
        return populationService.getContinentPopulation(continent);
    }

    /**
     * Get the population of a region
     * @param region the region to get the population from
     * @return the population of a region
     */
    public Long getRegionPopulation(String region) {
        return populationService.getRegionPopulation(region);
    }

    /**
     * Get the population of a country
     * @param country the country to get the population from
     * @return the population of a country
     */
    public Long getCountryPopulation(String country) {
        return populationService.getCountryPopulation(country);
    }

    /**
     * Get the population of a district
     * @param country the country to get the population from
     * @return the population of a district
     */
    public Long getDistrictPopulation(String country) {
        return populationService.getDistrictPopulation(country);
    }

    /**
     * Get the population of a city
     * @param city the city to get the population from
     * @return the population of a city
     */
    public Long getCityPopulation(String city) {
        return populationService.getCityPopulation(city);
    }
}
