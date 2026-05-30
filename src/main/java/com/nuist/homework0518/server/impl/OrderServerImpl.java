package com.nuist.homework0518.server.impl;

import com.nuist.homework0518.dao.ProductDAO;
import com.nuist.homework0518.dao.impl.ProductDAOImpl;
import com.nuist.homework0518.entity.OrderStatus;
import com.nuist.homework0518.entity.Product;
import com.nuist.homework0518.server.OrderServer;
import com.nuist.homework0518.util.DBUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderServerImpl implements OrderServer {

    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public synchronized int placeOrder(int userId, Map<Integer, Integer> products) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            BigDecimal totalAmount = BigDecimal.ZERO;
            for (Map.Entry<Integer, Integer> entry : products.entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();

                String lockSql = "SELECT stock, price FROM products WHERE product_id = ? FOR UPDATE";
                try (PreparedStatement ps = conn.prepareStatement(lockSql)) {
                    ps.setInt(1, productId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            conn.rollback();
                            return -1;
                        }
                        int stock = rs.getInt("stock");
                        if (stock < quantity) {
                            conn.rollback();
                            return -1;
                        }
                        totalAmount = totalAmount.add(
                                rs.getBigDecimal("price").multiply(BigDecimal.valueOf(quantity)));
                    }
                }
            }

            for (Map.Entry<Integer, Integer> entry : products.entrySet()) {
                String sql = "UPDATE products SET stock = stock - ? WHERE product_id = ? AND stock >= ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, entry.getValue());
                    ps.setInt(2, entry.getKey());
                    ps.setInt(3, entry.getValue());
                    if (ps.executeUpdate() == 0) {
                        conn.rollback();
                        return -1;
                    }
                }
            }

            String orderSql = "INSERT INTO orders(user_id, total_amount, order_status, create_time) "
                    + "VALUES(?, ?, ?, NOW())";
            int orderId;
            try (PreparedStatement ps = conn.prepareStatement(orderSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, userId);
                ps.setBigDecimal(2, totalAmount);
                ps.setString(3, OrderStatus.Pending.name());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        orderId = rs.getInt(1);
                    } else {
                        conn.rollback();
                        return -1;
                    }
                }
            }

            for (Map.Entry<Integer, Integer> entry : products.entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();

                String prodSql = "SELECT product_name, price FROM products WHERE product_id = ?";
                String productName;
                BigDecimal unitPrice;
                try (PreparedStatement ps = conn.prepareStatement(prodSql)) {
                    ps.setInt(1, productId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            conn.rollback();
                            return -1;
                        }
                        productName = rs.getString("product_name");
                        unitPrice = rs.getBigDecimal("price");
                    }
                }

                String itemSql = "INSERT INTO order_items(order_id, product_id, product_name, unit_price, quantity, subtotal) "
                        + "VALUES(?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(itemSql)) {
                    ps.setInt(1, orderId);
                    ps.setInt(2, productId);
                    ps.setString(3, productName);
                    ps.setBigDecimal(4, unitPrice);
                    ps.setInt(5, quantity);
                    ps.setBigDecimal(6, unitPrice.multiply(BigDecimal.valueOf(quantity)));
                    ps.executeUpdate();
                }
            }

            conn.commit();
            return orderId;

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("下单失败", e);
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Product> getAvailableProducts() {
        return productDAO.findAll();
    }
}
