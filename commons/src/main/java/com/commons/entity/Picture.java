package com.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Picture implements Serializable {
    private Integer id;
    private String size;
    private String path;
    private String title;
    private Timestamp uploadTime;
}
