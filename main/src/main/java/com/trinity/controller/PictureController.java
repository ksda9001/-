package com.trinity.controller;

import com.commons.entity.Picture;
import com.commons.entity.Video;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trinity.service.PictureService;
import com.trinity.util.XMLUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

@Controller
public class PictureController {
    @Autowired
    PictureService pictureService;

    @GetMapping(value = "/getPictureList")
    public String getPictureList(@RequestParam(value = "pageNum", defaultValue = "1") Integer num, Model model) {
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("uploadTime desc");
        List<Video> videoList = pictureService.getPictureList();

        PageInfo pageInfo = new PageInfo(videoList);
        model.addAttribute("pageInfo", pageInfo);
        return "pictureUpload/pictureList";
    }

    @GetMapping(value = "/getPictureByNameByAdmin")
    public String getPictureByNameByAdmin(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                          String title, Model model) {
        PageHelper.startPage(pageNum, 5);
        PageHelper.orderBy("uploadTime desc");
        List<Video> list = pictureService.getPictureByName(title);

        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo", pageInfo);
        return "pictureUpload/pictureList";
    }

    @PostMapping(value = "/pictureUpload")
    public String pictureUpload(String title, MultipartFile file, Model model) throws IOException {
        Picture picture = new Picture();
        String pictureName = file.getOriginalFilename();  //获取上传后的文件名
//        String path = request.getServletContext().getRealPath("video");
        //文件可进行重命名去重复
        String newPictureName = this.getName(pictureName);  //根据上传的文件名重新生成一份新的文件名
        String path = XMLUtil2.getText();
        File picturePath = new File(path, newPictureName);

        if (!picturePath.getParentFile().exists()) {
            picturePath.getParentFile().mkdirs();
        }

        file.transferTo(picturePath);

        picture.setPath("/picture/" + newPictureName);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        picture.setUploadTime(timestamp);
        picture.setTitle(title);
        picture.setSize(this.getSize(picturePath));
        boolean result = pictureService.addPicture(picture);
        if (result) {
            model.addAttribute("message", "上传成功！");
            return "/main/admin/tip";
        } else {
            model.addAttribute("message", "上传失败！");
            return "/main/admin/tip";
        }
    }

    //上传文件重命名去重复
    private String getName(String fileName) {
        String random = UUID.randomUUID().toString().toUpperCase();
        String ext = this.getFileExt(fileName);
        //原文件名去后缀名、加随机字符串再补回后缀名
        return fileName.replace(ext, "") + random + ext;
    }

    //文件后缀名获取器
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    @GetMapping(value = "/deletePictureById")
    public String deletePictureById(Integer id, Model model) {

        Picture picture = pictureService.getPictureById(id);

        if (picture != null) {
            int result = pictureService.deletePictureById(id);
            if (result > 0) {
                //去path中重复的“/picture/”
                String path = picture.getPath().replace("/picture/", "");
                File file = new File(XMLUtil2.getText() + path);
                if (file.exists()) {
                    file.delete();
                    return "forward:/getPictureList";
                }
            }
        }
        model.addAttribute("message", "删除失败");
        return "/main/admin/tip";
    }

    @GetMapping(value = "/downloadPictureById")
    public void downloadPictureById(Integer id, HttpServletResponse response) throws IOException {

        Picture picture = pictureService.getPictureById(id);
        String dict = XMLUtil2.getText();
        String path = picture.getPath().replace("/picture/", "");
        File file = new File(dict, path);
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(picture.getPath(), "UTF-8"));
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
}
