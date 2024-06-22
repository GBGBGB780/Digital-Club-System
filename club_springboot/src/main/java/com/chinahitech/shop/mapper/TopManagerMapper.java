package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TopManagerMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE user_id = #{num} and status >= 10")
    User getByNum(String num);

    @Select("select * from `user` order by status")
    List<User> getAllUsers();

    @Select("SELECT * FROM user WHERE user_name = #{userName} order by status")
    List<User> getUser(String userName);

    @Insert("INSERT INTO user(user_id, password, email, salt, create_time, modify_time, status) " +
            "VALUES (#{userId}, #{password}, #{email}, #{salt}, #{createTime}, #{modifyTime}, #{status})")
    int addManager(@Param("userId") String userId,
                   @Param("password") String password,
                   @Param("email") String email,
                   @Param("salt") String salt,
                   @Param("createTime") Date createTime,
                   @Param("modifyTime") Date modifyTime,
                   @Param("status") int status);

    @Update("UPDATE user SET password = #{password}, modify_time = #{modifyTime} WHERE user_id = #{userId} and status >= 10")
    int updatePassword(@Param("userId") String userId,
                       @Param("password") String password,
                       @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `user` SET phone = #{phone}, modify_time = #{modifyTime} WHERE user_id = #{userId} and status >= 10")
    int updatePhone(@Param("userId") String userId, @Param("phone") String phone, @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `user` SET description = #{description}, modify_time = #{modifyTime} " +
            "WHERE user_id = #{userId} and status >= 10")
    int updateDescription(@Param("userId") String userId,
                          @Param("description") String description,
                          @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `user` SET nickname = #{nickname}, modify_time = #{modifyTime} WHERE user_id = #{userId} and status >= 10")
    int updateNickname(@Param("userId") String userId,
                       @Param("nickname") String nickname,
                       @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `user` SET campus = #{campus}, school = #{school}, major = #{major}, modify_time = #{modifyTime} " +
            "WHERE user_id = #{userId} and status >= 10")
    int updateMajor(@Param("userId") String userId,
                    @Param("campus") String campus,
                    @Param("school") String school,
                    @Param("major") String major,
                    @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `user` SET status = #{status}, modify_time = #{modifyTime} WHERE user_id = #{userId} and status < 10")
    int updatePermission(@Param("userId") String userId, @Param("status") int status, @Param("modifyTime") Date modifyTime);

    @Select("SELECT * FROM user WHERE user_id = #{num}")
    User getByNumNoStatus(String num);
}

