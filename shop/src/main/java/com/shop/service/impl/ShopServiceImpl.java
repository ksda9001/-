package com.shop.service.impl;

import com.commons.entity.*;
import com.shop.mapper.ShopMapper;
import com.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopMapper shopMapper;

    @Override
    public ShopType getTypeBySTypeName(String type) {
        return shopMapper.getTypeByTypeName(type);
    }

    @Override
    public void addTypeByTypeName(ShopType shopType1) {
        shopMapper.addTypeByTypeName(shopType1);
    }

    @Override
    public List<Shop> getShopByName(String title) {
        return shopMapper.getShopByName(title);
    }

    @Override
    public Shop getShopById(Integer id) {
        return shopMapper.getShopById(id);
    }

    @Override
    public Integer selectTypeIdByName(String type) {
        return shopMapper.selectTypeIdByName(type);
    }

    @Override
    @Transactional
    public int addShop(Shop shop) {
        shop.setDate(new Date());
        return shopMapper.addShop(shop);
    }

    @Transactional
    @Override
    public int deleteShop(Integer id) {
        return shopMapper.deleteShopById(id);
    }

    @Override
    public int updateShop(Shop shop) {
        shop.setViews(null);
        return shopMapper.updateShop(shop);
    }

    @Override
    public Shop findShopById(Integer id) {
        return shopMapper.getShopById(id);
    }

    @Override
    public List<ShopType> getOneType() {
        return shopMapper.getOneType();
    }

    @Override
    public List<Shop> findShopByConditionVisible(Map<String, Object> map) {
        map.put("state", 1);
        return shopMapper.findShopByCondition(map);
    }
}
