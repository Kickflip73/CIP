package com.uchain.cip.dao;

import com.uchain.cip.pojo.Thing;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 30652
* @description 针对表【thing】的数据库操作Mapper
* @createDate 2023-03-16 10:24:20
* @Entity com.uchain.cip.pojo.Thing
*/
@Mapper
public interface ThingMapper extends BaseMapper<Thing> {
    public int add(Thing thing);
}
