package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.bean.IndividualGroup;
import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.mapper.GroupMapper;
import com.chinahitech.shop.mapper.IndividualGroupMapper;
import com.chinahitech.shop.mapper.ManagerMapper;
import com.chinahitech.shop.mapper.StuMapper;
import com.chinahitech.shop.service.exception.AccessDeniedException;
import com.chinahitech.shop.service.exception.EntityNotFoundException;
import com.chinahitech.shop.service.exception.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class IndividualGroupService {
    @Autowired
    private IndividualGroupMapper individualGroupMapper;
    private StuMapper stuMapper;
    private ManagerMapper managerMapper;
    private GroupMapper groupMapper;

    public List<IndividualGroup> getGroupByStuId(String userId) {
        User user = validateStuName(userId);
        return individualGroupMapper.getGroupByStuId(userId);
    }

    public List<IndividualGroup> getGroupByGroupId(String groupId) {
        Group group = validateGroup(groupId);
        return individualGroupMapper.getGroupByGroupId(groupId);
    }

    public List<Group> getAllManagedGroups(String managerId) {
        //检测管理者名字是否一致
        User user = validateManagerName(managerId);

        List<IndividualGroup> individualGroupList = individualGroupMapper.getAllManagedGroups(managerId);
        List<Group> groupList = new ArrayList<>();
        for (IndividualGroup individualGroup : individualGroupList) {
            groupList.add(groupMapper.getGroupById(individualGroup.getGroupId()));
        }
        return groupList;
    }

    public IndividualGroup getUserByUserIdAndGroupId(String userId, String groupId) {
        User user = validateStuName(userId);
        Group group = validateGroup(groupId);
        return individualGroupMapper.getUserByUserIdAndGroupId(userId, groupId);
    }

    public void addGroupStudent(String groupId, String userId, String position) {
        User user = validateStu(userId);
        Group group = validateGroup(groupId);
        IndividualGroup test = individualGroupMapper.getUserByUserIdAndGroupId(userId, groupId);
        if (test != null) {
            throw new InsertException("用户"+ userId +"在社团"+ groupId +"中已存在，无法重复加入");
        }
        IndividualGroup individualGroup  = new IndividualGroup();
        //初始化学生信息
        individualGroup.setGroupId(groupId);
        individualGroup.setUserId(userId);
        individualGroup.setUserName(user.getUserName());
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
        validateStatus(groupId, userId);
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
        validateStatus(groupId, userId);
        //初始化学生信息
//        individualGroup.setGroupId(groupId);
//        individualGroup.setUserId(userId);
//        individualGroup.setUserName(stu.getUserName());

        int i = individualGroupMapper.deleteGroupStudent(groupId, userId);
        if(i != 1){
            throw new InsertException("社团"+ groupId +"删除学生"+ userId +"的信息失败");
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
        List<IndividualGroup> individualGroupList = individualGroupMapper.getGroupByStuId(userId);
        //查询该学生是否存在
        User stu = validateStu(userId);
        for (IndividualGroup individualGroup : individualGroupList) {
            if (!Objects.equals(individualGroup.getUserName(), stu.getUserName())) {
                throw new AccessDeniedException("学生"+ userId +"在社团"+ individualGroup.getGroupId() +"中的信息有误");
            }
        }
        return stu;
    }

    //检测该管理员在社团中保存的姓名与个人资料中的姓名是否一致
    public User validateManagerName(String userId) {
        List<IndividualGroup> individualGroupList = individualGroupMapper.getAllManagedGroups(userId);
        //查询该管理员是否存在
        User user = validateManager(userId);
        for (IndividualGroup individualGroup : individualGroupList) {
            if (!Objects.equals(individualGroup.getUserName(), user.getUserName())) {
                throw new AccessDeniedException("管理员"+ userId +"在社团"+ individualGroup.getGroupId() +"中的信息有误");
            }
        }
        return user;
    }

    //查询该社团是否存在
    public Group validateGroup(String groupId) {
        Group group = groupMapper.getGroupById(groupId);
        if (group == null) {
            throw new EntityNotFoundException("社团"+ groupId +"不存在");
        }
        return group;
    }

    //查询修改对象的权限
    public void validateStatus(String groupId, String userId) {
        User user = validateStuName(userId);
        Group group = validateGroup(groupId);
        //查询修改或删除的用户是否存在及其权限是否为管理员
        IndividualGroup individualGroup = individualGroupMapper.getUserByUserIdAndGroupId(userId, groupId);
        if (individualGroup == null) {
            throw new EntityNotFoundException("用户"+ userId +"在社团"+ groupId +"不存在");
        } else if (individualGroup.getStatus() >= 1) {
            throw new AccessDeniedException("用户"+ userId +"在社团"+ groupId +"中拥有管理员权限，你的权限不足");
        }
    }
}
