package com.cercetare.ecommerce.unit;

import com.cercetare.ecommerce.dao.StateRepositories;
import com.cercetare.ecommerce.entity.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class StateRepositoryTests {

    @Autowired
    private StateRepositories stateRepositories;

    private final Map<String, Integer> statesByCountryIdMap = new HashMap<>();

    @BeforeEach
    public void setupStates() {
        statesByCountryIdMap.put("Amazonas", 1);
        statesByCountryIdMap.put("Tocantins", 1);
        statesByCountryIdMap.put("Sergipe", 1);
        statesByCountryIdMap.put("Ontario", 2);
        statesByCountryIdMap.put("Quebec", 2);
        statesByCountryIdMap.put("Hesse", 3);
        statesByCountryIdMap.put("Lower Saxony", 3);
        statesByCountryIdMap.put("Jammu & Kashmir", 4);
        statesByCountryIdMap.put("Tamil Nadu", 4);
        statesByCountryIdMap.put("Bursa", 5);
        statesByCountryIdMap.put("Giresun", 5);
        statesByCountryIdMap.put("Kars", 5);
        statesByCountryIdMap.put("Oregon", 6);
        statesByCountryIdMap.put("West Virginia", 6);
        statesByCountryIdMap.put("Wyoming", 6);
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
}
