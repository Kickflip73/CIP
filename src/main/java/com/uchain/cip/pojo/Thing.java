package com.uchain.cip.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import lombok.Data;

/**
 * 
 * @TableName thing
 */
@TableName(value ="thing")
@Data
public class Thing implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 发布者id
     */
    private Long userId;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 标题
     */
    private String title;

    /**
     * 文本内容
     */
    private String content;

    /**
     * 金额（求助）
     */
    private Integer money;

    /**
     * 发布日期时间
     * */
    private Date createDateTime;

    /**
     * 浏览量
     * */
    private int views;

    /**
     * 评论数
     * */
    private int commentsNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Thing{" +
                "id=" + id +
                ", userId=" + userId +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", money=" + money +
                ", createDateTime=" + createDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Thing thing = (Thing) o;

        if (!Objects.equals(id, thing.id)) return false;
        if (!Objects.equals(userId, thing.userId)) return false;
        if (!Objects.equals(type, thing.type)) return false;
        if (!Objects.equals(title, thing.title)) return false;
        if (!Objects.equals(content, thing.content)) return false;
        if (!Objects.equals(money, thing.money)) return false;
        return Objects.equals(createDateTime, thing.createDateTime);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (createDateTime != null ? createDateTime.hashCode() : 0);
        return result;
    }
}
