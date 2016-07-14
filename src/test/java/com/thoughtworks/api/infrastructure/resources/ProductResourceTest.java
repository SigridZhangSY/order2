package com.thoughtworks.api.infrastructure.resources;

import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;

import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.verify;

/**
 * Created by syzhang on 7/13/16.
 */

@RunWith(ApiTestRunner.class)
public class ProductResourceTest extends ApiSupport {


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
    public void should_return_200_when_list_products(){
        WebTarget target = target("/products");
        Response get = target.request().get();
        assertThat(get.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
    }
}
