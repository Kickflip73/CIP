package com.uchain.cip.mapper;

import com.uchain.cip.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 30652
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-03-22 16:58:15
* @Entity com.uchain.cip.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User getUserByNickNameOrEmail(String email);
}
