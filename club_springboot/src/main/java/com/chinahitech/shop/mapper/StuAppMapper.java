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

    @Select("SELECT * FROM `stu_app` WHERE stunumber = #{stunumber}")
    List<StuApp> findMyapp(@Param("stunumber") String stunumber);

    @Select("SELECT * FROM `stu_app` WHERE groupname = #{groupname}")
    List<StuApp> findRecvapp(@Param("groupname") String groupname);

    @Select("SELECT * FROM `stu_app` WHERE applicationid = #{id}")
    List<StuApp> findDetailapp(Integer id);

    @Select("SELECT CASE WHEN isaccepted IS NULL THEN 'null' WHEN isaccepted = true THEN 'true' ELSE 'false' END as isaccepted FROM `stu_app` WHERE applicationid = #{id}")
    String findIsAccepted(Integer id);

    @Update("UPDATE stu_app SET attachment = #{attachment} WHERE applicationid = #{applicationid}")
    int updateAttachment(@Param("applicationid") int applicationid, @Param("attachment") String attachment);

    @Update("update `stu_app` set isaccepted = true where applicationid = #{applicationid}")
    int confirmApplicationByid(@Param("applicationid") Integer applicationid);

    @Update("update `stu_app` set isaccepted = false where applicationid = #{applicationid}")
    int denyApplicationByid(@Param("applicationid") Integer applicationid);
}
