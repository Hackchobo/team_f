package com.green.team_f.main;

import com.green.team_f.main.model.GetDataOfTodayDto;
import com.green.team_f.main.model.GetDataOfTodayVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {
    GetDataOfTodayVo GetDataOfToday(GetDataOfTodayDto dto);
}
