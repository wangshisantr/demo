package com.springboot.demo.jdbc;

import com.springboot.demo.jdbc.entity.User;
import com.springboot.demo.jdbc.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author wanglei
 * @Date 2020/2/5
 */
public abstract class AbstractDao<T> {
    /**
     * 增删改
     * @param sql
     * @param args
     * @return
     */
    public int update(String sql, Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtil.free(null, ps, conn);
        }
    }

    /**
     * 查询
     * @param sql
     * @param args
     * @return
     */
    public T find(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        T obj = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            obj =  rowMapper(rs);
            return obj;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
    }

    /**
     * 查询
     * @param sql
     * @param args
     * @return
     */
    public List<T> findList(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            list =  rowMapper2(rs);
            return list;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
    }
    abstract protected T rowMapper(ResultSet rs);
    abstract protected List<T> rowMapper2(ResultSet rs);
}
