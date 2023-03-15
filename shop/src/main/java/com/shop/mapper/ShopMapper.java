package com.shop.mapper;

import com.commons.entity.Shop;
import com.commons.entity.ShopType;
import com.commons.entity.VideoType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShopMapper {
    ShopType getTypeByTypeName(String typeName);

    void addTypeByTypeName(ShopType typeName);

    List<Shop> getShopByName(String title);

    Shop getShopById(Integer id);

    Integer selectTypeIdByName(String type);

    int addShop(Shop shop);

    int deleteShopById(Integer id);

    int updateShop(Shop shop);

    List<ShopType> getOneType();

    List<Shop> findShopByCondition(Map<String, Object> map);
}
