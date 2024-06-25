package com.chinahitech.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StuApp {
    @TableId(type = IdType.AUTO)
    private int applicationId;
    private String groupName;
    private String stuName;
    private String stuNumber;
    private String phone;
    private String gender;
    private String major;
    private String selfIntro;
    private String attachment;
    private Boolean isAccepted;
    private String createUser;
    private Date createTime;
    private String modifyUser;
    private Date modifyTime;
}