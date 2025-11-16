package com.napier.devops.controller;

import com.napier.devops.model.LanguageStats;
import com.napier.devops.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Controller for managing language-related endpoints.
 */
@Controller
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    /**
     * USE CASE 32: Get language statistics for Chinese, English, Hindi, Spanish, and Arabic.
     * Returns languages ordered by number of speakers (descending).
     *
     * @return ResponseEntity containing list of LanguageStats
     */
    @GetMapping("/languages")
    public ResponseEntity<List<LanguageStats>> getLanguageStatistics() {
        return ResponseEntity.ok(languageService.getLanguageStatistics());
    }

    /**
     * Gets language statistics for use in the main application.
     *
     * @return List of LanguageStats
     */
    public List<LanguageStats> getLanguageStatisticsList() {
        return languageService.getLanguageStatistics();
    }
}
