package com.chinahitech.shop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.mapper.GroupMapper;
import com.chinahitech.shop.service.exception.EntityNotFoundException;
import com.chinahitech.shop.service.exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class GroupService {
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
    private GroupMapper groupMapper;

    public Group getByName(String name) {
        Group group = groupMapper.getByName(name);
        if (group == null) {
            throw new EntityNotFoundException("社团"+ name +"不存在");
        }
        return group;
    }

    // public void updatePassword(String id, String password){
    //     manMapper.updatePassword(id, password);
    // }

    public List<Group> query(String searchinfo) {
        if (searchinfo == null || searchinfo.trim().isEmpty()) {
            return groupMapper.findall();
        } else {
            return groupMapper.findBySearch(searchinfo);
        }
    }

    public List<Group> queryTop() {
        return groupMapper.findtop();
    }

    public List<Group> queryGroup(String groupname){
        return groupMapper.findGroup(groupname);
    }

    public void updateDescription(String groupname, String description,String attachment,String image) {
        int i = groupMapper.updateDescriptionByName(groupname, description, attachment,image);
        if(i != 1){
            throw new UpdateException("社团"+ groupname +"简介修改失败");
        }
    }

    public void updatePassword(String groupname, String password) {
        int i = groupMapper.updatePasswordByName(groupname, password);
        if(i != 1){
            throw new UpdateException("社团"+ groupname +"密码修改失败");
        }
    }
        
    public Group getGroupById(String id) {
        Group group = groupMapper.getGroupById(id);
        if (group == null) {
            throw new EntityNotFoundException("社团"+ id +"不存在");
        }
        return group;
    }

    public void addHot(String groupName) {
        System.out.println(groupName);
        Group group = groupMapper.getHot(groupName);
        if(group == null){
            throw new EntityNotFoundException("社团"+ groupName +"不存在");
        }
        int hot = group.getHot();
        hot++;
        int i = groupMapper.updateHot(groupName, hot);
        if(i != 1){
            throw new UpdateException("社团"+ groupName +"热度修改失败");
        }
    }

    public void updateAttachment(String name, String attachment) {
        int rowsUpdated = groupMapper.updateAttachment(name, attachment);
        if (rowsUpdated == 0) {
            throw new UpdateException("社团"+ name +"附件修改失败");
        }
    }

    public void updateImage(String name, String image) {
        int rowsUpdated = groupMapper.updateImage(name, image);
        if (rowsUpdated == 0) {
            throw new UpdateException("社团"+ name +"图片修改失败");
        }
    }
}


