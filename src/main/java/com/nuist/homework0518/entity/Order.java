package com.nuist.homework0518.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int orderId;
    private int userId;
    private int totalAmount;
    private OrderStatus orderStatus;
    private Date createTime;
}
