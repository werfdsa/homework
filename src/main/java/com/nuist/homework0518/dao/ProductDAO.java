package com.nuist.homework0518.dao;

import com.nuist.homework0518.entity.Product;

import java.util.List;

public interface ProductDAO {
    Product findByProductName(String name);
    boolean insertProduct(Product product);
    List<Product> findAll();
    Product findById(int id);
    boolean updateStock(int productId, int quantity);
    List<Product> findByCategoryId(int categoryId);
    boolean deleteProduct(int id);
}
