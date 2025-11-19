package com.napier.devops.service;

import com.napier.devops.model.PopulationBreakdown;
import com.napier.devops.repository.PopulationBreakdownProjection;
import com.napier.devops.repository.PopulationBreakdownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for population breakdown related operations
 */
@Service
public class PopulationBreakdownService {

    private final PopulationBreakdownRepository repository;

    /**
     * Constructor for PopulationBreakdownService
     * @param repository the repository to handle population breakdown data
     */
    @Autowired
    public PopulationBreakdownService(PopulationBreakdownRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all population breakdowns by continent
     * @return a list of all population breakdowns by continent
     */
    public List<PopulationBreakdown> getAllByContinent() {
           List<PopulationBreakdownProjection> rows = repository.getAllByContinent();
           return rows.stream().map(this::toRecord).toList();
    }

    /**
     * Get all population breakdowns by region
     * @return a list of all population breakdowns by region
     */
    public List<PopulationBreakdown> getAllByRegion() {
           List<PopulationBreakdownProjection> rows = repository.getAllByRegion();
           return rows.stream().map(this::toRecord).toList();
    }

    /**
     * Get all population breakdowns by country
     * @return a list of all population breakdowns by country
     */
    public List<PopulationBreakdown> getAllByCountry() {
           List<PopulationBreakdownProjection> rows = repository.getAllByCountry();
           return rows.stream().map(this::toRecord).toList();
    }

    /**
     * Convert a PopulationBreakdownProjection to a PopulationBreakdown record
     * @param p the projection to convert
     * @return the converted record
     */
    private PopulationBreakdown toRecord(PopulationBreakdownProjection p) {
           return new PopulationBreakdown(
                 p.getType(),
                 p.getName(),
                 p.getTotalPopulation(),
                 p.getPopulationInCities(),
                 p.getPopulationNotInCities(),
                 p.getInCitiesPercentage(),
                 p.getNotInCitiesPercentage()
           );
        }
}
