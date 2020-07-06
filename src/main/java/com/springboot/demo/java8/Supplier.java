package com.springboot.demo.java8;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wanglei
 * @description
 * @date 2020/6/15
 */
@FunctionalInterface
public interface Supplier<T> {
    T get();
}

class Car {
    // Supplier是jdk1.8的接口，这里和lambda一起使用了
    // 方法引用
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("collide:" + car.toString());
    }

    public void follow(final Car car) {
        System.out.println("follow:" + car.toString());
    }

    public void repair() {
        System.out.println("repair:" + this.toString());
    }

    public static void main(String[] args) throws ParseException {
        // 构造器引用：它的语法是Class::new，或者更一般的Class< T >::new
        final Car car = Car.create(Car::new);
        final List<Car> cars = Collections.singletonList(car);

        // 静态方法引用：它的语法是Class::static_method
        cars.forEach(Car::collide);

        // 特定类的任意对象的方法引用：它的语法是Class::method
        cars.forEach(Car::repair);

        // 特定对象的方法引用：它的语法是instance::method
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);

        List<Integer> list = Arrays.asList(12, 24, 23, 435);
        list.sort(Integer::compareTo);
        list.sort(Integer::compare);
        System.out.println(list);

        Date parse = new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-07");
        System.out.println(parse);
    }
}
