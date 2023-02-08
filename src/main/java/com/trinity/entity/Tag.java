package com.trinity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag implements Serializable {

    /**
     * 标签id编号
     */
    private Integer id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 博客数量
     */
    private Integer number = 0;

    /**
     * 更新时间
     */
    private Date date;

}