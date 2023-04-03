package com.uchain.cip.service;

import com.uchain.cip.pojo.Comment;
import com.uchain.cip.vo.ResultVO;

public interface ActionOnThingService {
    ResultVO getCommentList(int thingType, long thingId);

    ResultVO commentOn(Comment comment);

    ResultVO deleteComment(long id);
}
