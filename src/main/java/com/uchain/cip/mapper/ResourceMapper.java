package com.uchain.cip.mapper;

import com.uchain.cip.pojo.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 30652
* @description 针对表【resource】的数据库操作Mapper
* @createDate 2023-03-22 16:58:15
* @Entity com.uchain.cip.pojo.Resource
*/
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 浏览量+1
     * */
    public void addViews(long id);

    /**
     * 评论数+1
     * */
    public void addComments(long id);

    /**
     * 收藏数+1
     * */
    public void addStars(long id);

    /**
     * 评论数-1
     * */
    public void reduceComments(long id);

    /**
     * 收藏数-1
     * */
    public void reduceStars(long id);

    /**
     * 依据用户的id获取此用户发表的资源帖子
     * */
     public List<Resource> selectResourcesByUserId(long userId);

}
