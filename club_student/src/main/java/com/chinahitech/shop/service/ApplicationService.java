package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.Application;
import com.chinahitech.shop.mapper.ApplicationMapper;
import com.chinahitech.shop.service.exception.InsertException;
import com.chinahitech.shop.service.exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationMapper applicationMapper;

    public List query() {
        return applicationMapper.findall();
    }

    public List queryMyapp(String stunumber) {
        return applicationMapper.findMyapp(stunumber);
    }

    public void insert(Application application) {
        int i = applicationMapper.insert(application);
        if (i != 1) {
            throw new InsertException("申请" + application + "无法保存到数据库");
        }
    }

    public List queryRecvapp(String groupname){
        return applicationMapper.findRecvapp(groupname);
    }

    public List queryDetailapp(Integer id) {
        return applicationMapper.findDetailapp(id);
    }

    public String findIsAccepted(Integer id) {
        return applicationMapper.findIsAccepted(id);
    }

    public void updateAttachment(int applicationid, String attachment) {
        int i = applicationMapper.updateAttachment(applicationid, attachment);
        if (i != 1) {
            throw new UpdateException("申请" + applicationid + "附件修改失败");
        }
    }

    public void confirmApplication(Integer applicationid) {
        int i = applicationMapper.confirmApplicationByid(applicationid);
        if (i != 1) {
            throw new UpdateException("申请" + applicationid + "确认失败");
        }
    }

    public void denyApplication(Integer applicationid) {
        int i = applicationMapper.denyApplicationByid(applicationid);
        if (i != 1) {
            throw new UpdateException("申请" + applicationid + "拒绝失败");
        }
    }
}
