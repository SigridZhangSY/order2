package com.thoughtworks.api.infrastructure.mybatis.mappers;

import com.thoughtworks.api.infrastructure.core.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by syzhang on 7/14/16.
 */
public interface ProductMapper {
    int save(Map info);

    Product findById(String id);

    List<Product> findProducts();
}
