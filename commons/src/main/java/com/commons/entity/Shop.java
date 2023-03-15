package com.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop implements Serializable {

    /**
     * ID编号
     */
    private Integer id;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 金额
     */
    private String cost;

    /**
     * 描述内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date date;

    /**
     * 浏览量
     */
    private Integer views = 0;

    /**
     * 标签
     */
    private int typeId;

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
     * 是否开启评论
     */
    private Integer isComment = 0;

    /**
     * 状态：0.草稿；1.已发布
     */
    private Integer state;
}
