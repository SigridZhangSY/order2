package com.thoughtworks.api.infrastructure.resources;

import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by syzhang on 7/14/16.
 */

@RunWith(ApiTestRunner.class)
public class UserResourceTest extends ApiSupport {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void should_return_201_when_create_user(){
        Map<String, Object> map = new HashMap();
        map.put("name", "sdcc");
        Response post = post("/users", map);

    }
}
