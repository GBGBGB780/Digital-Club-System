package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.Activity;

import com.chinahitech.shop.mapper.ActivityMapper;
import com.chinahitech.shop.mapper.IndividualActivityMapper;
import com.chinahitech.shop.mapper.StuMapper;
import com.chinahitech.shop.exception.EntityNotFoundException;
import com.chinahitech.shop.exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityService {
//    interface EntityNotFoundExceptionInterface {
//        String getMessage();
//    }
//
//    class EntityNotFoundException extends RuntimeException implements EntityNotFoundExceptionInterface {
//        private final Function<String, String> ex = (String message) -> message;
//
//        public EntityNotFoundException(String message) {
//            super(message);  // 使用超类抛出异常
//        }
//
//        @Override
//        public String getMessage() {
//            return ex.apply(super.getMessage());  // 调用lambda表达式
//        }
//    }
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private IndividualActivityMapper individualActivityMapper;
    @Autowired
    private StuMapper stuMapper;
    @Autowired
    private IndividualActivityService individualActivityService;
//    public Activity getByName(String name) {
//        Activity activity = activityMapper.getActivityByName(name);
//        if(activity == null){
//            throw new EntityNotFoundException("活动"+ name +"不存在");
//        }
//        return activity;
//    }

    public Activity getActivityByNameAndGroupName(String name, String groupName) {
        Activity activity = activityMapper.getActivityByNameAndGroupName(name, groupName);
        if(activity == null){
            throw new EntityNotFoundException("社团"+ groupName +"组织的活动"+ name +"不存在");
        }
        return activity;
    }

    public List<Activity> query(String searchinfo) {
        if (searchinfo == null || searchinfo.trim().isEmpty()) {
            return activityMapper.findall();
        } else {
            return activityMapper.findBySearch(searchinfo);
        }
    }

    public List<Activity> queryTop() {
        return activityMapper.findtop();
    }

//    public List<Activity> queryActivity(String name){
//        return activityMapper.findActivity(name);
//    }

    public List<Activity> getActivityByGroupName(String groupName){
        return activityMapper.getActivityByGroupName(groupName);
    }

    public Activity getActivityById(int id) {
        Activity activity = activityMapper.getActivityById(id);
        if(activity == null){
            throw new EntityNotFoundException("活动"+ id +"不存在");
        }
        return activity;
    }

    public void updateDescription(String groupName, String name, String description,String attachment,String image) {
        Date date = new Date();
        int i = activityMapper.updateDescriptionByName(groupName, name, description, attachment, image, date);
        if(i != 1){
            throw new UpdateException("活动"+ name +"简介修改失败");
        }
    }

    public void modifyInfo(Activity activity) {
        Date date = new Date();
        int i = activityMapper.modifyInfo(
                activity.getName(),
                activity.getOrganizer(),
                activity.getDescription(),
                activity.getAttachment(),
                activity.getImage(),
                activity.getArrange(),
                activity.getTime(),
                activity.getNumber(),
                activity.getPlace(),
                activity.getType(),
                date,
                activity.getId());
        if(i != 1){
            throw new UpdateException("活动"+ activity.getName() +"信息修改失败");
        }
    }

    public void updateAttachment(String groupName, String name, String attachment) {
        Date date = new Date();
        int rowsUpdated = activityMapper.updateAttachment(groupName, name, attachment, date);
        if (rowsUpdated == 0) {
            throw new UpdateException("活动"+ name +"附件修改失败");
        }
    }

    public void updateImage(String groupName, String name, String image) {
        Date date = new Date();
        int rowsUpdated = activityMapper.updateImage(groupName, name, image, date);
        if (rowsUpdated == 0) {
            throw new UpdateException("活动"+ name +"图片修改失败");
        }
    }

    public void addActivity(Activity activity) {
        Date date = new Date();
        activity.setId(0);
        activity.setHot(0);
        activity.setNumber(0);
        activity.setCreateTime(date);
        activity.setModifyTime(date);
        if (activity.getName() == null || activity.getName().trim().isEmpty()) {
            activity.setName("未知");
        }
        int i = activityMapper.insert(activity);
        if(i != 1){
            throw new UpdateException("活动"+ activity.getName() +"添加失败");
        }
    }

    public void deleteActivity(Activity activity) {
        int id = activity.getId();
        int i = activityMapper.deleteById(id);
        if(i != 1){
            throw new UpdateException("活动"+ activity.getName() +"删除失败");
        }
    }


    public List<Activity> getAllApp(String searchinfo) {
        if (searchinfo == null || searchinfo.trim().isEmpty()) {
            return activityMapper.findAllApp();
        } else {
            return activityMapper.findAppBySearch(searchinfo);
        }
    }

    public Activity getAppByNameAndGroupName(String name, String groupName) {
        Activity activity = activityMapper.getAppByNameAndGroupName(name, groupName);
        if (activity == null) {
            throw new EntityNotFoundException("活动"+ name +"不存在");
        }
        return activity;
    }

    public void confirmApplication(int activityId) {
        int i = activityMapper.confirmApplicationByid(activityId);
        if (i != 1) {
            throw new UpdateException("对活动" + activityId + "的操作确认失败");
        }
    }

    public void denyApplication(int activityId) {
        int i = activityMapper.denyApplicationByid(activityId);
        if (i != 1) {
            throw new UpdateException("对活动" + activityId + "的操作拒绝失败");
        }
    }
}



