package com.green.team_f.main;

import com.green.team_f.main.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MainService {
    private final MainMapper mapper;

    GetDataOfTodayVo GetDataOfToday(GetDataOfTodayDto dto){
        return mapper.GetDataOfToday(dto);
    }

    GetGraphDataVo2 GetGraphDataByDate(GetGraphDataDto dto){
        List<GetGraphDataVo> voList = mapper.GetGraphDataByDate(dto);
        return  GetGraphDataVo2.builder()
                .graphData(voList)
                .iuser(dto.getIuser())
                .build();
    }
}
