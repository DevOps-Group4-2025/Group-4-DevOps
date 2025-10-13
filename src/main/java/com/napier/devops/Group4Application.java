package com.napier.devops;


import com.napier.devops.controller.CountryController;
import com.napier.devops.model.Country;
import com.napier.devops.service.PopulationBreakdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Group4Application implements CommandLineRunner {

    @Autowired
    private CountryController countryController;

    @Autowired
    private PopulationBreakdownService populationBreakdownService;

    public static void main(String[] args) {
        SpringApplication.run(Group4Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    displayWelcomeMessage();

    // For Docker/containerized environment, automatically run Use Case 1
    System.out.println("Running in containerized mode - automatically executing Use Case 1...");
    displayAllCountriesWorld();

    // Run Use Case: Population Breakdown by Continent
    System.out.println("\nRunning Use Case: Population Breakdown by Continent (example: Africa)...");
    displayPopulationBreakdownByContinent("Africa");

    // Run Use Case: Population Breakdown by Country
    System.out.println("\nRunning Use Case: Population Breakdown by Country (example: Germany)...");
    displayPopulationBreakdownByCountry("Germany");

    // Run Use Case: Population Breakdown by Region
    System.out.println("\nRunning Use Case: Population Breakdown by Region (example: Western Europe)...");
    displayPopulationBreakdownByRegion("Western Europe");

    System.out.println("\nUse Case 1, continent, country, and region breakdowns completed successfully!");
    System.out.println("Application will now exit.");
    }

    private void displayWelcomeMessage() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("    WELCOME TO WORLD POPULATION REPORTING SYSTEM");
        System.out.println("=".repeat(60));
        System.out.println("This system provides comprehensive population reports");
        System.out.println("for countries worldwide.");
        System.out.println("=".repeat(60));
    }

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

    private void handleMenuSelection(int selection, Scanner scanner) {
        switch (selection) {
            case 1:
                displayAllCountriesWorld();
                break;
            case 2:
                System.out.print("Enter continent name: ");
                String continent = scanner.nextLine();
                displayPopulationBreakdownByContinent(continent);
                break;
            case 3:
                System.out.print("Enter country name: ");
                String country = scanner.nextLine();
                displayPopulationBreakdownByCountry(country);
                break;
            case 4:
                System.out.print("Enter region name: ");
                String region = scanner.nextLine();
                displayPopulationBreakdownByRegion(region);
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
    // Use Case: Display Population Breakdown by Continent
    private void displayPopulationBreakdownByContinent(String continent) {
        com.napier.devops.model.PopulationBreakdown breakdown = populationBreakdownService.getByContinent(continent);
        if (breakdown == null) {
            System.out.println("No population breakdown found for continent: " + continent);
        } else {
            System.out.println("\n=== POPULATION BREAKDOWN FOR CONTINENT: " + continent + " ===");
            System.out.println(breakdown);
        }
    }

    // Use Case: Display Population Breakdown by Country
    private void displayPopulationBreakdownByCountry(String country) {
        com.napier.devops.model.PopulationBreakdown breakdown = populationBreakdownService.getByCountry(country);
        if (breakdown == null) {
            System.out.println("No population breakdown found for country: " + country);
        } else {
            System.out.println("\n=== POPULATION BREAKDOWN FOR COUNTRY: " + country + " ===");
            System.out.println(breakdown);
        }
    }

    // Use Case 1: Display All Countries in the World by Population
    private void displayAllCountriesWorld() {
        System.out.println("\n=== ALL COUNTRIES IN THE WORLD (BY POPULATION) ===");
        List<Country> countries = countryController.getAllCountriesWorld();
        displayCountries(countries);
    }

    // Use Case 4: Display Population Breakdown by Region
    private void displayPopulationBreakdownByRegion(String region) {
        com.napier.devops.model.PopulationBreakdown breakdown = populationBreakdownService.getByRegion(region);
        if (breakdown == null) {
            System.out.println("No population breakdown found for region: " + region);
        } else {
            System.out.println("\n=== POPULATION BREAKDOWN FOR REGION: " + region + " ===");
            System.out.println(breakdown);
        }
    }

    // Display Helper Method for Countries
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

    private void displayPopulationBreakdowns(List<com.napier.devops.model.PopulationBreakdown> breakdowns) {
        if (breakdowns == null || breakdowns.isEmpty()) {
            System.out.println("No population breakdowns found.");
            return;
        }

        System.out.printf("%-12s %-30s %15s %20s %20s\n", "Type", "Name", "Total Population", "Population in Cities", "Population not in Cities");
        System.out.println("-".repeat(105));

        for (com.napier.devops.model.PopulationBreakdown breakdown : breakdowns) {
            System.out.printf("%-12s %-30s %,15d %,20d (%.2f%%) %,20d (%.2f%%)\n",
                    breakdown.getType(),
                    breakdown.getName(),
                    breakdown.getTotalPopulation(),
                    breakdown.getPopulationInCities(),
                    breakdown.getInCitiesPercentage(),
                    breakdown.getPopulationNotInCities(),
                    breakdown.getNotInCitiesPercentage());
        }
    }
}