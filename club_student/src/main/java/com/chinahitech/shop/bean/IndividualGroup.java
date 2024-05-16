package com.chinahitech.shop.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IndividualGroup {
    //    private String id;
    @JsonProperty("username")
    private int individualId;
    private String groupId;
    private String studentId;
    private String position;
}
