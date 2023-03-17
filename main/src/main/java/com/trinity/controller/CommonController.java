package com.trinity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Get请求的传统类型映射
@Controller
public class CommonController {
    @GetMapping("/pictureUpload")
    public String videoUpload() {
        return "pictureUpload/pictureUpload";
    }
    @GetMapping("/connectAdmin")
    public String connectAdmin() {
        return "main/connectAdmin";
    }
    @GetMapping("/connectUser")
    public String connectUser() {
        return "main/connectUser";
    }
}
