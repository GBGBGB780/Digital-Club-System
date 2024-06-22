package com.chinahitech.shop;

import com.chinahitech.shop.mapper.IndividualGroupMapper;
import com.chinahitech.shop.mapper.ManagerMapper;
import com.chinahitech.shop.mapper.StuMapper;
import com.chinahitech.shop.service.IndividualGroupService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.mapper.GroupMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
public class IndividualGroupServiceTest {

    @Autowired
    private IndividualGroupService individualGroupService;

    @Test
    public void getAllManagedGroupsTest() {
        String managerId = "21311366";
        List<Group> groupList = individualGroupService.getAllManagedGroups(managerId);
        System.out.println(groupList);
    }
}