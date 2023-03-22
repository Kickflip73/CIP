package com.uchain.cip.service;

import com.uchain.cip.pojo.Competition;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uchain.cip.tools.CompetitionCondition;
import com.uchain.cip.vo.ResultVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 30652
* @description 针对表【competition】的数据库操作Service
* @createDate 2023-03-21 18:37:49
*/
@Transactional
public interface CompetitionService {
    ResultVO getCompetitionById(long id);

    public ResultVO getCompetitionPage(int pageIndex, int pageSize, CompetitionCondition condition);

    ResultVO saveCompetition(Competition competition);

    ResultVO deleteCompetitionById(long id);

    ResultVO updateCompetitionById(Competition competition);
}
