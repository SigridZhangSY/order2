package com.thoughtworks.api.infrastructure.mybatis.mappers;

import com.thoughtworks.api.infrastructure.core.User;
import java.util.Map;
import java.util.Optional;

/**
 * Created by syzhang on 7/14/16.
 */
public interface UserMapper {
    int save(Map<String, Object> info);
    User findById(String id);
    User findByName(String name);
}
