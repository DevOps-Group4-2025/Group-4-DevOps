package com.napier.devops.service;


import com.napier.devops.model.Country;
import com.napier.devops.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public List<Country> getAllCountriesWorld() {
        return countryRepository.getAllCountriesWorld();
    }

    /**
     * Retrieves all countries in a given continent, ordered by population.
     * @param continent The name of the continent.
     * @return A list of countries in the specified continent.
     */
    public List<Country> getAllCountriesInContinent(String continent) {
        return countryRepository.findByContinentOrderByPopulationDesc(continent);
    }
    // USE CASE 3
    public List<Country> getAllCountriesInRegion(String region) {
        return countryRepository.findCountriesInRegionByPopulationDesc(region);
    }

    // USE CASE 4
    public List<Country> getTopNCountriesInWorld(int limit) {
        List<Country> countries = countryRepository.findTopCountriesInWorld();
        return countries.size() > limit ? countries.subList(0, limit) : countries;
    }

    // USE CASE 5
    public List<Country> getTopNCountriesInContinent(String continent, int limit) {
        List<Country> countries = countryRepository.findTopCountriesInContinent(continent);
        return countries.size() > limit ? countries.subList(0, limit) : countries;
    }

    // USE CASE 6
    public List<Country> getTopNCountriesInRegion(String region, int limit) {
        List<Country> countries = countryRepository.findTopCountriesInRegion(region);
        return countries.size() > limit ? countries.subList(0, limit) : countries;
    }
}