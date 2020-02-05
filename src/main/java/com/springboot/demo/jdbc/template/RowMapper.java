package com.springboot.demo.jdbc.template;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description
 * @Author wanglei
 * @Date 2020/2/5
 */
public interface RowMapper<T> {
    /**
     * 映射结果集
     * @param rs
     * @return
     * @throws SQLException
     */
    T mapRow(ResultSet rs) throws SQLException;
}
