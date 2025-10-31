package com.napier.devops.service;

import com.napier.devops.TestApplication;
import com.napier.devops.model.CapitalCity;
import com.napier.devops.model.City;
import com.napier.devops.model.Country;
import com.napier.devops.repository.CityRepository;
import com.napier.devops.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("test")
class CapitalCityServiceTest {

    @Autowired
    private CapitalCityService capitalCityService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    void setUp() {
        countryRepository.deleteAll();
        cityRepository.deleteAll();

        City tokyo = buildCity(1L, "Tokyo", "JPN", "Tokyo", 9000000);
        City seoul = buildCity(2L, "Seoul", "KOR", "Seoul", 9800000);
        City bangkok = buildCity(3L, "Bangkok", "THA", "Bangkok", 7800000);
        City madrid = buildCity(4L, "Madrid", "ESP", "Madrid", 3200000);

        cityRepository.saveAll(List.of(tokyo, seoul, bangkok, madrid));

        countryRepository.saveAll(List.of(
                buildCountry("JPN", "Japan", "Asia", "Eastern Asia", tokyo.getId()),
                buildCountry("KOR", "South Korea", "Asia", "Eastern Asia", seoul.getId()),
                buildCountry("THA", "Thailand", "Asia", "Southeast Asia", bangkok.getId()),
                buildCountry("ESP", "Spain", "Europe", "Southern Europe", madrid.getId())
        ));
    }

    @Test
    void getTopCapitalCitiesInContinent_returnsOrderedSubset() {
        List<CapitalCity> results = capitalCityService.getTopCapitalCitiesInContinent("Asia", 2);

        assertThat(results)
                .hasSize(2)
                .extracting(CapitalCity::getCityName)
                .containsExactly("Seoul", "Tokyo");

        assertThat(results)
                .extracting(CapitalCity::getCountryName)
                .containsExactly("South Korea", "Japan");
    }

    @Test
    void getTopCapitalCitiesInContinent_rejectsInvalidLimit() {
        assertThatThrownBy(() -> capitalCityService.getTopCapitalCitiesInContinent("Asia", 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("limit");
    }

    @Test
    void getTopCapitalCitiesInContinent_rejectsBlankContinent() {
        assertThatThrownBy(() -> capitalCityService.getTopCapitalCitiesInContinent("  ", 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("continent");
    }

    private City buildCity(Long id, String name, String countryCode, String district, int population) {
        City city = new City();
        city.setId(id);
        city.setName(name);
        city.setCountryCode(countryCode);
        city.setDistrict(district);
        city.setPopulation(population);
        return city;
    }

    private Country buildCountry(String code, String name, String continent, String region, Long capitalId) {
        Country country = new Country();
        country.setCode(code);
        country.setName(name);
        country.setContinent(continent);
        country.setRegion(region);
        country.setPopulation(1_000_000L);
        country.setCapital(capitalId);
        country.setCode2(code.substring(0, 2));
        return country;
    }
}


