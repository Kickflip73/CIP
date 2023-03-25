package com.uchain.cip.controller;

import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.pojo.User;
import com.uchain.cip.service.UserService;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

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
    public ResultVO register(@RequestBody User user, HttpServletRequest request) {
        ResultEnum resultEnum = userService.formatValidationAndSendVerifyCode(user, request);
        return new ResultVO(resultEnum.getCode(), resultEnum.getMessage(), user);
    }


    /**
     * 验证验证码，注册用户
     * */
    @PostMapping("/verification")
    public ResultVO verification(@RequestParam String verifyCode, HttpServletRequest request) {
        if (Objects.equals(request.getSession().getAttribute("verifyCode"), verifyCode)) {
            return userService.saveUser((User) request.getSession().getAttribute("user"));
        } else {
            return new ResultVO(ResultEnum.VERIFY_CODE_ERROR.getCode(), ResultEnum.VERIFY_CODE_ERROR.getMessage(), (User) request.getSession().getAttribute("user"));
        }
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

    /**
     * 用户登录
     * */
    @PostMapping("/login")
    public ResultVO login(@RequestParam String  nickNameOrEmail, @RequestParam String password) {
        return userService.login(nickNameOrEmail, password);
    }
}
