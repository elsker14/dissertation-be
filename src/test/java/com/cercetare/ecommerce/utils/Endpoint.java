package com.cercetare.ecommerce.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Endpoint {
    ALL_COUNTRIES("/api/countries"),
    COUNTRY_BY_ID("/api/countries/{id}");

    private final String path;
}
