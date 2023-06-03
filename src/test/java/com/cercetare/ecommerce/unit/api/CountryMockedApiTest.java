package com.cercetare.ecommerce.unit.api;

import com.cercetare.ecommerce.SpringBootEcommerceApplication;
import com.cercetare.ecommerce.dao.CountryRepository;
import com.cercetare.ecommerce.entity.Country;
import com.cercetare.ecommerce.utils.Endpoint;
import com.cercetare.ecommerce.utils.RandomGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.cercetare.ecommerce.utils.RandomGenerator.generateInvalidIndexOver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = SpringBootEcommerceApplication.class)
@AutoConfigureMockMvc
@Transactional
public class CountryMockedApiTest {

    private static final String APPLICATION_JSON = "application/hal+json";

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private MockMvc mockMvc;

    private List<Country> allCountries;

    @BeforeEach
    public void setupBeforeEachTest() {
        allCountries = countryRepository.findAll();
    }

    @Test
    @DisplayName("Send mocked api request: `findAll` countries")
    public void findAllCountriesRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.ALL_COUNTRIES.getPath()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$._embedded.countries", hasSize(allCountries.size())))
                .andExpect(jsonPath("$.page.totalElements", is(allCountries.size())));
    }

    @Test
    @DisplayName("Send mocked api request: `findById` with valid id")
    public void findRandomCountryRequest() throws Exception {
        int randomIndex = RandomGenerator.generateIndexBetweenOneAnd(allCountries.size());
        Optional<Country> randomCountry = countryRepository.findById(randomIndex);

        randomCountry.ifPresent(value -> assertThat(value).isNotNull());
        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.COUNTRY_BY_ID.getPath(), randomIndex))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(randomCountry.get().getId())))
                .andExpect(jsonPath("$.code", is(randomCountry.get().getCode())))
                .andExpect(jsonPath("$.name", is(randomCountry.get().getName())));
    }

    @Test
    @DisplayName("Send mocked api request: `findById` with invalid id")
    public void findNonExistentCountryRequest() throws Exception {
        int randomIndex = generateInvalidIndexOver(allCountries.size());

        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.COUNTRY_BY_ID.getPath(), randomIndex))
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Send mocked api request: `findById` with wrong format id")
    public void findCountryById_InvalidIdFormat() throws Exception {
        String invalidId = "abc";

        mockMvc.perform(MockMvcRequestBuilders.get(Endpoint.COUNTRY_BY_ID.getPath(), invalidId))
                .andExpect(status().is5xxServerError())
                .andExpect(status().isInternalServerError());
    }

}
