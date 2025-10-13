package com.napier.devops;


import com.napier.devops.controller.CountryController;
import com.napier.devops.model.Country;
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

    public static void main(String[] args) {
        SpringApplication.run(Group4Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        displayWelcomeMessage();

        // For Docker/containerized environment, automatically run Use Case 1
        System.out.println("Running in containerized mode - automatically executing Use Case 1...");
        displayAllCountriesWorld();

        System.out.println("\nUse Case 1 completed successfully!");
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

    // Use Case 1: Display All Countries in the World by Population
    private void displayAllCountriesWorld() {
        System.out.println("\n=== ALL COUNTRIES IN THE WORLD (BY POPULATION) ===");
        List<Country> countries = countryController.getAllCountriesWorld();
        displayCountries(countries);
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
}