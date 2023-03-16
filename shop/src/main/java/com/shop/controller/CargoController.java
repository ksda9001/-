package com.shop.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.commons.entity.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.service.OrderService;
import com.shop.service.ShopService;
import com.shop.service.TotalOrderService;
import com.shop.util.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.*;

@Controller
public class CargoController {
    @Autowired
    ShopService shopService;
    @Autowired
    OrderService orderService;
    @Autowired
    TotalOrderService totalOrderService;

    @GetMapping("/createOrder/{id}/{buynum}")
    public String createOrder(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "0") Integer pageSize,
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
    @DeleteMapping("/cargoDel/{id}")
    @ResponseBody
    public ResponseData<String> cargoDel(@PathVariable String id) {
        int test = orderService.cargoDel(id);
        if (test > 0) {
            return new ResponseData<>(200, "删除成功！", null);
        } else {
            return new ResponseData<>(500, "删除失败！", null);
        }
    }

    //批量删除
    @DeleteMapping("/cargoMutilDel")
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

    @ResponseBody
    @RequestMapping(value ="/mutilBuy" , produces = {"text/html; charset=utf-8"})
    public String mutilBuy(@RequestParam String ordersNo, HttpSession session) throws AlipayApiException{
        //Some Magic.
        String[] NOS = ordersNo.split(",");
        List<String> noList = Arrays.asList(NOS);
        Double priceTotal = 0.0;
        Integer userId = 0;
        for (String number:noList){
            Double price = orderService.getOrderPriceByNo(number);
            userId = orderService.getUserIdByNo(number);
            priceTotal += price;
        }
        Users user = (Users) session.getAttribute("user");
        TotalOrders totalOrders = new TotalOrders();
        totalOrders.setOrderNo(UUID.randomUUID().toString().replace("-" , ""));
        totalOrders.setPrice(priceTotal);
        totalOrders.setUserId(userId);
        totalOrders.setState("待付款");
        totalOrders.setAddress(user.getUserAddress());
        totalOrderService.addTotalOrders(totalOrders);
        // 创建支付工具，并提交支付请求
        //获得初始化的AlipayClient
        AlipayClient alipayClient =
                new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ totalOrders.getOrderNo() +"\","
                + "\"total_amount\":\""+ priceTotal +"\","
                + "\"subject\":\"商品付款\","
                + "\"body\":\"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        return result;
    }

    @RequestMapping("/alipayReturn")
    public String alipayReturn(HttpServletRequest request , Map<String,Object> map) throws UnsupportedEncodingException, AlipayApiException {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        // 支付结果返回后的业务流程
        if(signVerified) {
            //修改订单状态，并且将页面转发至支付成功提醒页面
            //获取支付成功后的返回参数中的订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            totalOrderService.updateOrderStateToPaySuccess(out_trade_no);
            map.put("trade_no",trade_no);
            map.put("total_amount",total_amount);
            return "message";
        }else {
            // 将页面转发至支付失败页面，让用户通过其他方式支付
            return "payFail";
        }
    }

    @GetMapping("/checkCargo")
    public String checkCargo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "0") Integer pageSize,
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
