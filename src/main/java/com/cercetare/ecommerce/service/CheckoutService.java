package com.cercetare.ecommerce.service;

import com.cercetare.ecommerce.dtos.PaymentInfo;
import com.cercetare.ecommerce.dtos.Purchase;
import com.cercetare.ecommerce.dtos.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
