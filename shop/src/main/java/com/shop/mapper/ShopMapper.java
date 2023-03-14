package com.shop.mapper;

import com.commons.entity.ShopType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper {
    ShopType getTypeByTypeName(String typeName);

    void addTypeByTypeName(ShopType typeName);
}
