package com.chinahitech.shop.service;

import org.springframework.util.DigestUtils;

import java.util.Objects;

/*
MD5算法加密
*/

public class md5 {
    /*
    MD5算法加密判断
    */
    public boolean isEqual(String oldPwd, String currPwd, String salt){
        String newPwd = MD5handler(currPwd, salt);
        return Objects.equals(oldPwd, newPwd);
    }

    /*
    MD5算法实现
     */
    public String MD5handler(String pwd, String salt){
        //三次加密
        for(int i = 0;i < 3;i++){
            pwd = DigestUtils.md5DigestAsHex((salt + pwd + salt).getBytes()).toUpperCase();
        }
        return pwd;
    }
}
