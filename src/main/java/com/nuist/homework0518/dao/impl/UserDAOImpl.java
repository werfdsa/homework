package com.nuist.homework0518.dao.impl;

import com.nuist.homework0518.dao.UserDAO;
import com.nuist.homework0518.entity.User;
import com.nuist.homework0518.util.DBUtil;

import java.sql.*;

public class UserDAOImpl implements UserDAO {
    @Override
    public User findUserById(int id){

        User user = new User();
        String sql = "select * from users where id =?";
        try(Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setCreateTime(rs.getDate("create_time"));
                user.setModifyTime(rs.getDate("update_time"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public boolean insertUser(User user){
        boolean result;
        String sql = "insert into users(username, password, create_time, update_time) values(?,?,now(),? )";
        try(Connection c = DBUtil.getConnection();
        PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setDate(3,(Date) user.getModifyTime());
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
    public User findUserByUsernameAndPassword(String name, String password) {
        User user = new User();
        String sql = "select * from users where id =?, password = ?";
        try(Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,name);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setCreateTime(rs.getDate("create_time"));
                user.setModifyTime(rs.getDate("update_time"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
