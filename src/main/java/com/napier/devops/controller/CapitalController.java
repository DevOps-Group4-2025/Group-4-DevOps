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

    @GetMapping("/top")
    public ResponseEntity<List<CapitalCity>> getTopCapitalCitiesInContinent(
            @RequestParam String continent,
            @RequestParam(name = "limit", defaultValue = "10") int limit
    ) {
        List<CapitalCity> results = capitalCityService.getTopCapitalCitiesInContinent(continent, limit);
        return ResponseEntity.ok(results);
    }
}
