package com.uchain.cip.controller;

import com.uchain.cip.pojo.Notice;
import com.uchain.cip.service.NoticeService;
import com.uchain.cip.service.UserService;
import com.uchain.cip.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin
@Api(tags = "管理员操作接口")
public class AdminController {
    @Autowired
    NoticeService noticeService;

    @Autowired
    UserService userService;

    /**
     * 发布公告
     * */
    @PostMapping("/notice")
    @ApiOperation(value = "发布公告", notes = "管理员发布公告信息")
    public ResultVO publishNotice(@RequestBody Notice notice) {
        return noticeService.publishNotice(notice);
    }

    /**
     * 获取所有的公告（含未发布的）
     * */
    @GetMapping("/allNotice")
    @ApiOperation(value = "获取所有公告", notes = "管理员获取所有的公告（含未发布的）")
    public ResultVO getAllNotices() {
        return noticeService.getAllNotices();
    }

    /**
     * 修改公告
     * */
    @PutMapping("/notice")
    @ApiOperation(value = "修改公告", notes = "管理员修改公告信息")
    public ResultVO updateNoticeById(@RequestBody Notice notice) {
        return noticeService.updateById(notice);
    }

    /**
     * 删除公告
     * */
    @DeleteMapping("/notice/{id}")
    @ApiOperation(value = "删除公告", notes = "管理员删除公告信息，依据公告的id来删除")
    public ResultVO deleteNoticeById(@PathVariable long id) {
        return noticeService.deleteNoticerById(id);
    }

    /**
     * 删除用户信息
     * */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户信息", notes = "依据id删除用户信息")
    public ResultVO deleteById(@PathVariable long id) {
        return userService.deleteById(id);
    }

    /**
     * 查询所有用户
     * */
    @GetMapping()
    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    public ResultVO getAllUser() {
        return userService.getAllUser();
    }
}
