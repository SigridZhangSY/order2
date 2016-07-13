package com.thoughtworks.api.infrastructure.records;

import com.thoughtworks.api.infrastructure.core.Product;
import com.thoughtworks.api.web.jersey.Routes;

import java.util.Map;

/**
 * Created by syzhang on 7/13/16.
 */
public class ProductRecord implements Product {
    private String id;
    private String name;
    private String description;
    private float price;

    public ProductRecord(Map info){
        this.id = String.valueOf(info.get("productId"));
        this.name = String.valueOf(info.get("name"));
        this.description = String.valueOf(info.get("description"));
        this.price = Float.valueOf(String.valueOf(info.get("price")));
    }

    @Override
    public String getId() {
        return this.id;
    }

}
