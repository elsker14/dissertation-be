package com.cercetare.ecommerce.unit.dao;

import com.cercetare.ecommerce.SpringBootEcommerceApplication;
import com.cercetare.ecommerce.dao.CountryRepository;
import com.cercetare.ecommerce.entity.Country;
import com.cercetare.ecommerce.utils.RandomGenerator;
import com.cercetare.ecommerce.utils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = SpringBootEcommerceApplication.class)
public class CountryMockedRepositoryTest {

    @MockBean
    private CountryRepository countryRepository;

    private List<Country> countryMockTestData;

    @BeforeEach
    public void setupBeforeEachTest() {
        countryMockTestData = TestData.readCountriesFromCsv("src/test/resources/data/unit/countries_test_data.csv");
    }

    @Test
    @DisplayName("Mock all countries")
    public void itShouldMockFindAll() {
        when(countryRepository.findAll())
                .thenReturn(countryMockTestData);

        List<Country> countryDb = countryRepository.findAll();

        assertThat(countryMockTestData).hasSameSizeAs(countryDb);
        for (int i = 0; i < countryMockTestData.size(); i++) {
            assertThat(countryMockTestData.get(i)).isNotNull()
                    .isEqualTo(countryDb.get(i)).isNotNull();
        }

        verify(countryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Mock country by id")
    public void itShouldMockFindById() {
        int randomIndex = RandomGenerator.generateIndexBetweenOneAnd(countryMockTestData.size());

        when(countryRepository.findById(anyInt()))
                .thenReturn(Optional.ofNullable(countryMockTestData.get(randomIndex)));

        Optional<Country> countryById = countryRepository.findById(randomIndex);

        assertThat(countryById)
                .isPresent()
                .isNotNull();
        assertThat(countryById.get().getId()).isEqualTo(countryMockTestData.get(randomIndex).getId());
        assertThat(countryById.get().getName()).isEqualTo(countryMockTestData.get(randomIndex).getName());
        assertThat(countryById.get().getCode()).isEqualTo(countryMockTestData.get(randomIndex).getCode());

        verify(countryRepository, times(1)).findById(anyInt());
    }
}
