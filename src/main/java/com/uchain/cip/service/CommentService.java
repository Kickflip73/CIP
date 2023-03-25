package com.uchain.cip.service;

import com.uchain.cip.pojo.Comment;
import com.uchain.cip.vo.ResultVO;

import java.util.List;

/**
* @author 30652
* @description 针对表【comment】的数据库操作Service
* @createDate 2023-03-21 18:37:56
*/
public interface CommentService  {
    ResultVO commentOn(Comment comment);

    ResultVO deleteComment(long id);

    ResultVO getCommentList(int thingType, long thingId);
}
