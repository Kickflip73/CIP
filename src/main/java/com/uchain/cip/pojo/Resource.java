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
 * @TableName resource
 */
@TableName(value ="resource")
@Data
public class Resource implements Serializable {
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
     * 帖子类型（1：分享资源帖子 / 2：求资源帖子）
     */
    private Integer postType;

    /**
     * 资源名
     */
    private String resourceName;

    /**
     * 资源类型
     */
    private String resourceType;

    /**
     * 描述
     */
    private String description;

    /**
     * 联系方式
     */
    private String contactInformation;

    /**
     * 金额
     */
    private Double money;

    /**
     * 资源链接
     */
    private String interlink;

    /**
     * 创建日期时间
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
        Resource other = (Resource) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getPostType() == null ? other.getPostType() == null : this.getPostType().equals(other.getPostType()))
            && (this.getResourceName() == null ? other.getResourceName() == null : this.getResourceName().equals(other.getResourceName()))
            && (this.getResourceType() == null ? other.getResourceType() == null : this.getResourceType().equals(other.getResourceType()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getContactInformation() == null ? other.getContactInformation() == null : this.getContactInformation().equals(other.getContactInformation()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getInterlink() == null ? other.getInterlink() == null : this.getInterlink().equals(other.getInterlink()))
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
        result = prime * result + ((getResourceName() == null) ? 0 : getResourceName().hashCode());
        result = prime * result + ((getResourceType() == null) ? 0 : getResourceType().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getContactInformation() == null) ? 0 : getContactInformation().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getInterlink() == null) ? 0 : getInterlink().hashCode());
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
        sb.append(", resourceName=").append(resourceName);
        sb.append(", resourceType=").append(resourceType);
        sb.append(", description=").append(description);
        sb.append(", contactInformation=").append(contactInformation);
        sb.append(", money=").append(money);
        sb.append(", interlink=").append(interlink);
        sb.append(", createDateTime=").append(createDateTime);
        sb.append(", views=").append(views);
        sb.append(", comments=").append(comments);
        sb.append(", hot=").append(hot);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}