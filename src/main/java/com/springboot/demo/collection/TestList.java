package com.springboot.demo.collection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author: lei
 * @Date: 2020/1/8
 * @Description:
 */
public class TestList {
    public static void main(String[] args) {
        ArrayList<String> arrayList = Lists.newArrayList();
        arrayList.add("list1");
        arrayList.add("list2");
        arrayList.add("list3");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            // iterator的remove方法必须在next方法后调用
            // iterator.remove();
            // 用list对象的remove方法会使modCount发生变化，抛出ConcurrentModificationException
            // 也就是说，modCount记录修改此列表的次数：包括改变列表的结构，改变列表的大小，打乱列表的顺序等使正在进行迭代产生错误的结果。
            // ArrayList是线程不安全的，如果在使用迭代器的过程中有其他的线程修改了List就会抛出ConcurrentModificationException，这就是Fail-Fast机制。
            // arrayList.remove("list2");
        }
        System.out.println(arrayList.toString());
    }
}
