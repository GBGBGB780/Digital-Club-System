package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.chinahitech.shop.bean.Inspection;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.Date;
import java.util.List;

@Repository
public interface InspectionMapper extends BaseMapper<Inspection> {

    @Select("SELECT * FROM `Inspection` ")
    List<Inspection> findAll();

    @Select("SELECT * FROM `Inspection` WHERE group_name LIKE CONCAT('%', #{searchinfo}, '%') ")
    List<Inspection> getBySearchInfo(String searchInfo);

    @Select("SELECT * FROM `Inspection` WHERE group_name = #{groupName} ")
    List<Inspection> getByGroup(String groupName);

    @Select("SELECT * FROM `Inspection` WHERE group_name = #{groupName} and year = #{year} ")
    List<Inspection> getByGroupAndYear(String groupName, Year year);

    @Select("SELECT * FROM `Inspection` WHERE year = #{year} ")
    List<Inspection> getByYear(Year year);

    @Select("SELECT * FROM `Inspection` WHERE id = #{id} ")
    Inspection getById(int id);

    @Update("UPDATE `Inspection` SET feedback = #{feedback}, modify_time = #{modifyTime} " +
            "WHERE id = #{inspectionId} and group_name = #{groupName}")
    int addFeedback(@Param("inspectionId") int inspectionId,
                         @Param("feedback") String feedback,
                         @Param("groupName") String groupName,
                         @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `Inspection` SET attachment = #{attachment}, modify_time = #{modifyTime} " +
            "WHERE id = #{inspectionId} and group_name = #{groupName}")
    int modifyAttachment(@Param("inspectionId") int inspectionId,
                         @Param("attachment") String attachment,
                         @Param("groupName") String groupName,
                         @Param("modifyTime") Date modifyTime);


    @Update("update `Inspection` set is_accepted = true where id = #{inspectionId}")
    int confirmApplicationByid(@Param("inspectionId") int inspectionId);

    @Update("update `Inspection` set is_accepted = false where id = #{inspectionId}")
    int denyApplicationByid(@Param("inspectionId") int inspectionId);
}
