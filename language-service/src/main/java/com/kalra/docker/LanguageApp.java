package com.kalra.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Language microservice.
 * <p>
 * Provides an endpoint to return languages for a set of countries. Used by country-service.
 */
@SpringBootApplication
public class LanguageApp {

    public static void main(String[] args) {
        SpringApplication.run(LanguageApp.class, args);
    }

}
