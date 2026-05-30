package com.nuist.homework0518.view;

import com.nuist.homework0518.controller.OrderController;
import com.nuist.homework0518.dao.impl.ProductDAOImpl;
import com.nuist.homework0518.entity.Product;
import com.nuist.homework0518.server.impl.OrderServerImpl;
import com.nuist.homework0518.util.Print;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserView {
    public static void userView(int userId) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            Print.print("点单系统");
            Print.print("1.查看菜单");
            Print.print("2.查看购物车");
            Print.print("3.退出登录");
            String userChoice = sc.nextLine();

            switch (userChoice) {
                case "1":
                    showMenu(userId);
                    break;
                case "2":
                    showCart(userId);
                    break;
                case "3":
                    Print.print("已退出登录");
                    LoginView.loginView();
                    return;
                default:
                    Print.print("输入错误，请重新输入");
            }
        }
    }

    private static void showMenu(int userId) {
        List<Product> products = new OrderServerImpl().getAvailableProducts();

        Print.print("---- 商品列表 ----");
        for (Product p : products) {
            Print.print("商品ID: " + p.getId()
                    + " | " + p.getProductName()
                    + " | 价格: " + p.getPrice()
                    + " | 库存: " + p.getStock());
        }
        Print.print();

        Scanner sc = new Scanner(System.in);
        Print.print("请输入要加入购物车的商品ID（输入0返回主菜单）");
        String input = sc.nextLine();
        if ("0".equals(input)) {
            return;
        }

        try {
            int productId = Integer.parseInt(input);
            boolean exists = products.stream().anyMatch(p -> p.getId() == productId);
            if (!exists) {
                Print.print("商品不存在");
                return;
            }

            Print.print("请输入数量");
            int quantity = sc.nextInt();
            sc.nextLine();

            if (quantity <= 0) {
                Print.print("数量必须大于0");
                return;
            }

            OrderController.addToCart(productId, quantity);
        } catch (NumberFormatException e) {
            Print.print("输入格式错误");
        }
    }

    private static void showCart(int userId) {
        Map<Integer, Integer> cart = OrderController.getCart();
        if (cart.isEmpty()) {
            Print.print("购物车为空");
            return;
        }

        Print.print("---- 购物车 ----");
        ProductDAOImpl productDAO = new ProductDAOImpl();
        BigDecimal total = BigDecimal.ZERO;

        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            Product product = productDAO.findById(entry.getKey());
            if (product != null) {
                BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(entry.getValue()));
                Print.print(product.getProductName() + " x " + entry.getValue() + " = ¥" + subtotal);
                total = total.add(subtotal);
            }
        }
        Print.print("总计: ¥" + total);
        Print.print();

        Scanner sc = new Scanner(System.in);
        Print.print("1.提交订单");
        Print.print("2.清空购物车");
        Print.print("3.返回主菜单");
        String choice = sc.nextLine();

        switch (choice) {
            case "1":
                OrderController.placeOrder(userId);
                break;
            case "2":
                OrderController.clearCart();
                Print.print("购物车已清空");
                break;
            case "3":
                break;
            default:
                Print.print("输入错误");
        }
    }
}
