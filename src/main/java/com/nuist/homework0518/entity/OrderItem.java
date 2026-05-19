package com.nuist.homework0518.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderItem {
    private int id;
    private int orderId;
    private int productId;
    private BigDecimal productPrice;
    private String productName;
    private int quantity;
    private BigDecimal subtotal;
}
