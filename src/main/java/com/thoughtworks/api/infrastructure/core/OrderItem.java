package com.thoughtworks.api.infrastructure.core;

/**
 * Created by syzhang on 7/14/16.
 */
public interface OrderItem {
    String getProductId();
    int getQuantity();
    float getAmount();
}
