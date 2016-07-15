package com.thoughtworks.api.infrastructure.records;

import com.thoughtworks.api.infrastructure.core.Payment;
import com.thoughtworks.api.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by syzhang on 7/15/16.
 */
public class PaymentRecord implements Payment, Record{
    private String orderId;
    private String payType;
    private float amount;
    private String userId;

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        Map<String, Object> map = new HashMap<>();
        map.put("order_uri", routes.order(userId, orderId));
        map.put("uri", routes.payment(userId, PaymentRecord.this));
        map.put("payment_type", payType);
        map.put("amount", amount);

        return map;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }
}
