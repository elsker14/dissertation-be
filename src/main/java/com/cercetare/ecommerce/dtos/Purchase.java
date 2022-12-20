package com.cercetare.ecommerce.dtos;

import com.cercetare.ecommerce.entity.Address;
import com.cercetare.ecommerce.entity.Customer;
import com.cercetare.ecommerce.entity.Order;
import com.cercetare.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
