package com.napier.devops.model;

import jakarta.persistence.*;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "`Code`")
    String code;

    @Column(name = "`Name`")
    String name;

    @Column(name = "`Continent`")
    String continent;

    @Column(name = "`Region`")
    String region;

    @Column(name = "`SurfaceArea`")
    Float surfaceArea;

    @Column(name = "`IndepYear`")
    Integer indepYear;

    @Column(name = "`Population`")
    Long population;

    @Column(name = "`LifeExpectancy`")
    Float lifeExpectancy;

    @Column(name = "`GNP`")
    Float gnp;

    @Column(name = "`GNPOld`")
    Float gnpOld;

    @Column(name = "`LocalName`")
    String localName;

    @Column(name = "`GovernmentForm`")
    String governmentForm;

    @Column(name = "`HeadOfState`")
    String headOfState;

    @Column(name = "`Capital`")
    Integer capital;

    @Column(name = "`Code2`")
    String code2;

    public Country() {
    }

    // Getters and Setters
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

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s, %s - Population: %,d",
                name, code, continent, region, population != null ? population : 0L);
    }
}