package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.Group;
import com.chinahitech.shop.mapper.GroupMapper;
import com.chinahitech.shop.service.exception.EntityNotFoundException;
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
    private md5 md5 = new md5();

    public Group getByName(String name) {
        Group group = groupMapper.getByName(name);
        if (group == null) {
            throw new EntityNotFoundException("社团"+ name +"不存在");
        }
        return group;
    }

    public Group login(String name, String pwd) {
        System.out.println(name);
        Group group = groupMapper.getByName(name);
        if (group == null){
            throw new EntityNotFoundException("社团"+ name +"不存在");
        }
        String oldPwd = group.getPassword();
        //获取盐值
        String salt = group.getSalt();
//        System.out.println(salt);
        //获取用户输入的密码对应的加密
//        String newPwd = MD5handler(pwd,salt);
        if (!md5.isEqual(oldPwd,pwd,salt)){
//            System.out.println(oldPwd);
//            System.out.println(pwd);
            group.setPassword(null);
//            throw new PwdNotMatchException("密码错误");
        }
        return group;
    }

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
        Date date = new Date();
        int i = groupMapper.updateDescriptionByName(groupname, description, attachment, image, date);
        if(i != 1){
            throw new UpdateException("社团"+ groupname +"简介修改失败");
        }
    }

    public void updatePassword(String groupname, String password) {
        Group group = groupMapper.getByName(groupname);
//        String oldMD5pwd = stu.getPwd();
        String salt = group.getSalt();
//        if (!isEqual(oldMD5pwd, oldPwd, salt)){
//            throw new PwdNotMatchException("密码错误");
//        }
        String newMD5pwd = md5.MD5handler(password, salt);
        Date date = new Date();

        int i = groupMapper.updatePasswordByName(groupname, newMD5pwd, date);
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
}


