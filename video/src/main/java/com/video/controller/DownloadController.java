package com.video.controller;

import com.commons.entity.Video;
import com.video.service.VideoCommentService;
import com.video.service.VideoService;
import com.video.util.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
public class DownloadController {
    @Autowired
    VideoService videoService;

    @Autowired
    VideoCommentService videoCommentService;

    @GetMapping(value = "/downloadVideoById")
    public void downloadVideoById(Integer id, HttpServletResponse response) throws IOException {

        Video video = videoService.getVideoById(id);
        String dict = XMLUtil.getText();
        File file = new File(dict, video.getPath());
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(video.getPath(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        FileInputStream fis = null;
        OutputStream fos = response.getOutputStream();
        if (file.exists()) {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        }
        fis.close();
        fos.close();
        videoService.addVideoDownload(id);
    }
}
