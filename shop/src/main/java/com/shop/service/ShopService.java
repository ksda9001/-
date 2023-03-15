package com.shop.service;

import com.commons.entity.Blog;
import com.commons.entity.Shop;
import com.commons.entity.ShopType;
import com.commons.entity.VideoType;

import java.util.List;
import java.util.Map;

public interface ShopService {
    ShopType getTypeBySTypeName(String type);

    void addTypeByTypeName(ShopType shopType1);

    List<Shop> getShopByName(String title);

    Shop getShopById(Integer id);

    Integer selectTypeIdByName(String type);

    int addShop(Shop shop);

    int deleteShop(Integer id);

    int updateShop(Shop shop);

    Shop findShopById(Integer id);

    List<ShopType> getOneType();

    List<Shop> findShopByConditionVisible(Map<String, Object> map);
}
