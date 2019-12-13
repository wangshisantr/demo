package com.springboot.demo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wanglei
 * @date 2019/11/7
 */
@Data
public class TestChange {

    public static void main(String[] args) {
        TestChange u1 = new TestChange("我是谁", "ok", 30, "刘德华", (byte) 1);
        TestChange u2 = new TestChange("我在哪", "no", 20, "郭富城", (byte) 2);
        BeanChangeUtil<TestChange> t = new BeanChangeUtil<>();
        String str = t.contrastObj(u1, u2);
        if ("".equals(str)) {
            System.out.println("未有改变");
        } else {
            System.out.println(str);
        }
        System.out.println(t.addOrDeleteObj(u1, "", true, "用户"));
        BigDecimal a = new BigDecimal(4);
        System.out.println(a.divide(new BigDecimal(7),6,BigDecimal.ROUND_HALF_UP));
    }

    public TestChange() {
    }

    public TestChange(String about, String lock, Integer age, String name, Byte sex) {
        this.about = about;
        this.lock = lock;
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    @PropertyMsg(value = "关于")
    private String about;
    private String lock;
    private Integer age;
    @PropertyMsg(value = "姓名", delete = true)
    private String name;
    @PropertyMsg(value = "姓别", replace = "1_男,2_女")
    private Byte sex;
}
