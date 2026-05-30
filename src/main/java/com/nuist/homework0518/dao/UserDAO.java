package com.nuist.homework0518.dao;

import com.nuist.homework0518.entity.User;

import java.util.List;

public interface UserDAO {
    User findUserById(int id);
    boolean insertUser(String username, String password);
    User findUserByUsernameAndPassword(String name, String password);
    List<User> findAll();

    boolean deleteUser(int id);
}
