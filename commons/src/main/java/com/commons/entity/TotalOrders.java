package com.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalOrders implements Serializable {
    Integer id;
    Double price;
    String orderNo;
    Integer userId;
    String state;
    String address;
}
