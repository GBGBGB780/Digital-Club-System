package com.chinahitech.shop.exception;

/*
redis数据库错误
 */
public class RedisAddException extends ServiceException {
    public RedisAddException() {
        super();
    }

    public RedisAddException(String message) {
        super(message);
    }

    public RedisAddException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisAddException(Throwable cause) {
        super(cause);
    }

    protected RedisAddException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
