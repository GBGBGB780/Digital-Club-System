package com.chinahitech.shop.exception;

/*
用户已存在
 */
public class UseridDuplicateException extends ServiceException {
    public UseridDuplicateException() {
        super();
    }

    public UseridDuplicateException(String message) {
        super(message);
    }

    public UseridDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UseridDuplicateException(Throwable cause) {
        super(cause);
    }

    protected UseridDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
