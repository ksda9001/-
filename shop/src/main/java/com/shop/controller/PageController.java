package com.shop.controller;

import com.commons.entity.ShopType;
import com.shop.service.ShopCombinationService;
import com.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {
    @Autowired
    ShopService shopService;

    @GetMapping("/shopControl/shop-input")
    public ModelAndView blog_input(Model model) {
        ModelAndView mv = new ModelAndView();
        List<ShopType> shopTypeList = shopService.getOneType();
        model.addAttribute("types", shopTypeList);
        mv.addObject("method", "post");
        mv.setViewName("shopControl/shop-input");
        return getModelAndView(mv);
    }

    @GetMapping("/shopControl/shop-input/{shopId}")
    public ModelAndView shop_input_update(@PathVariable Integer shopId, Model model) {
        ModelAndView mv = new ModelAndView();
        List<ShopType> shopTypeList = shopService.getOneType();
        model.addAttribute("types", shopTypeList);
        mv.addObject("shop", shopService.findShopById(shopId));
        mv.addObject("method", "put");
        mv.setViewName("shopControl/shop-input");
        return getModelAndView(mv);
    }

    @GetMapping("/admin/blogs")
    public ModelAndView blogs(Model model) {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> map = new HashMap<>();
        map.put("begin", 0);
        map.put("size", 6);
        mv.setViewName("shopControl/shops");
        List<ShopType> shopTypeList = shopService.getOneType();
        model.addAttribute("types", shopTypeList);
        mv.addObject("shop", shopCombinationService.findBlogCombinationByCondition(map));
        return getModelAndView(mv);
    }

    private ModelAndView getModelAndView(ModelAndView mv) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("begin", 0);
        map.put("size", 3);
        map.put("isRecommend", 1);
        mv.addObject("newShop", shopService.findShopByConditionVisible(map));
        return mv;
    }
}
