package com.napier.devops;

import com.napier.devops.controller.CapitalController;
import com.napier.devops.controller.PopulationController;
import com.napier.devops.model.CapitalCity;
import com.napier.devops.model.Country;
import com.napier.devops.model.PopulationBreakdown;
import com.napier.devops.controller.CityController;
import com.napier.devops.model.City;
import com.napier.devops.controller.LanguageController;
import com.napier.devops.model.LanguageStats;
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

    private boolean running = true;

    /**
     * Service for managing and retrieving country-related data.
     */
    @Autowired
    private CountryService countryService;

    /**
     * Service for managing and retrieving population breakdown data.
     */
    @Autowired
    private PopulationBreakdownService populationBreakdownService;

    /**
     * Service for managing and retrieving capital city data.
     */
    @Autowired
    private CapitalCityService capitalCityService;

    /**
     * Controller for managing and retrieving city-related data.
     */
    @Autowired
    private CityController cityController;

    /**
     * Controller for managing and retrieving population data.
     */
    @Autowired
    private PopulationController populationController;

    /**
     * Application parameters.
     */
    @Autowired
    private AppParameters appParameters;

    /**
     * Controller for managing and retrieving capital city data.
     */
    @Autowired
    private CapitalController capitalController;

    /**
     * Controller for managing and retrieving language data.
     */
    @Autowired
    private LanguageController languageController;


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
     *                 <p>Behavior:</p>
     *                 <ul>
     *                   <li>If the output directory "output" does not exist, it will attempt to create it.</li>
     *                   <li>Prints the current date and time at the beginning of the report.</li>
     *                   <li>Redirects {@link System#out} to a dual stream that prints to both console and file.</li>
     *                   <li>Restores {@link System#out} after execution is complete.</li>
     *                   <li>Catches and logs any exceptions during file writing without terminating the program.</li>
     *                 </ul>
     */
    public static void runUseCase(String fileName, Runnable action) {
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
                while (this.running) {
                    int selection = mainMenu(scanner);
                    handleMenuSelection(selection, scanner, this);
                }
            }
        } else {
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

            runUseCase("usecase3.log", () -> {
                System.out.println("\n=== USE CASE 3: All countries in a region (Western Europe) ===");
                displayAllCountriesInRegion(appParameters.getUseCase3Region());
            });

            runUseCase("usecase4.log", () -> {
                System.out.println("\n=== USE CASE 4: Top " + appParameters.getUseCase4Limit() + " populated countries in the world ===");
                displayTopCountriesInWorld(appParameters.getUseCase4Limit());
            });

            runUseCase("usecase5.log", () -> {
                System.out.println("\n=== USE CASE 5: Top " + appParameters.getUseCase5Limit() + " populated countries in a continent (Asia) ===");
                displayTopCountriesInContinent(appParameters.getUseCase5Continent(), appParameters.getUseCase5Limit());
            });

            runUseCase("usecase6.log", () -> {
                System.out.println("\n=== USE CASE 6: Top " + appParameters.getUseCase6Limit() + " populated countries in a region (Eastern Asia) ===");
                displayTopCountriesInRegion(appParameters.getUseCase6Region(), appParameters.getUseCase6Limit());
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
            runUseCase("usecase18.log", () -> {
                System.out.println("\nUSE CASE 18: All capital cities in a continent by population");
                displayCapitalCities(
                        capitalController.getCapitalCitiesInContinent(appParameters.getUseCase18Continent()).getBody()
                );
            });
            runUseCase("usecase19.log", () -> {
                System.out.println("\nUSE CASE 19: All capital cities in a region by population");
                displayAllCapitalCitiesRegion(appParameters.getUseCase19Region());
            });
            // USE CASE 20
            runUseCase("usecase20.log", () -> {
                System.out.println("\nUSE CASE 20: Top N capital cities in the world by population");
                displayTopCapitalCitiesWorld(appParameters.getUseCase20Limit());
            });
            // USE CASE 21
            runUseCase("usecase21.log", () -> {
                System.out.println("\nUSE CASE 21: Top N capital cities in a continent by population");
                displayTopCapitalCitiesContinent(
                        appParameters.getUseCase21Continent(),
                        appParameters.getUseCase21Limit()
                );
            });
            // USE CASE 22
            runUseCase("usecase22.log", () -> {
                System.out.println("\nUSE CASE 22: Top N capital cities in a region by population");
                displayTopCapitalCitiesRegion(
                        appParameters.getUseCase22Region(),
                        appParameters.getUseCase22Limit()
                );
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

            // === POPULATION REPORTS (26–31) ===
            runUseCase("usecase26.log", () -> {
                System.out.println("\nUSE CASE 26: The population of the world.");
                displayBasicPopulation("the world", populationController.getWorldPopulation());
            });
            runUseCase("usecase27.log", () -> {
                System.out.println("\nUSE CASE 27: The population of a continent.");
                displayBasicPopulation(appParameters.getUseCase27Continent(),populationController.getContinentPopulation(appParameters.getUseCase27Continent()));
            });
            runUseCase("usecase28.log", () -> {
                System.out.println("\nUSE CASE 28: The population of a region.");
                displayBasicPopulation(appParameters.getUseCase28Region(),populationController.getRegionPopulation(appParameters.getUseCase28Region()));
            });
            runUseCase("usecase29.log", () -> {
                System.out.println("\nUSE CASE 29: The population of a country.");
                displayBasicPopulation(appParameters.getUseCase29Country(),populationController.getCountryPopulation(appParameters.getUseCase29Country()));
            });
            runUseCase("usecase30.log", () -> {
                System.out.println("\nUSE CASE 30: The population of a district.");
                displayBasicPopulation(appParameters.getUseCase30District(),populationController.getDistrictPopulation(appParameters.getUseCase30District()));
            });
            runUseCase("usecase31.log", () -> {
                System.out.println("\nUSE CASE 31: The population of a city.");
                displayBasicPopulation(appParameters.getUseCase31City(),populationController.getCityPopulation(appParameters.getUseCase31City()));
            });

            // === LANGUAGE REPORTS (32) ===
            runUseCase("usecase32.log", () -> {
                System.out.println("\nUSE CASE 32: Languages report (Chinese, English, Hindi, Spanish, Arabic)");
                displayLanguageStatistics();
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
                System.out.println("2.  All countries in a continent (by population)");
                System.out.println("3.  All countries in a region (by population)");
                System.out.println("4.  Top N populated countries in the world");
                System.out.println("5.  Top N populated countries in a continent");
                System.out.println("6.  Top N populated countries in a region");
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
                System.out.println("\n--- AGGREGATED BREAKDOWNS ---");
                System.out.println("23. Population breakdowns by continent (all)");
                System.out.println("24. Population breakdowns by region (all)");
                System.out.println("25. Population breakdowns by country (all)");
                System.out.println("\n--- LANGUAGE REPORTS ---");
                System.out.println("32. Languages report (Chinese, English, Hindi, Spanish, Arabic)");
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
    private void handleMenuSelection(int selection, Scanner scanner, Group4Application app) {
        switch (selection) {
            case 1:
                runUseCase("interactive-usecase1.log", this::displayAllCountriesWorld);
                break;
            case 2:
                System.out.print("Enter continent name: ");
                String continent = scanner.nextLine();
                runUseCase("interactive-usecase2.log", () -> displayAllCountriesInContinent(continent));
                break;
            case 3:
                System.out.print("Enter region name: ");
                String region = scanner.nextLine();
                runUseCase("interactive-usecase3.log", () -> displayAllCountriesInRegion(region));
                break;
            case 4:
                System.out.print("Enter N: ");
                int n = Integer.parseInt(scanner.nextLine());
                runUseCase("interactive-usecase4.log", () -> displayTopCountriesInWorld(n));
                break;
            case 5:
                System.out.print("Enter continent name: ");
                String continent5 = scanner.nextLine();
                System.out.print("Enter N: ");
                int n5 = Integer.parseInt(scanner.nextLine());
                runUseCase("interactive-usecase5.log", () -> displayTopCountriesInContinent(continent5, n5));
                break;
            case 6:
                System.out.print("Enter region name: ");
                String region6 = scanner.nextLine();
                System.out.print("Enter N: ");
                int n6 = Integer.parseInt(scanner.nextLine());
                runUseCase("interactive-usecase6.log", () -> displayTopCountriesInRegion(region6, n6));
                break;
            case 7:
                runUseCase("interactive-usecase7.log", () -> displayCities(cityController.getAllCitiesInTheWorld()));
                break;
            case 8:
                System.out.print("Enter continent name: ");
                String continent8 = scanner.nextLine();
                runUseCase("interactive-usecase8.log", () -> displayCities(cityController.getAllCitiesInAContinent(continent8)));
                break;
            case 9:
                System.out.print("Enter region name: ");
                String region9 = scanner.nextLine();
                runUseCase("interactive-usecase9.log", () -> displayCities(cityController.getAllCitiesInARegion(region9)));
                break;
            case 10:
                System.out.print("Enter country name: ");
                String country10 = scanner.nextLine();
                runUseCase("interactive-usecase10.log", () -> displayCities(cityController.getAllCitiesInACountry(country10)));
                break;
            case 11:
                System.out.print("Enter district name: ");
                String district11 = scanner.nextLine();
                runUseCase("interactive-usecase11.log", () -> displayCities(cityController.getAllCitiesInADistrict(district11)));
                break;
            case 12:
                System.out.print("Enter limit (N): ");
                int limit12 = Integer.parseInt(scanner.nextLine());
                runUseCase("interactive-usecase12.log", () -> displayCities(cityController.getTopNCitiesInTheWorld(limit12)));
                break;
            case 13:
                System.out.print("Enter continent name: ");
                String continent13 = scanner.nextLine();
                System.out.print("Enter limit (N): ");
                int limit13 = Integer.parseInt(scanner.nextLine());
                runUseCase("interactive-usecase13.log", () -> displayCities(cityController.getTopNCitiesInAContinent(continent13, limit13)));
                break;
            case 14:
                System.out.print("Enter region name: ");
                String region14 = scanner.nextLine();
                System.out.print("Enter limit (N): ");
                int limit14 = Integer.parseInt(scanner.nextLine());
                runUseCase("interactive-usecase14.log", () -> displayCities(cityController.getTopNCitiesInARegion(region14, limit14)));
                break;
            case 15:
                System.out.print("Enter country name: ");
                String country15 = scanner.nextLine();
                System.out.print("Enter limit (N): ");
                int limit15 = Integer.parseInt(scanner.nextLine());
                runUseCase("interactive-usecase15.log", () -> displayCities(cityController.getTopNCitiesInACountry(country15, limit15)));
                break;
            case 16:
                System.out.print("Enter district name: ");
                String district16 = scanner.nextLine();
                System.out.print("Enter limit (N): ");
                int limit16 = Integer.parseInt(scanner.nextLine());
                runUseCase("interactive-usecase16.log", () -> displayCities(cityController.getTopNCitiesInADistrict(district16, limit16)));
                break;
            case 17:
                runUseCase("interactive-usecase17.log", this::displayAllCapitalCitiesWorld);
                break;
            case 18:
                System.out.print("Enter continent name: ");
                String continent18 = scanner.nextLine();
                runUseCase("interactive-usecase18.log", () -> displayAllCapitalCitiesContinent(continent18));
                break;
            case 19:
                System.out.print("Enter region name: ");
                String region19 = scanner.nextLine();
                runUseCase("interactive-usecase19.log", () -> displayAllCapitalCitiesRegion(region19));
                break;
            case 20:
                System.out.print("Enter limit (N): ");
                int limit20 = Integer.parseInt(scanner.nextLine());
                runUseCase("interactive-usecase20.log", () -> displayTopCapitalCitiesWorld(limit20));
                break;
            case 21:
                System.out.print("Enter continent name: ");
                String continent21 = scanner.nextLine();
                System.out.print("Enter limit (N): ");
                int limit21 = Integer.parseInt(scanner.nextLine());
                runUseCase("interactive-usecase21.log", () -> displayTopCapitalCitiesContinent(continent21, limit21));
                break;
            case 22:
                System.out.print("Enter region name: ");
                String region22 = scanner.nextLine();
                System.out.print("Enter limit (N): ");
                int limit22 = Integer.parseInt(scanner.nextLine());
                runUseCase("interactive-usecase22.log", () -> displayTopCapitalCitiesRegion(region22, limit22));
                break;
            case 23:
                runUseCase("interactive-usecase23.log", this::displayPopulationBreakdownsByContinentAll);
                break;
            case 24:
                runUseCase("interactive-usecase24.log", this::displayPopulationBreakdownsByRegionAll);
                break;
            case 25:
                runUseCase("interactive-usecase25.log", this::displayPopulationBreakdownsByCountryAll);
                break;
            case 32:
                runUseCase("interactive-usecase32.log", this::displayLanguageStatistics);
                break;
            case 100:
                System.out.println("Thank you for using the World Population Reporting System. Goodbye!");
                app.running = false; // Set running to false to exit the loop gracefully
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
                break;
        }
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
        if (!app.running) scanner.close(); // Close scanner if exiting
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

    /**
     * Use Case 2:
     * Displays all countries in a continent ordered by population (largest to smallest).
     * @param continent the continent to display the countries of
     */
    private void displayAllCountriesInContinent(String continent) {
        System.out.printf("\n=== ALL COUNTRIES IN %s (BY POPULATION) ===\n", continent.toUpperCase());
        List<Country> countries = countryService.getAllCountriesInContinent(continent);
        displayCountries(countries);
    }

    /**
     * Use Case 3:
     * Displays all countries in a region ordered by population (largest to smallest).
     * @param region the region to display the countries of
     */
    private void displayAllCountriesInRegion(String region) {
        System.out.printf("\n=== ALL COUNTRIES IN %s (BY POPULATION) ===\n", region.toUpperCase());
        List<Country> countries = countryService.getCountriesInRegionByPopulation(region);
        displayCountries(countries);
    }

    /**
     * Use Case 4:
     * Displays top N countries in the world ordered by population (largest to smallest).
     * @param n the number of countries to display
     */
    private void displayTopCountriesInWorld(int n) {
        System.out.printf("\n=== TOP %d COUNTRIES IN THE WORLD (BY POPULATION) ===\n", n);
        List<Country> countries = countryService.getTopCountriesInWorld(n);
        displayCountries(countries);
    }

    /**
     * Use Case 5:
     * Displays top N countries in a continent ordered by population (largest to smallest).
     * @param continent the continent to display the countries of
     * @param n the number of countries to display
     */
    private void displayTopCountriesInContinent(String continent, int n) {
        System.out.printf("\n=== TOP %d COUNTRIES IN %s (BY POPULATION) ===\n", n, continent.toUpperCase());
        List<Country> countries = countryService.getTopCountriesInContinent(continent, n);
        displayCountries(countries);
    }

    /**
     * Use Case 6:
     * Displays top N countries in a region ordered by population (largest to smallest).
     * @param region the region to display the countries of
     * @param n the number of countries to display
     */
    private void displayTopCountriesInRegion(String region, int n) {
        System.out.printf("\n=== TOP %d COUNTRIES IN %s (BY POPULATION) ===\n", n, region.toUpperCase());
        List<Country> countries = countryService.getTopCountriesInRegion(region, n);
        displayCountries(countries);
    }

    /**
     * USE CASE 17: List All Capital Cities in the World by Population
     */
    private void displayAllCapitalCitiesWorld() {
        System.out.println("\n=== ALL CAPITAL CITIES IN THE WORLD (BY POPULATION) ===");
        List<CapitalCity> capitalCities = capitalCityService.getAllCapitalCitiesByPopulation();
        displayCapitalCities(capitalCities);
    }

    /**
     * USE CASE 18: List All Capital Cities in a Continent by Population
     * @param continent the continent to display the capital cities of
     */
    private void displayAllCapitalCitiesContinent(String continent) {
        System.out.println("\n=== ALL CAPITAL CITIES IN " + continent.toUpperCase() + " (BY POPULATION) ===");
        List<CapitalCity> capitalCities = capitalCityService.getCapitalCitiesInContinentByPopulation(continent);
        displayCapitalCities(capitalCities);
    }

    /**
     * USE CASE 19: List All Capital Cities in a Region by Population
     * @param region the region to display the capital cities of
     */
    private void displayAllCapitalCitiesRegion(String region) {
        System.out.println("\n=== ALL CAPITAL CITIES IN " + region.toUpperCase() + " (BY POPULATION) ===");
        displayCapitalCities(capitalCityService.getCapitalCitiesInRegionByPopulation(region));
    }

    /**
     * USE CASE 20: Produce a Report on Top N Capital Cities in the World
     * @param limit the number of capital cities to display
     */
    private void displayTopCapitalCitiesWorld(int limit) {
        System.out.println("\n=== TOP " + limit + " CAPITAL CITIES IN THE WORLD (BY POPULATION) ===");
        displayCapitalCities(capitalCityService.getTopCapitalCitiesWorld(limit));
    }

    /**
     * USE CASE 21: Produce a Report on Top N Capital Cities in a Continent
     * @param continent the continent to display the capital cities of
     * @param limit the number of capital cities to display
     */
    private void displayTopCapitalCitiesContinent(String continent, int limit) {
        System.out.println("\n=== TOP " + limit + " CAPITAL CITIES IN " + continent.toUpperCase() + " (BY POPULATION) ===");
        displayCapitalCities(capitalCityService.getTopCapitalCitiesInContinent(continent, limit));
    }

    /**
     * USE CASE 22: Produce a Report on Top N Capital Cities in a Region
     * @param region the region to display the capital cities of
     * @param limit the number of capital cities to display
     */
    private void displayTopCapitalCitiesRegion(String region, int limit) {
        System.out.println("\n=== TOP " + limit + " CAPITAL CITIES IN " + region.toUpperCase() + " (BY POPULATION) ===");
        displayCapitalCities(capitalCityService.getTopCapitalCitiesInRegion(region, limit));
    }

    /**
     * Displays top capital cities by continent
     * @param scanner the scanner to use for user input
     */
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

    /**
     * Helper method to display a list of capital cities in a formatted table.
     * @param capitals a list of {@link CapitalCity} objects to display
     */
    void displayCapitalCities(List<CapitalCity> capitals) {
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
    void displayCountries(List<Country> countries) {
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
    void displayPopulationBreakdowns(List<PopulationBreakdown> breakdowns) {
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

    /**
     * Use Case 23: Display population breakdowns for all continents
     */
    private void displayPopulationBreakdownsByContinentAll() {
        System.out.println("\n=== POPULATION BREAKDOWNS BY CONTINENT ===");
        List<PopulationBreakdown> breakdowns = populationBreakdownService.getAllByContinent();
        displayPopulationBreakdowns(breakdowns);
    }

    /**
     * Use Case 24: Display population breakdowns for all regions
     */
    private void displayPopulationBreakdownsByRegionAll() {
        System.out.println("\n=== POPULATION BREAKDOWNS BY REGION ===");
        List<PopulationBreakdown> breakdowns = populationBreakdownService.getAllByRegion();
        displayPopulationBreakdowns(breakdowns);
    }

    /**
     * Use Case 25: Display population breakdowns for all countries
     */
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
    void displayCities(List<City> cities) {
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

    /**
     * Basic Population Number Display
     * @param option the option to display the population of
     * @param population the population to display
     */
    void displayBasicPopulation(String option, Long population) {
        System.out.println("\nThe population of " + option + " : " + population);
    }

    /**
     * USE CASE 32: Displays language statistics for Chinese, English, Hindi, Spanish, and Arabic.
     * Shows languages ordered by number of speakers (descending), including
     * total speakers and percentage of world population.
     */
    private void displayLanguageStatistics() {
        System.out.println("\n=== LANGUAGE STATISTICS REPORT ===");
        List<LanguageStats> languageStats = languageController.getLanguageStatisticsList();
        displayLanguages(languageStats);
    }

    /**
     * Helper method to display a list of language statistics in a formatted table.
     *
     * @param languageStats a list of {@link LanguageStats} objects to display
     */
    void displayLanguages(List<LanguageStats> languageStats) {
        if (languageStats == null || languageStats.isEmpty()) {
            System.out.println("No language statistics found.");
            return;
        }

        System.out.printf("%-20s %20s %25s%n", "Language", "Speakers", "Percentage of World");
        System.out.println("-".repeat(70));

        for (LanguageStats stats : languageStats) {
            System.out.printf("%-20s %,20d %24.2f%%%n",
                    stats.getLanguage() != null ? stats.getLanguage() : "Unknown",
                    stats.getSpeakers() != null ? stats.getSpeakers() : 0L,
                    stats.getPercentageOfWorldPopulation() != null ? stats.getPercentageOfWorldPopulation() : 0.0);
        }
    }
}
