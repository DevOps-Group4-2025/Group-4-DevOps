package com.napier.devops.service;

import com.napier.devops.model.PopulationBreakdown;
import com.napier.devops.repository.PopulationBreakdownProjection;
import com.napier.devops.repository.PopulationBreakdownRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class PopulationBreakdownServiceTest {

    @Mock
    private PopulationBreakdownRepository repository;

    @InjectMocks
    private PopulationBreakdownService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllByContinent_returnsDataCorrectly() {
        when(repository.getAllByContinent()).thenReturn(List.of(
                proj("Continent", "Asia", 4000, 2500, 1500, 62.5, 37.5),
                proj("Continent", "Europe", 3000, 1200, 1800, 40.0, 60.0)
        ));

        List<PopulationBreakdown> actual = service.getAllByContinent();

        assertThat(actual)
                .hasSize(2)
                .extracting(PopulationBreakdown::name)
                .containsExactlyInAnyOrder("Asia", "Europe");
    }

    @Test
    void getAllByRegion_returnsDataCorrectly() {
        when(repository.getAllByRegion()).thenReturn(List.of(
                proj("Region", "Eastern Asia", 2000, 1200, 800, 60.0, 40.0)
        ));

        List<PopulationBreakdown> actual = service.getAllByRegion();

        assertThat(actual)
                .hasSize(1)
                .extracting(PopulationBreakdown::name)
                .containsExactly("Eastern Asia");
    }

    @Test
    void getAllByCountry_returnsDataCorrectly() {
        when(repository.getAllByCountry()).thenReturn(List.of(
                proj("Country", "China", 1400, 800, 600, 57.1, 42.9)
        ));

        List<PopulationBreakdown> actual = service.getAllByCountry();

        assertThat(actual)
                .hasSize(1)
                .extracting(PopulationBreakdown::name)
                .containsExactly("China");
    }


    // Helper to create projection instances
        
    private PopulationBreakdownProjection proj(
            String type,
            String name,
            Long total,
            Long inCities,
            Long notInCities,
            Double pctIn,
            Double pctOut
    ) {
        return new PopulationBreakdownProjection() {
            @Override public String getType() { return type; }
            @Override public String getName() { return name; }
            @Override public Long getTotalPopulation() { return total; }        // boxed
            @Override public Long getPopulationInCities() { return inCities; }  // boxed
            @Override public Long getPopulationNotInCities() { return notInCities; } // boxed
            @Override public Double getInCitiesPercentage() { return pctIn; }   // boxed
            @Override public Double getNotInCitiesPercentage() { return pctOut; } // boxed
        };
    }
    
}
