package com.video.mapper;


import com.commons.entity.Comment;
import com.commons.entity.VideoComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface VideoCommentMapper {
    /**
     * 插入一条评论
     *
     * @param comment
     * @return
     */
    int insertVideoComment(VideoComment comment);

    /**
     * 根据主键删除一条评论
     *
     * @param id
     * @return
     */
    int deleteVideoCommentById(Integer id);

    /**
     * 根据主键删除多条评论
     *
     * @param ids
     * @return
     */
    int deleteVideoCommentByIds(List<Integer> ids);

    /**
     * 更新一条评论
     *
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
     * 根据id查询父级评论
     *
     * @return
     */
    List<VideoComment> findPVideoCommentByVideoId(Integer video);

    /**
     * 根据id查询子级评论
     *
     * @return
     */
    List<VideoComment> findSVideoCommentByVideoId(Integer video);
}
