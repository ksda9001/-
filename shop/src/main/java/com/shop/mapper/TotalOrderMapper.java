package com.shop.mapper;

import com.commons.entity.TotalOrders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TotalOrderMapper {
    void addTotalOrders(TotalOrders totalOrders);

    void updateOrderStateToPaySuccess(String orderNo);
}
