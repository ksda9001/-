package com.trinity.service.impl;

import com.commons.entity.Picture;
import com.commons.entity.Video;
import com.trinity.mapper.PictureMapper;
import com.trinity.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    PictureMapper pictureMapper;

    @Override
    public Boolean addPicture(Picture picture) {
        return pictureMapper.addPicture(picture);
    }

    @Override
    public Picture getPictureById(Integer id) {
        return pictureMapper.getPictureById(id);
    }

    @Override
    public Integer deletePictureById(Integer id) {
        return pictureMapper.deletePictureById(id);
    }

    @Override
    public List<Video> getPictureList() {
        return pictureMapper.getPictureList();
    }

    @Override
    public List<Video> getPictureByName(String title) {
        return pictureMapper.getPictureByName(title);
    }
}
