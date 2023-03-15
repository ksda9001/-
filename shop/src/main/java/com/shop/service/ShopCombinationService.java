package com.shop.service;

import java.util.List;
import java.util.Map;

public interface ShopCombinationService {

    List<Shop> findBlogCombinationByCondition(Map<String, Object> map);
}
