package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.infrastructure.core.User;
import com.thoughtworks.api.support.DatabaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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

}
