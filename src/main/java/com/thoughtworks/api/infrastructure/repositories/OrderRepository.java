package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.mybatis.mappers.ProductMapper;

import javax.inject.Inject;

/**
 * Created by syzhang on 7/14/16.
 */
public class OrderRepository implements com.thoughtworks.api.infrastructure.core.OrderRepository {
    @Inject
    ProductMapper productMapper;

    @Override
    public String createOrder(String orderId) {
        return orderId;
    }

    @Override
    public float getPrice(String productId){
        return productMapper.getPrice(productId);
    }
}
