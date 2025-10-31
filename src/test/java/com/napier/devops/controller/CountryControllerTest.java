package com.napier.devops.controller;

import com.napier.devops.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = com.napier.devops.TestApplication.class)
@ActiveProfiles("test")
class CountryControllerTest {

    @Autowired
    private CountryController countryController;

    @Test
    void getAllCountriesWorld_returnsNonNullList() {
        List<Country> actual = countryController.getAllCountriesWorld();
        assertThat(actual).isNotNull();
    }
}


