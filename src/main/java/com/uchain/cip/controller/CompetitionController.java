package com.uchain.cip.controller;

import com.uchain.cip.pojo.Competition;
import com.uchain.cip.pojo.User;
import com.uchain.cip.service.CompetitionService;
import com.uchain.cip.tools.CompetitionCondition;
import com.uchain.cip.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author kickflip
 * @description 比赛帖子控制类
 * */
@RestController
@RequestMapping("/competitions")
@CrossOrigin
@Api(tags = "比赛帖子接口")
public class CompetitionController {
    @Autowired
    CompetitionService competitionService;

    /**
     * 依据id获取单个比赛帖子
     * */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取单个比赛帖子", notes = "依据id来获取单个比赛帖子")
    public ResultVO getCompetitionById(@PathVariable long id) {
        return competitionService.getCompetitionById(id);
    }

    /**
     * 分页条件查询比赛帖子
     * */
    @PostMapping("search/{pageIndex}/{pageSize}")
    @ApiOperation(value = "分页条件查询比赛帖子", notes = "依据传入的索引和页面大小以及筛选条件来查询一批比赛帖子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex",
                    value = "当前页下标（从1开始）",
                    required = true,
                    dataType = "int",
                    paramType = "path"
            ),

            @ApiImplicitParam(name = "pageSize",
                    value = "页面大小，即每页展示的条目数",
                    required = true,
                    dataType = "int",
                    paramType = "path"
            )
    })
    public ResultVO getCompetitionPage(@PathVariable int pageIndex, @PathVariable int pageSize, @RequestBody CompetitionCondition condition) {
        return competitionService.getCompetitionPage(pageIndex, pageSize, condition);
    }

    /**
     * 创建比赛帖子
     * */
    @PostMapping
    @ApiOperation(value = "创建比赛帖子", notes = "创建比赛帖子")
    public ResultVO createCompetition(@RequestBody Competition competition) {
        return competitionService.saveCompetition(competition);
    }

    /**
     * 依据id删除帖子
     * */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "依据id删除帖子", notes = "依据id删除帖子")
    public ResultVO deleteCompetitionById(@PathVariable long id) {
        return competitionService.deleteCompetitionById(id);
    }

    /**
     * 依据id修改帖子
     * */
    @PutMapping
    @ApiOperation(value = "依据id修改帖子", notes = "依据id修改帖子")
    public ResultVO updateCompetitionById(@RequestBody Competition competition) {
        return competitionService.updateCompetitionById(competition);
    }

    /**
     * 比赛大厅推荐帖子
     * */
    @GetMapping("/{userId}")
    @ApiOperation(value = "比赛帖子推荐", notes = "传入用户id，返回推荐给此用户的帖子")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "userId",
                    value = "当前用户id",
                    required = true,
                    dataType = "long",
                    paramType = "path"
            )
    })
    public ResultVO recommend(@PathVariable long userId) {
        return competitionService.recommend(userId);
    }
}
