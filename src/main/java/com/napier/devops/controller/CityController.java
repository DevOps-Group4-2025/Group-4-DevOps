package com.napier.devops.controller;

import com.napier.devops.model.City;
import com.napier.devops.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    public List<City> getAllCitiesInTheWorld() {
        return cityService.getAllCitiesInTheWorld();
    }

    public List<City> getAllCitiesInAContinent() {
        return cityService.getAllCitiesInAContinent();
    }

    public List<City> getAllCitiesInARegion() {
        return cityService.getAllCitiesInARegion();
    }

    public List<City> getAllCitiesInACountry() {
        return cityService.getAllCitiesInACountry();
    }

    public List<City> getAllCitiesInADistrict() {
        return cityService.getAllCitiesInADistrict();
    }

    public List<City> getTopNCitiesInTheWorld() {
        return cityService.getTopNCitiesInTheWorld();
    }

    public List<City> getTopNCitiesInAContinent() {
        return cityService.getTopNCitiesInAContinent();
    }

    public List<City> getTopNCitiesInARegion() {
        return cityService.getTopNCitiesInARegion();
    }

    public List<City> getTopNCitiesInACountry() {
        return cityService.getTopNCitiesInACountry();
    }

    public List<City> getTopNCitiesInADistrict() {
        return cityService.getTopNCitiesInADistrict();
    }
}
