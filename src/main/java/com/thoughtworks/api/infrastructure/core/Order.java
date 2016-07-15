package com.thoughtworks.api.infrastructure.core;

import com.thoughtworks.api.infrastructure.records.OrderItemRecord;

import java.util.List;

/**
 * Created by syzhang on 7/14/16.
 */
public interface Order {
     String getId();
     String getName();
     String getAddress();
     String getPhone();
     float getTotalPrice();
     String getTime();
     List<OrderItemRecord> getItems();
}
