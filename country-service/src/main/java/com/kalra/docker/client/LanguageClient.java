package com.kalra.docker.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * HTTP client for interacting with the language-service.
 * <p>
 * Uses Spring's {@code RestClient} to POST a list of countries and retrieve
 * the languages spoken in each country.
 */
@Service
public class LanguageClient {

    private final RestClient restClient;

    /**
     * Creates a client configured with a base URL.
     *
     * @param url base URL of the language-service (e.g., {@code http://language-service:8080})
     */
    public LanguageClient(@Value("${LANGUAGE_URL:http://localhost:8081}") String url) {
        this.restClient = RestClient.builder().baseUrl(url).build();
    }


    /**
     * Fetches languages for the given list of countries.
     *
     * @param countries country names
     * @return language responses, one per country
     */
    public List<LanguageResponse> getLanguages(List<String> countries) {
        LanguageRequest request = new LanguageRequest(countries);
        return restClient.post()
                .uri("/languages")
                .body(request)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<LanguageResponse>>() {})
                .getBody();
    }

}
