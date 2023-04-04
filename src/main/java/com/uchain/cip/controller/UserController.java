package com.uchain.cip.controller;

import com.uchain.cip.pojo.User;
import com.uchain.cip.service.NoticeService;
import com.uchain.cip.service.UserService;
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
     * 邮箱验证
     * */
    @PostMapping("/verifyEmail")
    @ApiOperation(value = "验证邮箱", notes = "第一次只发送邮箱地址，验证码为空，验证码邮件发送成功过后，第二次再发送邮箱和验证码来验证邮箱是否正确")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email",
                    value = "邮箱",
                    required = true,
                    dataType = "String",
                    paramType = "query"
            ),

            @ApiImplicitParam(name = "verifyCode",
                    value = "验证码",
                    dataType = "String",
                    paramType = "query"
            )
    })
    public ResultVO verifyEmail(@RequestParam String email, @RequestParam String verifyCode) {
        return userService.verifyEmail(email, verifyCode);
    }

    /**
     * 注册用户
     * */
    @PostMapping("/register")
    @ApiOperation(value = "发送用户信息", notes = "验证用户信息是否可用，注册用户，返回是否注册成功")
    public ResultVO register(@RequestBody User user) {
        return userService.register(user);
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
    public ResultVO login(@RequestParam String  nickNameOrEmail, @RequestParam String password) {
        return userService.login(nickNameOrEmail, password);
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
