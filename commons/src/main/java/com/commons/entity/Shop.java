package com.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop implements Serializable {
    private Integer id;
    private String title;
    private String cost;
    private String content;
    private Date date;
    private Integer vv;
    private int typeId;
    private String pictureUrl;
    private Integer push;
    private Timestamp pushTime;
    private Integer isComment;
    private Integer state;
    private Integer buy;
}
