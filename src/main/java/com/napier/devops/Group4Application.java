package com.napier.devops;

import com.napier.devops.controller.CountryController;
import com.napier.devops.dto.LabeledPopulationRow;
import com.napier.devops.model.Country;
import com.napier.devops.service.PopulationTotalService;
import com.napier.devops.util.UseCaseWriter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SpringBootApplication
public class Group4Application implements CommandLineRunner {

    private final CountryController countryController;
    private final PopulationTotalService totals;

    public Group4Application(CountryController countryController,
                             PopulationTotalService totals) {
        this.countryController = countryController;
        this.totals = totals;
    }

    // ====== tweak these samples as you like ======
    private static final String SAMPLE_CONTINENT = "Africa";
    private static final String SAMPLE_REGION    = "Western Europe";
    private static final String SAMPLE_COUNTRY   = "Germany";
    private static final String SAMPLE_DISTRICT  = "Noord-Holland";
    private static final String SAMPLE_CITY      = "Amsterdam";
    // ============================================

    public static void main(String[] args) {
        applyLocalDatasourceDefaults();
        SpringApplication.run(Group4Application.class, args);
    }

    private static void applyLocalDatasourceDefaults() {
        setIfMissing("spring.datasource.url",
                "jdbc:mysql://127.0.0.1:3307/world?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
        setIfMissing("spring.datasource.username", "root");
        setIfMissing("spring.datasource.password", "root");
        setIfMissing("spring.jpa.hibernate.ddl-auto", "none");

        // If you’re running a web context, you can avoid 8080 clashes by also doing:
        // setIfMissing("server.port", "8081");

        System.out.println("[DB] Effective URL: " + System.getProperty("spring.datasource.url"));
    }

    private static void setIfMissing(String key, String value) {
        String sys = System.getProperty(key);
        String env = System.getenv(key.toUpperCase().replace('.', '_'));
        if (sys == null && env == null) System.setProperty(key, value);
    }

    @Override
    public void run(String... args) {
        displayWelcomeMessage();

        // 1) One-line totals (as before)
        printPopulationSummaries();

        // 2) Print ALL ranked tables to console AND write usecase27..31.log
        printAllRankedTables();

        // 3) Your existing UC1
        System.out.println("Running in containerized mode - automatically executing Use Case 1...");
        displayAllCountriesWorld();

        System.out.println("\nUse Case 1, continent, country, and region breakdowns completed successfully!");
        System.out.println("Application will now exit.");
    }

    private void displayWelcomeMessage() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("   WELCOME TO WORLD POPULATION REPORTING SYSTEM BY GROUP 4");
        System.out.println("   Active contributor (current run): Gp22-code");
        System.out.println("=".repeat(60));
        System.out.println("This system provides comprehensive population reports for");
        System.out.println("the world, continents, regions, countries, districts, and cities.");
        System.out.println("=".repeat(60));
    }

    // ---------- direct service calls with per-call protection ----------
    private void printPopulationSummaries() {
        System.out.println("\n--- POPULATION TOTALS (AUTO) ---");

        safePrint("World total", () -> totals.getWorldTotal());
        safePrint("Continent total (" + SAMPLE_CONTINENT + ")", () -> totals.getContinentTotal(SAMPLE_CONTINENT));
        safePrint("Region total (" + SAMPLE_REGION + ")", () -> totals.getRegionTotal(SAMPLE_REGION));
        safePrint("Country total (" + SAMPLE_COUNTRY + ")", () -> totals.getCountryTotal(SAMPLE_COUNTRY));
        safePrint("District total (" + SAMPLE_DISTRICT + ")", () -> totals.getDistrictTotal(SAMPLE_DISTRICT));
        safePrint("City total (" + SAMPLE_CITY + ")", () -> totals.getCityTotal(SAMPLE_CITY));

        System.out.println("-".repeat(40) + "\n");
    }

    private void safePrint(String label, Supplier<Long> supplier) {
        try {
            Long v = supplier.get();
            long value = (v == null ? 0L : v);
            System.out.printf("%s: %,d%n", label, value);
        } catch (DataAccessException dae) {
            System.out.printf("%s: DB error (%s)%n", label, rootMsg(dae));
        } catch (Exception e) {
            System.out.printf("%s: Error (%s)%n", label, rootMsg(e));
        }
    }

    private static String rootMsg(Throwable t) {
        Throwable r = t;
        while (r.getCause() != null) r = r.getCause();
        return r.getMessage();
    }
    // -------------------------------------------------------------------

    /* =================== NEW HELPERS for ranked tables =================== */

    /** Call once: prints all ranked tables and writes UC26–UC31 logs. */
    private void printAllRankedTables() {
        // UC26 (world total) – also gets a tiny single-line log for completeness
        long world = totals.getWorldTotal();
        String worldLine = "World total: " + human(world) + System.lineSeparator();
        UseCaseWriter.writeLog("usecase26.log", "USE CASE 26: World population total", worldLine);
        System.out.print(worldLine);

        // UC27..31 – each prints to console and writes its own log
        printAndWrite(
                "usecase27.log",
                "USE CASE 27: Population by continent",
                "CONTINENT TOTALS (DESC)",
                "Continent",
                () -> totals.getContinentTotalsRanked(),
                Integer.MAX_VALUE
        );
        printAndWrite(
                "usecase28.log",
                "USE CASE 28: Population by region",
                "REGION TOTALS (DESC)",
                "Region",
                () -> totals.getRegionTotalsRanked(),
                Integer.MAX_VALUE
        );
        printAndWrite(
                "usecase29.log",
                "USE CASE 29: Population by country",
                "COUNTRY TOTALS (DESC) — TOP 50",
                "Country",
                () -> totals.getCountryTotalsRanked(),
                50
        );
        printAndWrite(
                "usecase30.log",
                "USE CASE 30: Population by district",
                "DISTRICT TOTALS (DESC) — TOP 50",
                "District",
                () -> totals.getDistrictTotalsRanked(),
                50
        );
        printAndWrite(
                "usecase31.log",
                "USE CASE 31: Population by city",
                "CITY TOTALS (DESC) — TOP 50",
                "City",
                () -> totals.getCityTotalsRanked(),
                50
        );
    }

    /**
     * Builds a ranked table from the supplied rows, prints it to the console,
     * and writes it to /output/<fileName> via UseCaseWriter.
     */
    private void printAndWrite(
            String fileName,
            String logTitle,
            String tableTitle,
            String labelHeader,
            Supplier<List<LabeledPopulationRow>> supplier,
            int limit
    ) {
        try {
            List<LabeledPopulationRow> rows = supplier.get();
            if (rows == null || rows.isEmpty()) {
                String msg = "=== " + tableTitle + " ===\n(no rows)\n\n";
                System.out.print(msg);
                UseCaseWriter.writeLog(fileName, logTitle, msg);
                return;
            }
            String body = table(tableTitle, labelHeader, rows, limit);
            System.out.print(body);
            UseCaseWriter.writeLog(fileName, logTitle, body);
        } catch (Exception e) {
            String err = "Table '" + tableTitle + "': Error (" + rootMsg(e) + ")\n\n";
            System.out.print(err);
            UseCaseWriter.writeLog(fileName, logTitle, err);
        }
    }

    /* =================== existing table builder (unchanged) =================== */

    private static String human(long n) { return String.format("%,d", n); }

    /** Builds a simple text table identical in style to your console screenshot. */
    private String table(String title, String labelHeader, List<LabeledPopulationRow> rows, int limit) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(title).append(" ===").append(System.lineSeparator());
        sb.append(String.format("%-20s %15s%n", labelHeader, "Population"));
        sb.append("-".repeat(37)).append(System.lineSeparator());
        for (LabeledPopulationRow r : rows.stream().limit(limit).collect(Collectors.toList())) {
            String label = r.getLabel() == null ? "(null)" : r.getLabel();
            long pop = r.getPopulation() == null ? 0L : r.getPopulation();
            sb.append(String.format("%-20s %,15d%n", label, pop));
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    // === UC1 helpers (unchanged) ===
    private void displayAllCountriesWorld() {
        System.out.println("\n=== ALL COUNTRIES IN THE WORLD (BY POPULATION) ===");
        List<Country> countries = countryController.getAllCountriesWorld();
        displayCountries(countries);
    }

    private void displayCountries(List<Country> countries) {
        if (countries.isEmpty()) {
            System.out.println("No countries found.");
            return;
        }
        System.out.printf("%-4s %-40s %-15s %-20s %15s%n",
                "Code", "Name", "Continent", "Region", "Population");
        System.out.println("-".repeat(95));

        for (Country country : countries) {
            System.out.printf("%-4s %-40s %-15s %-20s %,15d%n",
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation() != null ? country.getPopulation() : 0L);
        }
    }

    // Optional menu retained (unchanged)
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
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("INVALID INPUT - Please enter a number");
            }
        }
    }

    private void handleMenuSelection(int selection, Scanner scanner) {
        switch (selection) {
            case 1 -> displayAllCountriesWorld();
            case 100 -> {
                System.out.println("Thank you for using the World Population Reporting System. Goodbye!");
                System.exit(0);
            }
            default -> System.out.println("Invalid selection. Please try again.");
        }
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
