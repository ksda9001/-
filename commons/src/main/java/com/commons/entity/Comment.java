package com.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {

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
     * 博客ID编号
     */
    private Integer blog;

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
}