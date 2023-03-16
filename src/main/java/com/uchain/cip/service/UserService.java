package com.uchain.cip.service;

import com.uchain.cip.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uchain.cip.vo.ResultVO;

/**
* @author 30652
* @description 针对表【user】的数据库操作Service
* @createDate 2023-03-16 10:21:50
*/
public interface UserService extends IService<User> {

    public ResultVO getUserById(long id);

}
