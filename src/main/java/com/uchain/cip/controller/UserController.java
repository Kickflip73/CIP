package com.uchain.cip.controller;

import com.uchain.cip.pojo.User;
import com.uchain.cip.service.UserService;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author: kickflip
 * date: 2023.3.16 17:35
 * description: 用户控制器
 * */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 依据id查询单个用户
     * */
    @GetMapping("/{id}")
    public ResultVO getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    /**
     * 查询所有用户
     * */
    @GetMapping()
    public ResultVO getAllUser() {
        return userService.getAllUser();
    }

    /**
     * 验证账户信息，发送验证码
     * */
    @PostMapping("/register")
    public ResultVO register(@RequestBody User user, @RequestParam String verifyCode) {
        return userService.register(user, verifyCode);
    }

    /**
     * 修改用户信息
     * */
    @PutMapping
    public ResultVO update(@RequestBody User user) {
        return userService.updateById(user);
    }

    /**
     * 删除用户信息
     * */
    @DeleteMapping("/{id}")
    public ResultVO deleteById(@PathVariable long id) {
        return userService.deleteById(id);
    }

    /**
     * 用户登录
     * */
    @PostMapping("/login")
    public ResultVO login(@RequestParam String  nickNameOrEmail, @RequestParam String password) {
        return userService.login(nickNameOrEmail, password);
    }
}
