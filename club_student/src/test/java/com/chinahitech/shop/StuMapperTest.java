package com.chinahitech.shop;


import com.chinahitech.shop.bean.Students;
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
        Students students = new Students();
        students.setStunumber("21311377");
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

    @Test
    public void updateStudentTest() {
        Students students = new Students();
        students.setStunumber("21311377");
        students.setPassword("666666");
        Date date = new Date();

        int a = stuMapper.updatePassword(students.getStunumber(),students.getPassword(), date);
        System.out.println(a);

        Students students1 = stuMapper.getByNum(students.getStunumber());
        System.out.println(students1);
        System.out.println("测试通过");
    }

    @Test
    public void updatePhoneTest() {
        Students students = new Students();
        students.setStunumber("21311377");
        students.setPhone("13333333333");
        Date date = new Date();

        int a = stuMapper.updatePhone(students.getStunumber(),students.getPhone(), date);
        System.out.println(a);

        Students students1 = stuMapper.getByNum(students.getStunumber());
        System.out.println(students1);
        System.out.println("测试通过");
    }

    @Test
    public void updateDescriptionTest() {
        Students students = new Students();
        students.setStunumber("21311377");
        students.setDescription("hhh");
        Date date = new Date();

        int a = stuMapper.updateDescription(students.getStunumber(),students.getDescription(), date);
        System.out.println(a);

        Students students1 = stuMapper.getByNum(students.getStunumber());
        System.out.println(students1);
        System.out.println("测试通过");
    }

    @Test
    public void updateNicknameTest() {
        Students students = new Students();
        students.setStunumber("21311377");
        students.setNickname("GB");
        Date date = new Date();

        int a = stuMapper.updateNickname(students.getStunumber(),students.getNickname(), date);
        System.out.println(a);

        Students students1 = stuMapper.getByNum(students.getStunumber());
        System.out.println(students1);
        System.out.println("测试通过");
    }
}
