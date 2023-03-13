package com.video.service.impl;

import com.commons.entity.Comment;
import com.commons.entity.VideoComment;
import com.video.mapper.VideoCommentMapper;
import com.video.service.VideoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class VideoCommentServiceImpl implements VideoCommentService {
    @Autowired
    VideoCommentMapper videoCommentMapper;
    @Override
    public int insertVideoComment(VideoComment videoComment) {
        videoComment.setTime(new Date());
        return videoCommentMapper.insertVideoComment(videoComment);
    }

    @Override
    public int deleteVideoCommentById(Integer id) {
        return videoCommentMapper.deleteVideoCommentById(id);
    }

    @Override
    public int updateVideoComment(VideoComment videoComment) {
        return videoCommentMapper.updateVideoComment(videoComment);
    }

    @Override
    public List<VideoComment> findVideoCommentByPage(Map<String, Object> map) {
        return videoCommentMapper.findVideoCommentByPage(map);
    }

    @Override
    public VideoComment findVideoCommentById(Integer id) {
        return videoCommentMapper.findVideoCommentById(id);
    }

    @Override
    public Map<String, List<VideoComment>> findVideoCommentsByVideo(Integer id) {
        Map<String, List<VideoComment>> map = new HashMap<>(2);
        map.put("parents", videoCommentMapper.findPVideoCommentByVideoId(id));
        map.put("sons", videoCommentMapper.findSVideoCommentByVideoId(id));
        return map;
    }
}
