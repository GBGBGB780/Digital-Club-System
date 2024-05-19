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
public interface StuMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE userId = #{num}")
    User getByNum(String num);

    @Insert("INSERT INTO user(userId, password, email, salt, createTime, modifyTime, status) " +
            "VALUES (#{stunumber}, #{password}, #{email}, #{salt}, #{createTime}, #{modifyTime}, #{status})")
    int addStudent(@Param("stunumber") String stunumber,
                   @Param("password") String password,
                   @Param("email") String email,
                   @Param("salt") String salt,
                   @Param("createTime") Date createTime,
                   @Param("modifyTime") Date modifyTime,
                   @Param("status") int status);

    @Update("UPDATE user SET password = #{password}, modifyTime = #{modifyTime} WHERE userId = #{stunumber}")
    int updatePassword(@Param("stunumber") String stunumber, @Param("password") String password, @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `user` SET phone = #{phone}, modifyTime = #{modifyTime} WHERE userId = #{stunumber}")
    int updatePhone(@Param("stunumber") String stunumber, @Param("phone") String phone, @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `user` SET description = #{description}, modifyTime = #{modifyTime} WHERE userId = #{stunumber}")
    int updateDescription(@Param("stunumber") String stunumber, @Param("description") String description, @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `user` SET nickname = #{nickname}, modifyTime = #{modifyTime} WHERE userId = #{stunumber}")
    int updateNickname(@Param("stunumber") String stunumber, @Param("nickname") String nickname, @Param("modifyTime") Date modifyTime);
}
