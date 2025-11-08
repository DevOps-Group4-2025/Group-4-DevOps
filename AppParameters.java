package com.napier.devops.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Loads and provides access to externalized parameters from the parameters.properties file.
 * This allows changing report parameters without modifying the source code.
 */
@Component
@PropertySource(value = "classpath:parameters.properties", ignoreResourceNotFound = true)
@Getter
public class AppParameters {

    @Value("${use_case_2.continent}")
    private String useCase2Continent;

    @Value("${use_case_8.continent}")
    private String useCase8Continent;

    @Value("${use_case_9.region}")
    private String useCase9Region;

    @Value("${use_case_10.country}")
    private String useCase10Country;

    @Value("${use_case_11.district}")
    private String useCase11District;

    @Value("${use_case_12.limit}")
    private int useCase12Limit;

    @Value("${use_case_13.continent}")
    private String useCase13Continent;

    @Value("${use_case_13.limit}")
    private int useCase13Limit;

}