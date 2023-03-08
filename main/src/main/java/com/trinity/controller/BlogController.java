package com.trinity.controller;

import com.commons.entity.Blog;
import com.trinity.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    @PostMapping(value = "/blog")
    public ModelAndView insertBlog(Blog blog) {
        ModelAndView mv = new ModelAndView();
        //配合查找
        blog.setTags("," + blog.getTags() + ",");
        mv.setViewName("main/admin/tip");
        if (blogService.addBlog(blog) == 1) {
            mv.addObject("message", "博客保存成功!");
        } else {
            mv.addObject("message", "博客保存失败!");
        }
        return mv;
    }

    @DeleteMapping(value = "/blog")
    public ModelAndView deleteBlog(@RequestParam Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main/admin/tip");
        if (blogService.deleteBlog(id) == 1) {
            mv.addObject("message", "博客删除成功!");
        } else {
            mv.addObject("message", "博客删除失败!");
        }
        return mv;
    }

    @PutMapping(value = "/blog")
    public ModelAndView update(Blog blog) {
        blog.setTags("," + blog.getTags() + ",");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main/admin/tip");
        if (blogService.updateBlog(blog) == 1) {
            mv.addObject("message", "博客更新成功!");
        } else {
            mv.addObject("message", "博客更新失败!");
        }
        return mv;
    }
}
