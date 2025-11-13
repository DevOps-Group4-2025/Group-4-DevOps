package com.napier.devops.model;

import jakarta.persistence.*;

/**
 * Entity representing a country in the world database.
 * Maps to the 'country' table and contains comprehensive country information
 * including geographic, demographic, and political data.
 */
@Entity
@Table(name = "country")
public class Country {

    /**
     * Primary key - unique 3-letter country code (ISO 3166-1 alpha-3)
     * Examples: "USA", "GBR", "DEU"
     */
    @Id
    @Column(name = "`Code`")
    protected String code;

    /**
     * Official name of the country
     * Examples: "United States", "United Kingdom", "Germany"
     */
    @Column(name = "`Name`")
    protected String name;

    /**
     * Continent where the country is located
     * Examples: "North America", "Europe", "Asia"
     */
    @Column(name = "`Continent`")
    protected String continent;

    /**
     * Geographic region within the continent
     * Examples: "Caribbean", "Western Europe", "Southeast Asia"
     */
    @Column(name = "`Region`")
    protected String region;

    /**
     * Total surface area in square kilometers
     * Includes land and water areas
     */
    @Column(name = "`SurfaceArea`")
    protected Float surfaceArea;

    /**
     * Year when the country gained independence
     * Null if the country was never colonized or independence date is unknown
     */
    @Column(name = "`IndepYear`")
    protected Integer indepYear;

    /**
     * Total population of the country
     * Represents the most recent population count
     */
    @Column(name = "`Population`")
    protected Long population;

    /**
     * Average life expectancy in years
     * Calculated at birth for the current population
     */
    @Column(name = "`LifeExpectancy`")
    protected Float lifeExpectancy;

    /**
     * Gross National Product in current US dollars
     * Measures the total value of goods and services produced
     */
    @Column(name = "`GNP`")
    protected Float gnp;

    /**
     * Previous GNP value for comparison
     * Used to track economic growth over time
     */
    @Column(name = "`GNPOld`")
    protected Float gnpOld;

    /**
     * Local name of the country in its native language(s)
     * Examples: "Deutschland" for Germany, "España" for Spain
     */
    @Column(name = "`LocalName`")
    protected String localName;

    /**
     * Type of government system
     * Examples: "Federal Republic", "Constitutional Monarchy", "Parliamentary Democracy"
     */
    @Column(name = "`GovernmentForm`")
    protected String governmentForm;

    /**
     * Name of the current head of state
     * Could be president, prime minister, king, queen, etc.
     */
    @Column(name = "`HeadOfState`")
    protected String headOfState;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Float getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(Float surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Integer getIndepYear() {
        return indepYear;
    }

    public void setIndepYear(Integer indepYear) {
        this.indepYear = indepYear;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Float getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(Float lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public Float getGnp() {
        return gnp;
    }

    public void setGnp(Float gnp) {
        this.gnp = gnp;
    }

    public Float getGnpOld() {
        return gnpOld;
    }

    public void setGnpOld(Float gnpOld) {
        this.gnpOld = gnpOld;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public Long getCapital() {
        return capital;
    }

    public void setCapital(Long capital) {
        this.capital = capital;
    }

    @Column(name = "`Capital`")
    protected Long capital;


    /**
     * Alternative 2-letter country code (ISO 3166-1 alpha-2)
     * Examples: "US", "GB", "DE"
     */
    @Column(name = "`Code2`")
    protected String code2;

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    /**
     * Default constructor required by JPA/Hibernate.
     * Creates a new Country instance with all fields initialized to null.
     */
    public Country() {
    }
   /* public Weather(String country, String city, String description, double temprature, double wind_M_S, double humidity, String feelsLike) {

    /**
     * Constructor for creating a Country instance with essential fields.
     *
     * @param code The country code.
     * @param name The name of the country.
     * @param continent The continent.
     * @param region The region.
     * @param population The total population.
     */
    public Country(String code, String name, String continent, String region, Long population) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
    }

    /* public Weather(String country, String city, String description, double temprature, double wind_M_S, double humidity, String feelsLike) {
        this.country = country;
        this.city = city;
        this.description = description;
        this.temprature = temprature;
        this.wind_M_S = wind_M_S;
        this.humidity = humidity;
        this.feelsLike = feelsLike;
    }

    */

    @Override
    public String toString() {
        return String.format("%s (%s) - %s, %s - Population: %,d",
                name, code, continent, region, population != null ? population : 0L);
    }
}