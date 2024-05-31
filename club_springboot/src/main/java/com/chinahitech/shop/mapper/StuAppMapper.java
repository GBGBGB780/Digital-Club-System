package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.StuApp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StuAppMapper extends BaseMapper<StuApp> {


    @Select("select * from `stu_app`")
    List<StuApp> findall();

    @Select("SELECT * FROM `stu_app` WHERE stu_number = #{stunumber}")
    List<StuApp> findMyapp(@Param("stunumber") String stunumber);

    @Select("SELECT * FROM `stu_app` WHERE group_name = #{groupname}")
    List<StuApp> findRecvapp(@Param("groupname") String groupname);

    @Select("SELECT * FROM `stu_app` WHERE application_id = #{id}")
    StuApp getById(Integer id);

    @Select("SELECT CASE WHEN is_accepted IS NULL THEN 'null' " +
            "WHEN is_accepted = true THEN 'true' " +
            "ELSE 'false' END as isaccepted " +
            "FROM `stu_app` WHERE application_id = #{id}")
    String findIsAccepted(Integer id);

    @Update("UPDATE stu_app SET attachment = #{attachment} WHERE application_id = #{applicationid}")
    int updateAttachment(@Param("applicationid") int applicationid, @Param("attachment") String attachment);

    @Update("update `stu_app` set is_accepted = true where application_id = #{applicationid}")
    int confirmApplicationByid(@Param("applicationid") Integer applicationid);

    @Update("update `stu_app` set is_accepted = false where application_id = #{applicationid}")
    int denyApplicationByid(@Param("applicationid") Integer applicationid);
}
