package com.kalra.docker.client;

import java.util.List;

/**
 * Response payload returned by the language-service for a given country.
 *
 * @param country   country name
 * @param languages languages spoken in that country
 */
public record LanguageResponse(String country, List<String> languages) {}