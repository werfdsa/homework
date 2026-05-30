package com.nuist.homework0518.server;

import com.nuist.homework0518.entity.*;

import java.util.List;

public interface ManagerServer {
    List<User> getAllUsers();

    boolean deleteUser(int id);

    List<Order> getAllOrders();

    boolean deleteOrder(int id);

    boolean modifyOrder(int id, OrderStatus orderStatus);

    List<Product> getAllProducts();

    List<Product> findByCategoryId(int categoryId);

    List<ProductCategory> getAllProductCategories();

    boolean addProduct(Product product);

    boolean updateStock(int productId, int quantity);

    boolean deleteProduct(int productId);

    boolean addAdmin(String name, String password);
}
