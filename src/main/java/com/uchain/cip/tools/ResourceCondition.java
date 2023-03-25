package com.uchain.cip.tools;

public class ResourceCondition {
    /**
     * 搜索信息
     * */
    private String searchInfo;

    /**
     * 帖子类型
     * 1：分享资源
     * 2：找资源
     * 3：分享资源&找资源
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
