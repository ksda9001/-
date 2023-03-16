package com.shop.service.impl;

import com.commons.entity.Orders;
import com.shop.mapper.OrderMapper;
import com.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public void createOrder(Orders order) {
       orderMapper.createOrder(order);
    }

    @Override
    public List<Orders> getOrderListByUserId(Integer ordersUser) {
        return orderMapper.getOrderListByUserId(ordersUser);
    }

    @Override
    public int cargoDel(String ordersNo) {
        return orderMapper.cargoDel(ordersNo);
    }

    @Override
    public int mutilDelete(List<String> noList) {
        return orderMapper.mutilDelete(noList);
    }

    @Override
    public Double getOrderPriceByNo(String x) {
        Integer ordersNo = Integer.parseInt(x);
        return orderMapper.getOrderPriceByNo(ordersNo);
    }

    @Override
    public Integer getUserIdByNo(String x) {
        return orderMapper.getUserIdByNo(x);
    }
}
