package com.napier.devops;

import com.napier.devops.controller.CityController;
import com.napier.devops.model.Country;
import com.napier.devops.service.CountryService;
import com.napier.devops.service.PopulationBreakdownService;
import com.napier.devops.service.CapitalCityService;
import com.napier.devops.util.AppParameters;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

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
        // Arrange: create mocks
        CountryService countryService = mock(CountryService.class);
        PopulationBreakdownService pbService = mock(PopulationBreakdownService.class);
        CityController cityController = mock(CityController.class);
        CapitalCityService capitalCityService = mock(CapitalCityService.class);  // ✅ Added
        AppParameters appParameters = mock(AppParameters.class);                 // ✅ Added

        // Sample data
        Country sample = new Country();
        sample.setCode("TST");
        sample.setName("Testland");
        sample.setPopulation(12345L);

        // Stub service methods
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

        // ✅ Capital city service stubs
        when(capitalCityService.getAllCapitalCitiesByPopulation()).thenReturn(Collections.emptyList());

        // ✅ App parameters stubs
        when(appParameters.getUseCase2Continent()).thenReturn("Asia");
        when(appParameters.getUseCase8Continent()).thenReturn("Asia");
        when(appParameters.getUseCase9Region()).thenReturn("Eastern Asia");

        // Create app instance and inject mocks
        Group4Application app = new Group4Application();
        setField(app, "countryService", countryService);
        setField(app, "populationBreakdownService", pbService);
        setField(app, "cityController", cityController);
        setField(app, "capitalCityService", capitalCityService);  // ✅ Added
        setField(app, "appParameters", appParameters);            // ✅ Added

        // Act: run the application (executes all use cases)
        app.run();

        // Assert: verify expected interactions
        verify(countryService).getAllCountriesWorld();
        verify(pbService).getAllByContinent();
        verify(pbService).getAllByRegion();
        verify(pbService).getAllByCountry();
        verify(cityController).getAllCitiesInTheWorld();
        verify(capitalCityService).getAllCapitalCitiesByPopulation(); // ✅ New check
    }
}
