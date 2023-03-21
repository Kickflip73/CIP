package com.uchain.cip.mapper;

import com.uchain.cip.pojo.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 30652
* @description 针对表【resource】的数据库操作Mapper
* @createDate 2023-03-21 18:37:40
* @Entity com.uchain.cip.pojo.Resource
*/
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

}
