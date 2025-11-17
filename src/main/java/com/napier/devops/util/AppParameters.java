<<<<<<< Updated upstream
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

    
=======
package com.napier.devops.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Component for managing and accessing application parameters from the properties file.
 * This class injects values from `parameters.properties` and provides getters
 * for other parts of the application to use, primarily for non-interactive mode.
 */
@Component
public class AppParameters {

    // --- Country Reports ---
    @Value("${use_case_2.continent}")
    private String useCase2Continent;

    @Value("${use_case_3.region}")
    private String useCase3Region;

    @Value("${use_case_4.limit}")
    private int useCase4Limit;

    @Value("${use_case_5.continent}")
    private String useCase5Continent;

    @Value("${use_case_5.limit}")
    private int useCase5Limit;

    @Value("${use_case_6.region}")
    private String useCase6Region;

    @Value("${use_case_6.limit}")
    private int useCase6Limit;

    // --- City Reports ---
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

    // --- Capital City Reports ---
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

    // --- Population Reports ---
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

    // --- Getters ---
    public String getUseCase2Continent() { return useCase2Continent; }
    public String getUseCase3Region() { return useCase3Region; }
    public int getUseCase4Limit() { return useCase4Limit; }
    public String getUseCase5Continent() { return useCase5Continent; }
    public int getUseCase5Limit() { return useCase5Limit; }
    public String getUseCase6Region() { return useCase6Region; }
    public int getUseCase6Limit() { return useCase6Limit; }
    public String getUseCase8Continent() { return useCase8Continent; }
    public String getUseCase9Region() { return useCase9Region; }
    public String getUseCase10Country() { return useCase10Country; }
    public String getUseCase11District() { return useCase11District; }
    public int getUseCase12Limit() { return useCase12Limit; }
    public String getUseCase13Continent() { return useCase13Continent; }
    public int getUseCase13Limit() { return useCase13Limit; }
    public String getUseCase14Region() { return useCase14Region; }
    public int getUseCase14Limit() { return useCase14Limit; }
    public String getUseCase15Country() { return useCase15Country; }
    public int getUseCase15Limit() { return useCase15Limit; }
    public String getUseCase16District() { return useCase16District; }
    public int getUseCase16Limit() { return useCase16Limit; }
    public String getUseCase18Continent() { return useCase18Continent; }
    public String getUseCase19Region() { return useCase19Region; }
    public int getUseCase20Limit() { return useCase20Limit; }
    public String getUseCase21Continent() { return useCase21Continent; }
    public int getUseCase21Limit() { return useCase21Limit; }
    public String getUseCase22Region() { return useCase22Region; }
    public int getUseCase22Limit() { return useCase22Limit; }
    public String getUseCase27Continent() { return useCase27Continent; }
    public String getUseCase28Region() { return useCase28Region; }
    public String getUseCase29Country() { return useCase29Country; }
    public String getUseCase30District() { return useCase30District; }
    public String getUseCase31City() { return useCase31City; }
}
>>>>>>> Stashed changes
