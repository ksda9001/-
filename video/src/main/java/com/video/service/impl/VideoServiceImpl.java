package com.video.service.impl;

import com.commons.entity.Video;
import com.commons.entity.VideoClick;
import com.commons.entity.VideoType;
import com.video.mapper.VideoMapper;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper videoMapper;

    @Override
    public Boolean addVideo(Video video) {
        return videoMapper.addVideo(video);
    }

    @Override
    public Boolean addVideoClick(VideoClick videoClick) {
        return videoMapper.addVideoClick(videoClick);
    }

    @Override
    public Integer selectVideoClick(VideoClick videoClick) {
        return videoMapper.selectVideoClick(videoClick);
    }

    @Override
    public void addVideoClickNum(VideoClick videoClick) {
        videoMapper.addVideoClickNum(videoClick);
    }

    @Override
    public List<Video> getVideoList() {
        return videoMapper.getVideoList();
    }

    @Override
    public List<Video> getVideoListOfLike(String username) {
        Integer ty = getVideoTypeByLast(username);
        List<Video> videoList1 = getVideoListByType(ty);
        String au = getVideoAuthorByNum(username);
        List<Video> videoList2 = getVideoListByAuthor(au);
        //可在此加入随机化
        List<Video> v3 = new ArrayList<>();
        videoList1.addAll(videoList2);
        Map<Integer, Video> map = new HashMap<>();
        for (Video video : videoList1) {
            Integer id = video.getId();
            if (!map.containsKey(id)) {
                map.put(id, video);
                v3.add(video);
            }
        }
        return v3.subList(0, 4);
    }

    @Override
    public Integer getVideoTypeByLast(String username) {
        return videoMapper.getVideoTypeByLast(username);
    }

    @Override
    public String getVideoAuthorByNum(String username) {
        return videoMapper.getVideoAuthorByNum(username);
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
    public List<Video> getVideoListRecord(String username) {
        return videoMapper.getVideoListRecord(username);
    }

    @Override
    public List<Video> getOtherVideoListByType(Integer id) {
        return videoMapper.getOtherVideoListByType(id);
    }

    @Override
    public List<Video> getVideoListByAuthor(String author) {
        return videoMapper.getVideoListByAuthor(author);
    }

    @Override
    public List<Video> getVideoListByOnly(VideoClick videoClick) {
        return videoMapper.getVideoListByOnly(videoClick);
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
    public void addVideoTc(Integer id) {
        videoMapper.addVideoTc(id);
    }

    @Override
    public void delVideoTc(Integer id) {
        videoMapper.delVideoTc(id);
    }

    @Override
    public VideoType getTypeBySTypeName(String typeName) {
        return videoMapper.getTypeByTypeName(typeName);
    }

    @Override
    public void addTypeByTypeName(VideoType videoType) {
        videoMapper.addTypeByTypeName(videoType);
    }
}
