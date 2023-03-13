package com.video.service.impl;

import com.commons.entity.Video;
import com.commons.entity.VideoType;
import com.video.mapper.VideoMapper;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper videoMapper;

    @Override
    public Boolean addVideo(Video video) {
        return videoMapper.addVideo(video);
    }

    @Override
    public List<Video> getVideoList() {
        return videoMapper.getVideoList();
    }

    @Override
    public List<Video> getVideoListByHeat() {
        return videoMapper.getVideoListByHeat();
    }

    @Override
    public List<Video> getVideoListBySystem() {
        return videoMapper.getVideoListBySystem();
    }

    @Override
    public List<Video> getVideoListByType(Integer typeId) {
        return videoMapper.getVideoListByType(typeId);
    }

    @Override
    public List<VideoType> getOneType() {
        return videoMapper.getOneType();
    }

    @Override
    public Video getVideoById(Integer id) {
        return videoMapper.getVideoById(id);
    }

    @Override
    public Integer deleteVideoById(Integer id) {
        return videoMapper.deleteVideoById(id);
    }

    @Override
    public Integer selectTypeIdByName(String typeName) {
        return videoMapper.selectTypeIdByName(typeName);
    }

    @Override
    public List<Video> getVideoByName(String title) {
        return videoMapper.getVideoByName(title);
    }

    @Override
    public Boolean addVideoPushById(Video video) {
        return videoMapper.addVideoPushById(video);
    }

    @Override
    public void addVideoVv(Integer id) {
        videoMapper.addVideoVv(id);
    }

    @Override
    public void addVideoDownload(Integer id) {
        videoMapper.addVideoDownload(id);
    }

    @Override
    public VideoType getTypeBySTypeName(String typeName) {
        return videoMapper.getTypeByTypeName(typeName);
    }

    @Override
    public void addTypeByTypeName(VideoType videoType) {
        videoMapper.addTypeByTypeName(videoType);
    }

    @Override
    public VideoType getTypeByTypeId(Integer id) {
        return videoMapper.getTypeByTypeId(id);
    }
}
