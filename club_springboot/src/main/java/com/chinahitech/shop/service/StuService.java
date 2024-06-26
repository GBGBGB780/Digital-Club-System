package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.exception.EntityNotFoundException;
import com.chinahitech.shop.exception.InsertException;
import com.chinahitech.shop.exception.UpdateException;
import com.chinahitech.shop.mapper.StuMapper;
import com.chinahitech.shop.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class StuService {
    @Autowired
    private StuMapper stuMapper;
    private Md5 md5 = new Md5();

    public User getByStuNumber(String num) {
        User stu = stuMapper.getByNum(num);
        if (stu == null) {
            throw new EntityNotFoundException("学生"+ num +"不存在");
        }
        return stu;
    }

    public User login(String num, String pwd) {
        User stu = stuMapper.getByNum(num);
        if (stu == null){
            throw new EntityNotFoundException("学生"+ num +"不存在");
        }
        String oldPwd = stu.getPassword();
        //获取盐值
        String salt = stu.getSalt();
//        System.out.println(salt);
        //获取用户输入的密码对应的加密
//        String newPwd = MD5handler(pwd,salt);
        if (!md5.isEqual(oldPwd,pwd,salt)){
//            System.out.println(oldPwd);
//            System.out.println(pwd);
            stu.setPassword(null);
//            throw new PwdNotMatchException("密码错误");
        }
        return stu;
    }

//  md5加密
    public void addStudent(String stuNumber, String lastPwd, String email) {
        Date date = new Date();
        //密码加密(MD5算法)
//            System.out.println(lastPwd);
        String salt = UUID.randomUUID().toString().toUpperCase();
//            System.out.println(salt);
        String currPwd = md5.MD5handler(lastPwd, salt);

        User stu = stuMapper.getByNum(stuNumber);
        if (stu != null) {
            throw new UseridDuplicateException("学生"+ stuNumber +"已存在");
        } else {
            int i = stuMapper.addStudent(stuNumber, currPwd, email, salt, date, date, 0);
            if(i != 1){
                throw new InsertException("学生"+ stuNumber +"添加失败");
            }
        }
    }


//  md5加密
    public void updatePassword(String stunumber, String password){
        User stu = stuMapper.getByNum(stunumber);
//        String oldMD5pwd = stu.getPwd();
        String salt = stu.getSalt();
//        if (!isEqual(oldMD5pwd, oldPwd, salt)){
//            throw new PwdNotMatchException("密码错误");
//        }
        String newMD5pwd = md5.MD5handler(password, salt);
//        System.out.println(newMD5pwd);
        Date date = new Date();

        stu.setPassword(newMD5pwd);
        stu.setModifyTime(date);

        int i = stuMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("学生"+ stunumber +"密码修改失败");
        }
    }

    public void updatePhone(String stuNumber, String phone){
        Date date = new Date();

        User stu = getByStuNumber(stuNumber);
        stu.setPhone(phone);
        stu.setModifyTime(date);

        int i = stuMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("学生"+ stuNumber +"电话修改失败");
        }
    }

    public void updateDescription(String stuNumber, String description){
        Date date = new Date();

        User stu = getByStuNumber(stuNumber);
        stu.setDescription(description);
        stu.setModifyTime(date);

        int i = stuMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("学生"+ stuNumber +"简介修改失败");
        }
    }

    public void updateNickname(String stuNumber, String nickname){
        Date date = new Date();

        User stu = getByStuNumber(stuNumber);
        stu.setNickname(nickname);
        stu.setModifyTime(date);

        int i = stuMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("学生"+ stuNumber +"昵称修改失败");
        }
    }

}
