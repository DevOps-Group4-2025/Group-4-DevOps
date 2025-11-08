package com.napier.devops.repository;

import com.napier.devops.TestApplication;
import com.napier.devops.model.City;
import com.napier.devops.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("test")
class PopulationBreakdownRepositoryIT {

    @Autowired
    private PopulationBreakdownRepository populationBreakdownRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @BeforeEach
    void setUp() {
        countryRepository.deleteAll();
        cityRepository.deleteAll();

        // Europe: Spain (Southern Europe)
        Country spain = buildCountry("ESP", "Spain", "Europe", "Southern Europe", 40_000_000L);
        City madrid = buildCity("Madrid", "ESP", 3_000_000);

        // Asia: Japan (Eastern Asia)
        Country japan = buildCountry("JPN", "Japan", "Asia", "Eastern Asia", 120_000_000L);
        City tokyo = buildCity("Tokyo", "JPN", 8_000_000);

        countryRepository.saveAll(List.of(spain, japan));
        cityRepository.saveAll(List.of(madrid, tokyo));
    }

    @Test
    void getAllByContinent() {
        List<PopulationBreakdownProjection> reports = populationBreakdownRepository.getAllByContinent();

        assertThat(reports).hasSize(2);

        PopulationBreakdownProjection asiaReport = reports.stream().filter(r -> r.getName().equals("Asia")).findFirst().orElseThrow();
        assertThat(asiaReport.getTotalPopulation()).isEqualTo(120_000_000L);
        assertThat(asiaReport.getPopulationInCities()).isEqualTo(8_000_000L);
        assertThat(asiaReport.getPopulationNotInCities()).isEqualTo(112_000_000L);
        assertThat(asiaReport.getInCitiesPercentage()).isEqualTo(6.67);
    }

    @Test
    void getAllByRegion() {
        List<PopulationBreakdownProjection> reports = populationBreakdownRepository.getAllByRegion();

        PopulationBreakdownProjection easternAsiaReport = reports.stream().filter(r -> r.getName().equals("Eastern Asia")).findFirst().orElseThrow();
        assertThat(easternAsiaReport.getTotalPopulation()).isEqualTo(120_000_000L);
        assertThat(easternAsiaReport.getPopulationInCities()).isEqualTo(8_000_000L);
        assertThat(easternAsiaReport.getNotInCitiesPercentage()).isEqualTo(93.33);
    }

    @Test
    void getAllByCountry() {
        List<PopulationBreakdownProjection> reports = populationBreakdownRepository.getAllByCountry();

        PopulationBreakdownProjection japanReport = reports.stream().filter(r -> r.getName().equals("Japan")).findFirst().orElseThrow();
        assertThat(japanReport.getTotalPopulation()).isEqualTo(120_000_000L);
        assertThat(japanReport.getPopulationInCities()).isEqualTo(8_000_000L);
        assertThat(japanReport.getPopulationNotInCities()).isEqualTo(112_000_000L);
    }

    private Country buildCountry(String code, String name, String continent, String region, Long population) {
        Country country = new Country();
        country.setCode(code);
        country.setName(name);
        country.setContinent(continent);
        country.setRegion(region);
        country.setPopulation(population);
        return country;
    }

    private City buildCity(String name, String countryCode, int population) {
        City city = new City();
        city.setName(name);
        city.setCountryCode(countryCode);
        city.setPopulation(population);
        return city;
    }
}