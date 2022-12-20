package com.cercetare.ecommerce.dtos;

import lombok.Data;
import lombok.NonNull;

@Data
public class PurchaseResponse {

    // @Data = creeaza constructor doar pt parametri finali, deci putem proceda in 2 moduri
//    private final String orderTrackingNumber;

    @NonNull
    private String orderTrackingNumber;
}
