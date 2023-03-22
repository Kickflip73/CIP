package com.uchain.cip.controller;

import com.uchain.cip.service.CompetitionService;
import com.uchain.cip.tools.CompetitionCondition;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/competitions")
public class CompetitionController {
    @Autowired
    CompetitionService competitionService;

    /**
     * 依据条件分页查询比赛帖子
     * */
    @GetMapping("/{pageIndex}/{pageSize}")
    public ResultVO getCompetitionPage(@PathVariable int pageIndex,@PathVariable int pageSize,@RequestBody CompetitionCondition condition) {
        return competitionService.getCompetitionPage(pageIndex, pageSize, condition);
    }
}
