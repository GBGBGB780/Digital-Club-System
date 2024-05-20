package com.chinahitech.shop;

import com.chinahitech.shop.bean.Application;
import com.chinahitech.shop.mapper.ApplicationMapper;
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
    ApplicationMapper applicationMapper;

    @Test
    public void findallTest(){
        applicationMapper.findall();
        System.out.println("测试通过");
    }

    @Test
    public void findMyappTest(){
        Application application = new Application();
        application.setStunumber("21311272");
        List<Application> a = applicationMapper.findMyapp(application.getStunumber());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void findRecvappTest(){
        Application application = new Application();
        application.setGroupname("篮球社");
        List<Application> a = applicationMapper.findRecvapp(application.getGroupname());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void findDetailappTest(){
        Application application = new Application();
        application.setApplicationid(1);
        List<Application> a = applicationMapper.findDetailapp(application.getApplicationid());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void findIsAcceptedTest(){
        Application application = new Application();
        application.setApplicationid(1);
        String a = applicationMapper.findIsAccepted(application.getApplicationid());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void updateAttachmentTest(){
        Application application = new Application();
        application.setApplicationid(1);
        application.setAttachment("附件5");
        int a = applicationMapper.updateAttachment(application.getApplicationid(),application.getAttachment());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void confirmApplicationByidTest(){
        Application application = new Application();
        application.setApplicationid(6);
        int a = applicationMapper.confirmApplicationByid(application.getApplicationid());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void denyApplicationByidTest(){
        Application application = new Application();
        application.setApplicationid(6);
        int a = applicationMapper.denyApplicationByid(application.getApplicationid());
        System.out.println(a);
        System.out.println("测试通过");
    }
}