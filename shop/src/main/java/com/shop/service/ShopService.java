package com.shop.service;

import com.commons.entity.Shop;
import com.commons.entity.ShopType;

import java.util.List;

public interface ShopService {

    Integer selectTypeIdByName(String type);

    boolean addShop(Shop shop);

    List<Shop> getShopListByHeat();

    List<Shop> getShopList();

    List<ShopType> getOneType();

    List<Shop> getShopListBySystem();

    List<Shop> getShopByName(String title);

    List<Shop> getShopListByType(Integer typeId);

    Shop getShopById(Integer id);

    boolean addShopPushById(Shop shop);

    ShopType getTypeByTypeName(String type);

    void addTypeByTypeName(ShopType shopType1);
}
