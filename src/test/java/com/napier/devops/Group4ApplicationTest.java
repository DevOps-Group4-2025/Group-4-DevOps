package com.napier.devops;

import com.napier.devops.controller.CityController;
import com.napier.devops.controller.CapitalController;
import com.napier.devops.model.City;
import com.napier.devops.model.Country;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the main application class, Group4Application.
 * These tests focus on the application's command-line runner logic,
 * menu handling, and display methods.
 */
@ExtendWith(MockitoExtension.class)
public class Group4ApplicationTest {

    // Streams for capturing console output
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    // Mocks for service and controller layers
    @Mock
    private CountryService countryService;
    @Mock
    private PopulationBreakdownService populationBreakdownService;
    @Mock
    private CapitalCityService capitalCityService;
    @Mock
    private CityController cityController;
    @Mock
    private CapitalController capitalController;
    @Mock
    private AppParameters appParameters;

    // The class under test
    @InjectMocks
    private Group4Application group4Application;

    /**
     * Sets up output stream capture before each test.
     */
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Restores original System.out and System.in after each test.
     */
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    /**
     * Simulates user input for interactive tests.
     * @param data The string to be provided as user input.
     */
    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    /**
     * Tests the non-interactive (containerized) execution path of the run method.
     * Verifies that all use cases are called.
     */
    @Test
    void testRunNonInteractiveMode() throws Exception {
        // Arrange: Mock services to return empty lists to prevent NullPointerExceptions
        when(countryService.getAllCountriesWorld()).thenReturn(new ArrayList<>());
        when(cityController.getAllCitiesInTheWorld()).thenReturn(new ArrayList<>());
        when(capitalCityService.getAllCapitalCitiesByPopulation()).thenReturn(new ArrayList<>());
        when(populationBreakdownService.getAllByContinent()).thenReturn(new ArrayList<>());

        // Act: Run the application with no arguments
        group4Application.run();

        // Assert: Verify that key services were invoked, indicating use cases were run
        verify(countryService, atLeastOnce()).getAllCountriesWorld();
        verify(cityController, atLeastOnce()).getAllCitiesInTheWorld();
        verify(capitalCityService, atLeastOnce()).getAllCapitalCitiesByPopulation();
        verify(populationBreakdownService, atLeastOnce()).getAllByContinent();
        assertTrue(outContent.toString().contains("All use cases executed successfully!"));
    }

    /**
     * Tests the interactive menu flow, simulating a user selecting an option and then exiting.
     */
    @Test
    void testRunInteractiveMode() throws Exception {
        // Arrange: Simulate user typing "1" (for use case 1) and then "100" (to exit)
        provideInput("1\n\n100\n");
        when(countryService.getAllCountriesWorld()).thenReturn(Collections.singletonList(
                new Country("USA", "United States", "North America", "North America", 330000000L)
        ));

        // Act: Run the application, which should now exit the loop gracefully instead of crashing.
        group4Application.run("--interactive");

        // Assert: Verify the output contains the expected messages from the interactive session.
        String output = outContent.toString();
        assertTrue(output.contains("WELCOME TO WORLD POPULATION REPORTING SYSTEM"));
        assertTrue(output.contains("Interactive mode enabled"));
        assertTrue(output.contains("ALL COUNTRIES IN THE WORLD"));
        assertTrue(output.contains("United States"));
        assertTrue(output.contains("Thank you for using the World Population Reporting System. Goodbye!"));
    }

    /**
     * Tests the displayCities method with an empty list to ensure it handles no data gracefully.
     */
    @Test
    void testDisplayCitiesEmpty() {
        // Arrange (in this case, just an empty list)
        when(cityController.getAllCitiesInTheWorld()).thenReturn(Collections.emptyList());

        // Act: Call the display method with an empty list
        group4Application.displayCities(cityController.getAllCitiesInTheWorld());

        // Assert
        assertTrue(outContent.toString().contains("No city found."));
    }

    /**
     * Tests the displayCities method with a sample list of cities.
     */
    @Test
    void testDisplayCitiesWithData() {
        // Arrange
        City city = new City();
        city.setName("Test City");
        city.setCountryCode("TS");
        city.setDistrict("Test District");
        city.setPopulation(100000);
        when(cityController.getAllCitiesInTheWorld()).thenReturn(Collections.singletonList(city));

        // Act
        group4Application.displayCities(cityController.getAllCitiesInTheWorld());

        // Assert
        String output = outContent.toString();
        assertTrue(output.contains("Test City"));
        assertTrue(output.contains("Test District"));
        assertTrue(output.contains("100,000"));
    }

    /**
     * Tests the displayCountries method with an empty list.
     */
    @Test
    void testDisplayCountriesEmpty() {
        // Arrange
        when(countryService.getAllCountriesWorld()).thenReturn(new ArrayList<>());

        // Act
        group4Application.displayCountries(countryService.getAllCountriesWorld());

        // Assert
        assertTrue(outContent.toString().contains("No countries found."));
    }

    /**
     * Tests the displayCountries method with sample data.
     */
    @Test
    void testDisplayCountriesWithData() {
        // Arrange - Use the new constructor
        Country country = new Country("TST", "Testland", "Testinent", "Test Region", 1234567L); // This now works
        when(countryService.getAllCountriesWorld()).thenReturn(Collections.singletonList(country));

        // Act
        group4Application.displayCountries(countryService.getAllCountriesWorld());

        // Assert
        String output = outContent.toString();
        assertTrue(output.contains("Testland"));
        assertTrue(output.contains("Testinent"));
        assertTrue(output.contains("1,234,567"));
    }

    /**
     * Tests the runUseCase static method to ensure it creates an output file.
     */
    @Test
    void testRunUseCaseFileCreation() {
        // Arrange
        String testFilename = "test-output.log";
        File outputFile = new File("output/" + testFilename);
        if (outputFile.exists()) {
            outputFile.delete();
        }

        // Act
        Group4Application.runUseCase(testFilename, () -> System.out.println("Test content"));

        // Assert
        assertTrue(outputFile.exists(), "Output file should be created.");

        // Cleanup
        outputFile.delete();
        new File("output").delete();
    }
}