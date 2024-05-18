package com.chinahitech.shop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinahitech.shop.bean.Activity;
import com.chinahitech.shop.mapper.ActivityMapper;
import com.chinahitech.shop.mapper.GroupMapper;
import com.chinahitech.shop.service.exception.EntityNotFoundException;
import com.chinahitech.shop.service.exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

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

    public Activity getByName(String name) {
        Activity activity = activityMapper.getByName(name);
        if(activity == null){
            throw new EntityNotFoundException("活动"+ name +"不存在");
        }
        return activity;
    }

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

    public List<Activity> queryActivity(String name){
        return activityMapper.findActivity(name);
    }

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

    public void addHot(String groupName, String name) {
        Date date = new Date();
        System.out.println(name);
        Activity activity = activityMapper.getHot(groupName, name);
        if(activity == null){
            throw new EntityNotFoundException("活动"+ name +"不存在");
        }
        int hot = activity.getHot();
        hot++;
        int i = activityMapper.updateHot(groupName, name, hot, date);
        if(i != 1){
            throw new UpdateException("活动"+ name +"热度修改失败");
        }
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
                activity.getGroupName(),
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
}



