package com.springboot.demo.myjpa;

/**
 * @author wanglei
 * @date 2019/12/14
 */
public class UserDao extends BaseDao<User> {
    @Override
    public int insert(User bean) {
        return super.insert(bean);
    }
}
