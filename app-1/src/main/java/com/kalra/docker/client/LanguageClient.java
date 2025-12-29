package com.kalra.docker.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
public class LanguageClient {

    private final RestClient app1Client;

    public LanguageClient(@Value("${LANGUAGE_URL:http://localhost:8081}") String url) {
        this.app1Client = RestClient.builder().baseUrl(url).build();
    }


    public List<LanguageResponse> getLanguages(List<String> countries) {
        LanguageRequest request = new LanguageRequest(countries);
        return app1Client.post()
                .uri("/languages")
                .body(request)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<LanguageResponse>>() {})
                .getBody();
    }

}
