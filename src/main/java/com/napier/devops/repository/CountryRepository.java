package com.napier.devops.repository;


import com.napier.devops.model.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface CountryRepository extends CrudRepository<Country, String> {

    // Use Case 1: Get all countries in the world ordered by population (descending)
    @Query("SELECT c FROM Country c ORDER BY c.population DESC")
    List<Country> getAllCountriesWorld();
}