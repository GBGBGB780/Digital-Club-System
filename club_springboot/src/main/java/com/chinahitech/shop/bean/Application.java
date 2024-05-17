package com.chinahitech.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Application {
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
    private Timestamp time;
    private Boolean isaccepted;

//    public Integer getApplicationid() {
//        return applicationid;
//    }
//
//    public void setApplicationid(Integer applicationid) {
//        this.applicationid = applicationid;
//    }
//
//    public String getGroupname() {
//        return groupname;
//    }
//
//    public void setGroupname(String groupname) {
//        this.groupname = groupname;
//    }
//
//    public String getStuname() {
//        return stuname;
//    }
//
//    public void setStuname(String stuname) {
//        this.stuname = stuname;
//    }
//
//    public String getStunumber() {
//        return stunumber;
//    }
//
//    public void setStunumber(String stunumber) {
//        this.stunumber = stunumber;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getMajor() {
//        return major;
//    }
//
//    public void setMajor(String major) {
//        this.major = major;
//    }
//
//    public String getSelfintro() {
//        return selfintro;
//    }
//
//    public void setSelfintro(String selfintro) {
//        this.selfintro = selfintro;
//    }
//
//    public String getAttachment() {
//        return attachment;
//    }
//
//    public void setAttachment(String attachment) {
//        this.attachment = attachment;
//    }
//
//    public Timestamp getTime() {
//        return time;
//    }
//
//    public void setTime(Timestamp time) {
//        this.time = time;
//    }
//
//    public Boolean getIsaccepted() {
//        return isaccepted;
//    }
//
//    public void setIsaccepted(Boolean isaccepted) {
//        this.isaccepted = isaccepted;
//    }
//
//    @Override
//    public String toString() {
//        return "Application{" +
//                "applicationid=" + applicationid +
//                ", groupname='" + groupname + '\'' +
//                ", stuname='" + stuname + '\'' +
//                ", stunumber='" + stunumber + '\'' +
//                ", phone='" + phone + '\'' +
//                ", gender='" + gender + '\'' +
//                ", major='" + major + '\'' +
//                ", selfintro='" + selfintro + '\'' +
//                ", attachment='" + attachment + '\'' +
//                ", time=" + time +
//                ", isaccepted=" + isaccepted +
//                '}';
//    }
}