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
@PropertySource("classpath:parameters.properties")
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

    @Value("${use_case_14.region}")
    private String useCase14Region;

    @Value("${use_case_14.limit}")
    private int useCase14Limit;

    @Value("${use_case_15.country}")
    private String useCase15Country;

    @Value("${use_case_15.limit}")
    private int useCase15Limit;

    @Value("${use_case_16.district}")
    private String useCase16District;

    @Value("${use_case_16.limit}")
    private int useCase16Limit;

    @Value("${use_case_18.continent}")
    private String useCase18Continent;

    @Value("${use_case_19.region}")
    private String useCase19Region;

    @Value("${use_case_20.limit}")
    private int useCase20Limit;

    @Value("${use_case_21.continent}")
    private String useCase21Continent;

    @Value("${use_case_21.limit}")
    private int useCase21Limit;

    @Value("${use_case_22.region}")
    private String useCase22Region;

    @Value("${use_case_22.limit}")
    private int useCase22Limit;

    @Value("${use_case_27.continent}")
    private String useCase27Continent;

    @Value("${use_case_28.region}")
    private String useCase28Region;

    @Value("${use_case_29.country}")
    private String useCase29Country;

    @Value("${use_case_30.district}")
    private String useCase30District;

    @Value("${use_case_31.city}")
    private String useCase31City;

}

    