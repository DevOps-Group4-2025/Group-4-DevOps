package com.napier.devops.service;

import com.napier.devops.model.PopulationBreakdown;
import com.napier.devops.repository.PopulationBreakdownProjection;
import com.napier.devops.repository.PopulationBreakdownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PopulationBreakdownService {

    private final PopulationBreakdownRepository repository;

    @Autowired
    public PopulationBreakdownService(PopulationBreakdownRepository repository) {
        this.repository = repository;
    }

    public List<PopulationBreakdown> getAllByContinent() {
           List<PopulationBreakdownProjection> rows = repository.getAllByContinent();
           return rows.stream().map(this::toRecord).toList();
    }

    public List<PopulationBreakdown> getAllByRegion() {
           List<PopulationBreakdownProjection> rows = repository.getAllByRegion();
           return rows.stream().map(this::toRecord).toList();
    }

    public List<PopulationBreakdown> getAllByCountry() {
           List<PopulationBreakdownProjection> rows = repository.getAllByCountry();
           return rows.stream().map(this::toRecord).toList();
    }

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
