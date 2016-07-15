package com.thoughtworks.api.infrastructure.records;

import com.thoughtworks.api.infrastructure.core.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by syzhang on 7/14/16.
 */
public class OrderRecord implements Order{
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
}
