package com.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoClick implements Serializable {
    private Integer id;
    private String username;
    private Integer videoId;
    private Integer num;
    private Timestamp updateTime;
}
