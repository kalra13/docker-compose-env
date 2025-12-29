package com.kalra.docker.presentation;

/**
 * DTO returned by country-service summarizing the number of languages per country.
 *
 * @param country       country name
 * @param languageCount number of languages for the country
 */
public record CountryLanguageCountResponse(String country, int languageCount) { }
