package com.springboot.demo.annotation;

import java.lang.annotation.*;

/**
 * @author lei
 */
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
// 注解保留策略
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    //八种基本数据类型 byte short int long char boolean float double
    int intValue() default 0;
    // String
    String value() default "";
    // 枚举
    SeasonEnum seasonEnum() default SeasonEnum.AUTUMN;
    // Class
    Class<Person> clazz();
    // 注解类型
    PropertyMsg annotation();
    // 以上类型的一维数组
    int [] intArray();
}
