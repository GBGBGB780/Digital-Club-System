package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.IndividualGroup;
import com.chinahitech.shop.mapper.IndividualGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividualGroupService {
    @Autowired
    private IndividualGroupMapper individualGroupMapper;

    public List<IndividualGroup> getGroupByStuId(String stuId) {
        return individualGroupMapper.getGroupByStuId(stuId);
    }

    public List<IndividualGroup> getGroupByGroupId(String groupId) {
        return individualGroupMapper.getGroupByGroupId(groupId);
    }
}
