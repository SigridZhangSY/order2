package com.thoughtworks.api.infrastructure.mybatis.mappers;

import com.thoughtworks.api.infrastructure.records.ProductRecord;

import java.util.Map;

/**
 * Created by syzhang on 7/14/16.
 */
public interface ProductMapper {
    int save(Map info);
    ProductRecord findById(String id);
}
