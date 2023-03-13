package com.video.service;

import com.commons.entity.Video;
import com.commons.entity.VideoType;

import java.util.List;

public interface VideoService {
    Boolean addVideo(Video video);

    List<Video> getVideoList();

    List<Video> getVideoListByHeat();

    List<Video> getVideoListBySystem();

    List<Video> getVideoListByType(Integer typeId);

    List<VideoType> getOneType();

    Video getVideoById(Integer id);

    Integer deleteVideoById(Integer id);

    Integer selectTypeIdByName(String typeName);

    List<Video> getVideoByName(String title);

    Boolean addVideoPushById(Video video);

    void addVideoVv(Integer id);

    void addVideoDownload(Integer id);

    VideoType getTypeBySTypeName(String typeName);

    void addTypeByTypeName(VideoType videoType);

    VideoType getTypeByTypeId(Integer id);
}
