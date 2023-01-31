package com.cercetare.ecommerce.dao;

import com.cercetare.ecommerce.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "countries", path = "countries")
public interface CountryRepositories extends JpaRepository<Country, Integer> {
}
