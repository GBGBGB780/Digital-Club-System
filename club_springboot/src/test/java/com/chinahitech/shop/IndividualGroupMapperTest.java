package com.chinahitech.shop;

import com.chinahitech.shop.bean.IndividualGroup;
import com.chinahitech.shop.mapper.IndividualGroupMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)

public class IndividualGroupMapperTest {
    @Autowired
    IndividualGroupMapper individualGroupMapper;

    @Test
    public void getGroupByStuIdTest(){
        IndividualGroup individualGroup = new IndividualGroup();
        individualGroup.setUserId("1");
        List<IndividualGroup> re = individualGroupMapper.getGroupByStuId(individualGroup.getUserId());
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void getGroupByGroupIdTest(){
        IndividualGroup individualGroup = new IndividualGroup();
        individualGroup.setGroupId(1);
        List<IndividualGroup> re = individualGroupMapper.getGroupByGroupId(individualGroup.getGroupId());
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void getAllManagedGroupsTest(){
        IndividualGroup individualGroup = new IndividualGroup();
        individualGroup.setUserId("1");
        List<IndividualGroup> re = individualGroupMapper.getAllManagedGroups(individualGroup.getUserId());
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void getUserByUserIdAndGroupIdTest(){
        IndividualGroup individualGroup = new IndividualGroup();
        individualGroup.setUserId("1");
        individualGroup.setGroupId(1);
        IndividualGroup re = individualGroupMapper.getUserByUserIdAndGroupId(individualGroup.getUserId(),individualGroup.getGroupId());
        System.out.println(re);
        System.out.println("测试通过");
    }

//    @Test
//    public void addGroupStudentTest(){
//        IndividualGroup individualGroup = new IndividualGroup();
//        individualGroup.setUserId("");
//        individualGroup.setGroupId(1);
//        individualGroup.setPosition("");
//        individualGroup.setUserName("");
//        individualGroup.setStatus(1);
//        individualGroup.setCreateTime(new Date());
//        individualGroup.setModifyTime(new Date());
//        int re = individualGroupMapper.addGroupStudent(
//                individualGroup.getUserId(),
//                individualGroup.getGroupId(),
//                individualGroup.getPosition(),
//                individualGroup.getUserName(),
//                individualGroup.getStatus(),
//                individualGroup.getCreateTime(),
//                individualGroup.getModifyTime()
//                );
//        System.out.println(re);
//        System.out.println("测试通过");
//    }

    @Test
    public void modifyGroupStudentTest(){
        IndividualGroup individualGroup = new IndividualGroup();
        individualGroup.setPosition("");
        individualGroup.setModifyTime(new Date());
        individualGroup.setGroupId(1);
        individualGroup.setUserId("");
        int re = individualGroupMapper.modifyGroupStudent(
                individualGroup.getPosition(),
                individualGroup.getModifyTime(),
                individualGroup.getGroupId(),
                individualGroup.getUserId()
        );
        System.out.println(re);
        System.out.println("测试通过");
    }

    @Test
    public void deleteGroupStudentTest(){
        IndividualGroup individualGroup = new IndividualGroup();
        individualGroup.setGroupId(1);
        individualGroup.setUserId("");
        int re = individualGroupMapper.deleteGroupStudent(
                individualGroup.getGroupId(),
                individualGroup.getUserId()
        );
        System.out.println(re);
        System.out.println("测试通过");
    }
}
