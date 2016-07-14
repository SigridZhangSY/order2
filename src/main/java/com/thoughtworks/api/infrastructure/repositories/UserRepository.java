package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.User;

import java.util.UUID;

/**
 * Created by syzhang on 7/14/16.
 */
public class UserRepository implements com.thoughtworks.api.infrastructure.core.UserRepository{
    @Override
    public String createUser() {
        return "1";
    }


}
