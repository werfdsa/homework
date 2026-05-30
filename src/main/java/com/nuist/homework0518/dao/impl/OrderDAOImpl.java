package com.nuist.homework0518.dao.impl;

import com.nuist.homework0518.dao.OrderDAO;
import com.nuist.homework0518.dao.UserDAO;
import com.nuist.homework0518.entity.Order;
import com.nuist.homework0518.entity.OrderStatus;
import com.nuist.homework0518.entity.User;
import com.nuist.homework0518.util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public User findUserByOrderId(int id) {
        User user = new User();
        String sql = "select * from orders where order_id =?";
        try(Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                UserDAO userDAO = new UserDAOImpl();
                user = userDAO.findUserById(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public int insertOrder(Order order) {
        String sql = "INSERT INTO orders(user_id, total_amount, order_status, create_time) VALUES(?, ?, ?, NOW())";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getUserId());
            ps.setBigDecimal(2, BigDecimal.valueOf(order.getTotalAmount()));
            ps.setString(3, order.getOrderStatus().name());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public List<Order> findAllOrders() {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY create_time DESC";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalAmount(rs.getInt("total_amount"));
                order.setOrderStatus(OrderStatus.valueOf(rs.getString("order_status")));
                order.setCreateTime(rs.getTimestamp("create_time"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }

    @Override
    public boolean modifyOrder(int id, OrderStatus status) {
        String sql = "UPDATE orders SET order_status = ? WHERE order_id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, status.name());
            ps.setInt(2, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
