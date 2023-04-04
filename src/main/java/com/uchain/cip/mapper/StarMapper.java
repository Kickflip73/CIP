package com.uchain.cip.mapper;

import com.uchain.cip.pojo.Star;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 30652
* @description 针对表【star】的数据库操作Mapper
* @createDate 2023-03-25 17:58:01
* @Entity com.uchain.cip.pojo.Star
*/
@Mapper
public interface StarMapper extends BaseMapper<Star> {
    List<Long> selectUsersStars(int thingType, long userId);

    int isExist(Star star);

    void deleteStar(Star star);
}
