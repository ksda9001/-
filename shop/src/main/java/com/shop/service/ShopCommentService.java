package com.shop.service;

import com.commons.entity.ShopComment;

import java.util.List;
import java.util.Map;

public interface ShopCommentService {
    /**
     * 插入一条评论
     *
     * @return
     */
    int insertShopComment(ShopComment shopComment);

    /**
     * 根据主键删除一条评论
     *
     * @param id
     * @return
     */
    int deleteShopCommentById(Integer id);

    /**
     * 更新一条评论
     *
     * @return
     */
    int updateShopComment(ShopComment shopComment);

    /**
     * 分页查找
     *
     * @param map
     * @return
     */
    List<ShopComment> findShopCommentByPage(Map<String, Object> map);

    /**
     * 根据主键id查询评论
     *
     * @param id
     * @return
     */
    ShopComment findShopCommentById(Integer id);

    /**
     * 根据视频id查询相对应的评论，并将其变成一定格式返回
     *
     * @param id
     * @return
     */
    Map<String, List<ShopComment>> findShopCommentsByShop(Integer id);
}
