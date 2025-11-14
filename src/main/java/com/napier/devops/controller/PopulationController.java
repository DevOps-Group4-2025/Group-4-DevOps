package com.napier.devops.controller;

import com.napier.devops.service.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PopulationController {

    @Autowired
    private PopulationService populationService;

    public Long getWorldPopulation() {
        return populationService.getWorldPopulation();
    }

    public Long getContinentPopulation(String continent) {
        return populationService.getContinentPopulation(continent);
    }

    public Long getRegionPopulation(String region) {
        return populationService.getRegionPopulation(region);
    }

    public Long getCountryPopulation(String country) {
        return populationService.getCountryPopulation(country);
    }

    public Long getDistrictPopulation(String country) {
        return populationService.getDistrictPopulation(country);
    }

    public Long getCityPopulation(String city) {
        return populationService.getCityPopulation(city);
    }
}
