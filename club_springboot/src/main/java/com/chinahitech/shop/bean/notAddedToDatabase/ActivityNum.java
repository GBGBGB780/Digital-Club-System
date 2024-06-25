package com.chinahitech.shop.bean.notAddedToDatabase;

import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActivityNum{
    private int activityId;
    private int count;
    private String activityName;
}
