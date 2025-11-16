package com.napier.devops.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for CountryLanguageRepositoryImpl to ensure both MySQL and H2 code paths are covered.
 */
@ExtendWith(MockitoExtension.class)
class CountryLanguageRepositoryImplTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private Environment environment;

    @Mock
    private Query query;

    @InjectMocks
    private CountryLanguageRepositoryImpl repositoryImpl;

    @BeforeEach
    void setUp() {
        when(entityManager.createNativeQuery(anyString())).thenReturn(query);
    }

    @Test
    void getLanguageStatistics_usesH2Query_whenH2Database() {
        // Given
        when(environment.getProperty("spring.datasource.url", "")).thenReturn("jdbc:h2:mem:testdb");
        List<Object[]> mockResults = createMockResults();
        when(query.getResultList()).thenReturn(mockResults);

        // When
        List<LanguageStatsProjection> result = repositoryImpl.getLanguageStatistics();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        verify(entityManager).createNativeQuery(argThat(sql -> sql.contains("\"Language\"")));
        verify(entityManager).createNativeQuery(argThat(sql -> sql.contains("AS BIGINT")));
    }

    @Test
    void getLanguageStatistics_usesMySQLQuery_whenMySQLDatabase() {
        // Given
        when(environment.getProperty("spring.datasource.url", "")).thenReturn("jdbc:mysql://localhost:3306/world");
        List<Object[]> mockResults = createMockResults();
        when(query.getResultList()).thenReturn(mockResults);

        // When
        List<LanguageStatsProjection> result = repositoryImpl.getLanguageStatistics();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        verify(entityManager).createNativeQuery(argThat(sql -> sql.contains("`Language`")));
        verify(entityManager).createNativeQuery(argThat(sql -> sql.contains("AS SIGNED")));
    }

    @Test
    void getLanguageStatistics_usesMySQLQuery_whenNonH2Database() {
        // Given
        when(environment.getProperty("spring.datasource.url", "")).thenReturn("jdbc:postgresql://localhost:5432/world");
        List<Object[]> mockResults = createMockResults();
        when(query.getResultList()).thenReturn(mockResults);

        // When
        List<LanguageStatsProjection> result = repositoryImpl.getLanguageStatistics();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        verify(entityManager).createNativeQuery(argThat(sql -> sql.contains("`Language`")));
        verify(entityManager).createNativeQuery(argThat(sql -> sql.contains("AS SIGNED")));
    }

    @Test
    void getLanguageStatistics_mapsResultsCorrectly() {
        // Given
        when(environment.getProperty("spring.datasource.url", "")).thenReturn("jdbc:h2:mem:testdb");
        List<Object[]> mockResults = createMockResults();
        when(query.getResultList()).thenReturn(mockResults);

        // When
        List<LanguageStatsProjection> result = repositoryImpl.getLanguageStatistics();

        // Then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getLanguage()).isEqualTo("Chinese");
        assertThat(result.get(0).getSpeakers()).isEqualTo(1000000L);
        assertThat(result.get(0).getPercentageOfWorldPopulation()).isEqualTo(19.61);
        
        assertThat(result.get(1).getLanguage()).isEqualTo("English");
        assertThat(result.get(1).getSpeakers()).isEqualTo(500000L);
        assertThat(result.get(1).getPercentageOfWorldPopulation()).isEqualTo(9.80);
    }

    @Test
    void getLanguageStatistics_handlesEmptyResults() {
        // Given
        when(environment.getProperty("spring.datasource.url", "")).thenReturn("jdbc:h2:mem:testdb");
        when(query.getResultList()).thenReturn(new ArrayList<>());

        // When
        List<LanguageStatsProjection> result = repositoryImpl.getLanguageStatistics();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    void getLanguageStatistics_handlesNullDatabaseUrl() {
        // Given
        when(environment.getProperty("spring.datasource.url", "")).thenReturn("");
        List<Object[]> mockResults = createMockResults();
        when(query.getResultList()).thenReturn(mockResults);

        // When
        List<LanguageStatsProjection> result = repositoryImpl.getLanguageStatistics();

        // Then
        assertThat(result).isNotNull();
        // Should default to MySQL path when URL is empty
        verify(entityManager).createNativeQuery(argThat(sql -> sql.contains("`Language`")));
    }

    private List<Object[]> createMockResults() {
        List<Object[]> results = new ArrayList<>();
        results.add(new Object[]{"Chinese", 1000000L, 19.61});
        results.add(new Object[]{"English", 500000L, 9.80});
        return results;
    }
}

