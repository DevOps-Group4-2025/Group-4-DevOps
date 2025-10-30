package com.napier.devops.service;

import com.napier.devops.model.PopulationBreakdown;
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
        return repository.getAllByContinent();
    }

    public List<PopulationBreakdown> getAllByRegion() {
        return repository.getAllByRegion();
    }

    public List<PopulationBreakdown> getAllByCountry() {
        return repository.getAllByCountry();
    }
}
