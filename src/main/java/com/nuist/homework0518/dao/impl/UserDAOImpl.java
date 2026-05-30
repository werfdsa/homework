package com.nuist.homework0518.dao.impl;

import com.nuist.homework0518.dao.UserDAO;
import com.nuist.homework0518.entity.User;
import com.nuist.homework0518.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public User findUserById(int id){

        User user = new User();
        String sql = "select * from users where user_id =?";
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
    public boolean insertUser(String username, String password){
        boolean result;
        String sql = "insert into users(username, password, create_time, update_time) values(?,?,now(),? )";
        try(Connection c = DBUtil.getConnection();
        PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,username);
            ps.setString(2, password);
            ps.setDate(3,null);
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
        User user = null; // 用 null 而不是 new User() 以便判断是否查到
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setCreateTime(rs.getDate("create_time"));
                    user.setModifyTime(rs.getDate("update_time"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users ";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setCreateTime(rs.getDate("create_time"));
                user.setModifyTime(rs.getDate("update_time"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
