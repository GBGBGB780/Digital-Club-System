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
    private GroupMapper groupMapper;

    public Activity getByName(String name) {
        Activity activity = activityMapper.getByName(name);
        if(activity == null){
            throw new EntityNotFoundException("活动"+ name +"不存在");
        }
        return activity;
    }

    public Activity getActivityByNameAndGroupId(String name, String groupId) {
        Activity activity = activityMapper.getActivityByNameAndGroupId(name, groupId);
        if(activity == null){
            throw new EntityNotFoundException("社团"+ groupId +"组织的活动"+ name +"不存在");
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

    public List<Activity> getActivityByGroupId(String groupId){
        return activityMapper.getActivityByGroupId(groupId);
    }

//    public void updatePassword(String groupname, String password) {
//        int i = activityMapper.updatePasswordByName(groupname, password);
//        if(i != 1){
//            throw new UpdateException("活动"+ groupname +"密码修改失败");
//        }
//    }

    public Activity getActivityById(String id) {
        Activity activity = activityMapper.getActivityById(id);
        if(activity == null){
            throw new EntityNotFoundException("活动"+ id +"不存在");
        }
        return activity;
    }

    public void addHot(String groupId, String name) {
        Date date = new Date();
        System.out.println(name);
        Activity activity = activityMapper.getHot(groupId, name);
        if(activity == null){
            throw new EntityNotFoundException("活动"+ name +"不存在");
        }
        int hot = activity.getHot();
        hot++;
        int i = activityMapper.updateHot(groupId, name, hot, date);
        if(i != 1){
            throw new UpdateException("活动"+ name +"热度修改失败");
        }
    }

    public void updateDescription(String groupId, String name, String description,String attachment,String image) {
        Date date = new Date();
        int i = activityMapper.updateDescriptionByName(groupId, name, description, attachment, image, date);
        if(i != 1){
            throw new UpdateException("活动"+ name +"简介修改失败");
        }
    }

    public void updateAttachment(String groupId, String name, String attachment) {
        Date date = new Date();
        int rowsUpdated = activityMapper.updateAttachment(groupId, name, attachment, date);
        if (rowsUpdated == 0) {
            throw new UpdateException("活动"+ name +"附件修改失败");
        }
    }

    public void updateImage(String groupId, String name, String image) {
        Date date = new Date();
        int rowsUpdated = activityMapper.updateImage(groupId, name, image, date);
        if (rowsUpdated == 0) {
            throw new UpdateException("活动"+ name +"图片修改失败");
        }
    }
}



