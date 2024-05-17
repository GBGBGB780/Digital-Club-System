package com.chinahitech.shop;


import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.mapper.StuMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StuMapperTest {

    @Autowired
    StuMapper stuMapper;

    @Test
    public void addStudentTest() {
        User user = new User();
        user.setUserId("12345679");
        user.setPassword("666666");
        user.setEmail("15888888888");
        user.setSalt("abc");
        Date date = new Date();
        int a = stuMapper.addStudent(user.getUserId(), user.getPassword(),
                user.getEmail(), user.getSalt(), date, date, 0);
        System.out.println(a);
        User user1 = stuMapper.getByNum(user.getUserId());
        System.out.println(user1);
        System.out.println("测试通过");
    }
}
