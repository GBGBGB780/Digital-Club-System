package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.Activity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ActivityMapper extends BaseMapper<Activity> {

    @Select("SELECT * FROM `Activity` WHERE name = #{name}")
    Activity getByName(String name);

    @Select("select * from `Activity`")
    List<Activity> findall();

    @Select("SELECT * FROM `Activity` WHERE name LIKE CONCAT('%', #{searchinfo}, '%')")
    List<Activity> findBySearch(String searchinfo);

    // 对这个进行单元测试
    @Select("SELECT * FROM `Activity` WHERE name = #{name}")
    List<Activity> findActivity(@Param("name") String name);

    @Select("select * from `Activity` where id = #{id}")
    Activity getActivityById(@Param("id") int id);

    @Select("select * from `Activity` where name = #{name} and groupName = #{groupName}")
    Activity getActivityByNameAndGroupName(@Param("name") String name, @Param("groupName") String groupName);

    @Select("select * from `Activity` where groupName = #{groupName}")
    List<Activity> getActivityByGroupName(@Param("groupName") String groupName);

    @Update("UPDATE `Activity` SET attachment = #{attachment}, modifyTime = #{modifyTime} WHERE name = #{name} and groupName = #{groupName}")
    int updateAttachment(@Param("name") String name, @Param("attachment") String attachment, @Param("groupName") String groupName, @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `Activity` SET image = #{image}, modifyTime = #{modifyTime} WHERE name = #{name} and groupName = #{groupName}")
    int updateImage(@Param("name") String name, @Param("image") String image, @Param("groupName") String groupName, @Param("modifyTime") Date modifyTime);

    @Update("update `Activity` set description = #{description}, attachment=#{attachment}, image=#{image}, modifyTime = #{modifyTime} where name = #{name} and groupName = #{groupName}")
    int updateDescriptionByName(@Param("name") String name,
                                @Param("description") String description,
                                @Param("attachment") String attachment,
                                @Param("image") String image,
                                @Param("groupName") String groupName,
                                @Param("modifyTime") Date modifyTime);

    @Update("update `Activity` set name = #{name}, organizer = #{organizer}, description = #{description}, " +
            "attachment = #{attachment}, image = #{image}, arrange = #{arrange}, time = #{time}, " +
            "number = #{number}, place = #{place}, type = #{type}, modifyTime = #{modifyTime} " +
            "where id = #{id}")
    int modifyInfo(@Param("name") String name,
                   @Param("organizer") String organizer,
                   @Param("description") String description,
                   @Param("attachment") String attachment,
                   @Param("image") String image,
                   @Param("arrange") String arrange,
                   @Param("time") Date time,
                   @Param("number") int number,
                   @Param("place") String place,
                   @Param("type") int type,
                   @Param("modifyTime") Date modifyTime,
                   @Param("id") int id);

    @Select("select * from `Activity` where groupName = #{groupName} and name = #{name}")
    Activity getHot(String groupName, String name);

    @Update("update `Activity` set hot = #{hot}, modifyTime = #{modifyTime} where groupName = #{groupName} and name = #{name}")
    int updateHot(String groupName, String name, int hot, Date modifyTime);

    @Select("select * from `Activity` order by hot desc limit 5")
    List<Activity> findtop();

    @Insert("insert into `Activity`(id, name, organizer, image, description, attachment, hot, arrange, " +
            "time, number, place, type, groupName, createTime, modifyTime) " +
            "values (#{id}, #{name}, #{organizer}, #{image}, #{description}, #{attachment}, #{hot}, #{arrange}, #{time}, " +
            "#{number}, #{place}, #{type}, #{groupName}, #{createTime}, #{modifyTime} )" )
    int addActivity(@Param("id") int id,
                   @Param("name") String name,
                   @Param("organizer") String organizer,
                   @Param("description") String description,
                   @Param("attachment") String attachment,
                   @Param("image") String image,
                   @Param("hot") int hot,
                   @Param("arrange") String arrange,
                   @Param("time") Date time,
                   @Param("number") int number,
                   @Param("place") String place,
                   @Param("type") int type,
                   @Param("groupName") String groupName,
                   @Param("createTime") Date createTime,
                   @Param("modifyTime") Date modifyTime);

    @Delete("delete from Activity WHERE id = #{id}")
    int deleteActivity(@Param("id") int id);
}

