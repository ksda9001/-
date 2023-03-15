package com.shop.controller;

import com.commons.entity.ShopComment;
import com.shop.service.ShopCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class ShopCommentController {
    @Autowired
    ShopCommentService shopCommentService;

    @PostMapping("/shopComment")
    public String insertShopComment(ShopComment shopComment, HttpSession session) {
        if (session.getAttribute("user") == null) {
            shopComment.setIsAdmin(0);
        } else {
            shopComment.setIsAdmin(1);
        }
        shopCommentService.insertShopComment(shopComment);
        return "redirect:/shopDetail?id=" + shopComment.getShop();
    }

    @DeleteMapping("/shopComment")
    public ModelAndView deleteShopComment(Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tip");
        if (shopCommentService.deleteShopCommentById(id) == 1) {
            mv.addObject("message", "评论删除成功！");
        } else {
            mv.addObject("message", "评论删除失败！");
        }
        return mv;
    }

}
