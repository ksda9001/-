package com.video.controller;

import com.commons.entity.Users;
import com.commons.entity.Video;
import com.commons.entity.VideoClick;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
public class VideoController {
    @Autowired
    VideoService videoService;

    @GetMapping("/videoPlayById/{id}")
    public String videoPlayById(@PathVariable String id, Model model, HttpSession session){
//       TODO 后期加入session

//        Users user = (Users) session.getAttribute("user");
        Video video = videoService.getVideoById(Integer.valueOf(id));
        model.addAttribute("title",video.getTitle());
        model.addAttribute("path",video.getPath());
        model.addAttribute("video_id",video.getId());
        session.setAttribute("videoId",video.getId());
        videoService.addVideoVv(Integer.valueOf(id));

        VideoClick videoClick = new VideoClick();
//        videoClick.setUsername(user.getUsername());
        videoClick.setVideoId(Integer.valueOf(id));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        videoClick.setUpdateTime(timestamp);

        Integer num = videoService.selectVideoClick(videoClick);
        if (num!=1){
            videoService.addVideoClick(videoClick);
        }else {
            videoService.addVideoClickNum(videoClick);
        }

        return "videoPlay";
    }
}
