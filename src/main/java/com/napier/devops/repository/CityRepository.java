package com.napier.devops.repository;

import com.napier.devops.model.City;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CityRepository {
    private final DataSource dataSource;

    public CityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Returns the single largest city by population (or null if none).
     */
    public City getLargestCity() {
        String sql = "SELECT ID, Name, CountryCode, District, Population FROM city ORDER BY Population DESC LIMIT 1";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                City city = new City();
                city.setId(rs.getLong("ID"));
                city.setName(rs.getString("Name"));
                city.setCountryCode(rs.getString("CountryCode"));
                city.setDistrict(rs.getString("District"));
                city.setPopulation(rs.getInt("Population"));
                return city;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
