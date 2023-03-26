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
 * @TableName comment
 */
@TableName(value ="comment")
@Data
@ApiModel(value = "评论", description = "保存了评论的相关信息")
public class Comment implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "评论id", example = "1")
    private Long id;

    /**
     * 帖子类型（1：资源帖 / 2：比赛帖）
     */
    @ApiModelProperty(value = "帖子类型（1：资源帖 / 2：比赛帖）", example = "1")
    private Integer thingType;

    /**
     * 所属帖子id
     */
    @ApiModelProperty(value = "所属帖子id", example = "1")
    private Long thingId;

    /**
     * 评论者id
     */
    @ApiModelProperty(value = "评论者id", example = "1")
    private Long userId;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容", example = "666，大佬求带")
    private String content;

    /**
     * 发布日期时间
     */
    @ApiModelProperty(value = "发布日期时间")
    private Date createDateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
