package com.video.service;

import com.commons.entity.VideoComment;
import com.commons.entity.VideoComment;

import java.util.List;
import java.util.Map;


public interface VideoCommentService {
    /**
     * 插入一条评论
     *
     * @return
     */
    int insertVideoComment(VideoComment videoComment);

    /**
     * 根据主键删除一条评论
     *
     * @param id
     * @return
     */
    int deleteVideoCommentById(Integer id);

    /**
     * 更新一条评论
     *
     * @param videoComment
     * @return
     */
    int updateVideoComment(VideoComment videoComment);

    /**
     * 分页查找
     *
     * @param map
     * @return
     */
    List<VideoComment> findVideoCommentByPage(Map<String, Object> map);

    /**
     * 根据主键id查询评论
     *
     * @param id
     * @return
     */
    VideoComment findVideoCommentById(Integer id);

    /**
     * 根据视频id查询相对应的评论，并将其变成一定格式返回
     *
     * @param id
     * @return
     */
    Map<String, List<VideoComment>> findVideoCommentsByVideo(Integer id);
}
