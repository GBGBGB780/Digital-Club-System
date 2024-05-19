package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.bean.IndividualGroup;
import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.mapper.GroupMapper;
import com.chinahitech.shop.mapper.IndividualGroupMapper;
import com.chinahitech.shop.mapper.StuMapper;
import com.chinahitech.shop.service.exception.AccessDeniedException;
import com.chinahitech.shop.service.exception.EntityNotFoundException;
import com.chinahitech.shop.service.exception.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IndividualGroupService {
    @Autowired
    private IndividualGroupMapper individualGroupMapper;
    private StuMapper stuMapper;
    private GroupMapper groupMapper;

    public List<IndividualGroup> getGroupByStuId(String userId) {
        return individualGroupMapper.getGroupByStuId(userId);
    }

    public List<IndividualGroup> getGroupByGroupId(String groupId) {
        return individualGroupMapper.getGroupByGroupId(groupId);
    }

    public List<IndividualGroup> getAllManagedGroups(String userId) {
        return individualGroupMapper.getAllManagedGroups(userId);
    }

    public IndividualGroup getUserByUserIdAndGroupId(String userId, String groupId) {
        return individualGroupMapper.getUserByUserIdAndGroupId(userId, groupId);
    }

    public void addGroupStudent(String groupId, String userId, String position) {
        User stu = stuMapper.getByNum(userId);
        if (stu == null) {
            throw new EntityNotFoundException("学生"+ userId +"不存在");
        }
        Group group = groupMapper.getGroupById(groupId);
        if (group == null) {
            throw new EntityNotFoundException("社团"+ groupId +"不存在");
        }
        IndividualGroup test = individualGroupMapper.getUserByUserIdAndGroupId(userId, groupId);
        if (test != null) {
            throw new InsertException("用户"+ userId +"在社团"+ groupId +"中已存在，无法重复加入");
        }
        IndividualGroup individualGroup  = new IndividualGroup();
        //初始化学生信息
        individualGroup.setGroupId(groupId);
        individualGroup.setUserId(userId);
        individualGroup.setUserName(stu.getUserName());
        if (position != null) {
            individualGroup.setPosition(position);
        } else {
            individualGroup.setPosition("普通成员");
        }
        Date date = new Date();

        int i = individualGroupMapper.addGroupStudent(individualGroup.getUserId(), individualGroup.getGroupId(),
                individualGroup.getPosition(), individualGroup.getUserName(), 0, date, date);
        if(i != 1){
            throw new InsertException("社团"+ groupId +"添加学生"+ userId +"失败");
        }
    }

    public void modifyGroupStudent(String groupId, String userId, String position) {
        User stu = stuMapper.getByNum(userId);
        if (stu == null) {
            throw new EntityNotFoundException("学生"+ userId +"不存在");
        }

        Group group = groupMapper.getGroupById(groupId);
        if (group == null) {
            throw new EntityNotFoundException("社团"+ groupId +"不存在");
        }
        //查询修改的用户权限是否为管理员
        IndividualGroup individualGroup = individualGroupMapper.getUserByUserIdAndGroupId(userId, groupId);
        if (individualGroup.getStatus() >= 1) {
            throw new AccessDeniedException("用户"+ userId +"在社团"+ groupId +"中拥有管理员权限，你的权限不足");
        }
        //初始化学生信息
//        individualGroup.setGroupId(groupId);
//        individualGroup.setUserId(userId);
//        individualGroup.setUserName(stu.getUserName());
        if (position == null) {
            position = "普通成员";
        }
        Date date = new Date();

        int i = individualGroupMapper.modifyGroupStudent(position, date, groupId, userId);
        if(i != 1){
            throw new InsertException("社团"+ groupId +"修改学生"+ userId +"的信息失败");
        }
    }

    public void deleteGroupStudent(String groupId, String userId) {
        User stu = stuMapper.getByNum(userId);
        if (stu == null) {
            throw new EntityNotFoundException("学生"+ userId +"不存在");
        }

        Group group = groupMapper.getGroupById(groupId);
        if (group == null) {
            throw new EntityNotFoundException("社团"+ groupId +"不存在");
        }
        //查询修改的用户权限是否为管理员
        IndividualGroup individualGroup = individualGroupMapper.getUserByUserIdAndGroupId(userId, groupId);
        if (individualGroup.getStatus() >= 1) {
            throw new AccessDeniedException("用户"+ userId +"在社团"+ groupId +"中拥有管理员权限，你的权限不足");
        }
        //初始化学生信息
//        individualGroup.setGroupId(groupId);
//        individualGroup.setUserId(userId);
//        individualGroup.setUserName(stu.getUserName());

        int i = individualGroupMapper.deleteGroupStudent(groupId, userId);
        if(i != 1){
            throw new InsertException("社团"+ groupId +"删除学生"+ userId +"的信息失败");
        }
    }
}
