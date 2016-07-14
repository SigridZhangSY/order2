package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.*;
import com.thoughtworks.api.infrastructure.records.ProductRecord;
import com.thoughtworks.api.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by syzhang on 7/13/16.
 */

@RunWith(DatabaseTestRunner.class)
public class ProductRepositoryTest {
    @Inject
    ProductRepository productRepository;

    @Test
    public void should_create_and_get_product(){

        Map map = new HashMap<String, Object>();
        map.put("name", "apple");
        map.put("description", "red apple");
        map.put("price", 1.1);
        Product product = productRepository.createProduct(map);

        assertThat(product.getName(), is("apple"));
        assertThat(product.getDescription(), is("red apple"));
        assertEquals(product.getPrice(), 1.1, 0.01);

    }

    @Test
    public void should_get_all_products(){
        Map map = new HashMap<String, Object>();
        map.put("name", "apple");
        map.put("description", "red apple");
        map.put("price", 1.1);
        productRepository.createProduct(map);

        Product product = productRepository.find().get(0);
        assertThat(product.getName(), is("apple"));
        assertThat(product.getDescription(), is("red apple"));
        assertEquals(product.getPrice(), 1.1, 0.01);
    }
}
