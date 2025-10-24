package com.napier.devops.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Composite primary key class for CountryLanguage entity.
 * This class represents the combination of country code and language name
 * that uniquely identifies a country-language relationship.
 */
public class CountryLanguageId implements Serializable {

    /**
     * Country code component of the composite key
     * References the Code field in the country table
     * Examples: "USA", "GBR", "DEU"
     */
    protected String countryCode;

    /**
     * Language name component of the composite key
     * Name of the language spoken in the country
     * Examples: "English", "Spanish", "German"
     */
    protected String language;

    /**
     * Default constructor required by JPA/Hibernate.
     * Creates a new CountryLanguageId instance with null values.
     */
    public CountryLanguageId() {}

    /**
     * Constructor that initializes the composite key with provided values.
     *
     * @param countryCode the country code component of the key
     * @param language the language name component of the key
     */
    public CountryLanguageId(String countryCode, String language) {
        this.countryCode = countryCode;
        this.language = language;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryLanguageId that = (CountryLanguageId) o;
        return Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, language);
    }
}
