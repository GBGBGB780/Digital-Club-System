package com.chinahitech.shop.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Group extends Organization{
//    private String id;
    @JsonProperty("username")
    private String name;
    private String leader;
    private String image;
    private String description;
    private String attachment;
//    private String password;
    private int hot;
    private String createUser;
    private Date createTime;
    private String modifyUser;
    private Date modifyTime;
}
