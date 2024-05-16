package com.chinahitech.shop.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Activity extends Organization{
    //    private String id;
    @JsonProperty("username")
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
}

