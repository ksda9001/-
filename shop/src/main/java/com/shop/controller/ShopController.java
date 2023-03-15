package com.shop.controller;

import com.commons.entity.Shop;
import com.commons.entity.Shop;
import com.commons.entity.ShopType;
import com.commons.entity.Video;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class ShopController {
    @Autowired
    ShopService shopService;

    @PostMapping("/shopUpload")
    public String shopUpload(String title, String cost, String type, String content, String pictureUrl,
                             Integer isComment, Integer state, Model model) {
        Shop shop = new Shop();
        //标题
        shop.setTitle(title);
        //价格
        shop.setCost(cost);
        //分类
        Integer typeId = shopService.selectTypeIdByName(type);
        shop.setTypeId(typeId);
        //商品描述
        shop.setContent(content);
        //视频封面
        shop.setPictureUrl(pictureUrl);
        //留言
        shop.setIsComment(isComment);
        //状态
        shop.setState(state);
        //获取系统时钟
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        shop.setDate(timestamp);
        boolean result = shopService.addShop(shop);
        if (state == 1) {
            //返回处理消息
            if (result) {
                model.addAttribute("message", "发布成功！");
            } else {
                model.addAttribute("message", "发布失败！");
            }
        }
        if (state == 0) {
            //返回处理消息
            if (result) {
                model.addAttribute("message", "保存成功！");
            } else {
                model.addAttribute("message", "保存失败！");
            }
        }
        return "tip";
    }

    //获取最新视频
    @GetMapping("/getShopListByUser")
    public String getShopListByUser(@RequestParam(value = "pageNum", defaultValue = "1") Integer num, Model model) {
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("date desc");
        List<Shop> shopList = shopService.getShopList();
        PageInfo pageInfo = new PageInfo(shopList);
        List<ShopType> shopTypeList = shopService.getOneType();
        model.addAttribute("types", shopTypeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("type", 1);
        return "shopIndex";
    }

    //最热视频
    @GetMapping(value = "/getShopListByHeat")
    public String getShopListByHeat(@RequestParam(value = "pageNum", defaultValue = "1") Integer num, ModelMap model) {
        PageHelper.startPage(num, 5);
//        PageHelper.orderBy("date desc");
        List<Shop> shopList = shopService.getShopListByHeat();

        PageInfo pageInfo = new PageInfo(shopList);
        List<ShopType> shopTypeList = shopService.getOneType();
        model.addAttribute("types", shopTypeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("type", 2);
        return "shopIndex";
    }

    //推荐视频
    @GetMapping(value = "/getShopListBySystem")
    public String getShopListBySystem(@RequestParam(value = "pageNum", defaultValue = "1") Integer num, ModelMap model) {
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("pushTime desc");
        List<Shop> shopList = shopService.getShopListBySystem();

        PageInfo pageInfo = new PageInfo(shopList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("type", 3);
        List<ShopType> shopTypeList = shopService.getOneType();
        model.addAttribute("types", shopTypeList);
        return "shopIndex";
    }

    @GetMapping(value = "/getShopByName")
    public String getTopicByName(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 String title, ModelMap model) {
        PageHelper.startPage(pageNum, 5);
        PageHelper.orderBy("date desc");
        List<Shop> list = shopService.getShopByName(title);

        PageInfo pageInfo = new PageInfo(list);
        List<ShopType> shopTypeList = shopService.getOneType();
        model.addAttribute("types", shopTypeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("type", 0);
        model.addAttribute("title", title);
        return "shopIndex";
    }

    @GetMapping(value = "/getShopListByType")
    public String getShopListByType(String value, @RequestParam(value = "pageNum", defaultValue = "1") Integer num, ModelMap model) {
        Integer typeId = shopService.selectTypeIdByName(value);
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("date desc");
        List<Shop> shopList = shopService.getShopListByType(typeId);

        PageInfo pageInfo = new PageInfo(shopList);
        List<ShopType> shopTypeList = shopService.getOneType();
        model.addAttribute("types", shopTypeList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("type", 6);
        return "shopIndex";
    }


    @GetMapping(value = "/getShopByNameByAdmin")
    public String getShopByNameByAdmin(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       String title, ModelMap model) {
        PageHelper.startPage(pageNum, 5);
        PageHelper.orderBy("date desc");
        List<Shop> list = shopService.getShopByName(title);

        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo", pageInfo);
        return "shopControl/shopList";
    }

    @GetMapping(value = "/getShopList")
    public String getShopList(@RequestParam(value = "pageNum", defaultValue = "1") Integer num, ModelMap model) {
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("date desc");
        List<Shop> shopList = shopService.getShopList();

        PageInfo pageInfo = new PageInfo(shopList);
        model.addAttribute("pageInfo", pageInfo);
        return "shopControl/shopList";
    }

    @GetMapping(value = "/shopPushById")
    public String changeRoleById(Integer id, Model model) {
        Shop shop = shopService.getShopById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        if (shop.getPush() == 0) {
            shop.setPush(1);
            shop.setPushTime(timestamp);
        } else {
            shop.setPush(0);
            shop.setPushTime(null);
        }


        boolean result = shopService.addShopPushById(shop);
        if (result) {
            return "forward:/getShopList";
        }
        model.addAttribute("message", "推送失败！");
        return "tip";
    }

    @GetMapping(value = "/deleteVideoById")
    public String deleteVideoById(Integer id, Model model) {
        Shop shop = shopService.getShopById(id);
        if (shop != null) {
            int result = shopService.deleteShopById(id);
            if (result > 0) {
                return "forward:/getShopList";
            }
        }
        model.addAttribute("message", "删除失败");
        return "tip";
    }
}
