package com.thoughtworks.api.infrastructure.core;

import com.thoughtworks.api.infrastructure.records.OrderRecord;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by syzhang on 7/14/16.
 */
public interface OrderRepository {
    Order createOrder(Map<String, Object> info, String userId);
    float getPrice(String productId);
    List<Order> getOrdersForUser(String userId);
    Optional<OrderRecord> getOrderDetails(String orderId);
    Payment createPayment(Map<String, Object> info, String orderId);
    Optional<Payment> findPaymentById(String orderId);
}
