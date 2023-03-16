package com.shop.controller;

import com.commons.entity.ResponseData;
import com.commons.entity.TotalOrders;
import com.commons.entity.Users;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.service.TotalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CargoControlController {
    @Autowired
    TotalOrderService totalOrderService;
    @RequestMapping("/cargoControl")
    public String adminList(Model model,
                            @RequestParam(value = "keywords", defaultValue = "") String keywords,
                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        //进行分页，提供关键字查询
        //pageSize为分页的行数
        Map<String, Object> search = new HashMap<>();
        search.put("keywords", keywords);
        PageHelper.startPage(pageNum, pageSize);
        List<TotalOrders> list = totalOrderService.selectNSearch(search);
        //使用PageHelper包装数据
        PageInfo<TotalOrders> pageInfo = new PageInfo<>(list);
        model.addAttribute("result", new ResponseData<>(200, "ok", pageInfo));
        model.addAttribute("keywords", keywords);
        return "/shopControl/cargoControl";
    }

    //单条删除
    @DeleteMapping("/cargoControlDel/{id}")
    @ResponseBody
    public ResponseData<String> cargoControlDel(@PathVariable String id) {
        int test = totalOrderService.cargoControlDel(id);
        if (test > 0) {
            return new ResponseData<>(200, "删除成功！", null);
        } else {
            return new ResponseData<>(500, "删除失败！", null);
        }
    }

    //单条完成
    @PutMapping("/cargoControlFin/{id}")
    @ResponseBody
    public ResponseData<String> cargoControlFin(@PathVariable String id) {
        int test = totalOrderService.cargoControlFin(id);
        if (test > 0) {
            return new ResponseData<>(200, "操作成功！", null);
        } else {
            return new ResponseData<>(500, "操作失败！", null);
        }
    }

    //批量删除
    @DeleteMapping("/mutilcargoControlDel")
    @ResponseBody
    public ResponseData<String> mutilcargoControlDel(@RequestParam String id) {
        //Some Magic.
        String[] NOS = id.split(",");
        List<String> noList = Arrays.asList(NOS);
        int test = totalOrderService.mutilcargoControlDel(noList);
        if (test > 0) {
            return new ResponseData<>(200, "删除成功！", null);
        } else {
            return new ResponseData<>(500, "删除失败！", null);
        }
    }

    //批量完成
    @PutMapping("/mutilcargoControlFin")
    @ResponseBody
    public ResponseData<String> mutilcargoControlFin(@RequestParam String id) {
        //Some Magic.
        String[] NOS = id.split(",");
        List<String> noList = Arrays.asList(NOS);
        int test = totalOrderService.mutilcargoControlFin(noList);
        if (test > 0) {
            return new ResponseData<>(200, "操作成功！", null);
        } else {
            return new ResponseData<>(500, "操作失败！", null);
        }
    }
}
