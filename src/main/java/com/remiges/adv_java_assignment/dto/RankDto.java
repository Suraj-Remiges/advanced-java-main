package com.remiges.adv_java_assignment.dto;

import lombok.Data;

@Data
public class RankDto {

    private Integer id;

    private String rankDesc;

    private Integer parentRankId;
}
