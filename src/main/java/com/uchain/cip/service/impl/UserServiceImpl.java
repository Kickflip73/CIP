package com.uchain.cip.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.pojo.User;
import com.uchain.cip.service.UserService;
import com.uchain.cip.dao.UserMapper;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
* @author 30652
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-03-16 10:21:50
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ResultVO getUserById(long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return new ResultVO(ResultEnum.NOT_FIND_USER.getCode(), ResultEnum.NOT_FIND_USER.getMessage(), null);
        } else {
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), user);
        }
    }

    @Override
    public ResultVO getAllUser() {
        List<User> users = userMapper.selectList(null);
        if (users == null) {
            return new ResultVO(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMessage(), null);
        } else {
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), users);
        }
    }

    @Override
    public ResultVO register(User user) {

        //满足条件，插入数据库
        int insert = userMapper.insert(user);
        if (insert > 0) {
            return new ResultVO(ResultEnum.SUCCESS.getCode(), "注册成功", null);
        } else {
            return new ResultVO(ResultEnum.FAIL.getCode(), "注册失败", null);
        }
    }

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
