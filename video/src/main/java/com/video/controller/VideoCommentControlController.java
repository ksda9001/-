package com.video.controller;

import com.video.service.VideoCommentCombinationService;
import com.video.service.VideoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class VideoCommentControlController {
    @Autowired
    VideoCommentService videoCommentService;
    @Autowired
    VideoCommentCombinationService videoCommentCombinationService;

    @GetMapping("/control/comments")
    public ModelAndView comments() {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> map = new HashMap<>();
        map.put("begin", 0);
        map.put("size", 6);
        mv.addObject("comments", videoCommentCombinationService.findVideoCommentByPage(map));
        mv.setViewName("control/comments");
        return getModelAndView(mv);
    }

    @GetMapping("/control/comments-details/{id}")
    public ModelAndView comments_details(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("comment", videoCommentCombinationService.findVideoCommentById(id));
        mv.setViewName("control/comments-details");
        return getModelAndView(mv);
    }

    /**
     * @param mv
     * @return
     */
    private ModelAndView getModelAndView(ModelAndView mv) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("begin", 0);
        map.put("size", 3);
        map.put("isRecommend", 1);
//        mv.addObject("newBlogs", blogService.findBlogByConditionVisible(map));
        return mv;
    }

}
