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

    @Test
    public void modifyInfoTest() {
        Activity activity = activityMapper.getActivityById(1);
        System.out.println(activity);
        System.out.println(activity.getId());
        activity.setArrange("6667");
        activity.setCreateTime(new Date());
        activity.setGroupName("aaa");
        Date date = new Date();
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
                activity.getGroupName(),
                date,
                activity.getId());
        System.out.println(a);
        System.out.println("测试通过");
    }
}

