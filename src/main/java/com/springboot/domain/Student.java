package com.springboot.domain;

import lombok.Data;

/**
 * @author wanglei
 * @date 2019/12/3
 */
@Data
public class Student {

    private Long id;
    private String name;
    private Integer age;

    public void sleep() {
        System.out.println("sleep...");
    }
}
