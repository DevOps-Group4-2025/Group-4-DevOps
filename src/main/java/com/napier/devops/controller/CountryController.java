package com.napier.devops.controller;


import com.napier.devops.model.Country;
import com.napier.devops.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    public List<Country> getAllCountriesWorld() {
        return countryService.getAllCountriesWorld();
    }
}