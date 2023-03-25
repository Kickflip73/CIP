package com.uchain.cip.mapper;

import com.uchain.cip.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 30652
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2023-03-22 19:59:09
* @Entity com.uchain.cip.pojo.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
