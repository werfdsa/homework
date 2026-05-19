package com.nuist.homework0518.dao.impl;

import com.nuist.homework0518.dao.ProductDAO;
import com.nuist.homework0518.entity.Product;
import com.nuist.homework0518.entity.User;
import com.nuist.homework0518.util.DBUtil;

import java.sql.*;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public Product findByProductName(String name) {
        Product product = new Product();
        String sql = "select * from products where id =?";
        try(Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                product.setId(rs.getInt("product_id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setStock(rs.getInt("stock"));
                product.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public boolean insertProduct(Product product) {
        boolean result;
        String sql = "insert into product(category_id,product_name,price,stock,status) values(?,?,?,?,? )";
        try(Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,product.getCategoryId());
            ps.setString(2, product.getProductName());
            ps.setBigDecimal(3,product.getPrice());
            ps.setInt(4,product.getStock());
            ps.setBoolean(5,product.getStatus());
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
