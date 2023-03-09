package com.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video implements Serializable {
    private Integer id;
    private String type;
    private String size;
    private String path;
    private String title;
    private Timestamp uploadTime;
    private Integer vv;
    private Integer download;
    private String author;
    private Integer tc;
    private Integer typeId;
    private Integer push;
    private Timestamp pushTime;
    private List<VideoClick> videoClick;
    private String pictureUrl;
    private String comments;
    private Integer isReprint;
    private Integer isComment;
    private Integer property;
}
