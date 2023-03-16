package com.uchain.cip.controller;

import com.uchain.cip.enums.ResultEnum;
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
     * 注册用户
     * */
    @PostMapping
    public ResultVO register(@RequestBody User user) {
        return userService.register(user);
    }

    /**
     * 修改用户信息
     * */
    @PutMapping
    public ResultVO update(@RequestBody User user) {
        if (userService.updateById(user)) {
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
        } else {
            return new ResultVO(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMessage(), null);
        }
    }

    /**
     * 删除用户信息
     * */
    @DeleteMapping("/{id}")
    public ResultVO deleteById(@PathVariable long id) {
        if (userService.removeById(id)) {
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
        } else {
            return new ResultVO(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMessage(), null);
        }
    }
}
