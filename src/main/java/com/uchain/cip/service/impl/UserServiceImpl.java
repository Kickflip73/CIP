package com.uchain.cip.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.pojo.User;
import com.uchain.cip.service.UserService;
import com.uchain.cip.dao.UserMapper;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 30652
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-03-16 10:21:50
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

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
}
