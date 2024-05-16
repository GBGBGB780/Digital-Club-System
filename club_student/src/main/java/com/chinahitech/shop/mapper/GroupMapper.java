package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.Group;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMapper extends BaseMapper<Group> {

    @Select("SELECT * FROM `group` WHERE name = #{name}")
    Group getByName(String name);

    // @Update("UPDATE group SET password = #{password} WHERE id = #{id}")
    // void updatePassword(@Param("id") String id, @Param("password") String password);
    
    @Select("select * from `group`")
    List<Group> findall();

    @Select("SELECT * FROM `group` WHERE name LIKE CONCAT('%', #{searchinfo}, '%')")
    List<Group> findBySearch(String searchinfo);

    // 对这个进行单元测试
    @Select("SELECT * FROM `group` WHERE name = #{groupname}")
    List<Group> findGroup(@Param("groupname") String groupname);

    @Select("select * from `group` where id = #{id}")
    Group getGroupById(@Param("id") String id);

    @Update("UPDATE `group` SET attachment = #{attachment} WHERE name = #{name}")
    int updateAttachment(@Param("name") String name, @Param("attachment") String attachment);

    @Update("UPDATE `group` SET image = #{image} WHERE name = #{name}")
    int updateImage(@Param("name") String name, @Param("image") String image);


    @Update("update `group` set description = #{description}, attachment=#{attachment}, image=#{image} where name = #{name}")
    int updateDescriptionByName(@Param("name") String groupname,
                                 @Param("description") String description,
                                 @Param("attachment") String attachment,
                                 @Param("image") String image);

    @Update("update `group` set password = #{password} where name = #{name}")
    int updatePasswordByName(@Param("name") String groupname, @Param("password") String password);

    @Select("select * from `group` where name = #{name}")
    Group getHot(String name);

    @Update("update `group` set hot = #{hot} where name = #{name}")
    int updateHot(String name, int hot);

    @Select("select * from `group` order by hot desc limit 5")
    List<Group> findtop();
}
