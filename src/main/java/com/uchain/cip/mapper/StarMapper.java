package com.uchain.cip.mapper;

import com.uchain.cip.pojo.Star;
import org.apache.ibatis.annotations.Mapper;

/*
* 收藏相关的数据库
* */
@Mapper
public interface StarMapper {
    /*
    * 收藏帖子
    * */
    int addThing(Star star);

}
