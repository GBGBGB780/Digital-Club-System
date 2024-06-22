package com.chinahitech.shop.bean.notAddedToDatabase;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterUser {
    public String userId;
    public String password;
    public String email;
    public String valicode;

//    public void setUserName(String stunumber) {
//        this.userName = stunumber;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getValicode() {
//        return valicode;
//    }
//
//    public void setValicode(String valicode) {
//        this.valicode = valicode;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
}
