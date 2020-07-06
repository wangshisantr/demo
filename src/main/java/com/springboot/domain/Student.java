package com.springboot.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wanglei
 * @date 2019/12/3
 */
@Data
public class Student implements Comparable<Student> {

    private Long id;
    private String name;
    private Integer age;

    public Student(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    public void sleep() {
        System.out.println("sleep...");
    }

    @Override
    public int compareTo(Student student) {
        if (this.getAge() == null && student.getAge() == null) {
            return 0;
        }
        if (this.age == null) {
            return 1;
        }
        if (null == student.getAge()) {
            return -1;
        }
        return Integer.compare(this.getAge(), student.getAge());
    }
}
