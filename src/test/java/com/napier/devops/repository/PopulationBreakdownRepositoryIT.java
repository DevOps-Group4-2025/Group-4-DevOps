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
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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

    // ------------------------------------------------------------
    // CONTINENT TESTS
    // ------------------------------------------------------------

    @Test
    void getAllByContinent_shouldReturnCorrectAsiaPopulationBreakdown() {
        List<PopulationBreakdownProjection> reports = populationBreakdownRepository.getAllByContinent();
        PopulationBreakdownProjection asia = findReportByName(reports, "Asia");

        assertPopulationBreakdown(
                asia,
                120_000_000L,
                8_000_000L,
                112_000_000L,
                6.67
        );
    }

    @Test
    void getAllByContinent_shouldIncludeTwoContinents() {
        List<PopulationBreakdownProjection> reports = populationBreakdownRepository.getAllByContinent();
        assertThat(reports).hasSize(2);
    }

    // ------------------------------------------------------------
    // REGION TESTS
    // ------------------------------------------------------------

    @Test
    void getAllByRegion_shouldReturnCorrectEasternAsiaBreakdown() {
        List<PopulationBreakdownProjection> reports = populationBreakdownRepository.getAllByRegion();
        PopulationBreakdownProjection easternAsia = findReportByName(reports, "Eastern Asia");

        assertAll("Eastern Asia population breakdown",
                () -> assertThat(easternAsia.getTotalPopulation()).isEqualTo(120_000_000L),
                () -> assertThat(easternAsia.getPopulationInCities()).isEqualTo(8_000_000L),
                () -> assertThat(easternAsia.getNotInCitiesPercentage()).isEqualTo(93.33)
        );
    }

    // ------------------------------------------------------------
    // COUNTRY TESTS
    // ------------------------------------------------------------

    @Test
    void getAllByCountry_shouldReturnCorrectJapanBreakdown() {
        List<PopulationBreakdownProjection> reports = populationBreakdownRepository.getAllByCountry();
        PopulationBreakdownProjection japan = findReportByName(reports, "Japan");

        assertPopulationBreakdown(
                japan,
                120_000_000L,
                8_000_000L,
                112_000_000L,
                6.67
        );
    }

    // ------------------------------------------------------------
    // HELPERS
    // ------------------------------------------------------------

    private void assertPopulationBreakdown(
            PopulationBreakdownProjection projection,
            long total,
            long inCities,
            long notInCities,
            double inCitiesPercent
    ) {
        assertAll("Population breakdown for " + projection.getName(),
                () -> assertThat(projection.getTotalPopulation()).isEqualTo(total),
                () -> assertThat(projection.getPopulationInCities()).isEqualTo(inCities),
                () -> assertThat(projection.getPopulationNotInCities()).isEqualTo(notInCities),
                () -> assertThat(projection.getInCitiesPercentage()).isEqualTo(inCitiesPercent)
        );
    }

    private PopulationBreakdownProjection findReportByName(List<PopulationBreakdownProjection> reports, String name) {
        return reports.stream()
                .filter(r -> Objects.equals(r.getName(), name))
                .findFirst()
                .orElseThrow(() -> new AssertionError("No report found for " + name));
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
