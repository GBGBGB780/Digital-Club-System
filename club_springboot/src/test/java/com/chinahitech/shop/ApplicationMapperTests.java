package com.chinahitech.shop;

import com.chinahitech.shop.bean.StuApp;
import com.chinahitech.shop.mapper.StuAppMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class ApplicationMapperTests {

    @Autowired
    StuAppMapper stuAppMapper;

    @Test
    public void findallTest(){
        stuAppMapper.findall();
        System.out.println("测试通过");
    }

    @Test
    public void findMyappTest(){
        StuApp application = new StuApp();
        application.setStunumber("21311272");
        List<StuApp> a = stuAppMapper.findMyapp(application.getStunumber());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void findRecvappTest(){
        StuApp application = new StuApp();
        application.setGroupname("篮球社");
        List<StuApp> a = stuAppMapper.findRecvapp(application.getGroupname());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void findDetailappTest(){
        StuApp application = new StuApp();
        application.setApplicationid(1);
        List<StuApp> a = stuAppMapper.findDetailapp(application.getApplicationid());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void findIsAcceptedTest(){
        StuApp application = new StuApp();
        application.setApplicationid(1);
        String a = stuAppMapper.findIsAccepted(application.getApplicationid());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void updateAttachmentTest(){
        StuApp application = new StuApp();
        application.setApplicationid(1);
        application.setAttachment("附件5");
        int a = stuAppMapper.updateAttachment(application.getApplicationid(),application.getAttachment());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void confirmApplicationByidTest(){
        StuApp application = new StuApp();
        application.setApplicationid(6);
        int a = stuAppMapper.confirmApplicationByid(application.getApplicationid());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void denyApplicationByidTest(){
        StuApp application = new StuApp();
        application.setApplicationid(6);
        int a = stuAppMapper.denyApplicationByid(application.getApplicationid());
        System.out.println(a);
        System.out.println("测试通过");
    }
}