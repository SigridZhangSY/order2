package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.mybatis.mappers.UserMapper;
import com.thoughtworks.api.infrastructure.core.User;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by syzhang on 7/14/16.
 */
public class UserRepository implements com.thoughtworks.api.infrastructure.core.UserRepository{
    @Inject
    UserMapper userMapper;

    @Override
    public  User createUser(Map<String, Object> info){
        String userId = nextIdentity();
        info.put("userId", userId);
        userMapper.save(info);
        return userMapper.findById(userId);
    }

    @Override
    public Optional<com.thoughtworks.api.infrastructure.core.User> findByName(String name) {
            return Optional.ofNullable(userMapper.findByName(name));
    }

    private String nextIdentity() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
