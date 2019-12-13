package com.springboot.demo;


import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Person> list1 = Lists.newArrayList();
        List<Person> list2 = Lists.newArrayList();
        Person p1 = new Person(null, 20, "1");
        Person p5 = new Person("小红", 21, "2");

        Person p2 = new Person("小明", 21, "2");
        Person p6 = new Person("小红", 22, "2");
        list1.add(new Person());
        list1.add(p1);
        list1.add(p5);
        // list2.add(p2);
        list2.add(p1);
        list2.add(p1);
        list2.add(p1);
        list2.add(p1);
        System.out.println(Test.getChangeInfo(list1, list2, "用户"));
    }

    /**
     * 获取两个list差异信息，修改删除增加
     *
     * @param aList
     * @param bList
     * @param <E>
     * @return
     */
    public static <E> String getChangeInfo(List<E> aList, List<E> bList, String prefix) {
        if (CollectionUtils.isEmpty(aList) || CollectionUtils.isEmpty(bList)) {
            return null;
        }
        BeanChangeUtil<E> changeUtil = new BeanChangeUtil<>();
        StringBuilder stringBuilder = new StringBuilder();
        // 如果大小相等
        if (aList.size() == bList.size()) {
            for (int i = 0; i < aList.size(); i++) {
                // 修改
                String compareResult = changeUtil.contrastObj(aList.get(i), bList.get(i));
                if (StringUtils.isNotBlank(compareResult)) {
                    stringBuilder.append(prefix).append(i + 1).append(":").append(compareResult);
                }

            }
        } else if (aList.size() - bList.size() > 0) {
            // a比b大,多的删除
            for (int i = 0; i < bList.size(); i++) {
                // 修改
                String compareResult = changeUtil.contrastObj(aList.get(i), bList.get(i));
                if (StringUtils.isNotBlank(compareResult)) {
                    stringBuilder.append(prefix).append(i + 1).append(":").append(compareResult);
                }
            }
            for (int i = bList.size(); i < aList.size(); i++) {
                // 删除
                stringBuilder.append(changeUtil.addOrDeleteObj(aList.get(i), (i + 1) + "", false, prefix));
            }

        } else if (aList.size() - bList.size() < 0) {
            // b比a大,多的新增
            for (int i = 0; i < aList.size(); i++) {
                // 修改
                String compareResult = changeUtil.contrastObj(aList.get(i), bList.get(i));
                if (StringUtils.isNotBlank(compareResult)) {
                    stringBuilder.append(prefix).append(i + 1).append(":").append(compareResult);
                }
            }
            for (int i = aList.size(); i < bList.size(); i++) {
                // 新增
                stringBuilder.append(changeUtil.addOrDeleteObj(bList.get(i), (i + 1) + "", true, prefix));
            }
        }
        return stringBuilder.toString();
    }

}
