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
public class Activity{
    //    private String id;
    @TableId
    private int id;
    private String name;
    private String organizer;
    private String image;
    private String description;
    private String attachment;
    //    private String password;
    private int hot;
    private String arrange;
    private Date time;
    private int number;
    private String place;
    private int type;
    private String groupName;
    private String createUser;
    private Date createTime;
    private String modifyUser;
    private Date modifyTime;
    private Boolean isAccepted;
}

