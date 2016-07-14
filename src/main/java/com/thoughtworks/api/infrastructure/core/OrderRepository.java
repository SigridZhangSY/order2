package com.thoughtworks.api.infrastructure.core;

/**
 * Created by syzhang on 7/14/16.
 */
public interface OrderRepository {
    String createOrder(String orderId);
    public float getPrice(String productId);
}
