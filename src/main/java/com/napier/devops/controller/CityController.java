package com.napier.devops.controller;

import com.napier.devops.model.City;
import com.napier.devops.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class CityController {

    @Autowired
    CityService cityService;

    public List<City> getAllCitiesInTheWorld() {
        return cityService.getAllCitiesInTheWorld();
    }

    public List<City> getAllCitiesInAContinent(String continent) {
        return cityService.getAllCitiesInAContinent(continent);
    }

    public List<City> getAllCitiesInARegion(String region) {
        return cityService.getAllCitiesInARegion(region);
    }

    public List<City> getAllCitiesInACountry(String country) {
        return cityService.getAllCitiesInACountry(country);
    }

    public List<City> getAllCitiesInADistrict(String district) {
        return cityService.getAllCitiesInADistrict(district);
    }

    public List<City> getTopNCitiesInTheWorld(int topN) {
        return cityService.getTopNCitiesInTheWorld(topN);
    }

    public List<City> getTopNCitiesInAContinent(String continent, int topN) {
        return cityService.getTopNCitiesInAContinent(continent, topN);
    }

    public List<City> getTopNCitiesInARegion(String region, int topN) {
        return cityService.getTopNCitiesInARegion(region, topN);
    }

    public List<City> getTopNCitiesInACountry(String country, int topN) {
        return cityService.getTopNCitiesInACountry(country, topN);
    }

    public List<City> getTopNCitiesInADistrict(String district, int topN) {
        return cityService.getTopNCitiesInADistrict(district, topN);
    }
}
