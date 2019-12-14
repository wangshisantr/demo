package com.springboot.demo.myjpa;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author wanglei
 * @date 2019/12/14
 */
public class BaseDao<T> {

    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&generateSimpleParameterMetadata=true");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
    }
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    private Class<T> beanClass;

    public BaseDao() {
        // this指代子类
        // 通过子类得到子类传给父类的泛型class对象,比如User.class
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        beanClass  = (Class) superclass.getActualTypeArguments()[0];
    }

    /**
     * 插入方法
     *
     * @param bean
     * @return
     */
    public int insert(T bean) {
        // 得到泛型类的所有字段
        Field[] fields = beanClass.getDeclaredFields();
        // 拼接sql insert into table_name values (?,?);
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ").append(beanClass.getSimpleName()).append(" values (");
        for (Field field : fields) {
            sb.append("?,");
        }
        sb.setLength(sb.length()-1);
        sb.append(")");
        List<Object> paramList = Lists.newArrayList();
        // 获取bean的值
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object object = field.get(bean);
                paramList.add(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Object[] paramArray = paramList.toArray();
        int size = paramList.size();
        Object[] params = paramList.toArray(new Object[size]);
        System.out.println("insert sql:" + sb.toString());
        System.out.println("insert param:" + paramList.toString());
        return jdbcTemplate.update(sb.toString(), params);
    }
}
