package com.chinahitech.shop.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IndividualGroup {
    //    private String id;

    @TableId
    private int id;
    private int groupId;
    private String userId;
    private String position;
    @JsonProperty("userName")
    private String userName;
    private int status;
    private String createUser;
    private Date createTime;
    private String modifyUser;
    private Date modifyTime;
}
