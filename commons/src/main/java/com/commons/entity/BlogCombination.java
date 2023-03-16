package com.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * blog
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogCombination implements Serializable {

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
    private Type type;

    /**
     * 浏览量
     */
    private Integer views = 563;

    /**
     * 标签
     */
    private List<Tag> tags;

    /**
     * 评论
     */
    private List<Comment> comments;

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
     * 是否开启评论
     */
    private Integer isComment = 0;

    /**
     * 1.原创；2.转载；3.翻译
     */
    private Integer property;

    /**
     * 状态：0.草稿；1.已发布
     */
    private Integer state;

    public BlogCombination(Blog blog, Type type, List<Tag> tags, List<Comment> comments) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.summary = blog.getSummary();
        this.content = blog.getContent();
        this.date = blog.getDate();
        this.type = type;
        this.views = blog.getViews();
        this.tags = tags;
        this.comments = comments;
        this.pictureUrl = blog.getPictureUrl();
        this.isRecommend = blog.getIsRecommend();
        this.isReprint = blog.getIsReprint();
        this.isComment = blog.getIsComment();
        this.property = blog.getProperty();
        this.state = blog.getState();
    }

    public BlogCombination(Blog blog, Type type, List<Tag> tags) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.summary = blog.getSummary();
        this.content = blog.getContent();
        this.date = blog.getDate();
        this.type = type;
        this.views = blog.getViews();
        this.tags = tags;
        this.pictureUrl = blog.getPictureUrl();
        this.isRecommend = blog.getIsRecommend();
        this.isReprint = blog.getIsReprint();
        this.isComment = blog.getIsComment();
        this.property = blog.getProperty();
        this.state = blog.getState();
    }

    public BlogCombination(Blog blog, Type type) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.summary = blog.getSummary();
        this.content = blog.getContent();
        this.date = blog.getDate();
        this.type = type;
        this.views = blog.getViews();
        this.pictureUrl = blog.getPictureUrl();
        this.isRecommend = blog.getIsRecommend();
        this.isReprint = blog.getIsReprint();
        this.isComment = blog.getIsComment();
        this.property = blog.getProperty();
        this.state = blog.getState();
    }

    @Override
    public String toString() {
        return "BlogCombination{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", views=" + views +
                ", tags=" + tags +
                ", comments=" + comments +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", isRecommend=" + isRecommend +
                ", isReprint=" + isReprint +
                ", isComment=" + isComment +
                ", property=" + property +
                ", state=" + state +
                '}';
    }
}