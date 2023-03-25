package com.uchain.cip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.mapper.CommentMapper;
import com.uchain.cip.mapper.CompetitionMapper;
import com.uchain.cip.mapper.ResourceMapper;
import com.uchain.cip.pojo.Comment;
import com.uchain.cip.pojo.Competition;
import com.uchain.cip.pojo.Resource;
import com.uchain.cip.service.CommentService;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author 30652
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2023-03-21 18:37:56
*/
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CompetitionMapper competitionMapper;

    @Autowired
    ResourceMapper resourceMapper;

    /**
     * 新增评论
     * */
    @Override
    public ResultVO commentOn(Comment comment) {
        comment.setCreateDateTime(new Date());
        try {
            //判断评论帖子的类型
            if (comment.getThingType() == 1) {
                //1.资源帖
                LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Resource::getId, comment.getThingId());
                Long selectCount = resourceMapper.selectCount(wrapper);
                if (selectCount != 1) {
                    //要评论的帖子不存在
                    return new ResultVO(ResultEnum.THING_NOT_EXIST.getCode(), ResultEnum.THING_NOT_EXIST.getMessage(), null);
                }

                //增加帖子评论数
                resourceMapper.addComments(comment.getUserId());
            } else {
                //2.比赛贴
                LambdaQueryWrapper<Competition> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Competition::getId, comment.getThingId());
                Long selectCount = competitionMapper.selectCount(wrapper);
                if (selectCount != 1) {
                    //要评论的帖子不存在
                    return new ResultVO(ResultEnum.THING_NOT_EXIST.getCode(), ResultEnum.THING_NOT_EXIST.getMessage(), null);
                }

                //增加帖子评论数
                competitionMapper.addComments(comment.getThingId());
            }

            //插入评论
            commentMapper.insert(comment);
        } catch (Exception e) {
            //评论失败
            return new ResultVO(ResultEnum.COMMENT_ON_FAIL.getCode(), ResultEnum.COMMENT_ON_FAIL.getMessage(), null);
        }
        //评论成功
        return new ResultVO(ResultEnum.COMMENT_ON_SUCCESS.getCode(), ResultEnum.COMMENT_ON_SUCCESS.getMessage(), comment);
    }

    /**
     * 删除评论
     * */
    @Override
    public ResultVO deleteComment(long id) {
        try {
            //判断评论是否存在
            Comment comment = commentMapper.selectById(id);
            if (comment == null) {
                //评论不存在
                return new ResultVO(ResultEnum.COMMENT_NOT_EXIST.getCode(), ResultEnum.COMMENT_NOT_EXIST.getMessage(), null);
            }

            //判断评论帖子的类型
            if (comment.getThingType() == 1) {
                //1.资源帖
                Resource resource = resourceMapper.selectById(comment.getThingId());
                if (resource == null) {
                    //要评论的帖子不存在
                    return new ResultVO(ResultEnum.THING_NOT_EXIST.getCode(), ResultEnum.THING_NOT_EXIST.getMessage(), null);
                }

                //减少帖子评论数
                resourceMapper.reduceComments(comment.getUserId());
            } else {
                //2.比赛贴
                //判断要评论的帖子是否存在
                Competition competition = competitionMapper.selectById(comment.getThingId());
                if (competition == null) {
                    //要评论的帖子不存在
                    return new ResultVO(ResultEnum.THING_NOT_EXIST.getCode(), ResultEnum.THING_NOT_EXIST.getMessage(), null);
                }

                //减少帖子评论数
                competitionMapper.reduceComments(comment.getUserId());
            }

            //删除评论
            commentMapper.deleteById(id);
        } catch (Exception e) {
            //删除失败
            return new ResultVO(ResultEnum.COMMENT_DELETE_FAIL.getCode(), ResultEnum.COMMENT_DELETE_FAIL.getMessage(), null);
        }
        //删除成功
        return new ResultVO(ResultEnum.COMMENT_DELETE_SUCCESS.getCode(), ResultEnum.COMMENT_DELETE_SUCCESS.getMessage(), null);
    }

    /**
     * 获取帖子的全部评论
     * */
    @Override
    public ResultVO getCommentList(int thingType, long thingId) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getThingType, thingType)
                .eq(Comment::getThingId, thingId);
        List<Comment> comments = commentMapper.selectList(wrapper);

        return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), comments);
    }
}
