package com.napier.devops.controller;


import com.napier.devops.model.Country;
import com.napier.devops.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller for country related endpoints
 */
@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    /**
     * Get all countries in the world
     * @return a list of all countries in the world
     */
    public List<Country> getAllCountriesWorld() {
        return countryService.getAllCountriesWorld();
    }

    /**
     * USE CASE 3: List All Countries in a Region by Population (Descending)
     * @param region the region to get the countries from
     * @return a list of all countries in a region ordered by population
     */
    @GetMapping("/region")
    public ResponseEntity<List<Country>> getCountriesInRegion(@RequestParam String region) {
        return ResponseEntity.ok(countryService.getCountriesInRegionByPopulation(region));
    }

    /**
     * USE CASE 4: List Top N Most Populated Countries in the World
     * @param limit the number of countries to return
     * @return a list of top N countries in the world
     */
    @GetMapping("/world/top")
    public ResponseEntity<List<Country>> getTopCountriesWorld(@RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(countryService.getTopCountriesInWorld(limit));
    }

    /**
     * USE CASE 5: List Top N Most Populated Countries in a Continent
     * @param continent the continent to get the countries from
     * @param limit the number of countries to return
     * @return a list of top N countries in a continent
     */
    @GetMapping("/continent/top")
    public ResponseEntity<List<Country>> getTopCountriesContinent(
            @RequestParam String continent,
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(countryService.getTopCountriesInContinent(continent, limit));
    }

    /**
     * USE CASE 6: List Top N Most Populated Countries in a Region
     * @param region the region to get the countries from
     * @param limit the number of countries to return
     * @return a list of top N countries in a region
     */
    @GetMapping("/region/top")
    public ResponseEntity<List<Country>> getTopCountriesRegion(
            @RequestParam String region,
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(countryService.getTopCountriesInRegion(region, limit));
    }
}