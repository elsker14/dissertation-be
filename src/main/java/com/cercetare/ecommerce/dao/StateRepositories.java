package com.cercetare.ecommerce.dao;

import com.cercetare.ecommerce.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "states", path = "states")
public interface StateRepositories extends JpaRepository<State, Integer> {

    List<State> findByCountryCode(@Param("code") String code);
}
