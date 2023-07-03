package com.green.team_f.main;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {
    GetDataOfTodayVo GetDataOfToday(GetDataOfTodayDto dto);
}
