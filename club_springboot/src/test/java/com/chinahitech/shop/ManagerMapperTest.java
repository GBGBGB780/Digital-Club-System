package com.chinahitech.shop;

import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.mapper.ManagerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ManagerMapperTest {

    @Autowired
    ManagerMapper managerMapper;

    @Test
    public void getByNumTest(){
       User user = new User();
       user.setUserId("12345678");
       User re = managerMapper.getByNum(user.getUserId());
       System.out.println(re);
       System.out.println("测试通过");
    }

    @Test
    public void addManagerTest(){
        User user = new User();
        user.setUserId("21311610");
        user.setPassword("GBGBGB780");
        user.setEmail("Tsekp@mail2.sysu.edu.cn");
        user.setSalt("AOSHDAIDN");
        user.setCreateTime(new Date());
        user.setModifyTime(new Date());
        user.setStatus(1);
        int re = managerMapper.addManager(
                user.getUserId(),
                user.getPassword(),
                user.getEmail(),
                user.getSalt(),
                user.getCreateTime(),
                user.getModifyTime(),
                user.getStatus()
        );
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void updatePasswordTest(){
        User user = new User();
        user.setUserId("21311610");
        user.setPassword("BGBGBG870");
        user.setModifyTime(new Date());
        int re = managerMapper.updatePassword(user.getUserId(),user.getPassword(),user.getModifyTime());
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void updatePhoneTest(){
        User user = new User();
        user.setUserId("21311610");
        user.setPhone("13414388388");
        user.setModifyTime(new Date());
        int re = managerMapper.updatePhone(user.getUserId(),user.getPhone(),user.getModifyTime());
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void updateDescriptionTest(){
        User user = new User();
        user.setUserId("21311610");
        user.setDescription("我是GB最牛逼");
        user.setModifyTime(new Date());
        int re = managerMapper.updateDescription(user.getUserId(),user.getDescription(),user.getModifyTime());
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void updateNicknameTest(){
        User user = new User();
        user.setUserId("21311610");
        user.setNickname("GB");
        user.setModifyTime(new Date());
        int re = managerMapper.updateNickname(user.getUserId(),user.getNickname(),user.getModifyTime());
        System.out.println(re);
        System.out.println("测试通过");
    }
}
