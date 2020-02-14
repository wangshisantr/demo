package com.springboot.demo.jdbc;

import com.springboot.demo.jdbc.template.MyDataSource;

import javax.sound.midi.Soundbank;
import javax.swing.plaf.SpinnerUI;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Description 数据库连接工具类 1.0
 * @Author wanglei
 * @Date 2020/2/1
 */
public class JdbcUtil {

    // private static Properties properties = null;
    // static {
    //     // 加载配置文件
    //     properties = new Properties();
    //     InputStream inputStream = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
    //     try {
    //         properties.load(inputStream);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     // 加载驱动类
    //     try {
    //         Class.forName(properties.getProperty("driver"));
    //     } catch (ClassNotFoundException e) {
    //         e.printStackTrace();
    //     }
    //
    // }
    //
    // /**
    //  * 获取数据库连接
    //  */
    // public static Connection getConnection() throws SQLException {
    //     return DriverManager.getConnection(properties.getProperty("url"),
    //             properties.getProperty("username"),
    //             properties.getProperty("password"));
    // }

    // 初始化一个数据源
    private static MyDataSource dataSource = new MyDataSource();

    // 获取连接
    public static Connection getConnection() throws SQLException {
        // 从数据源获取Connection并返回
        return dataSource.getConnection();
    }

    // 获取数据源
    public static MyDataSource getDataSource() {
        return dataSource;
    }


    /**
     * 释放资源
     * @param resultSet
     * @param st
     * @param conn
     */
    public static void free(ResultSet resultSet, Statement st, Connection conn) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) { st.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
