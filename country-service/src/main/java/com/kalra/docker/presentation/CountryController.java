package com.kalra.docker.presentation;

import com.kalra.docker.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CountryController {

    private final CountryService service;


    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("/countries/all")
    public ResponseEntity<List<String>> getAllCountries() {

        return ResponseEntity.ok().body(service.getAllCountries());
    }

    @GetMapping( "/countries/languages/count")
    public ResponseEntity<List<CountryLanguageCountResponse>> getCountryLanguageSummaries() {

        log.info("Fetching country language summaries");
        return ResponseEntity.ok().body(service.getCountryLanguageSummaries());
    }
}
