package com.napier.devops.model;


import jakarta.persistence.*;

/**
 * Entity representing a city in the world database.
 * Maps to the 'city' table and contains information about cities worldwide
 * including their location, population, and administrative details.
 */
@Entity
@Table(name = "city")
public class City {

    /**
     * Primary key - unique city identifier
     * Auto-generated sequential number for each city
     */
    @Id
    @Column(name = "`ID`")
    protected Long id;

    /**
     * Name of the city
     * Examples: "New York", "London", "Tokyo"
     */
    @Column(name = "`Name`")
    protected String name;

    /**
     * Country code this city belongs to
     * References the Code field in the country table
     * Examples: "USA", "GBR", "JPN"
     */
    @Column(name = "CountryCode")
    protected String countryCode;

    /**
     * District or state within the country
     * Administrative subdivision where the city is located
     * Examples: "New York", "England", "Tokyo"
     */
    @Column(name = "`District`")
    protected String district;

    /**
     * Population of the city
     * Represents the most recent population count
     */
    @Column(name = "`Population`")
    protected Integer population;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s - Population: %,d",
                name, countryCode, district, population != null ? population : 0);
    }
}
