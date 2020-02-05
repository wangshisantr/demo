package com.springboot.demo.jdbc;

import com.springboot.demo.jdbc.dao.impl.UserDaoImpl;
import com.springboot.demo.jdbc.entity.User;
import com.springboot.demo.jdbc.template.UserDaoImpl2;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 * @author: wanglei
 * @Date: 2020/1/19
 * @Description:
 */
public class TestJDBC {

    @Test
    public void testJDBC() throws Exception {
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
        // 创建模板
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id = ?");
        // 设置模板参数
        preparedStatement.setInt(1, 1);;
        // 处理结果
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1) + "\t" + resultSet.getObject(2) + "\t" + resultSet.getObject(3));
        }
        // 释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testDao() {
        User u = new UserDaoImpl().findById(1);
        System.out.println(u.toString());
    }
    @Test
    public void testDao2() {
        List<User> list = new UserDaoImpl().findByAge(22);
        System.out.println(list.toString());
    }
    @Test
    public void testDao3() {
        User u = new UserDaoImpl2().findById(1);
        System.out.println(u.toString());
        List<User> list = new UserDaoImpl2().findByAge(22);
        System.out.println(list.toString());
    }
}
