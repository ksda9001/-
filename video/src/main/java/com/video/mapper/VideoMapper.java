package com.video.mapper;

import com.commons.entity.Video;
import com.commons.entity.VideoClick;
import com.commons.entity.VideoType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoMapper {
    Boolean addVideo(Video video);

    Boolean addVideoClick(VideoClick videoClick);

    Integer selectVideoClick(VideoClick videoClick);

    void addVideoClickNum(VideoClick videoClick);

    List<Video> getVideoList();

    List<Video> getVideoListByHeat();

    List<Video> getVideoListBySystem();

    List<Video> getVideoListByType(Integer typeId);

    List<Video> getOtherVideoListByType(Integer id);

    List<Video> getVideoListByAuthor(String author);

    List<Video> getVideoListRecord(String username);

    List<Video> getVideoListByOnly(VideoClick videoClick);

    Video getVideoById(Integer id);

    Integer deleteVideoById(Integer id);

    Integer selectTypeIdByName(String typeName);

    List<Video> getVideoByName(String title);

    Boolean addVideoPushById(Video video);

    void addVideoVv(Integer id);

    void addVideoDownload(Integer id);

    void addVideoTc(Integer id);

    void delVideoTc(Integer id);

    List<VideoType> getOneType();

    VideoType getTypeByTypeName(String typeName);

    VideoType getTypeByTypeId(Integer id);

    void addTypeByTypeName(VideoType typeName);

    Integer getVideoTypeByLast(String username);

    String getVideoAuthorByNum(String username);
}
