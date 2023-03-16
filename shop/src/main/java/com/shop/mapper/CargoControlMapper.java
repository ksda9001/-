package com.shop.mapper;

import com.commons.entity.TotalOrders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CargoControlMapper {
    List<TotalOrders> selectNSearch(Map<String, Object> search);

    int mutilcargoControlFin(List<String> noList);

    int cargoControlDel(String id);

    int cargoControlFin(String id);

    int mutilcargoControlDel(List<String> noList);
}
