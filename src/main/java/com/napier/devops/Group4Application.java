package com.napier.devops;

import com.napier.devops.controller.CityController;
import com.napier.devops.controller.CountryController;
import com.napier.devops.model.City;
import com.napier.devops.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

/**
 * The main entry point for the World Population Reporting System.
 * <p>
 * This Spring Boot application retrieves and displays population data
 * for countries, continents, and regions around the world.
 * </p>
 *
 * @author Group 4
 * @version 1.0
 */
@SpringBootApplication
public class Group4Application implements CommandLineRunner {

    /**
     * Controller for managing and retrieving country-related data.
     */
    @Autowired
    private CountryController countryController;

    /**
     * Controller for managing and retrieving city-related data.
     */
    @Autowired
    private CityController cityController;

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Group4Application.class, args);
    }

    /**
     * Runs after the application context has been loaded.
     * Displays a welcome message and automatically executes Use Case 1.
     *
     * @param args command-line arguments
     * @throws Exception if an error occurs while running the program
     */
    @Override
    public void run(String... args) throws Exception {
        displayWelcomeMessage();

        // For Docker/containerized environment, automatically run Use Case 1
        System.out.println("Running in containerized mode - automatically executing Use Case 1...");
        //displayAllCountriesWorld();
        displayCityQueries();

        System.out.println("\nUse Case 1, continent, country, and region breakdowns completed successfully!");
        System.out.println("Application will now exit.");
    }

    /**
     * Displays a welcome message to the console.
     */
    private void displayWelcomeMessage() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("    WELCOME TO WORLD POPULATION REPORTING SYSTEM BY GROUP 4");
        System.out.println("=".repeat(60));
        System.out.println("This system provides comprehensive population reports");
        System.out.println("for countries worldwide.");
        System.out.println("=".repeat(60));
    }

    /**
     * Displays the main menu options to the user and captures their selection.
     * <p>
     * Currently used for future front-end implementations.
     * </p>
     *
     * @param scanner the Scanner used for reading user input
     * @return the selected menu option as an integer
     */
    private int mainMenu(Scanner scanner) {
        while (true) {
            try {
                System.out.println("\n### MAIN MENU ###");
                System.out.println("\n--- COUNTRY REPORTS ---");
                System.out.println("1.  All countries in the world (by population)");
                System.out.println("2.  Population breakdown by continent (example: Africa)");
                System.out.println("3.  Population breakdown by country (example: Germany)");
                System.out.println("4.  Population breakdown by region (example: Western Europe)");

                System.out.println("\n--- SYSTEM ---");
                System.out.println("100. Exit application");

                System.out.print("\nEnter your selection: ");
                String input = scanner.nextLine();
                int inputSelection = Integer.parseInt(input);

                return inputSelection;
            } catch (NumberFormatException e) {
                System.out.println("INVALID INPUT - Please enter a number");
            }
        }
    }

    /**
     * Handles the user’s menu selection.
     *
     * @param selection the user’s menu choice
     * @param scanner   the Scanner used for input
     */
    private void handleMenuSelection(int selection, Scanner scanner) {
        switch (selection) {
            case 1:
                displayAllCountriesWorld();
                break;
            case 100:
                System.out.println("Thank you for using the World Population Reporting System. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
                break;
        }
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Use Case 1:
     * Displays all countries in the world ordered by population (largest to smallest).
     */
    private void displayAllCountriesWorld() {
        System.out.println("\n=== ALL COUNTRIES IN THE WORLD (BY POPULATION) ===");
        List<Country> countries = countryController.getAllCountriesWorld();
        displayCountries(countries);
    }

    /**
     * Helper method to display a list of countries in a formatted table.
     *
     * @param countries a list of {@link Country} objects to display
     */
    private void displayCountries(List<Country> countries) {
        if (countries.isEmpty()) {
            System.out.println("No countries found.");
            return;
        }

        System.out.printf("%-4s %-40s %-15s %-20s %15s\n", "Code", "Name", "Continent", "Region", "Population");
        System.out.println("-".repeat(95));

        for (Country country : countries) {
            System.out.printf("%-4s %-40s %-15s %-20s %,15d\n",
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation() != null ? country.getPopulation() : 0L);
        }
    }

    private void displayCities(List<City> cities) {
        if (cities.isEmpty()) {
            System.out.println("No city found.");
            return;
        }

        System.out.printf("%-30s %-15s %-20s %15s\n", "Name", "Country", "District", "Population");
        System.out.println("-".repeat(85));

        for (City city : cities) {
            System.out.printf("%-30s %-15s %-20s %,15d\n",
                    city.getName(),
                    city.getCountryCode(),
                    city.getDistrict(),
                    city.getPopulation() != null ? city.getPopulation() : 0L);
        }
    }

    private void displayCityQueries() {
        displayCities(cityController.getAllCitiesInTheWorld()); //Req 7
        displayCities(cityController.getAllCitiesInAContinent()); //Req 8
        displayCities(cityController.getAllCitiesInARegion()); //Req 9
        displayCities(cityController.getAllCitiesInACountry());  //Req 10
        displayCities(cityController.getAllCitiesInADistrict()); //Req 11
        displayCities(cityController.getTopNCitiesInTheWorld()); //Req 12
        displayCities(cityController.getTopNCitiesInAContinent()); //Req 13
        displayCities(cityController.getTopNCitiesInARegion()); //Req 14
        displayCities(cityController.getTopNCitiesInACountry()); //Req 15
        displayCities(cityController.getTopNCitiesInADistrict()); //Req 16
    }
}
