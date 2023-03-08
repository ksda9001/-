package com.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog implements Serializable {

    /**
     * ID编号
     */
    private Integer id;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客摘要
     */
    private String summary;

    /**
     * 博客内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date date;

    /**
     * 所属专栏
     */
    private Integer type;

    /**
     * 浏览量
     */
    private Integer views = 0;

    /**
     * 标签
     */
    private String tags;

    /**
     * 评论
     */
    private String comments;

    /**
     * 首图地址
     */
    private String pictureUrl;

    /**
     * 是否开启推荐
     */
    private Integer isRecommend = 0;

    /**
     * 是否开启转载声明
     */
    private Integer isReprint = 0;

    /**
     * 是否开启赞赏
     */
    private Integer isAppreciation = 0;

    /**
     * 是否开启评论
     */
    private Integer isComment = 0;

    /**
     * 1.原创；2.转载；3.翻译
     */
    private Integer property = 1;

    /**
     * 状态：0.草稿；1.已发布
     */
    private Integer state;
}