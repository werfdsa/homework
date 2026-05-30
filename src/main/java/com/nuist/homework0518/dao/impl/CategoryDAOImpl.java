package com.nuist.homework0518.dao.impl;

import com.nuist.homework0518.dao.CategoryDAO;
import com.nuist.homework0518.entity.Category;
import com.nuist.homework0518.entity.ProductCategory;
import com.nuist.homework0518.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public boolean insertCategory(ProductCategory category) {
        boolean result;
        String sql = "insert into categories(category_name) values(?)";
        try(Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, String.valueOf(category.getCategoryName()));
            if(ps.executeUpdate() == 1){
                result = true;
            }else {
                result = false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<ProductCategory> findAllCategories() {
        List<ProductCategory> list = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProductCategory pc = new ProductCategory();
                pc.setId(rs.getInt("category_id"));
                pc.setCategoryName(rs.getString("category_name"));
                list.add(pc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
