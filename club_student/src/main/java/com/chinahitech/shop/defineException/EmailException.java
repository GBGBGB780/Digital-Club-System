package com.chinahitech.shop.defineException;

public class EmailException extends Exception {
    String email;
    public EmailException(String email_) {
        email = email_;
    }

    public String expMessage() {
        return email + "不属于校园邮箱，请使用中大邮箱注册";
    }
}
