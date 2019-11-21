package com.lwq.www.classloader.spi;

import com.mysql.jdbc.NonRegisteringDriver;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author: LWQ
 * @create: 2019/11/21
 * @description: MyDriver
 **/
public class MyDriver extends NonRegisteringDriver implements Driver {
    static {
        try {
            java.sql.DriverManager.registerDriver(new MyDriver());
        } catch (SQLException e) {
            throw new RuntimeException("Can't register driver!", e);
        }
    }

    public MyDriver() throws SQLException {
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        System.out.println("准备创建数据库连接，url：" + url);
        System.out.println("JDBC配置信息：" + info);
        info.setProperty("user", "root");
        Connection connect = super.connect(url, info);
        System.out.println("数据库连接创建完成！" + connect.toString());
        return connect;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.233.130:3306/nrts?useUnicode=true&characterEncoding=utf8";
        String user = "abc";
        String password = "root";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(1) from db_info");
            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt(1);

            }
            System.out.println("表: db_info 数据量: " + count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
