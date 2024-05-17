package com.chinahitech.shop.controller;


import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.bean.IndividualGroup;
import com.chinahitech.shop.service.GroupService;
import com.chinahitech.shop.service.IndividualGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.chinahitech.shop.utils.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/individualGroup")
@CrossOrigin
public class IndividualGroupController {
    @Autowired
    private IndividualGroupService individualGroupService;

    // 学生端
    //获取该学生参加的所有社团及其在对应社团的职位
    @RequestMapping("/allGroups")
    public Result getIndividualGroup(String studentId) {
        List<IndividualGroup> individualGroupList = individualGroupService.getGroupByStuId(studentId);
        System.out.println(individualGroupList);
        return Result.ok().data("items", individualGroupList);
    }

    //管理员端
    //获取该社团的所有学生及其职位
    @RequestMapping("/allStudents")
    public Result getStudentsByGroup(String groupId) {
        List<IndividualGroup> studentList = individualGroupService.getGroupByGroupId(groupId);
        System.out.println(studentList);
        return Result.ok().data("items", studentList);
    }
}
