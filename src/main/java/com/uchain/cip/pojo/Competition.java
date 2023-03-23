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
 * @TableName competition
 */
@TableName(value ="competition")
@Data
public class Competition implements Serializable {
    /**
     * id（自增）
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 发布者id
     */
    private Long ownerId;

    /**
     * 帖子类型（1：找队伍 / 2：找队友）
     */
    private Integer postType;

    /**
     * 标题
     */
    private String title;

    /**
     * 比赛名称
     */
    private String competitionName;

    /**
     * 比赛类型
     */
    private String competitionType;

    /**
     * 正文描述
     */
    private String description;

    /**
     * 联系方式
     */
    private String contactInformation;

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
     * 收藏数
     * */
    private Integer stars;

    /**
     * 热度（浏览量 + 评论数*3 + 收藏数*5）
     */
    private Integer hot;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Competition other = (Competition) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getPostType() == null ? other.getPostType() == null : this.getPostType().equals(other.getPostType()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getCompetitionName() == null ? other.getCompetitionName() == null : this.getCompetitionName().equals(other.getCompetitionName()))
            && (this.getCompetitionType() == null ? other.getCompetitionType() == null : this.getCompetitionType().equals(other.getCompetitionType()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getContactInformation() == null ? other.getContactInformation() == null : this.getContactInformation().equals(other.getContactInformation()))
            && (this.getCreateDateTime() == null ? other.getCreateDateTime() == null : this.getCreateDateTime().equals(other.getCreateDateTime()))
            && (this.getViews() == null ? other.getViews() == null : this.getViews().equals(other.getViews()))
            && (this.getComments() == null ? other.getComments() == null : this.getComments().equals(other.getComments()))
            && (this.getHot() == null ? other.getHot() == null : this.getHot().equals(other.getHot()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getPostType() == null) ? 0 : getPostType().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getCompetitionName() == null) ? 0 : getCompetitionName().hashCode());
        result = prime * result + ((getCompetitionType() == null) ? 0 : getCompetitionType().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getContactInformation() == null) ? 0 : getContactInformation().hashCode());
        result = prime * result + ((getCreateDateTime() == null) ? 0 : getCreateDateTime().hashCode());
        result = prime * result + ((getViews() == null) ? 0 : getViews().hashCode());
        result = prime * result + ((getComments() == null) ? 0 : getComments().hashCode());
        result = prime * result + ((getHot() == null) ? 0 : getHot().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", postType=").append(postType);
        sb.append(", title=").append(title);
        sb.append(", competitionName=").append(competitionName);
        sb.append(", competitionType=").append(competitionType);
        sb.append(", description=").append(description);
        sb.append(", contactInformation=").append(contactInformation);
        sb.append(", createDateTime=").append(createDateTime);
        sb.append(", views=").append(views);
        sb.append(", comments=").append(comments);
        sb.append(", hot=").append(hot);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
