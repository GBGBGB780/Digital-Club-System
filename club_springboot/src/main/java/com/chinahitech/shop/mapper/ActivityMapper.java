package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.Activity;
import com.chinahitech.shop.bean.Group;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ActivityMapper extends BaseMapper<Activity> {


    @Select("select * from `Activity` where isaccepted = true")
    List<Activity> findall();

    @Select("SELECT * FROM `Activity` WHERE name LIKE CONCAT('%', #{searchinfo}, '%') and isaccepted = true")
    List<Activity> findBySearch(String searchinfo);

//    @Select("SELECT * FROM `Activity` WHERE name = #{name} and isaccepted = true")
//    List<Activity> findActivity(@Param("name") String name);

    @Select("SELECT * FROM `Activity` WHERE name = #{name} and isaccepted = true")
    Activity getActivityByName(String name);

    @Select("select * from `Activity` where id = #{id} and isaccepted = true")
    Activity getActivityById(@Param("id") int id);

    @Select("select * from `Activity` where name = #{name} and groupName = #{groupName} and isaccepted = true")
    Activity getActivityByNameAndGroupName(@Param("name") String name, @Param("groupName") String groupName);

    @Select("select * from `Activity` where groupName = #{groupName} and isaccepted = true")
    List<Activity> getActivityByGroupName(@Param("groupName") String groupName);

    @Update("UPDATE `Activity` SET attachment = #{attachment}, modifyTime = #{modifyTime} " +
            "WHERE name = #{name} and groupName = #{groupName} and isaccepted = true")
    int updateAttachment(@Param("name") String name,
                         @Param("attachment") String attachment,
                         @Param("groupName") String groupName,
                         @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `Activity` SET image = #{image}, modifyTime = #{modifyTime} " +
            "WHERE name = #{name} and groupName = #{groupName} and isaccepted = true")
    int updateImage(@Param("name") String name,
                    @Param("image") String image,
                    @Param("groupName") String groupName,
                    @Param("modifyTime") Date modifyTime);

    @Update("update `Activity` set description = #{description}, attachment=#{attachment}, image=#{image}, modifyTime = #{modifyTime} " +
            "where name = #{name} and groupName = #{groupName} and isaccepted = true")
    int updateDescriptionByName(@Param("name") String name,
                                @Param("description") String description,
                                @Param("attachment") String attachment,
                                @Param("image") String image,
                                @Param("groupName") String groupName,
                                @Param("modifyTime") Date modifyTime);

    @Update("update `Activity` set name = #{name}, organizer = #{organizer}, description = #{description}, " +
            "attachment = #{attachment}, image = #{image}, arrange = #{arrange}, time = #{time}, " +
            "number = #{number}, place = #{place}, type = #{type}, modifyTime = #{modifyTime} " +
            "where id = #{id} and isaccepted = true")
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

    @Update("update `Activity` set hot = #{hot}, modifyTime = #{modifyTime} " +
            "where name = #{name} and isaccepted = true")
    int updateHot(String name, int hot, Date modifyTime);

    @Select("select * from `Activity`  where isaccepted = true order by hot desc limit 5")
    List<Activity> findtop();

    @Delete("delete from Activity WHERE id = #{id} and isaccepted = true")
    int deleteActivity(@Param("id") int id);


    @Select("select * from `Activity` WHERE isaccepted = false")
    List<Activity> findAllApp();

    @Select("SELECT * FROM `Activity` WHERE name LIKE CONCAT('%', #{searchinfo}, '%') and isaccepted = false")
    List<Activity> findAppBySearch(String searchinfo);

    @Select("SELECT * FROM `Activity` WHERE name = #{name} and groupName = #{groupName} and isaccepted = false")
    Activity getAppByNameAndGroupName(@Param("name") String name, @Param("groupName") String groupName);

    @Update("update `Activity` set isaccepted = true where id = #{activityId}")
    int confirmApplicationByid(@Param("activityId") int activityId);

    @Update("update `Activity` set isaccepted = false where id = #{activityId}")
    int denyApplicationByid(@Param("activityId") int activityId);
//    @Insert("insert into `Activity`(id, name, organizer, image, description, attachment, hot, arrange, " +
//            "time, number, place, type, groupName, createTime, modifyTime, isaccepted) " +
//            "values (#{id}, #{name}, #{organizer}, #{image}, #{description}, #{attachment}, #{hot}, #{arrange}, #{time}, " +
//            "#{number}, #{place}, #{type}, #{groupName}, #{createTime}, #{modifyTime}, false)" )
//    int addActivity(@Param("id") int id,
//                   @Param("name") String name,
//                   @Param("organizer") String organizer,
//                   @Param("description") String description,
//                   @Param("attachment") String attachment,
//                   @Param("image") String image,
//                   @Param("hot") int hot,
//                   @Param("arrange") String arrange,
//                   @Param("time") Date time,
//                   @Param("number") int number,
//                   @Param("place") String place,
//                   @Param("type") int type,
//                   @Param("groupName") String groupName,
//                   @Param("createTime") Date createTime,
//                   @Param("modifyTime") Date modifyTime);

}

