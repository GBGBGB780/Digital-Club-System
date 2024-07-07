package com.chinahitech.shop.controller;


import com.chinahitech.shop.aop.RepeatLimit;
import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.bean.IndividualGroup;
import com.chinahitech.shop.bean.notAddedToDatabase.GroupNum;
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
    @RepeatLimit
    @RequestMapping("/allGroups")
    public Result getIndividualGroup(String studentId) {
        List<IndividualGroup> individualGroupList = individualGroupService.getGroupByStuId(studentId);
//        System.out.println(individualGroupList);
        return Result.ok().data("items", individualGroupList);
    }

    //管理员端
    //获取该社团的所有学生及其职位
    @RepeatLimit
    @RequestMapping("/getStudentsByGroup")
    public Result getStudentsByGroup(int groupId, String searchInfo) {
        List<IndividualGroup> studentList = individualGroupService.getStudentsByGroup(groupId, searchInfo);
//        System.out.println(studentList);
        return Result.ok().data("items", studentList);
    }

    //该社团普通成员的增加
    @RepeatLimit
    @RequestMapping("/addGroupStudent")
    public Result addGroupStudent(int groupId, String studentId, String position) {
        individualGroupService.addGroupStudent(groupId, studentId, position);
        return Result.ok().message("社团"+ groupId +"添加学生"+ studentId +"成功");
    }

    //该社团普通成员的修改
    @RepeatLimit
    @RequestMapping("/modifyGroupStudent")
    public Result modifyGroupStudent(int groupId, String studentId, String position) {
        individualGroupService.modifyGroupStudent(groupId, studentId, position);
        return Result.ok().message("社团"+ groupId +"修改学生"+ studentId +"的信息成功");
    }

    //该社团普通成员的删除
    @RepeatLimit
    @RequestMapping("/deleteGroupStudent")
    public Result deleteGroupStudent(int groupId, String studentId) {
        individualGroupService.deleteGroupStudent(groupId, studentId);
        return Result.ok().message("社团"+ groupId +"删除学生"+ studentId +"成功");
    }

    //获取该管理员管理的所有社团
    @RepeatLimit
    @RequestMapping("/allManagedGroups")
    public Result getAllManagedGroups(String managerId) {
//        System.out.println(managerId);
        List<Group> groupList = individualGroupService.getAllManagedGroups(managerId);
//        System.out.println(groupList);
        return Result.ok().data("items", groupList);
    }

    //该社团的管理权限转让
    @RepeatLimit
    @RequestMapping("/transferStatus")
    public Result transferStatus(int groupId, String managerId, String userId) {
        individualGroupService.transferStatus(groupId, managerId, userId);
        return Result.ok().message("社团"+ groupId +"管理权限转让给学生"+ userId +"成功");
    }

    //超级管理员端

    //修改权限（需要事先获取所需修改的用户id，所在社团id以及在该社团的权限）
    @RepeatLimit
    @RequestMapping("/updatePermission")
    public Result updatePermission(int groupId, String studentId, int status) {
        individualGroupService.updatePermission(groupId, studentId, status);
        return Result.ok().message("社团"+ groupId +"修改用户"+ studentId +"的权限成功");
    }

    //获取所有学生信息（根据社团来排序）
    @RepeatLimit
    @RequestMapping("/getAllStudents")
    public Result getAllStudents(String searchInfo) {
        List<IndividualGroup> studentList = individualGroupService.getAllStudents(searchInfo);
//        System.out.println(studentList);
        return Result.ok().data("items", studentList);
    }

    //获取人数前五多的社团查询同时获取社团热度
    @RepeatLimit
    @RequestMapping("/getGroupMembers")
    public Result getGroupMembers() {
        List<GroupNum> groupList = individualGroupService.getGroupMembers();
//        System.out.println(groupList);
        return Result.ok().data("items", groupList);
    }
}
