package com.nuist.homework0518.dao;

import com.nuist.homework0518.entity.User;

public interface UserDAO {
    public User findUserById(int id);
    public boolean insertUser(User user);
    public User findUserByUsernameAndPassword(String name, String password);

}
