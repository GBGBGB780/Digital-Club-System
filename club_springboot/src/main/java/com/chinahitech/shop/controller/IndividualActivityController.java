package com.chinahitech.shop.controller;


import com.chinahitech.shop.bean.Activity;
import com.chinahitech.shop.bean.IndividualActivity;
import com.chinahitech.shop.service.IndividualActivityService;
import com.chinahitech.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    //申请加入活动
    @PostMapping("/joinActivity")
    public Result joinActivity(int activityId, String studentId){
        individualActivityService.joinActivity(studentId, activityId);
        return Result.ok();
    }

    //管理员端
    //获取该活动的所有学生及其职位
    @RequestMapping("/getAllStudents")
    public Result getStudentsByActivity(int activityId) {
        List<IndividualActivity> individualActivityList = individualActivityService.getActivityByActivityId(activityId);
        System.out.println(individualActivityList);
        return Result.ok().data("items", individualActivityList);
    }

    //获取该活动所有的学生申请
    @RequestMapping("/getAllApplyStudents")
    public Result getAllApplyStudents(int activityId) {
        List<IndividualActivity> applyList = individualActivityService.getApplyByActivityId(activityId);
        System.out.println(applyList);
        return Result.ok().data("items", applyList);
    }

    //该活动普通成员的增加
    @RequestMapping("/addActivityStudent")
    public Result addGroupStudent(int activityId, String studentId, String position) {
        individualActivityService.addActivityStudent(studentId, activityId, position, true);
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

    //接受申请
    @PostMapping("/accept")
    public Result acceptApplication(int activityId, String studentId){
        System.out.println(activityId);
        // System.out.println(isaccepted);
        individualActivityService.confirmApplication(activityId, studentId);
        return Result.ok();
    }

    //拒绝申请
    @PostMapping("/reject")
    public Result rejectApplication(int activityId, String studentId){
        System.out.println(activityId);
        // System.out.println(isaccepted);
        individualActivityService.denyApplication(activityId, studentId);
        return Result.ok();
    }

    //超级管理员端
}