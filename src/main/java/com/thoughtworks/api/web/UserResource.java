package com.thoughtworks.api.web;

import com.thoughtworks.api.infrastructure.core.Order;
import com.thoughtworks.api.infrastructure.core.OrderRepository;
import com.thoughtworks.api.infrastructure.core.User;
import com.thoughtworks.api.infrastructure.core.UserRepository;
import com.thoughtworks.api.web.exception.InvalidParameterException;
import com.thoughtworks.api.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by syzhang on 7/14/16.
 */

@Path("users")
public class UserResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(@Context UserRepository userRepository,
                               @Context Routes routes,
                               Map<String, Object> info){
        if(info.getOrDefault("name", "").toString().trim().isEmpty())
            throw new InvalidParameterException("name is required");
        Optional<User> user = userRepository.findByName(String.valueOf(info.get("name")));
        if (user.isPresent())
            return Response.status(Response.Status.BAD_REQUEST).entity("User with same name already exists").build();

        return Response.created(routes.user(userRepository.createUser(info))).build();
    }

    @POST
    @Path("/{userId}/orders")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(Map<String, Object> info,
                                @PathParam("userId") String userId,
                                @Context UserRepository userRepository,
                                @Context OrderRepository orderRepository,
                                @Context Routes routes){
        if(info.getOrDefault("name", "").toString().trim().isEmpty() ||
                info.getOrDefault("address", "").toString().trim().isEmpty() ||
                info.getOrDefault("phone", "").toString().trim().isEmpty() ||
                info.getOrDefault("order_items", "").toString().trim().isEmpty())
            throw new InvalidParameterException("name, address, phone and order_items are required");
        List<Map<String, Object>> items = (List<Map<String, Object>>)info.get("order_items");
        if(items.size() == 0)
            throw new InvalidParameterException("order_items can't be empty");
        for(int i = 0; i < items.size(); i++) {
            if (items.get(i).getOrDefault("product_id", "").toString().trim().isEmpty() ||
                    items.get(i).getOrDefault("quantity", "").toString().trim().isEmpty())
                throw new InvalidParameterException("product_id and quantity are required");
        }

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent() == false)
            return Response.status(Response.Status.BAD_REQUEST).entity("User dose not exists").build();
        Order order = orderRepository.createOrder(info, userId);
        return Response.created(routes.order(userId, order.getId())).build();

    }

    @GET
    @Path("/{userId}/orders")
    public String getAllOrders(){
        return "OK";
    }
}
