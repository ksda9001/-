package com.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type implements Serializable {

    /**
     * ID编号
     */
    private Integer id;

    /**
     * 专栏名称
     */
    private String name;

    /**
     * 专栏简介
     */
    private String introduction;

    /**
     * 博客数量
     */
    private Integer number = null;

    /**
     * 更新时间
     */
    private Date date;

}