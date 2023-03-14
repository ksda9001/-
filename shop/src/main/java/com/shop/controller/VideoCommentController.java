//package com.shop.controller;
//
//import com.commons.entity.VideoComment;
//import com.video.service.VideoCommentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpSession;
//
//
//@Controller
//public class VideoCommentController {
//    @Autowired
//    VideoCommentService videoCommentService;
//
//    @PostMapping("/videoComment")
//    public String insertVideoComment(VideoComment videoComment, HttpSession session) {
//        if (session.getAttribute("user") == null) {
//            videoComment.setIsAdmin(0);
//        } else {
//            videoComment.setIsAdmin(1);
//        }
//        videoCommentService.insertVideoComment(videoComment);
//        return "redirect:/videoPlayById?id=" + videoComment.getVideo();
//    }
//
//    @DeleteMapping("/videoComment")
//    public ModelAndView deleteVideoComment(Integer id) {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("tip");
//        if (videoCommentService.deleteVideoCommentById(id) == 1) {
//            mv.addObject("message", "评论删除成功！");
//        } else {
//            mv.addObject("message", "评论删除失败！");
//        }
//        return mv;
//    }
//
//
//}
