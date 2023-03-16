package com.shop.controller;

import com.commons.entity.Orders;
import com.commons.entity.ResponseData;
import com.commons.entity.Shop;
import com.commons.entity.Users;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.service.OrderService;
import com.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class CargoController {
    @Autowired
    ShopService shopService;
    @Autowired
    OrderService orderService;

    @GetMapping("/createOrder/{id}/{buynum}")
    public String createOrder(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                              @PathVariable Integer id,
                              @PathVariable Integer buynum,
                              HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        Shop shop = shopService.getShopById(id);
        Orders orders = new Orders();
        Integer ordersUser = user.getUserID();
        orders.setOrdersId(id);
        //设置订单名称
        orders.setOrdersTitle(shop.getTitle());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        orders.setOrdersTime(timestamp);
        //设置总价
        orders.setOrdersPrice(buynum * Double.parseDouble(shop.getCost()));
        orders.setOrdersUser(ordersUser);
        orders.setOrdersState("待支付");
        orders.setOrdersNum(buynum);
        orders.setOrdersImg(shop.getPictureUrl());


        orderService.createOrder(orders);

        PageHelper.startPage(pageNum, pageSize);
        //获取用户id下所有订单
        List<Orders> orderList = orderService.getOrderListByUserId(ordersUser);
        //使用PageHelper包装数据
        PageInfo<Orders> pageInfo = new PageInfo<>(orderList);
        model.addAttribute("result", new ResponseData<>(200, "ok", pageInfo));
        return "/cargo";
    }

    //单条删除
    @DeleteMapping("/cargoDel/{ordersNo}")
    @ResponseBody
    public ResponseData<String> cargoDel(@PathVariable String ordersNo) {
        int test = orderService.cargoDel(ordersNo);
        if (test > 0) {
            return new ResponseData<>(200, "删除成功！", null);
        } else {
            return new ResponseData<>(500, "删除失败！", null);
        }
    }

    //批量删除
    @DeleteMapping("/mutilDel")
    @ResponseBody
    public ResponseData<String> mutilDel(@RequestParam String ordersNo) {
        //Some Magic.
        String[] NOS = ordersNo.split(",");
        List<String> noList = Arrays.asList(NOS);
        int test = orderService.mutilDelete(noList);
        if (test > 0) {
            return new ResponseData<>(200, "删除成功！", null);
        } else {
            return new ResponseData<>(500, "删除失败！", null);
        }
    }

    //批量删除
    @PutMapping("/mutilBuy")
    @ResponseBody
    public ResponseData<String> mutilBuy(@RequestParam String ordersNo) {
        //Some Magic.
        String[] NOS = ordersNo.split(",");
        List<String> noList = Arrays.asList(NOS);
        int test = orderService.mutilDelete(noList);
        if (test > 0) {
            return new ResponseData<>(200, "删除成功！", null);
        } else {
            return new ResponseData<>(500, "删除失败！", null);
        }
    }

    @GetMapping("/checkCargo")
    public String checkCargo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                             HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        Integer ordersUser = user.getUserID();
        PageHelper.startPage(pageNum, pageSize);
        //获取用户id下所有订单
        List<Orders> orderList = orderService.getOrderListByUserId(ordersUser);
        //使用PageHelper包装数据
        PageInfo<Orders> pageInfo = new PageInfo<>(orderList);
        model.addAttribute("result", new ResponseData<>(200, "ok", pageInfo));
        return "/cargo";
    }
}
