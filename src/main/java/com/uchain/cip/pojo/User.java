package com.uchain.cip.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id（自增）
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 账户类型
     */
    private Integer type;

    /**
     * 头像
     */
    private String headPicture;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 个人介绍
     */
    private String description;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 院系
     */
    private String department;

    /**
     * 专业
     */
    private String major;

    /**
     * 平均学分绩点
     */
    private Double gpa;

    /**
     * 注册时间
     */
    private Date registerDateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
