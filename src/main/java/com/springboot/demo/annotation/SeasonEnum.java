package com.springboot.demo.annotation;

import lombok.Getter;

/**
 * 季节枚举
 *
 * @author lei
 */
@Getter
public enum SeasonEnum {

    /**
     * 春季
     **/
    SPRING("001", "春"),
    /**
     * 夏季
     **/
    SUMMER("002", "夏"),
    /**
     * 秋季
     **/
    AUTUMN("003", "秋"),
    /**
     * 冬季
     **/
    WINTER("005", "冬"),
    ;

    private String code;
    private String name;

    SeasonEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}