package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.domain.user.User;
import com.thoughtworks.api.domain.user.UserRepository;
import com.thoughtworks.api.domain.user.UserId;
import com.thoughtworks.api.infrastructure.mybatis.mappers.UserMapper;

import javax.inject.Inject;
import java.util.Optional;

public class MyBatisUserRepository implements UserRepository {
    @Inject
    UserMapper mapper;

    @Override
    public com.thoughtworks.api.domain.user.User save(com.thoughtworks.api.domain.user.User user) {
        mapper.save(user);
        return mapper.ofId(user.getUserId().id());
    }

    @Override
    public Optional<com.thoughtworks.api.domain.user.User> ofId(UserId id) {
        return Optional.ofNullable(mapper.ofId(id.id()));
    }

    @Override
    public User findUserByName(String userName) {
        return mapper.findByUserName(userName);
    }
}
