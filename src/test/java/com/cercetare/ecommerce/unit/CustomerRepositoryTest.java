package com.cercetare.ecommerce.unit;

import com.cercetare.ecommerce.dao.CustomerRepository;
import com.cercetare.ecommerce.entity.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Check if retrieved customer list and values are not null")
    void itShouldRetrieveNotNullCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();

        allCustomers.forEach(it -> {
            assertFalse(it.getEmail().isEmpty());
            assertFalse(it.getFirstName().isEmpty());
            assertFalse(it.getLastName().isEmpty());
        });
        assertThat(allCustomers).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"jianu@yopmail.com", "marian.maricel@email.com", "conasu.iancu@gmail.com"})
    @DisplayName("Check if customer retrieval by email works")
    void itShouldRetrieveExistingCustomerByEmail(String email) {
        Customer retrievedCustomer = customerRepository.findByEmail(email);

        assertThat(retrievedCustomer).isNotNull();
    }
}
