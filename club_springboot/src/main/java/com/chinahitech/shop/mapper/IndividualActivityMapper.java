package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.IndividualActivity;
import com.chinahitech.shop.bean.notAddedToDatabase.ActivityNum;
import com.chinahitech.shop.bean.notAddedToDatabase.GroupNum;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IndividualActivityMapper extends BaseMapper<IndividualActivity> {
    @Select("select * from `individual_activity` WHERE user_id = #{userId} and is_accepted = true")
    List<IndividualActivity> getActivityByStuId(@Param("userId") String userId);

    @Select("select * from `individual_activity` WHERE activity_id = #{activityId} and is_accepted = true")
    List<IndividualActivity> getActivityByActivityId(@Param("activityId") int activityId);

    @Select("select * from `individual_activity` WHERE activity_id = #{activityId} ")
    List<IndividualActivity> getApplyByActivityId(@Param("activityId") int activityId);

    @Select("select * from `individual_activity` WHERE user_id = #{userId} and status >= 1")
    List<IndividualActivity> getAllManagedActivities(@Param("userId") String userId);

    @Select("select * from `individual_activity` " +
            "WHERE user_id = #{userId} and activity_id = #{activityId} and is_accepted = true")
    IndividualActivity getUserByUserIdAndActivityId(@Param("userId") String userId, @Param("activityId") int activityId);

    @Select("select activity_id as activityId, count(*) as count from `individual_activity` WHERE is_accepted = true" +
            " group by activity_id order by count desc limit 5")
    List<ActivityNum> getActivityMembers();

//    @Insert("INSERT INTO Individual_group(userId, groupId, position, userName, status, createTime, modifyTime) " +
//            "VALUES (#{userId}, #{groupId}, #{position}, #{userName}, #{status}, #{createTime}, #{modifyTime})")
//    int addGroupStudent(@Param("userId") String userId,
//                   @Param("groupId") String groupId,
//                   @Param("position") String position,
//                   @Param("userName") String userName,
//                   @Param("status") int status,
//                   @Param("createTime") Date createTime,
//                   @Param("modifyTime") Date modifyTime);

    @Update("UPDATE individual_activity SET position = #{position}, modify_time = #{modifyTime} " +
            "WHERE activity_id = #{activityId} and user_id = #{userId} and is_accepted = true")
    int modifyActivityStudent(@Param("position") String position,
                              @Param("modifyTime") Date modifyTime,
                              @Param("activityId") int activityId,
                              @Param("userId") String userId);

    @Delete("delete from individual_activity WHERE activity_id = #{activityId} and user_id = #{userId} and is_accepted = true")
    int deleteActivityStudent(@Param("activityId") int activityId,
                              @Param("userId") String userId);

    @Update("update `individual_activity` set is_accepted = true where activity_id = #{activityId} and user_id = #{userId}")
    int confirmApplicationByid(@Param("activityId") int activityId,
                               @Param("userId") String userId);

    @Update("update `individual_activity` set is_accepted = false where activity_id = #{activityId} and user_id = #{userId}")
    int denyApplicationByid(@Param("activityId") int activityId,
                            @Param("userId") String userId);

    @Update("UPDATE individual_activity SET status = #{status}, modify_time = #{modifyTime} " +
            "WHERE activity_id = #{activityId} and user_id = #{userId}")
    int updatePermission(@Param("activityId") int activityId,
                         @Param("userId") String userId,
                         @Param("status") int status,
                         @Param("modifyTime") Date modifyTime);
}
