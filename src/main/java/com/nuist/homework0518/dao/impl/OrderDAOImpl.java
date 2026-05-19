package com.nuist.homework0518.dao.impl;

import com.nuist.homework0518.dao.OrderDAO;
import com.nuist.homework0518.dao.UserDAO;
import com.nuist.homework0518.entity.Order;
import com.nuist.homework0518.entity.User;
import com.nuist.homework0518.util.DBUtil;

import java.sql.*;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public User findUserByOrderId(int id) {
        User user = new User();
        String sql = "select * from  where id =?";
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
    public boolean insertOrder(Order order) {
        boolean result;
        String sql = "insert into orders(order_id,user_id,total_amount,order_status,create_time) values(?,?,?,?,now())";
        try(Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,order.getOrderId());
            ps.setInt(2,order.getUserId());
            ps.setDouble(3,order.getTotalAmount());
            ps.setString(4,String.valueOf(order.getOrderStatus()));
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
