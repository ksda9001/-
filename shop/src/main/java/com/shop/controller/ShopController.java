package com.shop.controller;

import com.commons.entity.Shop;
import com.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShopController {
    @Autowired
    ShopService shopService;

    @PostMapping(value = "/shop")
    public ModelAndView insertBlog(Shop shop, String type) {
        ModelAndView mv = new ModelAndView();
        //配合查找
        Integer typeId = shopService.selectTypeIdByName(type);
        shop.setTypeId(typeId);
        mv.setViewName("tip");
        if (shopService.addShop(shop) == 1) {
            mv.addObject("message", "保存成功!");
        } else {
            mv.addObject("message", "保存失败!");
        }
        return mv;
    }

    @DeleteMapping(value = "/blog")
    public ModelAndView deleteBlog(@RequestParam Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tip");
        if (shopService.deleteShop(id) == 1) {
            mv.addObject("message", "删除成功!");
        } else {
            mv.addObject("message", "删除失败!");
        }
        return mv;
    }

    @PutMapping(value = "/blog")
    public ModelAndView update(Shop shop, String type) {
        Integer typeId = shopService.selectTypeIdByName(type);
        shop.setTypeId(typeId);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tip");
        if (shopService.updateShop(shop) == 1) {
            mv.addObject("message", "更新成功!");
        } else {
            mv.addObject("message", "更新失败!");
        }
        return mv;
    }
}
