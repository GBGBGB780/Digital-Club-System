package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.IndividualActivity;
import com.chinahitech.shop.bean.IndividualGroup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IndividualActivityMapper extends BaseMapper<IndividualActivity> {
    @Select("select * from `individual_activity` WHERE userId = #{userId}")
    List<IndividualActivity> getActivityByStuId(@Param("userId") String userId);

    @Select("select * from `individual_activity` WHERE activityId = #{activityId}")
    List<IndividualActivity> getActivityByActivityId(@Param("activityId") int activityId);

    @Select("select * from `individual_activity` WHERE userId = #{userId} and status >= 1")
    List<IndividualActivity> getAllManagedActivities(@Param("userId") String userId);

    @Select("select * from `individual_activity` WHERE userId = #{userId} and activityId = #{activityId}")
    IndividualActivity getUserByUserIdAndActivityId(@Param("userId") String userId, @Param("activityId") int activityId);

//    @Insert("INSERT INTO Individual_group(userId, groupId, position, userName, status, createTime, modifyTime) " +
//            "VALUES (#{userId}, #{groupId}, #{position}, #{userName}, #{status}, #{createTime}, #{modifyTime})")
//    int addGroupStudent(@Param("userId") String userId,
//                   @Param("groupId") String groupId,
//                   @Param("position") String position,
//                   @Param("userName") String userName,
//                   @Param("status") int status,
//                   @Param("createTime") Date createTime,
//                   @Param("modifyTime") Date modifyTime);

    @Update("UPDATE individual_activity SET position = #{position}, modifyTime = #{modifyTime} WHERE activityId = #{activityId} and userId = #{userId}")
    int modifyActivityStudent(@Param("position") String position,
                              @Param("modifyTime") Date modifyTime,
                              @Param("activityId") int activityId,
                              @Param("userId") String userId);

    @Delete("delete from individual_activity WHERE activityId = #{activityId} and userId = #{userId}")
    int deleteActivityStudent(@Param("activityId") int activityId,
                              @Param("userId") String userId);
}
