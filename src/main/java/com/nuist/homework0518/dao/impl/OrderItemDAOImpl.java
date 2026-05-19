package com.nuist.homework0518.dao.impl;

import com.nuist.homework0518.dao.OrderItemDAO;
import com.nuist.homework0518.entity.OrderItem;
import com.nuist.homework0518.util.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDAOImpl implements OrderItemDAO {
    @Override
    public boolean insertItem(OrderItem orderItem) {
        boolean result;
        String sql = "insert into order_items(order_id,product_id,product_name,unit_price,quantity,subtotal) values(?,?,?,?,?,? )";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getProductId());
            ps.setString(3, orderItem.getProductName());
            ps.setBigDecimal(4, orderItem.getProductPrice());
            ps.setInt(5, orderItem.getQuantity());
            ps.setBigDecimal(6, orderItem.getSubtotal());
            if (ps.executeUpdate() == 1) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
