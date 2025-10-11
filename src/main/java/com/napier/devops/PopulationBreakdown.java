package com.napier.devops;

import java.util.Map;

public class PopulationBreakdown {
    private String type; // continent, region, or country
    private String name;
    private long totalPopulation;
    private long populationInCities;
    private long populationNotInCities;

    public PopulationBreakdown(String type, String name, long totalPopulation, long populationInCities, long populationNotInCities) {
        this.type = type;
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.populationInCities = populationInCities;
        this.populationNotInCities = populationNotInCities;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public long getTotalPopulation() {
        return totalPopulation;
    }

    public long getPopulationInCities() {
        return populationInCities;
    }

    public long getPopulationNotInCities() {
        return populationNotInCities;
    }

    public double getInCitiesPercentage() {
        if (totalPopulation == 0) return 0;
        return (double) populationInCities / totalPopulation * 100;
    }

    public double getNotInCitiesPercentage() {
        if (totalPopulation == 0) return 0;
        return (double) populationNotInCities / totalPopulation * 100;
    }

    @Override
    public String toString() {
        return String.format("%s: %s\nTotal Population: %d\nPopulation in Cities: %d (%.2f%%)\nPopulation not in Cities: %d (%.2f%%)",
                type, name, totalPopulation, populationInCities, getInCitiesPercentage(), populationNotInCities, getNotInCitiesPercentage());
    }
}
