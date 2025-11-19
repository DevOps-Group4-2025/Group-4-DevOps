package com.napier.devops.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for CountryLanguage entities
 */
public interface CountryLanguageRepository extends CrudRepository<com.napier.devops.model.CountryLanguage, com.napier.devops.model.CountryLanguageId>, CountryLanguageRepositoryCustom {

}
