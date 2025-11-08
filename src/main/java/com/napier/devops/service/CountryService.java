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
}