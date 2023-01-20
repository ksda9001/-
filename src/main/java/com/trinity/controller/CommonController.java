package com.trinity.controller;

import com.trinity.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//Get请求的传统类型映射
//TODO 待整合
@Controller
public class CommonController {
    @Autowired
    UserService userService;

    @GetMapping({"/", "/login"})
    public String login() {
        return "login";
    }

    @GetMapping("/invalid")
    public String invalid() {
        return "invalid";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    //系统登出
    @RequestMapping("/logout")
    public String logout() {
        //调用Shiro清除token
        SecurityUtils.getSubject().logout();
        return "login";
    }

    @GetMapping("/updatePassword")
    public String updatePassword() {
        return "updatePassword";
    }

    @GetMapping("/updatePhone")
    public String updatePhone() {
        return "updatePhone";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/queryuser")
    public String queryuser() {
        return "queryuserPage";
    }

    @GetMapping("/updateuser")
    public String updateuser() {
        return "updateuserPage";
    }

    @GetMapping("/revoke")
    public String revoke() {
        return "revokePage";
    }

    @GetMapping("/addUser")
    public String addUser() {
        return "addUserPage";
    }

    @GetMapping("/adminAdd")
    public String adminAdd() {
        return "/admin/adminAdd";
    }

    @GetMapping("/deleteUser")
    public String deleteUser() {
        return "deleteUserPage";
    }
}
