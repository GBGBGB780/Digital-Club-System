package com.chinahitech.shop.controller;


import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.bean.IndividualGroup;
import com.chinahitech.shop.service.IndividualGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chinahitech.shop.utils.Result;

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
    @RequestMapping("/getAllStudents")
    public Result getStudentsByGroup(int groupId) {
        List<IndividualGroup> studentList = individualGroupService.getGroupByGroupId(groupId);
        System.out.println(studentList);
        return Result.ok().data("items", studentList);
    }

    //该社团普通成员的增加
    @RequestMapping("/addGroupStudent")
    public Result addGroupStudent(int groupId, String studentId, String position) {
        individualGroupService.addGroupStudent(groupId, studentId, position);
        return Result.ok().message("社团"+ groupId +"添加学生"+ studentId +"成功");
    }

    //该社团普通成员的修改
    @RequestMapping("/modifyGroupStudent")
    public Result modifyGroupStudent(int groupId, String studentId, String position) {
        individualGroupService.modifyGroupStudent(groupId, studentId, position);
        return Result.ok().message("社团"+ groupId +"修改学生"+ studentId +"的信息成功");
    }

    //该社团普通成员的删除
    @RequestMapping("/deleteGroupStudent")
    public Result deleteGroupStudent(int groupId, String studentId) {
        individualGroupService.deleteGroupStudent(groupId, studentId);
        return Result.ok().message("社团"+ groupId +"删除学生"+ studentId +"成功");
    }

    //获取该管理员管理的所有社团
    @RequestMapping("/allManagedGroups")
    public Result getAllManagedGroups(String managerId) {
        List<Group> groupList = individualGroupService.getAllManagedGroups(managerId);
        System.out.println(groupList);
        return Result.ok().data("items", groupList);
    }

    //超级管理员端

    //修改权限
    @RequestMapping("/updatePermission")
    public Result updatePermission(int groupId, String studentId, int status) {
        individualGroupService.updatePermission(groupId, studentId, status);
        return Result.ok().message("社团"+ groupId +"修改用户"+ studentId +"的权限成功");
    }
}
