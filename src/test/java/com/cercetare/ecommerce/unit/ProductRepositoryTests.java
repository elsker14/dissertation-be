package com.cercetare.ecommerce.unit;

import com.cercetare.ecommerce.dao.ProductRepository;
import com.cercetare.ecommerce.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Check if retrieved products are not null")
    void itShouldSelectAllProducts() {
        List<Product> allProducts = productRepository.findAll();

        assertThat(allProducts).isNotNull();
        allProducts.forEach(it -> assertThat(it.getName()).isNotNull());
    }

    @ParameterizedTest
    @ValueSource(longs = {1,2,3,4})
    @DisplayName("Check if retrieved products by category are correct")
    void itShouldSelectOnlyProductsFromEachCategory(Long parameterCategoryId) {
        Page<Product> allProductsFromCategory = productRepository.findByCategoryId(parameterCategoryId, Pageable.ofSize(100));

        allProductsFromCategory.forEach(it -> assertThat(it.getCategory().getId()).isEqualTo(parameterCategoryId));
    }

    @RepeatedTest(10)
    @DisplayName("Check if retrieved products contain the requested string")
    void itShouldReturnExistingProductsStartingWithName() {
        List<String> productNames = new ArrayList<>(Arrays.asList("python", "java", "sql", "coffee", "pad"));
        Random random = new Random();
        int randomIndex = random.nextInt(productNames.size());

        System.out.println("This test checks all returned products contain: " + productNames.get(randomIndex));
        Page<Product> foundProductsStartingWithName = productRepository.findByNameContaining(productNames.get(randomIndex), Pageable.ofSize(100));
        foundProductsStartingWithName.forEach(it -> assertThat(it.getName().toLowerCase().contains(productNames.get(randomIndex))).isTrue());
    }
}
