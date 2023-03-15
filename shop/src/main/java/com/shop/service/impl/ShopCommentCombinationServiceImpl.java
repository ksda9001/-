package com.shop.service.impl;

import com.commons.entity.ShopComment;
import com.commons.entity.ShopCommentCombination;
import com.commons.entity.VideoComment;
import com.commons.entity.VideoCommentCombination;
import com.shop.service.ShopCommentCombinationService;
import com.shop.service.ShopCommentService;
import com.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ShopCommentCombinationServiceImpl implements ShopCommentCombinationService {
    @Autowired
    ShopService shopService;
    @Autowired
    ShopCommentService shopCommentService;

    @Override
    public List<ShopCommentCombination> findShopCommentByPage(Map<String, Object> map) {
        Object title = map.get("shop");
        if (title != null && title != "") {
            map.replace("shop", shopService.getShopByName(String.valueOf(title)));
        }
        List<ShopComment> shopComments = shopCommentService.findShopCommentByPage(map);
        List<ShopCommentCombination> shopCommentCombinations = new ArrayList<>();
        for (ShopComment shopComment : shopComments) {
            ShopCommentCombination shopCommentCombination = new ShopCommentCombination(shopComment, shopService.getShopById(shopComment.getShop()));
            shopCommentCombinations.add(shopCommentCombination);
        }
        return shopCommentCombinations;
    }

    @Override
    public ShopCommentCombination findShopCommentById(Integer id) {
        ShopComment shopComment = shopCommentService.findShopCommentById(id);
        ShopCommentCombination shopCommentCombination = new ShopCommentCombination(shopComment, shopService.getShopById(shopComment.getShop()));

        return shopCommentCombination;
    }
}
