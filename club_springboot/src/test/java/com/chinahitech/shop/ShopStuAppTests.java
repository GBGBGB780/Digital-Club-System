package com.chinahitech.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.mapper.GroupMapper;
import java.util.List;

@SpringBootTest
class ShopStuAppTests {

    @Autowired
    GroupMapper groupMapper;

    @Test
    void findGroup() {
        String groupName = "足球社"; // Set the name of the group to search for
        Group group = groupMapper.getByName(groupName);
        Assertions.assertNotNull(group);
//        Assertions.assertEquals(groupName, group.getName());

        // 输出Group对象的属性
        System.out.println(group);
    }
}