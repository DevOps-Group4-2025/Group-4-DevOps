package com.napier.devops.repository;

import com.napier.devops.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@org.springframework.boot.test.context.SpringBootTest(classes = com.napier.devops.TestApplication.class)
@ActiveProfiles("test")
class CountryRepositoryIT {

    @org.springframework.beans.factory.annotation.Autowired
    private CountryRepository countryRepository;

    @Test
    void getAllCountriesWorld_executesQuery() {
        List<Country> countries = countryRepository.getAllCountriesWorld();
        assertThat(countries).isNotNull();
    }
}


