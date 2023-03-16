package com.shop.service.impl;

import com.commons.entity.TotalOrders;
import com.shop.mapper.TotalOrderMapper;
import com.shop.service.TotalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotalOrderServiceImpl implements TotalOrderService {
    @Autowired
    TotalOrderMapper totalOrderMapper;
    @Override
    public void addTotalOrders(TotalOrders totalOrders) {
        totalOrderMapper.addTotalOrders(totalOrders);
    }

    @Override
    public void updateOrderStateToPaySuccess(String orderNo) {
        totalOrderMapper.updateOrderStateToPaySuccess(orderNo);
    }
}
