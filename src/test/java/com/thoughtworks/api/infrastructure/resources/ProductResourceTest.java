package com.thoughtworks.api.infrastructure.resources;

import com.thoughtworks.api.infrastructure.core.ProductRepository;
import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;

import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.verify;

/**
 * Created by syzhang on 7/13/16.
 */

@RunWith(ApiTestRunner.class)
public class ProductResourceTest extends ApiSupport {

    @Inject
    ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void should_able_return_201_when_create(){
        Map map = new HashMap<String, Object>();
        map.put("name", "apple");
        map.put("description", "red apple");
        map.put("price", 1.2);

        WebTarget target = target("/products");
        Response created = target.request().post(Entity.json(map));
        assertThat(created.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));


    }

    @Test
    public void should_able_return_400_when_create(){
        Map map = new HashMap<String, Object>();
        map.put("description", "red apple");
        map.put("price", 1.2);

        WebTarget target = target("/products");
        Response created = target.request().post(Entity.json(map));
        assertThat(created.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }



    @Test
    public void should_return_details_when_list_products(){
        Map map = new HashMap<String, Object>();
        map.put("name", "apple");
        map.put("description", "red apple");
        map.put("price", 1.2);
        productRepository.createProduct(map);

        WebTarget target = target("/products");
        Response get = target.request().get();
        assertThat(get.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
        final List<Map> product = get.readEntity(List.class);
        assertThat(product.get(0).get("name"), is("apple"));
        assertThat(product.get(0).get("description"), is("red apple"));
        assertEquals(1.2, Float.valueOf(String.valueOf(product.get(0).get("price"))), 0.01);
    }

    @Test
    public void should_return_200_when_get_a_product(){
        WebTarget target = target("/products/1");
        Response get = target.request().get();
        assertThat(get.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
    }

}
