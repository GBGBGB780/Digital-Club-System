package com.chinahitech.shop.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Group{
    @JsonProperty("username")
    private String id;
    private String name;
    private String leader;
    private String image;
    private String description;
    private String attachment;
    private int hot;
    private String createUser;
    private Date createTime;
    private String modifyUser;
    private Date modifyTime;
}
