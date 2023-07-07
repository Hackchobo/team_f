package com.green.team_f.calender;


import com.green.team_f.calender.model.MonthRecordByUserDto;
import com.green.team_f.calender.model.MonthRecordByUserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CalenderService {
    private final CalenderMapper mapper;
    List<MonthRecordByUserVo> GetMonthRecordByUser(MonthRecordByUserDto dto){
        return mapper.getMonthRecordByUser(dto);
    }
}
