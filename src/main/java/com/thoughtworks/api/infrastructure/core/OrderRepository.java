package com.thoughtworks.api.infrastructure.core;

import java.util.Map;

/**
 * Created by syzhang on 7/14/16.
 */
public interface OrderRepository {
    Order createOrder(Map<String, Object> info, String userId);
    public float getPrice(String productId);
}
