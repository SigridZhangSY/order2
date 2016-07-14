package com.thoughtworks.api.infrastructure.core;

import java.util.List;
import java.util.Map;

/**
 * Created by syzhang on 7/13/16.
 */
public interface ProductRepository {
    Product createProduct(Map info);
    List<Product> find();
}
