package com.kalra.docker.presentation;

import java.util.List;

/**
 * Request body containing the list of country names. The property is
 * intentionally named {@code languages} for compatibility with the
 * existing API contract used by country-service.
 *
 * @param languages list of country names
 */
public record LanguageRequest(List<String> languages) { }
