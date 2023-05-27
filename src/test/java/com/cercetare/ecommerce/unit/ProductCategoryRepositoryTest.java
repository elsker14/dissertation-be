package com.cercetare.ecommerce.unit;

import com.cercetare.ecommerce.dao.ProductCategoryRepository;
import com.cercetare.ecommerce.entity.ProductCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    @DisplayName("Retrieve all product categories and check existence")
    void itShouldRetrieveAllProductCategories() {
        List<ProductCategory> allProductCategoriesList = productCategoryRepository.findAll();

        assertFalse(allProductCategoriesList.isEmpty());
        assertThat(allProductCategoriesList).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Books", "Coffee Mugs", "Mouse Pads", "Luggage Tags"})
    @DisplayName("Check if parameter categories can be found in the list")
    void itShouldContainFollowingCategories(String category) {
        List<ProductCategory> allProductCategoriesList = productCategoryRepository.findAll();

        ProductCategory retrievedProductCategory = allProductCategoriesList
                .stream()
                .filter(pc -> pc.getCategoryName().equals(category))
                .findFirst()
                .get();

        assertThat(retrievedProductCategory).isNotNull();
    }
}
