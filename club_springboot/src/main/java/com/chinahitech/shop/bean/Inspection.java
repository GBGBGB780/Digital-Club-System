package com.chinahitech.shop.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.Year;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Inspection{
    @JsonProperty("userName")
    @TableId
    private int id;
    private String attachment;
    private String groupName;
    private Year year;
    private String submitter_id;
    private String feedback;
    private String createUser;
    private Date createTime;
    private String modifyUser;
    private Date modifyTime;
    private Boolean isAccepted;
}
