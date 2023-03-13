package com.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoCommentCombination implements Serializable {
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
     * 视频ID名称
     */
    private String video;

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

    public VideoCommentCombination(VideoComment videoComment, Video video) {
        this.id = videoComment.getId();
        this.name = videoComment.getName();
        this.email = videoComment.getEmail();
        this.video = video.getTitle();
        this.time = videoComment.getTime();
        this.isAdmin = videoComment.getIsAdmin();
        this.content = videoComment.getContent();
        this.parent = videoComment.getParent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoCommentCombination that = (VideoCommentCombination) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(video, that.video) &&
                Objects.equals(time, that.time) &&
                Objects.equals(isAdmin, that.isAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, video, time, isAdmin, content, parent);
    }
}
