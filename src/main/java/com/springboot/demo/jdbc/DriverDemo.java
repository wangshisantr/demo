package com.springboot.demo.jdbc;

import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.sql.Connection;
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
        String url = "jdbc:mysql://47.101.169.192:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");
        Connection connection= driver.connect(url, info);
        System.out.println(connection.toString());
    }
}
