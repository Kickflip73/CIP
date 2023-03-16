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
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 所属帖子id
     */
    private Long thingId;

    /**
     * 评论者id
     */
    private Long userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 发布日期时间
     * */
    private Date createDateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", thingId=" + thingId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", createDateTime=" + createDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (!Objects.equals(id, comment.id)) return false;
        if (!Objects.equals(thingId, comment.thingId)) return false;
        if (!Objects.equals(userId, comment.userId)) return false;
        if (!Objects.equals(content, comment.content)) return false;
        return Objects.equals(createDateTime, comment.createDateTime);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (thingId != null ? thingId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createDateTime != null ? createDateTime.hashCode() : 0);
        return result;
    }
}
