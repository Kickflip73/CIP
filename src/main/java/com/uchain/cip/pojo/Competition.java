package com.uchain.cip.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @TableName competition
 */
@TableName(value ="competition")
@Data
public class Competition implements Serializable {
    /**
     * id（自增）
     */
    @TableId
    private Long id;

    /**
     * 发布者id
     */
    private Long ownerId;

    /**
     * 帖子类型（找队伍/找队友）
     * 1：找队伍
     * 2：找队友
     * */
    private Integer postType;

    /**
     * 标题
     */
    private String title;

    /**
     * 正文描述
     */
    private String description;

    /**
     * 比赛名称
     */
    private String competitionName;

    /**
     * 比赛类型
     */
    private String competitionType;

    /**
     * 发布日期时间
     */
    private Date createDateTime;

    /**
     * 浏览量
     */
    private Integer views;

    /**
     * 评论数
     */
    private Integer comments;

    /**
     * 热度（浏览量 +评论数*5）
     * */
    private Integer hot;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", postType=" + postType +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", competitionName='" + competitionName + '\'' +
                ", competitionType='" + competitionType + '\'' +
                ", createDateTime=" + createDateTime +
                ", views=" + views +
                ", comments=" + comments +
                ", hot=" + hot +
                '}';
    }
}
