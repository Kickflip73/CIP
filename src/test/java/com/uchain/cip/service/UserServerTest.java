package com.uchain.cip.service;

import com.uchain.cip.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServerTest {
    @Autowired
    UserService userService;

    @Test
    public void testGetUserById() {
        User user = (User) userService.getUserById(1).getData();
        System.out.println(user);
    }

}
