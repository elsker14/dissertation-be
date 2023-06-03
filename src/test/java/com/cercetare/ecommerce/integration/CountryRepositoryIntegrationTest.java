package com.cercetare.ecommerce.integration;

import com.cercetare.ecommerce.SpringBootEcommerceApplication;
import com.cercetare.ecommerce.dao.CountryRepository;
import com.cercetare.ecommerce.entity.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = SpringBootEcommerceApplication.class)
@Transactional
public class CountryRepositoryIntegrationTest {

    private static final String NON_EXISTENT_COUNTRY_NAME = "Romania";
    private static final String NON_EXISTENT_COUNTRY_CODE = "RO";

    @Autowired
    private CountryRepository countryRepository;

    private List<Country> allCountries;

    @BeforeEach
    public void setupBeforeEachTest() {
        allCountries = countryRepository.findAll();
        assertThat(allCountries)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    @DisplayName("Retrieve all countries from Db")
    public void itShouldRetrieveAllCountriesFromDb() {
        assertThat(allCountries)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThan(0);
        allCountries.forEach(it -> assertThat(it).isNotNull());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "src/test/resources/data/countries_names_test_data.csv", delimiter = ',')
    @DisplayName("Find country by name in Db")
    public void itShouldFindCountryByNameInDb(String countryNameParam) {
        assertThat(countryNameParam)
                .isNotNull()
                .isNotEmpty();

        Optional<Country> retrievedCountryByName = allCountries
                .stream()
                .filter(country -> country.getName().equals(countryNameParam))
                .findFirst();

        retrievedCountryByName
                .ifPresent(country -> assertThat(country.getName()).isEqualTo(countryNameParam));
    }

    @Test
    @DisplayName("Find non-existent country by name in Db")
    public void itShouldNotFindCountryByNameInDb() {
        Optional<Country> retrievedCountryByName = allCountries
                .stream()
                .filter(country -> country.getName().equals(NON_EXISTENT_COUNTRY_NAME))
                .findFirst();

        assertThat(retrievedCountryByName)
                .isEmpty();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "src/test/resources/data/countries_codes_test_data.csv", delimiter = ',')
    @DisplayName("Find country by code in Db")
    public void itShouldFindCountryByCodeInDb(String codeParam) {
        assertThat(codeParam)
                .isNotNull()
                .isNotEmpty();

        Optional<Country> retrievedCountryByCode = allCountries
                .stream()
                .filter(country -> country.getName().equals(codeParam))
                .findFirst();

        retrievedCountryByCode
                .ifPresent(country -> assertThat(country.getCode()).isEqualTo(codeParam));
    }

    @Test
    @DisplayName("Find non-existent country by code in Db")
    public void itShouldNotFindCountryByCodeInDb() {
        Optional<Country> retrievedCountryByCode = allCountries
                .stream()
                .filter(country -> country.getCode().equals(NON_EXISTENT_COUNTRY_CODE))
                .findFirst();

        assertThat(retrievedCountryByCode)
                .isEmpty();
    }

    @ParameterizedTest
    @MethodSource("generateCountriesRange")
    @DisplayName("Find country by ids between given range in Db")
    public void itShouldFindCountryByIdInDb(int countryId) {
        Optional<Country> retrievedCountryByIdInDb = countryRepository.findById(countryId);

        assertThat(retrievedCountryByIdInDb)
                .isNotEmpty();
    }

    @Test
    @DisplayName("Find non-existent country by id in Db")
    public void itShouldNotFindCountryByIdInDb() {
        Optional<Country> retrievedCountryByIdInDb = countryRepository.findById(new Random().nextInt(100) + allCountries.size());

        assertThat(retrievedCountryByIdInDb)
                .isEmpty();
    }

    private IntStream generateCountriesRange() {
        return IntStream.rangeClosed(1, this.allCountries.size());
    }
}
