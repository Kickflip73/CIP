package com.uchain.cip.service;

import com.uchain.cip.pojo.User;
import com.uchain.cip.tools.LoginForm;
import com.uchain.cip.vo.ResultVO;
import org.springframework.transaction.annotation.Transactional;

/**
* @author 30652
* @description 针对表【user】的数据库操作Service
* @createDate 2023-03-16 10:21:50
*/
@Transactional
public interface UserService {

    public ResultVO getUserById(long id);

    public ResultVO getAllUser();

    public ResultVO login(LoginForm loginForm);

    public ResultVO register(User user, String verifyCode);

    public ResultVO updateById(User user);

    public ResultVO deleteById(long id);

    public ResultVO upDatepasswordById(int id,String newPassword,String password);

    public ResultVO sendVerifyCode(String email);
}
