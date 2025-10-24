package com.napier.devops.model;


/**
 * Data Transfer Object (DTO) representing capital city information.
 * This class is used for reporting purposes and contains aggregated data
 * about capital cities including their names, countries, and populations.
 * Note: This is not a JPA entity but a DTO for query results.
 */
public class CapitalCity {

    /**
     * Name of the capital city
     * Examples: "Washington", "London", "Berlin"
     */
    protected String cityName;

    /**
     * Name of the country where this city is the capital
     * Examples: "United States", "United Kingdom", "Germany"
     */
    protected String countryName;

    /**
     * Population of the capital city
     * Represents the most recent population count
     */
    protected Integer population;

    /**
     * Default constructor required by frameworks.
     * Creates a new CapitalCity instance with null values.
     */
    public CapitalCity() {
        // Default constructor required by Hibernate
    }

    /**
     * Constructor that initializes all fields with provided values.
     *
     * @param cityName the name of the capital city
     * @param countryName the name of the country
     * @param population the population of the capital city
     */
    public CapitalCity(String cityName, String countryName, Integer population) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.population = population;
    }


    // Getters och setters
    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }

    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }

    public Integer getPopulation() { return population; }
    public void setPopulation(Integer population) { this.population = population; }
}
