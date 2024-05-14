package com.chinahitech.shop.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Students extends Organization{
    @JsonProperty("username")
    private String stunumber;
    private String stuname;
    private String email;
    private String phone;
//    private String password;

    public String getStunumber() {
        return stunumber;
    }

    public void setStunumber(String stunumber) {
        this.stunumber = stunumber;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return super.getPassword();
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    public String toString() {
        return "Students{stunumber='" + this.stunumber + '\'' + ", stuname='" + this.stuname + '\'' + ", email='" + this.email + '\'' + ", phone='" + this.phone + '\'' + ", password='" + this.getPassword() + '\'' + '}';
    }
}
