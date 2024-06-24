package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.Group;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GroupMapper extends BaseMapper<Group> {

    @Select("SELECT * FROM `group` WHERE name = #{name} and is_accepted = true")
    Group getByName(String name);

    // @Update("UPDATE group SET password = #{password} WHERE id = #{id}")
    // void updatePassword(@Param("id") String id, @Param("password") String password);
    
    @Select("select * from `group` WHERE is_accepted = true")
    List<Group> findAll();

    @Select("SELECT * FROM `group` WHERE name LIKE CONCAT('%', #{searchInfo}, '%') and is_accepted = true")
    List<Group> findBySearch(String searchInfo);


    @Select("select * from `group` where id = #{id} and is_accepted = true" )
    Group getGroupById(@Param("id") int id);

    @Update("UPDATE `group` SET attachment = #{attachment}, modify_time = #{modifyTime} " +
            "WHERE name = #{name} and is_accepted = true")
    int updateAttachment(@Param("name") String name,
                         @Param("attachment") String attachment,
                         @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `group` SET image = #{image}, modify_time = #{modifyTime} WHERE name = #{name} and is_accepted = true")
    int updateImage(@Param("name") String name, @Param("image") String image, @Param("modifyTime") Date modifyTime);


    @Update("update `group` set description = #{description}, attachment=#{attachment}, image=#{image}, " +
            "modify_time = #{modifyTime} where name = #{name} and is_accepted = true")
    int updateDescriptionByName(@Param("name") String groupname,
                                @Param("description") String description,
                                @Param("attachment") String attachment,
                                @Param("image") String image,
                                @Param("modifyTime") Date modifyTime);

    @Select("select * from `group` where name = #{name} and is_accepted = true")
    Group getHot(String name);

    @Update("update `group` set hot = #{hot}, modify_time = #{modifyTime} where name = #{name} and is_accepted = true")
    int updateHot(String name, int hot, Date modifyTime);

    @Select("select * from `group`  where is_accepted = true order by hot desc limit 5")
    List<Group> findtop();

    @Select("select * from `group` ")
    List<Group> findAllApp();

    @Select("SELECT * FROM `group` WHERE name LIKE CONCAT('%', #{searchinfo}, '%') ")
    List<Group> findAppBySearch(String searchinfo);

    @Select("SELECT * FROM `group` WHERE name = #{groupname} ")
    Group getAppByName(@Param("groupname") String groupname);

    @Update("update `group` set is_accepted = true where id = #{groupId}")
    int confirmApplicationByid(@Param("groupId") int groupId);

    @Update("update `group` set is_accepted = false where id = #{groupId}")
    int denyApplicationByid(@Param("groupId") int groupId);
}
