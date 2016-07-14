package com.thoughtworks.api.infrastructure.repositories;

/**
 * Created by syzhang on 7/14/16.
 */
public class OrderRepository implements com.thoughtworks.api.infrastructure.core.OrderRepository {
    @Override
    public String createOrder(String orderId) {
        return orderId;
    }
}
