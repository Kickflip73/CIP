package com.uchain.cip.server;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uchain.cip.pojo.Competition;
import com.uchain.cip.service.CompetitionService;
import com.uchain.cip.tools.CompetitionCondition;
import com.uchain.cip.vo.ResultVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 比赛帖子测试类
 * */

@SpringBootTest
public class CompetitionServiceTest {
    @Autowired
    CompetitionService competitionService;

    @Test
    public void testGetCompetitionPage() {
        int index = 1, size = 5;
        CompetitionCondition condition = new CompetitionCondition();
        condition.setSearchInfo("计算机");
        condition.setPostType(3);
        condition.setOrderBy(1);
        condition.setAscOrDesc(1);

        ResultVO result = competitionService.getCompetitionPage(index, size, condition);
        if (result.getData() != null) {
            for (Competition competition : ((Page<Competition>) result.getData()).getRecords()) {
                System.out.println(competition);
            }
        }
    }
}
