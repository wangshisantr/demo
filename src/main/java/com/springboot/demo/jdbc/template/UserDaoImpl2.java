package com.springboot.demo.jdbc.template;

import com.springboot.demo.jdbc.AbstractDao;
import com.springboot.demo.jdbc.dao.UserDao;
import com.springboot.demo.jdbc.entity.User;
import com.springboot.demo.jdbc.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author wanglei
 * @Date 2020/2/5
 */
public class UserDaoImpl2 implements UserDao {

    private MyJDBCTemplate jdbcTemplate = new MyJDBCTemplate();

    @Override
    public int add(User user) {
        return jdbcTemplate.update("insert into user (name,age) values(?,?)", user.getName(), user.getAge());
    }

    @Override
    public int delete(User user) {
        return jdbcTemplate.update("delete from user where id=?", user.getId());
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update("update user set name=?, age=? where id=?", user.getId());
    }

    @Override
    public User findById(Integer id) {
        String sql = "select * from user where id = ?";
        List<User> list = jdbcTemplate.query(sql, (RowMapper<User>) rs -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setAge(rs.getInt("age"));
            user.setName(rs.getString("name"));
            return user;
        }, 1);
        return list.get(0);
    }

    @Override
    public List<User> findByAge(Integer age) {
        String sql = "Select * from user where age = ?";
        return  jdbcTemplate.query(sql, (RowMapper<User>) rs -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setAge(rs.getInt("age"));
            user.setName(rs.getString("name"));
            return user;
        }, age);
    }
}
