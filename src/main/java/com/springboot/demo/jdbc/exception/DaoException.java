package com.springboot.demo.jdbc.exception;

/**
 * @Description
 * @Author wanglei
 * @Date 2020/2/5
 */
public class DaoException extends RuntimeException{
    public DaoException() {}
    public DaoException(String message) {
        super(message);
    }
    public DaoException(Throwable throwable) {
        super(throwable);
    }
    public DaoException(String messge, Throwable throwable) {
        super(messge, throwable);
    }
}
