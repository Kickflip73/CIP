package com.uchain.cip.tools;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 竞赛筛选类
 * */
@Getter
@Setter
@ApiModel(value = "资源帖子筛选类", description = "保存了筛选资源帖子的各种条件")
public class ResourceCondition {
    /**
     * 搜索信息
     * */
    @ApiModelProperty(value = "用户输入的搜索信息", example = "高数")
    private String searchInfo;

    /**
     * 帖子类型（1：分享资源帖子，2：求资源帖子，3：分享资源帖子&求资源帖子）
     */
    @ApiModelProperty(value = "帖子类型，1：分享资源帖子，2：求资源帖子，3：分享资源帖子&求资源帖子", example = "1")
    private int postType;

    /**
     * 排序规则
     * 1：创建时间
     * 2：热度（浏览量 + 评论数*5）
     * */
    @ApiModelProperty(value = "排序规则，1：创建时间，2：热度（浏览量 + 评论数*5）", example = "2")
    private int orderBy;

    /**
     * 升序或降序
     * 1：asc升序
     * 2：desc降序
     * */
    @ApiModelProperty(value = "升序或降序，1：asc升序，2：desc降序", example = "2")
    private int ascOrDesc;
}
