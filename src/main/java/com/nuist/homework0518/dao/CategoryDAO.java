package com.nuist.homework0518.dao;

import com.nuist.homework0518.entity.ProductCategory;

import java.util.List;

public interface CategoryDAO {
    boolean insertCategory(ProductCategory category);
    List<ProductCategory> findAllCategories();
}
