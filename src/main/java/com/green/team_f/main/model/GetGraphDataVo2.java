package com.green.team_f.main.model;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetGraphDataVo2 {
    private long iuser;
    List<GetGraphDataVo> graphData;
}
