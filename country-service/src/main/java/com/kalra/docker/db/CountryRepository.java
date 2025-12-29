package com.kalra.docker.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data repository for {@link CountryEntity} records.
 */
@Repository
public interface CountryRepository extends CrudRepository<CountryEntity, Long> {

    @Override
    List<CountryEntity> findAll();
}
