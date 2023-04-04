package com.uchain.cip.service;

import com.uchain.cip.pojo.Comment;
import com.uchain.cip.pojo.Star;
import com.uchain.cip.vo.ResultVO;

public interface ActionOnThingService {
    /**
     * 评论相关
     * */
    ResultVO getCommentList(int thingType, long thingId);

    ResultVO commentOn(Comment comment);

    ResultVO deleteComment(long id);

    /**
     * 搜藏相关
     * */
    ResultVO getUsersStars(int thingType, long userId);

    ResultVO star(Star star);

    ResultVO deleteStar(Star star);

    ResultVO report(long userId, int thingType, long thingId);
}
