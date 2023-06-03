package com.cercetare.ecommerce.integration;

import com.cercetare.ecommerce.dao.StateRepositories;
import com.cercetare.ecommerce.entity.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class StateRepositoryTest {

    @Autowired
    private StateRepositories stateRepositories;

    private final Map<String, Integer> statesByCountryIdMap = new HashMap<>();

    private final Map<String, String> statesByCountryCodeMap = new HashMap<>();

    @BeforeEach
    public void setupStates() {
        statesByCountryIdMap.put("Amazonas", 1);
        statesByCountryCodeMap.put("Amazonas", "BR");

        statesByCountryIdMap.put("Tocantins", 1);
        statesByCountryCodeMap.put("Tocantins", "BR");

        statesByCountryIdMap.put("Sergipe", 1);
        statesByCountryCodeMap.put("Sergipe", "BR");

        statesByCountryIdMap.put("Ontario", 2);
        statesByCountryCodeMap.put("Ontario", "CA");

        statesByCountryIdMap.put("Quebec", 2);
        statesByCountryCodeMap.put("Quebec", "CA");

        statesByCountryIdMap.put("Hesse", 3);
        statesByCountryCodeMap.put("Hesse", "DE");

        statesByCountryIdMap.put("Lower Saxony", 3);
        statesByCountryCodeMap.put("Lower Saxony", "DE");

        statesByCountryIdMap.put("Jammu & Kashmir", 4);
        statesByCountryCodeMap.put("Jammu & Kashmir", "IN");

        statesByCountryIdMap.put("Tamil Nadu", 4);
        statesByCountryCodeMap.put("Tamil Nadu", "IN");

        statesByCountryIdMap.put("Bursa", 5);
        statesByCountryCodeMap.put("Bursa", "TR");

        statesByCountryIdMap.put("Giresun", 5);
        statesByCountryCodeMap.put("Giresun", "TR");

        statesByCountryIdMap.put("Kars", 5);
        statesByCountryCodeMap.put("Kars", "TR");

        statesByCountryIdMap.put("Oregon", 6);
        statesByCountryCodeMap.put("Oregon", "US");

        statesByCountryIdMap.put("West Virginia", 6);
        statesByCountryCodeMap.put("West Virginia", "US");

        statesByCountryIdMap.put("Wyoming", 6);
        statesByCountryCodeMap.put("Wyoming", "US");
    }

    @Test
    @DisplayName("Search some states in retrieved list of states by country id")
    void itShouldReturnCorrectStateByCountryId() {
        List<State> retrievedStatesByCountryIdList = stateRepositories.findAll();

        for (Map.Entry<String, Integer> it : statesByCountryIdMap.entrySet()) {
            State retrievedStateByName = retrievedStatesByCountryIdList
                    .stream()
                    .filter(state -> state.getName().equals(it.getKey()))
                    .findFirst()
                    .get();

            assertThat(retrievedStateByName.getCountry().getId()).isEqualTo(it.getValue());
            assertThat(retrievedStateByName).isNotNull();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"BR", "CA", "DE", "IN", "TR", "US"})
    @DisplayName("Search some states in retrieved list of states by country code")
    void itShouldReturnStateByCountryCode(String countryCode) {
        List<State> retrievedStatesByCountryCodeList = stateRepositories.findByCountryCode(countryCode);

        for (Map.Entry<String, String> it : statesByCountryCodeMap.entrySet()) {
            if(it.getValue().equals(countryCode)) {
                State retrievedStateByName = retrievedStatesByCountryCodeList
                        .stream()
                        .filter(state -> state.getName().equals(it.getKey()))
                        .findFirst()
                        .get();

                assertThat(retrievedStateByName).isNotNull();
            }
        }
    }
}
