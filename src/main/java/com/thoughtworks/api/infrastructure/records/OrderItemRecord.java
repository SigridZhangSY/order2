package com.thoughtworks.api.infrastructure.records;

import com.thoughtworks.api.infrastructure.core.OrderItem;

import java.util.Map;

/**
 * Created by syzhang on 7/14/16.
 */
public class OrderItemRecord implements OrderItem {
    private String id;
    private String productId;
    private int quantity;
    private float amount;

    public OrderItemRecord(Map<String, Object> map){
        this.id = String.valueOf(map.get("id"));
        this.productId = String.valueOf(map.get("product_id"));
        this.quantity = Integer.valueOf(String.valueOf(map.get("quantity")));
        this.amount = Integer.valueOf(String.valueOf(map.get("amount")));
    }
}
