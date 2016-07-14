package com.thoughtworks.api.web;

import com.thoughtworks.api.infrastructure.core.UserRepository;
import com.thoughtworks.api.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by syzhang on 7/14/16.
 */

@Path("users")
public class UserResource {

    @POST
    public Response createUser(@Context UserRepository userRepository,
                               @Context Routes routes){

        return Response.created(routes.user(userRepository.createUser())).build();
    }
}
