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

    /**
     * The capital city of the country
     */
    @Column(name = "`Capital`")
    protected Long capital;

    /**
     * Alternative 2-letter country code (ISO 3166-1 alpha-2)
     * Examples: "US", "GB", "DE"
     */
    @Column(name = "`Code2`")
    protected String code2;

    /**
     * Gets the country code.
     * @return the country code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the country code.
     * @param code the country code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the name of the country.
     * @return the name of the country
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the country.
     * @param name the name of the country
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the continent.
     * @return the continent
     */
    public String getContinent() {
        return continent;
    }

    /**
     * Sets the continent.
     * @param continent the continent
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * Gets the region.
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the region.
     * @param region the region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Gets the surface area.
     * @return the surface area
     */
    public Float getSurfaceArea() {
        return surfaceArea;
    }

    /**
     * Sets the surface area.
     * @param surfaceArea the surface area
     */
    public void setSurfaceArea(Float surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    /**
     * Gets the independence year.
     * @return the independence year
     */
    public Integer getIndepYear() {
        return indepYear;
    }

    /**
     * Sets the independence year.
     * @param indepYear the independence year
     */
    public void setIndepYear(Integer indepYear) {
        this.indepYear = indepYear;
    }

    /**
     * Gets the population.
     * @return the population
     */
    public Long getPopulation() {
        return population;
    }

    /**
     * Sets the population.
     * @param population the population
     */
    public void setPopulation(Long population) {
        this.population = population;
    }

    /**
     * Gets the life expectancy.
     * @return the life expectancy
     */
    public Float getLifeExpectancy() {
        return lifeExpectancy;
    }

    /**
     * Sets the life expectancy.
     * @param lifeExpectancy the life expectancy
     */
    public void setLifeExpectancy(Float lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    /**
     * Gets the GNP.
     * @return the GNP
     */
    public Float getGnp() {
        return gnp;
    }

    /**
     * Sets the GNP.
     * @param gnp the GNP
     */
    public void setGnp(Float gnp) {
        this.gnp = gnp;
    }

    /**
     * Gets the old GNP.
     * @return the old GNP
     */
    public Float getGnpOld() {
        return gnpOld;
    }

    /**
     * Sets the old GNP.
     * @param gnpOld the old GNP
     */
    public void setGnpOld(Float gnpOld) {
        this.gnpOld = gnpOld;
    }

    /**
     * Gets the local name.
     * @return the local name
     */
    public String getLocalName() {
        return localName;
    }

    /**
     * Sets the local name.
     * @param localName the local name
     */
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    /**
     * Gets the government form.
     * @return the government form
     */
    public String getGovernmentForm() {
        return governmentForm;
    }

    /**
     * Sets the government form.
     * @param governmentForm the government form
     */
    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    /**
     * Gets the head of state.
     * @return the head of state
     */
    public String getHeadOfState() {
        return headOfState;
    }

    /**
     * Sets the head of state.
     * @param headOfState the head of state
     */
    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    /**
     * Gets the capital.
     * @return the capital
     */
    public Long getCapital() {
        return capital;
    }

    /**
     * Sets the capital.
     * @param capital the capital
     */
    public void setCapital(Long capital) {
        this.capital = capital;
    }

    /**
     * Gets the code2.
     * @return the code2
     */
    public String getCode2() {
        return code2;
    }

    /**
     * Sets the code2.
     * @param code2 the code2
     */
    public void setCode2(String code2) {
        this.code2 = code2;
    }

    /**
     * Default constructor required by JPA/Hibernate.
     * Creates a new Country instance with all fields initialized to null.
     */
    public Country() {
    }

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

    @Override
    public String toString() {
        return String.format("%s (%s) - %s, %s - Population: %,d",
                name, code, continent, region, population != null ? population : 0L);
    }
}