package com.thoughtworks.api.infrastructure.core;

import java.util.Map;
import java.util.Optional;

/**
 * Created by syzhang on 7/14/16.
 */
public interface UserRepository {
    User createUser(Map<String, Object> info);
    Optional<User> findByName(String name);
}
