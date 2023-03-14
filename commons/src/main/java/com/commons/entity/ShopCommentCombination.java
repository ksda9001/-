package com.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopCommentCombination implements Serializable {
    /**
     * ID编号
     */
    private Integer id;

    /**
     * 发表评论的用户名称
     */
    private String name;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 视频ID名称
     */
    private String shop;

    /**
     * 发布时间
     */
    private Date time;

    /**
     * 是否是管理员
     */
    private Integer isAdmin;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 父评论的id
     */
    private Integer parent;

    public ShopCommentCombination(ShopComment shopComment, Shop shop) {
        this.id = shopComment.getId();
        this.name = shopComment.getName();
        this.email = shopComment.getEmail();
        this.shop = shop.getTitle();
        this.time = shopComment.getTime();
        this.isAdmin = shopComment.getIsAdmin();
        this.content = shopComment.getContent();
        this.parent = shopComment.getParent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopCommentCombination that = (ShopCommentCombination) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(shop, that.shop) &&
                Objects.equals(time, that.time) &&
                Objects.equals(isAdmin, that.isAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, shop, time, isAdmin, content, parent);
    }
}
