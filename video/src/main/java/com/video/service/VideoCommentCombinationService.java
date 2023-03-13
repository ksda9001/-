package com.video.service;

import com.commons.entity.Comment;
import com.commons.entity.CommentCombination;
import com.commons.entity.VideoComment;
import com.commons.entity.VideoCommentCombination;

import java.util.List;
import java.util.Map;

public interface VideoCommentCombinationService {
    /**
     * 插入一条评论
     *
     * @param comment
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
     * 分页查找
     *
     * @param map
     * @return
     */
    List<VideoCommentCombination> findVideoCommentByPage(Map<String, Object> map);

    /**
     * 根据主键id查询评论
     *
     * @param id
     * @return
     */
    VideoCommentCombination findVideoCommentById(Integer id);
}
