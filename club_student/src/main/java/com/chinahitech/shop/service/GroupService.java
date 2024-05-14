package com.chinahitech.shop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class GroupService {
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
    private GroupMapper groupMapper;

    public Group getByName(String name) {
        return groupMapper.getByName(name);
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

    public List queryTop() {
        return groupMapper.findtop();
    }

    public List<Group> queryGroup(String groupname){
        return groupMapper.findGroup(groupname);
    }

    public void updateDescription(String groupname, String description,String attachment,String image) {
        groupMapper.updateDescriptionByName(groupname, description, attachment,image);
    }

    public void updatePassword(String groupname, String password) {
        groupMapper.updatePasswordByName(groupname, password);
    }
        
    public Group getGroupById(String id) {
        return groupMapper.getGroupById(id);
    }

    public void addHot(String groupName) {
        System.out.println(groupName);
        Group group = groupMapper.getHot(groupName);
        int hot = group.getHot();
        hot++;
        groupMapper.updateHot(groupName, hot);
    }

    public void updateAttachment(String name, String attachment) {
        int rowsUpdated = groupMapper.updateAttachment(name, attachment);
        if (rowsUpdated == 0) {
            throw new EntityNotFoundException("Group with name " + name + " not found");
        }
    }

    public void updateImage(String name, String image) {
        int rowsUpdated = groupMapper.updateImage(name, image);
        if (rowsUpdated == 0) {
            throw new EntityNotFoundException("Group with name " + name + " not found");
        }
    }
}


