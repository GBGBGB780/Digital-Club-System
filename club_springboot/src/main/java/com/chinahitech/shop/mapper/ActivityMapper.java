package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.Activity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ActivityMapper extends BaseMapper<Activity> {

    @Select("SELECT * FROM `Activity` WHERE name = #{name}")
    Activity getByName(String name);

    // @Update("UPDATE group SET password = #{password} WHERE id = #{id}")
    // void updatePassword(@Param("id") String id, @Param("password") String password);

    @Select("select * from `Activity`")
    List<Activity> findall();

    @Select("SELECT * FROM `Activity` WHERE name LIKE CONCAT('%', #{searchinfo}, '%')")
    List<Activity> findBySearch(String searchinfo);

    // 对这个进行单元测试
    @Select("SELECT * FROM `Activity` WHERE name = #{name}")
    List<Activity> findActivity(@Param("name") String name);

    @Select("select * from `Activity` where id = #{id}")
    Activity getActivityById(@Param("id") String id);

    @Select("select * from `Activity` where name = #{name} and groupId = #{groupId}")
    Activity getActivityByNameAndGroupId(@Param("name") String name, @Param("groupId") String groupId);

    @Select("select * from `Activity` where groupId = #{groupId}")
    List<Activity> getActivityByGroupId(@Param("groupId") String groupId);

    @Update("UPDATE `Activity` SET attachment = #{attachment}, modifyTime = #{modifyTime} WHERE name = #{name} and groupId = #{groupId}")
    int updateAttachment(@Param("name") String name, @Param("attachment") String attachment, @Param("groupId") String groupId, @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `Activity` SET image = #{image}, modifyTime = #{modifyTime} WHERE name = #{name} and groupId = #{groupId}")
    int updateImage(@Param("name") String name, @Param("image") String image, @Param("groupId") String groupId, @Param("modifyTime") Date modifyTime);


    @Update("update `Activity` set description = #{description}, attachment=#{attachment}, image=#{image}, modifyTime = #{modifyTime} where name = #{name} and groupId = #{groupId}")
    int updateDescriptionByName(@Param("name") String name,
                                @Param("description") String description,
                                @Param("attachment") String attachment,
                                @Param("image") String image,
                                @Param("groupId") String groupId,
                                @Param("modifyTime") Date modifyTime);

//    @Update("update `Activity` set password = #{password} where name = #{name}")
//    int updatePasswordByName(@Param("name") String groupname, @Param("password") String password);

    @Select("select * from `Activity` where groupId = #{groupId} and name = #{name}")
    Activity getHot(String groupId, String name);

    @Update("update `Activity` set hot = #{hot}, modifyTime = #{modifyTime} where groupId = #{groupId} and name = #{name}")
    int updateHot(String groupId, String name, int hot, Date modifyTime);

    @Select("select * from `Activity` order by hot desc limit 5")
    List<Activity> findtop();
}

