package com.thoughtworks.api.web;

import com.thoughtworks.api.infrastructure.core.OrderRepository;
import com.thoughtworks.api.infrastructure.core.User;
import com.thoughtworks.api.infrastructure.core.UserRepository;
import com.thoughtworks.api.web.exception.InvalidParameterException;
import com.thoughtworks.api.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

//    @POST
//    @Path("/{userId}/orders")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createOrder(@PathParam("userId") String userId,
//                                @Context OrderRepository orderRepository,
//                                @Context Routes routes){
//        String orderId = orderRepository.createOrder("1");
//        return Response.created(routes.order(userId, orderId)).build();
//
//    }
}
