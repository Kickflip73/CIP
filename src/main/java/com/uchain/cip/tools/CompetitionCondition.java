package com.uchain.cip.tools;

import lombok.Getter;
import lombok.Setter;

/**
 * 竞赛筛选类
 * */
@Getter
@Setter
public class CompetitionCondition {
    /**
     * 搜索信息
     * */
    private String searchInfo;

    /**
     * 帖子类型
     * 1：找队伍
     * 2：找队友
     * 3：找队伍&找队友
     * */
    private int postType;

    /**
     * 排序规则
     * 1：创建时间
     * 2：热度（浏览量 + 评论数*5）
     * */
    private int orderBy;

    /**
     * 升序或降序
     * 1：asc升序
     * 2：desc降序
     * */
    private int ascOrDesc;
}
