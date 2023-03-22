package com.uchain.cip.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, 1);

        User user = userService.getOne(wrapper);
        System.out.println(user);
    }

}
