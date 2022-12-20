package com.cercetare.ecommerce.service;

import com.cercetare.ecommerce.dtos.Purchase;
import com.cercetare.ecommerce.dtos.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
