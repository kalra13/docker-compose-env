package com.kalra.docker.db;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends ListCrudRepository<LanguageEntity, String> {

    @Override
    List<LanguageEntity> findAll();


    List<LanguageEntity> findAllByCountryIn(List<String> country);
}


