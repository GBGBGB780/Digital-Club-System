package com.chinahitech.shop;

import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.mapper.GroupMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GroupMapperTest {
    @Autowired
    GroupMapper groupMapper;

    @Test
    public void getByNameTest(){
        Group group = new Group();
        group.setName("足球社");
        Group re = groupMapper.getByName(group.getName());
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void findallTest(){
        Group group = new Group();
        List<Group> re = groupMapper.findall();
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void findBySearchTest(){
        Group group = new Group();
        List<Group> re = groupMapper.findBySearch("唱");
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void findGroupTest(){
        Group group = new Group();
        group.setName("足球社");
        List<Group> re = groupMapper.findBySearch(group.getName());
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void getGroupByIdTest(){
        Group group = new Group();
        group.setId(1);
        Group re = groupMapper.getGroupById(group.getId());
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void updateAttachmentTest(){
        Group group = new Group();
        group.setName("足球社");
        group.setAttachment("");
        group.setModifyTime(new Date());
        int re = groupMapper.updateAttachment(group.getName(),group.getAttachment(),group.getModifyTime());
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void updateImageTest(){
        Group group = new Group();
        group.setName("足球社");
        group.setImage("");
        group.setModifyTime(new Date());
        int re = groupMapper.updateImage(group.getName(),group.getImage(),group.getModifyTime());
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void updateDescriptionByNameTest(){
        Group group = new Group();
        group.setName("足球社");
        group.setDescription("");
        group.setAttachment("");
        group.setImage("");
        group.setModifyTime(new Date());
        int re = groupMapper.updateDescriptionByName(group.getName(),group.getDescription(),group.getAttachment(),group.getImage(),group.getModifyTime());
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void getHotTest(){
        Group group = new Group();
        group.setName("足球社");
        Group re = groupMapper.getHot(group.getName());
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void updateHotTest(){
        Group group = new Group();
        group.setName("足球社");
        group.setHot(3);
        group.setModifyTime(new Date());
        int re = groupMapper.updateHot(group.getName(),group.getHot(),group.getModifyTime());
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void findtopTest(){
        Group group = new Group();
        List<Group> re = groupMapper.findtop();
        System.out.println(re);
        System.out.println("测试通过");
    }
}
