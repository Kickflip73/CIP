package com.uchain.cip.controller;

import com.uchain.cip.pojo.Comment;
import com.uchain.cip.service.CommentService;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     * 新增评论
     * */
    @PostMapping
    private ResultVO commentOn(@RequestBody Comment comment) {
        return commentService.commentOn(comment);
    }

    /**
     * 删除评论
     * */
    @DeleteMapping("/{id}")
    private ResultVO deleteComment(@PathVariable long id) {
        return commentService.deleteComment(id);
    }
}
