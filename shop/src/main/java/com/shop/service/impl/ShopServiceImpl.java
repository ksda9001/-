package com.shop.service.impl;

import com.commons.entity.Shop;
import com.commons.entity.ShopType;
import com.shop.mapper.ShopMapper;
import com.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopMapper shopMapper;


    @Override
    public Integer selectTypeIdByName(String type) {
        return shopMapper.selectTypeIdByName(type);
    }

    @Override
    public boolean addShop(Shop shop) {
        return shopMapper.addShop(shop);
    }

    @Override
    public List<Shop> getShopListByHeat() {
        return shopMapper.getShopListByHeat();
    }

    @Override
    public List<Shop> getShopList() {
        return shopMapper.getShopList();
    }

    @Override
    public List<ShopType> getOneType() {
        return shopMapper.getOneType();
    }

    @Override
    public List<Shop> getShopListBySystem() {
        return shopMapper.getShopListBySystem();
    }

    @Override
    public List<Shop> getShopByName(String title) {
        return shopMapper.getShopByName(title);
    }

    @Override
    public List<Shop> getShopListByType(Integer typeId) {
        return shopMapper.getShopListByType(typeId);
    }

    @Override
    public Shop getShopById(Integer id) {
        return shopMapper.getShopById(id);
    }

    @Override
    public boolean addShopPushById(Shop shop) {
        return shopMapper.addShopPushById(shop);
    }

    @Override
    public ShopType getTypeByTypeName(String type) {
        return shopMapper.getTypeByTypeName(type);
    }

    @Override
    public void addTypeByTypeName(ShopType shopType1) {
        shopMapper.addTypeByTypeName(shopType1);
    }
}
