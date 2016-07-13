package com.thoughtworks.api.web;

import com.thoughtworks.api.infrastructure.core.ProductRepository;
import com.thoughtworks.api.web.jersey.Routes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by syzhang on 7/13/16.
 */

@Path("products")
public class ProductResource {
    @POST

    public Response createProduct(@Context ProductRepository productRepository,
                                  @Context Routes routes){

        String productId = productRepository.creatProduct();
        return Response.created(routes.prodcut(productId)).build();
    }
}
