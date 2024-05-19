package com.chinahitech.shop.bean;

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
    @JsonProperty("username")
    private int id;
    private String groupId;
    private String userId;
    private String position;
    private String userName;
    private int status;
    private String createUser;
    private Date createTime;
    private String modifyUser;
    private Date modifyTime;
}
