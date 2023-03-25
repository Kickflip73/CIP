package com.uchain.cip.service.impl;

import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.mapper.UserMapper;
import com.uchain.cip.pojo.User;
import com.uchain.cip.service.UserService;
import com.uchain.cip.tools.EmailUtil;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
* @author 30652
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-03-16 10:21:50
*/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    EmailUtil emailUtil;

    /**
     * 依据id查询单个用户
     * */
    @Override
    public ResultVO getUserById(long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return new ResultVO(ResultEnum.NOT_FIND_USER.getCode(), ResultEnum.NOT_FIND_USER.getMessage(), null);
        } else {
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), user);
        }
    }

    /**
     * 查询所有用户
     * */
    @Override
    public ResultVO getAllUser() {
        List<User> users = userMapper.selectList(null);
        if (users == null) {
            return new ResultVO(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMessage(), null);
        } else {
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), users);
        }
    }

    /**
     * 验证账户信息，发送验证码
     * */
    @Override
    public ResultEnum formatValidationAndSendVerifyCode(User user, HttpServletRequest request) {
        //验证信息是否重合
        if (userMapper.getUserByNickNameOrEmail(user.getEmail()) != null) {
            return ResultEnum.EMAIL_ALREADY_EXISTS;
        }
        if (userMapper.getUserByNickNameOrEmail(user.getNickName()) != null) {
            return ResultEnum.NICKNAME_ALREADY_EXISTS;
        }

        //发送验证码
        try {
            String verifyCode = String.format("%04d", new Random().nextInt(9999 - 1000 + 1) + 1000);
            String text = "您正在注册智慧校园互助平台账户\n昵称：" + user.getNickName() + "\n\t验证码：" + verifyCode + "\n若非本人操作，请忽略此条信息~";
            emailUtil.sendSimpleMailMessage(user.getEmail(), "智慧校园互助平台", text);
            request.getSession().setAttribute("verifyCode", verifyCode);
            request.getSession().setAttribute("user", user);
        } catch (Exception e) {
            return ResultEnum.EMAIL_SEN_FAIL;
        }

        return ResultEnum.EMAIL_SEN_SUCCESS;
    }

    /**
     * 插入用户到数据库
     * */
    @Override
    public ResultVO saveUser(User user) {
        //插入数据库
        int insert = 0;
        try {
            insert = userMapper.insert(user);
        } catch (Exception e) {
            return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMessage(), null);
        }
        if (insert > 0) {
            return new ResultVO(ResultEnum.REGISTER_SUCCESS.getCode(), ResultEnum.REGISTER_SUCCESS.getMessage(), user);
        } else {
            return new ResultVO(ResultEnum.REGISTER_FAIL.getCode(), ResultEnum.REGISTER_FAIL.getMessage(), null);
        }
    }

    /**
     * 用户登录
     * */
    @Override
    public ResultVO login(String nickNameOrEmail, String password) {
        User user = userMapper.getUserByNickNameOrEmail(nickNameOrEmail);
        if (user != null && Objects.equals(user.getPassword(), password)) {
            return new ResultVO(ResultEnum.LOGIN_SUCCESS.getCode(), ResultEnum.LOGIN_SUCCESS.getMessage(), user);
        } else {
            return new ResultVO(ResultEnum.LOGIN_FAIL.getCode(), ResultEnum.LOGIN_FAIL.getMessage(), null);
        }
    }
}
