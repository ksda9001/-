package com.trinity.mapper;

import com.commons.entity.Picture;
import com.commons.entity.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PictureMapper {
    Boolean addPicture(Picture picture);
    Picture getPictureById(Integer id);

    Integer deletePictureById(Integer id);

    List<Video> getPictureList();

    List<Video> getPictureByName(String title);
}
