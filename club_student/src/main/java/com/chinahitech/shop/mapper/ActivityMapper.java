package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.Activity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityMapper extends BaseMapper<Activity> {

    @Select("SELECT * FROM `Activity` WHERE name = #{name}")
    public Activity getByName(String name);

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

    @Update("UPDATE `Activity` SET attachment = #{attachment} WHERE name = #{name}")
    int updateAttachment(@Param("name") String name, @Param("attachment") String attachment);

    @Update("UPDATE `Activity` SET image = #{image} WHERE name = #{name}")
    int updateImage(@Param("name") String name, @Param("image") String image);


    @Update("update `Activity` set description = #{description}, attachment=#{attachment}, image=#{image} where name = #{name}")
    void updateDescriptionByName(@Param("name") String groupname,
                                 @Param("description") String description,
                                 @Param("attachment") String attachment,
                                 @Param("image") String image);

    @Update("update `Activity` set password = #{password} where name = #{name}")
    void updatePasswordByName(@Param("name") String groupname, @Param("password") String password);

    @Select("select * from `Activity` where name = #{name}")
    Activity getHot(String name);

    @Update("update `Activity` set hot = #{hot} where name = #{name}")
    void updateHot(String name, int hot);

    @Select("select * from `Activity` order by hot desc limit 5")
    List<Activity> findtop();
}

