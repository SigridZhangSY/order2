package com.thoughtworks.api.web;

import com.thoughtworks.api.infrastructure.core.User;
import com.thoughtworks.api.infrastructure.core.UserRepository;
import com.thoughtworks.api.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

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
        User user = userRepository.createUser(info);
        return Response.created(routes.user(user)).build();
    }
}
