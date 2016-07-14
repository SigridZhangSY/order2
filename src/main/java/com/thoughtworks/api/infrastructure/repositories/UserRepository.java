package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.records.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by syzhang on 7/14/16.
 */
public class UserRepository implements com.thoughtworks.api.infrastructure.core.UserRepository{
    @Override
    public  User createUser(Map<String, Object> info){
        info.put("userId", nextIdentity());
        return new User(info);
    }

    @Override
    public Optional<com.thoughtworks.api.infrastructure.core.User> findByName(String name) {
        if(name.equals("xxx")){
            Map<String, Object> info = new HashMap();
            info.put("userId", nextIdentity());
            info.put("name", "xxx");
            return Optional.ofNullable(new User(info));
        }
        else
            return Optional.ofNullable(null);


    }

    private String nextIdentity() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
