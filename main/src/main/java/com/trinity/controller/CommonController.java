package com.trinity.controller;

import com.commons.entity.Users;
import com.trinity.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//Get请求的传统类型映射
//TODO 待整合
@Controller
public class CommonController {
    @Autowired
    UserService userService;

    @GetMapping({"/login"})
    public String login() {
        return "control/login";
    }

//    @RequestMapping({"/","/index"})
//    public String index() {
//        return "index";
//    }

    @GetMapping("/register")
    public String register() {
        return "control/register";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "control/welcome";
    }

    //系统登出
    @RequestMapping("/logout")
    public String logout() {
        //调用Shiro清除token
        SecurityUtils.getSubject().logout();
        return "control/login";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/editUser")
    public String editUser(Model model, String userID) {
        Users user = userService.selectUserByID(userID);
        model.addAttribute("user", user);
        return "admin/adminEdit";
    }

    @GetMapping("/pictureUpload")
    public String videoUpload() {
        return "pictureUpload/pictureUpload";
    }
}
