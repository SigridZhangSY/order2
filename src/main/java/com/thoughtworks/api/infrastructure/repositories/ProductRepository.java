package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.Product;
import com.thoughtworks.api.infrastructure.records.ProductRecord;

import java.util.Map;
import java.util.UUID;

/**
 * Created by syzhang on 7/13/16.
 */
public class ProductRepository implements com.thoughtworks.api.infrastructure.core.ProductRepository {
    public Product creatProduct(Map info){
        info.put("productId", nextIdentity());

        return new ProductRecord(info);
    }

    private String nextIdentity() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
