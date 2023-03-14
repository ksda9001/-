package com.shop.controller;

import com.commons.entity.Users;
import com.shop.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    //注册
    @PostMapping("/register")
    public String register(Model model, Users user, String userPassword2) {
        //此处的用户名校验主要用于向前端提供错误信息
        //TODO i18n
        if (userService.selectUserByUsername(user.getUserName()) != null) {
            model.addAttribute("loginmsg", "用户名已注册");
        }
        //后端密码校验
        String password = user.getPassword();
        if (userPassword2 != null && password != null) {
            if (!userPassword2.equals(password)) {
                model.addAttribute("registermsg", "注册失败");
                return "control/register";
            }
        }
        String sw = "register";
        Boolean register = userService.register(user,sw);
        if (register) {
            model.addAttribute("loginmsg", "注册成功");
            return "control/login";
        } else {
            model.addAttribute("registermsg", "注册失败");
            return "control/register";
        }
    }

    @PostMapping("/login")
    public String login(Model model, Users user, HttpServletRequest httpServletRequest) {
        String username = user.getUserName();
        String password = user.getPassword();
        Boolean login = userService.login(username, password);
        //如果密码正确则通过shiro认证
        if (login) {
            Subject subject = SecurityUtils.getSubject();
            //进行Shiro认证
            subject.login(new UsernamePasswordToken(username, password));
            Users userEntity = userService.selectUserByUsername(username);
            //置空防止前台捕获？
            userEntity.setPassword(null);
            user.setSalt(null);
            httpServletRequest.getSession().setAttribute("user", userEntity);
            if (userEntity.getRole().equals("user")) {
                return "control/index";
            }
            return "adminIndex";
        }
        model.addAttribute("loginmsg", "用户名或密码错误");
        return "control/login";
    }
}
