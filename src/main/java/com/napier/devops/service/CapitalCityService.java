package com.napier.devops.service;

import com.napier.devops.model.CapitalCity;
import com.napier.devops.repository.CapitalCityRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Service layer for capital city reporting use cases.
 */
@Service
public class CapitalCityService {

    private final CapitalCityRepository capitalCityRepository;

    public CapitalCityService(CapitalCityRepository capitalCityRepository) {
        this.capitalCityRepository = capitalCityRepository;
    }

    /**
     * Returns the top {@code limit} capital cities within the supplied continent
     * ordered by population descending.
     *
     * @param continent the continent to filter on (case insensitive)
     * @param limit     number of results to return (must be positive)
     * @return ordered list of capital cities
     * @throws IllegalArgumentException when the continent is blank or limit invalid
     */
    public List<CapitalCity> getTopCapitalCitiesInContinent(String continent, int limit) {
        if (!StringUtils.hasText(continent)) {
            throw new IllegalArgumentException("continent must not be blank");
        }
        if (limit <= 0) {
            throw new IllegalArgumentException("limit must be greater than zero");
        }

        return capitalCityRepository.findTopCapitalCitiesByContinent(
                continent.trim(),
                PageRequest.of(0, limit)
        );
    }
}


