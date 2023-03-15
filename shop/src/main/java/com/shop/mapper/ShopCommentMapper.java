package com.shop.mapper;

import com.commons.entity.ShopComment;
import com.commons.entity.VideoComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShopCommentMapper {
    int insertShopComment(ShopComment shopComment);

    int deleteShopCommentById(Integer id);

    int updateShopComment(ShopComment shopComment);

    List<ShopComment> findShopCommentByPage(Map<String, Object> map);

    ShopComment findShopCommentById(Integer id);

    List<ShopComment> findPShopCommentByShopId(Integer id);

    List<ShopComment> findSShopCommentByShopId(Integer id);
}
