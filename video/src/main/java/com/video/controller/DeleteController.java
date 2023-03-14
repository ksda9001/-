package com.video.controller;

import com.commons.entity.Video;
import com.video.service.VideoCommentService;
import com.video.service.VideoService;
import com.video.util.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

@Controller
public class DeleteController {
    @Autowired
    VideoService videoService;

    @Autowired
    VideoCommentService videoCommentService;

    @GetMapping(value = "/deleteVideoById")
    public String deleteVideoById(Integer id, Model model) {

        Video video = videoService.getVideoById(id);

        if (video != null) {
            int result = videoService.deleteVideoById(id);
            if (result > 0) {
                //去path中重复的“/video/”
                String path = video.getPath().replace("/video/", "");
                File file = new File(XMLUtil.getText() + path);
                if (file.exists()) {
                    file.delete();
                    return "forward:/getVideoList";
                }
            }
        }
        model.addAttribute("message","删除失败");
        return "tip";
    }
}
