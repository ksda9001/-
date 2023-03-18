package com.shop.controller;

import com.commons.entity.*;
import com.shop.service.ShopCommentService;
import com.shop.service.ShopService;
import com.shop.util.MarkdownUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ShopDetailController {
    @Autowired
    ShopService shopService;
    @Autowired
    ShopCommentService shopCommentService;

    @GetMapping("/shopDetail")
    public String videoPlayById(Integer id, Model model, HttpSession session) {
//        Users user = (Users) session.getAttribute("user");
        Shop shop = shopService.getShopById(id);
        Integer typeId = shop.getTypeId();
        ShopType videoType = shopService.getTypeByTypeId(typeId);
        model.addAttribute("title", shop.getTitle());
        model.addAttribute("type", videoType.getTypeName());
        model.addAttribute("cost", shop.getCost());
        model.addAttribute("time", shop.getDate());
        model.addAttribute("vv", shop.getVv());
        model.addAttribute("buy", shop.getBuy());
        model.addAttribute("pic", shop.getPictureUrl());
        model.addAttribute("isComment", shop.getIsComment());
        model.addAttribute("id", shop.getId());
        //Markdown转html
        model.addAttribute("content", MarkdownUtil.markdownToHtmlExtens(shop.getContent()));
        shopService.addShopVv(id);


        Map<String, List<ShopComment>> map = shopCommentService.findShopCommentsByShop(id);
        model.addAttribute("parents", map.get("parents"));
        model.addAttribute("sons", map.get("sons"));


        return "shopDetail";
    }
}
