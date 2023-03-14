package com.trinity.service;

import com.commons.entity.Picture;
import com.commons.entity.Video;

import java.util.List;

public interface PictureService {
    Boolean addPicture(Picture picture);

    Picture getPictureById(Integer id);

    Integer deletePictureById(Integer id);

    List<Video> getPictureList();

    List<Video> getPictureByName(String title);
}
