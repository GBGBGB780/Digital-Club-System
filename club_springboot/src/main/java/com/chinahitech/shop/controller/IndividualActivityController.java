package com.chinahitech.shop.controller;


import com.chinahitech.shop.bean.Activity;
import com.chinahitech.shop.bean.IndividualActivity;
import com.chinahitech.shop.service.IndividualActivityService;
import com.chinahitech.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/individualActivity")
@CrossOrigin
public class IndividualActivityController {
    @Autowired
    private IndividualActivityService individualActivityService;

    // 学生端
    //获取该学生参加的所有活动及其在对应活动的职位
    @RequestMapping("/allActivities")
    public Result getIndividualActivity(String studentId) {
        List<IndividualActivity> individualActivityList = individualActivityService.getActivityByStuId(studentId);
        System.out.println(individualActivityList);
        return Result.ok().data("items", individualActivityList);
    }

    //管理员端
    //获取该活动的所有学生及其职位
    @RequestMapping("/getAllStudents")
    public Result getStudentsByActivity(int activityId) {
        List<IndividualActivity> studentList = individualActivityService.getActivityByActivityId(activityId);
        System.out.println(studentList);
        return Result.ok().data("items", studentList);
    }

    //该活动普通成员的增加
    @RequestMapping("/addActivityStudent")
    public Result addGroupStudent(int activityId, String studentId, String position) {
        individualActivityService.addActivityStudent(activityId, studentId, position);
        return Result.ok().message("活动"+ activityId +"添加学生"+ studentId +"成功");
    }

    //该活动普通成员的修改
    @RequestMapping("/modifyGroupStudent")
    public Result modifyGroupStudent(int activityId, String studentId, String position) {
        individualActivityService.modifyActivityStudent(activityId, studentId, position);
        return Result.ok().message("活动"+ activityId +"修改学生"+ studentId +"的信息成功");
    }

    //该活动普通成员的删除
    @RequestMapping("/deleteActivityStudent")
    public Result deleteActivityStudent(int activityId, String studentId) {
        individualActivityService.deleteActivityStudent(activityId, studentId);
        return Result.ok().message("活动"+ activityId +"删除学生"+ studentId +"成功");
    }

    //获取该管理员管理的所有活动
    @RequestMapping("/allManagedActivities")
    public Result getAllManagedActivities(String managerId) {
        List<Activity> activityList = individualActivityService.getAllManagedActivities(managerId);
        System.out.println(activityList);
        return Result.ok().data("items", activityList);
    }

    //超级管理员端
}
