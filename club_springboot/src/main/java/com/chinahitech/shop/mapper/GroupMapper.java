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

    @Select("SELECT * FROM `group` WHERE name = #{name} and isaccepted = true")
    Group getByName(String name);

    // @Update("UPDATE group SET password = #{password} WHERE id = #{id}")
    // void updatePassword(@Param("id") String id, @Param("password") String password);
    
    @Select("select * from `group` WHERE isaccepted = true")
    List<Group> findall();

    @Select("SELECT * FROM `group` WHERE name LIKE CONCAT('%', #{searchinfo}, '%') and isaccepted = true")
    List<Group> findBySearch(String searchinfo);

    // 对这个进行单元测试
    @Select("SELECT * FROM `group` WHERE name = #{groupname} and isaccepted = true")
    List<Group> findGroup(@Param("groupname") String groupname);

    @Select("select * from `group` where id = #{id} and isaccepted = true" )
    Group getGroupById(@Param("id") int id);

    @Update("UPDATE `group` SET attachment = #{attachment}, modifyTime = #{modifyTime} WHERE name = #{name} and isaccepted = true")
    int updateAttachment(@Param("name") String name, @Param("attachment") String attachment, @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `group` SET image = #{image}, modifyTime = #{modifyTime} WHERE name = #{name} and isaccepted = true")
    int updateImage(@Param("name") String name, @Param("image") String image, @Param("modifyTime") Date modifyTime);


    @Update("update `group` set description = #{description}, attachment=#{attachment}, image=#{image}, modifyTime = #{modifyTime} where name = #{name} and isaccepted = true")
    int updateDescriptionByName(@Param("name") String groupname,
                                @Param("description") String description,
                                @Param("attachment") String attachment,
                                @Param("image") String image,
                                @Param("modifyTime") Date modifyTime);

    @Select("select * from `group` where name = #{name} and isaccepted = true")
    Group getHot(String name);

    @Update("update `group` set hot = #{hot}, modifyTime = #{modifyTime} where name = #{name} and isaccepted = true")
    int updateHot(String name, int hot, Date modifyTime);

    @Select("select * from `group`  where isaccepted = true order by hot desc limit 5")
    List<Group> findtop();

    @Update("update `group` set isaccepted = true where id = #{groupId}")
    int confirmApplicationByid(@Param("groupId") Integer groupId);

    @Update("update `group` set isaccepted = false where id = #{groupId}")
    int denyApplicationByid(@Param("groupId") Integer groupId);
}
