package com.chinahitech.shop.defineException;

public class RedisAddException extends Exception{
    public String expMessage() {
        return "Redis added error!";
    }
}
