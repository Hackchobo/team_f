package com.green.team_f.main;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MainService {
    private final MainMapper mapper;

    GetDataOfTodayVo GetDataOfToday(GetDataOfTodayDto dto){
        return mapper.GetDataOfToday(dto);
    }
}
