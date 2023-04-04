package com.uchain.cip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uchain.cip.enums.ResultEnum;
import com.uchain.cip.pojo.Competition;
import com.uchain.cip.service.CompetitionService;
import com.uchain.cip.mapper.CompetitionMapper;
import com.uchain.cip.tools.CompetitionCondition;
import com.uchain.cip.tools.ResourceCondition;
import com.uchain.cip.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
* @author kickflip
* @description 针对表【competition】的数据库操作Service实现
* @createDate 2023-03-21 18:37:49
*/
@Service
@Transactional
public class CompetitionServiceImpl implements CompetitionService {
    @Autowired
    CompetitionMapper competitionMapper;

    /**
     * 依据id获取单个比赛帖子
     * */
    @Override
    public ResultVO getCompetitionById(long id) {
        //依据id查询数据
        Competition competition = competitionMapper.selectById(id);

        if (competition != null) {
            //浏览量+1
            competitionMapper.addViews(id);
            return new ResultVO(ResultEnum.COMPETITION_DATA_QUERY_SUCCESS.getCode(), ResultEnum.COMPETITION_DATA_QUERY_SUCCESS.getMessage(), competition);
        } else {
            return new ResultVO(ResultEnum.THING_NOT_EXIST.getCode(), ResultEnum.THING_NOT_EXIST.getMessage(), null);
        }
    }

    /**
     * 依据条件分页查询比赛帖子
     * */
    @Override
    public ResultVO getCompetitionPage(int pageIndex, int pageSize, CompetitionCondition condition) {
        //设置分页
        Page<Competition> page = new Page<>(pageIndex, pageSize);
        //设置条件
        LambdaQueryWrapper<Competition> wrapper = new LambdaQueryWrapper<>();

                //匹配帖子类型
        wrapper.eq(condition.getPostType() == 1 || condition.getPostType() == 2, Competition::getPostType, condition.getPostType());

        if (condition.getSearchInfo() != null && condition.getSearchInfo().length() != 0) {
            wrapper.and(   //文本匹配，利用lambda表达式，提高条件优先级
                    i -> i.like(condition.getSearchInfo() != null, Competition::getCompetitionName, condition.getSearchInfo())
                            .or()
                            .like(condition.getSearchInfo() != null, Competition::getTitle, condition.getSearchInfo())
                            .or()
                            .like(condition.getSearchInfo() != null, Competition::getDescription, condition.getSearchInfo())
            );
        }

        //设置排序规则
        if (condition.getOrderBy() == 1) {
            if (condition.getAscOrDesc() == 1) {
                //按创建时间升序
                wrapper.orderByAsc(Competition::getCreateDateTime);
            } else {
                //按创建时间降序
                wrapper.orderByDesc(Competition::getCreateDateTime);
            }
        } else  if (condition.getOrderBy() == 2) {
            if (condition.getAscOrDesc() == 1) {
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
            //将查询到的帖子浏览量都+1
            for (Competition competition : resultPage.getRecords()) {
                competitionMapper.addViews(competition.getId());
            }
            return new ResultVO(ResultEnum.COMPETITION_DATA_QUERY_SUCCESS.getCode(), ResultEnum.COMPETITION_DATA_QUERY_SUCCESS.getMessage(), resultPage);
        } else {
            return new ResultVO(ResultEnum.COMPETITION_DATA_QUERY_FAIL.getCode(), ResultEnum.COMPETITION_DATA_QUERY_FAIL.getMessage(), null);
        }
    }

    /**
     * 创建比赛帖子
     * */
    @Override
    public ResultVO saveCompetition(Competition competition) {
        //设置创建时间
        competition.setCreateDateTime(new Date());

        //插入帖子，返回影响行数
        int count = competitionMapper.insert(competition);

        if (count == 1) {
            return new ResultVO(ResultEnum.CREATE_COMPETITION_SUCCESS.getCode(), ResultEnum.CREATE_COMPETITION_SUCCESS.getMessage(), competition);
        } else {
            return new ResultVO(ResultEnum.CREATE_COMPETITION_FAIL.getCode(), ResultEnum.CREATE_COMPETITION_FAIL.getMessage(), competition);
        }
    }

    /**
     * 依据id删除帖子
     * */
    @Override
    public ResultVO deleteCompetitionById(long id) {
        //条件查询是否存在有这个id的比赛帖子
        LambdaQueryWrapper<Competition> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Competition::getId, id);
        Long selectCount = competitionMapper.selectCount(wrapper);
        if (selectCount != 1) {
            //未查询到此id对应有帖子，返回
            return new ResultVO(ResultEnum.THING_NOT_EXIST.getCode(), ResultEnum.THING_NOT_EXIST.getMessage(), null);
        }

        //删除帖子，返回影响行数
        int count = competitionMapper.deleteById(id);

        if (count == 1) {
            return new ResultVO(ResultEnum.DELETE_COMPETITION_SUCCESS.getCode(), ResultEnum.DELETE_COMPETITION_SUCCESS.getMessage(), null);
        } else {
            return new ResultVO(ResultEnum.DELETE_COMPETITION_FAIL.getCode(), ResultEnum.DELETE_COMPETITION_FAIL.getMessage(), null);
        }
    }

    /**
     * 依据id修改帖子
     * */
    @Override
    public ResultVO updateCompetitionById(Competition competition) {
        //条件查询是否存在有这个id的比赛帖子
        LambdaQueryWrapper<Competition> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Competition::getId, competition.getId());
        Long selectCount = competitionMapper.selectCount(wrapper);
        if (selectCount != 1) {
            //未查询到此id对应有帖子，返回
            return new ResultVO(ResultEnum.THING_NOT_EXIST.getCode(), ResultEnum.THING_NOT_EXIST.getMessage(), null);
        }

        //修改帖子，返回影响行数
        int count = competitionMapper.updateById(competition);

        if (count == 1) {
            return new ResultVO(ResultEnum.UPDATE_COMPETITION_SUCCESS.getCode(), ResultEnum.UPDATE_COMPETITION_SUCCESS.getMessage(), competition);
        } else {
            return new ResultVO(ResultEnum.UPDATE_COMPETITION_FAIL.getCode(), ResultEnum.UPDATE_COMPETITION_FAIL.getMessage(), null);
        }
    }

    /**
     * 比赛大厅推荐帖子
     * */
    @Override
    public ResultVO recommend(long userId) {
        /**
         * 推荐算法
         * */

        /**
         * 临时顶凑的
         * */
        CompetitionCondition competitionCondition = new CompetitionCondition();
        competitionCondition.setSearchInfo(null);
        competitionCondition.setPostType(3);
        competitionCondition.setOrderBy(2);
        competitionCondition.setAscOrDesc(2);
        return this.getCompetitionPage(1, 5, competitionCondition);
    }
}
