package com.shop.controller;

import com.shop.service.ShopCommentCombinationService;
import com.shop.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ShopCommentCombinationController {
    @Autowired
    ShopCommentCombinationService shopCommentCombinationService;

    @GetMapping("/ShopCommentCombination")
    public String findCommentCombinationByPage(@RequestParam Map<String, Object> map, Model model) {
        model.addAttribute("comments", shopCommentCombinationService.findShopCommentByPage(MapUtil.handle(map)));
        return "shopControl/comments::table_refresh";
    }

}
