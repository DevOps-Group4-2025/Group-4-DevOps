package com.napier.devops.controller;

import com.napier.devops.service.PopulationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PopulationControllerTest {

    @Mock
    private PopulationService populationService; // ← mock the service

    @InjectMocks
    private PopulationController populationController; // ← inject mock into controller

    @Test
    void getWorldPopulation_returnsCorrectValue() {
        when(populationService.getWorldPopulation()).thenReturn(8000000000L);

        Long result = populationController.getWorldPopulation();

        assertEquals(8000000000L, result);
    }

    @Test
    void getContinentPopulation_returnsCorrectValue() {
        when(populationService.getContinentPopulation("Europe")).thenReturn(747000000L);

        Long result = populationController.getContinentPopulation("Europe");

        assertEquals(747000000L, result);
    }

    @Test
    void getRegionPopulation_returnsCorrectValue() {
        when(populationService.getRegionPopulation("Western Europe")).thenReturn(200000000L);

        Long result = populationController.getRegionPopulation("Western Europe");

        assertEquals(200000000L, result);
    }

    @Test
    void getCountryPopulation_returnsCorrectValue() {
        when(populationService.getCountryPopulation("France")).thenReturn(67000000L);

        Long result = populationController.getCountryPopulation("France");

        assertEquals(67000000L, result);
    }

    @Test
    void getDistrictPopulation_returnsCorrectValue() {
        when(populationService.getDistrictPopulation("Ile-de-France")).thenReturn(12000000L);

        Long result = populationController.getDistrictPopulation("Ile-de-France");

        assertEquals(12000000L, result);
    }

    @Test
    void getCityPopulation_returnsCorrectValue() {
        when(populationService.getCityPopulation("Paris")).thenReturn(2140000L);

        Long result = populationController.getCityPopulation("Paris");

        assertEquals(2140000L, result);
    }
}
