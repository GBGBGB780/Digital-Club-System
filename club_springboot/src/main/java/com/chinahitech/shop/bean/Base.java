package com.chinahitech.shop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

/*
时间基类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Base {
    private String createUser;
    private Date createTime;
    private String modifyUser;
    private Date modifyTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Base base)) return false;
        return Objects.equals(createUser, base.createUser) && Objects.equals(createTime, base.createTime) && Objects.equals(modifyUser, base.modifyUser) && Objects.equals(modifyTime, base.modifyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createUser, createTime, modifyUser, modifyTime);
    }
}
