package com.napier.devops.service;

import com.napier.devops.model.PopulationBreakdown;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = com.napier.devops.TestApplication.class)
@ActiveProfiles("test")
class PopulationBreakdownServiceTest {

    @Autowired
    private PopulationBreakdownService populationBreakdownService;

    @Test
    void getAllByContinent_returnsNonNullList() {
        List<PopulationBreakdown> actual = populationBreakdownService.getAllByContinent();
        assertThat(actual).isNotNull();
    }

    @Test
    void getAllByRegion_returnsNonNullList() {
        List<PopulationBreakdown> actual = populationBreakdownService.getAllByRegion();
        assertThat(actual).isNotNull();
    }

    @Test
    void getAllByCountry_returnsNonNullList() {
        List<PopulationBreakdown> actual = populationBreakdownService.getAllByCountry();
        assertThat(actual).isNotNull();
    }
}
