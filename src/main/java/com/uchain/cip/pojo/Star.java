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
 * @TableName star
 */
@TableName(value ="star")
@Data
@ApiModel(value = "收藏", description = "保存了收藏的相关信息")
public class Star implements Serializable {
    /**
     * id（自增）
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "搜藏id（自增）")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", example = "1")
    private Long userId;

    /**
     * 帖子类型（1：资源帖 / 2：比赛帖）
     */
    @ApiModelProperty(value = "帖子类型（1：资源帖 / 2：比赛帖）", example = "1")
    private Integer thingType;

    /**
     * 帖子id
     */
    @ApiModelProperty(value = "帖子id", example = "1")
    private Long thingId;

    /**
     * 收藏日期时间
     */
    @ApiModelProperty(value = "收藏日期时间")
    private Date starDateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
