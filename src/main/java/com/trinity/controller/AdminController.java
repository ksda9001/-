package com.trinity.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trinity.entity.ResponseData;
import com.trinity.entity.Users;
import com.trinity.service.AdminService;
import com.trinity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;

    //shiro权限校验
    //@RequiresRoles(value = {"admin"})
    @RequestMapping("/adminList")
    public String adminList(Model model,
                            @RequestParam(value = "keywords", defaultValue = "") String keywords,
                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        //进行分页，提供关键字查询
        //pageSize为分页的行数
        Map<String, Object> search = new HashMap<>();
        search.put("keywords", keywords);
        PageHelper.startPage(pageNum, pageSize);
        List<Users> list = adminService.selectNSearch(search);
        //使用PageHelper包装数据
        PageInfo<Users> pageInfo = new PageInfo<>(list);
        model.addAttribute("result", new ResponseData<>(200, "ok", pageInfo));
        model.addAttribute("keywords", keywords);
        return "/admin/adminList";
    }

    @PutMapping("/editStatus/{userStatus}")
    @ResponseBody
    public ResponseData<String> editStatus(@PathVariable String userStatus, @RequestParam String userID) {
        //Some Magic.
        String[] IDS = userID.split(",");
        List<String> IDList = Arrays.asList(IDS);
        int test = adminService.editStatus(IDList, userStatus);
        if (test > 0) {
            return new ResponseData<>(200, "状态修改成功！", null);
        } else {
            return new ResponseData<>(500, "状态修改失败！", null);
        }
    }

    @PostMapping("/adminAdd")
    public void register(Model model, Users user, String userPassword2) {
        //此处的用户名校验主要用于向前端提供错误信息
        //TODO i18n
        if (userService.selectUserByUsername(user.getUserName()) != null) {
            model.addAttribute("msg", "用户名已存在");
        }
        //后端密码校验
        String password = user.getPassword();
        if (userPassword2 != null && password != null) {
            if (!userPassword2.equals(password)) {
                model.addAttribute("msg", "添加失败");
            }
        }
        String sw = "adminAdd";
        Boolean register = userService.register(user, sw);
        if (register) {
            model.addAttribute("msg", "添加成功");
        } else {
            model.addAttribute("msg", "添加失败");
        }
    }
}
