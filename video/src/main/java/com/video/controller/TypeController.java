package com.video.controller;

import com.commons.entity.VideoType;
import com.video.service.VideoCommentService;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class TypeController {
    @Autowired
    VideoService videoService;

    @Autowired
    VideoCommentService videoCommentService;
    @PostMapping(value = "/videoAddType")
    public String videoAddType(String type, ModelMap model ) {
        if(type!=null){
            VideoType videoType = videoService.getTypeBySTypeName(type);
            if(videoType==null){
                VideoType videoType1 = new VideoType();
                videoType1.setTypeName(type);
                videoService.addTypeByTypeName(videoType1);
                return "admin/uploadSuccess";
            }else{
                model.addAttribute("error","已有类型！");
                return "control/videoTypeAdd";

            }
        }else{
            model.addAttribute("error","请输入类型！");
            return "control/videoTypeAdd";
        }

    }
}
