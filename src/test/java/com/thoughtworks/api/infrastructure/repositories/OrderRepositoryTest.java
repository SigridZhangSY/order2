package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.*;
import com.thoughtworks.api.infrastructure.core.OrderRepository;
import com.thoughtworks.api.infrastructure.core.ProductRepository;
import com.thoughtworks.api.support.DatabaseTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @Inject
    UserRepository userRepository;

    @Test
    public void should_get_product_price(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "apple");
        map.put("description", "red apple");
        map.put("price", 1.1);
        Product product_save = productRepository.createProduct(map);

        assertEquals(orderRepository.getPrice(product_save.getId()), 1.1, 0.01);
    }

    @Test
    public void should_save_a_order(){
//        Map<String, Object> map = new HashMap();
//        map.put("name", "sdcc");
//        User user = userRepository.createUser(TestHelper.user("sdcc"));
//        String userId = user.getId();
//        Map<String, Object> map2 = new HashMap<String, Object>();
//        map2.put("name", "apple");
//        map2.put("description", "red apple");
//        map2.put("price", 1.1);
//        Product product = productRepository.createProduct(TestHelper.product("apple"));
//        String productId = product.getId();
//
//        Map orderMap = new HashMap<String, Object>();
//        orderMap.put("name", "kayla");
//        orderMap.put("address", "beijing");
//        orderMap.put("phone", "12300000000");
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        Map orderIterm1 = new HashMap<String, Object>();
//        orderIterm1.put("product_id", productId);
//        orderIterm1.put("quantity", 2);
//        list.add(orderIterm1);
//        orderMap.put("orderItem", list);


        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        String productId = product.getId();
        Order order = orderRepository.createOrder(TestHelper.order("kayla", productId), userId);

        assertThat(order.getName(), is("kayla"));
    }

    @Test
    public void should_find_order_for_user(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        String productId = product.getId();
        Order order = orderRepository.createOrder(TestHelper.order("kayla", productId), userId);

        List<Order> order_res = orderRepository.getOrdersForUser(userId);
        assertEquals(order_res.size(), 1);
        assertThat(order_res.get(0).getName(), is("kayla"));
    }

    @Test
    public void should_get_order_details(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        String productId = product.getId();
        Order order = orderRepository.createOrder(TestHelper.order("kayla", productId), userId);

        Order order_res = orderRepository.getOrderDetails(order.getId()).orElseThrow(() -> new NotFoundException("Order not found"));
        assertThat(order_res.getName(), is("kayla"));

    }

    @Test
    public void should_save_and_get_payment(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        String productId = product.getId();
        Order order = orderRepository.createOrder(TestHelper.order("kayla", productId), userId);
        Payment payment = orderRepository.createPayment(TestHelper.payment(), order.getId());

        assertThat(payment.getOrderId(), is(order.getId()));
    }

    @Test
    public void should_find_payment(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        String productId = product.getId();
        Order order = orderRepository.createOrder(TestHelper.order("kayla", productId), userId);
        Payment payment = orderRepository.createPayment(TestHelper.payment(), order.getId());

        Payment payment_res = orderRepository.findPaymentById(order.getId()).orElseThrow(() -> new NotFoundException("Payment not found"));
        assertThat(payment_res.getOrderId(), is(order.getId()));
    }
}
