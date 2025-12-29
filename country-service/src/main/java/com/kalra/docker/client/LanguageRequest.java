package com.kalra.docker.client;

import java.util.List;

/**
 * Request payload for the language-service containing a list of country names.
 * The property is named {@code languages} for compatibility with the language-service API.
 */
public record LanguageRequest(List<String> languages) { }
