package com.uchain.cip.service;

import com.uchain.cip.pojo.Comment;
import com.uchain.cip.pojo.Star;
import com.uchain.cip.tools.ReportForm;
import com.uchain.cip.vo.ResultVO;

public interface ActionOnThingService {
    /**
     * 评论
     * */
    ResultVO getCommentList(int thingType, long thingId);

    ResultVO commentOn(Comment comment);

    ResultVO deleteComment(long id);

    /**
     * 搜藏
     * */
    ResultVO getUsersStars(int thingType, long userId);

    ResultVO star(Star star);

    ResultVO deleteStar(Star star);

    /**
     * 举报
     * */
    ResultVO report(ReportForm reportForm);
}
