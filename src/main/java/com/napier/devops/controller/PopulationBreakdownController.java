package com.napier.devops.controller;

import com.napier.devops.model.PopulationBreakdown;
import com.napier.devops.service.PopulationBreakdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/population-breakdown")
public class PopulationBreakdownController {
    private final PopulationBreakdownService populationBreakdownService;

    @Autowired
    public PopulationBreakdownController(PopulationBreakdownService populationBreakdownService) {
        this.populationBreakdownService = populationBreakdownService;
    }

    @GetMapping("/continent/{continent}")
    public PopulationBreakdown getByContinent(@PathVariable String continent) {
        return populationBreakdownService.getByContinent(continent);
    }

    @GetMapping("/region/{region}")
    public PopulationBreakdown getByRegion(@PathVariable String region) {
        return populationBreakdownService.getByRegion(region);
    }

    @GetMapping("/country/{country}")
    public PopulationBreakdown getByCountry(@PathVariable String country) {
        return populationBreakdownService.getByCountry(country);
    }
}
