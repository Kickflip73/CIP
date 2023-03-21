package com.uchain.cip.mapper;

import com.uchain.cip.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 30652
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2023-03-21 18:37:56
* @Entity com.uchain.cip.pojo.Comment
*/
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

}
