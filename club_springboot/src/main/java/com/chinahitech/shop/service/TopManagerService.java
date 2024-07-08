package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.exception.*;
import com.chinahitech.shop.mapper.TopManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TopManagerService {
    @Autowired
    private TopManagerMapper topManagerMapper;
    private Md5 md5 = new Md5();

    private final String XLSX = ".xlsx";
    private final String XLS=".xls";

    public User getByUserId(String num) {
        User stu = topManagerMapper.getTopManagerByNum(num);
        if (stu == null) {
            throw new EntityNotFoundException("超级管理员"+ num +"不存在");
        }
        return stu;
    }

    public User login(String num, String pwd) {
        User user = topManagerMapper.getTopManagerByNum(num);
        if (user == null){
            throw new EntityNotFoundException("超级管理员"+ num +"不存在");
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

        User user = topManagerMapper.getTopManagerByNum(userId);
        if (user != null) {
            throw new UseridDuplicateException("超级管理员"+ userId +"已存在");
        } else {
            int i = topManagerMapper.addManager(userId, currPwd, email, salt, date, date, 10);
            if(i != 1){
                throw new InsertException("超级管理员"+ userId +"添加失败");
            }
        }
    }

    //  md5加密
    public void updatePassword(String userId, String password){
        User stu = getByUserId(userId);
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

        int i = topManagerMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("超级管理员"+ userId +"密码修改失败");
        }
    }

    public void updatePhone(String userId, String phone){
        Date date = new Date();

        User stu = getByUserId(userId);
        stu.setPhone(phone);
        stu.setModifyTime(date);

        int i = topManagerMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("超级管理员"+ userId +"电话修改失败");
        }
    }

    public void updateDescription(String userId, String description){
        Date date = new Date();

        User stu = getByUserId(userId);
        stu.setDescription(description);
        stu.setModifyTime(date);

        int i = topManagerMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("超级管理员"+ userId +"简介修改失败");
        }
    }

    public void updateNickname(String userId, String nickname){
        Date date = new Date();

        User stu = getByUserId(userId);
        stu.setNickname(nickname);
        stu.setModifyTime(date);

        int i = topManagerMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("超级管理员"+ userId +"昵称修改失败");
        }
    }

    public void updateMajor(String userId, String campus, String school, String major){
        Date date = new Date();

        User stu = getByUserId(userId);
        stu.setCampus(campus);
        stu.setSchool(school);
        stu.setMajor(major);
        stu.setModifyTime(date);

        int i = topManagerMapper.updateById(stu);
        if(i != 1){
            throw new UpdateException("超级管理员"+ userId +"专业信息修改失败");
        }
    }

    public List<User> getAllUsers(String searchInfo) {
        if (searchInfo == null || searchInfo.trim().isEmpty()) {
            return topManagerMapper.getAllUsers();
        } else {
            return topManagerMapper.getUser(searchInfo);
        }
    }

    //  md5加密
    public void addUser(String userId, String lastPwd, String email) {
        Date date = new Date();
        //密码加密(MD5算法)
//            System.out.println(lastPwd);
        String salt = UUID.randomUUID().toString().toUpperCase();
//            System.out.println(salt);
        String currPwd = md5.MD5handler(lastPwd, salt);
        User user = new User();
        user.setUserId(userId);
        user.setPassword(currPwd);
        user.setSalt(salt);
        user.setEmail(email);
        user.setCreateTime(date);
        user.setModifyTime(date);
        user.setStatus(0);

        int i = topManagerMapper.insert(user);
        if(i != 1){
            throw new InsertException("用户"+ userId +"添加失败");
        }
    }

    public void updateUserInfo(User user){
        Date date = new Date();

        user.setModifyTime(date);

        int i = topManagerMapper.updateById(user);
        if(i != 1){
            throw new UpdateException("用户"+ user.getUserId() +"信息修改失败");
        }
    }

    public void deleteUser(User user) {
        String id = user.getUserId();
        int i = topManagerMapper.deleteById(id);
        if(i != 1){
            throw new UpdateException("用户"+ user.getUserName() +"删除失败");
        }
    }

    public void uploadExcel(String fileUrl) {
        String fileType  = fileUrl.substring(fileUrl.indexOf("."));
//        System.out.println(fileType);
        if(fileType.equals(XLSX) || fileType.equals(XLS)){
            List<User> users;
            try {
                ScanExcel scanExcel = new ScanExcel();
                //启动
                users = scanExcel.readExcel(fileUrl);
            } catch (Exception e) {
                throw new FileUploadException("Excel文件处理出错！");
            }

            if (users != null && !users.isEmpty()){
                for (User user : users) {
                    Date date = new Date();
                    //密码加密(MD5算法)
//            System.out.println(lastPwd);
                    String salt = UUID.randomUUID().toString().toUpperCase();
//            System.out.println(salt);
                    String currPwd = md5.MD5handler(user.getPassword(), salt);
                    user.setPassword(currPwd);
                    user.setSalt(salt);
                    user.setCreateTime(date);
                    user.setModifyTime(date);
                    user.setStatus(0);
                    User tempUser = topManagerMapper.getUserByNum(user.getUserId());
                    if (tempUser != null) {
                        throw new UseridDuplicateException("用户" + user.getUserId() + "已存在");
                    }
                    int i = topManagerMapper.insert(user);
                    if(i != 1){
                        throw new InsertException("用户"+ user.getUserId() +"添加失败");
                    }
                }
            }
        } else {
            throw new FileTypeException("文件类型错误！");
        }
    }
}

