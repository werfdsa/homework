package com.nuist.homework0518.server.impl;

import com.nuist.homework0518.dao.impl.AdminDAOImpl;
import com.nuist.homework0518.dao.impl.UserDAOImpl;
import com.nuist.homework0518.entity.Admin;
import com.nuist.homework0518.entity.User;
import com.nuist.homework0518.server.LoginServer;

public class LoginServerImpl implements LoginServer {
    @Override
    public boolean userServerLogin(String username, String password) {
        User user = new UserDAOImpl().findUserByUsernameAndPassword(username,password);
        return user == null ? false : true;

    }

    @Override
    public boolean adminServerLogin(String adminUserName, String password) {
        Admin admin = new AdminDAOImpl().findAdminByAdminNameAndPassword(adminUserName, password);
        return admin == null ? false : true;
    }

    @Override
    public boolean userRegister(String username, String password) {

        return new UserDAOImpl().insertUser(username, password);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return new UserDAOImpl().findUserByUsernameAndPassword(username, password);
    }
}
