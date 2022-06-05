package com.cercetare.ecommerce.dao;

import com.cercetare.ecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}

/*
Info:
    @RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")

    collectionResourceRel = name of JSON entry
    path = endpoint path la URL : www.blabla.com/product-category
 */