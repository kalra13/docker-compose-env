package com.kalra.docker.presentation;

import com.kalra.docker.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller exposing country-related endpoints.
 * <p>
 * Delegates to {@link com.kalra.docker.CountryService} for data retrieval and aggregation.
 */
@Slf4j
@RestController
public class CountryController {

    private final CountryService service;


    public CountryController(CountryService service) {
        this.service = service;
    }

    /**
     * Returns all available country names.
     *
     * @return HTTP 200 with list of country names
     */
    @GetMapping("/countries/all")
    public ResponseEntity<List<String>> getAllCountries() {

        return ResponseEntity.ok().body(service.getAllCountries());
    }

    /**
     * Returns a summary with the number of languages per country.
     * <p>
     * The service calls language-service under the hood to compute language counts.
     *
     * @return HTTP 200 with list of country-language count summaries
     */
    @GetMapping( "/countries/languages/count")
    public ResponseEntity<List<CountryLanguageCountResponse>> getCountryLanguageSummaries() {

        log.info("Fetching country language summaries");
        return ResponseEntity.ok().body(service.getCountryLanguageSummaries());
    }
}
