package com.napier.devops;


public class Countrylanguage {

  private String countryCode;
  private String language;
  private String isOfficial;
  private double percentage;

    public Countrylanguage(String countryCode, String language, String isOfficial, double percentage) {
        this.countryCode = countryCode;
        this.language = language;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public String getIsOfficial() {
        return isOfficial;
    }

    public double getPercentage() {
        return percentage;
    }

    @Override
    public String toString() {
        return "Countrylanguage{" +
                "countryCode='" + countryCode + '\'' +
                ", language='" + language + '\'' +
                ", isOfficial='" + isOfficial + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
