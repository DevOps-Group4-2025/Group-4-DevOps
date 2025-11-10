package com.napier.devops;

import com.napier.devops.model.CapitalCity;
import com.napier.devops.model.Country;
import com.napier.devops.model.PopulationBreakdown;
import com.napier.devops.controller.CityController;
import com.napier.devops.model.City;
import com.napier.devops.service.CapitalCityService;
import com.napier.devops.service.CountryService;
import com.napier.devops.service.PopulationBreakdownService;
import com.napier.devops.util.AppParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
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
     * Service for managing and retrieving country-related data.
     */
    @Autowired
    private CountryService countryService;

    @Autowired
    private PopulationBreakdownService populationBreakdownService;

    @Autowired
    private CapitalCityService capitalCityService;

    /**
     * Controller for managing and retrieving city-related data.
     */
    @Autowired
    private CityController cityController;

    @Autowired
    private AppParameters appParameters;




    /**
     * Executes a use case and writes its output to both the console and a log file.
     * <p>
     * This method ensures that the output directory exists, creates or overwrites
     * the log file specified by {@code fileName}, and prints both to the console
     * and to the file simultaneously. Additionally, a timestamp is added at the
     * top of each report to indicate when it was generated.
     * </p>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * runUseCase("usecase1.log", () -> displayAllCountriesWorld());
     * }</pre>
     *
     * @param fileName the name of the file to write the report to (e.g., "usecase1.log")
     * @param action   a {@link Runnable} representing the use case logic to execute
     *
     * <p>Behavior:</p>
     * <ul>
     *   <li>If the output directory "output" does not exist, it will attempt to create it.</li>
     *   <li>Prints the current date and time at the beginning of the report.</li>
     *   <li>Redirects {@link System#out} to a dual stream that prints to both console and file.</li>
     *   <li>Restores {@link System#out} after execution is complete.</li>
     *   <li>Catches and logs any exceptions during file writing without terminating the program.</li>
     * </ul>
     */
    private static void runUseCase(String fileName, Runnable action) {
        java.io.File dir = new java.io.File("output");
        if (!dir.exists() && !dir.mkdirs()) {
            System.err.println("Warning: could not create output directory " + dir.getAbsolutePath());
        }

        try (java.io.PrintStream fileOut = new java.io.PrintStream(new java.io.FileOutputStream("output/" + fileName))) {
            java.io.PrintStream console = System.out;

            java.io.PrintStream dual = new java.io.PrintStream(new java.io.OutputStream() {
                @Override
                public void write(int b) {
                    console.write(b);
                    fileOut.write(b);
                }
            });

            System.setOut(dual);

            // Add a timestamp at the start of the report
            java.time.LocalDateTime now = java.time.LocalDateTime.now();
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println("Report generated on: " + now.format(formatter) + "\n");

            // Execute the use case action
            action.run();

            // Restore original System.out
            System.setOut(console);

        } catch (Exception e) {
            System.err.println("Error writing output for " + fileName + ": " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }


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
        boolean interactiveMenu = Arrays.asList(args).contains("--interactive")
                || Boolean.parseBoolean(System.getenv().getOrDefault("INTERACTIVE_MENU", "false"));

        displayWelcomeMessage();

        if (interactiveMenu) {
            System.out.println("Interactive mode enabled. Launching main menu...");
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    int selection = mainMenu(scanner);
                    handleMenuSelection(selection, scanner);
                }
            }
        }else {
                System.out.println("Running in containerized mode - automatically executing all use cases...");

                // === COUNTRY REPORTS ===
                runUseCase("usecase1.log", () -> {
                    System.out.println("\n=== USE CASE 1: All countries in the world ===");
                    displayAllCountriesWorld();
                });

                runUseCase("usecase2.log", () -> {
                    System.out.println("\n=== USE CASE 2: All countries in a continent (Asia) ===");
                    displayAllCountriesInContinent(appParameters.getUseCase2Continent());
                });


                // === CITY REPORTS (7–16) ===
                runUseCase("usecase7.log", () -> {
                    System.out.println("\nUSE CASE 7: All cities in the world ordered by population");
                    displayCities(cityController.getAllCitiesInTheWorld());
                });

                runUseCase("usecase8.log", () -> {
                    System.out.println("\nUSE CASE 8: All cities in a given continent (" + appParameters.getUseCase8Continent() + ")");
                    displayCities(cityController.getAllCitiesInAContinent(appParameters.getUseCase8Continent()));
                });

                runUseCase("usecase9.log", () -> {
                    System.out.println("\nUSE CASE 9: All cities in region " + appParameters.getUseCase9Region());
                    displayCities(cityController.getAllCitiesInARegion(appParameters.getUseCase9Region()));
                });

                runUseCase("usecase10.log", () -> {
                    System.out.println("\nUSE CASE 10: All cities in country " + appParameters.getUseCase10Country());
                    displayCities(cityController.getAllCitiesInACountry(appParameters.getUseCase10Country()));
                });

                runUseCase("usecase11.log", () -> {
                    System.out.println("\nUSE CASE 11: All cities in district " + appParameters.getUseCase11District());
                    displayCities(cityController.getAllCitiesInADistrict(appParameters.getUseCase11District()));
                });

                runUseCase("usecase12.log", () -> {
                    System.out.println("\nUSE CASE 12: Top " + appParameters.getUseCase12Limit() + " most populated cities in the world");
                    displayCities(cityController.getTopNCitiesInTheWorld(appParameters.getUseCase12Limit()));
                });

                runUseCase("usecase13.log", () -> {
                    System.out.println("\nUSE CASE 13: Top " + appParameters.getUseCase13Limit() + " cities in continent " + appParameters.getUseCase13Continent());
                    displayCities(cityController.getTopNCitiesInAContinent(appParameters.getUseCase13Continent(), appParameters.getUseCase13Limit()));
                });

                runUseCase("usecase14.log", () -> {
                    System.out.println("\nUSE CASE 14: Top " + appParameters.getUseCase14Limit() + " cities in region " + appParameters.getUseCase14Region());
                    displayCities(cityController.getTopNCitiesInARegion(appParameters.getUseCase14Region(), appParameters.getUseCase14Limit()));
                });

                runUseCase("usecase15.log", () -> {
                    System.out.println("\nUSE CASE 15: Top " + appParameters.getUseCase15Limit() + " cities in country " + appParameters.getUseCase15Country());
                    displayCities(cityController.getTopNCitiesInACountry(appParameters.getUseCase15Country(), appParameters.getUseCase15Limit()));
                });

                runUseCase("usecase16.log", () -> {
                    System.out.println("\nUSE CASE 16: Top " + appParameters.getUseCase16Limit() + " cities in district " + appParameters.getUseCase16District());
                    displayCities(cityController.getTopNCitiesInADistrict(appParameters.getUseCase16District(), appParameters.getUseCase16Limit()));
                });

                // === CAPITAL CITIES REPORTS (17-22)===
                runUseCase("usecase17.log", () -> {
                    System.out.println("\nUSE CASE 17: All capital cities in the world by population");
                    displayAllCapitalCitiesWorld();
                });

                // === POPULATION BREAKDOWNS ===
                runUseCase("usecase23.log", () -> {
                    System.out.println("\n=== USE CASE 23: Population breakdowns by continent ===");
                    displayPopulationBreakdownsByContinentAll();
                });

                runUseCase("usecase24.log", () -> {
                    System.out.println("\n=== USE CASE 24: Population breakdowns by region ===");
                    displayPopulationBreakdownsByRegionAll();
                });

                runUseCase("usecase25.log", () -> {
                    System.out.println("\n=== USE CASE 25: Population breakdowns by country ===");
                    displayPopulationBreakdownsByCountryAll();
                });

                System.out.println("\nAll use cases executed successfully!");
            }


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
                System.out.println("\n=== CITY REPORTS ===");
                System.out.println("7.  Display all cities in the world ordered by population");
                System.out.println("8.  Display all cities in a given continent (e.g., Asia)");
                System.out.println("9.  Display all cities in a specific region (e.g., Eastern Asia)");
                System.out.println("10.  Display all cities in a specific country (e.g., Japan)");
                System.out.println("11.  Display all cities in a given district (e.g., Shanghai)");
                System.out.println("12.  Display the top N most populated cities in the world (e.g., 10)");
                System.out.println("13.  Display the top N most populated cities in a continent (e.g., Asia,10)");
                System.out.println("14.  Display the top N most populated cities in a region (e.g., Eastern Asia,10)");
                System.out.println("15.  Display the top N most populated cities in a country (e.g., Japan,10)");
                System.out.println("16.  Display the top N most populated cities in a district (e.g., Shanghai,10)");
                System.out.println("17.  All capital cities in the world by population");
                System.out.println("\n--- AGGREGATED BREAKDOWNS ---");
                System.out.println("23. Population breakdowns by continent (all)");
                System.out.println("24. Population breakdowns by region (all)");
                System.out.println("25. Population breakdowns by country (all)");
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
            case 2:
                displayAllCountriesInContinent("Asia"); // Or prompt user for continent
                break;
            case 7: {displayCities(cityController.getAllCitiesInTheWorld()); break;}
            case 8: {displayCities(cityController.getAllCitiesInAContinent("Asia")); break;}
            case 9: {displayCities(cityController.getAllCitiesInARegion("Eastern Asia")); break;}
            case 10: {displayCities(cityController.getAllCitiesInACountry("Japan")); break;}
            case 11: {displayCities(cityController.getAllCitiesInADistrict("Shanghai")); break;}
            case 12: {displayCities(cityController.getTopNCitiesInTheWorld(10)); break;}
            case 13: {displayCities(cityController.getTopNCitiesInAContinent("Asia", 10)); break;}
            case 14: {displayCities(cityController.getTopNCitiesInARegion("Eastern Asia", 10)); break;}
            case 15: {displayCities(cityController.getTopNCitiesInACountry("Japan", 10)); break;}
            case 16: {displayCities(cityController.getTopNCitiesInADistrict("Shanghai", 10)); break;}
            case 17: {displayAllCapitalCitiesWorld(); break;}
            case 23:
                displayPopulationBreakdownsByContinentAll();
                break;
            case 24:
                displayPopulationBreakdownsByRegionAll();
                break;
            case 25:
                displayPopulationBreakdownsByCountryAll();
                break;
            case 21:
                displayTopCapitalCitiesByContinent(scanner);
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
        List<Country> countries = countryService.getAllCountriesWorld();
        displayCountries(countries);
    }

    private void displayAllCountriesInContinent(String continent) {
        System.out.printf("\n=== ALL COUNTRIES IN %s (BY POPULATION) ===\n", continent.toUpperCase());
        List<Country> countries = countryService.getAllCountriesInContinent(continent);
        displayCountries(countries);
    }

    private void displayAllCapitalCitiesWorld() {
        System.out.println("\n=== ALL CAPITAL CITIES IN THE WORLD (BY POPULATION) ===");
        List<CapitalCity> capitalCities = capitalCityService.getAllCapitalCitiesByPopulation();
        displayCapitalCities(capitalCities);
    }

    private void displayTopCapitalCitiesByContinent(Scanner scanner) {
        try {
            System.out.print("Enter continent: ");
            String continent = scanner.nextLine();

            System.out.print("Enter the number of capital cities to display (N): ");
            String limitInput = scanner.nextLine();
            int limit = Integer.parseInt(limitInput);

            List<CapitalCity> capitals = capitalCityService.getTopCapitalCitiesInContinent(continent, limit);
            displayCapitalCities(capitals);
        } catch (NumberFormatException e) {
            System.out.println("INVALID INPUT - N must be a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void displayCapitalCities(List<CapitalCity> capitals) {
        if (capitals == null || capitals.isEmpty()) {
            System.out.println("No capital cities found for the selected criteria.");
            return;
        }

        System.out.printf("%-30s %-30s %15s%n", "Capital City", "Country", "Population");
        System.out.println("-".repeat(80));

        for (CapitalCity capitalCity : capitals) {
            System.out.printf("%-30s %-30s %,15d%n",
                    capitalCity.getCityName(),
                    capitalCity.getCountryName(),
                    capitalCity.getPopulation() != null ? capitalCity.getPopulation() : 0);
        }
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
    /**
     * Displays a list of population breakdowns in a formatted table.
     *
     * @param breakdowns list of {@link PopulationBreakdown} to display; may be null or empty
     */
    private void displayPopulationBreakdowns(List<PopulationBreakdown> breakdowns) {
        if (breakdowns == null || breakdowns.isEmpty()) {
            System.out.println("No population breakdowns found.");
            return;
        }

        System.out.printf("%-12s %-30s %15s %20s %20s\n", "Type", "Name", "Total Population", "Population in Cities", "Population not in Cities");
        System.out.println("-".repeat(105));

        for (PopulationBreakdown b : breakdowns) {
            System.out.printf("%-12s %-30s %,15d %,20d (%.2f%%) %,20d (%.2f%%)\n",
                    b.type(),
                    b.name(),
                    b.totalPopulation() != null ? b.totalPopulation() : 0L,
                    b.populationInCities() != null ? b.populationInCities() : 0L,
                    b.inCitiesPercentage() != null ? b.inCitiesPercentage() : 0.0,
                    b.populationNotInCities() != null ? b.populationNotInCities() : 0L,
                    b.notInCitiesPercentage() != null ? b.notInCitiesPercentage() : 0.0);
        }
    }

    // Use Case 23: Display population breakdowns for all continents
    private void displayPopulationBreakdownsByContinentAll() {
        System.out.println("\n=== POPULATION BREAKDOWNS BY CONTINENT ===");
        List<PopulationBreakdown> breakdowns = populationBreakdownService.getAllByContinent();
        displayPopulationBreakdowns(breakdowns);
    }

    // Use Case 24: Display population breakdowns for all regions
    private void displayPopulationBreakdownsByRegionAll() {
        System.out.println("\n=== POPULATION BREAKDOWNS BY REGION ===");
        List<PopulationBreakdown> breakdowns = populationBreakdownService.getAllByRegion();
        displayPopulationBreakdowns(breakdowns);
    }

    // Use Case 25: Display population breakdowns for all countries
    private void displayPopulationBreakdownsByCountryAll() {
        System.out.println("\n=== POPULATION BREAKDOWNS BY COUNTRY ===");
        List<PopulationBreakdown> breakdowns = populationBreakdownService.getAllByCountry();
        displayPopulationBreakdowns(breakdowns);
    }


    /**
     * Displays a formatted list of cities to the console.
     * Prints a table header and each city's name, country, district, and population.
     *
     * @param cities List of City objects to display.
     */
    private void displayCities(List<City> cities) {
        // If there are no cities in the list, show a message and exit early.
        if (cities.isEmpty()) {
            System.out.println("No city found.");
            return;
        }

        // Print table headers with formatted column spacing.
        // %-30s = left-align string in a 30-character field, etc.
        System.out.printf("%-30s %-15s %-20s %15s\n", "Name", "Country", "District", "Population");
        System.out.println("-".repeat(85));

        // Loop through each city in the list and print its details in the same column format.
        for (City city : cities) {
            System.out.printf("%-30s %-15s %-20s %,15d\n",
                    city.getName(),
                    city.getCountryCode(),
                    city.getDistrict(),
                    city.getPopulation() != null ? city.getPopulation() : 0L);
        }
    }

}
