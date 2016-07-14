package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.*;
import com.thoughtworks.api.infrastructure.records.ProductRecord;
import com.thoughtworks.api.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        Map<String, Object> map = new HashMap<String, Object>();
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
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "apple");
        map.put("description", "red apple");
        map.put("price", 1.1);
        productRepository.createProduct(map);

        Product product = productRepository.find().get(0);
        assertThat(product.getName(), is("apple"));
        assertThat(product.getDescription(), is("red apple"));
        assertEquals(product.getPrice(), 1.1, 0.01);
    }

    @Test
    public void should_get_a_product(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "apple");
        map.put("description", "red apple");
        map.put("price", 1.1);
        Product product_save = productRepository.createProduct(map);

        Product product_get = productRepository.findById(product_save.getId()).orElseThrow(() -> new NotFoundException("User not found"));;
        assertThat(product_get.getName(), is("apple"));
        assertThat(product_get.getDescription(), is("red apple"));
        assertEquals(product_get.getPrice(), 1.1, 0.01);
    }
}
