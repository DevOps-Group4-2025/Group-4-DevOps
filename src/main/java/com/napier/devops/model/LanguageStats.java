package com.napier.devops.model;


/**
 * Data Transfer Object (DTO) representing language statistics.
 * This class is used for reporting purposes and contains aggregated data
 * about language usage worldwide including speaker counts and percentages.
 * Note: This is not a JPA entity but a DTO for query results.
 */
public class LanguageStats {

    /**
     * Name of the language
     * Examples: "English", "Spanish", "Chinese", "Arabic"
     */
    protected String language;

    /**
     * Total number of speakers worldwide
     * Represents the total population that speaks this language
     */
    protected Long speakers;

    /**
     * Percentage of world population that speaks this language
     * Value between 0.0 and 100.0
     */
    protected Double percentageOfWorldPopulation;

    /**
     * Default constructor required by frameworks.
     * Creates a new LanguageStats instance with null values.
     */
    public LanguageStats() {
        // Default constructor required by Hibernate
    }

    /**
     * Constructor that initializes all fields with provided values.
     *
     * @param language the name of the language
     * @param speakers the total number of speakers worldwide
     * @param percentageOfWorldPopulation the percentage of world population that speaks this language
     */
    public LanguageStats(String language, Long speakers, Double percentageOfWorldPopulation) {
        this.language = language;
        this.speakers = speakers;
        this.percentageOfWorldPopulation = percentageOfWorldPopulation;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Long speakers) {
        this.speakers = speakers;
    }

    public Double getPercentageOfWorldPopulation() {
        return percentageOfWorldPopulation;
    }

    public void setPercentageOfWorldPopulation(Double percentageOfWorldPopulation) {
        this.percentageOfWorldPopulation = percentageOfWorldPopulation;
    }
}