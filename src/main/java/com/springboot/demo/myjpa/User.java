package com.springboot.demo.myjpa;

import lombok.Data;

import java.util.Date;

/**
 * @author wanglei
 * @date 2019/12/14
 */
@Data
@TableName("user")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @FieldName("create_time")
    private Date createTime;
    @FieldName("update_time")
    private Date updateTime;
    private Integer version;
    private Integer deleted;

    public static User getOne() {
        User u = new User();
        u.setId(1L);
        u.setName("liudehua");
        u.setAge(18);
        u.setEmail("1234566@qq.com");
        u.setCreateTime(new Date());
        u.setUpdateTime(new Date());
        u.setVersion(1);
        u.setDeleted(0);
        return u;
    }
}
