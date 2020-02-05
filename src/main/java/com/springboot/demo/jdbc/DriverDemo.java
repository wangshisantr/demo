package com.springboot.demo.jdbc;

import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
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
    }
    @Test
    public void testDriverManager2() throws Exception {
        // 从配置文件拿到数据库配置信息
        Properties properties = new Properties();
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(resourceAsStream);
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        Class.forName(driver);
        // 用driverManager获得数据库连接
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println(connection.toString());
    }

    @Test
    public void t () throws SQLException {
        System.out.println(JdbcUtil.getConnection());
    }
}
