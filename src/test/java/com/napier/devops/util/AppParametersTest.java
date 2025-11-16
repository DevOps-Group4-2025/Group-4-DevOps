package com.napier.devops.util;

import com.napier.devops.Group4Application; // Make sure this is your main app class
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration tests for the AppParameters class.
 * This test class verifies that the application parameters are correctly loaded
 * from the 'parameters.properties' file into the AppParameters bean.
 * It uses the Spring Boot testing framework to load the application context
 * and inject the AppParameters bean for testing.
 */
@SpringBootTest(classes = Group4Application.class) // Explicitly specify the main configuration class
@ActiveProfiles("test")
class AppParametersTest {

    @Autowired
    private AppParameters appParameters;

    /**
     * Tests that all parameters are loaded correctly from the properties file.
     */
    @Test
    void testAppParametersLoading() {
        assertEquals("Asia", appParameters.getUseCase2Continent());
        assertEquals("Asia", appParameters.getUseCase8Continent());
        assertEquals("Eastern Asia", appParameters.getUseCase9Region());
        assertEquals("Japan", appParameters.getUseCase10Country());
        assertEquals("Shanghai", appParameters.getUseCase11District());
        assertEquals(10, appParameters.getUseCase12Limit());
        assertEquals("Asia", appParameters.getUseCase13Continent());
        assertEquals(10, appParameters.getUseCase13Limit());
        assertEquals("Eastern Asia", appParameters.getUseCase14Region());
        assertEquals(10, appParameters.getUseCase14Limit());
        assertEquals("Japan", appParameters.getUseCase15Country());
        assertEquals(10, appParameters.getUseCase15Limit());
        assertEquals("Shanghai", appParameters.getUseCase16District());
        assertEquals(10, appParameters.getUseCase16Limit());
        assertEquals("Asia", appParameters.getUseCase18Continent());
        assertEquals("Caribbean", appParameters.getUseCase19Region());
        assertEquals(10, appParameters.getUseCase20Limit());
        assertEquals("Asia", appParameters.getUseCase21Continent());
        assertEquals(10, appParameters.getUseCase21Limit());
        assertEquals("Caribbean", appParameters.getUseCase22Region());
        assertEquals(10, appParameters.getUseCase22Limit());
        assertEquals("Asia", appParameters.getUseCase27Continent());
        assertEquals("Caribbean", appParameters.getUseCase28Region());
        assertEquals("Italy", appParameters.getUseCase29Country());
        assertEquals("Shanghai", appParameters.getUseCase30District());
        assertEquals("London", appParameters.getUseCase31City());
    }
}
