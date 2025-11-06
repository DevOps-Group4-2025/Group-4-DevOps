package com.napier.devops.com.napier.devops;

import com.napier.devops.Group4Application;
import com.napier.devops.controller.CityController;
import com.napier.devops.model.Country;
import com.napier.devops.model.PopulationBreakdown;
import com.napier.devops.model.City;
import com.napier.devops.service.CountryService;
import com.napier.devops.service.PopulationBreakdownService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link Group4Application} that runs the main runner methods
 * without loading the Spring context. Dependencies are injected via reflection
 * as mocks so no DB or network is required.
 */
public class Group4ApplicationUnitTest {

    private static void setField(Object target, String fieldName, Object value) throws Exception {
        Field f = target.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        f.set(target, value);
    }

    @Test
    void runInvokesServicesAndControllers() throws Exception {
        // Arrange: create mocks and stub returns
        CountryService countryService = mock(CountryService.class);
        PopulationBreakdownService pbService = mock(PopulationBreakdownService.class);
        CityController cityController = mock(CityController.class);

        Country sample = new Country();
        sample.setCode("TST");
        sample.setName("Testland");
        sample.setPopulation(12345L);

        when(countryService.getAllCountriesWorld()).thenReturn(List.of(sample));
        when(pbService.getAllByContinent()).thenReturn(Collections.emptyList());
        when(pbService.getAllByRegion()).thenReturn(Collections.emptyList());
        when(pbService.getAllByCountry()).thenReturn(Collections.emptyList());

        when(cityController.getAllCitiesInTheWorld()).thenReturn(Collections.emptyList());
        when(cityController.getAllCitiesInAContinent("Asia")).thenReturn(Collections.emptyList());
        when(cityController.getAllCitiesInARegion("Eastern Asia")).thenReturn(Collections.emptyList());
        when(cityController.getAllCitiesInACountry("Japan")).thenReturn(Collections.emptyList());
        when(cityController.getAllCitiesInADistrict("Shanghai")).thenReturn(Collections.emptyList());
        when(cityController.getTopNCitiesInTheWorld(10)).thenReturn(Collections.emptyList());
        when(cityController.getTopNCitiesInAContinent("Asia", 10)).thenReturn(Collections.emptyList());
        when(cityController.getTopNCitiesInARegion("Eastern Asia", 10)).thenReturn(Collections.emptyList());
        when(cityController.getTopNCitiesInACountry("Japan", 10)).thenReturn(Collections.emptyList());
        when(cityController.getTopNCitiesInADistrict("Shanghai", 10)).thenReturn(Collections.emptyList());

        // Create app instance and inject mocks
        Group4Application app = new Group4Application();
        setField(app, "countryService", countryService);
        setField(app, "populationBreakdownService", pbService);
        setField(app, "cityController", cityController);

        // Act: run the application runner (will call our methods)
        app.run();

        // Assert: verify key interactions happened
        verify(countryService).getAllCountriesWorld();
        verify(pbService).getAllByContinent();
        verify(pbService).getAllByRegion();
        verify(pbService).getAllByCountry();
        verify(cityController).getAllCitiesInTheWorld();
    }
}
