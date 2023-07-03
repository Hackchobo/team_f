package com.green.team_f.calender;

import com.green.team_f.calender.model.MonthRecordByUserDto;
import com.green.team_f.calender.model.MonthRecordByUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CalenderMapper {
    List<MonthRecordByUserVo> getMonthRecordByUser(MonthRecordByUserDto dto);
}
