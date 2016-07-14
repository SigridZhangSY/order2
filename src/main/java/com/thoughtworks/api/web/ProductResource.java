package com.thoughtworks.api.web;

import com.thoughtworks.api.infrastructure.core.Product;
import com.thoughtworks.api.infrastructure.core.ProductRepository;
import com.thoughtworks.api.web.exception.InvalidParameterException;
import com.thoughtworks.api.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by syzhang on 7/13/16.
 */

@Path("products")
public class ProductResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Map<String, Object> info,
                                  @Context ProductRepository productRepository,
                                  @Context Routes routes){

        if(info.getOrDefault("name", "").toString().trim().isEmpty() ||
                info.getOrDefault("description", "").toString().trim().isEmpty() ||
                info.getOrDefault("price", "").toString().trim().isEmpty())
            throw new InvalidParameterException("name, description and price are required");
        Product product = productRepository.createProduct(info);
        return Response.created(routes.prodcut(product)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> listProducts( @Context ProductRepository productRepository){
        return productRepository.find();
    }

    @Path("{productId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map getProduct(@Context ProductRepository productRepository,
                          @Context Routes routes,
                          @PathParam("productId") String productId){
        Product product = productRepository.findById(productId);
        Map<String, Object> map = new HashMap<>();
        map.put("uri", routes.prodcut(product));
        map.put("name", product.getName());
        map.put("description", product.getDescription());
        map.put("price", product.getPrice());

        return map;
    }
}
