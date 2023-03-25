package com.uchain.cip.service;

import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uchain.cip.vo.ResultVO;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

/**
* @author 30652
* @description 针对表【user】的数据库操作Service
* @createDate 2023-03-16 10:21:50
*/
public interface UserService extends IService<User> {

    public ResultVO getUserById(long id);

    public ResultVO getAllUser();

    public ResultVO login(String nickNameOrEmail, String password);

    public ResultEnum formatValidationAndSendVerifyCode(User user, HttpServletRequest request);

    public ResultVO saveUser(User user);
}
