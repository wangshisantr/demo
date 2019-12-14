package com.springboot.demo.myjpa;

import lombok.Data;

import java.util.Date;

/**
 * @author wanglei
 * @date 2019/12/14
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Date create_time;
    private Date update_time;
    private Integer version;
    private Integer deleted;

    public static User getOne() {
        User u = new User();
        u.setId(1L);
        u.setName("liudehua");
        u.setAge(18);
        u.setEmail("1234566@qq.com");
        u.setCreate_time(new Date());
        u.setUpdate_time(new Date());
        u.setVersion(1);
        u.setDeleted(0);
        return u;
    }
}
