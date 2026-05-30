package com.nuist.homework0518.controller;

import com.nuist.homework0518.entity.Product;
import com.nuist.homework0518.entity.ProductCategory;
import com.nuist.homework0518.server.ManagerServer;
import com.nuist.homework0518.server.impl.ManagerServerImpl;
import com.nuist.homework0518.view.ManagerView;
import com.nuist.homework0518.view.ProductManagerView;

import java.util.List;
import java.util.Scanner;

public class ProductManagerController {
    private static ManagerServer managerServer = new ManagerServerImpl();
    public static void productManagerController(String userChoice){
        Scanner sc = new Scanner(System.in);
        switch (userChoice) {
            case "1":
                List<ProductCategory> categories = managerServer.getAllProductCategories();
                for(ProductCategory category : categories){
                    System.out.println(category);
                }
                System.out.println("请输入你想查看的商品种类");
                int categoryId = sc.nextInt();
                List<Product> products = managerServer.findByCategoryId(categoryId);
                for(Product category : products){
                    System.out.println(category);
                }
                break;
            case "2":
                Product p = new Product();
                System.out.println("请输入商品价格");
                p.setPrice(sc.nextBigDecimal());
                System.out.println("请输入商品库存");
                p.setStock(sc.nextInt());
                System.out.println("请输入商品种类");
                p.setCategoryId(sc.nextInt());
                System.out.println("请输入商品名称");
                p.setProductName(sc.next());
                System.out.println("请输入商品状态");
                p.setStatus(sc.nextBoolean());
                if(managerServer.addProduct(p)){
                    System.out.println("添加成功");
                }else {
                    System.out.println("添加失败");
                    ProductManagerView.productManagerView();
                }
                break;
            case "3":
                System.out.println("请输入你想修改的商品id");
                int id = sc.nextInt();
                System.out.println("请输入你想修改的商品库存");
                int stock = sc.nextInt();
                if(managerServer.updateStock(id,stock)){
                    System.out.println("修改成功");
                }else {
                    System.out.println("修改失败");
                    ProductManagerView.productManagerView();
                }
                break;
            case "4":
                System.out.println("请输入你想删除的商品id");
                int deleteId = sc.nextInt();
                if(managerServer.deleteProduct(deleteId)){
                    System.out.println("删除成功");
                }else {
                    System.out.println("删除失败");
                    ProductManagerView.productManagerView();
                }
                break;
            case "5":
                ManagerView.managerView();
                break;
            default:
                System.out.println("输入错误");
        }
        ManagerView.managerView();
    }
}
