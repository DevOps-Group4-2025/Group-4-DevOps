package com.napier.devops.controller;

import com.napier.devops.model.CapitalCity;
import com.napier.devops.service.CapitalCityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for capital city related endpoints
 */
@RestController
@RequestMapping("/api/capitals")
public class CapitalController {

    private final CapitalCityService capitalCityService;

    /**
     * Constructor for CapitalController
     * @param capitalCityService the service to handle capital city logic
     */
    public CapitalController(CapitalCityService capitalCityService) {
        this.capitalCityService = capitalCityService;
    }

    /**
     * USE CASE 17: List All Capital Cities in the World by Population
     * @return a list of all capital cities in the world ordered by population
     */
    @GetMapping("/world")
    public ResponseEntity<List<CapitalCity>> getAllCapitalCities() {
        List<CapitalCity> results = capitalCityService.getAllCapitalCitiesByPopulation();
        return ResponseEntity.ok(results);
    }

    /**
     * USE CASE 18: List All Capital Cities in a Continent by Population
     * @param continent the continent to get the capital cities from
     * @return a list of all capital cities in a continent ordered by population
     */
    @GetMapping("/continent")
    public ResponseEntity<List<CapitalCity>> getCapitalCitiesInContinent(
            @RequestParam String continent
    ) {
        if (continent == null || continent.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        List<CapitalCity> results =
                capitalCityService.getCapitalCitiesInContinentByPopulation(continent);
        return ResponseEntity.ok(results);
    }

    /**
     * USE CASE 19: List All Capital Cities in a Region by Population
     * @param region the region to get the capital cities from
     * @return a list of all capital cities in a region ordered by population
     */
    @GetMapping("/region")
    public ResponseEntity<List<CapitalCity>> getCapitalCitiesInRegion(@RequestParam String region) {
        return ResponseEntity.ok(capitalCityService.getCapitalCitiesInRegionByPopulation(region));
    }

    /**
     * USE CASE 20: GET /api/capitals/world/top?limit=10
     * @param limit the number of capital cities to return
     * @return a list of top N capital cities in the world
     */
    @GetMapping("/world/top")
    public ResponseEntity<List<CapitalCity>> getTopCapitalCitiesWorld(@RequestParam int limit) {
        return ResponseEntity.ok(capitalCityService.getTopCapitalCitiesWorld(limit));
    }

    /**
     * USE CASE 21: GET /api/capitals/continent/top?continent=Asia&limit=10
     * @param continent the continent to get the capital cities from
     * @param limit the number of capital cities to return
     * @return a list of top N capital cities in a continent
     */
    @GetMapping("/top")
    public ResponseEntity<List<CapitalCity>> getTopCapitalCitiesInContinent(
            @RequestParam String continent,
            @RequestParam(name = "limit", defaultValue = "10") int limit
    ){
        List<CapitalCity> results = capitalCityService.getTopCapitalCitiesInContinent(continent, limit);
        return ResponseEntity.ok(results);
    }

    /**
     * USE CASE 22: GET /api/capitals/region/top?region=Caribbean&limit=10
     * @param region the region to get the capital cities from
     * @param limit the number of capital cities to return
     * @return a list of top N capital cities in a region
     */
    @GetMapping("/region/top")
    public ResponseEntity<List<CapitalCity>> getTopCapitalCitiesRegion(
            @RequestParam String region,
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(capitalCityService.getTopCapitalCitiesInRegion(region, limit));
    }
}


