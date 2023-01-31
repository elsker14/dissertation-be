package com.cercetare.ecommerce.unit;

import com.cercetare.ecommerce.dao.CountryRepositories;
import com.cercetare.ecommerce.entity.Country;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CountryRepositoryTests {

    @Autowired
    private CountryRepositories countryRepositories;

    private final Map<String, String> countriesByCodeMap = new HashMap<>();

    @BeforeEach
    public void setupCountries() {
        countriesByCodeMap.put("BR", "Brazil");
        countriesByCodeMap.put("CA", "Canada");
        countriesByCodeMap.put("DE", "Germany");
        countriesByCodeMap.put("IN", "India");
        countriesByCodeMap.put("TR", "Turkey");
        countriesByCodeMap.put("US", "United States");
    }

    @Test
    @DisplayName("Retrieve all countries and check if they exist")
    void itShouldRetrieveAllCountriesAndCheckExistence() {
        List<Country> allCountries = countryRepositories.findAll();

        allCountries.forEach(it -> assertThat(it).isNotNull());
        assertThat(allCountries).isNotNull();
    }

    @Test
    @DisplayName("Check if some countries can be found in retrieved list")
    void itShouldContainCountry() {
        List<Country> allCountries = countryRepositories.findAll();

        for (Map.Entry<String, String> it : countriesByCodeMap.entrySet()) {
            Country retrievedCountryByCode = allCountries
                    .stream()
                    .filter(country -> country.getCode().equals(it.getKey()))
                    .findFirst()
                    .get();

            assertThat(retrievedCountryByCode.getName().equals(it.getValue()));
        }
    }
}
