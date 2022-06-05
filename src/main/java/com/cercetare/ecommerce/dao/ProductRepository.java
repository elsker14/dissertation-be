package com.cercetare.ecommerce.dao;

import com.cercetare.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {

}

/*
Info:
    Adaugam adnotarea cu CrossOrigin in step60 pentru a putea permite lui SpringBoot sa accepte scripturi web cu de la surse cu origine diferita
 */