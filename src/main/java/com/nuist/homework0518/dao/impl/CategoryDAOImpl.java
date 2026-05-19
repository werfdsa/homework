package com.nuist.homework0518.dao.impl;

import com.nuist.homework0518.dao.CategoryDAO;
import com.nuist.homework0518.entity.ProductCategory;
import com.nuist.homework0518.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
