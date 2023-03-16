package com.shop.service.impl;

import com.commons.entity.TotalOrders;
import com.commons.entity.Users;
import com.shop.mapper.CargoControlMapper;
import com.shop.mapper.TotalOrderMapper;
import com.shop.service.TotalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TotalOrderServiceImpl implements TotalOrderService {
    @Autowired
    TotalOrderMapper totalOrderMapper;
    @Autowired
    CargoControlMapper cargoControlMapper;

    @Override
    public void addTotalOrders(TotalOrders totalOrders) {
        totalOrderMapper.addTotalOrders(totalOrders);
    }

    @Override
    public void updateOrderStateToPaySuccess(String orderNo) {
        totalOrderMapper.updateOrderStateToPaySuccess(orderNo);
    }

    @Override
    public List<TotalOrders> selectNSearch(Map<String, Object> search) {
        return cargoControlMapper.selectNSearch(search);
    }

    @Override
    public int mutilcargoControlFin(List<String> noList) {
        return cargoControlMapper.mutilcargoControlFin(noList);
    }

    @Override
    public int cargoControlDel(String id) {
        return cargoControlMapper.cargoControlDel(id);
    }

    @Override
    public int cargoControlFin(String id) {
        return cargoControlMapper.cargoControlFin(id);
    }

    @Override
    public int mutilcargoControlDel(List<String> noList) {
        return cargoControlMapper.mutilcargoControlDel(noList);
    }
}
