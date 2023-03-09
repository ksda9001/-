package com.trinity.controller;

import com.commons.entity.Comment;
import com.trinity.service.BlogService;
import com.trinity.service.CommentService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    BlogService blogService;

    @PostMapping("/comment")
    public String insertComment(Comment comment, HttpSession session) {
        if (session.getAttribute("user") == null) {
            comment.setIsAdmin(0);
        } else {
            comment.setIsAdmin(1);
        }
        commentService.insertComment(comment);
        return "redirect:/page_blog/" + comment.getBlog();
    }

    @DeleteMapping("/comment")
    public ModelAndView deleteComment(Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main/admin/tip");
        if (commentService.deleteCommentById(id) == 1) {
            mv.addObject("message", "评论删除成功！");
        } else {
            mv.addObject("message", "评论删除失败！");
        }
        return mv;
    }
}
