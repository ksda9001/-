package com.video.controller;

import com.commons.entity.Users;
import com.commons.entity.Video;
import com.commons.entity.VideoClick;
import com.commons.entity.VideoType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class VideoController {
    @Autowired
    VideoService videoService;

    @GetMapping("/videoPlayById/{id}")
    public String videoPlayById(@PathVariable String id, Model model, HttpSession session) {
//       TODO 后期加入session

//        Users user = (Users) session.getAttribute("user");
        Video video = videoService.getVideoById(Integer.valueOf(id));
        model.addAttribute("title", video.getTitle());
        model.addAttribute("path", video.getPath());
        model.addAttribute("video_id", video.getId());
        session.setAttribute("videoId", video.getId());
        videoService.addVideoVv(Integer.valueOf(id));

        VideoClick videoClick = new VideoClick();
//        videoClick.setUsername(user.getUsername());
        videoClick.setVideoId(Integer.valueOf(id));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        videoClick.setUpdateTime(timestamp);

        Integer num = videoService.selectVideoClick(videoClick);
        if (num != 1) {
            videoService.addVideoClick(videoClick);
        } else {
            videoService.addVideoClickNum(videoClick);
        }

        return "videoPlay";
    }

    @GetMapping("/videoPlayById")
    public String videoPlayById(Integer id, Model model, HttpSession session) {
//        Users user = (Users) session.getAttribute("user");
        Video video = videoService.getVideoById(id);
        model.addAttribute("title", video.getTitle());
        model.addAttribute("path", video.getPath());
        model.addAttribute("video_id", video.getId());
        session.setAttribute("videoId", video.getId());
        videoService.addVideoVv(id);

        VideoClick videoClick = new VideoClick();
//        videoClick.setUsername(user.getUsername());
        videoClick.setVideoId(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        videoClick.setUpdateTime(timestamp);

        Integer num = videoService.selectVideoClick(videoClick);
        if (num != 1) {
            videoService.addVideoClick(videoClick);
        } else {
            videoService.addVideoClickNum(videoClick);
        }

        return "videoPlay";
    }

    //获取最新视频
    @GetMapping("/getVideoListByUser")
    public String getVideoListByUser(@RequestParam(value = "pageNum", defaultValue = "1") Integer num, Model model) {
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("uploadTime desc");
        List<Video> videoList = videoService.getVideoList();
        PageInfo pageInfo = new PageInfo(videoList);
        List<VideoType> videoTypeList = videoService.getOneType();
        model.addAttribute("types", videoTypeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("type", 1);
        return "videoIndex";
    }

    //最热视频
    @GetMapping(value = "/getVideoListByHeat")
    public String getVideoListByHeat(@RequestParam(value = "pageNum", defaultValue = "1") Integer num, ModelMap model) {
        PageHelper.startPage(num, 5);
//        PageHelper.orderBy("uploadTime desc");
        List<Video> videoList = videoService.getVideoListByHeat();

        PageInfo pageInfo = new PageInfo(videoList);
        List<VideoType> videoTypeList = videoService.getOneType();
        model.addAttribute("types", videoTypeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("type", 2);
        return "videoIndex";
    }

    //推荐视频
    @GetMapping(value = "/getVideoListBySystem")
    public String getVideoListBySystem(@RequestParam(value = "pageNum", defaultValue = "1") Integer num, ModelMap model) {
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("pushTime desc");
        List<Video> videoList = videoService.getVideoListBySystem();

        PageInfo pageInfo = new PageInfo(videoList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("type", 3);
        List<VideoType> videoTypeList = videoService.getOneType();
        model.addAttribute("types", videoTypeList);
        return "videoIndex";
    }

    @GetMapping(value = "/getVideoByName")
    public String getTopicByName(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 String title, ModelMap model) {
        PageHelper.startPage(pageNum, 5);
        PageHelper.orderBy("uploadTime desc");
        List<Video> list = videoService.getVideoByName(title);

        PageInfo pageInfo = new PageInfo(list);
        List<VideoType> videoTypeList = videoService.getOneType();
        model.addAttribute("types", videoTypeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("type", 0);
        model.addAttribute("title", title);
        return "videoIndex";
    }

    @GetMapping(value = "/getVideoListByType")
    public String getVideoListByType(String value, @RequestParam(value = "pageNum", defaultValue = "1") Integer num, ModelMap model) {
        Integer typeId = videoService.selectTypeIdByName(value);
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("uploadTime desc");
        List<Video> videoList = videoService.getVideoListByType(typeId);

        PageInfo pageInfo = new PageInfo(videoList);
        List<VideoType> videoTypeList = videoService.getOneType();
        model.addAttribute("types", videoTypeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("type", 6);
        return "videoIndex";
    }
}
