package com.napier.devops.service;

import com.napier.devops.model.City;
import com.napier.devops.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public List<City> getAllCitiesInTheWorld() {
        return cityRepository.getAllCitiesInTheWorld();
    }

    public List<City> getAllCitiesInAContinent() {
        return cityRepository.getAllCitiesInAContinent();
    }

    public List<City> getAllCitiesInARegion() {
        return cityRepository.getAllCitiesInARegion();
    }

    public List<City> getAllCitiesInACountry() {
        return cityRepository.getAllCitiesInACountry();
    }

    public List<City> getAllCitiesInADistrict() {
        return cityRepository.getAllCitiesInADistrict();
    }

    public List<City> getTopNCitiesInTheWorld() {
        return cityRepository.getTopNCitiesInTheWorld();
    }

    public List<City> getTopNCitiesInAContinent() {
        return cityRepository.getTopNCitiesInAContinent();
    }

    public List<City> getTopNCitiesInARegion() {
        return cityRepository.getTopNCitiesInARegion();
    }

    public List<City> getTopNCitiesInACountry() {
        return cityRepository.getTopNCitiesInACountry();
    }

    public List<City> getTopNCitiesInADistrict() {
        return cityRepository.getTopNCitiesInADistrict();
    }
}
