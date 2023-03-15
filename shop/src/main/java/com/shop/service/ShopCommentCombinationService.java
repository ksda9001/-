package com.shop.service;

import com.commons.entity.ShopCommentCombination;

import java.util.List;
import java.util.Map;

public interface ShopCommentCombinationService {
    List<ShopCommentCombination> findShopCommentByPage(Map<String, Object> map);

    ShopCommentCombination findShopCommentById(Integer id);
}
