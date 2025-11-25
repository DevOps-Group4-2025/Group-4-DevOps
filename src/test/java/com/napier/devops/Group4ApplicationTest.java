package com.napier.devops;

import com.napier.devops.controller.CityController;
import com.napier.devops.controller.CapitalController;
import com.napier.devops.controller.PopulationController;
import com.napier.devops.controller.LanguageController;
import com.napier.devops.model.City;
import com.napier.devops.model.Country;
import com.napier.devops.model.CapitalCity;
import com.napier.devops.model.PopulationBreakdown;
import com.napier.devops.model.LanguageStats;
import com.napier.devops.service.CountryService;
import com.napier.devops.service.CapitalCityService;
import com.napier.devops.service.PopulationBreakdownService;
import com.napier.devops.util.AppParameters;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Enhanced unit tests for the main application class, Group4Application.
 * These tests focus on comprehensive coverage including menu handling,
 * display methods, and edge cases.
 */
@ExtendWith(MockitoExtension.class)
public class Group4ApplicationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Mock
    private CountryService countryService;
    @Mock
    private PopulationBreakdownService populationBreakdownService;
    @Mock
    private CapitalCityService capitalCityService;
    @Mock
    private CityController cityController;
    @Mock
    private LanguageController languageController;
    @Mock

    @InjectMocks
    private Group4Application group4Application;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    // ============ EXISTING TESTS (kept for compatibility) ============

    @Test
    void testRunNonInteractiveMode() throws Exception {
        when(countryService.getAllCountriesWorld()).thenReturn(new ArrayList<>());
        when(cityController.getAllCitiesInTheWorld()).thenReturn(new ArrayList<>());
        when(capitalCityService.getAllCapitalCitiesByPopulation()).thenReturn(new ArrayList<>());
        when(populationBreakdownService.getAllByContinent()).thenReturn(new ArrayList<>());

        group4Application.run();

        verify(countryService, atLeastOnce()).getAllCountriesWorld();
        verify(cityController, atLeastOnce()).getAllCitiesInTheWorld();
        verify(capitalCityService, atLeastOnce()).getAllCapitalCitiesByPopulation();
        verify(populationBreakdownService, atLeastOnce()).getAllByContinent();
        assertTrue(outContent.toString().contains("All use cases executed successfully!"));
    }

    @Test
    void testRunInteractiveMode() throws Exception {
        provideInput("1\n\n100\n\n");
        when(countryService.getAllCountriesWorld()).thenReturn(Collections.singletonList(
                new Country("USA", "United States", "North America", "North America", 330000000L)
        ));

        group4Application.run("--interactive");

        String output = outContent.toString();
        assertTrue(output.contains("WELCOME TO WORLD POPULATION REPORTING SYSTEM"));
        assertTrue(output.contains("Interactive mode enabled"));
        assertTrue(output.contains("ALL COUNTRIES IN THE WORLD"));
        assertTrue(output.contains("United States"));
        assertTrue(output.contains("Thank you for using the World Population Reporting System. Goodbye!"));
    }

    // ============ NEW COMPREHENSIVE TESTS ============

    // Tests for handleMenuSelection - Case 2 (Countries in Continent)
    @Test
    void testHandleMenuSelection_Case2_CountriesInContinent() throws Exception {
        provideInput("2\nAsia\n\n100\n\n");

        Country country = new Country("CHN", "China", "Asia", "Eastern Asia", 1400000000L);
        when(countryService.getAllCountriesInContinent("Asia")).thenReturn(Collections.singletonList(country));

        group4Application.run("--interactive");

        verify(countryService).getAllCountriesInContinent("Asia");
        String output = outContent.toString();
        assertTrue(output.contains("China"));
    }

    @Test
    void testHandleMenuSelection_Case3_CountriesInRegion() throws Exception {
        provideInput("3\nWestern Europe\n\n100\n\n");

        Country country = new Country("FRA", "France", "Europe", "Western Europe", 67000000L);
        when(countryService.getCountriesInRegionByPopulation("Western Europe"))
                .thenReturn(Collections.singletonList(country));

        group4Application.run("--interactive");

        verify(countryService).getCountriesInRegionByPopulation("Western Europe");
        String output = outContent.toString();
        assertTrue(output.contains("France"));
    }


    // Case 4 – Top countries in the world
    @Test
    void testHandleMenuSelection_Case4_TopCountriesWorld() throws Exception {
        provideInput("4\n5\n\n100\n\n"); // menu 4, N=5, extra lines for scanner

        Country country = new Country("CHN", "China", "Asia", "Eastern Asia", 1400000000L);
        when(countryService.getTopCountriesInWorld(5))
                .thenReturn(Collections.singletonList(country));

        group4Application.run("--interactive");

        verify(countryService).getTopCountriesInWorld(5);
        String output = outContent.toString();
        assertTrue(output.contains("China"));
    }

    // Case 5 – Top countries in a continent
    @Test
    void testHandleMenuSelection_Case5_TopCountriesInContinent() throws Exception {
        provideInput("5\nAsia\n10\n\n100\n\n"); // menu 5, continent=Asia, N=10, extra lines

        Country country = new Country("CHN", "China", "Asia", "Eastern Asia", 1400000000L);
        when(countryService.getTopCountriesInContinent("Asia", 10))
                .thenReturn(Collections.singletonList(country));

        group4Application.run("--interactive");

        verify(countryService).getTopCountriesInContinent("Asia", 10);
        String output = outContent.toString();
        assertTrue(output.contains("China"));
    }

    // Case 6 – Top countries in a region
    @Test
    void testHandleMenuSelection_Case6_TopCountriesInRegion() throws Exception {
        provideInput("6\nEastern Asia\n5\n\n100\n\n"); // menu 6, region=Eastern Asia, N=5, extra lines

        Country country = new Country("CHN", "China", "Asia", "Eastern Asia", 1400000000L);
        when(countryService.getTopCountriesInRegion("Eastern Asia", 5))
                .thenReturn(Collections.singletonList(country));

        group4Application.run("--interactive");

        verify(countryService).getTopCountriesInRegion("Eastern Asia", 5);
        String output = outContent.toString();
        assertTrue(output.contains("China"));
    }




    // Tests for handleMenuSelection - Cases 7-16 (City Reports)
    @Test
    void testHandleMenuSelection_Case7_AllCitiesWorld() throws Exception {
        provideInput("7\n\n100\n\n");

        City city = new City();
        city.setName("Tokyo");
        city.setCountryCode("JPN");
        city.setDistrict("Tokyo");
        city.setPopulation(37400000);

        when(cityController.getAllCitiesInTheWorld()).thenReturn(Collections.singletonList(city));

        group4Application.run("--interactive");

        verify(cityController, atLeastOnce()).getAllCitiesInTheWorld();
        String output = outContent.toString();
        assertTrue(output.contains("Tokyo"));
    }

    @Test
    void testHandleMenuSelection_Case8_CitiesInContinent() throws Exception {
        provideInput("8\nAsia\n\n100\n\n");

        City city = new City();
        city.setName("Mumbai");
        city.setCountryCode("IND");
        city.setDistrict("Maharashtra");
        city.setPopulation(20000000);

        when(cityController.getAllCitiesInAContinent("Asia")).thenReturn(Collections.singletonList(city));

        group4Application.run("--interactive");

        verify(cityController).getAllCitiesInAContinent("Asia");
        assertTrue(outContent.toString().contains("Mumbai"));
    }

    @Test
    void testHandleMenuSelection_Case9_CitiesInRegion() throws Exception {
        provideInput("9\nEastern Asia\n\n100\n\n");

        City city = new City();
        city.setName("Seoul");
        city.setCountryCode("KOR");
        city.setDistrict("Seoul");
        city.setPopulation(9700000);

        when(cityController.getAllCitiesInARegion("Eastern Asia")).thenReturn(Collections.singletonList(city));

        group4Application.run("--interactive");

        verify(cityController).getAllCitiesInARegion("Eastern Asia");
        assertTrue(outContent.toString().contains("Seoul"));
    }

    @Test
    void testHandleMenuSelection_Case10_CitiesInCountry() throws Exception {
        provideInput("10\nJapan\n\n100\n\n");

        City city = new City();
        city.setName("Osaka");
        city.setCountryCode("JPN");
        city.setDistrict("Osaka");
        city.setPopulation(2700000);

        when(cityController.getAllCitiesInACountry("Japan")).thenReturn(Collections.singletonList(city));

        group4Application.run("--interactive");

        verify(cityController).getAllCitiesInACountry("Japan");
        assertTrue(outContent.toString().contains("Osaka"));
    }

    @Test
    void testHandleMenuSelection_Case11_CitiesInDistrict() throws Exception {
        provideInput("11\nShanghai\n\n100\n\n");

        City city = new City();
        city.setName("Shanghai");
        city.setCountryCode("CHN");
        city.setDistrict("Shanghai");
        city.setPopulation(24000000);

        when(cityController.getAllCitiesInADistrict("Shanghai")).thenReturn(Collections.singletonList(city));

        group4Application.run("--interactive");

        verify(cityController).getAllCitiesInADistrict("Shanghai");
        assertTrue(outContent.toString().contains("Shanghai"));
    }

    @Test
    void testHandleMenuSelection_Case12_TopNCitiesWorld() throws Exception {
        provideInput("12\n5\n\n100\n\n");

        City city = new City();
        city.setName("New York");
        city.setCountryCode("USA");
        city.setDistrict("New York");
        city.setPopulation(8400000);

        when(cityController.getTopNCitiesInTheWorld(5)).thenReturn(Collections.singletonList(city));

        group4Application.run("--interactive");

        verify(cityController).getTopNCitiesInTheWorld(5);
        assertTrue(outContent.toString().contains("New York"));
    }

    @Test
    void testHandleMenuSelection_Case13_TopNCitiesInContinent() throws Exception {
        provideInput("13\nEurope\n10\n\n100\n\n");

        City city = new City();
        city.setName("London");
        city.setCountryCode("GBR");
        city.setDistrict("England");
        city.setPopulation(8900000);

        when(cityController.getTopNCitiesInAContinent("Europe", 10)).thenReturn(Collections.singletonList(city));

        group4Application.run("--interactive");

        verify(cityController).getTopNCitiesInAContinent("Europe", 10);
        assertTrue(outContent.toString().contains("London"));
    }

    @Test
    void testHandleMenuSelection_Case14_TopNCitiesInRegion() throws Exception {
        provideInput("14\nWestern Europe\n5\n\n100\n\n");

        City city = new City();
        city.setName("Paris");
        city.setCountryCode("FRA");
        city.setDistrict("Île-de-France");
        city.setPopulation(2200000);

        when(cityController.getTopNCitiesInARegion("Western Europe", 5)).thenReturn(Collections.singletonList(city));

        group4Application.run("--interactive");

        verify(cityController).getTopNCitiesInARegion("Western Europe", 5);
        assertTrue(outContent.toString().contains("Paris"));
    }

    @Test
    void testHandleMenuSelection_Case15_TopNCitiesInCountry() throws Exception {
        provideInput("15\nGermany\n3\n\n100\n\n");

        City city = new City();
        city.setName("Berlin");
        city.setCountryCode("DEU");
        city.setDistrict("Berlin");
        city.setPopulation(3700000);

        when(cityController.getTopNCitiesInACountry("Germany", 3)).thenReturn(Collections.singletonList(city));

        group4Application.run("--interactive");

        verify(cityController).getTopNCitiesInACountry("Germany", 3);
        assertTrue(outContent.toString().contains("Berlin"));
    }

    @Test
    void testHandleMenuSelection_Case16_TopNCitiesInDistrict() throws Exception {
        provideInput("16\nCalifornia\n5\n\n100\n\n");

        City city = new City();
        city.setName("Los Angeles");
        city.setCountryCode("USA");
        city.setDistrict("California");
        city.setPopulation(4000000);

        when(cityController.getTopNCitiesInADistrict("California", 5)).thenReturn(Collections.singletonList(city));

        group4Application.run("--interactive");

        verify(cityController).getTopNCitiesInADistrict("California", 5);
        assertTrue(outContent.toString().contains("Los Angeles"));
    }

    // Tests for Capital City Reports (Cases 17-22)
    @Test
    void testHandleMenuSelection_Case17_AllCapitalCitiesWorld() throws Exception {
        provideInput("17\n\n100\n\n");

        CapitalCity capital = new CapitalCity("Tokyo", "Japan", 37400000);
        when(capitalCityService.getAllCapitalCitiesByPopulation()).thenReturn(Collections.singletonList(capital));

        group4Application.run("--interactive");

        verify(capitalCityService, atLeastOnce()).getAllCapitalCitiesByPopulation();
        assertTrue(outContent.toString().contains("Tokyo"));
    }

    @Test
    void testHandleMenuSelection_Case18_CapitalCitiesInContinent() throws Exception {
        provideInput("18\nAfrica\n\n100\n\n");

        CapitalCity capital = new CapitalCity("Cairo", "Egypt", 20000000);
        when(capitalCityService.getCapitalCitiesInContinentByPopulation("Africa"))
                .thenReturn(Collections.singletonList(capital));

        group4Application.run("--interactive");

        verify(capitalCityService).getCapitalCitiesInContinentByPopulation("Africa");
        assertTrue(outContent.toString().contains("Cairo"));
    }

    @Test
    void testHandleMenuSelection_Case19_CapitalCitiesInRegion() throws Exception {
        provideInput("19\nSouthern Europe\n\n100\n\n");

        CapitalCity capital = new CapitalCity("Rome", "Italy", 2800000);
        when(capitalCityService.getCapitalCitiesInRegionByPopulation("Southern Europe"))
                .thenReturn(Collections.singletonList(capital));

        group4Application.run("--interactive");

        verify(capitalCityService).getCapitalCitiesInRegionByPopulation("Southern Europe");
        assertTrue(outContent.toString().contains("Rome"));
    }

    @Test
    void testHandleMenuSelection_Case20_TopNCapitalCitiesWorld() throws Exception {
        provideInput("20\n10\n\n100\n\n");

        CapitalCity capital = new CapitalCity("Beijing", "China", 21000000);
        when(capitalCityService.getTopCapitalCitiesWorld(10)).thenReturn(Collections.singletonList(capital));

        group4Application.run("--interactive");

        verify(capitalCityService).getTopCapitalCitiesWorld(10);
        assertTrue(outContent.toString().contains("Beijing"));
    }

    @Test
    void testHandleMenuSelection_Case21_TopNCapitalCitiesInContinent() throws Exception {
        provideInput("21\nSouth America\n5\n\n100\n\n");

        CapitalCity capital = new CapitalCity("Buenos Aires", "Argentina", 15000000);
        when(capitalCityService.getTopCapitalCitiesInContinent("South America", 5))
                .thenReturn(Collections.singletonList(capital));

        group4Application.run("--interactive");

        verify(capitalCityService).getTopCapitalCitiesInContinent("South America", 5);
        assertTrue(outContent.toString().contains("Buenos Aires"));
    }

    @Test
    void testHandleMenuSelection_Case22_TopNCapitalCitiesInRegion() throws Exception {
        provideInput("22\nNorthern Europe\n3\n\n100\n\n");

        CapitalCity capital = new CapitalCity("Stockholm", "Sweden", 1500000);
        when(capitalCityService.getTopCapitalCitiesInRegion("Northern Europe", 3))
                .thenReturn(Collections.singletonList(capital));

        group4Application.run("--interactive");

        verify(capitalCityService).getTopCapitalCitiesInRegion("Northern Europe", 3);
        assertTrue(outContent.toString().contains("Stockholm"));
    }

    // Tests for Population Breakdown Reports (Cases 23-25)
    @Test
    void testHandleMenuSelection_Case23_PopulationBreakdownByContinent() throws Exception {
        provideInput("23\n\n100\n\n");

        PopulationBreakdown breakdown = new PopulationBreakdown(
                "Continent", "Asia", 4500000000L, 2000000000L, 2500000000L, 44.4, 55.6
        );
        when(populationBreakdownService.getAllByContinent()).thenReturn(Collections.singletonList(breakdown));

        group4Application.run("--interactive");

        verify(populationBreakdownService, atLeastOnce()).getAllByContinent();
        assertTrue(outContent.toString().contains("Asia"));
    }

    @Test
    void testHandleMenuSelection_Case24_PopulationBreakdownByRegion() throws Exception {
        provideInput("24\n\n100\n\n");

        PopulationBreakdown breakdown = new PopulationBreakdown(
                "Region", "Western Europe", 195000000L, 150000000L, 45000000L, 76.9, 23.1
        );
        when(populationBreakdownService.getAllByRegion()).thenReturn(Collections.singletonList(breakdown));

        group4Application.run("--interactive");

        verify(populationBreakdownService, atLeastOnce()).getAllByRegion();
        assertTrue(outContent.toString().contains("Western Europe"));
    }

    @Test
    void testHandleMenuSelection_Case25_PopulationBreakdownByCountry() throws Exception {
        provideInput("25\n\n100\n\n");

        PopulationBreakdown breakdown = new PopulationBreakdown(
                "Country", "United States", 330000000L, 275000000L, 55000000L, 83.3, 16.7
        );
        when(populationBreakdownService.getAllByCountry()).thenReturn(Collections.singletonList(breakdown));

        group4Application.run("--interactive");

        verify(populationBreakdownService, atLeastOnce()).getAllByCountry();
        assertTrue(outContent.toString().contains("United States"));
    }

    // Test for Language Statistics (Case 32)
    @Test
    void testHandleMenuSelection_Case32_LanguageStatistics() throws Exception {
        provideInput("32\n\n100\n\n");

        LanguageStats stats = new LanguageStats("English", 1500000000L, 19.0);
        when(languageController.getLanguageStatisticsList()).thenReturn(Collections.singletonList(stats));

        group4Application.run("--interactive");

        verify(languageController, atLeastOnce()).getLanguageStatisticsList();
        assertTrue(outContent.toString().contains("English"));
    }

    // Test for Invalid Menu Selection
    @Test
    void testHandleMenuSelection_InvalidSelection() throws Exception {
        provideInput("999\n\n100\n\n");

        group4Application.run("--interactive");

        String output = outContent.toString();
        assertTrue(output.contains("Invalid selection"));
    }

    // ============ DISPLAY METHOD TESTS ============

    @Test
    void testDisplayCitiesEmpty() {
        when(cityController.getAllCitiesInTheWorld()).thenReturn(Collections.emptyList());
        group4Application.displayCities(cityController.getAllCitiesInTheWorld());
        assertTrue(outContent.toString().contains("No city found."));
    }

    @Test
    void testDisplayCitiesWithData() {
        City city = new City();
        city.setName("Test City");
        city.setCountryCode("TS");
        city.setDistrict("Test District");
        city.setPopulation(100000);

        when(cityController.getAllCitiesInTheWorld()).thenReturn(Collections.singletonList(city));
        group4Application.displayCities(cityController.getAllCitiesInTheWorld());

        String output = outContent.toString();
        assertTrue(output.contains("Test City"));
        assertTrue(output.contains("Test District"));
        assertTrue(output.contains("100,000"));
    }

    @Test
    void testDisplayCitiesWithNullPopulation() {
        City city = new City();
        city.setName("Test City");
        city.setCountryCode("TS");
        city.setDistrict("Test District");
        city.setPopulation(null);

        group4Application.displayCities(Collections.singletonList(city));

        String output = outContent.toString();
        assertTrue(output.contains("Test City"));
        assertTrue(output.contains("0"));
    }

    @Test
    void testDisplayCountriesEmpty() {
        when(countryService.getAllCountriesWorld()).thenReturn(new ArrayList<>());
        group4Application.displayCountries(countryService.getAllCountriesWorld());
        assertTrue(outContent.toString().contains("No countries found."));
    }

    @Test
    void testDisplayCountriesWithData() {
        Country country = new Country("TST", "Testland", "Testinent", "Test Region", 1234567L);
        when(countryService.getAllCountriesWorld()).thenReturn(Collections.singletonList(country));
        group4Application.displayCountries(countryService.getAllCountriesWorld());

        String output = outContent.toString();
        assertTrue(output.contains("Testland"));
        assertTrue(output.contains("Testinent"));
        assertTrue(output.contains("1,234,567"));
    }

    @Test
    void testDisplayCountriesWithNullPopulation() {
        Country country = new Country("TST", "Testland", "Testinent", "Test Region", null);
        group4Application.displayCountries(Collections.singletonList(country));

        String output = outContent.toString();
        assertTrue(output.contains("Testland"));
        assertTrue(output.contains("0"));
    }

    @Test
    void testDisplayCapitalCitiesEmpty() {
        group4Application.displayCapitalCities(Collections.emptyList());
        assertTrue(outContent.toString().contains("No capital cities found"));
    }

    @Test
    void testDisplayCapitalCitiesNull() {
        group4Application.displayCapitalCities(null);
        assertTrue(outContent.toString().contains("No capital cities found"));
    }

    @Test
    void testDisplayCapitalCitiesWithData() {
        CapitalCity capital = new CapitalCity("London", "United Kingdom", 8900000);
        group4Application.displayCapitalCities(Collections.singletonList(capital));

        String output = outContent.toString();
        assertTrue(output.contains("London"));
        assertTrue(output.contains("United Kingdom"));
        assertTrue(output.contains("8,900,000"));
    }

    @Test
    void testDisplayCapitalCitiesWithNullPopulation() {
        CapitalCity capital = new CapitalCity("Test Capital", "Test Country", null);
        group4Application.displayCapitalCities(Collections.singletonList(capital));

        String output = outContent.toString();
        assertTrue(output.contains("Test Capital"));
        assertTrue(output.contains("0"));
    }

    @Test
    void testDisplayPopulationBreakdownsEmpty() {
        group4Application.displayPopulationBreakdowns(Collections.emptyList());
        assertTrue(outContent.toString().contains("No population breakdowns found"));
    }

    @Test
    void testDisplayPopulationBreakdownsNull() {
        group4Application.displayPopulationBreakdowns(null);
        assertTrue(outContent.toString().contains("No population breakdowns found"));
    }

    @Test
    void testDisplayPopulationBreakdownsWithData() {
        PopulationBreakdown breakdown = new PopulationBreakdown(
                "Continent", "Europe", 750000000L, 550000000L, 200000000L, 73.3, 26.7
        );
        group4Application.displayPopulationBreakdowns(Collections.singletonList(breakdown));

        String output = outContent.toString();
        assertTrue(output.contains("Europe"));
        assertTrue(output.contains("750,000,000"));
        assertTrue(output.contains("73.3"));
    }

    @Test
    void testDisplayPopulationBreakdownsWithNulls() {
        PopulationBreakdown breakdown = new PopulationBreakdown(
                "Country", "Test", null, null, null, null, null
        );
        group4Application.displayPopulationBreakdowns(Collections.singletonList(breakdown));

        String output = outContent.toString();
        assertTrue(output.contains("Test"));
        assertTrue(output.contains("0"));
    }

    @Test
    void testDisplayLanguagesEmpty() {
        group4Application.displayLanguages(Collections.emptyList());
        assertTrue(outContent.toString().contains("No language statistics found"));
    }

    @Test
    void testDisplayLanguagesNull() {
        group4Application.displayLanguages(null);
        assertTrue(outContent.toString().contains("No language statistics found"));
    }

    @Test
    void testDisplayLanguagesWithData() {
        LanguageStats stats = new LanguageStats("Spanish", 500000000L, 6.5);
        group4Application.displayLanguages(Collections.singletonList(stats));

        String output = outContent.toString();
        assertTrue(output.contains("Spanish"));
        assertTrue(output.contains("500,000,000"));
        assertTrue(output.contains("6.5"));
    }

    @Test
    void testDisplayLanguagesWithNulls() {
        LanguageStats stats = new LanguageStats(null, null, null);
        group4Application.displayLanguages(Collections.singletonList(stats));

        String output = outContent.toString();
        assertTrue(output.contains("Unknown"));
        assertTrue(output.contains("0"));
    }

    @Test
    void testDisplayBasicPopulation() {
        group4Application.displayBasicPopulation("Europe", 750000000L);
        String output = outContent.toString();
        assertTrue(output.contains("The population of Europe"));
        assertTrue(output.contains("750000000"));
    }

    @Test
    void testRunUseCaseWithException() {
        String testFilename = "test-exception.log";

        Group4Application.runUseCase(testFilename, () -> {
            throw new RuntimeException("Test exception");
        });

        // Should not throw exception, just log it
        assertTrue(true);
    }

    @Test
    void testMainMenuWithInvalidInput() throws Exception {
        provideInput("invalid\n1\n\n100\n\n");
        when(countryService.getAllCountriesWorld()).thenReturn(new ArrayList<>());

        group4Application.run("--interactive");

        String output = outContent.toString();
        assertTrue(output.contains("INVALID INPUT"));
    }
}