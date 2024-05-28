package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.StuApp;
import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.mapper.StuAppMapper;
import com.chinahitech.shop.service.exception.InsertException;
import com.chinahitech.shop.service.exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StuAppService {
    @Autowired
    private StuAppMapper stuAppMapper;

    public List<StuApp> query() {
        return stuAppMapper.findall();
    }

    public List<StuApp> queryMyapp(String stunumber) {
        return stuAppMapper.findMyapp(stunumber);
    }

    public void insert(StuApp stuApp) {
        int i = stuAppMapper.insert(stuApp);
        if (i != 1) {
            throw new InsertException("申请" + stuApp + "无法保存到数据库");
        }
    }

    public List<StuApp> queryRecvapp(String groupname){
        return stuAppMapper.findRecvapp(groupname);
    }

    public StuApp queryDetailapp(Integer id) {
        return stuAppMapper.getById(id);
    }

    public String findIsAccepted(Integer id) {
        return stuAppMapper.findIsAccepted(id);
    }

    public void updateAttachment(int applicationid, String attachment) {
        StuApp stuApp = queryDetailapp(applicationid);
        stuApp.setAttachment(attachment);

        int i = stuAppMapper.updateById(stuApp);
        if (i != 1) {
            throw new UpdateException("申请" + applicationid + "附件修改失败");
        }
    }

    public void confirmApplication(Integer applicationid) {
        StuApp stuApp = queryDetailapp(applicationid);
        stuApp.setIsAccepted(true);

        int i = stuAppMapper.updateById(stuApp);

        if (i != 1) {
            throw new UpdateException("申请" + applicationid + "确认失败");
        }
    }

    public void denyApplication(Integer applicationid) {
        StuApp stuApp = queryDetailapp(applicationid);
        stuApp.setIsAccepted(false);

        int i = stuAppMapper.updateById(stuApp);
        if (i != 1) {
            throw new UpdateException("申请" + applicationid + "拒绝失败");
        }
    }
}
