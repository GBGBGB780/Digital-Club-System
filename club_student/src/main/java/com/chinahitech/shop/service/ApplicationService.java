package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.Application;
import com.chinahitech.shop.mapper.ApplicationMapper;
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
        applicationMapper.insert(application);
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
        applicationMapper.updateAttachment(applicationid, attachment);
    }

    public void confirmApplication(Integer applicationid) {
        applicationMapper.confirmApplicationByid(applicationid);
    }

    public void denyApplication(Integer applicationid) {
        applicationMapper.denyApplicationByid(applicationid);
    }
}
