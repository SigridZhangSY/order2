package com.thoughtworks.api.support;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHelper {
    private static int auto_increment_key = 1;
    public static Map<String, Object> deployment(String appName, String releaseId) {
        return new HashMap<String, Object>() {{
            put("app", String.format("http://service-api.tw.com/apps/%s", appName));
            put("release", String.format("http://service-api.tw.com/apps/%s/releases/%s", appName, releaseId));
        }};
    }

    public static Map<String, Object> stackMap(String id, String name) {
        Map<String, Object> stackMap = new HashMap<String, Object>() {{
            put("id", id);
            put("name", name);
        }};
        return stackMap;
    }

    public static Map<String, Object> user(String name){
        return new HashMap<String, Object>(){{
            put("name", name);
        }};
    }

    public static Map<String, Object> product(String name){
        return new HashMap<String, Object>() {{
            put("name", name);
            put("description", "red apple");
            put("price", 1.1);

        }};
    }

    public static Map<String, Object> order(String name, String productId){
        Map orderMap = new HashMap<String, Object>();
        orderMap.put("name", name);
        orderMap.put("address", "beijing");
        orderMap.put("phone", "12300000000");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map orderIterm1 = new HashMap<String, Object>();
        orderIterm1.put("product_id", productId);
        orderIterm1.put("quantity", 2);
        list.add(orderIterm1);
        orderMap.put("order_items", list);
        return orderMap;
    }

    public static Map<String, Object> payment(){
        Map payMap = new HashMap<String, Object>();
        payMap.put("pay_type", "CASH");
        payMap.put("amount", "100");

        return payMap;
    }


}
