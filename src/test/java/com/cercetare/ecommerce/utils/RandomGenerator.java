package com.cercetare.ecommerce.utils;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class RandomGenerator {

    public static int generateIndexBetweenOneAnd(int highBoundary) {
        int randomIndex = new Random().nextInt(highBoundary) + 1;
        assertThat(randomIndex)
                .isGreaterThanOrEqualTo(1)
                .isLessThanOrEqualTo(highBoundary);

        return randomIndex;
    }

    public static int generateInvalidIndexOver(int highBoundary) {
        return new Random().nextInt(100) + highBoundary;
    }
}
