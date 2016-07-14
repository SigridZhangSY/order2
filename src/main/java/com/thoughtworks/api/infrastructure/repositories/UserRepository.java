package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.records.User;

import java.util.Map;
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

    private String nextIdentity() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
