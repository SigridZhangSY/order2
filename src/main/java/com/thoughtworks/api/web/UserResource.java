package com.thoughtworks.api.web;

import com.thoughtworks.api.infrastructure.core.*;
import com.thoughtworks.api.infrastructure.records.OrderRecord;
import com.thoughtworks.api.web.exception.InvalidParameterException;
import com.thoughtworks.api.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

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
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAllOrders(@PathParam("userId") String userId,
                                    @Context OrderRepository orderRepository,
                                    @Context UserRepository userRepository){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent() == false)
            throw new InvalidParameterException("user not exists");
        return orderRepository.getOrdersForUser(userId);
    }

    @GET
    @Path("/{userId}/orders/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> findOrder(@PathParam("userId") String userId,
                                        @PathParam("orderId") String orderId,
                                        @Context OrderRepository orderRepository,
                                         @Context Routes routes){
        OrderRecord order = orderRepository.getOrderDetails(orderId).orElseThrow(() -> new NotFoundException("Order not found"));

        Map<String, Object> map = new HashMap();
        map.put("uri", routes.order(userId, orderId));
        map.put("name", order.getName());
        map.put("address", order.getAddress());
        map.put("phone", order.getPhone());
        map.put("total_price", order.getTotalPrice());
        map.put("created_at", order.getTime());

        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < order.getItems().size(); i++){
            Map<String, Object> item = new HashMap();
            item.put("product_id", order.getItems().get(i).getProductId());
            item.put("quantity", order.getItems().get(i).getQuantity());
            item.put("amount", order.getItems().get(i).getAmount());
            items.add(item);
        }

        map.put("order_items", items);

        return map;
    }

    @POST
    @Path("/{userId}/orders/{orderId}/payment")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPayment(Map<String, Object> info,
                                  @PathParam("userId") String userId,
                                  @PathParam("orderId") String orderId,
                                  @Context OrderRepository orderRepository,
                                  @Context Routes routes){
        if(info.getOrDefault("pay_type", "").toString().trim().isEmpty() ||
                info.getOrDefault("amount", "").toString().trim().isEmpty())
            throw new InvalidParameterException("pay_type and amount are required");
        if(orderRepository.findPaymentById(orderId).isPresent())
            return Response.status(Response.Status.BAD_REQUEST).entity("Payment for this order has been created").build();

        Payment payment = orderRepository.createPayment(info, orderId);
        return Response.created(routes.payment(userId, payment)).build();
    }

    @GET
    @Path("/{userId}/orders/{orderId}/payment")
    public String findPayment(){
        return "OK";
    }
}
