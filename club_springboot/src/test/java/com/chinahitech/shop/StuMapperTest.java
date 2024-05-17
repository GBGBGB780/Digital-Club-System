package com.chinahitech.shop;


import com.chinahitech.shop.bean.Students;
import com.chinahitech.shop.mapper.StuMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class StuMapperTest {

    @Autowired
    StuMapper stuMapper;

    @Test
    public void addStudentTest() {
        Students students = new Students();
        students.setStunumber("12345678");
        students.setPassword("666666");
        students.setEmail("15888888888");
        students.setSalt("abc");
        Date date = new Date();
        int a = stuMapper.addStudent(students.getStunumber(), students.getPassword(),
                students.getEmail(), students.getSalt(), date, date);
        System.out.println(a);
        Students students1 = stuMapper.getByNum(students.getStunumber());
        System.out.println(students1);
        System.out.println("测试通过");
    }
}
