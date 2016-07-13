package com.thoughtworks.api.infrastructure.resources;

import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        map.put("price", 1.1);

        Response created = target("/products").request().post(Entity.json(map));
        assertThat(created.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
        assertThat(created.getLocation().toString(), endsWith("/products/1"));

    }
}
