package com.napier.devops.repository;

import com.napier.devops.model.PopulationBreakdown;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
/**
 * Repository responsible for executing aggregated population queries.
 *
 * <p>This class executes SQL aggregation queries that compute total population,
 * population living in cities and population not living in cities grouped by
 * continent, region or country. Results are returned as {@link PopulationBreakdown}
 * records.</p>
 */
public class PopulationBreakdownRepository {
    private final DataSource dataSource;

    public PopulationBreakdownRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Returns a list of population breakdowns grouped by continent.
     *
     * <p>The SQL computes totals and percent values (rounded to two decimal places).
     * If a database error occurs the method returns an empty list and the exception
     * is logged.</p>
     *
     * @return a list of {@link PopulationBreakdown} records, never null but may be empty
     */
    public List<PopulationBreakdown> getAllByContinent() {
    String sql = "SELECT \n" +
        "  country.Continent,\n" +
        "  SUM(country.Population) AS TotalPopulation,\n" +
        "  SUM(city.Population) AS PopulationInCities,\n" +
        "  SUM(country.Population) - SUM(city.Population) AS PopulationNotInCities,\n" +
        "  ROUND(SUM(city.Population) / SUM(country.Population) * 100, 2) AS PercentInCities,\n" +
        "  ROUND((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population) * 100, 2) AS PercentNotInCities\n" +
        "FROM country\n" +
        "JOIN city ON country.Code = city.CountryCode\n" +
        "GROUP BY country.Continent";
        List<PopulationBreakdown> result = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Continent");
                long total = rs.getLong("TotalPopulation");
                long inCities = rs.getLong("PopulationInCities");
                long notInCities = rs.getLong("PopulationNotInCities");
                double inPct = rs.getDouble("PercentInCities");
                double notInPct = rs.getDouble("PercentNotInCities");
                result.add(new PopulationBreakdown("continent", name, Long.valueOf(total), Long.valueOf(inCities), Long.valueOf(notInCities), Double.valueOf(inPct), Double.valueOf(notInPct)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Returns a list of population breakdowns grouped by region.
     *
     * <p>The SQL computes totals and percent values (rounded to two decimal places).
     * If a database error occurs the method returns an empty list and the exception
     * is logged.</p>
     *
     * @return a list of {@link PopulationBreakdown} records, never null but may be empty
     */
    public List<PopulationBreakdown> getAllByRegion() {
    String sql = "SELECT \n" +
        "  country.Region,\n" +
        "  SUM(country.Population) AS TotalPopulation,\n" +
        "  SUM(city.Population) AS PopulationInCities,\n" +
        "  SUM(country.Population) - SUM(city.Population) AS PopulationNotInCities,\n" +
        "  ROUND(SUM(city.Population) / SUM(country.Population) * 100, 2) AS PercentInCities,\n" +
        "  ROUND((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population) * 100, 2) AS PercentNotInCities\n" +
        "FROM country\n" +
        "JOIN city ON country.Code = city.CountryCode\n" +
        "GROUP BY country.Region";
        List<PopulationBreakdown> result = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Region");
                long total = rs.getLong("TotalPopulation");
                long inCities = rs.getLong("PopulationInCities");
                long notInCities = rs.getLong("PopulationNotInCities");
                double inPct = rs.getDouble("PercentInCities");
                double notInPct = rs.getDouble("PercentNotInCities");
                result.add(new PopulationBreakdown("region", name, Long.valueOf(total), Long.valueOf(inCities), Long.valueOf(notInCities), Double.valueOf(inPct), Double.valueOf(notInPct)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Returns a list of population breakdowns grouped by country.
     *
     * <p>The SQL computes totals and percent values (rounded to two decimal places).
     * If a database error occurs the method returns an empty list and the exception
     * is logged.</p>
     *
     * @return a list of {@link PopulationBreakdown} records, never null but may be empty
     */
    public List<PopulationBreakdown> getAllByCountry() {
    String sql = "SELECT \n" +
        "  country.Name AS Country,\n" +
        "  country.Population AS TotalPopulation,\n" +
        "  SUM(city.Population) AS PopulationInCities,\n" +
        "  country.Population - SUM(city.Population) AS PopulationNotInCities,\n" +
        "  ROUND(SUM(city.Population) / country.Population * 100, 2) AS PercentInCities,\n" +
        "  ROUND((country.Population - SUM(city.Population)) / country.Population * 100, 2) AS PercentNotInCities\n" +
        "FROM country\n" +
        "JOIN city ON country.Code = city.CountryCode\n" +
        "GROUP BY country.Name, country.Population";
        List<PopulationBreakdown> result = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Country");
                long total = rs.getLong("TotalPopulation");
                long inCities = rs.getLong("PopulationInCities");
                long notInCities = rs.getLong("PopulationNotInCities");
                double inPct = rs.getDouble("PercentInCities");
                double notInPct = rs.getDouble("PercentNotInCities");
                result.add(new PopulationBreakdown("country", name, Long.valueOf(total), Long.valueOf(inCities), Long.valueOf(notInCities), Double.valueOf(inPct), Double.valueOf(notInPct)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
