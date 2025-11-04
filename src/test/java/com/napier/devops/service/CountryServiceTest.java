package com.napier.devops.service;

import com.napier.devops.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = com.napier.devops.TestApplication.class)
@ActiveProfiles("test")
class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    void getAllCountriesWorld_returnsNonNullList() {
        List<Country> actual = countryService.getAllCountriesWorld();
        assertThat(actual).isNotNull();
    }
}


