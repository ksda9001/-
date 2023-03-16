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
public class Orders implements Serializable {
    private Integer ordersId;
    private String ordersTitle;
    private Timestamp ordersTime;
    private Double ordersPrice;
    private Integer ordersUser;
    private String ordersState;
    private Integer ordersNum;
    private String ordersImg;
    private Integer ordersNo;
}
