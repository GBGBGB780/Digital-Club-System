package com.chinahitech.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinahitech.shop.bean.IndividualGroup;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndividualGroupMapper extends BaseMapper<IndividualGroup> {
    @Select("select * from `Individual_group` WHERE userId = #{userId}")
    List<IndividualGroup> getGroupByStuId(@Param("userId") String userId);

    @Select("select * from `Individual_group` WHERE groupId = #{groupId}")
    List<IndividualGroup> getGroupByGroupId(@Param("groupId") String groupId);

    @Select("select * from `Individual_group` WHERE userId = #{userId} and status >= 1")
    List<IndividualGroup> getAllManagedGroups(@Param("userId") String userId);
}
