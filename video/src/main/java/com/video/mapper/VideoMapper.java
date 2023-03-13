package com.video.mapper;

import com.commons.entity.Video;
import com.commons.entity.VideoType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoMapper {
    Boolean addVideo(Video video);

    List<Video> getVideoList();

    List<Video> getVideoListByHeat();

    List<Video> getVideoListBySystem();

    List<Video> getVideoListByType(Integer typeId);

    List<Video> getOtherVideoListByType(Integer id);

    Video getVideoById(Integer id);

    Integer deleteVideoById(Integer id);

    Integer selectTypeIdByName(String typeName);

    List<Video> getVideoByName(String title);

    Boolean addVideoPushById(Video video);

    void addVideoVv(Integer id);

    void addVideoDownload(Integer id);

    List<VideoType> getOneType();

    VideoType getTypeByTypeName(String typeName);

    VideoType getTypeByTypeId(Integer id);

    void addTypeByTypeName(VideoType typeName);
}
