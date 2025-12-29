package com.kalra.docker.presentation;

import com.kalra.docker.LanguageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class LanguageController {

    private final LanguageService service;

    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @PostMapping("/languages")
    public ResponseEntity<List<LanguageResponse>> getLanguages(@RequestBody LanguageRequest request) {
        log.info("Received request for languages: {}", request);
        return ResponseEntity.ok().body(service.getLanguages(request.languages()));
    }
}
