package com.napier.devops.model;

/**
 * Data Transfer Object (DTO) representing population statistics.
 * This class is used for reporting purposes and contains aggregated data
 * about population distribution between urban and rural areas.
 * Note: This is not a JPA entity but a DTO for query results.
 */
public class PopulationStats {

    /**
     * Geographic area name (continent, region, or country)
     * Examples: "North America", "Western Europe", "United States"
     */
    protected String area;

    /**
     * Total population of the area
     * Represents the sum of all people in the specified area
     */
    protected Long totalPopulation;

    /**
     * Population living in cities within the area
     * Urban population count
     */
    protected Long populationInCities;

    /**
     * Population living outside cities within the area
     * Rural population count
     */
    protected Long populationNotInCities;

    /**
     * Percentage of population living in cities
     * Value between 0.0 and 100.0
     */
    protected Double percentInCities;

    /**
     * Percentage of population living outside cities
     * Value between 0.0 and 100.0
     */
    protected Double percentNotInCities;

    /**
     * Default constructor required by frameworks.
     * Creates a new PopulationStats instance with null values.
     */
    public PopulationStats() {
        // Default constructor required by Hibernate
    }

    /**
     * Constructor that initializes all fields with provided values.
     *
     * @param area the geographic area name (continent, region, or country)
     * @param totalPopulation the total population of the area
     * @param populationInCities the population living in cities
     * @param populationNotInCities the population living outside cities
     * @param percentInCities the percentage of population living in cities
     * @param percentNotInCities the percentage of population living outside cities
     */
    public PopulationStats(String area, Long totalPopulation, Long populationInCities,
                           Long populationNotInCities, Double percentInCities, Double percentNotInCities) {
        this.area = area;
        this.totalPopulation = totalPopulation;
        this.populationInCities = populationInCities;
        this.populationNotInCities = populationNotInCities;
        this.percentInCities = percentInCities;
        this.percentNotInCities = percentNotInCities;
    }

    // Getters och setters
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public Long getTotalPopulation() { return totalPopulation; }
    public void setTotalPopulation(Long totalPopulation) { this.totalPopulation = totalPopulation; }

    public Long getPopulationInCities() { return populationInCities; }
    public void setPopulationInCities(Long populationInCities) { this.populationInCities = populationInCities; }

    public Long getPopulationNotInCities() { return populationNotInCities; }
    public void setPopulationNotInCities(Long populationNotInCities) { this.populationNotInCities = populationNotInCities; }

    public Double getPercentInCities() { return percentInCities; }
    public void setPercentInCities(Double percentInCities) { this.percentInCities = percentInCities; }

    public Double getPercentNotInCities() { return percentNotInCities; }
    public void setPercentNotInCities(Double percentNotInCities) { this.percentNotInCities = percentNotInCities; }
}