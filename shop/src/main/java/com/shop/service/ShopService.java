package com.shop.service;

import com.commons.entity.ShopType;

public interface ShopService {
    ShopType getTypeBySTypeName(String type);

    void addTypeByTypeName(ShopType shopType1);
}
