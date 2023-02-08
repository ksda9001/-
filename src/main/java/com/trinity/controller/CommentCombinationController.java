package com.trinity.controller;

import com.trinity.service.CommentCombinationService;
import com.trinity.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CommentCombinationController {
    @Autowired
    CommentCombinationService commentCombinationService;

    @GetMapping("/commentCombination")
    public String findCommentCombinationByPage(@RequestParam Map<String, Object> map, Model model) {
        model.addAttribute("comments", commentCombinationService.findCommentByPage(MapUtil.handle(map)));
        return "main/admin/comments::table_refresh";
    }

}
