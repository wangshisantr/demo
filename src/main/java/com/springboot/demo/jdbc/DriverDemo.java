package com.springboot.demo.jdbc;

import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: wanglei
 * @Date: 2020/1/20
 * @Description:
 */
public class DriverDemo {
    @Test
    public void testDriver() throws SQLException {
        // 用driver拿到一个数据库连接
        Driver driver = new Driver();
        String url = "jdbc:mysql://47.101.169.192:3306/test?useSSL=false";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");
        Connection connection= driver.connect(url, info);
        System.out.println(connection.toString());
    }

    @Test
    public void testDriverManager() throws Exception {
        // todo ??不用这句也能拿到数据库连接
        Class.forName("com.mysql.jdbc.Driver");
        // 用driverManager获得数据库连接
        String url = "jdbc:mysql://47.101.169.192:3306/test?useSSL=false";
        String username = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println(connection.toString());
        PreparedStatement preparedStatement = connection.prepareStatement("select * from test");
    }
    
}
