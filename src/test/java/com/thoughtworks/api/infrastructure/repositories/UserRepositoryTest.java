package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.User;
import com.thoughtworks.api.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by syzhang on 7/14/16.
 */

@RunWith(DatabaseTestRunner.class)
public class UserRepositoryTest {
    @Inject
    UserRepository userRepository;

    @Test
    public void should_create_and_find_user(){
        Map<String, Object> map = new HashMap();
        map.put("name", "sdcc");
        User user = userRepository.createUser(map);

        assertThat(user.getName(), is("sdcc"));
    }

    @Test
    public void should_find_user_by_name(){
        Map<String, Object> map = new HashMap();
        map.put("name", "sdcc");
        userRepository.createUser(map);

        Optional<User> user = userRepository.findByName(String.valueOf(map.get("name")));
        assertTrue(user.isPresent());
    }

}
