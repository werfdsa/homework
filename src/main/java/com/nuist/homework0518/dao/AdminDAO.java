package com.nuist.homework0518.dao;

import com.nuist.homework0518.entity.Admin;

public interface AdminDAO {
    public Admin findAdminById(int id);
    public boolean insertAdmin(String name, String password);
    Admin findAdminByAdminNameAndPassword(String name, String password);
}
