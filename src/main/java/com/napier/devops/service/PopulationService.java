package com.napier.devops.service;

import com.napier.devops.repository.PopulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopulationService{

@Autowired
PopulationRepository populationRepository;

    public Long getWorldPopulation() {
        return populationRepository.getWorldPopulation();
    }

    public Long getContinentPopulation(String continent) {
        return populationRepository.getContinentPopulation(continent);
    }

    public Long getRegionPopulation(String region) {
        return populationRepository.getRegionPopulation(region);
    }

    public Long getCountryPopulation(String country) {
        return populationRepository.getCountryPopulation(country);
    }

    public Long getDistrictPopulation(String district) {
        return populationRepository.getDistrictPopulation(district);
    }

    public Long getCityPopulation(String city) {
        return populationRepository.getCityPopulation(city);
    }

}
