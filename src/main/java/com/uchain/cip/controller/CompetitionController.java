package com.uchain.cip.controller;

import com.uchain.cip.pojo.Competition;
import com.uchain.cip.service.CompetitionService;
import com.uchain.cip.tools.CompetitionCondition;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author kickflip
 * @description 比赛帖子控制类
 * */
@RestController
@RequestMapping("/competitions")
public class CompetitionController {
    @Autowired
    CompetitionService competitionService;

    /**
     * 依据id获取单个比赛帖子
     * */
    @GetMapping("/{id}")
    public ResultVO getCompetitionById(@PathVariable long id) {
        return competitionService.getCompetitionById(id);
    }

    /**
     * 依据条件分页查询比赛帖子
     * */
    @GetMapping("/{pageIndex}/{pageSize}")
    public ResultVO getCompetitionPage(@PathVariable int pageIndex,@PathVariable int pageSize,@RequestBody CompetitionCondition condition) {
        return competitionService.getCompetitionPage(pageIndex, pageSize, condition);
    }

    /**
     * 创建比赛帖子
     * */
    @PostMapping
    public ResultVO createCompetition(@RequestBody Competition competition) {
        return competitionService.saveCompetition(competition);
    }

    /**
     * 依据id删除帖子
     * */
    @DeleteMapping("/{id}")
    public ResultVO deleteCompetitionById(@PathVariable long id) {
        return competitionService.deleteCompetitionById(id);
    }

    /**
     * 依据id修改帖子
     * */
    @PutMapping
    public ResultVO updateCompetitionById(@RequestBody Competition competition) {
        return competitionService.updateCompetitionById(competition);
    }

}
