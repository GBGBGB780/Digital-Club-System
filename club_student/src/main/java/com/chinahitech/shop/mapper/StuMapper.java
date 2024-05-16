package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.Students;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StuMapper extends BaseMapper<Students> {
    @Select("SELECT * FROM students WHERE stunumber = #{num}")
    public Students getByNum(String num);

    @Insert("INSERT INTO students(stunumber, password, email) VALUES (#{stunumber}, #{password}, #{email})")
    void addStudent(@Param("stunumber") String stunumber, @Param("password") String password, @Param("email") String email);

    @Update("UPDATE students SET password = #{password} WHERE stunumber = #{stunumber}")
    void updatePassword(@Param("stunumber") String stunumber, @Param("password") String password);

    @Update("UPDATE `students` SET phone = #{phone} WHERE stunumber = #{stunumber}")
    void updatePhone(@Param("stunumber") String stunumber, @Param("phone") String phone);

    @Update("UPDATE `students` SET description = #{description} WHERE stunumber = #{stunumber}")
    void updateDescription(@Param("stunumber") String stunumber, @Param("description") String description);

    @Update("UPDATE `students` SET nickname = #{nickname} WHERE stunumber = #{stunumber}")
    void updateNickname(@Param("stunumber") String stunumber, @Param("nickname") String nickname);
}
