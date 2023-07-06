package com.green.team_f.exrec.model;

import lombok.Data;

@Data
public class SelSubInfoForExRecVo extends SelListKcalVo {
    private int time;//시간
    private String helName;//운동이름
    private int hKcal;//분당소모칼로리
    private int totalHelKcal;//분당소모칼*시간
}
