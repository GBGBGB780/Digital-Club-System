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
    private Integer applicationid;
    private String groupname;
    private String stuname;
    private String stunumber;
    private String phone;
    private String gender;
    private String major;
    private String selfintro;
    private String attachment;
    private Boolean isaccepted;
    private String createUser;
    private Date createTime;
    private String modifyUser;
    private Date modifyTime;
}