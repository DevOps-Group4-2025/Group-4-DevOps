package com.napier.devops;


public class Country {

  private String code;
  private String name;
  private String continent;
  private String region;
  private double surfaceArea;
  private long indepYear;
  private long population;
  private double lifeExpectancy;
  private double gnp;
  private double gnpOld;
  private String localName;
  private String governmentForm;
  private String headOfState;
  private long capital;
  private String code2;

    public Country(String code, String name, String continent, String region, double surfaceArea, long indepYear, long population, double lifeExpectancy, double gnp, double gnpOld, String localName, String governmentForm, String headOfState, long capital, String code2) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.surfaceArea = surfaceArea;
        this.indepYear = indepYear;
        this.population = population;
        this.lifeExpectancy = lifeExpectancy;
        this.gnp = gnp;
        this.gnpOld = gnpOld;
        this.localName = localName;
        this.governmentForm = governmentForm;
        this.headOfState = headOfState;
        this.capital = capital;
        this.code2 = code2;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public String getRegion() {
        return region;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public long getIndepYear() {
        return indepYear;
    }

    public long getPopulation() {
        return population;
    }

    public double getLifeExpectancy() {
        return lifeExpectancy;
    }

    public double getGnp() {
        return gnp;
    }

    public double getGnpOld() {
        return gnpOld;
    }

    public String getLocalName() {
        return localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public long getCapital() {
        return capital;
    }

    public String getCode2() {
        return code2;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", surfaceArea=" + surfaceArea +
                ", indepYear=" + indepYear +
                ", population=" + population +
                ", lifeExpectancy=" + lifeExpectancy +
                ", gnp=" + gnp +
                ", gnpOld=" + gnpOld +
                ", localName='" + localName + '\'' +
                ", governmentForm='" + governmentForm + '\'' +
                ", headOfState='" + headOfState + '\'' +
                ", capital=" + capital +
                ", code2='" + code2 + '\'' +
                '}';
    }
}
