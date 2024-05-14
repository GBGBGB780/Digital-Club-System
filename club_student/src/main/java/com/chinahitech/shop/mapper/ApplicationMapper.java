package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.Application;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationMapper extends BaseMapper<Application> {
    @Select("select * from `application`")
    List<Application> findall();

    @Select("SELECT * FROM `application` WHERE stunumber = #{stunumber}")
    List<Application> findMyapp(@Param("stunumber") String stunumber);

    @Select("SELECT * FROM `application` WHERE groupname = #{groupname}")
    List<Application> findRecvapp(@Param("groupname") String groupname);

    @Select("SELECT * FROM `application` WHERE applicationid = #{id}")
    List<Application> findDetailapp(Integer id);

    @Select("SELECT CASE WHEN isaccepted IS NULL THEN 'null' WHEN isaccepted = true THEN 'true' ELSE 'false' END as isaccepted FROM `application` WHERE applicationid = #{id}")
    String findIsAccepted(Integer id);

    @Update("UPDATE application SET attachment = #{attachment} WHERE applicationid = #{applicationid}")
    void updateAttachment(@Param("applicationid") int applicationid, @Param("attachment") String attachment);

    @Update("update `application` set isaccepted = true where applicationid = #{applicationid}")
    void confirmApplicationByid(@Param("applicationid") Integer applicationid);

    @Update("update `application` set isaccepted = false where applicationid = #{applicationid}")
    void denyApplicationByid(@Param("applicationid") Integer applicationid);
}
