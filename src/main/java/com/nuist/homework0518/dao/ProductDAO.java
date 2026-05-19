package com.nuist.homework0518.dao;

import com.nuist.homework0518.entity.Product;

public interface ProductDAO {
    Product findByProductName(String name);
    boolean insertProduct(Product product);
}
