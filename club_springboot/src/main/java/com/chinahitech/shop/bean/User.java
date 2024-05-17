package com.chinahitech.shop.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends Organization{
    @JsonProperty("username")
    private String userId;
    private String userName;
    private String email;
    private String phone;
    private String campus;
    private String major;
    private String description;
    private int status;
    private String nickname;
    private String avatar;
    private String salt;
    private String createUser;
    private Date createTime;
    private String modifyUser;
    private Date modifyTime;
}
