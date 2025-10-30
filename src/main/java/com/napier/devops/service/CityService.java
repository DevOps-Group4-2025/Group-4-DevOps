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

    public List<City> getAllCitiesInAContinent(String continent) {
        return cityRepository.getAllCitiesInAContinent(continent);
    }

    public List<City> getAllCitiesInARegion(String region) {
        return cityRepository.getAllCitiesInARegion(region);
    }

    public List<City> getAllCitiesInACountry(String country) {
        return cityRepository.getAllCitiesInACountry(country);
    }

    public List<City> getAllCitiesInADistrict(String district) {
        return cityRepository.getAllCitiesInADistrict(district);
    }

    public List<City> getTopNCitiesInTheWorld(int topN) {
        return cityRepository.getTopNCitiesInTheWorld(topN);
    }

    public List<City> getTopNCitiesInAContinent(String continent, int topN) {
        return cityRepository.getTopNCitiesInAContinent(continent, topN);
    }

    public List<City> getTopNCitiesInARegion(String region, int topN) {
        return cityRepository.getTopNCitiesInARegion(region, topN);
    }

    public List<City> getTopNCitiesInACountry(String country, int topN) {
        return cityRepository.getTopNCitiesInACountry(country, topN);
    }

    public List<City> getTopNCitiesInADistrict(String district, int topN) {
        return cityRepository.getTopNCitiesInADistrict(district, topN);
    }
}
