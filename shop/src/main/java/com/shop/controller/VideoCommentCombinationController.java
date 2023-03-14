//package com.shop.controller;
//
//import com.video.service.VideoCommentCombinationService;
//import com.video.util.MapUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Map;
//
//@Controller
//public class VideoCommentCombinationController {
//    @Autowired
//    VideoCommentCombinationService videoCommentCombinationService;
//
//    @GetMapping("/VideoCommentCombination")
//    public String findCommentCombinationByPage(@RequestParam Map<String, Object> map, Model model) {
//        model.addAttribute("comments", videoCommentCombinationService.findVideoCommentByPage(MapUtil.handle(map)));
//        return "control/comments::table_refresh";
//    }
//
//}
