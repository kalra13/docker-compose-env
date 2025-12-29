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
public class CountryService {

    private final CountryRepository countryRepository;
    private final LanguageClient languageClient;

    public CountryService(CountryRepository countryRepository, LanguageClient languageClient) {
        this.countryRepository = countryRepository;
        this.languageClient = languageClient;
    }

    public List<String> getAllCountries() {
        log.info("Fetching all countries from db");
        return countryRepository.findAll().stream().map(CountryEntity::getName).toList();
    }

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
