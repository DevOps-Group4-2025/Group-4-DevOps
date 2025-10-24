package com.napier.devops.model;

import jakarta.persistence.*;

/**
 * Entity representing the relationship between countries and languages.
 * Maps to the 'countrylanguage' table and contains information about
 * languages spoken in each country, including official status and usage percentage.
 */
@Entity
@Table(name = "countrylanguage")
@IdClass(CountryLanguageId.class)
public class CountryLanguage {

    /**
     * Composite primary key - country code
     * References the Code field in the country table
     * Examples: "USA", "GBR", "DEU"
     */
    @Id
    @Column(name = "`CountryCode`")
    protected String countryCode;

    /**
     * Composite primary key - language name
     * Name of the language spoken in the country
     * Examples: "English", "Spanish", "German"
     */
    @Id
    @Column(name = "`Language`")
    protected String language;

    /**
     * Official status of the language in the country
     * "T" for official language, "F" for non-official language
     */
    @Column(name = "`IsOfficial`")
    protected String isOfficial;

    /**
     * Percentage of the country's population that speaks this language
     * Value between 0.0 and 100.0
     */
    @Column(name = "`Percentage`")
    protected Double percentage;

    // Getters and setters
    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getIsOfficial() { return isOfficial; }
    public void setIsOfficial(String isOfficial) { this.isOfficial = isOfficial; }

    public Double getPercentage() { return percentage; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }
}