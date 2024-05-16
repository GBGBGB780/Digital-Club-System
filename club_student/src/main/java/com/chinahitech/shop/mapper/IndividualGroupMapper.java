package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.IndividualGroup;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndividualGroupMapper extends BaseMapper<IndividualGroup> {
    @Select("select * from `Individual_group` WHERE studentId = #{studentId}")
    List<IndividualGroup> getGroupByStudentId(@Param("studentId") String studentId);
}
