package com.kalra.docker;

import com.kalra.docker.db.LanguageEntity;
import com.kalra.docker.db.LanguageRepository;
import com.kalra.docker.presentation.LanguageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Application service for language lookups.
 * <p>
 * Fetches language rows from the database and groups them by country
 * to produce presentation responses.
 */
@Slf4j
@Component
public class LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }


    /**
     * Returns languages for the given list of countries.
     *
     * @param countries country names
     * @return list of {@link com.kalra.docker.presentation.LanguageResponse} grouped per country
     */
    public List<LanguageResponse>  getLanguages(List<String> countries) {
        log.info("Fetching languages for countries from db: {}", countries);
        List<LanguageEntity> entities = languageRepository.findAllByCountryIn(countries);

        return entities.stream()
                .collect(Collectors.groupingBy(LanguageEntity::getCountry))
                .entrySet()
                .stream()
                .map(e -> new LanguageResponse(e.getKey(), e.getValue().stream().map(LanguageEntity::getName).toList()))
                .toList();
    }



}
