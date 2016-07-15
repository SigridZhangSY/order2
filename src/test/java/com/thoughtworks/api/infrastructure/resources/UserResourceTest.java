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
import static org.junit.Assert.assertEquals;
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

    @Test
    public void should_return_400_when_create_order_with_name_is_null(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        String productId = product.getId();
        Map orderMap = new HashMap<String, Object>();
        orderMap.put("address", "beijing");
        orderMap.put("phone", "12300000000");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map orderIterm1 = new HashMap<String, Object>();
        orderIterm1.put("product_id", productId);
        orderIterm1.put("quantity", 2);
        list.add(orderIterm1);
        orderMap.put("order_items", list);

        Response post = post("/users/" + userId + "/orders", orderMap);
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));

    }

    @Test
    public void should_return_400_when_no_product_exits(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Map orderMap = new HashMap<String, Object>();
        orderMap.put("address", "beijing");
        orderMap.put("phone", "12300000000");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map orderIterm1 = new HashMap<String, Object>();
        orderIterm1.put("product_id", "111");
        orderIterm1.put("quantity", 2);
        list.add(orderIterm1);
        orderMap.put("order_items", list);

        Response post = post("/users/" + userId + "/orders", orderMap);
        assertThat(post.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }

    @Test
    public void should_return_details_when_get_all_orders(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        String productId = product.getId();
        Order order = orderRepository.createOrder(TestHelper.order("kayla", productId), userId);

        Response get = get("/users/" + userId + "/orders");
        assertThat(get.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
        final List<Map<String, Object>> res = get.readEntity(List.class);
        assertEquals(1, res.size());
        assertThat(res.get(0).get("uri"), is("/users/" + userId + "/orders/" + order.getId()));

    }

    @Test
    public void should_return_400_when_get_orders_for_no_exist_user(){
        Response get = get("/users/111/orders");
        assertThat(get.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }

    @Test
    public void should_return_detail_when_find_order(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        String productId = product.getId();
        Order order = orderRepository.createOrder(TestHelper.order("kayla", productId), userId);
        String orderId = order.getId();

        Response get = get("/users/" + userId + "/orders/" + orderId);
        assertThat(get.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
        final Map<String, Object> res = get.readEntity(Map.class);
        assertThat(res.get("name"), is("kayla"));
        assertEquals(res.get("total_price"), 2.2);
        List<Map> items = (List<Map>)res.get("order_items");
        assertEquals(items.size(), 1);
        assertEquals(items.get(0).get("amount"), 1.1);
    }

    @Test
    public void should_return_404_when_no_order_exists(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Response get = get("/users/" + userId + "/orders/111");
        assertThat(get.getStatus(), is(HttpStatus.NOT_FOUND_404.getStatusCode()));
    }

    @Test
    public void should_return_210_when_create_payment_with_specified_parameter(){
        User user = userRepository.createUser(TestHelper.user("sdcc"));
        String userId = user.getId();
        Product product = productRepository.createProduct(TestHelper.product("apple"));
        String productId = product.getId();
        Order order = orderRepository.createOrder(TestHelper.order("kayla", productId), userId);
        String orderId = order.getId();

        Response post = post("/users/" + userId + "/orders/" + orderId + "/payment", TestHelper.payment());
        assertThat(post.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));

    }
}
