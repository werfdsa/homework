package com.nuist.homework0518.dao.impl;

import com.nuist.homework0518.dao.AdminDAO;
import com.nuist.homework0518.entity.Admin;
import com.nuist.homework0518.entity.User;
import com.nuist.homework0518.util.DBUtil;

import java.sql.*;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public Admin findAdminById(int id) {
        Admin admin = new Admin();
        String sql = "select * from adminusers where id =?";
        try(Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                admin.setId(rs.getInt("admin_id"));
                admin.setAdminName(rs.getString("admin_username"));
                admin.setPassword(rs.getString("admin_password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }

    @Override
    public boolean insertAdmin(Admin admin) {
        boolean result;
        String sql = "insert into adminusers(admin_username, admin_password) values(?,?)";
        try(Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, admin.getAdminName());
            ps.setString(2, admin.getPassword());
            int a = ps.executeUpdate();
            if(a == 1){
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
    public Admin findAdminByAdminName(String name) {
        Admin admin = new Admin();
        String sql = "select * from adminusers where id =?";
        try(Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                admin.setId(rs.getInt("admin_id"));
                admin.setAdminName(rs.getString("admin_username"));
                admin.setPassword(rs.getString("admin_password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }
}
