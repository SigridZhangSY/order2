package com.thoughtworks.api.infrastructure.core;

import java.util.Map;

/**
 * Created by syzhang on 7/14/16.
 */
public interface OrderRepository {
    Order createOrder(Map<String, Object> info, String userId);
    float getPrice(String productId);
    Order findById(String orderId);
}
