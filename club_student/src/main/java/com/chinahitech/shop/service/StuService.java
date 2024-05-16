package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.Students;
import com.chinahitech.shop.mapper.StuMapper;
import com.chinahitech.shop.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class StuService {
    @Autowired
    private StuMapper stuMapper;

    public Students getByStunumber(String num) {
        Students stu = stuMapper.getByNum(num);
        if (stu == null) {
            throw new EntityNotFoundException("学生"+ num +"不存在");
        }
        return stu;
    }

    public Students login(String num,String pwd) {
        Students stu = stuMapper.getByNum(num);
        if (stu == null){
            throw new EntityNotFoundException("学生"+ num +"不存在");
        }
        String oldPwd = stu.getPassword();
        //获取盐值
        String salt = stu.getSalt();
//        System.out.println(salt);
        //获取用户输入的密码对应的加密
//        String newPwd = MD5handler(pwd,salt);
        if (!isEqual(oldPwd,pwd,salt)){
//            System.out.println(oldPwd);
//            System.out.println(pwd);
            throw new PwdNotMatchException("密码错误");
        }
        return stu;
    }

//  md5加密
    public void addStudent(String stunumber, String lastPwd, String email) {
        Date date = new Date();
        //密码加密(MD5算法)
//            System.out.println(lastPwd);
        String salt = UUID.randomUUID().toString().toUpperCase();
//            System.out.println(salt);
        String currPwd = MD5handler(lastPwd, salt);

        int i = stuMapper.addStudent(stunumber, currPwd, email, salt, date, date);
        if(i != 1){
            throw new InsertException("学生"+ stunumber +"添加失败");
        }
    }

//  md5加密
    public void updatePassword(String stunumber, String password){
        Students stu = stuMapper.getByNum(stunumber);
//        String oldMD5pwd = stu.getPwd();
        String salt = stu.getSalt();
//        if (!isEqual(oldMD5pwd, oldPwd, salt)){
//            throw new PwdNotMatchException("密码错误");
//        }
        String newMD5pwd = MD5handler(password, salt);
        Date date = new Date();
//        System.out.println(newMD5pwd);
        int i = stuMapper.updatePassword(stunumber, newMD5pwd, date);
        if(i != 1){
            throw new UpdateException("学生"+ stunumber +"密码修改失败");
        }
    }

    public void updatePhone(String stunumber, String phone){
        Date date = new Date();
        int i = stuMapper.updatePhone(stunumber, phone, date);
        if(i != 1){
            throw new UpdateException("学生"+ stunumber +"电话修改失败");
        }
    }

    public void updateDescription(String stunumber, String description){
        Date date = new Date();
        int i = stuMapper.updateDescription(stunumber, description, date);
        if(i != 1){
            throw new UpdateException("学生"+ stunumber +"简介修改失败");
        }
    }

    public void updateNickname(String stunumber, String nickname){
        Date date = new Date();
        int i = stuMapper.updateNickname(stunumber, nickname, date);
        if(i != 1){
            throw new UpdateException("学生"+ stunumber +"昵称修改失败");
        }
    }

    /*
    MD5算法加密判断
    */
    private boolean isEqual(String oldPwd, String currPwd, String salt){
        String newPwd = MD5handler(currPwd, salt);
        return Objects.equals(oldPwd, newPwd);
    }

    /*
    MD5算法实现
     */
    private String MD5handler(String pwd, String salt){
        //三次加密
        for(int i = 0;i < 3;i++){
            pwd = DigestUtils.md5DigestAsHex((salt + pwd + salt).getBytes()).toUpperCase();
        }
        return pwd;
    }

}
