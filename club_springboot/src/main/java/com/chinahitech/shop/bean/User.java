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
public class User{
    @TableId
    private String userId;
    private String password;
    @JsonProperty("userName")
    private String userName;
    private String email;
    private String phone;
    private String campus;
    private String school;
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
