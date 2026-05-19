package com.nuist.homework0518.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private int id;
    private int categoryId;
    private String productName;
    private BigDecimal price;
    private int stock;
    private boolean status;


    public boolean getStatus(){
        return status;
    }
}
