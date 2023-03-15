package com.shop.service.impl;

import com.commons.entity.ShopComment;
import com.commons.entity.VideoComment;
import com.shop.mapper.ShopCommentMapper;
import com.shop.service.ShopCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopCommentServiceImpl implements ShopCommentService {
    @Autowired
    ShopCommentMapper shopCommentMapper;

    @Override
    public int insertShopComment(ShopComment shopComment) {
        shopComment.setTime(new Date());
        return shopCommentMapper.insertShopComment(shopComment);
    }

    @Override
    public int deleteShopCommentById(Integer id) {
        return shopCommentMapper.deleteShopCommentById(id);
    }

    @Override
    public int updateShopComment(ShopComment shopComment) {
        return shopCommentMapper.updateShopComment(shopComment);
    }

    @Override
    public List<ShopComment> findShopCommentByPage(Map<String, Object> map) {
        return shopCommentMapper.findShopCommentByPage(map);
    }

    @Override
    public ShopComment findShopCommentById(Integer id) {
        return shopCommentMapper.findShopCommentById(id);
    }

    @Override
    public Map<String, List<ShopComment>> findShopCommentsByShop(Integer id) {
        Map<String, List<ShopComment>> map = new HashMap<>(2);
        map.put("parents", shopCommentMapper.findPShopCommentByShopId(id));
        map.put("sons", shopCommentMapper.findSShopCommentByShopId(id));
        return map;
    }
}
