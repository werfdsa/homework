package com.nuist.homework0518.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String url = "jdbc: mysql://localhost:3306/test2?character=utf8&useSSL=false&serverTimeZone=UTC&allowPublicKeyRetrieval=true";
    private static final String useName = "root";
    private static final String password= "123456";
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,useName,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
