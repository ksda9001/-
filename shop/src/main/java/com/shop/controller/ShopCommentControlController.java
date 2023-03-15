package com.shop.controller;

import com.shop.service.ShopCommentCombinationService;
import com.shop.service.ShopCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ShopCommentControlController {
    @Autowired
    ShopCommentService shopCommentService;
    @Autowired
    ShopCommentCombinationService shopCommentCombinationService;

    @GetMapping("/shopControl/comments")
    public ModelAndView comments() {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> map = new HashMap<>();
        map.put("begin", 0);
        map.put("size", 6);
        mv.addObject("comments", shopCommentCombinationService.findShopCommentByPage(map));
        mv.setViewName("/shopControl/comments");
        return getModelAndView(mv);
    }

    @GetMapping("/shopControl/comments-details/{id}")
    public ModelAndView comments_details(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("comment", shopCommentCombinationService.findShopCommentById(id));
        mv.setViewName("/shopControl/comments-details");
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
        return mv;
    }

}
