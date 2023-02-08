package com.trinity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentCombination implements Serializable {
    /**
     * ID编号
     */
    private Integer id;

    /**
     * 发表评论的用户名称
     */
    private String name;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 博客ID名称
     */
    private String blog;

    /**
     * 发布时间
     */
    private Date time;

    /**
     * 是否是管理员
     */
    private Integer isAdmin;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 父评论的id
     */
    private Integer parent;

    public CommentCombination(Comment comment, Blog blog) {
        this.id = comment.getId();
        this.name = comment.getName();
        this.email = comment.getEmail();
        this.blog = blog.getTitle();
        this.time = comment.getTime();
        this.isAdmin = comment.getIsAdmin();
        this.content = comment.getContent();
        this.parent = comment.getParent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentCombination that = (CommentCombination) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(blog, that.blog) &&
                Objects.equals(time, that.time) &&
                Objects.equals(isAdmin, that.isAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, blog, time, isAdmin, content, parent);
    }
}
