package com.thoughtworks.api.infrastructure.records;

import java.util.Map;

/**
 * Created by syzhang on 7/14/16.
 */
public class User implements com.thoughtworks.api.infrastructure.core.User {
    String id;
    String name;

    public User(){

    }

    public User(Map<String, Object> info){
        this.id = String.valueOf(info.get("userId"));
        this.name = String.valueOf(info.get("name"));
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
