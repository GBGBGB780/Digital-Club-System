package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.Students;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface StuMapper extends BaseMapper<Students> {
    @Select("SELECT * FROM students WHERE stunumber = #{num}")
    Students getByNum(String num);

    @Insert("INSERT INTO students(stunumber, password, email, salt, createTime, modifyTime) " +
            "VALUES (#{stunumber}, #{password}, #{email}, #{salt}, #{createTime}, #{modifyTime})")
    int addStudent(@Param("stunumber") String stunumber,
                   @Param("password") String password,
                   @Param("email") String email,
                   @Param("salt") String salt,
                   @Param("createTime") Date createTime,
                   @Param("modifyTime") Date modifyTime);

    @Update("UPDATE students SET password = #{password}, modifyTime = #{modifyTime} WHERE stunumber = #{stunumber}")
    int updatePassword(@Param("stunumber") String stunumber, @Param("password") String password, @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `students` SET phone = #{phone}, modifyTime = #{modifyTime} WHERE stunumber = #{stunumber}")
    int updatePhone(@Param("stunumber") String stunumber, @Param("phone") String phone, @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `students` SET description = #{description}, modifyTime = #{modifyTime} WHERE stunumber = #{stunumber}")
    int updateDescription(@Param("stunumber") String stunumber, @Param("description") String description, @Param("modifyTime") Date modifyTime);

    @Update("UPDATE `students` SET nickname = #{nickname}, modifyTime = #{modifyTime} WHERE stunumber = #{stunumber}")
    int updateNickname(@Param("stunumber") String stunumber, @Param("nickname") String nickname, @Param("modifyTime") Date modifyTime);
}
