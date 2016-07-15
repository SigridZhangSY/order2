package com.thoughtworks.api.infrastructure.resources;

import com.thoughtworks.api.infrastructure.core.*;
import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;


/**
 * Created by syzhang on 7/14/16.
 */

@RunWith(ApiTestRunner.class)
public class UserResourceTest extends ApiSupport {

    @Inject
    UserRepository userRepository;
    @Inject
    ProductRepository productRepository;
    @Inject
    OrderRepository orderRepository;
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void should_return_201_when_create_user_with_specified_parameter(){
        Map<String, Object> map = new HashMap();
        map.put("name", "sdcc");
        Response post = post("/users", map);
        assertThat(post.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
    }

    @Test
    public void should_return_400_when_user_exists(){
        Map<String, Object> map = new HashMap();
        map.put("name", "xxx");

        Response post = post("/users", map);
        post = post("/users", map);
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }

    @Test
    public void should_return_400_when_name_is_null(){
        Map<String, Object> map = new HashMap();
        Response post = post("/users", map);
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }

    @Test
    public void should_201_when_create_order_with_specified_parameter(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        String productId = product.getId();
        Order order = orderRepository.createOrder(TestHelper.order("kayla", productId), userId);


        Response post = post("/users/" + userId + "/orders", TestHelper.order("kayla", productId));
        assertThat(post.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
    }

    @Test
    public void should_return_400_when_create_order_for_no_exists_user(){
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        String productId = product.getId();
        Response post = post("/users/111/orders", TestHelper.order("kayla", productId));
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));

    }
}
