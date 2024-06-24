package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.mapper.ManagerMapper;
import com.chinahitech.shop.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ManagerService {
    @Autowired
    private ManagerMapper managerMapper;
    private Md5 md5 = new Md5();

    public User getByUserId(String num) {
        User stu = managerMapper.getByNum(num);
//        if (stu == null) {
//            throw new EntityNotFoundException("管理员"+ num +"不存在");
//        }
        return stu;
    }

    public User login(String num, String pwd) {
        User user = managerMapper.getByNum(num);
        if (user == null){
            throw new EntityNotFoundException("管理员"+ num +"不存在");
        }
        String oldPwd = user.getPassword();
        //获取盐值
        String salt = user.getSalt();
//        System.out.println(salt);
        //获取用户输入的密码对应的加密
//        String newPwd = MD5handler(pwd,salt);
        if (!md5.isEqual(oldPwd,pwd,salt)){
//            System.out.println(oldPwd);
//            System.out.println(pwd);
            user.setPassword(null);
//            throw new PwdNotMatchException("密码错误");
//        } else if (user.getStatus() < 1) { //判断用户权限级别
//            user.setPassword(null);
        }
        return user;
    }

    //  md5加密
    public void addManager(String userId, String lastPwd, String email) {
        Date date = new Date();
        //密码加密(MD5算法)
//            System.out.println(lastPwd);
        String salt = UUID.randomUUID().toString().toUpperCase();
//            System.out.println(salt);
        String currPwd = md5.MD5handler(lastPwd, salt);

        int i = managerMapper.addManager(userId, currPwd, email, salt, date, date, 1);
        if(i != 1){
            throw new InsertException("管理员"+ userId +"添加失败");
        }
    }

    //  md5加密
    public void updatePassword(String userId, String password){
        User stu = managerMapper.getByNum(userId);
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

        int i = managerMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("管理员"+ userId +"密码修改失败");
        }
    }

    public void updatePhone(String userId, String phone){
        Date date = new Date();

        User stu = getByUserId(userId);
        stu.setPhone(phone);
        stu.setModifyTime(date);

        int i = managerMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("管理员"+ userId +"电话修改失败");
        }
    }

    public void updateDescription(String userId, String description){
        Date date = new Date();

        User stu = getByUserId(userId);
        stu.setDescription(description);
        stu.setModifyTime(date);

        int i = managerMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("管理员"+ userId +"简介修改失败");
        }
    }

    public void updateNickname(String userId, String nickname){
        Date date = new Date();

        User stu = getByUserId(userId);
        stu.setNickname(nickname);
        stu.setModifyTime(date);

        int i = managerMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("管理员"+ userId +"昵称修改失败");
        }
    }

}

