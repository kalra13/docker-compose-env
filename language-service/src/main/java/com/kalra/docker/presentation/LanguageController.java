package com.kalra.docker.presentation;

import com.kalra.docker.LanguageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * REST controller for the language-service.
 * <p>
 * Accepts a list of country names and returns languages grouped by country.
 */
@Slf4j
@RestController
public class LanguageController {

    private final LanguageService service;

    public LanguageController(LanguageService service) {
        this.service = service;
    }

    /**
     * Returns languages for the requested countries.
     *
     * @param request body containing the list of country names under the {@code languages} property
     * @return HTTP 200 with a list of country-language mappings
     */
    @PostMapping("/languages")
    public ResponseEntity<List<LanguageResponse>> getLanguages(@RequestBody LanguageRequest request) {
        log.info("Received request for languages: {}", request);
        return ResponseEntity.ok().body(service.getLanguages(request.languages()));
    }
}
