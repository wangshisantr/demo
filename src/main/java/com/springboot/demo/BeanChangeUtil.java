package com.springboot.demo;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @date 2019/11/7
 */
public class BeanChangeUtil<T> {
    public String contrastObj(Object oldBean, Object newBean) {
        // 创建字符串拼接对象
        StringBuilder str = new StringBuilder();
        // 转换为传入的泛型T
        T pojo1 = (T) oldBean;
        T pojo2 = (T) newBean;
        // 通过反射获取类的Class对象
        Class clazz = pojo1.getClass();
        // 获取类型及字段属性
        Field[] fields = clazz.getDeclaredFields();
        return jdk8Before(fields, pojo1, pojo2, str, clazz);
//        return jdk8OrAfter(fields, pojo1, pojo2, str,clazz);
    }

    /**
     * 获取添加或删除对象的字符串描述
     * @param bean  添加或删除对象
     * @param index 第几个
     * @param isAdd 是否是新增
     * @param prefix 字符串前缀，bean的信息描述
     * @return
     */
    public String addOrDeleteObj(T bean, String index, boolean isAdd, String prefix) {
        StringBuilder str = new StringBuilder();
        str = isAdd ? str.append("新增") : str.append("删除");
        str.append(prefix).append(index).append(":");
        Class clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(PropertyMsg.class)) {
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                    // 获取对应属性值
                    Method getMethod = pd.getReadMethod();
                    Object object = getMethod.invoke(bean);
                    if (object == null) {
                        continue;
                    }
                    String replace = field.getAnnotation(PropertyMsg.class).replace();
                    String appendStr;
                    // 替换replace字段
                    if (StringUtils.isNotBlank(replace)) {
                        appendStr = getReplaceString(replace, object);
                    } else {
                        appendStr = object.toString();
                    }
                    // 添加
                    if (isAdd) {
                        str.append(field.getAnnotation(PropertyMsg.class).value()).append(":\"").append(appendStr).append("\";");
                    } else {
                        // 判断是否是删除字段
                        if (field.getAnnotation(PropertyMsg.class).delete()) {
                            str.append(field.getAnnotation(PropertyMsg.class).value()).append(":\"").append(appendStr).append("\";");
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return str.toString();
    }

    // jdk8 普通循环方式
    private String jdk8Before(Field[] fields, T pojo1, T pojo2, StringBuilder str, Class clazz) {
        int i = 1;
        try {
            for (Field field : fields) {
                if (field.isAnnotationPresent(PropertyMsg.class)) {
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                    // 获取对应属性值
                    Method getMethod = pd.getReadMethod();
                    Object o1 = getMethod.invoke(pojo1);
                    Object o2 = getMethod.invoke(pojo2);
                    if (o1 == null && o2 == null) {
                        continue;
                    }
                    if (o1 == null) {
                        str.append("从\"空\"改为\"").append(o2).append("\";");
                    } else if (o2 == null) {
                        str.append("从\"").append(o1).append("\"改为\"\";");
                    } else if (!o1.toString().trim().equals(o2.toString().trim())) {
                        // 1_男,2_女
                        String replace = field.getAnnotation(PropertyMsg.class).replace();
                        if (StringUtils.isNotBlank(replace)) {
                            String oldStr = getReplaceString(replace, o1);
                            String newStr = getReplaceString(replace, o2);
                            str.append(field.getAnnotation(PropertyMsg.class).value()).append(":").append("从\"").append(oldStr).append("\"改为\"").append(newStr).append("\";");
                        } else {
                            str.append(field.getAnnotation(PropertyMsg.class).value()).append(":").append("从\"").append(o1).append("\"改为\"").append(o2).append("\";");
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    // lambda表达式，表达式内部的变量都是final修饰，需要传入需要传入final类型的数组
    private String jdk8OrAfter(Field[] fields, T pojo1, T pojo2, StringBuilder str, Class clazz) {
        final int[] i = {1};
        Arrays.asList(fields).forEach(f -> {
            if (f.isAnnotationPresent(PropertyMsg.class)) {
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(f.getName(), clazz);
                    // 获取对应属性值
                    Method getMethod = pd.getReadMethod();
                    Object o1 = getMethod.invoke(pojo1);
                    Object o2 = getMethod.invoke(pojo2);
                    if (o1 == null || o2 == null) {
                        return;
                    }
                    if (!o1.toString().equals(o2.toString())) {
                        str.append(i[0] + "、" + f.getAnnotation(PropertyMsg.class).value() + ":" + "修改前=>" + o1 + "\t修改后=>" + o2 + "\n");
                        i[0]++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return str.toString();
    }

    /**
     * 通过replace替换字段值
     *
     * @param replace
     * @param obj
     * @return
     */
    private String getReplaceString(String replace, Object obj) {
        if (StringUtils.isEmpty(replace) || obj == null) {
            return null;
        }
        String[] split = replace.split(",");
        for (String s :
                split) {
            String oldStr = StringUtils.substringBefore(s, "_");
            String newStr = StringUtils.substringAfter(s, "_");
            if (obj.toString().equals(oldStr)) {
                return newStr;
            }
        }
        return obj.toString();
    }

    public static void main(String[] args) {
        String s = "1_男,2_女";
        System.out.println(new BeanChangeUtil<>().getReplaceString(s, "1"));
        System.out.println(new BeanChangeUtil<>().getReplaceString(s, "22"));
    }
}
