package com.napier.devops.repository;

import com.napier.devops.model.CapitalCity;
import com.napier.devops.model.Country;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository exposing capital city reporting queries.
 */
public interface CapitalCityRepository extends JpaRepository<Country, String> {

    @Query("""
            SELECT new com.napier.devops.model.CapitalCity(
                city.name,
                country.name,
                city.population
            )
            FROM Country country
            JOIN City city ON city.id = country.capital
            WHERE LOWER(country.continent) = LOWER(:continent)
            ORDER BY city.population DESC
            """)
    List<com.napier.devops.model.CapitalCity> findTopCapitalCitiesByContinent(
            @Param("continent") String continent,
            Pageable pageable);

    List<CapitalCity> findCapitalCitiesByContinentOrderByPopulationDesc(String string);
}


