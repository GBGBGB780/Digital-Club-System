package com.chinahitech.shop.exception;

/*
申请次数过多
 */
public class ApplyException extends ServiceException{
    public ApplyException() {
        super();
    }

    public ApplyException(String message) {
        super(message);
    }

    public ApplyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplyException(Throwable cause) {
        super(cause);
    }

    protected ApplyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


