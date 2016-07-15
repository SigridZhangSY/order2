package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.Order;
import com.thoughtworks.api.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.api.infrastructure.mybatis.mappers.ProductMapper;
import com.thoughtworks.api.infrastructure.records.OrderItemRecord;
import com.thoughtworks.api.infrastructure.records.OrderRecord;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by syzhang on 7/14/16.
 */
public class OrderRepository implements com.thoughtworks.api.infrastructure.core.OrderRepository {
    @Inject
    ProductMapper productMapper;
    @Inject
    OrderMapper orderMapper;
    @Override
    public Order createOrder(Map<String, Object> info, String userId){
        float totalPrice = 0;
        String orderId = nextIdentity();
        List<Map<String, Object>> items = (List<Map<String, Object>>)info.get("order_items");
        for(int i = 0; i < items.size(); i++){
            items.get(i).put("order_id", orderId);
            float price = getPrice(String.valueOf(items.get(i).get("product_id")));
            totalPrice += price * Integer.valueOf(String.valueOf(items.get(i).get("quantity")));
            items.get(i).put("amount", price);
            items.get(i).put("id", nextIdentity());
        }

        info.put("id", orderId);
        info.put("user_id", userId);
        info.put("total_price", totalPrice);
//        orderMapper.saveOrder(info);
//        orderMapper.saveOrderItem(items);

        System.out.println(orderMapper.saveOrder(info));
        System.out.println(orderMapper.saveOrderItem(items));

        return orderMapper.findById(orderId);
    }


    @Override
    public float getPrice(String productId){
        return productMapper.getPrice(productId);
    }

    private String nextIdentity() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
