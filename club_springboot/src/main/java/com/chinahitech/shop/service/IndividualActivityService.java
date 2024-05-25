package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.Activity;
import com.chinahitech.shop.bean.IndividualActivity;
import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.mapper.*;
import com.chinahitech.shop.service.exception.AccessDeniedException;
import com.chinahitech.shop.service.exception.EntityNotFoundException;
import com.chinahitech.shop.service.exception.InsertException;
import com.chinahitech.shop.service.exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class IndividualActivityService {
    @Autowired
    private IndividualActivityMapper individualActivityMapper;
    @Autowired
    private StuMapper stuMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private ActivityMapper activityMapper;

    public List<IndividualActivity> getActivityByStuId(String userId) {
        User user = validateStuName(userId);
        return individualActivityMapper.getActivityByStuId(userId);
    }

    public List<IndividualActivity> getActivityByActivityId(int activityId) {
        Activity activity = validateActivity(activityId);
        return individualActivityMapper.getActivityByActivityId(activityId);
    }

    public List<IndividualActivity> getApplyByActivityId(int activityId) {
        Activity activity = validateActivity(activityId);
        return individualActivityMapper.getApplyByActivityId(activityId);
    }

    public List<Activity> getAllManagedActivities(String managerId) {
        //检测管理者名字是否一致
        User user = validateManagerName(managerId);

        List<IndividualActivity> individualActivityList = individualActivityMapper.getAllManagedActivities(managerId);
//        System.out.println(individualGroupList);
        List<Activity> activityList = new ArrayList<>();
        for (IndividualActivity individualActivity : individualActivityList) {
//            System.out.println(individualGroup.getGroupId());
            activityList.add(activityMapper.getActivityById(individualActivity.getActivityId()));
        }
        return activityList;
    }

    public IndividualActivity getUserByUserIdAndActivityId(String userId, int activityId) {
        User user = validateStuName(userId);
        Activity activity = validateActivity(activityId);
        return individualActivityMapper.getUserByUserIdAndActivityId(userId, activityId);
    }

    public void joinActivity(String stuId, int activityId) {
        User user = validateStu(stuId);
        Activity activity = validateActivity(activityId);

        addHot(activity);

        addActivityStudent(stuId, activityId, null, false);
    }

    public void addHot(Activity activity) {
        Date date = new Date();
        int hot = activity.getHot();
        hot++;
        String name = activity.getName();

        int i = activityMapper.updateHot(name, hot, date);
        if(i != 1){
            throw new UpdateException("活动"+ name +"热度修改失败");
        }
    }

    public void addActivityStudent(String userId, int activityId, String position, Boolean isaccepted) {
        User user = validateStu(userId);
        Activity activity = validateActivity(activityId);
        IndividualActivity test = individualActivityMapper.getUserByUserIdAndActivityId(userId, activityId);
        if (test != null) {
            throw new InsertException("用户"+ userId +"在活动"+ activityId +"中已存在，无法重复加入");
        }
        IndividualActivity individualActivity  = new IndividualActivity();
        //初始化信息
        individualActivity.setActivityId(activityId);
        individualActivity.setUserId(userId);
        individualActivity.setUserName(user.getUserName());
        Date date = new Date();
        individualActivity.setStatus(0);
        individualActivity.setCreateTime(date);
        individualActivity.setModifyTime(date);
        individualActivity.setIsaccepted(isaccepted);
        if (position != null) {
            individualActivity.setPosition(position);
        } else {
            individualActivity.setPosition("普通成员");
        }

        int i = individualActivityMapper.insert(individualActivity);
//        int i = individualActivityMapper.addGroupStudent(individualGroup.getUserId(), individualGroup.getGroupId(),
//                individualGroup.getPosition(), individualGroup.getUserName(), 0, date, date);
        if(i != 1){
            throw new InsertException("活动"+ activityId +"添加学生"+ userId +"失败");
        }
    }

    public void modifyActivityStudent(int activityId, String userId, String position) {
        validateStatus(activityId, userId);
        //初始化学生信息
//        individualGroup.setGroupId(groupId);
//        individualGroup.setUserId(userId);
//        individualGroup.setUserName(stu.getUserName());
        if (position == null) {
            position = "普通成员";
        }
        Date date = new Date();

        int i = individualActivityMapper.modifyActivityStudent(position, date, activityId, userId);
        if(i != 1){
            throw new InsertException("活动"+ activityId +"修改学生"+ userId +"的信息失败");
        }
    }

    public void deleteActivityStudent(int activityId, String userId) {
        validateStatus(activityId, userId);
        //初始化学生信息
//        individualGroup.setGroupId(groupId);
//        individualGroup.setUserId(userId);
//        individualGroup.setUserName(stu.getUserName());

        int i = individualActivityMapper.deleteActivityStudent(activityId, userId);
        if(i != 1){
            throw new InsertException("活动"+ activityId +"删除学生"+ userId +"的信息失败");
        }
    }

    //查询该学生是否存在
    public User validateStu(String userId) {
        User stu = stuMapper.getByNum(userId);
        if (stu == null) {
            throw new EntityNotFoundException("学生"+ userId +"不存在");
        }
        return stu;
    }

    //查询该管理员是否存在
    public User validateManager(String userId) {
        User user = managerMapper.getByNum(userId);
        if (user == null) {
            throw new EntityNotFoundException("管理员"+ userId +"不存在");
        }
        return user;
    }

    //检测该学生在社团中保存的姓名与个人资料中的姓名是否一致
    public User validateStuName(String userId) {
        List<IndividualActivity> individualActivityList = individualActivityMapper.getActivityByStuId(userId);
        //查询该学生是否存在
        User stu = validateStu(userId);
        for (IndividualActivity individualActivity : individualActivityList) {
            if (!Objects.equals(individualActivity.getUserName(), stu.getUserName())) {
                throw new AccessDeniedException("学生"+ userId +"在活动"+ individualActivity.getActivityId() +"中的信息有误");
            }
        }
        return stu;
    }

    //检测该管理员在社团中保存的姓名与个人资料中的姓名是否一致
    public User validateManagerName(String userId) {
        List<IndividualActivity> individualActivityList = individualActivityMapper.getAllManagedActivities(userId);
        //查询该管理员是否存在
        User user = validateManager(userId);
        for (IndividualActivity individualActivity : individualActivityList) {
            if (!Objects.equals(individualActivity.getUserName(), user.getUserName())) {
                throw new AccessDeniedException("管理员"+ userId +"在活动"+ individualActivity.getActivityId() +"中的信息有误");
            }
        }
        return user;
    }

    //查询该活动是否存在
    public Activity validateActivity(int activityId) {
        Activity activity = activityMapper.getActivityById(activityId);
        if (activity == null) {
            throw new EntityNotFoundException("活动"+ activityId +"不存在");
        }
        return activity;
    }

    //查询修改对象的权限
    public void validateStatus(int activityId, String userId) {
        User user = validateStuName(userId);
        Activity activity = validateActivity(activityId);
        //查询修改或删除的用户是否存在及其权限是否为管理员
        IndividualActivity individualActivity = individualActivityMapper.getUserByUserIdAndActivityId(userId, activityId);
        if (individualActivity == null) {
            throw new EntityNotFoundException("用户"+ userId +"在活动"+ activityId +"不存在");
        } else if (individualActivity.getStatus() >= 1) {
            throw new AccessDeniedException("用户"+ userId +"在活动"+ activityId +"中拥有管理员权限，你的权限不足");
        }
    }

    public void confirmApplication(int activityId, String userId) {
        int i = individualActivityMapper.confirmApplicationByid(activityId, userId);
        if (i != 1) {
            throw new UpdateException("用户"+ userId +"在活动" + activityId + "的申请确认失败");
        }
    }

    public void denyApplication(int activityId, String userId) {
        int i = individualActivityMapper.denyApplicationByid(activityId, userId);
        if (i != 1) {
            throw new UpdateException("用户"+ userId +"在活动" + activityId + "的申请确认失败");
        }
    }
}
