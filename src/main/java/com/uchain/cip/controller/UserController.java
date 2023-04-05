package com.uchain.cip.controller;

import com.uchain.cip.pojo.User;
import com.uchain.cip.service.NoticeService;
import com.uchain.cip.service.UserService;
import com.uchain.cip.tools.LoginForm;
import com.uchain.cip.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author: kickflip
 * date: 2023.3.16 17:35
 * description: 用户控制器
 * */
@RestController
@RequestMapping("/users")
@CrossOrigin
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    NoticeService noticeService;

    /**
     * 依据id查询单个用户
     * */
    @GetMapping("/{id}")
    @ApiOperation(value = "查询单个用户", notes = "依据id查询单个用户")
    public ResultVO getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    /**
     * 发送验证码
     * */
    @PostMapping("/sendVerifyCode/{email}")
    @ApiOperation(value = "发送验证码", notes = "传入邮箱地址，发送验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email",
                    value = "邮箱",
                    required = true,
                    dataType = "String",
                    paramType = "path"
            )
    })
    public ResultVO verifyEmail(@PathVariable String email) {
        return userService.sendVerifyCode(email);
    }

    /**
     * 注册用户
     * */
    @PostMapping("/register/{verifyCode}")
    @ApiOperation(value = "注册", notes = "传入用户对象和验证码，验证验证码是否正确并进行注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "verifyCode",
                    value = "验证码",
                    required = true,
                    dataType = "String",
                    paramType = "path"
            )
    })
    public ResultVO register(@RequestBody User user, @PathVariable String verifyCode) {
        return userService.register(user, verifyCode);
    }

    /**
     * 修改用户信息
     * */
    @PutMapping
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    public ResultVO update( User user) {

        return userService.updateById(user);
    }

    /**
     * 用户登录
     * */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public ResultVO login(@RequestBody LoginForm loginForm) {
        return userService.login(loginForm);
    }

    /**
     * 修改密码
     * */
    @GetMapping("/upd")
    @ApiOperation(value = "修改密码", notes = "修改密码")
    public ResultVO updatepassword(Integer id,String newPassword,String password){
    return userService.upDatepasswordById(id,newPassword,password);
    }

    /**
     * 获取所有已发布的公告
     * */
    @GetMapping("/publishedNotice")
    @ApiOperation(value = "获取已发布的公告", notes = "普通用户进入首页后，即可查看的已经发布的公告")
    public ResultVO getPublishedNotices() {
        return noticeService.getPublishedNotices();
    }
}
