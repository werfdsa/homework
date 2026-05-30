package com.nuist.homework0518.server;

import com.nuist.homework0518.entity.User;

public interface LoginServer {
    public boolean userServerLogin(String username, String password);
    public boolean adminServerLogin(String adminUserName, String password);

    public boolean userRegister(String username, String password);
    User getUserByUsernameAndPassword(String username, String password);
}
