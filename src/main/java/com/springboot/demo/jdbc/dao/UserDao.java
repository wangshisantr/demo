package com.springboot.demo.jdbc.dao;

import com.springboot.demo.jdbc.entity.User;

import java.util.List;

/**
 * @Description
 * @Author wanglei
 * @Date 2020/2/4
 */
public interface UserDao {
    int add(User user);
    int delete(User user);
    int update(User user);
    User findById(Integer id);
    List<User> findByAge(Integer age);
}
