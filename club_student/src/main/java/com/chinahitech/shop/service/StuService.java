package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.Students;
import com.chinahitech.shop.mapper.StuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class StuService {
    @Autowired
    private StuMapper stuMapper;

    public Students getByStunumber(String num) {
        return stuMapper.getByNum(num);
    }

    public void addStudent(String stunumber, String password, String email) {
        stuMapper.addStudent(stunumber, password, email);
    }

    public void updatePassword(String stunumber, String password){
        stuMapper.updatePassword(stunumber, password);
    }

    public void updatePhone(String stunumber, String phone){
        stuMapper.updatePhone(stunumber, phone);
    }

    public void updateDescription(String stunumber, String description){
        stuMapper.updateDescription(stunumber, description);
    }

    public void updateNickname(String stunumber, String nickname){
        stuMapper.updateNickname(stunumber, nickname);
    }

}
