package com.uchain.cip.dao;

import com.uchain.cip.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 30652
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-03-16 10:21:50
* @Entity com.uchain.cip.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    public User getUserByNickNameOrEmail(String nickNameOrEmail);
}
