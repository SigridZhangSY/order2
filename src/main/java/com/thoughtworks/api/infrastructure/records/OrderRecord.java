package com.thoughtworks.api.infrastructure.records;

import com.thoughtworks.api.infrastructure.core.Order;
import com.thoughtworks.api.web.jersey.Routes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by syzhang on 7/14/16.
 */
public class OrderRecord implements Order, Record{
    private String id;
    private String userId;
    private String name;
    private String address;
    private String phone;
    private float totalPrice;
    String time;
    private List<OrderItemRecord> items;

    public OrderRecord(){

    }

//    public OrderRecord(Map<String, Object> info){
//        this.id = String.valueOf(info.get("id"));
//        this.userId = String.valueOf(info.get("user_id"));
//        this.name = String.valueOf(info.get("name"));
//        this.address = String.valueOf(info.get("address"));
//        this.phone = String.valueOf(info.get("phone"));
//        this.totalPrice = Float.valueOf(String.valueOf(info.get("total_price")));
//        List<Map<String, Object>> items = (List<Map<String, Object>>)info.get("order_items");
//        for(int i = 0; i < items.size(); i++){
//            this.items.add(new OrderItemRecord((Map<String, Object>) items.get(i)));
//        }
//
//    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        Map<String, Object> map = new HashMap<>();
        map.put("uri", routes.order(userId, id));
        map.put("name", name);
        map.put("address", address);
        map.put("phone", phone);
        map.put("created_at", time);

        return map;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public float getTotalPrice() {
        return totalPrice;
    }

    @Override
    public List<OrderItemRecord> getItems() {
        return items;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }
}
