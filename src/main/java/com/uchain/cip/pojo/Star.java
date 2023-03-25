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
 * @TableName star
 */
@TableName(value ="star")
@Data
public class Star implements Serializable {
    /**
     * id（自增）
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 帖子id
     */
    private Long thingId;

    /**
     * 收藏日期时间
     */
    private Date starDateTime;

    /**
     * 帖子类型
     */
    private Integer thingType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
