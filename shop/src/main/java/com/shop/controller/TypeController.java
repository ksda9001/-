package com.shop.controller;

import com.commons.entity.ShopType;
import com.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TypeController {
    @Autowired
    ShopService shopService;

    @PostMapping(value = "/shopAddType")
    public String videoAddType(String type, ModelMap model) {
        if (type != null) {
            ShopType shopType = shopService.getTypeByTypeName(type);
            if (shopType == null) {
                ShopType shopType1 = new ShopType();
                shopType1.setTypeName(type);
                shopService.addTypeByTypeName(shopType1);
                model.addAttribute("message", "添加成功！");
                return "tip";
            } else {
                model.addAttribute("error", "已有类型！");
                return "shopControl/shopTypeAdd";

            }
        } else {
            model.addAttribute("error", "请输入类型！");
            return "shopControl/shopTypeAdd";
        }

    }
}
