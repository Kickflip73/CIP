package com.uchain.cip.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @TableName competition
 */
@TableName(value ="competition")
@Data
@ApiModel(value = "比赛帖子类", description = "保存比赛帖子相关的信息")
public class Competition implements Serializable {
    /**
     * id（自增）
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "比赛帖子id（自增）")
    private Long id;

    /**
     * 发布者id
     */
    @ApiModelProperty(value = "帖子所属者id")
    private Long ownerId;

    /**
     * 帖子类型（1：找队伍 / 2：找队友）
     */
    @ApiModelProperty(value = "帖子类型（1：找队伍 / 2：找队友）", allowableValues = "{1,2}", required = true)
    private Integer postType;

    /**
     * 标题
     */
    @ApiModelProperty(value = "帖子标题", required = true)
    private String title;

    /**
     * 比赛名称
     */
    @ApiModelProperty(value = "比赛名称", required = true)
    private String competitionName;

    /**
     * 比赛类型
     */
    @ApiModelProperty(value = "比赛类型")
    private String competitionType;

    /**
     * 正文描述
     */
    @ApiModelProperty(value = "正文描述", required = true)
    private String description;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    private String contactInformation;

    /**
     * 发布日期时间
     */
    @ApiModelProperty(value = "发布日期时间")
    private Date createDateTime;

    /**
     * 浏览量
     */
    @ApiModelProperty(value = "浏览量")
    private Integer views;

    /**
     * 评论数
     */
    @ApiModelProperty(value = "评论数")
    private Integer comments;

    /**
     * 收藏数
     * */
    @ApiModelProperty(value = "收藏数")
    private Integer stars;

    /**
     * 热度（浏览量 + 评论数*3 + 收藏数*5）
     */
    @ApiModelProperty(value = "热度（浏览量 + 评论数*3 + 收藏数*5）")
    private Integer hot;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
