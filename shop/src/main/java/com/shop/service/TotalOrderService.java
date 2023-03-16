package com.shop.service;

import com.commons.entity.TotalOrders;

public interface TotalOrderService {

    void addTotalOrders(TotalOrders totalOrders);

    void updateOrderStateToPaySuccess(String orderNo);
}
