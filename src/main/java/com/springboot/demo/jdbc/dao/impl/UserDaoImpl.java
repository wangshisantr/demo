package com.springboot.demo.jdbc.dao.impl;

import com.springboot.demo.jdbc.AbstractDao;
import com.springboot.demo.jdbc.JdbcUtil;
import com.springboot.demo.jdbc.dao.UserDao;
import com.springboot.demo.jdbc.entity.User;
import com.springboot.demo.jdbc.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author wanglei
 * @Date 2020/2/4
 */
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    @Override
    public int add(User user) {
        return super.update("insert into user (name,age) values(?,?)", user.getName(), user.getAge());
    }

    @Override
    public int delete(User user) {
        return super.update("delete from user where id=?", user.getId());
    }

    @Override
    public int update(User user) {
        return super.update("update user set name=?, age=? where id=?", user.getId());
    }

    @Override
    public User findById(Integer id) {
        String sql = "select * from user where id = ?";
        return  super.find(sql, 1);
    }

    @Override
    public List<User> findByAge(Integer age) {
        return super.findList("select * from user where age = ?",age);
    }

    @Override
    protected User rowMapper(ResultSet rs) {
        User user = new User();
        try {
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
            }
        } catch (SQLException e) {
            throw new DaoException("mapper error");
        }
        return user;
    }

    @Override
    protected List<User> rowMapper2(ResultSet rs) {
        List<User> list = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("mapper error");
        }
        return list;
    }
}
