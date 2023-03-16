package com.shop.mapper;

import com.commons.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    void createOrder(Orders order);

    List<Orders> getOrderListByUserId(Integer ordersUser);

    int cargoDel(String ordersNo);

    int mutilDelete(List<String> noList);

    Double getOrderPriceByNo(Integer ordersNo);

    Integer getUserIdByNo(String x);

    void setFinish(String ordersNo);

    void delAllFinish();
}
