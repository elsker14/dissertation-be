package com.cercetare.ecommerce.dao;

import com.cercetare.ecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}

/*
Info:
    @RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")

    collectionResourceRel = name of JSON entry
    path = endpoint path la URL : www.blabla.com/product-category

    Adaugam adnotarea cu CrossOrigin in step60 pentru a putea permite lui SpringBoot sa accepte scripturi web cu de la surse cu origine diferita
 */