package com.uchain.cip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.mapper.CommentMapper;
import com.uchain.cip.mapper.CompetitionMapper;
import com.uchain.cip.mapper.ResourceMapper;
import com.uchain.cip.mapper.StarMapper;
import com.uchain.cip.pojo.Comment;
import com.uchain.cip.pojo.Competition;
import com.uchain.cip.pojo.Resource;
import com.uchain.cip.pojo.Star;
import com.uchain.cip.service.ActionOnThingService;
import com.uchain.cip.tools.ReportForm;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 对帖子的操作类
 * */
@Service
@Transactional
public class ActionOnThingServiceImpl implements ActionOnThingService {
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    StarMapper starMapper;

    @Autowired
    CompetitionMapper competitionMapper;

    @Autowired
    ResourceMapper resourceMapper;

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

    /**
     * 新增评论
     * */
    @Override
    public ResultVO commentOn(Comment comment) {
        comment.setCommentDateTime(new Date());
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
                resourceMapper.addComments(comment.getThingId());
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
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
                if (resource != null) {
                    //评论的帖子存在
                    //减少帖子评论数
                    resourceMapper.reduceComments(comment.getThingId());
                }
            } else {
                //2.比赛贴
                Competition competition = competitionMapper.selectById(comment.getThingId());
                if (competition != null) {
                    //评论的帖子存在
                    //减少帖子评论数
                    competitionMapper.reduceComments(comment.getThingId());
                }
            }

            //删除评论
            commentMapper.deleteById(id);
        } catch (Exception e) {
            //删除失败
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultVO(ResultEnum.COMMENT_DELETE_FAIL.getCode(), ResultEnum.COMMENT_DELETE_FAIL.getMessage(), null);
        }
        //删除成功
        return new ResultVO(ResultEnum.COMMENT_DELETE_SUCCESS.getCode(), ResultEnum.COMMENT_DELETE_SUCCESS.getMessage(), null);
    }

    /**
     * 获取用户该类型帖子的所有收藏
     * */
    @Override
    public ResultVO getUsersStars(int thingType, long userId) {
        List<Long> thingIdList = starMapper.selectUsersStars(thingType, userId);

        if (thingType == 1) {
            List<Resource> resources = new ArrayList<>();
            for (Long thingId : thingIdList) {
                Resource resource = resourceMapper.selectById(thingId);
                resources.add(resource);
            }
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), resources);
        } else {
            List<Competition> competitions = new ArrayList<>();
            for (Long thingId : thingIdList) {
                Competition competition = competitionMapper.selectById(thingId);
                competitions.add(competition);
            }
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), competitions);
        }
    }

    /**
     * 新增收藏
     * */
    @Override
    public ResultVO star(Star star) {
        //判断是否已经被收藏过
        if (starMapper.isExist(star) > 0) {
            return new ResultVO(ResultEnum.STAR_REPEAT.getCode(), ResultEnum.STAR_REPEAT.getMessage(), null);
        }

        star.setStarDateTime(new Date());
        try {
            //判断评论帖子的类型
            if (star.getThingType() == 1) {
                //1.资源帖
                LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Resource::getId, star.getThingId());
                Long selectCount = resourceMapper.selectCount(wrapper);
                if (selectCount != 1) {
                    //要搜藏的帖子不存在
                    return new ResultVO(ResultEnum.THING_NOT_EXIST.getCode(), ResultEnum.THING_NOT_EXIST.getMessage(), null);
                }

                //增加帖子收藏数
                resourceMapper.addStars(star.getThingId());
            } else {
                //2.比赛贴
                LambdaQueryWrapper<Competition> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Competition::getId, star.getThingId());
                Long selectCount = competitionMapper.selectCount(wrapper);
                if (selectCount != 1) {
                    //要收藏的帖子不存在
                    return new ResultVO(ResultEnum.THING_NOT_EXIST.getCode(), ResultEnum.THING_NOT_EXIST.getMessage(), null);
                }

                //增加帖子收藏数
                competitionMapper.addStars(star.getThingId());
            }

            //插入收藏
            starMapper.insert(star);
        } catch (Exception e) {
            e.printStackTrace();
            //收藏失败
            return new ResultVO(ResultEnum.STAR_FAIL.getCode(), ResultEnum.STAR_FAIL.getMessage(), null);
        }
        //收藏成功
        return new ResultVO(ResultEnum.STAR_SUCCESS.getCode(), ResultEnum.STAR_SUCCESS.getMessage(), null);
    }

    /**
     * 取消收藏
     * */
    @Override
    public ResultVO deleteStar(Star star) {
        try {
            //判断收藏是否存在
            if (starMapper.isExist(star) == 0) {
                //不存在此收藏，返回取消收藏成功
                return new ResultVO(ResultEnum.STAR_DELETE_SUCCESS.getCode(), ResultEnum.STAR_DELETE_SUCCESS.getMessage(), null);
            }

            //判断收藏帖子的类型
            if (star.getThingType() == 1) {
                //1.资源帖
                Resource resource = resourceMapper.selectById(star.getThingId());
                if (resource != null) {
                    //收藏的帖子存在
                    //减少帖子收藏数
                    resourceMapper.reduceStars(star.getThingId());
                }
            } else {
                //2.比赛贴
                Competition competition = competitionMapper.selectById(star.getThingId());
                if (competition != null) {
                    //收藏的帖子存在
                    //减少帖子收藏数
                    competitionMapper.reduceStars(star.getThingId());
                }
            }

            //删除收藏
            starMapper.deleteStar(star);
        } catch (Exception e) {
            //取消收藏失败
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultVO(ResultEnum.STAR_DELETE_FAIL.getCode(), ResultEnum.STAR_DELETE_FAIL.getMessage(), null);
        }
        //取消收藏成功
        return new ResultVO(ResultEnum.STAR_DELETE_SUCCESS.getCode(), ResultEnum.STAR_DELETE_SUCCESS.getMessage(), null);
    }

    /**
     * 举报帖子
     * */
    @Override
    public ResultVO report(ReportForm reportForm) {
        //判断帖子是否存在
        //判断评论帖子的类型
        if (reportForm.getThingType() == 1) {
            //1.资源帖
            LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Resource::getId, reportForm.getThingId());
            Long selectCount = resourceMapper.selectCount(wrapper);
            if (selectCount != 1) {
                //该帖子不存在
                return new ResultVO(ResultEnum.THING_NOT_EXIST.getCode(), ResultEnum.THING_NOT_EXIST.getMessage(), null);
            }
        } else {
            //2.比赛贴
            LambdaQueryWrapper<Competition> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Competition::getId, reportForm.getThingId());
            Long selectCount = competitionMapper.selectCount(wrapper);
            if (selectCount != 1) {
                //该帖子不存在
                return new ResultVO(ResultEnum.THING_NOT_EXIST.getCode(), ResultEnum.THING_NOT_EXIST.getMessage(), null);
            }
        }

        //给管理员用户发送举报消息


        return new ResultVO(ResultEnum.REPORT_SUCCESS.getCode(), ResultEnum.REPORT_SUCCESS.getMessage(), null);
    }
}
