package com.kalra.docker;

import com.kalra.docker.client.LanguageClient;
import com.kalra.docker.client.LanguageResponse;
import com.kalra.docker.db.CountryEntity;
import com.kalra.docker.db.CountryRepository;
import com.kalra.docker.presentation.CountryLanguageCountResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
/**
 * Application service for country-related operations.
 * <p>
 * Provides methods to fetch countries from the database and to aggregate
 * language counts for each country by delegating to the {@link com.kalra.docker.client.LanguageClient}.
 */
public class CountryService {

    private final CountryRepository countryRepository;
    private final LanguageClient languageClient;

    public CountryService(CountryRepository countryRepository, LanguageClient languageClient) {
        this.countryRepository = countryRepository;
        this.languageClient = languageClient;
    }

    /**
     * Returns all country names persisted in the database.
     *
     * @return list of country names
     */
    public List<String> getAllCountries() {
        log.info("Fetching all countries from db");
        return countryRepository.findAll().stream().map(CountryEntity::getName).toList();
    }

    /**
     * Computes language counts for each country by calling the language-service.
     *
     * @return list of summary rows containing country and language count
     */
    public List<CountryLanguageCountResponse> getCountryLanguageSummaries() {

        List<String> allCountries = this.getAllCountries();
        log.info("Calling LanguageClient to get languages: {}", allCountries);
        List<LanguageResponse> languages = languageClient.getLanguages(allCountries);

        log.info("Received languages: {}", languages);
        return languages.stream()
                .map(l -> new CountryLanguageCountResponse(l.country(), l.languages().size()))
                .toList();
    }
}
