package com.shop.service.impl;

import com.commons.entity.ShopType;
import com.shop.mapper.ShopMapper;
import com.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
