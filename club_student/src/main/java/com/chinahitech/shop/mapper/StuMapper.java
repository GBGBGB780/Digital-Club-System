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

    @Update("UPDATE students SET password = #{password} WHERE stunumber = #{stunumber}")
    void updatePassword(@Param("stunumber") String stunumber, @Param("password") String password);

    @Insert("INSERT INTO students(stunumber, password, email) VALUES (#{stunumber}, #{password}, #{email})")
    void addStudent(@Param("stunumber") String stunumber, @Param("password") String password, @Param("email") String email);
    
    @Update("UPDATE `students` SET phone = #{phone} WHERE stunumber = #{stunumber}")
    void updatePhone(@Param("stunumber") String stunumber, @Param("phone") String phone);
}
