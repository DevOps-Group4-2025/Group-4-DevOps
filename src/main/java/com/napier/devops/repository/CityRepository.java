package com.napier.devops.repository;

import com.napier.devops.model.City;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring Data repository for City operations.
 * <p>
 * Provides a derived query to find the largest city by population. Spring Data
 * will generate the implementation automatically.
 * </p>
 */
public interface CityRepository extends CrudRepository<City, Long> {

    /**
     * Return the single city with the highest population.
     *
     * @return the largest {@link City} by population, or null if none
     */
    City findTopByOrderByPopulationDesc();
}
