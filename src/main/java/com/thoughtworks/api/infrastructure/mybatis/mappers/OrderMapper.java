package com.thoughtworks.api.infrastructure.mybatis.mappers;

import com.thoughtworks.api.infrastructure.core.Order;
import com.thoughtworks.api.infrastructure.records.OrderRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by syzhang on 7/15/16.
 */
public interface OrderMapper {
    int saveOrder(Map<String, Object> info);
    int saveOrderItem(List<Map<String, Object>> info);
    Order findById(String orderId);
}
