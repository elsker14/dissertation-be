package com.cercetare.ecommerce.integration;

import com.cercetare.ecommerce.dao.OrderRepository;
import com.cercetare.ecommerce.entity.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @ParameterizedTest
    @ValueSource(strings = {"jianu@yopmail.com", "marian.maricel@email.com", "conasu.iancu@gmail.com", "jianu.iancu@stud.etti.upb.ro"})
    @DisplayName("Check if retrieving orders by email works")
    void itShouldRetrieveOrdersByEmail(String email) {
        Page<Order> ordersByEmail = orderRepository.findByCustomerEmailOrderByDateCreatedDesc(email, Pageable.ofSize(100));

        ordersByEmail.forEach(it -> {
            System.out.println(it.getCustomer().getEmail() + " -> " + it.getId() + " -> " + it.getOrderTrackingNumber());
            assertThat(it).isNotNull();
        });
    }
}
