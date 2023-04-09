package com.uchain.cip.controller;

import com.uchain.cip.pojo.Comment;
import com.uchain.cip.pojo.Star;
import com.uchain.cip.service.ActionOnThingService;
import com.uchain.cip.tools.ReportForm;
import com.uchain.cip.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 对帖子的操作类
 * */
@RestController
@CrossOrigin
@Api(tags = "对帖子进行操作的接口")
public class ActionOnThingController {
    @Autowired
    ActionOnThingService actionOnThingService;

    /**
     * 获取帖子的全部评论
     * */
    @GetMapping("/comment/{thingType}/{thingId}")
    @ApiOperation(value = "获取帖子的全部评论", notes = "依据帖子类型和帖子id来获取帖子的全部评论")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "thingType",
                    value = "帖子类型，1：资源帖，2：比赛贴",
                    required = true,
                    dataType = "int",
                    paramType = "path"
            ),
            @ApiImplicitParam(
                    name = "thingId",
                    value = "帖子id",
                    required = true,
                    dataType = "int",
                    paramType = "path"
            )
    })
    public ResultVO getCommentList(@PathVariable int thingType, @PathVariable long thingId) {
        return actionOnThingService.getCommentList(thingType, thingId);
    }

    /**
     * 发表评论
     * */
    @PostMapping("/comment")
    @ApiOperation(value = "发表评论", notes = "传入评论对象，新增一个评论")
    public ResultVO commentOn(@RequestBody Comment comment) {
        return actionOnThingService.commentOn(comment);
    }

    /**
     * 删除评论
     * */
    @DeleteMapping("/comment/{id}")
    @ApiOperation(value = "删除评论", notes = "依据id删除评论")
    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "id",
                    value = "评论的id",
                    required = true,
                    dataType = "long",
                    paramType = "path"
            )
    )
    public ResultVO deleteComment(@PathVariable long id) {
        return actionOnThingService.deleteComment(id);
    }

    /**
     * 获取用户该类型帖子的所有收藏
     * */
    @GetMapping("/star/{thingType}/{userId}")
    @ApiOperation(value = "获取用户该类型帖子的所有收藏", notes = "依据帖子类型和用户id来获取此用户的此类型帖子的全部搜藏")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "thingType",
                    value = "帖子类型，1：资源帖，2：比赛贴",
                    required = true,
                    dataType = "int",
                    paramType = "path"
            ),
            @ApiImplicitParam(
                    name = "userId",
                    value = "用户id",
                    required = true,
                    dataType = "int",
                    paramType = "path"
            )
    })
    public ResultVO getUsersStars(@PathVariable int thingType, @PathVariable long userId) {
        return actionOnThingService.getUsersStars(thingType, userId);
    }

    /**
     * 用户收藏帖子
     * */
    @PostMapping("/star")
    @ApiOperation(value = "用户收藏帖子", notes = "传入收藏对象")
    public ResultVO star(@RequestBody Star star) {
        return actionOnThingService.star(star);
    }

    /**
     * 取消收藏
     * */
    @DeleteMapping("/star")
    @ApiOperation(value = "取消收藏", notes = "依据搜藏的id取消收藏")
    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "id",
                    value = "收藏的id",
                    required = true,
                    dataType = "long",
                    paramType = "path"
            )
    )
    public ResultVO unStar(@RequestBody Star star) {
        return actionOnThingService.deleteStar(star);
    }

    /**
     * 举报帖子
     * */
    @PostMapping("/report")
    @ApiOperation(value = "举报帖子", notes = "传入举报表单对象")
    public ResultVO report(@RequestBody ReportForm reportForm) {
        return actionOnThingService.report(reportForm);
    }
}
