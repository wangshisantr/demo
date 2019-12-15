package com.springboot.demo.myjpa;

/**
 * @author wanglei
 * @date 2019/12/14
 */
public class TestUserDao {
    public static void main(String[] args) {
        User u = User.getOne();
        u.setId(12L);
        u.setCreate_time(null);
        u.setUpdate_time(null);
        UserDao userDao = new UserDao();
        System.out.println(userDao.insert(u));
    }
}
