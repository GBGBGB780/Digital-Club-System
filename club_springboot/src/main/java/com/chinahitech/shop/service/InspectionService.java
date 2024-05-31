package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.Activity;
import com.chinahitech.shop.bean.Inspection;
import com.chinahitech.shop.mapper.*;
import com.chinahitech.shop.service.exception.EntityNotFoundException;
import com.chinahitech.shop.service.exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Date;
import java.util.List;

@Service
public class InspectionService {
    @Autowired
    private InspectionMapper inspectionMapper;

    public Inspection getInspectionById(int id) {
        Inspection inspection = inspectionMapper.getById(id);
        if(inspection == null){
            throw new EntityNotFoundException("年检申请"+ id +"不存在");
        }
        return inspection;
    }

    public List<Inspection> findAllByGroup(String groupName) {
        return inspectionMapper.getByGroup(groupName);
    }

    public List<Inspection> findAllByGroupAndYear(String groupName, Year year) {
        return inspectionMapper.getByGroupAndYear(groupName, year);
    }

    public List<Inspection> findAll(String searchInfo) {
        if (searchInfo == null || searchInfo.trim().isEmpty()) {
            return inspectionMapper.findAll();
        } else {
            return inspectionMapper.getBySearchInfo(searchInfo);
        }
    }

    public List<Inspection> findAllByYear(Year year) {
        return inspectionMapper.getByYear(year);
    }

    public void addInspection(Inspection inspection) {
        Date date = new Date();
        inspection.setId(0);
        inspection.setYear(Year.now());
        inspection.setCreateTime(date);
        inspection.setModifyTime(date);
        int i = inspectionMapper.insert(inspection);
        if(i != 1){
            throw new UpdateException("年检申请"+ inspection.getId() +"添加失败");
        }
    }

    public void addFeedback(int inspectionId, String groupName, String feedback) {
        Date date = new Date();
        int i = inspectionMapper.addFeedback(inspectionId, groupName, feedback, date);
        if(i != 1){
            throw new UpdateException("年检申请"+ inspectionId +"反馈添加失败");
        }
    }

    public void modifyAttachment(int inspectionId, String groupName, String attachment) {
        Date date = new Date();
        int rowsUpdated = inspectionMapper.modifyAttachment(inspectionId, groupName, attachment, date);
        if (rowsUpdated == 0) {
            throw new UpdateException("年检申请"+ inspectionId +"附件修改失败");
        }
    }


    public void confirmApplication(int inspectionId) {
        int i = inspectionMapper.confirmApplicationByid(inspectionId);
        if (i != 1) {
            throw new UpdateException("对年检申请" + inspectionId + "的操作确认失败");
        }
    }

    public void denyApplication(int inspectionId) {
        int i = inspectionMapper.denyApplicationByid(inspectionId);
        if (i != 1) {
            throw new UpdateException("对年检申请" + inspectionId + "的操作拒绝失败");
        }
    }
}
