package com.uchain.cip.mapper;

import com.uchain.cip.pojo.Competition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 30652
* @description 针对表【competition】的数据库操作Mapper
* @createDate 2023-03-22 16:58:15
* @Entity com.uchain.cip.pojo.Competition
*/
@Mapper
public interface CompetitionMapper extends BaseMapper<Competition> {
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
}
