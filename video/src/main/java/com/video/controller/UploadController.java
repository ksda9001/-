package com.video.controller;

import com.commons.entity.Users;
import com.commons.entity.Video;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.UUID;

@Controller
public class UploadController {
    @Autowired
    VideoService videoService;

    //视频上传器
    @PostMapping("/videoUpload")
    public void videoUpload(String title, String pictureUrl, String type, MultipartFile file, HttpServletRequest request, HttpSession session, Model model) throws IOException {
        String extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //Artplayer默认支持mp4、ogg、webm
        if (!extName.equals(".mp4") && !extName.equals(".ogg") && !extName.equals(".webm")) {
            model.addAttribute("msg", "上传格式错误，仅支持ogg,webm,mp4。");
        } else {
            Video video = new Video();
            //获取上传后的文件名
            String videoName = file.getOriginalFilename();
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/videoResource";
            //文件可进行重命名去重复
            String newVideoName = this.getName(videoName);  //根据上传的文件名重新生成一份新的文件名
            File videoPath = new File(path, newVideoName);
            if (!videoPath.getParentFile().exists()) {
                videoPath.getParentFile().mkdirs();
            }
            file.transferTo(videoPath);
            //TODO 后期添加用户模块
//            Users user = (Users) session.getAttribute("user");
//            video.setAuthor(user.getUserName());
            video.setPath("/videoResource/" + newVideoName);
            //跨平台存疑
            //获取系统时钟
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            video.setUploadTime(timestamp);
            //视频标题
            video.setTitle(title);
            //视频封面
            video.setPictureUrl(pictureUrl);
            //文件大小
            video.setSize(this.getSize(videoPath));
            //获取文件后缀名
            video.setType(this.getFileExt(newVideoName));
            Integer typeId = videoService.selectTypeIdByName(type);
            video.setTypeId(typeId);
            boolean result = videoService.addVideo(video);
            //返回处理消息
            if (result) {
                model.addAttribute("msg", "上传成功！");
            } else {
                model.addAttribute("msg", "上传失败！");
            }
        }
    }

    //上传文件重命名去重复
    private String getName(String fileName) {
        String random = UUID.randomUUID().toString().toUpperCase();
        String ext = this.getFileExt(fileName);
        //原文件名去后缀名、加随机字符串再补回后缀名
        return fileName.replace(ext, "") + random + ext;
    }

    //文件大小获取器
    private String getSize(File file) {
        String size = "";
        long fileLength = file.length();
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileLength < 1024) {
            size = df.format((double) fileLength) + "BT";
        } else if (fileLength < 1048576) {
            size = df.format((double) fileLength / 1024) + "KB";
        } else if (fileLength < 1073741824) {
            size = df.format((double) fileLength / 1048576) + "MB";
        } else {
            size = df.format((double) fileLength / 1073741824) + "GB";
        }
        return size;
    }

    //文件后缀名获取器
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
