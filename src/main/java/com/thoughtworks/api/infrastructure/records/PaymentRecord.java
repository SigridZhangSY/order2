package com.thoughtworks.api.infrastructure.records;

import com.thoughtworks.api.infrastructure.core.Payment;

/**
 * Created by syzhang on 7/15/16.
 */
public class PaymentRecord implements Payment {
    private String orderId;
    private String payType;
    private float amount;

    @Override
    public String getOrderId() {
        return orderId;
    }
}
