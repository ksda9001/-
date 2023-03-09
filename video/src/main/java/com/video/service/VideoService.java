package com.video.service;

import com.commons.entity.Video;
import com.commons.entity.VideoClick;
import com.commons.entity.VideoType;

import java.util.List;

public interface VideoService {
    Boolean addVideo(Video video);

    Boolean addVideoClick(VideoClick videoClick);

    Integer selectVideoClick(VideoClick videoClick);

    void addVideoClickNum(VideoClick videoClick);

    List<Video> getVideoList();

    List<Video> getVideoListOfLike(String username);

    Integer getVideoTypeByLast(String username);

    String getVideoAuthorByNum(String username);

    List<Video> getVideoListByHeat();

    List<Video> getVideoListBySystem();

    List<Video> getVideoListByType(Integer typeId);

    List<Video> getVideoListRecord(String username);

    List<Video> getOtherVideoListByType(Integer id);

    List<Video> getVideoListByAuthor(String author);

    List<Video> getVideoListByOnly(VideoClick videoClick);

    List<VideoType> getOneType();

    Video getVideoById(Integer id);

    Integer deleteVideoById(Integer id);

    Integer selectTypeIdByName(String typeName);

    List<Video> getVideoByName(String title);

    Boolean addVideoPushById(Video video);

    void addVideoVv(Integer id);

    void addVideoDownload(Integer id);

    void addVideoTc(Integer id);

    void delVideoTc(Integer id);

    VideoType getTypeBySTypeName(String typeName);

    void addTypeByTypeName(VideoType videoType);

    VideoType getTypeByTypeId(Integer id);
}
