package com.video.controller;

import com.commons.entity.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.service.VideoCommentService;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
public class VideoController {
    @Autowired
    VideoService videoService;

    @Autowired
    VideoCommentService videoCommentService;

    @GetMapping("/videoPlayById")
    public String videoPlayById(Integer id, Model model, HttpSession session) {
//        Users user = (Users) session.getAttribute("user");
        Video video = videoService.getVideoById(id);
        Integer typeId = video.getTypeId();
        VideoType videoType = videoService.getTypeByTypeId(typeId);
        model.addAttribute("title", video.getTitle());
        model.addAttribute("type", videoType.getTypeName());
        model.addAttribute("path", video.getPath());
        model.addAttribute("time", video.getUploadTime());
        model.addAttribute("vv", video.getVv());
        model.addAttribute("download",video.getDownload());
        model.addAttribute("pic", video.getPictureUrl());
        model.addAttribute("pictureUrl", video.getPictureUrl());
        model.addAttribute("property", video.getProperty());
        model.addAttribute("isReprint", video.getIsReprint());
        model.addAttribute("isComment", video.getIsComment());
        model.addAttribute("video_id", video.getId());
        videoService.addVideoVv(id);


        Map<String, List<VideoComment>> map = videoCommentService.findVideoCommentsByVideo(id);
        model.addAttribute("parents", map.get("parents"));
        model.addAttribute("sons", map.get("sons"));


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


    @GetMapping(value = "/getVideoByNameByAdmin")
    public String getVideoByNameByAdmin(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        String title, ModelMap model) {
        PageHelper.startPage(pageNum, 5);
        PageHelper.orderBy("uploadTime desc");
        List<Video> list = videoService.getVideoByName(title);

        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo", pageInfo);
        return "control/videoList";
    }

    @GetMapping(value = "/getVideoList")
    public String getVideoList(@RequestParam(value = "pageNum", defaultValue = "1") Integer num, ModelMap model) {
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("uploadTime desc");
        List<Video> videoList = videoService.getVideoList();

        PageInfo pageInfo = new PageInfo(videoList);
        model.addAttribute("pageInfo", pageInfo);
        return "control/videoList";
    }

    @GetMapping(value = "/videoPushById")
    public String changeRoleById(Integer id, Model model) {
        Video video = videoService.getVideoById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        if (video.getPush() == 0) {
            video.setPush(1);
            video.setPushTime(timestamp);
        } else {
            video.setPush(0);
            video.setPushTime(null);
        }


        boolean result = videoService.addVideoPushById(video);
        if (result) {
            return "forward:/getVideoList";
        }
        model.addAttribute("message", "推送失败！");
        return "tip";
    }
}
