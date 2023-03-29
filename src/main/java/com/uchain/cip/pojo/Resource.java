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
 * @TableName resource
 */
@TableName(value ="resource")
@Data
@ApiModel(value = "资源帖子类", description = "保存资源帖子相关的信息")
public class Resource implements Serializable {
    /**
     * id（自增）
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "资源帖子id（自增）")
    private Long id;

    /**
     * 发布者id
     */
    @ApiModelProperty(value = "帖子所属者id")
    private Long ownerId;

    /**
     * 帖子类型（1：分享资源帖子 / 2：求资源帖子）
     */
    @ApiModelProperty(value = "帖子类型（1：分享资源帖子 / 2：求资源帖子）", allowableValues = "{1,2}", required = true)
    private Integer postType;

    /**
     * 资源名
     */
    @ApiModelProperty(value = "资源名")
    private String resourceName;

    /**
     * 资源类型
     */
    @ApiModelProperty(value = "资源类型")
    private String resourceType;

    /**
     * 描述
     */
    @ApiModelProperty(value = "资源描述", required = true)
    private String description;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    private String contactInformation;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private Double money;

    /**
     * 资源链接
     */
    @ApiModelProperty(value = "资源链接")
    private String interlink;

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
