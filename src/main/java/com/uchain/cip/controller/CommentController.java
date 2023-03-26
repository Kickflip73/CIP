package com.uchain.cip.controller;

import com.uchain.cip.pojo.Comment;
import com.uchain.cip.service.CommentService;
import com.uchain.cip.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@CrossOrigin
@Api(tags = "评论接口")
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     * 获取帖子的全部评论
     * */
    @GetMapping("/{thingType}/{thingId}")
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
        return commentService.getCommentList(thingType, thingId);
    }

    /**
     * 新增评论
     * */
    @PostMapping
    @ApiOperation(value = "新增评论", notes = "传入评论对象，新增一个评论")
    public ResultVO commentOn(@RequestBody Comment comment) {
        return commentService.commentOn(comment);
    }

    /**
     * 删除评论
     * */
    @DeleteMapping("/{id}")
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
        return commentService.deleteComment(id);
    }

}
