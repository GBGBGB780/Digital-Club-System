package com.chinahitech.shop.bean.notAddedToDatabase;

import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupNum{
    private int groupId;
    private int count;
    private String groupName;
}
