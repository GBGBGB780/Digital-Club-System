package com.chinahitech.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.mapper.GroupMapper;
import java.util.List;

@SpringBootTest
class ShopApplicationTests {

    @Autowired
    GroupMapper groupMapper;

    @Test
    void findGroup() {
        String groupName = "足球社"; // Set the name of the group to search for
        List<Group> groups = groupMapper.findGroup(groupName);
        Assertions.assertNotNull(groups);
        Assertions.assertFalse(groups.isEmpty());
        Group group = groups.get(0);
        Assertions.assertEquals(groupName, group.getName());

        // 输出Group对象的属性
        System.out.println("Group ID: " + group.getId());
        System.out.println("Group Name: " + group.getName());
        System.out.println("Group Description: " + group.getDescription());
        System.out.println("Group Attachment: " + group.getAttachment());
        System.out.println("Group Password: " + group.getPassword());
    }   
}