package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.DriverManager;

/**
 * @Author tdragon.
 * @Date 2020/10/31.
 * @Time 19:09
 * @Description: 返回数据库连接对象
 */

public class dBConnection {
    public static Connection getConnection(){
        Connection conn=null;
        try {
            //加载驱动
            /**
             * jdbc4.0 是不用显式的去加载驱动，如果驱动包符合 SPI 模式就会自动加载
             * 就是说程序会自动去项目中查找是否有驱动，当然没有驱动的话自然是连接不了的
             */
            Class.forName("com.mysql.jdbc.Driver");
            //建立数据库连接，获取连接对象
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/requiretest","root","root");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection connection=dBConnection.getConnection();
        if(connection==null)
        {
            System.out.println("connect fail");
        }
        else{
            System.out.println("connect success");
        }
    }
}

