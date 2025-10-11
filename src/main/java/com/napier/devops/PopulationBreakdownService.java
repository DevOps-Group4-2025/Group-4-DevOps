package com.napier.devops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PopulationBreakdownService {
    private final Connection con;

    public PopulationBreakdownService(Connection con) {
        this.con = con;
    }

    public PopulationBreakdown getByContinent(String continent) {
        String sql = "SELECT c.Continent, SUM(c.Population) AS totalPopulation, " +
                "SUM(IFNULL(cityPop,0)) AS populationInCities, " +
                "SUM(c.Population - IFNULL(cityPop,0)) AS populationNotInCities " +
                "FROM country c LEFT JOIN (SELECT CountryCode, SUM(Population) AS cityPop FROM city GROUP BY CountryCode) ci " +
                "ON c.Code = ci.CountryCode WHERE c.Continent = ? GROUP BY c.Continent";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, continent);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                long total = rs.getLong("totalPopulation");
                long inCities = rs.getLong("populationInCities");
                long notInCities = rs.getLong("populationNotInCities");
                return new PopulationBreakdown("continent", continent, total, inCities, notInCities);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PopulationBreakdown getByRegion(String region) {
        String sql = "SELECT c.Region, SUM(c.Population) AS totalPopulation, " +
                "SUM(IFNULL(cityPop,0)) AS populationInCities, " +
                "SUM(c.Population - IFNULL(cityPop,0)) AS populationNotInCities " +
                "FROM country c LEFT JOIN (SELECT CountryCode, SUM(Population) AS cityPop FROM city GROUP BY CountryCode) ci " +
                "ON c.Code = ci.CountryCode WHERE c.Region = ? GROUP BY c.Region";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, region);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                long total = rs.getLong("totalPopulation");
                long inCities = rs.getLong("populationInCities");
                long notInCities = rs.getLong("populationNotInCities");
                return new PopulationBreakdown("region", region, total, inCities, notInCities);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PopulationBreakdown getByCountry(String country) {
        String sql = "SELECT c.Name, c.Population AS totalPopulation, " +
                "IFNULL(ci.cityPop,0) AS populationInCities, " +
                "(c.Population - IFNULL(ci.cityPop,0)) AS populationNotInCities " +
                "FROM country c LEFT JOIN (SELECT CountryCode, SUM(Population) AS cityPop FROM city GROUP BY CountryCode) ci " +
                "ON c.Code = ci.CountryCode WHERE c.Name = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, country);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                long total = rs.getLong("totalPopulation");
                long inCities = rs.getLong("populationInCities");
                long notInCities = rs.getLong("populationNotInCities");
                return new PopulationBreakdown("country", country, total, inCities, notInCities);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}