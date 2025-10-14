package com.napier.devops.service;

import com.napier.devops.model.PopulationBreakdown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.napier.devops.repository.PopulationBreakdownRepository;

@Service
public class PopulationBreakdownService {
    private final PopulationBreakdownRepository populationBreakdownRepository;

    @Autowired
    public PopulationBreakdownService(PopulationBreakdownRepository populationBreakdownRepository) {
        this.populationBreakdownRepository = populationBreakdownRepository;
    }

    public PopulationBreakdown getByContinent(String continent) {
        return populationBreakdownRepository.getByContinent(continent);
    }

    public PopulationBreakdown getByRegion(String region) {
        return populationBreakdownRepository.getByRegion(region);
    }

    public PopulationBreakdown getByCountry(String country) {
        return populationBreakdownRepository.getByCountry(country);
    }
}
