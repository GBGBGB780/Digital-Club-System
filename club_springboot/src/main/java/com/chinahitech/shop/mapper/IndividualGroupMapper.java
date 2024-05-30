package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.IndividualGroup;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IndividualGroupMapper extends BaseMapper<IndividualGroup> {
    @Select("select * from `Individual_group` WHERE user_id = #{userId}")
    List<IndividualGroup> getGroupByStuId(@Param("userId") String userId);

    @Select("select * from `Individual_group` WHERE group_id = #{groupId}")
    List<IndividualGroup> getGroupByGroupId(@Param("groupId") int groupId);

    @Select("select * from `Individual_group` WHERE user_id = #{userId} and status >= 1")
    List<IndividualGroup> getAllManagedGroups(@Param("userId") String userId);

    @Select("select * from `Individual_group` WHERE user_id = #{userId} and group_id = #{groupId}")
    IndividualGroup getUserByUserIdAndGroupId(@Param("userId") String userId, @Param("groupId") int groupId);

//    @Insert("INSERT INTO Individual_group(userId, groupId, position, userName, status, createTime, modifyTime) " +
//            "VALUES (#{userId}, #{groupId}, #{position}, #{userName}, #{status}, #{createTime}, #{modifyTime})")
//    int addGroupStudent(@Param("userId") String userId,
//                   @Param("groupId") String groupId,
//                   @Param("position") String position,
//                   @Param("userName") String userName,
//                   @Param("status") int status,
//                   @Param("createTime") Date createTime,
//                   @Param("modifyTime") Date modifyTime);

    @Update("UPDATE Individual_group SET position = #{position}, modify_time = #{modifyTime} WHERE group_id = #{groupId} and user_id = #{userId}")
    int modifyGroupStudent(@Param("position") String position,
                           @Param("modifyTime") Date modifyTime,
                           @Param("groupId") int groupId,
                           @Param("userId") String userId);

    @Delete("delete from Individual_group WHERE group_id = #{groupId} and user_id = #{userId}")
    int deleteGroupStudent(@Param("groupId") int groupId,
                           @Param("userId") String userId);

    @Update("UPDATE Individual_group SET status = #{status}, modify_time = #{modifyTime} WHERE group_id = #{groupId} and user_id = #{userId}")
    int updatePermission(@Param("groupId") int groupId,
                      @Param("userId") String userId,
                      @Param("status") int status,
                      @Param("modifyTime") Date modifyTime);
}
