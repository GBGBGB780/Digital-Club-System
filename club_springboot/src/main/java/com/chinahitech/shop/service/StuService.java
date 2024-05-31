package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.mapper.StuMapper;
import com.chinahitech.shop.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class StuService {
    @Autowired
    private StuMapper stuMapper;
    private Md5 md5 = new Md5();

    public User getByStunumber(String num) {
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
    public void addStudent(String stunumber, String lastPwd, String email) {
        Date date = new Date();
        //密码加密(MD5算法)
//            System.out.println(lastPwd);
        String salt = UUID.randomUUID().toString().toUpperCase();
//            System.out.println(salt);
        String currPwd = md5.MD5handler(lastPwd, salt);

        int i = stuMapper.addStudent(stunumber, currPwd, email, salt, date, date, 0);
        if(i != 1){
            throw new InsertException("学生"+ stunumber +"添加失败");
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

    public void updatePhone(String stunumber, String phone){
        Date date = new Date();

        User stu = getByStunumber(stunumber);
        stu.setPhone(phone);
        stu.setModifyTime(date);

        int i = stuMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("学生"+ stunumber +"电话修改失败");
        }
    }

    public void updateDescription(String stunumber, String description){
        Date date = new Date();

        User stu = getByStunumber(stunumber);
        stu.setDescription(description);
        stu.setModifyTime(date);

        int i = stuMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("学生"+ stunumber +"简介修改失败");
        }
    }

    public void updateNickname(String stunumber, String nickname){
        Date date = new Date();

        User stu = getByStunumber(stunumber);
        stu.setNickname(nickname);
        stu.setModifyTime(date);

        int i = stuMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("学生"+ stunumber +"昵称修改失败");
        }
    }

}
