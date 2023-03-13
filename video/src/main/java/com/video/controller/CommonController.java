package com.video.controller;

import com.commons.entity.VideoType;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CommonController {
    @Autowired
    VideoService videoService;

    @GetMapping("/videoUpload")
    public String videoUpload(Model model){
        List<VideoType> videoTypeList = videoService.getOneType();
        model.addAttribute("types",videoTypeList);
        return "control/videoUpload";
    }

    @GetMapping("/playTest")
    public String playTest(){
        return "playTest";
    }

    @GetMapping(value = "/toAddVideoTypeList")
    public String toAddVideoTypeList() { return "control/videoTypeAdd"; }
}
