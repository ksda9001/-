package com.video.service.impl;

import com.commons.entity.Comment;
import com.commons.entity.CommentCombination;
import com.commons.entity.VideoComment;
import com.commons.entity.VideoCommentCombination;
import com.video.service.VideoCommentCombinationService;
import com.video.service.VideoCommentService;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class VideoCommentCombinationServiceImpl implements VideoCommentCombinationService {
    @Autowired
    VideoCommentService videoCommentService;
    @Autowired
    VideoService videoService;

    @Override
    public int insertVideoComment(VideoComment videoComment) {
        videoComment.setTime(new Date());
        return videoCommentService.insertVideoComment(videoComment);
    }

    @Override
    public int deleteVideoCommentById(Integer id) {
        return videoCommentService.deleteVideoCommentById(id);
    }

    @Override
    public List<VideoCommentCombination> findVideoCommentByPage(Map<String, Object> map) {
        Object title = map.get("video");
        if (title != null && title != "") {
            map.replace("video", videoService.getVideoByName(String.valueOf(title)));
        }
        List<VideoComment> videoComments = videoCommentService.findVideoCommentByPage(map);
        List<VideoCommentCombination> videoCommentCombinations = new ArrayList<>();
        for (VideoComment videoComment : videoComments) {
            VideoCommentCombination videoCommentCombination = new VideoCommentCombination(videoComment, videoService.getVideoById(videoComment.getVideo()));
            videoCommentCombinations.add(videoCommentCombination);
        }
        return videoCommentCombinations;
    }

    @Override
    public VideoCommentCombination findVideoCommentById(Integer id) {
        VideoComment videoComment = videoCommentService.findVideoCommentById(id);
        VideoCommentCombination videoCommentCombination = new VideoCommentCombination(videoComment, videoService.getVideoById(videoComment.getVideo()));

        return videoCommentCombination;
    }
}

