package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.mapper.GroupMapper;
import com.chinahitech.shop.service.exception.EntityNotFoundException;
import com.chinahitech.shop.service.exception.InsertException;
import com.chinahitech.shop.service.exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    private Md5 md5 = new Md5();

    public Group getByName(String name) {
        Group group = groupMapper.getByName(name);
        if (group == null) {
            throw new EntityNotFoundException("社团"+ name +"不存在");
        }
        return group;
    }

    public List<Group> query(String searchInfo) {
        if (searchInfo == null || searchInfo.trim().isEmpty()) {
            return groupMapper.findAll();
        } else {
            return groupMapper.findBySearch(searchInfo);
        }
    }

    public List<Group> queryTop() {
        return groupMapper.findtop();
    }

//    public List<Group> queryGroup(String groupname){
//        return groupMapper.findGroup(groupname);
//    }

    public void updateDescription(String groupname, String description,String attachment,String image) {
        Date date = new Date();
        int i = groupMapper.updateDescriptionByName(groupname, description, attachment, image, date);
        if(i != 1){
            throw new UpdateException("社团"+ groupname +"简介修改失败");
        }
    }
        
    public Group getGroupById(int id) {
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

        Date date = new Date();
        int i = groupMapper.updateHot(groupName, hot, date);
        if(i != 1){
            throw new UpdateException("社团"+ groupName +"热度修改失败");
        }
    }

    public void updateAttachment(String name, String attachment) {
        Date date = new Date();
        int rowsUpdated = groupMapper.updateAttachment(name, attachment, date);
        if (rowsUpdated == 0) {
            throw new UpdateException("社团"+ name +"附件修改失败");
        }
    }

    public void updateImage(String name, String image) {
        Date date = new Date();
        int rowsUpdated = groupMapper.updateImage(name, image, date);
        if (rowsUpdated == 0) {
            throw new UpdateException("社团"+ name +"图片修改失败");
        }
    }

    public void insert(Group group) {
        Date date = new Date();
        group.setId(0);
        group.setCreateTime(date);
        group.setModifyTime(date);
        int i = groupMapper.insert(group);
        if (i != 1) {
            throw new InsertException("社团" + group + "无法保存到数据库");
        }
    }


    public List<Group> getAllApp(String searchinfo) {
        if (searchinfo == null || searchinfo.trim().isEmpty()) {
            return groupMapper.findAllApp();
        } else {
            return groupMapper.findAppBySearch(searchinfo);
        }
    }

    public Group getAppByName(String name) {
        Group group = groupMapper.getAppByName(name);
        if (group == null) {
            throw new EntityNotFoundException("社团"+ name +"不存在");
        }
        return group;
    }

    public void confirmApplication(int groupId) {
        int i = groupMapper.confirmApplicationByid(groupId);
        if (i != 1) {
            throw new UpdateException("对社团" + groupId + "的操作确认失败");
        }
    }

    public void denyApplication(int groupId) {
        int i = groupMapper.denyApplicationByid(groupId);
        if (i != 1) {
            throw new UpdateException("对社团" + groupId + "的操作拒绝失败");
        }
    }
}


