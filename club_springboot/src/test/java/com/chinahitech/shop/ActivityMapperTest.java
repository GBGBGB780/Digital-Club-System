package com.chinahitech.shop;


import com.chinahitech.shop.bean.Activity;
import com.chinahitech.shop.mapper.ActivityMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivityMapperTest {

    @Autowired
    ActivityMapper activityMapper;

//    @Test
//    public void modifyInfoTest() {
//        Activity activity = new Activity();
//        System.out.println(activity);
//        System.out.println(activity.getId());
//        activity.setArrange("6667");
//        activity.setCreateTime(new Date());
//        activity.setGroupName("aaa");
//        Date date = new Date();
//        int a = activityMapper.modifyInfo(
//                activity.getName(),
//                activity.getOrganizer(),
//                activity.getDescription(),
//                activity.getAttachment(),
//                activity.getImage(),
//                activity.getArrange(),
//                activity.getTime(),
//                activity.getNumber(),
//                activity.getPlace(),
//                activity.getType(),
//                date,
//                activity.getId());
//        System.out.println(a);
//        System.out.println("测试通过");
//    }

    @Test
    public void getByNameTest() {
        Activity activity = new Activity();
        activity.setName("a");
        Activity a = activityMapper.getByName(activity.getName());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void findallTest() {
        activityMapper.findall();
        System.out.println("测试通过");
    }

    @Test
    public void findBySearchTest() {
        activityMapper.findBySearch("a");
        System.out.println("测试通过");
    }

    @Test
    public void findActivityTest() {
        Activity activity = new Activity();
        activity.setName("a");
        activityMapper.findActivity(activity.getName());
        System.out.println("测试通过");
    }

    @Test
    public void getActivityByIdTest() {
        Activity activity = new Activity();
        activity.setId(1);
        Activity a = activityMapper.getActivityById(activity.getId());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void getActivityByNameAndGroupNameTest() {
        Activity activity = new Activity();
        activity.setName("a");
        activity.setGroupName("aaa");
        Activity a = activityMapper.getActivityByNameAndGroupName(activity.getName(),activity.getGroupName());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void getActivityByGroupNameTest() {
        Activity activity = new Activity();
        activity.setGroupName("aaa");
        activityMapper.getActivityByGroupName(activity.getGroupName());
        System.out.println("测试通过");
    }

    @Test
    public void updateAttachmentTest() {
        Activity activity = new Activity();
        activity.setName("a");
        activity.setAttachment("111");
        activity.setGroupName("aaa");
        Date date = new Date();
        int a = activityMapper.updateAttachment(activity.getName(),activity.getAttachment(),activity.getGroupName(),date);
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void updateImageTest() {
        Activity activity = new Activity();
        activity.setName("a");
        activity.setImage("111");
        activity.setGroupName("aaa");
        Date date = new Date();
        int a = activityMapper.updateImage(activity.getName(),activity.getImage(),activity.getGroupName(),date);
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void updateDescriptionByNameTest() {
        Activity activity = new Activity();
        activity.setName("a");
        activity.setDescription("111");
        activity.setAttachment("111");
        activity.setImage("111");
        activity.setGroupName("aaa");
        Date date = new Date();
        int a = activityMapper.updateDescriptionByName(
                activity.getName(),
                activity.getDescription(),
                activity.getAttachment(),
                activity.getImage(),
                activity.getGroupName(),
                date);
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void modifyInfoTest() {
        Activity activity = new Activity();
        activity.setName("a");
        activity.setOrganizer("b");
        activity.setDescription("111");
        activity.setAttachment("111");
        activity.setImage("111");
        activity.setArrange("6666");
        activity.setTime(new Date());
        activity.setNumber(1);
        activity.setPlace("111");
        activity.setType(1);
        Date date = new Date();
        activity.setId(1);
        int a = activityMapper.modifyInfo(
                activity.getName(),
                activity.getOrganizer(),
                activity.getDescription(),
                activity.getAttachment(),
                activity.getImage(),
                activity.getArrange(),
                activity.getTime(),
                activity.getNumber(),
                activity.getPlace(),
                activity.getType(),
                date,
                activity.getId());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void getHotTest() {
        Activity activity = new Activity();
        activity.setGroupName("aaa");
        activity.setName("a");
        Activity a = activityMapper.getHot(activity.getGroupName(),activity.getName());
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void updateHotTest() {
        Activity activity = new Activity();
        activity.setGroupName("aaa");
        activity.setName("a");
        activity.setHot(1);
        Date date = new Date();
        int a = activityMapper.updateHot(activity.getGroupName(),activity.getName(),activity.getHot(),date);
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void findtopTest() {
        activityMapper.findtop();
        System.out.println("测试通过");
    }

    @Test
    public void addActivityTest() {
        Activity activity = new Activity();
        activity.setId(2);
        activity.setName("aa");
        activity.setOrganizer("bb");
        activity.setDescription("1111");
        activity.setAttachment("1111");
        activity.setImage("1111");
        activity.setHot(1);
        activity.setArrange("66666");
        activity.setTime(new Date());
        activity.setNumber(1);
        activity.setPlace("111");
        activity.setType(1);
        activity.setGroupName("aaaa");
        activity.setCreateTime(new Date());
        Date date = new Date();

        int a = activityMapper.addActivity(
                activity.getId(),
                activity.getName(),
                activity.getOrganizer(),
                activity.getDescription(),
                activity.getAttachment(),
                activity.getImage(),
                activity.getHot(),
                activity.getArrange(),
                activity.getTime(),
                activity.getNumber(),
                activity.getPlace(),
                activity.getType(),
                activity.getGroupName(),
                activity.getCreateTime(),
                date
                );
        System.out.println(a);
        System.out.println("测试通过");
    }

    @Test
    public void deleteActivityTest() {
        Activity activity = new Activity();
        activity.setId(2);
        int a = activityMapper.deleteActivity(activity.getId());
        System.out.println(a);
        System.out.println("测试通过");
    }
}

