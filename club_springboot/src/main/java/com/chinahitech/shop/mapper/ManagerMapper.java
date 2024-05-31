package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ManagerMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE user_id = #{num} and status >= 1")
    User getByNum(String num);

    @Insert("INSERT INTO user(user_id, password, email, salt, create_time, modify_time, status) " +
            "VALUES (#{userId}, #{password}, #{email}, #{salt}, #{createTime}, #{modifyTime}, #{status})")
    int addManager(@Param("userId") String userId,
                   @Param("password") String password,
                   @Param("email") String email,
                   @Param("salt") String salt,
                   @Param("createTime") Date createTime,
                   @Param("modifyTime") Date modifyTime,
                   @Param("status") int status);

    @Update("UPDATE user SET password = #{password}, modify_time = #{modifyTime} WHERE user_id = #{userId} and status >= 1")
    int updatePassword(@Param("userId") String userId, @Param("password") String password, @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `user` SET phone = #{phone}, modify_time = #{modifyTime} WHERE user_id = #{userId} and status >= 1")
    int updatePhone(@Param("userId") String userId, @Param("phone") String phone, @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `user` SET description = #{description}, modify_time = #{modifyTime} WHERE user_id = #{userId} and status >= 1")
    int updateDescription(@Param("userId") String userId, @Param("description") String description, @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `user` SET nickname = #{nickname}, modify_time = #{modifyTime} WHERE user_id = #{userId} and status >= 1")
    int updateNickname(@Param("userId") String userId, @Param("nickname") String nickname, @Param("modifyTime") Date modifyTime);
}

