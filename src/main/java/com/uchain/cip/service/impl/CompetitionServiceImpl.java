package com.uchain.cip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.pojo.Competition;
import com.uchain.cip.service.CompetitionService;
import com.uchain.cip.mapper.CompetitionMapper;
import com.uchain.cip.tools.CompetitionCondition;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 30652
* @description 针对表【competition】的数据库操作Service实现
* @createDate 2023-03-21 18:37:49
*/
@Service
public class CompetitionServiceImpl implements CompetitionService{

    @Autowired
    CompetitionMapper competitionMapper;

    /**
     * 依据条件分页查询比赛帖子
     * */
    @Override
    public ResultVO getCompetitionPage(int pageIndex, int pageSize, CompetitionCondition condition) {
        //设置分页
        Page<Competition> page = new Page<>(pageIndex, pageSize);
        //设置条件
        LambdaQueryWrapper<Competition> wrapper = new LambdaQueryWrapper<>();
        //模糊查询搜索信息
        wrapper.like(condition.getSearchInfo() != null, Competition::getCompetitionName, condition.getSearchInfo())
                .or()
                .like(condition.getSearchInfo() != null, Competition::getTitle, condition.getSearchInfo())
                .or()
                .like(condition.getSearchInfo() != null, Competition::getDescription, condition.getSearchInfo())
                //帖子类型
                .eq(condition.getPostType() == 1 || condition.getPostType() == 2, Competition::getPostType, condition.getPostType());

        if (condition.getOrderBy() == 0) {
            if (condition.getAscOrDesc() == 0) {
                //按创建时间升序
                wrapper.orderByAsc(Competition::getCreateDateTime);
            } else {
                //按创建时间降序
                wrapper.orderByDesc(Competition::getCreateDateTime);
            }
        } else  if (condition.getOrderBy() == 1) {
            if (condition.getAscOrDesc() == 0) {
                //按热度升序
                wrapper.orderByAsc(Competition::getHot);
            } else {
                //按热度降序
                wrapper.orderByDesc(Competition::getHot);
            }
        }

        //查询，得到页面数据
        Page<Competition> resultPage = competitionMapper.selectPage(page, wrapper);

        if (resultPage != null) {
            return new ResultVO(ResultEnum.DATA_QUERY_SUCCESS.getCode(), ResultEnum.DATA_QUERY_SUCCESS.getMessage(), resultPage);
        } else {
            return new ResultVO(ResultEnum.DATA_QUERY_FAIL.getCode(), ResultEnum.DATA_QUERY_FAIL.getMessage(), null);
        }
    }
}
