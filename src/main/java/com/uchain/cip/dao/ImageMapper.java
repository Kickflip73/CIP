package com.uchain.cip.dao;

import com.uchain.cip.pojo.Image;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 30652
* @description 针对表【image】的数据库操作Mapper
* @createDate 2023-03-16 10:24:52
* @Entity com.uchain.cip.pojo.Image
*/
@Mapper
public interface ImageMapper extends BaseMapper<Image> {

}
