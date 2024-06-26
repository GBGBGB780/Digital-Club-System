package com.chinahitech.shop.exception;


public class EmailException extends ServiceException {
    String email;
//    public EmailException(String email_) {
//        email = email_;
//    }

//    public String expMessage() {
//        return email + "不属于校园邮箱，请使用中大邮箱注册";
//    }

    public EmailException() {
        super();
    }

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailException(Throwable cause) {
        super(cause);
    }

    protected EmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
