package com.nuist.homework0518.server.impl;

import com.nuist.homework0518.dao.*;
import com.nuist.homework0518.dao.impl.*;
import com.nuist.homework0518.entity.*;
import com.nuist.homework0518.server.ManagerServer;

import java.util.List;

public class ManagerServerImpl implements ManagerServer {
    UserDAO userDAO = new UserDAOImpl();
    OrderDAO orderDAO = new OrderDAOImpl();
    ProductDAO productDAO = new ProductDAOImpl();
    CategoryDAO categoryDAO = new CategoryDAOImpl();
    AdminDAO adminDAO = new AdminDAOImpl();
    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public boolean deleteUser(int id) {
        return userDAO.deleteUser(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.findAllOrders();
    }

    @Override
    public boolean deleteOrder(int id) {
        return false;
    }

    @Override
    public boolean modifyOrder(int id, OrderStatus orderStatus) {
        return orderDAO.modifyOrder(id, orderStatus);
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {
        return categoryDAO.findAllCategories();
    }

    @Override
    public List<Product> findByCategoryId(int categoryId) {
        return productDAO.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    @Override
    public boolean addProduct(Product product) {
        return productDAO.insertProduct(product);
    }

    @Override
    public boolean updateStock(int productId, int quantity) {
        return productDAO.updateStock(productId, quantity);
    }

    @Override
    public boolean deleteProduct(int productId) {
        return productDAO.deleteProduct(productId);
    }

    @Override
    public boolean addAdmin(String adminName, String password) {
        return adminDAO.insertAdmin(adminName, password);
    }
}
