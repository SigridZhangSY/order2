package com.thoughtworks.api.web;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by syzhang on 7/13/16.
 */

@Path("products")
public class ProductResource {
    @POST

    public Response createProduct(){
        return Response.status(201).build();
    }
}
