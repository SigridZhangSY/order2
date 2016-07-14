package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.Product;
import com.thoughtworks.api.infrastructure.core.OrderRepository;
import com.thoughtworks.api.infrastructure.core.ProductRepository;
import com.thoughtworks.api.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by syzhang on 7/14/16.
 */

@RunWith(DatabaseTestRunner.class)
public class OrderRepositoryTest {

    @Inject
    OrderRepository orderRepository;
    @Inject
    ProductRepository productRepository;

    @Test
    public void should_get_product_price(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "apple");
        map.put("description", "red apple");
        map.put("price", 1.1);
        Product product_save = productRepository.createProduct(map);

        assertEquals(orderRepository.getPrice(product_save.getId()), 1.1, 0.01);
    }
}
