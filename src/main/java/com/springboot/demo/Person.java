package com.springboot.demo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wanglei
 */
@Data
public class Person implements Serializable {

    @PropertyMsg(value = "姓名", delete = true)
    private String name;
    @PropertyMsg(value = "年龄", delete = true)
    private Integer age;
    @PropertyMsg(value = "性别", replace = "1_男,2_女")
    private String sex;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println("eat...");
    }

    public Person() {
    }

    public Person(String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
