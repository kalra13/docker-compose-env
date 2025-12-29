package com.kalra.docker.presentation;

import java.util.List;

/**
 * Response body mapping a country to its list of languages.
 *
 * @param country   country name
 * @param languages languages spoken in the country
 */
public record LanguageResponse(String country, List<String> languages) {}