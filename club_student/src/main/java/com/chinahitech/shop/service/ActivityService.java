package com.chinahitech.shop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinahitech.shop.bean.Activity;
import com.chinahitech.shop.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class ActivityService {
    interface EntityNotFoundExceptionInterface {
        String getMessage();
    }

    class EntityNotFoundException extends RuntimeException implements EntityNotFoundExceptionInterface {
        private final Function<String, String> ex = (String message) -> message;

        public EntityNotFoundException(String message) {
            super(message);  // 使用超类抛出异常
        }

        @Override
        public String getMessage() {
            return ex.apply(super.getMessage());  // 调用lambda表达式
        }
    }
    @Autowired
    private ActivityMapper activityMapper;

    public Activity getByName(String name) {
        return activityMapper.getByName(name);
    }

    // public void updatePassword(String id, String password){
    //     manMapper.updatePassword(id, password);
    // }

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

    public void updateDescription(String groupname, String description,String attachment,String image) {
        activityMapper.updateDescriptionByName(groupname, description, attachment,image);
    }

    public void updatePassword(String groupname, String password) {
        activityMapper.updatePasswordByName(groupname, password);
    }

    public Activity getActivityById(String id) {
        return activityMapper.getActivityById(id);
    }

    public void addHot(String name) {
        System.out.println(name);
        Activity activity = activityMapper.getHot(name);
        int hot = activity.getHot();
        hot++;
        activityMapper.updateHot(name, hot);
    }

    public void updateAttachment(String name, String attachment) {
        int rowsUpdated = activityMapper.updateAttachment(name, attachment);
        if (rowsUpdated == 0) {
            throw new EntityNotFoundException("Activity with name " + name + " not found");
        }
    }

    public void updateImage(String name, String image) {
        int rowsUpdated = activityMapper.updateImage(name, image);
        if (rowsUpdated == 0) {
            throw new EntityNotFoundException("Activity with name " + name + " not found");
        }
    }
}



