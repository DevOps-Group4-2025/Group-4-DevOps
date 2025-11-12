package com.napier.devops.controller;

import com.napier.devops.model.CapitalCity;
import com.napier.devops.service.CapitalCityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/capitals")
public class CapitalController {

    private final CapitalCityService capitalCityService;

    public CapitalController(CapitalCityService capitalCityService) {
        this.capitalCityService = capitalCityService;
    }

    // USE CASE 17: List All Capital Cities in the World by Population
    @GetMapping("/world")
    public ResponseEntity<List<CapitalCity>> getAllCapitalCities() {
        List<CapitalCity> results = capitalCityService.getAllCapitalCitiesByPopulation();
        return ResponseEntity.ok(results);
    }
    // USE CASE 18: List All Capital Cities in a Continent by Population
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
    // USE CASE 19: List All Capital Cities in a Region by Population
    @GetMapping("/region")
    public ResponseEntity<List<CapitalCity>> getCapitalCitiesInRegion(@RequestParam String region) {
        return ResponseEntity.ok(capitalCityService.getCapitalCitiesInRegionByPopulation(region));
    }
    // 20: GET /api/capitals/world/top?limit=10
    @GetMapping("/world/top")
    public ResponseEntity<List<CapitalCity>> getTopCapitalCitiesWorld(@RequestParam int limit) {
        return ResponseEntity.ok(capitalCityService.getTopCapitalCitiesWorld(limit));
    }
    // 21: GET /api/capitals/continent/top?continent=Asia&limit=10
    @GetMapping("/top")
    public ResponseEntity<List<CapitalCity>> getTopCapitalCitiesInContinent(
            @RequestParam String continent,
            @RequestParam(name = "limit", defaultValue = "10") int limit
    ){
        List<CapitalCity> results = capitalCityService.getTopCapitalCitiesInContinent(continent, limit);
        return ResponseEntity.ok(results);
    }
    // 22: GET /api/capitals/region/top?region=Caribbean&limit=10
    @GetMapping("/region/top")
    public ResponseEntity<List<CapitalCity>> getTopCapitalCitiesRegion(
            @RequestParam String region,
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(capitalCityService.getTopCapitalCitiesInRegion(region, limit));
    }
}


