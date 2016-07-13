package com.thoughtworks.api.infrastructure.mybatis.mappers;

import com.thoughtworks.api.domain.user.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User ofId(@Param("id") String id);

    void save(@Param("user") User user);

    User findByUserName(@Param("userName") String userName);
}
